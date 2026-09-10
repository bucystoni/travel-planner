import { describe, it } from 'vitest'
import { render } from '@testing-library/react'
import { BrowserRouter } from 'react-router-dom'
import App from './App'
import { AuthProvider } from './context/AuthProvider.jsx'
import { TripProvider } from './context/TripProvider.jsx'

describe('App', () => {
    it('renders the app with routing', () => {
        render(
            <AuthProvider>
                <TripProvider>
                    <BrowserRouter>
                        <App />
                    </BrowserRouter>
                </TripProvider>
            </AuthProvider>
        )
    })
})