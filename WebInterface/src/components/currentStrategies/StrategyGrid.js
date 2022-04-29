import React from "react";
import { WidthProvider, Responsive } from "react-grid-layout";
import _ from "lodash";
import '/node_modules/react-grid-layout/css/styles.css'
import '/node_modules/react-resizable/css/styles.css'
import Greeter from '../../artifacts/contracts/Greeter.sol/Greeter.json'
import { ethers } from 'ethers'

const ResponsiveReactGridLayout = WidthProvider(Responsive);
const greeterAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"
const provider = new ethers.providers.JsonRpcProvider("http://localhost:8545");
const contract = new ethers.Contract(greeterAddress, Greeter.abi, provider)

class StrategyGrid extends React.PureComponent {
  static defaultProps = {
    className: "test",
    cols: { lg: 12, md: 10, sm: 6, xs: 10, xxs: 3 },
    rowHeight: 40,
  };

  constructor(props) {
    super(props);

    this.state = {
      items: [],
      // .map(function(element, index) {
      //   return {
      //     name: element.name,
      //     desc: element.desc,
      //     x: (index * 2) % 10,
      //     y: 0,
      //     w: 2,
      //     h: 2,
      //     static: true
      //   };
      // }),
    };
    this.updateCurrentStrategies = this.updateCurrentStrategies.bind(this)
  }

  createElement(element) {
    const removeStyle = {
      position: "absolute",
      right: "2px",
      top: 0,
      cursor: "pointer"
    };
    return (
      <div className = "test2" key={element.lat} data-grid={element}>
        <span>{element.lat} {element.lng} {element.radius}</span>
      </div>
    );
  }

  updateCurrentStrategies(){
    provider.on("block", async (blockNumber) => {
      var _data = await contract.getCircle()
      _data = _data.split(", ")

      if(_data[0] === ""){
        return 
      } 

    this.setState({ 
      items: [{
        lat: _data[0],
        lng: _data[1],
        radius: _data[2]
      }].map(function(element, index, list) {
        return {
          lat: element.lat,
          lng: element.lng,
          radius: element.radius,
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
          {_.map(this.state.items, element => this.createElement(element))}
        </ResponsiveReactGridLayout>
      </div>
    );
  }
}

export default StrategyGrid
// npx hardhat compile
// npx hardhat node
// npx hardhat run .\scripts\deploy_contract.js --network localhost