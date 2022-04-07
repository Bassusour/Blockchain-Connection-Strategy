import React, {useContext} from 'react'
import { StratContext } from '../../App';
import './Card.scss'

function Card(props) {
  var { selectedStrat, setSelectedStrat } = useContext(StratContext)
  console.log(setSelectedStrat)
  return (
    <div className="card">
      <div className="card__body">
        {/* <img src={props.img} className="card__image" /> */}
        <h2 className="card__title">{props.title}</h2>
        <p className="card__description">{props.description}</p>
      </div>
      <button className="card__btn"
              onClick={() => {setSelectedStrat(props.title);
                            //  console.log(selectedStrat)
                            }}>
              Select Strategy</button>
    </div>
  );
}

export default Card