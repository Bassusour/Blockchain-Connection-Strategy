import styled from 'styled-components'
import { NavLink as Link } from 'react-router-dom'

export const Nav = styled.nav`
    background: orangered;
    height: 85px;
    width: 100%;
    display: flex;
    align-items: center;

    /* justify-content: space-around; */
    /* text-align: center; */
    /* padding: 0.2rem */
    /* z-index: 12; */
`

export const NavLogo = styled(Link)`
  color: #fff;
  font-size: 2rem;
  text-decoration: none;
  padding-left: 3%;
  /* justify-content: flex-end; */
  /* align-items: center; */
  /* cursor: pointer; */
`

export const NavMenu = styled.div`
  align-items: center;
  margin-left: auto; 
  margin-right: auto;
  padding-right: 9%;
`

export const NavLink = styled(Link)`
color: #fff;
width: 20%;
/* align-items: center; */
text-decoration: none;
padding: 0 4rem;
margin-left: auto; 
margin-right: auto;
height: 100%;
font-size: 150%;
cursor: pointer;
&:hover {
  color: black;
}
`

export const NavBtn = styled.nav`
  display: flex;
  align-items: center;
  margin-right: 24px;
`

export const NavBtnLink = styled(Link)`
  border-radius: 4px;
  background: transparent;
  padding: 10px 22px;
  color: #fff;
  outline: none;
  border: 1px solid #fff;
  /* cursor: pointer; */
  transition: all 0.2s ease-in-out;
  text-decoration: none;
  /* margin-left: 24px; */

  &:hover {
    transition: all 0.2s ease-in-out;
    background: #fff;
    color: #808080;
  }
`