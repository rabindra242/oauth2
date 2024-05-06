import {BrowserRouter, Route, Routes} from 'react-router-dom'
import SiteLayout from './layout/SiteLayout'
// import CSVData from './component/CSVData'
// import AggsData from './component/AggsData'
// import RegisterUser from './component/register/Register'
import LoginUser from './component/login/Login'
import Register from "./component/register/Register.jsx";

// import OAuthLogin from './component/login/OAuthLogin'

function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/home' element={<SiteLayout/>}/>
                <Route path="/login" element={<LoginUser/>}/>
                <Route path="/" element={<LoginUser/>}/>
                <Route path="/register" element={<Register/>}/>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes
