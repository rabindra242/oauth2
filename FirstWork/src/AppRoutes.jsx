import {BrowserRouter, Route, Routes} from 'react-router-dom'
import SiteLayout from './layout/SiteLayout'
// import CSVData from './component/CSVData'
// import AggsData from './component/AggsData'
// import RegisterUser from './component/register/Register'
import LoginUser from './component/login/Login'
import Register from "./component/register/Register.jsx";
// import Form from "./component/form/Forms.jsx";
import Forms from "./component/form/Forms.jsx";
import NavScrollExample from "./commons/Navbar.jsx";
import Details from "./component/form-data/Details.jsx";

// import OAuthLogin from './component/login/OAuthLogin'

function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/home' element={<><NavScrollExample/><SiteLayout/></>}/>
                <Route path="/login" element={<LoginUser/>}/>
                <Route path="/" element={<LoginUser/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/form" element={<><NavScrollExample/><Forms/></>}/>
                <Route path="/form-data" element={<Details/>}/>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes
