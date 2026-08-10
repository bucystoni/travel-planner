import { describe, expect, it } from 'vitest'
import { fireEvent, render, screen } from '@testing-library/react'
import App from './App'

describe('App', () => {
    it('shows the page heading', () => {
        render(<App />)

        expect(screen.getByRole('heading', { name: 'Get started' })).toBeInTheDocument()
    })

    it('counts up when the button is clicked', () => {
        render(<App />)
        const button = screen.getByRole('button', { name: 'Count is 0' })

        fireEvent.click(button)

        expect(screen.getByRole('button', { name: 'Count is 1' })).toBeInTheDocument()
    })
})