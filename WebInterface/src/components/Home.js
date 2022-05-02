import React, { useState, useEffect } from 'react';
import Greeter from '../artifacts/contracts/SixG_Strategy.sol/SixG_Strategy.json'
import { ethers } from 'ethers'
import { Link } from 'react-router-dom';

const greeterAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"

function Home() {
    const [message, setMessage] = useState("")

  /* async function requestAccount() {
    await window.ethereum.request({ method: 'eth_requestAccounts' })
  } */

  async function fetchGreeting() {
    /* if(typeof window.ethereum !== 'undefined') { */
      /* const provider = new ethers.providers.Web3Provider(window.ethereum) */
      const provider = new ethers.providers.JsonRpcProvider();
      const contract = new ethers.Contract(greeterAddress, Greeter.abi, provider)

      try {
        const data = await contract.greet()
        console.log("Data: " + data)
      } catch(error) {
        console.log("Error: " + error)
      }
    /* } */
  }

  async function setGreeting() {
    if(!message) return
    /* if(typeof window.ethereum !== 'undefined') { */
      /* await requestAccount() */

      /* const provider = new ethers.providers.Web3Provider(window.ethereum) */
      const provider = new ethers.providers.JsonRpcProvider();
      const signer = provider.getSigner()

      const contract = new ethers.Contract(greeterAddress, Greeter.abi, signer)
      const transaction = await contract.setGreeting(message)

      await transaction.wait()
      fetchGreeting()
    /* } */
  }

    return(
      <div className="background">
        <div className="mainframe">
          <h1>Blockchain Connection Strategy</h1>
          <h3>A decentralized app, that updates the connection strategy for costumers</h3>
          <div className="home-buttons">
            <button className="select-strategy-btn">Select strategy</button> 
            <button className="github-btn">GitHub</button>
          </div>
        </div>
        <div className="current-strategy">
          <h1>Current strategy</h1>
          <p>Description of current strategy</p>
        </div>
      </div>
    )
}

export default Home

