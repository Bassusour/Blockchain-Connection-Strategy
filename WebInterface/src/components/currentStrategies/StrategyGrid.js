import React from 'react';
import Strategy from './Strategy'

function ChangeStrategy() {
  return(
    <>
    <div className="wrapper" >
      <Strategy
        title="Strategy #1"
        description="Strategy #1 description"
      />

      <Strategy
        title="Strategy #2"
        description="Strategy #2 description"
      />

      <Strategy
        title="Strategy #3"
        description="Strategy #3 description"
      />
    </div>

    <div className="wrapper">
      <Strategy
        title="Strategy #4"
        description="Strategy #4 description"
      />

      <Strategy
        title="Strategy #5"
        description="Strategy #5 description"
      />

      <Strategy
        title="Strategy #6"
        description="Strategy #6 description"
      />
    </div>
    </>
  )
}


export default ChangeStrategy;