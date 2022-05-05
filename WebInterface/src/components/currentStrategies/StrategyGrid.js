import React from "react";
import { WidthProvider, Responsive } from "react-grid-layout";
import { ethers } from 'ethers';
import SixG_Strategy from '../../artifacts/contracts/SixG_Strategy.sol/SixG_Strategy.json';
import _ from "lodash";
import '/node_modules/react-grid-layout/css/styles.css'
import '/node_modules/react-resizable/css/styles.css'

const ResponsiveReactGridLayout = WidthProvider(Responsive);

class StrategyGrid extends React.PureComponent {
  static defaultProps = {
    className: "test",
    cols: { lg: 12, md: 10, sm: 6, xs: 10, xxs: 3 },
    rowHeight: 40,
  };

  constructor(props) {
    super(props);
    this.contractAddress = props.contractAddress
    this.provider = props.provider
    this.contract = props.contract

    this.state = {
      items: []
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

  hexToString(str1) {
    var hex  = str1.toString();
    var str = '';
    for (var n = 0; n < hex.length; n += 2) {
      str += String.fromCharCode(parseInt(hex.substr(n, 2), 16));
    }
    return str;
  }

  displayStrategy(strategy, index) {
    // var id = "id" + Math.random().toString(16).slice(2)
    const removeStyle = {
      position: "absolute",
      right: "2px",
      top: 0,
      cursor: "pointer"
    };
    return (
      <div className = "test2" key={index} data-grid={strategy}>
        <span>{this.hexToString(strategy.name)} <br/> {this.hexToString(strategy.description)}</span>
        <span
          className="remove"
          style={removeStyle}
          onClick={this.deleteStrategy.bind(this, index)}
        >
          x
        </span>
      </div>
      
    );
  }

  async deleteStrategy(index) {
    const signer = this.provider.getSigner(0)
    const contract = new ethers.Contract(this.contractAddress, SixG_Strategy.abi, signer)
    const transaction = await contract.deleteStrategy(index)
    
    await transaction.wait()
  }

  updateCurrentStrategies(){
    this.provider.on("block", async (blockNumber) => {
      var _data = await this.contract.getStrategies()

      if(_data.length === 0){
        return 
      } 

    this.setState({ 
      items: _data.map(function(strategy, index, list) {
        return {
          name: strategy.name,
          description: strategy.description,
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
          {_.map(this.state.items, (strategy, index) => this.displayStrategy(strategy, index))}
        </ResponsiveReactGridLayout>
      </div>
    );
  }
}

export default StrategyGrid
// npx hardhat compile
// npx hardhat node
// npx hardhat run .\scripts\deploy_contract.js --network localhost