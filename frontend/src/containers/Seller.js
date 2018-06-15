import React, {Component} from "react";
import {Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
import axios from "axios";

const sellerRegistrationURL = `http://localhost:8080/seller`;

export default class Seller extends Component {
    constructor(props){
        super(props);
        this.state = {
            seller: {
                sid: '',
                name: '',
                phone_number: '',
                address: ''
            }
        }
    }

    validateForm() {
        return this.state.seller.sid.length > 0 && this.state.seller.name.length > 0 && this.state.seller.phone_number.length > 0;
    }

    handleChange = event => {
        this.setState({ seller: Object.assign({}, this.state.seller, { [event.target.id]: event.target.value }) });
    }

    handleSubmit = event => {
        event.preventDefault();
        const newSeller = this.state.seller;
        var config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
        axios.post(sellerRegistrationURL, newSeller)
            .then(res => {
                if(res.status === 200){
                    alert("Seller Successfully Registered!!")
                    this.setState({doctor: {sid: '', name: '', phone_number: '', address: ''}});
                } else {
                    alert("Error registrating seller!!")
                }
            })
            .catch(function (error) {
                alert("Error registrating seller!!", error);
            });
    }


    render() {
        return (
            <div>
                <h4 style={{ textAlign: 'center' }}>
                    Seller Registration
                </h4>
                <Row className="show-grid">
                    <Col xs={6} xsOffset={3}>
                        <form onSubmit={this.handleSubmit}>
                            <FormGroup controlId="sid" bsSize="large">
                                <ControlLabel>Registration Number</ControlLabel>
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
                            <FormGroup controlId="phone_number" bsSize="large">
                                <ControlLabel>Phone</ControlLabel>
                                <FormControl
                                    value={this.state.seller.phone_number}
                                    autoComplete="off"
                                    onChange={this.handleChange}
                                    type="phone_number"
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
