
import React, { useState } from "react";
import * as Yup from "yup";
import { Formik, Form, Field } from "formik";
import { Button, FormGroup } from "react-bootstrap";
import {Label} from "reactstrap";
import axiosInstance from "../../axiosInstance.js";


const cookies = document.cookie.split(';');
console.log(cookies)
cookies.forEach(cookie => {
    const [name, value] = cookie.trim().split('=');
    if (name === 'email') {
        console.log("Email from cookie:", value);
    }
});
const Forms = () => {
    const [error, setError] = useState(null);

    const validationSchema = Yup.object().shape({
        phoneNumber: Yup.string().required("Phone Number is required"),
        dateOfBirth: Yup.date().required("Date is required"),
        jobType: Yup.string().oneOf(["intern", "part-time", "full-time"]).required("Job Type is required"),
        gender: Yup.string().oneOf(["male", "female"]).required("Gender is required"),
    });

    const formikSubmit = async (values, { setSubmitting }) => {
        try {
            console.log(values);
            const {status,data}= await axiosInstance.post("/auth/form/post",values)
            console.log("Response status:", status);
            console.log("Response data:", data);
            setSubmitting(true);

        }catch (e){
            console.log(e)
        }

    };

    return (
        <div className="container text-center">
            <h1>Detail Form</h1>
            <Formik
                initialValues={{
                    phoneNumber: "",
                    dateOfBirth: "",
                    jobType: "",
                    gender: "",
                }}
                validationSchema={validationSchema}
                onSubmit={formikSubmit}
            >
                {({ isSubmitting }) => (
                    <Form className="form-control">
                        {error && <div>{error}</div>}
                        <FormGroup className="mb-3">
                            <Label htmlFor="phoneNumber" className="form-label fw-normal">Phone Number</Label>
                            <Field type="text" name="phoneNumber" className="form-control" />
                        </FormGroup>
                        <FormGroup className="mb-3">
                            <Label htmlFor="dateOfBirth" className="form-label fw-normal">Date of Birth</Label>
                            <Field type="date" name="dateOfBirth" className="form-control" />
                        </FormGroup>
                        <FormGroup className="mb-3">
                            <Label htmlFor="jobType" className="form-label fw-normal">Job Type</Label>
                            <Field as="select" name="jobType" className="form-select">
                                <option value="intern">Intern</option>
                                <option value="part-time">Part-time</option>
                                <option value="full-time">Full-time</option>
                            </Field>
                        </FormGroup>
                        <FormGroup className="mb-3">
                            <Label htmlFor="gender" className="form-label fw-normal">Gender</Label>
                            <div className="form-check">
                                <Field type="radio" name="gender" value="male" className="form-check-input" />
                                <Label htmlFor="male" className="form-check-label">Male</Label>
                            </div>
                            <div className="form-check">
                                <Field type="radio" name="gender" value="female" className="form-check-input" />
                                <Label htmlFor="female" className="form-check-label">Female</Label>
                            </div>
                        </FormGroup>
                        <Button type="submit" disabled={isSubmitting} className="btn btn-primary">
                            {isSubmitting ? "Loading..." : "Submit Form"}
                        </Button>
                    </Form>
                )}
            </Formik>
        </div>
    );
};

export default Forms;
