import React from 'react';

function Home() {
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

