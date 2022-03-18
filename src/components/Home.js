import React, { useState, useEffect } from 'react';
import Greeter from '../artifacts/contracts/Greeter.sol/Greeter.json'
import { ethers } from 'ethers'
import { Link } from 'react-router-dom';
import '../App.css';

const greeterAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"

function Home() {

    return(
        <h1>Current strategy</h1>
    )
}

export default Home;