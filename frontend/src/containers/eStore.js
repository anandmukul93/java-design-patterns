import React, {Component} from "react";
import {Alert, Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
// import {TextField} from "material-ui";
import axios from "axios";

export default class eStore extends Component {
    constructor(props) {
        super(props);
        this.state = {
            eStore: {
                pid: ''
            }
        }
    }

    handleChange = event => {
        this.setState({ eStore: Object.assign({}, this.state.eStore, { [event.target.id]: event.target.value }) });
    }

    handleSubmit = event => {
        event.preventDefault();
        const newEStore = this.state.eStore;
        var config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
        axios.post(`http://localhost:8080/validation/initiate`, newEStore, config)
            .then(res => {
                this.setState({showStatus: true});
            })
    }
}
