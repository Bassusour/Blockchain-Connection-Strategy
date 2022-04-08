import React, {useContext, useEffect} from 'react'
import { StratContext } from '../../App';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import './Card.scss'
import './Test.css'

function Card(props) {
  var { selectedStrat, setSelectedStrat } = useContext(StratContext)

//   useEffect(() => {
//    console.log('the age has changed', selectedStrat)
// }, [selectedStrat])

  return (
    <div className="card">
      <div className="card__body">
        {/* <img src={props.img} className="card__image" /> */}
        <h2 className="card__title">{props.title}</h2>
        <p className="card__description">{props.description}</p>
      </div>

      <Popup 
        trigger={<button className="card__btn" onClick={console.log("test")}> Details </button>}
        modal
      > 
        <div className="header"> Title </div>        
        <div className="content">          
          Bla bla bla        
          <br />          
          Bla bla bla
        </div>        
      </Popup>
      {/* <Popup
        trigger = { open => (
      <button className="card__btn"
              onClick={() => {setSelectedStrat(props.title);
                              // console.log(selectedStrat)
                            }}>
              Details</button>)}
        modal>
          test
        </Popup> */}
    </div>
  );
}

export default Card