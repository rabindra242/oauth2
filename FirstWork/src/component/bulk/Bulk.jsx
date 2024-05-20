import React, {useEffect, useState} from 'react';
import {Button, Card, CardBody, CardText, CardTitle, Col, Container, Row, Table} from 'reactstrap';
import axiosInstance from '../../axiosInstance.js';
import Navbar from "../../commons/Navbar.jsx";

function Bulk() {
    const [file, setFile] = useState(null);
    const [currentPage, setCurrentPage] = useState(0);
    const [data, setData] = useState([]);
    const pageSize = 10; // Assuming pageSize is always 10


    useEffect(()=>{
        const fetchData = async () => {
            try {
                const response = await axiosInstance.get(`customers/getAllCustomer/${currentPage}/${pageSize}`);
                setData(response.data.response);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };
        fetchData()
    },[currentPage])

        // const fetchData = async () => {
        //     try {
        //         const response = await axiosInstance.get(`customers/getAllCustomer/${currentPage}/${pageSize}`);
        //         setData(response.data.response);
        //     } catch (error) {
        //         console.error('Error fetching data:', error);
        //     }
        // };

    const handleGetButtonClick = () => {
        setCurrentPage(0)
        // fetchData();
    };

    const handlePreviousPage = () => {
        if (currentPage >= 1) {
            setCurrentPage(currentPage => currentPage - 1);
            // fetchData();
        }
    };

    const handleNextPage = () => {
        setCurrentPage(currentPage => currentPage + 1);
        // fetchData();
    };


    const uploadFile = (event) => {
        const dataFile = event.target.files[0];
        setFile(dataFile);
    };
    const uploadFileNew = (event) => {
        const dataFile = event.target.files[0];
        setFile(dataFile);
    };
    const downloadCustomerExcel=async ()=>{
        await axiosInstance({
            url:'/downloadCustomerExcelFile',
            method:'GET',
            responseType:'blob',
            headers: {
                'Content-Type': 'application/vnd.ms-excel'
            }
        }).then(
            response=>{
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const  link=document.createElement('a');
                link.href=url;
                link.setAttribute("download","Customer.xls");
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }
        )
            .catch(error=>{
                console.error("Error downloading the excelfile:",error)
            })
    };
    const downloadFormat=async ()=>{
        await axiosInstance({
            url:'/downloadFormat',
            method:'GET',
            responseType:'blob',
            headers: {
                'Content-Type': 'application/vnd.ms-excel'
            }
        }).then(
            response=>{
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const  link=document.createElement('a');
                link.href=url;
                link.setAttribute("download","Customer.xls");
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }
        )
            .catch(error=>{
                console.error("Error downloading the excelfile:",error)
            })
    };

    const handleUploadButtonClick = async () => {
        console.log(file);
        const formData = new FormData();
        formData.append('file', file);
        console.log(formData);
        try {
            await axiosInstance.post('/customers/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            console.log('File uploaded successfully');
            // Add any success handling here
        } catch (error) {
            console.error('Error uploading file:', error);
            // Add error handling here
        }
    };
    const handleUploadButtonClick1 = async () => {
        console.log(file);
        const formData = new FormData();
        formData.append('file', file);
        console.log(formData);
        try {
            await axiosInstance.post('/excel/file/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            console.log('File uploaded successfully');
            // Add any success handling here
        } catch (error) {
            console.error('Error uploading file:', error);
            // Add error handling here
        }
    };


    return (
        <>
            <Navbar/>
        <Container className="my-5">
            <Row className="mb-sm-2">
                <Col >
                    <Card>
                        <CardBody>
                            <CardTitle tag="h5">Post</CardTitle>
                            <input
                                type="file"
                                onChange={uploadFile}
                            />
                            <Button color="primary" onClick={handleUploadButtonClick}>
                                Upload File
                            </Button>
                        </CardBody>
                    </Card>
                </Col>
                <Col>
                    {/*<Card>*/}
                    {/*    <img src="https://via.placeholder.com/500x300" alt="Product" className="card-img-top"/>*/}
                    {/*    <CardBody>*/}
                    {/*        <CardTitle tag="h5">Users Data</CardTitle>*/}
                    {/*        <CardText>This is the users data in csv format it is the data save by the user</CardText>*/}
                    {/*        <Button color="primary"  onClick={downloadCustomerExcel}>Download The Excel</Button>*/}
                    {/*    </CardBody>*/}
                    {/*</Card>*/}
                    <Card>
                        <CardBody>
                            <CardTitle tag="h5">User Data</CardTitle>
                            {/*<input*/}
                            {/*    type="file"*/}
                            {/*    onChange={uploadFile}*/}
                            {/*/>*/}
                            <Button color="primary"  onClick={downloadCustomerExcel}>Download The Excel</Button>
                        </CardBody>
                    </Card>

                    <Card>
                        <CardBody>
                            <CardTitle tag="h5">Download Empty Format</CardTitle>
                            <Button color="primary"  onClick={downloadFormat}>Download Empty Format</Button>
                        </CardBody>
                    </Card>
                    <Card>
                        <CardBody>
                            <CardTitle tag="h5">Upload New</CardTitle>
                            <input
                                type="file"
                                onChange={uploadFileNew}
                            />
                            <Button color="primary" onClick={handleUploadButtonClick1}>
                                Upload New File
                            </Button>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Card>
                        <CardBody>
                            <CardTitle tag="h5">Customers Data</CardTitle>
                            {/*<Button color="primary" onClick={handleGetButtonClick}>*/}
                            {/*    Get Data*/}
                            {/*</Button>*/}
                            <div style={{display: "flex",gap: "0.8rem"}}>
                                <Button color="primary" onClick={handlePreviousPage} disabled={currentPage === 0}>
                                    Previous Page
                                </Button>
                                <Button color="primary" onClick={handleNextPage}>
                                    Next Page
                                </Button>
                            </div>

                        </CardBody>
                    </Card>
                    {data.length > 0 && ( // Check if data array is not empty
                        <Table>
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Gender</th>
                                <th>Contact No</th>
                                <th>Country</th>
                            </tr>
                            </thead>
                            <tbody>
                            {data.map((item, index) => (
                                <tr key={index}>
                                    <th scope="row">{item.id}</th>
                                    <td>{item.name}</td>
                                    <td>{item.email}</td>
                                    <td>{item.gender}</td>
                                    <td>{item.contactNo}</td>
                                    <td>{item.country}</td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                    )}
                </Col>
            </Row>
        </Container>
            </>
    );
}

export default Bulk;
