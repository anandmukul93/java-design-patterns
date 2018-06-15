import React, {Component} from "react";
import {Alert, Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
// import {TextField} from "material-ui";
import axios from "axios";

const validationURL = `http://localhost:8080/validation/initiate`;
const otpURL = `http://localhost:8080/validation/validate`;

export default class eStore extends Component {
    constructor(props) {
        super(props);
        this.state = {
            eStore: {
                prescription_id: ''
            },
            otp: {
                prescription_id: '',
                prescription_OTP: '',
                validation_time_stamp: ''
            },
            showForm: false,
            showData: false
        }
    }

    validateForm() {
        return this.state.eStore.prescription_id.length > 0;
    }

    handleChange = event => {
        this.setState({ eStore: Object.assign({}, this.state.eStore, { [event.target.id]: event.target.value }) });
    }

    handleChangeOTP = event => {
        this.setState({ otp: Object.assign({}, this.state.otp, { [event.target.id]: event.target.value }) });
    }

    handleVerification = event => {
        event.preventDefault();
        const newOTP = this.state.otp;
        newOTP.prescription_id = this.state.eStore.prescription_id;
        newOTP.validation_time_stamp = "2018-06-14 23:00:01";
        let config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
        axios.post(otpURL, newOTP, config)
            .then(res => {
                if(res.status === 200){
                    alert("Successfully verified !!")
                    this.setState({showData: true});
                } else {
                    alert("Error in verification!!")
                }
            })
            .catch(function (error) {
                alert("Error in verification!!")
            });
    }

    handleSubmit = event => {
        event.preventDefault();
        const newEStore = this.state.eStore;
        let config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
        axios.post(validationURL, newEStore, config)
            .then(res => {
                if(res.status === 200){
                    alert("OTP sent successfully!!")
                    // display
                    this.setState({showForm: true});
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
                <h4 style={{ textAlign: 'center' }}>
                    e Store
                </h4>
                {!this.state.showData &&
                <Row className="show-grid">
                    {!this.state.showForm &&
                    <Col xs={6} xsOffset={3}>
                        <form onSubmit={this.handleSubmit}>
                            <FormGroup controlId="prescription_id" bsSize="large">
                                <ControlLabel>Prescription ID</ControlLabel>
                                <FormControl
                                    value={this.state.eStore.prescription_id}
                                    onChange={this.handleChange}
                                    autoComplete="off"
                                    type="prescription_id"
                                />
                            </FormGroup>
                            <Button bsStyle="primary"
                                    block
                                    bsSize="large"
                                    disabled={!this.validateForm()}
                                    type="submit"
                            >
                                Submit
                            </Button>
                        </form>
                    </Col>
                    }
                    {this.state.showForm &&
                    <Col xs={6} xsOffset={3}>
                        <form onSubmit={this.handleVerification}>
                            <FormGroup style={{display: 'none'}} controlId="prescription_id" bsSize="large">
                                <ControlLabel>Prescription ID</ControlLabel>
                                <FormControl
                                    value={this.state.otp.prescription_id}
                                    onChange={this.handleChangeOTP}
                                    autoComplete="off"
                                    type="prescription_id"
                                />
                            </FormGroup>
                            <FormGroup controlId="prescription_OTP" bsSize="large">
                                <ControlLabel>OTP</ControlLabel>
                                <FormControl
                                    value={this.state.otp.prescription_OTP}
                                    onChange={this.handleChangeOTP}
                                    autoComplete="off"
                                    type="prescription_OTP"
                                />
                            </FormGroup>
                            <FormGroup style={{display: 'none'}} controlId="validation_time_stamp" bsSize="large">
                                <ControlLabel>validation_time_stamp</ControlLabel>
                                <FormControl
                                    value={this.state.otp.validation_time_stamp}
                                    onChange={this.handleChangeOTP}
                                    autoComplete="off"
                                    type="validation_time_stamp"
                                />
                            </FormGroup>
                            <Button bsStyle="primary"
                                    block
                                    bsSize="large"
                                    disabled={!this.validateForm()}
                                    type="submit"
                            >
                                Verify OTP
                            </Button>
                        </form>
                    </Col>
                    }
                </Row>
                }
                {this.state.showData &&
                <div>
                    <h1>showing data</h1>
                </div>
                }
            </div>
        );
    }
}
