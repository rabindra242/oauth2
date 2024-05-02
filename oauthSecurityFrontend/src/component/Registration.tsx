import { useForm } from "react-hook-form";
import Navbar from "./Navbar";
import { Icon } from "semantic-ui-react";
import Button from "react-bootstrap/Button";
import { useNavigate } from 'react-router-dom';


type Inputs = {
    name: string;
    email: string;
    password: string;
    phoneNumber: string;
};

export default function RegisterForm() {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<Inputs>();

    const onSubmit = (data: Inputs) => {
        // Handle form submission
        console.log(data);
    };

    const handleGoogleSignUp = () => {
        // Implement Google sign-up functionality
    };

    const handleFacebookSignUp = () => {
        // Implement Facebook sign-up functionality
    };
    const navigate = useNavigate();

    return (
        <div>
            <Navbar />
            <section className="vh-100">
                <div className="container-fluid h-custom">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-md-9 col-lg-6 col-xl-5">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" className="img-fluid" alt="Sample image" />
                        </div>
                        <div className="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                            <form onSubmit={handleSubmit(onSubmit)} className="register-form">
                                <div className="divider d-flex align-items-center my-4">
                                    <p className="text-center fw-bold mx-3 mb-0">Register</p>
                                </div>
                                <div data-mdb-input-init className="form-outline mb-4">
                                    <input
                                        type="text"
                                        id="name"
                                        className="form-control form-control-lg"
                                        placeholder="Enter your name"
                                        {...register("name", {
                                            required: "Name is required",
                                        })}
                                    />
                                    {errors.name && <span className="error">{errors.name.message}</span>}
                                </div>
                                <div data-mdb-input-init className="form-outline mb-4">
                                    <input
                                        type="email"
                                        id="email"
                                        className="form-control form-control-lg"
                                        placeholder="Enter your email"
                                        {...register("email", {
                                            required: "Email is required",
                                            pattern: {
                                                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                                                message: "Invalid email address",
                                            },
                                        })}
                                    />
                                    {errors.email && <span className="error">{errors.email.message}</span>}
                                </div>
                                <div data-mdb-input-init className="form-outline mb-4">
                                    <input
                                        type="password"
                                        id="password"
                                        className="form-control form-control-lg"
                                        placeholder="Enter your password"
                                        {...register("password", {
                                            required: "Password is required",
                                            minLength: {
                                                value: 6,
                                                message: "Password must be at least 6 characters",
                                            },
                                        })}
                                    />
                                    {errors.password && <span className="error">{errors.password.message}</span>}
                                </div>
                                <div data-mdb-input-init className="form-outline mb-4">
                                    <input
                                        type="text"
                                        id="phoneNumber"
                                        className="form-control form-control-lg"
                                        placeholder="Enter your phone number"
                                        {...register("phoneNumber", {
                                            required: "Phone number is required",
                                            pattern: {
                                                value: /^\d{10}$/,
                                                message: "Invalid phone number",
                                            },
                                        })}
                                    />
                                    {errors.phoneNumber && <span className="error">{errors.phoneNumber.message}</span>}
                                </div>
                                <>OR</>
                                <div className="text-center mb-4">
                                    <Button variant="outline-primary" onClick={handleGoogleSignUp}>
                                        <Icon name="google" /> Sign up with Google
                                    </Button>{' '}
                                    <Button variant="outline-primary" onClick={handleFacebookSignUp}>
                                        <Icon name="facebook" /> Sign up with Facebook
                                    </Button>
                                </div>
                                <div className="text-center text-lg-start mt-4 pt-2">
                                    <button type="submit" data-mdb-button-init data-mdb-ripple-init className="btn btn-primary btn-lg" style={{ paddingLeft: "2.5rem", paddingRight: "2.5rem" }}>
                                        Register
                                    </button>
                                    <p className="small fw-bold mt-2 pt-1 mb-0">Already have an account? <a href="/login" className="link-danger"  onClick={()=>navigate("/login")}>Login</a></p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}
