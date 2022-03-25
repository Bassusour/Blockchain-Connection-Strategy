import Web3 from 'web3'
import React, { useState } from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/navbar/Navbar'
import Home from './components/Home'
import About from './components/About';
import Contact from './components/Contact';
import ChangeStrategy from './components/ChangeStrategy';
import Stats from './components/Stats';
import Footer from './components/Footer';



function App() {
  return (
    <Router>
      <Navbar />
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/about" element={<About/>}/>
            <Route path="/contact" element={<Contact/>}/>
            <Route path="/changestrategy" element={<ChangeStrategy/>}/>
            <Route path="/stats" element={<Stats/>}/>
        </Routes>
        <Footer/>
    </Router>
    
  );
}

export default App;
