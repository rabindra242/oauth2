//
// import React, { useState } from "react";
// import * as Yup from "yup";
// import { Formik, Form, Field } from "formik";
// import { Button, FormGroup } from "react-bootstrap";
// import {Label} from "reactstrap";
// import axiosInstance from "../../axiosInstance.js";
//
//
// const cookies = document.cookie.split(';');
// console.log(cookies)
// cookies.forEach(cookie => {
//     const [name, value] = cookie.trim().split('=');
//     if (name === 'email') {
//         console.log("Email from cookie:", value);
//     }
// });
// const Forms = () => {
//     const [error, setError] = useState(null);
//
//     const validationSchema = Yup.object().shape({
//         phoneNumber: Yup.string().required("Phone Number is required"),
//         dateOfBirth: Yup.date().required("Date is required"),
//         jobType: Yup.string().oneOf(["intern", "part-time", "full-time"]).required("Job Type is required"),
//         gender: Yup.string().oneOf(["male", "female"]).required("Gender is required"),
//     });
//
//     const formikSubmit = async (values, { setSubmitting }) => {
//         try {
//             console.log(values);
//             const {status,data}= await axiosInstance.post("/auth/form/post",values)
//             console.log("Response status:", status);
//             console.log("Response data:", data);
//             setSubmitting(true);
//
//         }catch (e){
//             console.log(e)
//         }
//
//     };
//
//     return (
//         <div className="container text-center">
//             <h1>Detail Form</h1>
//             <Formik
//                 initialValues={{
//                     phoneNumber: "",
//                     dateOfBirth: "",
//                     jobType: "",
//                     gender: "",
//                 }}
//                 validationSchema={validationSchema}
//                 onSubmit={formikSubmit}
//             >
//                 {({ isSubmitting }) => (
//                     <Form className="form-control">
//                         {error && <div>{error}</div>}
//                         <FormGroup className="mb-3">
//                             <Label htmlFor="phoneNumber" className="form-label fw-normal">Phone Number</Label>
//                             <Field type="text" name="phoneNumber" className="form-control" />
//                         </FormGroup>
//                         <FormGroup className="mb-3">
//                             <Label htmlFor="dateOfBirth" className="form-label fw-normal">Date of Birth</Label>
//                             <Field type="date" name="dateOfBirth" className="form-control" />
//                         </FormGroup>
//                         <FormGroup className="mb-3">
//                             <Label htmlFor="jobType" className="form-label fw-normal">Job Type</Label>
//                             <Field as="select" name="jobType" className="form-select">
//                                 <option value="intern">Intern</option>
//                                 <option value="part-time">Part-time</option>
//                                 <option value="full-time">Full-time</option>
//                             </Field>
//                         </FormGroup>
//                         <FormGroup className="mb-3">
//                             <Label htmlFor="gender" className="form-label fw-normal">Gender</Label>
//                             <div className="form-check">
//                                 <Field type="radio" name="gender" value="male" className="form-check-input" />
//                                 <Label htmlFor="male" className="form-check-label">Male</Label>
//                             </div>
//                             <div className="form-check">
//                                 <Field type="radio" name="gender" value="female" className="form-check-input" />
//                                 <Label htmlFor="female" className="form-check-label">Female</Label>
//                             </div>
//                         </FormGroup>
//                         <Button type="submit" disabled={isSubmitting} className="btn btn-primary">
//                             {isSubmitting ? "Loading..." : "Submit Form"}
//                         </Button>
//                     </Form>
//                 )}
//             </Formik>
//         </div>
//     );
// };
//
// export default Forms;
import React, { useState } from "react";
import * as Yup from "yup";
import { Formik, Form, Field } from "formik";
import { Button, FormGroup } from "react-bootstrap";
import { Label } from "reactstrap";
import axiosInstance from "../../axiosInstance.js";
import { toast, ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

const Forms = () => {
    const validationSchema = Yup.object().shape({
        phoneNumber: Yup.string()
            .matches(/^[0-9]+$/, 'Phone number must contain only digits')
            .min(10, 'Phone number must be at least 10 digits')
            .max(15, 'Phone number must be at most 15 digits')
            .required('Phone number is required'),
        dateOfBirth: Yup.date().required("Date of Birth is required"),
        jobType: Yup.string().oneOf(["intern", "part-time", "full-time"]).required("Job Type is required"),
        gender: Yup.string().oneOf(["male", "female"]).required("Gender is required"),
    });

    const formikSubmit = async (values, { setSubmitting }) => {
        try {
            console.log(values);
            const { status, data } = await axiosInstance.post("/auth/form/post", values);
            console.log("Response status:", status);
            console.log("Response data:", data);
            toast.success(data.message);
            setSubmitting(false);
        } catch (e) {
            console.log(e);
            if (e.response && e.response.data && e.response.data.message) {
                toast.error(e.response.data.message);
            } else {
                toast.error("An error occurred. Please try again later.");
            }
            setSubmitting(false);
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
                {({ isSubmitting, errors, touched }) => (
                    <Form className="form-control">
                        <FormGroup className="mb-3">
                            <Label htmlFor="phoneNumber" className="form-label fw-normal">Phone Number</Label>
                            <Field type="text" name="phoneNumber" className="form-control" />
                            {errors.phoneNumber && touched.phoneNumber && (
                                <div style={{ color: "red" }}>{errors.phoneNumber}</div>
                            )}
                        </FormGroup>
                        <FormGroup className="mb-3">
                            <Label htmlFor="dateOfBirth" className="form-label fw-normal">Date of Birth</Label>
                            <Field type="date" name="dateOfBirth" className="form-control" />
                            {errors.dateOfBirth && touched.dateOfBirth && (
                                <div style={{ color: "red" }}>{errors.dateOfBirth}</div>
                            )}
                        </FormGroup>
                        <FormGroup className="mb-3">
                            <Label htmlFor="jobType" className="form-label fw-normal">Job Type</Label>
                            <Field as="select" name="jobType" className="form-select">
                                <option value="">Select Job Type</option>
                                <option value="intern">Intern</option>
                                <option value="part-time">Part-time</option>
                                <option value="full-time">Full-time</option>
                            </Field>
                            {errors.jobType && touched.jobType && (
                                <div style={{ color: "red" }}>{errors.jobType}</div>
                            )}
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
                            {errors.gender && touched.gender && (
                                <div style={{ color: "red" }}>{errors.gender}</div>
                            )}
                        </FormGroup>
                        <ToastContainer
                            position="top-right"
                            autoClose={5000}
                            hideProgressBar={false}
                            newestOnTop={false}
                            closeOnClick
                            rtl={false}
                            pauseOnFocusLoss
                            draggable
                            pauseOnHover
                            theme="light"
                        />
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

