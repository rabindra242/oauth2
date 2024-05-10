
import axios from 'axios';
import {Container, Row, Col, Card, CardBody, CardTitle, CardText, Button} from 'reactstrap';

function Home() {
    const downloadExcel = () => {
        axios({
            url: '/downloadExcelFile',
            method: 'GET',
            responseType: 'blob', // Set the response type to blob
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
                            <Button color="primary" >Download The Excel</Button>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default Home;
