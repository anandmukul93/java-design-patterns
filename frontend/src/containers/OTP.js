import React, {Component} from "react";
import {Alert, Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
// import {TextField} from "material-ui";
import axios from "axios";

const validationURL = `http://localhost:8080/validation/initiate`;