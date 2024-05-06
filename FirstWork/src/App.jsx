import { QueryClientProvider } from '@tanstack/react-query'
import './App.css'
import AppRoutes from './AppRoutes'
import queryClient from './queryClient'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'

function App() {
  return (
    <>
    <QueryClientProvider client={queryClient}>
    <AppRoutes/>
    <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
    </>
      )
}

export default App
