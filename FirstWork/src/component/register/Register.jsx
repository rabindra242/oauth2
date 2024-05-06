import {useState} from "react";
import {Field, Form, Formik} from "formik";
import {Button, Label} from "reactstrap";
import {Link, useNavigate} from "react-router-dom";
import {Col, Row} from "react-bootstrap";
import axiosInstance from "../../axiosInstance.js";

const Registration = () => {
    const navigate = useNavigate();
    const [userForm, setUserForm] = useState({
        name: "",
        email: "",
        passwords: "",
        phoneNumber: "",
    });

    // Validation functions...

    const formikSubmit = async (values, actions) => {
        try {
            const {status, data} = await axiosInstance.post(
                "/auth/register",
                values
            );
            console.log(data)
            // Form submission logic...
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div className="registration-container">
            <div className="registration-form">
                <h1>Registration</h1>
                <Formik initialValues={userForm} onSubmit={formikSubmit}>
                    {({errors, touched}) => (
                        <Form>
                            <div className="form-group">
                                <Label>Name</Label>
                                <Field
                                    className="form-control"
                                    name="name"
                                    type="text"
                                    placeholder="Enter your name"
                                />
                            </div>

                            <div className="form-group">
                                <Label>Email</Label>
                                <Field
                                    className="form-control"
                                    name="email"
                                    type="email"
                                    placeholder="Enter your email"
                                />
                            </div>

                            <div className="form-group">
                                <Label>Password</Label>
                                <Field
                                    className="form-control"
                                    name="passwords"
                                    type="passwords"
                                    placeholder="Enter your password"
                                />
                            </div>

                            <div className="form-group">
                                <Label>Phone Number</Label>
                                <Field
                                    className="form-control"
                                    name="phoneNumber"
                                    type="text"
                                    placeholder="Enter your phone number"
                                />
                            </div>

                            <Button type="submit" color="primary">
                                Submit
                            </Button>
                        </Form>
                    )}
                </Formik>
                <Row className="py-3">
                    <Col>
                        Already registered? <Link to={"/login"}>Login</Link>
                    </Col>
                </Row>
            </div>
        </div>
    );
};

export default Registration;
