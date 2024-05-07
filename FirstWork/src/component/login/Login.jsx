import { useState } from "react";
import { Formik, Form, Field } from "formik";
import * as Yup from "yup";
import { Link, useNavigate } from "react-router-dom";
import { Container, Row, Col, Button, FormGroup, Label, Input, Alert } from "reactstrap";
import axiosInstance from "../../axiosInstance.js";


const LoginUser = () => {
  const navigate = useNavigate();
  const [error, setError] = useState(null);

  const validationSchema = Yup.object().shape({
    email: Yup.string()
        .email("Invalid email address")
        .required("Email is required"),
    password: Yup.string()
        .min(6, "Password must be at least 6 characters long")
        .required("Password is required"),
  });

  const formikSubmit = async (values, { setSubmitting }) => {
    try {
      const response = await axiosInstance.post("/auth/login", values,{
        withCredentials:true,
      });
      console.log("Response:", response);

      if (response.status === 200) {
        // Handle successful login
        console.log("Login successful:", response.data);
        // Add your logic to handle the successful login here
      } else {
        setError(response.data.message || "An error occurred. Please try again.");
      }
    } catch (error) {
      console.error("Error:", error);
      setError("An error occurred. Please try again.");
    } finally {
      setSubmitting(false);
    }
  };


  const buttonHandler = async () => {
    try {
      const URL = "http://localhost:8080/oauth2/authorization/google";
      window.location.replace(URL);
    } catch (error) {
      console.error("Error:", error);
    }
  };
  return (
      <Container className="my-5">
        <Row className="justify-content-center">
          <Col md={6}>
            <h1 className="text-center mb-4">Login</h1>
            <Formik
                initialValues={{ email: "", password: "" }}
                validationSchema={validationSchema}
                onSubmit={formikSubmit}
            >
              {({ isSubmitting }) => (
                  <Form>
                    {error && <Alert color="danger">{error}</Alert>}
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
                      <Label for="password">Password</Label>
                      <Field
                          type="text"
                          name="password"
                          id="password"
                          className="form-control"
                          placeholder="Enter your password"
                      />
                    </FormGroup>
                    <Button type="submit" color="primary" block disabled={isSubmitting}>
                      {isSubmitting ? "Loading..." : "Login"}
                    </Button>
                  </Form>
              )}
            </Formik>
            <Row className="mt-3">
              <Col>
                New Customer? <Link to="/register">Register</Link>
              </Col>
              <Col className="text-right">
                Sign in using Google? <Button color="secondary" onClick={buttonHandler}>Click here</Button>
              </Col>
            </Row>
          </Col>
        </Row>
      </Container>
  );
};

export default LoginUser;
