import logo from './logo.svg';
import './App.css';
import Header from './components/Header/Header';
import '@fortawesome/fontawesome-free/css/all.css';
import DragDropFiles from './components/DragDropFiles/DragDropFiles';
import { useState } from 'react';
import InvoicesList from './components/InvoicesList/InvoicesList';

function App() {
  const [nav, setNav] = useState(1);

  return (
    <div className="App">
      <Header setNav={setNav}/>
      {nav == 1 ? (<DragDropFiles setNav={setNav}/>) : (<InvoicesList/>)}
    </div>
  );
}

export default App;
