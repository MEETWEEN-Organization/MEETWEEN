import { Outlet } from 'react-router-dom';

import ToastContainer from '@/components/common/ToastContainer/ToastContainer';
import ToastFactory from '@/components/common/ToastFactory/ToastFactory';
import Header from '@/components/layout/Header/Header';

const App = () => {
  return (
    <>
      <Header />
      <main>
        <Outlet />
      </main>
      <ToastContainer>
        <ToastFactory />
      </ToastContainer>
    </>
  );
};
export default App;
