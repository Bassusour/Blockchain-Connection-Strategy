import Web3 from 'web3'
import React from 'react'
import Nav from './components/navbar/Navbar'
import Home from './components/Home'
import { AddStrategy } from './components/addStrategy/AddStrategy';
import StrategyGrid from './components/currentStrategies/StrategyGrid';
import Footer from './components/footer/Footer';
import "leaflet/dist/leaflet.css";
import "leaflet-area-select";
import { ethers } from 'ethers'
import Greeter from './artifacts/contracts/Greeter.sol/Greeter.json'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css';
import { Circle, MapContainer, TileLayer } from 'react-leaflet'
import icon from 'leaflet/dist/images/marker-icon.png';
import iconShadow from 'leaflet/dist/images/marker-shadow.png';
import './App.css'

let DefaultIcon = L.icon({
    iconUrl: icon,
    shadowUrl: iconShadow
});
L.Marker.prototype.options.icon = DefaultIcon; 
L.Icon.Default.imagePath='leaflet_images/';

const initialPos = [55.78373878553941, 12.518501326376303];
const zoomLv = 13;
const greeterAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3"
const provider = new ethers.providers.JsonRpcProvider("http://localhost:8545");
const contract = new ethers.Contract(greeterAddress, Greeter.abi, provider)

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
    };
  }

  RenderCircle() {
    provider.on("block", async (blockNumber) => {
      console.log("blocknumber: " + blockNumber)
      var _data = await contract.getCircle()
      _data = _data.split(", ")

      if(_data[0] === ""){
        return
      } 

      this.setState((state, props) => ({
        ...state,
        data: _data
      }))
    }) 

    if (this.state.data.length > 0) {
      return <Circle center={[parseFloat(this.state.data[0]), parseFloat(this.state.data[1])]} radius={parseFloat(this.state.data[2])} />
    }
    // return null
  }

  render() {
    return (
      <>
      <Nav/>
      <Home/>
        <div className="background">
          <div className='map'>

          <MapContainer center={initialPos} zoom={zoomLv} id='map'>
            <TileLayer
              url="https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png"
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              maxZoom={20}
            />
            <div>{this.RenderCircle()}</div>
            </MapContainer>
          </div> 
          
          <div className="changestrategy">
            <StrategyGrid/>
          </div>
        </div>
        
        <AddStrategy/>
      <Footer/>
      </>
    );
  }
}

export default App