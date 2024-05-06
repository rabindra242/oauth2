import {useState} from "react";
import {Field, Form, Formik} from "formik";
import {Button, Label} from "reactstrap";

import {Link, useNavigate} from "react-router-dom";
import {Col, Row} from "react-bootstrap";
import axiosInstance from "../../axiosInstance.js";


const LoginUser = () => {
  const navigate = useNavigate();
  const [userForm, setUserForm] = useState({
    email: "",
    passwords: "",
  });

  function validateEmail(value) {
    let error;
    if (!value) {
      error = "Email is Required";
    } else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(value)) {
      error = "Invalid email address";
    }
    return error;
  }


  function validatePassword(value) {
    let error;
    if (!value) {
      error = "Password is Required";
    } else if (value.length < 6) {
      error = "Password must be at least 6 characters long";
    }
    return error;
  }

  const buttonHandler = async () => {
    try {
      // const response = await axiosInstance.get("/auth/google");
      // console.log(response);
      // const URL = "http://localhost:8080/oauth2/authorization/google";
      const URL = "http://localhost:8080/oauth2/authorization/google"
      window.location.replace(URL)

    } catch (error) {
      console.error("Error:", error);
    }
  };


  const formikSubmit = async (value, action) => {
    try {
      const { status, data } = await axiosInstance.post(
        "/auth/login",
        value
      );
      console.log(status)
      console.log(data)
    //   const token = data?.token;

    //   Cookies.set("auth", "Bearer " + token);
    //   if (status === 200 && Cookies.get("auth")) {
    //     const response = await axiosInstance.get(`/userprofile`);
    //     localStorage.setItem("userprofile", JSON.stringify(response?.data));
    //     setUser(response?.data);
    //     const resData = await getWallet();
    //     console.log(resData?.data?.response);
    //     if (resData?.data?.success === true) {
    //       localStorage.setItem(
    //         "wallet",
    //         JSON.stringify(resData?.data?.response)
    //       );
    //       navigate("/user/wallet");
    //     }touched
    //     // }
    //   }
    } catch (error) {
      console.log(error)
      // navigate("/user/register-wallet");
    }
  };
  return (
    <div className="name" style={{ paddingBottom: "200px" }}>
      <div>
        <h1>Login User!!!</h1>
        <br></br>
        <Formik initialValues={userForm} onSubmit={formikSubmit}>
          {({ errors, touched }) => (
            <Form>
              <div className="form-group">
                <Label>Email</Label>
                <Field
                  className="form-control"
                  name="email"
                  type="email"
                  validate={validateEmail}
                />
                {errors.email && touched.email && (
                  <div style={{ color: "red" }}>{errors.email}</div>
                )}
              </div>

              <div>
                <Label>Password</Label>
                <Field
                  className="form-control"
                  name="passwords"
                  type="text"
                  validate={validatePassword}
                />
                {errors.passwords && touched.passwords && (
                  <div style={{ color: "red" }}>{errors.passwords}</div>
                )}
              </div>
              <br></br>
              <button type="submit" className="btn btn-primary">
                Submit
              </button>
            </Form>
          )}
        </Formik>
        <Row className="py-3">
          <Col>
            New Customer? <Link to={"/register"}>Register</Link>
          </Col>
        </Row>
        <Row className="py-3">
          <Col>
            Sign in using google ? <Button onClick={buttonHandler} >Click here</ Button>
          </Col>
        </Row>
      </div>
    </div>
  );
};

export default LoginUser;