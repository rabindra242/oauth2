//
// import React, { useState, useEffect } from 'react';
// import Container from 'react-bootstrap/Container';
// import Nav from 'react-bootstrap/Nav';
// import Navbar from 'react-bootstrap/Navbar';
// import axiosInstance from "../axiosInstance.js";
//
// function NavScrollExample() {
//     const [userEmail, setUserEmail] = useState("");
//
//     // Fetch user credentials when component mounts
//     useEffect(() => {
//         getUserCredentials();
//     }, []);
//
//     // Function to fetch user credentials
//     const getUserCredentials = async () => {
//         try {
//             const response = await axiosInstance.get("/userCredentials");
//             // const data = await response.json();
//             setUserEmail(response.data.response);
//             console.log(response.data)
//         } catch (error) {
//             console.error("Error fetching user credentials:", error);
//         }
//     };
//
//     return (
//         <Navbar expand="lg" className="bg-body-tertiary">
//             <Container fluid>
//                 <Navbar.Brand href="#">Navbar scroll</Navbar.Brand>
//                 <Navbar.Toggle aria-controls="navbarScroll" />
//                 <Navbar.Collapse id="navbarScroll">
//                     <Nav
//                         className="me-auto my-2 my-lg-0"
//                         style={{ maxHeight: '100px' }}
//                         navbarScroll
//                     >
//                         <Nav.Link href="/home">Home</Nav.Link>
//                         <Nav.Link href="/form">Form</Nav.Link>
//                         <Nav.Link href="/form-data"> Form Details</Nav.Link>
//
//                         {/* Add more Nav.Link components as needed */}
//                     </Nav>
//                     {/* Display user credentials */}
//                     {userEmail && <Nav.Link>{userEmail}</Nav.Link>}
//                 </Navbar.Collapse>
//             </Container>
//         </Navbar>
//     );
// }
// export default NavScrollExample;
//
// import React, { useState, useEffect } from 'react';
// import Container from 'react-bootstrap/Container';
// import Nav from 'react-bootstrap/Nav';
// import Navbar from 'react-bootstrap/Navbar';
// import axiosInstance from "../axiosInstance.js";
//
// function NavScrollExample() {
//     const [userEmail, setUserEmail] = useState("");
//
//     // Fetch user credentials when component mounts
//     useEffect(() => {
//         getUserCredentials();
//     }, []);
//
//     // Function to fetch user credentials
//     const getUserCredentials = async () => {
//         try {
//             const response = await axiosInstance.get("/userCredentials");
//             // const data = await response.json();
//             setUserEmail(response.data.response);
//             console.log(response.data)
//         } catch (error) {
//             console.error("Error fetching user credentials:", error);
//         }
//     };
//
//     return (
//         <Navbar expand="lg" className="bg-body-tertiary">
//             <Container fluid>
//                 <Navbar.Brand href="#">Navbar scroll</Navbar.Brand>
//                 <Navbar.Toggle aria-controls="navbarScroll" />
//                 <Navbar.Collapse id="navbarScroll">
//                     <Nav
//                         className="me-auto my-2 my-lg-0"
//                         style={{ maxHeight: '100px' }}
//                         navbarScroll
//                     >
//                         <Nav.Link href="/home">Home</Nav.Link>
//                         <Nav.Link href="/form">Form</Nav.Link>
//                         <Nav.Link href="/form-data"> Form Details</Nav.Link>
//
//                         {/* Add more Nav.Link components as needed */}
//                     </Nav>
//                     {/* Display user credentials */}
//                     {userEmail && <Nav.Link>{userEmail}</Nav.Link>}
//                 </Navbar.Collapse>
//             </Container>
//         </Navbar>
//     );
// }
// export default NavScrollExample;
import { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import axiosInstance from "../axiosInstance.js";

function NavScrollExample() {
    const [userEmail, setUserEmail] = useState("");

    // Fetch user credentials when component mounts
    useEffect(() => {
        getUserCredentials();
    }, []);

    // Function to fetch user credentials
    const getUserCredentials = async () => {
        try {
            const response = await axiosInstance.get("/userCredentials");
            setUserEmail(response.data.response);
        } catch (error) {
            console.error("Error fetching user credentials:", error);
        }
    };

    // Function to handle logout
    const handleLogout = async () => {
        try {
            console.log("edee");
            await axiosInstance.post("/logout");

            // Clear each cookie
            document.cookie.split(";").forEach((cookie) => {
                const cookieName = cookie.split("=")[0].trim();
                document.cookie = `${cookieName}=;expires=${new Date(0).toUTCString()};path=/`;
            });

            setUserEmail(""); // Clear user data

            // Redirect to home/login page
            // Add your redirection code here

        } catch (error) {
            console.error("Error logging out:", error);
        }
    };


    return (
        <Navbar expand="lg" className="bg-body-tertiary">
            <Container fluid>
                <Navbar.Brand href="#">Navbar scroll</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="me-auto my-2 my-lg-0"
                        style={{ maxHeight: '100px' }}
                        navbarScroll
                    >
                        <Nav.Link href="/home">Home</Nav.Link>
                        <Nav.Link href="/form">Form</Nav.Link>
                        <Nav.Link href="/form-data"> Form Details</Nav.Link>
                    </Nav>
                    {/* Display user credentials */}
                    {userEmail && <Nav.Link>{userEmail}</Nav.Link>}
                    {/* Logout button */}
                    <Nav.Link  onClick={handleLogout}>Logout</Nav.Link>

                    <Nav.Link href="/login">Login</Nav.Link>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default NavScrollExample;
