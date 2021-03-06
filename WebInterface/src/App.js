import React from 'react'
import Nav from './components/navbar/Navbar'
import Home from './components/Home'
import { AddStrategy } from './components/addStrategy/AddStrategy';
import StrategyGrid from './components/currentStrategies/StrategyGrid';
import Footer from './components/footer/Footer';
import "leaflet/dist/leaflet.css";
import "leaflet-area-select";
import { ethers } from 'ethers'
import SixG_Strategy from './artifacts/contracts/SixG_Strategy.sol/SixG_Strategy.json'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css';
import { Circle, Popup, MapContainer, TileLayer } from 'react-leaflet'
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';
import './App.css'
import { useState } from 'react';

let DefaultIcon = L.icon({
    iconUrl: icon,
    shadowUrl: iconShadow
});
L.Marker.prototype.options.icon = DefaultIcon; 
L.Icon.Default.imagePath='leaflet_images/';

const initialPos = [55.78373878553941, 12.518501326376303];
const zoomLv = 13;  
const contractAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"
// const contractAddress = "0xe086E3F3df3350C4B71E8FA9837A8eB6dE119DfF"
//const provider = new ethers.providers.JsonRpcProvider("http://127.0.0.1:8545");
 const provider = new ethers.providers.JsonRpcProvider("http://172.20.10.10:8545");
const contract = new ethers.Contract(contractAddress, SixG_Strategy.abi, provider)
const now = parseInt((new Date().getTime()/1000).toFixed(0))

function getFloat(num) {
  return num / (10 ** 5)
}

const fillOptions = {
  0: "green",
  1: "yellow",
  2: "red"
}
const prioToString = {
  0: "Low",
  1: "Medium",
  2: "High"
}
const connectionTypeToString = {
  0: "WiFi",
  1: "Data"
}
class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      strategies: [],
      userAddress: localStorage.getItem("userAddress")
    };
    this.updateAddress = this.updateAddress.bind(this)
    this.hexToString = this.hexToString.bind(this)
  }

  componentWillUnmount() {
    // fix Warning: Can't perform a React state update on an unmounted component
    this.setState = (state,callback)=>{
        return;
    };
  }

  updateAddress(addressData) {
    this.setState((state, props) => ({
      ...state,
      userAddress: addressData
    }), () => {
      localStorage.setItem("userAddress", this.state.userAddress)
    });
  }

  getStrategies() {
    provider.on("block", async (blockNumber) => {
      var _data = await contract.getStrategies()

      this.setState((state, props) => ({
        ...state,
        strategies: _data
      }))
    }) 
  }

  hexToString(str1) {
    var hex  = str1.toString();
    var str = '';
    for (var n = 0; n < hex.length; n += 2) {
      str += String.fromCharCode(parseInt(hex.substr(n, 2), 16));
    }
    return str;
  }

  render() {
    return (
      <>
      <Nav updateAddress = {this.updateAddress}
           userAddress = {this.state.userAddress}/>
      <Home/>
        <div className="background">
          <div className='map'>

          <MapContainer center={initialPos} zoom={zoomLv} id='map'>
            <TileLayer
              url="https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              maxZoom={20}
            />
            <div>{this.getStrategies()}</div>

             {this.state.strategies.map((strategy, index) => {
                const now = parseInt((new Date().getTime()/1000).toFixed(0))
                var color = ""
                if(strategy.endDate < now){
                  color = "#A865C9"
                } else if(strategy.startDate < now && strategy.endDate > now){ //active
                  color = "orangered"
                } else { //inactive
                  color = "black"
                }
               return (
                <Circle key={strategy.id} fillColor={fillOptions[strategy.priority]} color={color} center={[getFloat(strategy.location[1]), getFloat(strategy.location[0])]} 
                  radius={getFloat(strategy.location[2])}> 
                  <Popup>{"Name: " + this.hexToString(strategy.name)} <br/>
                         {"Priority: " + prioToString[strategy.priority]} <br/>
                         {/* {"Description: " + this.hexToString(strategy.description)} <br/> */}
                         {"Connection type: " + connectionTypeToString[strategy.connectionType]} <br/>
                         {"Start: " + new Date(parseInt(strategy.startDate*1000)).toLocaleString()} <br/>
                         {"End: " +  new Date(parseInt(strategy.endDate*1000)).toLocaleString()}
                         </Popup>
                </Circle>

              )})}

            </MapContainer>
          </div> 
          
          <div className="changestrategy">
            <StrategyGrid 
              contractAddress = {contractAddress}
              provider = {provider}
              contract = {contract}
              userAddress = {this.state.userAddress}/>
          </div>
        </div>
        
        <AddStrategy
          contractAddress = {contractAddress}
          provider = {provider}
          contract = {contract}
          initialPos = {initialPos}
          userAddress = {this.state.userAddress}
          />
      <Footer/>
      </>
    );
  }
}

export default App
