# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

React 19 + Vite frontend for an e-commerce platform, consuming a Spring Boot backend (sibling project `ecommerce-app` in this monorepo-style checkout). State is managed with Redux Toolkit using classic thunk action creators (not RTK slices/createAsyncThunk). Styling is Tailwind CSS v4 (via `@tailwindcss/vite`) plus MUI components for data-heavy admin UI.

## Commands

- `npm run dev` — start Vite dev server (default `http://localhost:5173`)
- `npm run build` — production build
- `npm run preview` — preview the production build
- `npm run lint` — run Oxlint (rules in `.oxlintrc.json`; no ESLint/Prettier in this project)

There is no test runner configured in `package.json` — do not assume Jest/Vitest exist unless you add the tooling yourself.

## Environment

Config comes from Vite env vars in `.env` (gitignored, present locally):
- `VITE_BACKEND_URL` — base URL of the Spring Boot API (e.g. `http://localhost:8080`)
- `VITE_STRIPE_PUBLISHABLE_KEY` — Stripe publishable key for the checkout flow
- `VITE_FRONTEND_URL` — this app's own origin, used for redirect URLs (e.g. PayPal/Stripe return URLs)

All backend calls go through the shared Axios instance in `src/api/api.js`, which prefixes requests with `${VITE_BACKEND_URL}/api` and sends credentials (session cookie auth, not bearer tokens, despite some commented-out JWT code in `store/actions/index.js`).

## Architecture

### State management (Redux, thunk-based)

- `src/store/reducers/store.js` wires up the root store with `configureStore` and hydrates `preloadedState` from `localStorage` (`auth`, `cartItems`, `CHECKOUT_ADDRESS`) so cart/login/checkout-address survive a page refresh.
- All async logic lives in **one file**, `src/store/actions/index.js` — there's no per-domain actions split. Every thunk follows the same shape: dispatch a loading action (`IS_FETCHING`/`CATEGORY_LOADER`/`BUTTON_LOADER`), call `api`, dispatch a data action, dispatch `IS_SUCCESS`, and on failure dispatch `IS_ERROR` with `error?.response?.data?.message`. When adding a new thunk, follow this exact pattern rather than introducing `createAsyncThunk`.
- Loading/error UI state is centralized in `errorReducer.js` (`isLoading`, `errorMessage`, `categoryLoader`, `categoryError`, `btnLoader`) rather than per-domain reducers — most thunks read/write this shared reducer regardless of what data they're fetching.
- Domain reducers: `ProductReducer`, `cartReducer`, `authReducer`, `adminReducer`, `orderReducer`, `paymentMethodReducer`, `sellerReducer` — each is a plain `switch` reducer keyed on string action types (no `createSlice`).
- Cart and checkout address are persisted to `localStorage` manually inside the thunks/action creators (`cartItems`, `auth`, `CHECKOUT_ADDRESS`, `client-secret`) — if you change what a reducer stores, check whether the corresponding thunk also needs updating to keep `localStorage` in sync.
- Admin dashboard thunks (`getOrdersForDashboard`, `updateOrderStatusFromDashboard`, `dashboardProductsAction`, product/category CRUD) take an `isAdmin` flag and switch between `/admin/...` and `/seller/...` endpoints — the frontend reuses the same admin UI for both roles, branching only on the API path.

### Routing & auth guards

- Routing is defined in `src/App.jsx` using `react-router-dom` v7 with nested `<Route>`s.
- Access control is centralized in `src/components/PrivateRoute.jsx`, used as a layout route wrapper with props rather than per-page checks:
  - `publicPage` — for routes only anonymous users should see (login/register); redirects logged-in users to `/`.
  - `adminOnly` — for `/admin/*`; checks `user.roles` for `ROLE_ADMIN`/`ROLE_SELLER`. Sellers are restricted to an allowlist of path prefixes (currently `/admin/orders`, `/admin/products`) inside this same component — when adding a new seller-accessible admin page, update `sellerAllowedPaths` here.
  - No prop — generic "must be logged in" guard.
- Auth state (`user`, roles, addresses) lives in `authReducer` and is read via `useSelector((state) => state.auth)` directly in guards/components rather than through a custom auth hook.

### Admin/seller dashboard area

`src/components/admin/` holds one subfolder per section (`dashboard`, `products`, `categories`, `orders`, `sellers`), each with a list/table component plus an add/update form component (e.g. `AddProductForm.jsx`, `ImageUploadForm.jsx`). `AdminLayout.jsx` + `Sidebar.jsx` provide the shell; data fetching for these pages goes through the `isAdmin`-branching thunks described above, with the `isAdmin` boolean derived from `user.roles` wherever the page is rendered.

### Checkout flow

`Checkout.jsx` drives a 4-step MUI `Stepper` (Address → Payment Method → Order Summary → Payment) using local `activeStep` state, gated on Redux state (`selectedUserCheckoutAddress`, `paymentMethod`) rather than route params. The final step renders either `StripePayment` or `PaypalPayment` depending on `state.payment.paymentMethod`. Stripe uses `@stripe/react-stripe-js` with a client secret fetched via `createStripePaymentSecret` and cached in `localStorage` under `client-secret`.

### Filtering hooks

`src/hooks/` contains `useProductFilter`/`useDashboardProductFilter`, `useCategoryFilter`, `useOrderFilter` — these read `useSearchParams` from the URL and dispatch the corresponding fetch thunk in a `useEffect`, so product/order/category list pages are driven by URL query params (`page`, `sortby`, `category`, `keyword`) rather than local component state. When adding a new filterable list page, follow this hook pattern instead of fetching in the component directly.
