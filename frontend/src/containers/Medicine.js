import React, {Component} from "react";
import {Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
import axios from "axios";

const medicineRegistrationURL = `http://172.29.132.0:8080/prescription`;

export default class Medicine extends Component {
    constructor(props){
        super(props);
        this.state = {
            medicine: {
                pid: '',
                medicine_id: '',
                quantity: '',
                remarks: '',
                no_of_days: '',
                time: ''
            }
        }
    }

    validateForm() {
        return this.state.medicine.pid.length > 0;
    }

    handleChange = event => {
        this.setState({ medicine: Object.assign({}, this.state.medicine, { [event.target.id]: event.target.value }) });
    }

    handleSubmit = event => {
        event.preventDefault();
        const newMedicine = this.state.medicine;
        var config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
        axios.post(medicineRegistrationURL, newMedicine, config)
            .then(res => {
                if(res.status === 200){
                    alert("Medicine Added Successfully!!")
                    this.setState({medicine: {pid: '', medicine_id: '', quantity: '', remarks: '', no_of_days:'', time: ''}});
                } else {
                    alert("Error registrating medicine!!")
                }
            })
            .catch(function (error) {
                alert("Error adding medicine !!", error);
            });
    }

    render() {
        return (
            <div>
                <h2>
                    Medicine Registration
                </h2>
                <Row className="show-grid">
                    <Col xs={6} xsOffset={3}>
                        <form onSubmit={this.handleSubmit}>
                            <FormGroup controlId="medicine_id" bsSize="large">
                                <ControlLabel>Medicine ID</ControlLabel>
                                <FormControl
                                    value={this.state.medicine.medicine_id}
                                    onChange={this.handleChange}
                                    autoComplete="off"
                                    type="text"
                                />
                            </FormGroup>
                            <FormGroup controlId="quantity" bsSize="large">
                                <ControlLabel>quantity</ControlLabel>
                                <FormControl
                                    value={this.state.medicine.quantity}
                                    autoComplete="off"
                                    onChange={this.handleChange}
                                    type="text"
                                />
                            </FormGroup>
                            <FormGroup controlId="remarks" bsSize="large">
                                <ControlLabel>remarks</ControlLabel>
                                <FormControl
                                    autoFocus
                                    type="text"
                                    autoComplete="off"
                                    value={this.state.medicine.remarks}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <FormGroup controlId="no_of_days" bsSize="large">
                                <ControlLabel>no_of_days</ControlLabel>
                                <FormControl
                                    autoFocus
                                    type="text"
                                    autoComplete="off"
                                    value={this.state.medicine.no_of_days}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <FormGroup controlId="time" bsSize="large">
                                <ControlLabel>time</ControlLabel>
                                <FormControl
                                    autoFocus
                                    type="text"
                                    autoComplete="off"
                                    value={this.state.medicine.time}
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