import React from "react";
import { WidthProvider, Responsive } from "react-grid-layout";
import { ethers } from 'ethers';
import SixG_Strategy from '../../artifacts/contracts/SixG_Strategy.sol/SixG_Strategy.json';
import _ from "lodash";
import '/node_modules/react-grid-layout/css/styles.css'
import '/node_modules/react-resizable/css/styles.css'
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
// import './Card.scss'
import "./strategy.css"

const ResponsiveReactGridLayout = WidthProvider(Responsive);

const prioToString = {
  0: "Low",
  1: "Medium",
  2: "High"
}
const connectionTypeToString = {
  0: "WiFi",
  1: "Data"
}

class StrategyGrid extends React.PureComponent {
  static defaultProps = {
    className: "gridContainer",
    cols: { lg: 12, md: 10, sm: 6, xs: 10, xxs: 3 },
    rowHeight: 50,
  };

  constructor(props) {
    super(props);
    this.contractAddress = props.contractAddress
    this.provider = props.provider
    this.contract = props.contract

    this.state = {
      items: [],
      userAddress: ""
    };
    this.updateCurrentStrategies = this.updateCurrentStrategies.bind(this)
    this.onRemoveItem = this.deleteStrategy.bind(this)
    this.hexToString = this.hexToString.bind(this)
  }

  componentWillUnmount() {
    // fix Warning: Can't perform a React state update on an unmounted component
    this.setState = (state,callback)=>{
        return;
    };
  }

  UNSAFE_componentWillReceiveProps(newProps) {
    this.setState((state, props) => ({
      ...state,
      userAddress: newProps.userAddress
    }));
  }

  // static getDerivedStateFromProps(props, state) {
  //   if (props.currentRow !== state.lastRow) {
  //     return {
  //       userAddress: props.userAddress
  //     };
  //   }
  //   return null;
  // }

  hexToString(str1) {
    var hex  = str1.toString();
    var str = '';
    for (var n = 0; n < hex.length; n += 2) {
      str += String.fromCharCode(parseInt(hex.substr(n, 2), 16));
    }
    return str;
  }

  displayStrategy(strategy) {
    const now = parseInt((new Date().getTime()/1000).toFixed(0))
    try { //Remove exipred strategies
      this.provider.getSigner(this.state.userAddress)
      if(strategy.endDate < now){ 
        this.deleteStrategy(strategy.id)
        return
      }
    } catch{}

    const removeStyle = {
      position: "absolute",
      right: "2px",
      top: 0,
      cursor: "pointer"
    };
    var backgroundColor = ""
    if(strategy.endDate < now){
      backgroundColor = "purple"
    } else if(strategy.startDate < now && strategy.endDate > now){ //active
      backgroundColor = "orangered"
    } else { //inactive
      backgroundColor = "black"
    }

    return (
      <div className = "strategy" key={strategy.id} data-grid={strategy} style={{"backgroundColor": backgroundColor}}>
        <span><b>{this.hexToString(strategy.name)}</b> <br/>
              {backgroundColor === "black" && <p> Inactive </p>}
              {backgroundColor === "orangered" && <p> Active </p>}
              {backgroundColor === "purple" && <p> Exipred </p>}
              <Popup 
                trigger={<button className="detailBtn"> Details </button>}
                modal
              > 
                <div className="header"> {this.hexToString(strategy.name)} </div>        
                <div className="content">          
                  {"Priority: " + prioToString[strategy.priority]} <br/>
                  {"Description: " + this.hexToString(strategy.description)} <br/>
                  {"Connection type: " + connectionTypeToString[strategy.connectionType]} <br/>
                  {"Start: " + new Date(parseInt(strategy.startDate*1000)).toLocaleString()} <br/>
                  {"End: " +  new Date(parseInt(strategy.endDate*1000)).toLocaleString()}
                </div>        
              </Popup>
        </span>
        <span
          className="remove"
          style={removeStyle}
          onClick={this.deleteStrategy.bind(this, parseInt(strategy.id, 16))}
        >
          x
        </span>
      </div>
    );
  }

  async deleteStrategy(id) {
    if (this.state.userAddress === ""){
      alert("Please enter your public address")
      return
    }
    var signer = ""
    try {
      signer = this.provider.getSigner(this.state.userAddress)
    } catch(e) {
      alert("Invalid address")
      return
    }
    const contract = new ethers.Contract(this.contractAddress, SixG_Strategy.abi, signer)
    const transaction = await contract.deleteStrategy(id)
    await transaction.wait()
  }

  updateCurrentStrategies(){
    this.provider.on("block", async (blockNumber) => {
      var _data = await this.contract.getStrategies()

      this.setState({ 
        items: _data.map(function(strategy, index, list) {
          return {
            name: strategy.name,
            description: strategy.description,
            id: strategy.id,
            priority: strategy.priority,
            connectionType: strategy.connectionType,
            startDate: strategy.startDate,
            endDate: strategy.endDate,
            x: (index * 2) % 10,
            y: 0,
            w: 2,
            h: 2,
            static: true
          };
        })
      })
    })
    return null
  }

  render() {
    return (
      <div>
        <div>{this.updateCurrentStrategies()}</div>
        <ResponsiveReactGridLayout
          onLayoutChange={this.onLayoutChange}
          {...this.props}
        >
          {_.map(this.state.items, (strategy) => this.displayStrategy(strategy))}
        </ResponsiveReactGridLayout>
      </div>
    );
  }
}

export default StrategyGrid
// npx hardhat compile
// npx hardhat node
// npx hardhat run .\scripts\deploy_contract.js --network localhost