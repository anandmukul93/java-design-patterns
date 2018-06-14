import React, {Component} from "react";
import {Alert, Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
// import {TextField} from "material-ui";
import axios from "axios";

export default class Seller extends Component {
    constructor(props){
        super(props);
        this.state = {
            seller: {
                sid: '',
                name: '',
                phone: '',
                address: ''
            },
            showStatus: false
        }
    }

    validateForm() {
        return this.state.seller.sid.length > 0 && this.state.seller.name.length > 0 && this.state.seller.phone.length > 0;
    }

    handleChange = event => {
        this.setState({ seller: Object.assign({}, this.state.seller, { [event.target.id]: event.target.value }) });
    }

    handleSubmit = event => {
        event.preventDefault();
        const newSeller = this.state.seller;
        var config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
        axios.post(`http://localhost:8080/seller`, newSeller, config)
            .then(res => {
                this.setState({showStatus: true});
            })
    }


    render() {
        return (
            <div>
                <h2>
                    Seller Registration
                </h2>
                <Row className="show-grid">
                    <Col xs={6} xsOffset={3}>
                        <form onSubmit={this.handleSubmit}>
                            <FormGroup controlId="sid" bsSize="large">
                                <ControlLabel>Seller ID</ControlLabel>
                                <FormControl
                                    value={this.state.seller.sid}
                                    onChange={this.handleChange}
                                    autoComplete="off"
                                    type="sid"
                                />
                            </FormGroup>
                            <FormGroup controlId="name" bsSize="large">
                                <ControlLabel>Name</ControlLabel>
                                <FormControl
                                    value={this.state.seller.name}
                                    onChange={this.handleChange}
                                    autoComplete="off"
                                    type="name"
                                />
                            </FormGroup>
                            <FormGroup controlId="phone" bsSize="large">
                                <ControlLabel>Phone</ControlLabel>
                                <FormControl
                                    value={this.state.seller.phone}
                                    autoComplete="off"
                                    onChange={this.handleChange}
                                    type="phone"
                                />
                            </FormGroup>
                            <FormGroup controlId="address" bsSize="large">
                                <ControlLabel>Address</ControlLabel>
                                <FormControl
                                    autoFocus
                                    type="address"
                                    autoComplete="off"
                                    value={this.state.seller.address}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <Button bsStyle="primary"
                                    block
                                    bsSize="large"
                                    disabled={!this.validateForm()}
                                    type="submit"
                            >
                                Register
                            </Button>
                        </form>
                    </Col>
                </Row>
            </div>
        );
    }
}
