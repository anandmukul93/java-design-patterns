import React, {Component} from "react";
import {Alert, Row, Col, Button, FormGroup, FormControl, ControlLabel} from "react-bootstrap";
// import {TextField} from "material-ui";
import axios from "axios";

const validationURL = `http://172.29.132.0:8080/validation/initiate`;