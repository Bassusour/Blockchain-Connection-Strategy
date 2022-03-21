import React, { useState, useEffect } from 'react';
import Card from './Card'
import Greeter from '../artifacts/contracts/Greeter.sol/Greeter.json'
import { ethers } from 'ethers'
import { Link } from 'react-router-dom';
import '../App.css';

function ChangeStrategy() {
  return(
    <div className="background">
    <div className="wrapper">
      <Card
        img="https://thumbs.dreamstime.com/b/connection-icon-white-background-simple-element-illustration-strategy-concept-isolated-editable-logo-symbol-design-can-be-141672484.jpg"
        title="Strategy #1"
        description="Strategy #1 description"
      />

      <Card
        img="https://thumbs.dreamstime.com/b/connection-icon-white-background-simple-element-illustration-strategy-concept-isolated-editable-logo-symbol-design-can-be-141672484.jpg"
        title="Strategy #2"
        description="Strategy #2 description"
      />

      <Card
        img="https://thumbs.dreamstime.com/b/connection-icon-white-background-simple-element-illustration-strategy-concept-isolated-editable-logo-symbol-design-can-be-141672484.jpg"
        title="Strategy #3"
        description="Strategy #3 description"
      />
    </div>

    <div className="wrapper">
      <Card
        img="https://thumbs.dreamstime.com/b/connection-icon-white-background-simple-element-illustration-strategy-concept-isolated-editable-logo-symbol-design-can-be-141672484.jpg"
        title="Strategy #4"
        description="Strategy #4 description"
      />

      <Card
        img="https://thumbs.dreamstime.com/b/connection-icon-white-background-simple-element-illustration-strategy-concept-isolated-editable-logo-symbol-design-can-be-141672484.jpg"
        title="Strategy #5"
        description="Strategy #5 description"
      />

      <Card
        img="https://thumbs.dreamstime.com/b/connection-icon-white-background-simple-element-illustration-strategy-concept-isolated-editable-logo-symbol-design-can-be-141672484.jpg"
        title="Strategy #6"
        description="Strategy #6 description"
      />
    </div>
    </div>
  )
}


export default ChangeStrategy;