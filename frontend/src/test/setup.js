import '@testing-library/jest-dom/vitest'
import { afterEach } from 'vitest'
import { cleanup } from '@testing-library/react'

// Remove the rendered DOM after each test, so tests cannot see
// each other's output. Without this, a second render() in the same
// file makes queries fail with "found multiple elements".
afterEach(() => {
    cleanup()
})