/** @type {import('tailwindcss').Config} */
export default {
  darkMode: 'selector',
  content: ["./src/**/*.{html,js,jsx,tsx}"],
  theme: {
    extend: {
      colors:{
        'custom-blue': '#1F509A'
      }
    },
  },
  plugins: [],
}

