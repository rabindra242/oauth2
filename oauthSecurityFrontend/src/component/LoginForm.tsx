import { useForm } from "react-hook-form";
import { Icon } from "semantic-ui-react";
import Button from "react-bootstrap/Button";
import {useNavigate} from 'react-router-dom';


type Inputs = {
    email: string;
    password: string;
};

export default function LoginForm() {
    const { register, handleSubmit, formState: { errors } } = useForm<Inputs>();
    const navigate = useNavigate();

    const onSubmit = (data: Inputs) => {
        // Handle form submission
        console.log(data);
    };


    // const handleGoogleLogin = async () => {
    //     try {
    //
    //         const response = await fetch('http://localhost:8080/oauth2/authorization/google',{
    //             mode:"no-cors"
    //         });
    //         console.log(response)
    //         // window.location.href= response
    //
    //         // Check if the response was successful
    //     //     if (!response.ok) {
    //     //         throw new Error('Network response was not ok');
    //     //     }
    //     //
    //     //     // Extract the JSON response body
    //     //     const responseData = await response.json();
    //     //
    //     //     // Assuming responseData contains the redirect URL from the backend
    //     //     if (responseData.redirectUrl) {
    //     //         // Redirect to the URL received from the backend
    //     //         window.location.href = responseData.redirectUrl;
    //     //     } else {
    //     //         console.error("Redirect URL not found in response");
    //     //     }
    //     } catch (error) {
    //         console.error("Error initiating Google Login:", error);
    //      }
    // }
    const handleGithubLogin = async () => {
        try {
            // Handle the response and redirect to the expected URL
            const redirectUrl = 'http://localhost:8080/oauth2/authorization/github';
            window.location.replace(redirectUrl);
        } catch (error) {
            console.error('Error initiating Google Login:', error);
        }
    };

    const handleGoogleLogin = async () => {
        try {
            // Handle the response and redirect to the expected URL
            const redirectUrl = 'http://localhost:8080/oauth2/authorization/google';
            window.location.replace(redirectUrl);
        } catch (error) {
            console.error('Error initiating Google Login:', error);
        }
    };


    return (
        <div>
            <section className="vh-100">
                <div className="container-fluid h-custom">
                    <div className="row d-flex justify-content-center align-items-center h-100">
                        <div className="col-md-9 col-lg-6 col-xl-5">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                                 className="img-fluid" alt="Sample image"/>
                        </div>
                        <div className="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                            <form onSubmit={handleSubmit(onSubmit)} className="login-form">
                                <div className="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                                    <p className="lead fw-normal mb-0 me-3">Sign in with</p>
                                    <Button color='google' onClick={handleGoogleLogin}>
                                        <Icon name='google'/> Google
                                    </Button>
                                    <Button color='github' onClick={handleGithubLogin}>
                                        <Icon name='github'/> Github
                                    </Button>
                                </div>
                                <div className="divider d-flex align-items-center my-4">
                                    <p className="text-center fw-bold mx-3 mb-0">Or</p>
                                </div>
                                <div data-mdb-input-init className="form-outline mb-4">
                                    <input
                                        type="email"
                                        id="email"
                                        className="form-control form-control-lg"
                                        placeholder="Enter a valid email address"
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
                                <div data-mdb-input-init className="form-outline mb-3">
                                    <input
                                        type="password"
                                        id="password"
                                        className="form-control form-control-lg"
                                        placeholder="Enter password"
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
                                <div className="d-flex justify-content-between align-items-center">
                                    <div className="form-check mb-0">
                                        <input className="form-check-input me-2" type="checkbox" value=""
                                               id="remember"/>
                                        <label className="form-check-label" htmlFor="remember">
                                            Remember me
                                        </label>
                                    </div>
                                    <a href="/forget-password" className="text-body" onClick={() => navigate("/forget-password")}>Forgot password?</a>
                                </div>
                                <div className="text-center text-lg-start mt-4 pt-2">
                                    <button type="submit" data-mdb-button-init data-mdb-ripple-init
                                            className="btn btn-primary btn-lg"
                                            style={{paddingLeft: "2.5rem", paddingRight: "2.5rem"}}>
                                        Login
                                    </button>
                                    <p className="small fw-bold mt-2 pt-1 mb-0">
                                        Don't have an account?
                                        <a href="/register" className="link-danger"
                                           onClick={() => navigate("/register")}>Register</a>
                                    </p>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}

