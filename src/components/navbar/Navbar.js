import '../../App.css'
import {Nav, NavLink, NavMenu, NavBtn, NavBtnLink, NavLogo } from './NavbarElements'


function Navbar() {
    return (
        <>
        <Nav>
            <NavLogo to="/">
                Logo
            </NavLogo>
            <NavMenu>
                <NavLink to="/changestrategy" activestyle = "true"> 
                Change Strategy 
                </NavLink>
                <NavLink to="/stats" activestyle = "true"> 
                Stats
                </NavLink>
                <NavLink to="/contact" activestyle = "true"> 
                Contact 
                </NavLink>
                <NavLink to="/about" activestyle = "true"> 
                About 
                </NavLink>
            </NavMenu>
            {/* <NavBtn>
                <NavBtnLink to='/siginin'>Sign in</NavBtnLink>
            </NavBtn> */}
        </Nav>
        </>
    );
}

export default Navbar;