import { useForm } from "react-hook-form";
import Navbar from "./Navbar";
import Button from "react-bootstrap/Button";

type Inputs = {
    email: string;
    oldPassword: string;
    newPassword: string;
    confirmNewPassword: string; // Add confirmation password field
};

export default function ForgetPasswordForm() {
    const {
        register,
        handleSubmit,
        formState: { errors },
        watch,
    } = useForm<Inputs>();
    const onSubmit = (data: Inputs) => {
        // Handle form submission
        console.log(data);
    };
    const handleForgotPasswordSubmit = (data: Inputs) => {
        // Handle forgot password submission
        console.log(data);
    };

    const watchNewPassword = watch("newPassword");
    // const watchConfirmNewPassword = watch("confirmNewPassword");

    const validateConfirmNewPassword = (value: string) => {
        return value === watchNewPassword || "Passwords do not match";
    };

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
                            <form onSubmit={handleSubmit(onSubmit)} className="login-form">
                                {/* Existing login form content */}

                                {/* Forgot password form */}
                                <div className="text-center mb-4">
                                    <p className="text-body">Forgot your password?</p>
                                    <input
                                        type="email"
                                        id="forgotEmail"
                                        className="form-control form-control-lg mb-3"
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
                                    <input
                                        type="password"
                                        id="oldPassword"
                                        className="form-control form-control-lg mb-3"
                                        placeholder="Enter your old password"
                                        {...register("oldPassword", {
                                            required: "Old password is required",
                                        })}
                                    />
                                    {errors.oldPassword && <span className="error">{errors.oldPassword.message}</span>}
                                    <input
                                        type="password"
                                        id="newPassword"
                                        className="form-control form-control-lg mb-3"
                                        placeholder="Enter your new password"
                                        {...register("newPassword", {
                                            required: "New password is required",
                                            minLength: {
                                                value: 6,
                                                message: "Password must be at least 6 characters",
                                            },
                                        })}
                                    />
                                    {errors.newPassword && <span className="error">{errors.newPassword.message}</span>}
                                    <input
                                        type="password"
                                        id="confirmNewPassword"
                                        className="form-control form-control-lg mb-3"
                                        placeholder="Confirm your new password"
                                        {...register("confirmNewPassword", {
                                            required: "Please confirm your new password",
                                            validate: validateConfirmNewPassword,
                                        })}
                                    />
                                    {errors.confirmNewPassword && <span className="error">{errors.confirmNewPassword.message}</span>}
                                    <Button type="submit" variant="primary" onClick={handleSubmit(handleForgotPasswordSubmit)}>
                                        Submit
                                    </Button>
                                </div>

                                {/* End of forgot password form */}

                                <div className="text-center text-lg-start mt-4 pt-2">
                                    {/* Login button and register link */}
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}
