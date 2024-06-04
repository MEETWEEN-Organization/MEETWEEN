import App from '@/App';

import { RouterProvider, createBrowserRouter } from 'react-router-dom';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      { index: true, element: <h1>Home</h1> },
      { path: 'login', element: <h1>Login</h1> },
    ],
  },
]);

export const AppRouter = () => {
  return <RouterProvider router={router} />;
};
