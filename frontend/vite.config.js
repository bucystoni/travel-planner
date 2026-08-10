import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  test: {
    environment: 'jsdom',              // components need a DOM
    setupFiles: './src/test/setup.js', // runs before every test file
  }
})
