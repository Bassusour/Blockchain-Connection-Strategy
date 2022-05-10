import React from 'react';
import { HashLink as Link } from "react-router-hash-link";
import { ethers } from 'ethers'

const scrollWithOffset = (el) => {
    const yCoordinate = el.getBoundingClientRect().top + window.pageYOffset;
    const yOffset = -85; 
    window.scrollTo({ top: yCoordinate + yOffset, behavior: 'smooth' }); 
}

function Home() {
    return(
      <div className="background-2">
        <div className="mainframe">
          <h1>Blockchain Connection Strategy</h1>
          <h3>A decentralized app, that updates the connection strategy for costumers</h3>
          <div className="home-buttons">
            <Link smooth to="#addStrategy" 
                  className="select-strategy-btn"
                  scroll={el => scrollWithOffset(el)}>
              Add Strategy
            </Link>
            <a href="https://github.com/Bassusour/Blockchain-Connection-Strategy/" target="_blank" className="github-btn">GitHub</a>

          </div>
        </div>
      </div>
    )
}

export default Home

