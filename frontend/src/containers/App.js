import React, { Component } from 'react';
import { Row, Col } from "react-bootstrap";
import './App.css';
import Doctor from './Doctor';
import Seller from './Seller';
import {BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";
import Prescription from "./Prescription";

class App extends Component {

    render() {
        return (
            <Router>
                <div>
                    <Row>
                        <Col xs={10} xsOffset={1}>
                            <nav className="navbar navbar-inverse">
                                <div className="container-fluid">
                                    <div className="navbar-header">
                                        <a className="navbar-brand" href="/Doctor">Medical Prescription Validating Application</a>
                                    </div>
                                    <ul className="nav navbar-nav" style={{float: 'right'}}>
                                        <li><a href="/Doctor">Doctor Registration</a></li>
                                        <li><a href="/Seller">Seller Registration</a></li>
                                        <li><a href="/Prescription">Prescription Registration</a></li>
                                    </ul>
                                </div>
                            </nav>
                            <hr />
                            <Row style={{height: '400px'}}>
                                <Switch>
                                    <Route exact path='/Doctor' component={Doctor} />
                                    <Route exact path='/Seller' component={Seller} />
                                    <Route exact path='/Prescription' component={Prescription} />
                                </Switch>
                            </Row>
                            {/* <footer className="container-fluid bg-4 text-center" style={{'borderRadius': '4px'}}>
                                <p>Medical Prescription Validating Application</p> 
                            </footer> */}
                        </Col>
                    </Row>
                </div>
            </Router>
        );
    }
}

export default App;
