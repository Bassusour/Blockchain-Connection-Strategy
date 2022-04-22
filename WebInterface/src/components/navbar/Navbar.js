import '../../App.css'
import "./Navbar.css"
import { HashLink as Link } from "react-router-hash-link";
// import {Nav, NavLink, NavMenu, NavBtn, NavBtnLink, NavLogo } from './NavbarElements'

// The navbar takes 85 pixels space
const scrollWithOffset = (el) => {
    const yCoordinate = el.getBoundingClientRect().top + window.pageYOffset;
    const yOffset = -85; 
    window.scrollTo({ top: yCoordinate + yOffset, behavior: 'smooth' }); 
}

function Nav() {
  return (
    <div className="navbar">
      <div className="logo">
        <Link smooth to="#top" scroll={el => scrollWithOffset(el)} className="logo">
          Logo
        </Link>
      </div>
    
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
    </div>
  );
}

// function Navbar() {
//     return (
//         <Nav>
//             <NavLogo to="/">
//                 Logo
//             </NavLogo>
//             <NavMenu>
//                 <NavLink to="/changestrategy" activestyle = "true"> 
//                 Change Strategy 
//                 </NavLink>
//                 <NavLink to="/stats" activestyle = "true"> 
//                 Stats
//                 </NavLink>
//                 <NavLink to="/contact" activestyle = "true"> 
//                 Contact 
//                 </NavLink>
//                 <NavLink to="/about" activestyle = "true"> 
//                 About 
//                 </NavLink>
//             </NavMenu>
//             {/* <NavBtn>
//                 <NavBtnLink to='/siginin'>Sign in</NavBtnLink>
//             </NavBtn> */}
//         </Nav>
//     );
// }



export default Nav;