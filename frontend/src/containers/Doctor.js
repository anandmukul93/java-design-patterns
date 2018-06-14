import React, {Component} from "react";
import { Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
// import {TextField} from "material-ui";
import axios from "axios";
   
const doctorRegistration = `http://172.29.132.4:8080/doctor`;
  export default class Doctor extends Component {
    constructor(props){
      super(props);
      this.state = {
        doctor: {
          name: '',
          din: '',
          phone: ''
        }
      }
    }

    validateForm() {
      return this.state.doctor.name.length > 0 && this.state.doctor.din.length > 0 && this.state.doctor.phone.length > 0;
    }
  
    handleChange = event => {
      this.setState({ doctor: Object.assign({}, this.state.doctor, { [event.target.id]: event.target.value }) });
    }
  
    handleSubmit = event => {
      event.preventDefault();
      const newDoctor = this.state.doctor;         
      var config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
      axios.post(doctorRegistration, newDoctor, config)
      .then(res => {
        console.log(res);
        if(res.status == 200){
          alert("Doctor Successfully created!!")
          this.setState({doctor: {name: '', din: '', phone: ''}});
        } else {
          alert("Error creating doctor!!")
        }
      })
    }

    
    render() {
      return (
        <div>
            <h2>
                Doctor Registration
            </h2>
            <Row className="show-grid">
              <Col xs={6} xsOffset={3}>
              <form onSubmit={this.handleSubmit}>
              <FormGroup controlId="name" bsSize="large">
                  <ControlLabel>Name</ControlLabel>
                  <FormControl
                    value={this.state.doctor.name}
                    onChange={this.handleChange}
                    autoComplete="off"
                    type="doctor.name"
                  />
                </FormGroup>
                <FormGroup controlId="din" bsSize="large">
                  <ControlLabel>License Number</ControlLabel>
                  <FormControl
                    autoFocus
                    type="din"
                    autoComplete="off"
                    value={this.state.doctor.din}
                    onChange={this.handleChange}
                  />
                </FormGroup>
                <FormGroup controlId="phone" bsSize="large">
                  <ControlLabel>Phone</ControlLabel>
                  <FormControl
                    value={this.state.doctor.phone}
                    autoComplete="off"
                    onChange={this.handleChange}
                    type="phone"
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