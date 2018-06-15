import React, {Component} from "react";
import {Row, Col, Form, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
import  Dropdown  from 'react-dropdown';
import 'react-dropdown/style.css'

import axios from "axios";
// import { Checkbox } from "material-ui";

const prescriptionRegistrationURL = `http://localhost:8080/validation`;
const medicineFetchUrl = `http://localhost:8080/medicine`
class Item extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            medicineName : '',
            medicineQuantity: '',
            medicineId: '',
            noOfDays:'',
            time: '',
            notify: false,
            key: this.props.index,
            medicineSelected: null
        }
    }


    handleSelect =  (selectedOption)=>{
        this.setState({medicineName: selectedOption.label, medicineId: selectedOption.value.id, medicineSelected: selectedOption.value.index })
        
        this.props.informParent(this.props.index,this.state);
    }

    handleChangeQuantity = (value) => {
        this.setState({medicineQuantity: value.target.value})
        this.props.informParent(this.props.index,this.state)
    }

    handleChangeDays = (value) => {
        this.setState({noOfDays : value.target.value})
        this.props.informParent(this.props.index,this.state)
    }

    handleTimeChange =  (value) => {
        this.setState({time: value.target.value})
        this.props.informParent(this.props.index,this.state)
    }


    render() {
        let optionItems = this.props.options.map((e,i) => {return {value : {id: e.id, index: i}, label: e.name}});
        let style = {padding: '5px', margin: '15px'}
        let dropdown = {width:'50px'}
        let bg = {backgroundColor:'#337ab7', padding:'20px', width:'700px',marginBottom:'20px'}
        return (
        <div class = "row" style = {bg}>
            <Dropdown onChange =  {this.handleSelect} value = {optionItems[this.state.medicineSelected]} placeholder="Select a medicine" options={optionItems} className = 'dropdown'/>
            <input onChange ={this.handleChangeQuantity} placeholder="Enter quantity" style = {style}/>
            <input onChange = {this.handleChangeDays} placeholder = "Enter no of days" style = {style}/>
            <input onChange = {this.handleTimeChange} placeholder = "Set time" style = {style}/>
            {/* <Checkbox onChange = {() => this.setState({notify : !this.states.notify})} value = {this.state.notify} /> */}
        </div>    
    )
    }
}
export default class Prescription extends Component {
    constructor(props){
        super(props);
        this.state = {
            prescription: {
                doc_id_no: '',
                patient_phone_no: '',
                patient_email: '',
                max_purchase: '',
            },
            prescriptionItems : [],
            submitted: false,
            medicineList: [],
            oldData:null
        }
    }

    addMore  = () => {
		let data = this.state.prescriptionItems;
		let size = data.length + 1;
		data.push({});
        this.setState({prescriptionItems:data});
        this.forceUpdate();
    }
    
    validateForm() {
        return this.state.prescription.doc_id_no.length > 0 && this.state.prescription.patient_phone_no.length > 0 && this.state.prescription.patient_email.length > 0;
    }

    handleChange = event => {
        this.setState({ prescription: Object.assign({}, this.state.prescription, { [event.target.id]: event.target.value }) });
    }

    showPrescriptionListItemView = () => {
        this.setState({submitted: true })
    }

    handleSubmit = event => {
        event.preventDefault();
        const newPrescription = this.state.prescription;
        var config = { headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-origin': '*' } };
        let outer = this;
        this.showPrescriptionListItemView()
        axios.get(medicineFetchUrl)
        .then(res => {
            this.setState({medicineList: res.data})
        })
    }

    getPrescriptionListItemView = (index) => {
       return  <Item  index={index} key = {index} uniqueKey = {index} informParent={this.handleUpdateItem} options={this.state.medicineList}/>;
    }

    handleUpdateItem = (index, data) => {
        this.state.prescriptionItems[index] = data;
    }

    submitPrescriptionItems = () => {
        let data = this.state.prescriptionItems;
        let request = {
            ...this.state.prescription, to_notify: true
        }
        
        request.medicines = this.state.prescriptionItems.map(e => ({type:"MEDICINE",
         medicine_id:e.medicineId, 
         quantity: e.medicineQuantity, 
         remarks: 'DAILY',
         no_of_days: e.noOfDays,
         time: e.time
        }))
        axios.post(prescriptionRegistrationURL, request)
        .then(res =>{
            alert("Prescription: generated with prescription id : " + res.data.prescription_id)
        })
        .catch(err => {
            alert("Error occurred")
        })
    }

    render() {
        let showPrescriptionView = !this.state.submitted;
        let prescriptionListData = this.state.prescriptionItems;
        let style = {padding: '5px', marginLeft: '10px', marginBottom: '10px', border: '2px solid black', backgroundColor: '#337ab7', color:'white'}
        let style2 = {padding: '5px', marginBottom: '10px', border: '2px solid black', backgroundColor: 'red', color:'white'}
        let style3 = {margin:'15px'}
        let button_style = {width:'200px', fontSize:'15px'}
        return (
            <div>
                <h2>
                    Prescription Registration
                </h2>
                <Row className="show-grid">
                    <Col xs={12} xsOffset={1}>
                        <div>
                        <Form inline onSubmit={this.handleSubmit}>
                            <FormGroup controlId="doc_id_no" bsSize="medium">
                                <ControlLabel>Doctor Identification Number</ControlLabel>
                                <FormControl
                                    value={this.state.prescription.doc_id_no}
                                    onChange={this.handleChange}
                                    autoComplete="off"
                                    type="text" style = {style3}
                                />
                            </FormGroup>{' '}
                            <FormGroup controlId="patient_phone_no" bsSize="medium">
                                <ControlLabel>Patient Phone</ControlLabel>
                                <FormControl
                                    value={this.state.prescription.patient_phone_no}
                                    autoComplete="off"
                                    onChange={this.handleChange}
                                    type="phone" style = {style3}
                                />
                            </FormGroup>{' '}
                            <FormGroup controlId="patient_email" bsSize="medium">
                                <ControlLabel>Patient Email</ControlLabel>
                                <FormControl
                                    autoFocus
                                    type="email"
                                    autoComplete="off"
                                    value={this.state.prescription.patient_email}
                                    onChange={this.handleChange} style = {style3}
                                />
                            </FormGroup>{' '}
                            <FormGroup controlId="max_purchase" bsSize="medium">
                                <ControlLabel>Maximum Purchase</ControlLabel>
                                <FormControl
                                    autoFocus
                                    type="text"
                                    autoComplete="off"
                                    value={this.state.prescription.max_purchase}
                                    onChange={this.handleChange}
                                    style = {style3}
                                />
                            </FormGroup>{' '}
                            <FormGroup controlId>

                            {!this.state.submitted &&
                            <Button bsStyle="primary"
                                    block
                                    bsSize="small"
                                    disabled={!this.validateForm()}
                                    type="submit" 
                                    style = {button_style}
                            >
                                Add medicines details
                            </Button>
                            }
                            </FormGroup>{' '}
                        </Form>
                        </div>
                    
                    {this.state.submitted &&  
                    (<div>
                    
				    <Button style = {style2} onClick={() => this.addMore()}>Add medicine</Button>
				    {prescriptionListData.map((item, i) => this.getPrescriptionListItemView(i))}       
			
                    <Button style = {style} onClick={() => this.submitPrescriptionItems()}>Submit Prescription items</Button>                      
                    </div>)
                    }
                    </Col>
                </Row>
            </div>
        );
    }
}