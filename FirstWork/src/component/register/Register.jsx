import  { useState } from "react";
import { Formik, Form, Field } from "formik";
import * as Yup from "yup";
import { Link, useNavigate } from "react-router-dom";
import { Container, Row, Col, Button, FormGroup, Label, Alert } from "reactstrap";
import axiosInstance from "../../axiosInstance.js";

const Registration = () => {
    const navigate = useNavigate();
    const [error, setError] = useState(null);

    const validationSchema = Yup.object().shape({
        firstName: Yup.string().required("First Name is required"),
        email: Yup.string()
            .email("Invalid email address")
            .required("Email is required"),
        passwords: Yup.string()
            .min(6, "Password must be at least 6 characters long")
            .required("Password is required"),
        phoneNumber: Yup.string().required("Phone Number is required"),
    });
    const formikSubmit = async (values, { setSubmitting }) => {
        try {
            console.log("Submitting form with values:", values);
            const { status, data } = await axiosInstance.post("/auth/register", values);
            console.log("Response status:", status);
            console.log("Response data:", data);

            if (status === 200) {
                // Handle successful registration
                alert("Registration successful! Please log in.");
                navigate("/login");
            } else {
                setError(data.message || "An error occurred. Please try again.");
            }
        } catch (error) {
            console.error("Error during form submission:", error);
            setError(error.response?.data?.message || "An error occurred. Please try again.");
        } finally {
            setSubmitting(false);
        }
    };
    return (
        <Container className="my-5">
            <Row className="justify-content-center">
                <Col md={6}>
                    <h1 className="text-center mb-4">Registration</h1>
                    <Formik
                        initialValues={{ firstName: "", email: "", passwords: "", phoneNumber: "" }}
                        validationSchema={validationSchema}
                        onSubmit={formikSubmit}
                    >
                        {({ isSubmitting }) => (
                            <Form>
                                {error && <Alert color="danger">{error}</Alert>}
                                <FormGroup>
                                    <Label for="firstName">First Name</Label>
                                    <Field
                                        type="text"
                                        name="firstName"
                                        id="firstName"
                                        className="form-control"
                                        placeholder="Enter your first name"
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="email">Email</Label>
                                    <Field
                                        type="email"
                                        name="email"
                                        id="email"
                                        className="form-control"
                                        placeholder="Enter your email"
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="passwords">Password</Label>
                                    <Field
                                        type="password"
                                        name="passwords"
                                        id="passwords"
                                        className="form-control"
                                        placeholder="Enter your password"
                                    />
                                </FormGroup>
                                <FormGroup>
                                    <Label for="phoneNumber">Phone Number</Label>
                                    <Field
                                        type="text"
                                        name="phoneNumber"
                                        id="phoneNumber"
                                        className="form-control"
                                        placeholder="Enter your phone number"
                                    />
                                </FormGroup>
                                <Button type="submit" color="primary" block disabled={isSubmitting}>
                                    {isSubmitting ? "Loading..." : "Register"}
                                </Button>
                            </Form>
                        )}
                    </Formik>
                    <Row className="mt-3">
                        <Col>
                            Already registered? <Link to="/login">Login</Link>
                        </Col>
                    </Row>
                </Col>
            </Row>
        </Container>
    );
};

export default Registration;
