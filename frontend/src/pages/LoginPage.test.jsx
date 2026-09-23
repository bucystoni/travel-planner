import { describe, it, expect, vi, beforeEach } from 'vitest'
import { render, screen, fireEvent, waitFor } from '@testing-library/react'
import { BrowserRouter } from 'react-router-dom'
import LoginPage from './LoginPage'
import { AuthProvider } from '../context/AuthProvider.jsx'
import { post } from '../api/client.js'

vi.mock('../api/client.js', () => ({
    post: vi.fn()
}))

describe('LoginPage', () => {
    beforeEach(() => {
        localStorage.clear()
        vi.clearAllMocks()
    })

    it('stores the JWT in localStorage after a successful login', async () => {
        post.mockResolvedValue({ jwt: 'fake-jwt-token', username: 'alice', roles: ['ROLE_USER'] })

        render(
            <AuthProvider>
                <BrowserRouter>
                    <LoginPage />
                </BrowserRouter>
            </AuthProvider>
        )

        fireEvent.change(screen.getByLabelText('Username'), { target: { value: 'alice' } })
        fireEvent.change(screen.getByLabelText('Password'), { target: { value: 'secret123' } })
        fireEvent.click(screen.getByRole('button', { name: 'Login' }))

        await waitFor(() => {
            expect(localStorage.getItem('jwt')).toBe('fake-jwt-token')
        })
    })
})