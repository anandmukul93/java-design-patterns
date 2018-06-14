import React, { Component } from 'react';
// import logo from '../images/logo.svg';
import './App.css';
import Doctor from './Doctor';
import Seller from './Seller';
import {BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";

class App extends Component {

  render() {
    return (
      <Router>
            <div>
               <h2>Welcome to MPVA</h2>
               <ul>
                  <li><Link to={'/Doctor'}>Doctor</Link></li>
                  <li><Link to={'/Seller'}>Seller</Link></li>
               </ul>
               <hr />
               <Switch>
                <Route exact path='/Doctor' component={Doctor} />
                <Route exact path='/Seller' component={Seller} />
               </Switch>
            </div>
         </Router>
    );
  }
}

export default App;
