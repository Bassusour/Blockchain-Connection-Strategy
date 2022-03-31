import '../../App.css'
import "./Navbar.css"
import { HashLink as Link } from "react-router-hash-link";
// import {Nav, NavLink, NavMenu, NavBtn, NavBtnLink, NavLogo } from './NavbarElements'

function Nav() {
  return (
    <div className="navbar">
      <div className="logo">
        <Link smooth to="#top" className="logo">
          Logo
        </Link>
      </div>
    
      <div className="navMenu">
        <Link smooth to="#changestrategy" 
              // scroll={(el) => el.scrollIntoView({ behavior: 'auto', block: 'end' })}
              className="navigationLink">
          connection strategy
        </Link>
        <Link smooth to="#contact" className="navigationLink">
          Contact
        </Link>
        <Link smooth to="#map" className="navigationLink">
          Map
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