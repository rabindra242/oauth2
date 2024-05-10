
import axios from 'axios';
import {Container, Row, Col, Card, CardBody, CardTitle, CardText, Button} from 'reactstrap';
import axiosInstance from "../axiosInstance.js";
// import {string} from "yup";
// import {l} from "vite/dist/node/types.d-aGj9QkWt.js";

function Home() {
    const downloadExcel = async () => {
        await axiosInstance({
            url: '/downloadExcelFile',
            method:'GET',
            responseType:'blob',
            headers: {
                'Content-Type': 'application/vnd.ms-excel'
            }
        })
            .then(response => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'contents.xls'); // Set the file name
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link); // Cleanup
            })
            .catch(error => {
                console.error('Error downloading Excel file:', error);
            });
    };
    const downloadUserExcel=async ()=>{
        await axiosInstance({
            url:'/downloadUserExcelFile',
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
                link.setAttribute("download","userDetails.xls");
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }
        )
            .catch(error=>{
                console.error("Error downloading the excelfile:",error)
            })
    }

    return (
        <Container className="my-5">
            <Row>
                <Col md={6}>
                    <Card>
                        <img src="https://via.placeholder.com/500x300" alt="Product" className="card-img-top"/>
                        <CardBody>
                            <CardTitle tag="h5">Form Data</CardTitle>
                            <CardText>This is the form data in csv format it is the data save by the user</CardText>
                            <Button color="primary" onClick={downloadExcel}>Download The Excel</Button>
                        </CardBody>
                    </Card>
                </Col>
                <Col md={6}>
                    <Card>
                        <img src="https://via.placeholder.com/500x300" alt="Product" className="card-img-top"/>
                        <CardBody>
                            <CardTitle tag="h5">Users Data</CardTitle>
                            <CardText>This is the users data in csv format it is the data save by the user</CardText>
                            <Button color="primary" onClick={downloadUserExcel} >Download The Excel</Button>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default Home;
