import './App.css';
import LoginForm from "./component/LoginForm.tsx";
import Footer from "./component/Footer.tsx";
import RegisterForm from "./component/Registration.tsx";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./component/Navbar.tsx";
import ForgetPasswordForm from "./component/ForgetPasswordForm.tsx";
import HomePage from "./component/HomePage.tsx";

function App() {
    return (
        <BrowserRouter> {/* Wrap entire app in BrowserRouter */}
            <div className="App">
                <Navbar />
                <div className="content">
                    <Routes> {/* Use Routes instead of nested BrowserRouter */}
                        <Route path="/" element={<LoginForm />} />
                        <Route path="/login" element={<LoginForm />} />  {/* Remove duplicate login route */}
                        <Route path="/register" element={<RegisterForm />} />
                        <Route path="/forget-password" element={<ForgetPasswordForm/>}/>
                        <Route path="/home" element={<HomePage/>}/>

                    </Routes>
                </div>
            </div>
            <Footer />
        </BrowserRouter>
    );
}

export default App;
