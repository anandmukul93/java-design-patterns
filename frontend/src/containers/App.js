import React, { Component } from 'react';
import { Row, Col } from "react-bootstrap";
import './App.css';
import Doctor from './Doctor';
import Seller from './Seller';
import {BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";

class App extends Component {

  render() {
    return (
      <Router>
            <div>
              <Row>
              <Col xs={10} xsOffset={1}>
               <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                  <div class="navbar-header">
                    <a class="navbar-brand" href="#">Medical Prescription Validating Application</a>
                  </div>
                  <ul class="nav navbar-nav">
                    <li><a href="/Doctor">Doctor Registration</a></li>
                    <li><a href="/Seller">Seller Registration</a></li>
                  </ul>
                </div>
              </nav>
               <hr />
               <Switch>
                <Route exact path='/Doctor' component={Doctor} />
                <Route exact path='/Seller' component={Seller} />
               </Switch>
               </Col>
               </Row>
            </div>
         </Router>
    );
  }
}

export default App;
