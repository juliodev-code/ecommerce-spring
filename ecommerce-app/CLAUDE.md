# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project context

This directory (`ecommerce-app`) is the Spring Boot backend of a larger monorepo at `../` (`ecommerce-spring`). Sibling directories: `ecommerce-frontend` (React client) and `infraestructure` (Terraform for AWS). Treat this directory as the Java project root for build/test purposes.

## Commands

Build and run (uses the Maven wrapper, no local Maven install needed):

```bash
./mvnw clean install        # full build
./mvnw spring-boot:run       # run the app locally (port 8080)
./mvnw test                  # run all tests
./mvnw test -Dtest=EcommerceAppApplicationTests        # run a single test class
./mvnw test -Dtest=EcommerceAppApplicationTests#contextLoads  # run a single test method
```

The app requires a running PostgreSQL instance matching `src/main/resources/application.properties` (`jdbc:postgresql://localhost:5532/ecommerce`, user `postgres`). `spring.jpa.hibernate.ddl-auto=update` means schema is auto-migrated on startup — there are no SQL migration files.

Stripe payments require a `STRIPE_SECRET_KEY` environment variable (referenced as `${STRIPE_SECRET_KEY}` in `application.properties`).

API docs (springdoc/Swagger) are served at `/swagger-ui/**` and `/v3/api-docs/**` when the app is running.

## Architecture

Standard layered Spring MVC architecture, organized by layer rather than by feature, under `com.juliodev.ecommerce`:

- `controller/` — `@RestController`s, thin; delegate to services and return `ResponseEntity`.
- `service/` — one interface + one `*ServiceImpl` per domain area (e.g. `ProductService`/`ProductServiceImpl`). Business logic and transaction boundaries live here.
- `repositories/` — Spring Data JPA repositories.
- `model/` — JPA entities.
- `payload/` — DTOs used on controller/service boundaries; conversion to/from entities goes through the `ModelMapper` bean (`config/AppConfig.java`), not manual mapping.
- `exceptions/` — `ResourceNotFoundException` and `APIException` are the two app-level exceptions; `MyGlobalExceptionHandler` (`@RestControllerAdvice`) converts them (and bean-validation failures) into JSON error responses. Throw these rather than returning error DTOs directly from services.
- `security/` — JWT-based auth. `WebSecurityConfig` defines the `SecurityFilterChain` and path-based authorization rules; `security/jwt/` has the JWT filter/utils; `security/services/UserDetailsImpl(ServiceImpl)` bridges `User` entities to Spring Security.
- `util/AuthUtil` — fetches the currently-authenticated `User`/email/id from `SecurityContextHolder`; use this in services instead of re-reading `Authentication` manually.
- `config/AppConstants` — shared defaults for pagination/sorting (`pageNumber`, `pageSize`, `sortBy`, `sortOrder`) used as `@RequestParam` defaults across list endpoints.

### Authorization model

Role-based via `AppRole` enum (`ROLE_USER`, `ROLE_SELLER`, `ROLE_ADMIN`), stored through `Role`/`User` entities. URL-prefix-based authorization is configured once in `WebSecurityConfig.filterChain`:

- `/api/public/**`, `/api/auth/**` — open
- `/api/admin/**` — `ROLE_ADMIN`
- `/api/seller/**` — `ROLE_ADMIN` or `ROLE_SELLER`
- everything else — any authenticated user

Many resources (e.g. products) expose near-duplicate endpoints under `/api/admin/...` and `/api/seller/...` that call the same service methods — when adding a seller-facing capability that mirrors an admin one, follow that existing pattern rather than introducing a different authorization mechanism.

JWT is read from either the `Authorization: Bearer` header or an HTTP cookie (name from `spring.app.jwtCookieName`); `AuthTokenFilter` runs before `UsernamePasswordAuthenticationFilter`.

### Other conventions

- List endpoints take `pageNumber`/`pageSize`/`sortBy`/`sortOrder` query params (defaults from `AppConstants`) and return a `*Response` wrapper (e.g. `ProductResponse`) containing the page content plus pagination metadata — follow this shape for new paginated endpoints.
- Image uploads go through `FileService`/`FileServiceImpl`, which writes to the local filesystem under the `project.image` path and returns a generated filename; `image.base.url` is prepended on the frontend/response side to build the full URL.
- Payments: `StripeService`/`StripeServiceImpl` creates/reuses a Stripe `Customer` by email before creating a `PaymentIntent`.
