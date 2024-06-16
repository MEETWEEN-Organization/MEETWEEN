import App from '@/App';

import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import LoginPage from '@/pages/LoginPage/LoginPage';
import MeetCreatePage from '@/pages/MeetCreatePage/MeetCreatePage';
import MeetJoinPage from '@/pages/MeetJoinPage/MeetJoinPage';
import MyMeetPage from '@/pages/MyMeetPage/MyMeetPage';
import OnBoarding from '@/pages/OnBoardingPage/OnBoardingPage';

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      { index: true, element: <OnBoarding /> },
      { path: 'login', element: <LoginPage /> },
      { path: 'create', element: <MeetCreatePage /> },
      { path: 'join', element: <MeetJoinPage /> },
      { path: 'join/:meetId', element: <MeetJoinPage /> },
      { path: 'mymeet', element: <MyMeetPage /> },
    ],
  },
]);

export const AppRouter = () => {
  return <RouterProvider router={router} />;
};
