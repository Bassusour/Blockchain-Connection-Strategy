import React from "react";
import { WidthProvider, Responsive } from "react-grid-layout";
import _ from "lodash";
import '/node_modules/react-grid-layout/css/styles.css'
import '/node_modules/react-resizable/css/styles.css'
const ResponsiveReactGridLayout = WidthProvider(Responsive);

/**
 * This layout demonstrates how to use a grid with a dynamic number of elements.
 */
class StrategyGrid extends React.PureComponent {
  static defaultProps = {
    className: "test",
    cols: { lg: 12, md: 10, sm: 6, xs: 10, xxs: 3 },
    rowHeight: 40,
  };

  constructor(props) {
    super(props);

    this.state = {
      items: [{
        name: "name1",
        desc: "desc1"
      }, {
        name: "name2",
        desc: "desc2"
      }].map(function(i, index) {
        return {
          i: i.name,
          desc: i.desc,
          x: (index * 2) % 10,
          y: 0,
          w: 2,
          h: 2,
          static: true
        };
      }),
      newCounter: 0
    };
    console.log(this)
    this.onAddItem = this.onAddItem.bind(this);
    this.onRemoveItem = this.onRemoveItem.bind(this)
  }

  createElement(el) {
    const removeStyle = {
      position: "absolute",
      right: "2px",
      top: 0,
      cursor: "pointer"
    };
    const i = el.i;
    // console.log(i)
    // console.log(el.add)
    return (
      <div className = "test2" key={i} data-grid={el}>
        <span className="text">{i}</span>
          {/* { this.onRemoveItem.bind(this, el) } */}
        <span
          className="remove"
          style={removeStyle}
          // onClick={this.onRemoveItem.bind(this, el)}
        >
          x
        </span>
      </div>
    );
  }

  onAddItem() {
    // console.log("adding", "n" + this.state.newCounter);
    // console.log("cols: " + this.state.cols);
    // console.log(this.state.items.length)
    this.setState({
      // Add a new item. It must have a unique key!
      items: this.state.items.concat({
        i: "n" + this.state.newCounter,
        desc: "blabla",
        x: (this.state.items.length * 2) % (10),
        y: -Infinity, 
        w: 2,
        h: 2
      }),
      // Increment the counter to ensure key is always unique.
      newCounter: this.state.newCounter + 1
    });
  }

  onRemoveItem() {
    // this.setState({ 
    //   items: [{
    //     name: "name3",
    //     desc: "desc3"
    //   }, {
    //     name: "name4",
    //     desc: "desc4"
    //   }, {
    //     name: "name5",
    //     desc: "desc5"
    //   }].map(function(i, index, list) {
    //     // console.log(index)
    //     return {
    //       i: i.name,
    //       desc: i.desc,
    //       x: (index * 2) % 10,
    //       y: 0,
    //       w: 2,
    //       h: 2,
    //       static: true
    //       // add: i === (list.length - 1)
    //     };
    //   })
    // })

    this.setState({
      items: _.reject(this.state.items, { i: "name1" })
    })
    console.log(this.state.items)
  }

  render() {
    return (
      <div>
        <button onClick={this.onAddItem}>Add Item</button>
        <button onClick={this.onRemoveItem}>Remove Item</button>
        <ResponsiveReactGridLayout
          onLayoutChange={this.onLayoutChange}
          {...this.props}
        >
          {/* Initialize list */}
          {_.map(this.state.items, el => this.createElement(el))}
        </ResponsiveReactGridLayout>
      </div>
    );
  }
}

export default StrategyGrid