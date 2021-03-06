import '../../App.css'
import "./Navbar.css"
import { HashLink as Link } from "react-router-hash-link";
import { useState } from 'react';

const scrollWithOffset = (el) => {
    const yCoordinate = el.getBoundingClientRect().top + window.pageYOffset;
    const yOffset = -85; 
    window.scrollTo({ top: yCoordinate + yOffset, behavior: 'smooth' }); 
}


function Nav(props) {
  const {updateAddress, userAddress} = props
  const [currentAddress, setCurrentAddress] = useState(userAddress)

  const handleSubmit = (e) => {
    updateAddress(e.target.address.value)
    setCurrentAddress(e.target.address.value)
    e.target.reset();
    e.preventDefault();
  }

  return (
    <div className="navbar">
      {/* <div className="logo">
        <Link smooth to="#top" scroll={el => scrollWithOffset(el)} className="logo">
          Logo
        </Link>
      </div> */}
    
      <div className="navMenu">
        <Link smooth to="#top" scroll={el => scrollWithOffset(el)}
              className="navigationLink">
          Home
        </Link>
        <Link smooth to="#map"  
              scroll={el => scrollWithOffset(el)}
              className="navigationLink">
          Current Strategies
        </Link>
        {/* <Link smooth to="#map" 
              className="navigationLink"
              scroll={el => scrollWithOffset(el)}>
          Map
        </Link> */}
        <Link smooth to="#addStrategy" 
              className="navigationLink"
              scroll={el => scrollWithOffset(el)}>
          Add Strategy
        </Link>
      </div>
        <div className="address">
          <form onSubmit={(e) => handleSubmit(e)}>
            <label className="addressLabel">public address </label>
            <input name="address" type="text" className="addressInput"></input>
            <input type="submit" value="Set address" className="addressBtn"></input>
          </form>
          <p className="currentAddress">Current address: {currentAddress}</p>
        </div>
    </div>
  );
}

export default Nav;