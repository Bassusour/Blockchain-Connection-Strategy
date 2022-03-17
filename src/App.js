import Web3 from 'web3'
import React, { useState } from 'react'
import { ethers } from 'ethers'
import Greeter from './artifacts/contracts/Greeter.sol/Greeter.json'
import './App.css';

const greeterAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"

function App() {
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

  return (
    <div className="App">
      <div className="App-header">
        <div className="description">
          <h1>Greeter.sol</h1>
          <h3>Full stack dapp using ReactJS and Hardhat</h3>
        </div>
        <div className='costum-buttons'>
        <button style={{ backgroundColor: 'green'}}
                onClick={fetchGreeting}>
                Fetch Greeting
        </button>
        <button style={{ backgroundColor: 'red'}}
                onClick={setGreeting}>
                Set Greeting
        </button>
        </div>
        <input 
          placeholder='Set Greeting'
          onChange={(e) => setMessage(e.target.value)}
          value={message}
        ></input>
      </div>
    </div>
  );
}

export default App;
