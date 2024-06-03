import { Outlet } from 'react-router-dom';

import ToastContainer from '@/components/common/ToastContainer/ToastContainer';
import ToastFactory from '@/components/common/ToastFactory/ToastFactory';

const App = () => {
  return (
    <>
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
