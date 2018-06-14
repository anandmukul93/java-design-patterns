import React, {Component} from "react";
import {Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
import axios from "axios";

const prescriptionRegistrationURL = `http://localhost:8080/validation`;

export default class Prescription extends Component {
    constructor(props){
        super(props);
        this.state = {
            prescription: {
                doc_id_no: '',
                patient_phone_no: '',
                patient_email: ''
            }
        }
    }

    validateForm() {
        return this.state.prescription.doc_id_no.length > 0 && this.state.prescription.patient_phone_no.length > 0 && this.state.prescription.patient_email.length > 0;
    }

    handleChange = event => {
        this.setState({ prescription: Object.assign({}, this.state.prescription, { [event.target.id]: event.target.value }) });
    }

    handleSubmit = event => {
        event.preventDefault();
        const newPrescription = this.state.prescription;
        var config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
        axios.post(prescriptionRegistrationURL, newPrescription, config)
            .then(res => {
                if(res.status === 200){
                    alert("Prescription Successfully Registered!!")
                    this.setState({prescription: {doc_id_no: '', patient_phone_no: '', patient_email: ''}});
                } else {
                    alert("Error registrating prescription!!")
                }
            })
            .catch(function (error) {
                alert("Error registrating prescription!!", error);
            });
    }

    render() {
        return (
            <div>
                <h2>
                    Prescription Registration
                </h2>
                <Row className="show-grid">
                    <Col xs={6} xsOffset={3}>
                        <form onSubmit={this.handleSubmit}>
                            <FormGroup controlId="doc_id_no" bsSize="large">
                                <ControlLabel>Doctor Identification Number</ControlLabel>
                                <FormControl
                                    value={this.state.prescription.doc_id_no}
                                    onChange={this.handleChange}
                                    autoComplete="off"
                                    type="text"
                                />
                            </FormGroup>
                            <FormGroup controlId="patient_phone_no" bsSize="large">
                                <ControlLabel>Patient Phone</ControlLabel>
                                <FormControl
                                    value={this.state.prescription.patient_phone_no}
                                    autoComplete="off"
                                    onChange={this.handleChange}
                                    type="phone"
                                />
                            </FormGroup>
                            <FormGroup controlId="patient_email" bsSize="large">
                                <ControlLabel>Patient Email</ControlLabel>
                                <FormControl
                                    autoFocus
                                    type="email"
                                    autoComplete="off"
                                    value={this.state.prescription.patient_email}
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