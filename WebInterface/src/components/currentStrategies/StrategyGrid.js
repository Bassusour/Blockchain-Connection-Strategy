import React from "react";
import { WidthProvider, Responsive } from "react-grid-layout";
import _ from "lodash";
import '/node_modules/react-grid-layout/css/styles.css'
import '/node_modules/react-resizable/css/styles.css'
import Greeter from '../../artifacts/contracts/Greeter.sol/Greeter.json'
import { ethers } from 'ethers'
const ResponsiveReactGridLayout = WidthProvider(Responsive);

/**
 * This layout demonstrates how to use a grid with a dynamic number of elements.
 */
class StrategyGrid extends React.PureComponent {
  static defaultProps = {
    className: "test",
    cols: { lg: 12, md: 10, sm: 6, xs: 10, xxs: 3 },
    rowHeight: 40,
  };

  constructor(props) {
    super(props);

    this.state = {
      items: [{
        name: "name1",
        desc: "desc1"
      }, {
        name: "name2",
        desc: "desc2"
      }].map(function(element, index) {
        return {
          name: element.name,
          desc: element.desc,
          x: (index * 2) % 10,
          y: 0,
          w: 2,
          h: 2,
          static: true
        };
      }),
      newCounter: 0
    };
    this.onAddItem = this.onAddItem.bind(this);
    /* this.onRemoveItem = this.onRemoveItem.bind(this) */
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
      <div className = "test2" key={element.name} data-grid={element}>
        <span className="text">{element.name}</span>
          {/* { this.onRemoveItem.bind(this, el) } */}
        <span
          className="remove"
          style={removeStyle}
          // onClick={this.onRemoveItem.bind(this, el)}
        >
          x
        </span>
      </div>
    );
  }

  onAddItem() {
    // console.log("adding", "n" + this.state.newCounter);
    // console.log("cols: " + this.state.cols);
    // console.log(this.state.items.length)
    this.setState({
      // Add a new item. It must have a unique key!
      items: this.state.items.concat({
        name: "n" + this.state.newCounter,
        desc: "blabla",
        x: (this.state.items.length * 2) % (10),
        y: -Infinity, 
        w: 2,
        h: 2
      }),
      // Increment the counter to ensure key is always unique.
      newCounter: this.state.newCounter + 1
    });
  }

  /* onRemoveItem() {
    this.setState({
      items: _.reject(this.state.items, { i: "name1" })
    })
    console.log(this.state.items)
  } */

  async updateCurrentStrategies(){
    const greeterAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"

    const provider = new ethers.providers.JsonRpcProvider("http://localhost:8545");
    const contract = new ethers.Contract(greeterAddress, Greeter.abi, provider)
    var data = ""
    try {
      data = await contract.getCircle()
      console.log("Data from strategyGrid: " + data)
    } catch(error) {
      console.log("Error: " + error)
    }

    this.setState({ 
      items: [{
        name: data,
        desc: "desc3"
      }].map(function(element, index, list) {
        return {
          name: element.name,
          desc: element.desc,
          x: (index * 2) % 10,
          y: 0,
          w: 2,
          h: 2,
          static: true
        };
      })
    })
  }

  render() {
    return (
      <div>
        {/* <button onClick={this.onAddItem}>Add Item</button> */}
        <button onClick={this.updateCurrentStrategies}>Update list</button>
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