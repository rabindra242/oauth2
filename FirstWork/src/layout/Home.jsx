
import { Container, Row, Col, Card, CardBody, CardTitle, CardText, Button } from 'reactstrap';

function Home() {
    return (
        <Container className="my-5">
            <Row>
                <Col md={6}>
                    <Card>
                        <img src="https://via.placeholder.com/500x300" alt="Product" className="card-img-top" />
                        <CardBody>
                            <CardTitle tag="h5">Product 1</CardTitle>
                            <CardText>This is a description of the product.</CardText>
                            <Button color="primary">Add to Cart</Button>
                        </CardBody>
                    </Card>
                </Col>
                <Col md={6}>
                    <Card>
                        <img src="https://via.placeholder.com/500x300" alt="Product" className="card-img-top" />
                        <CardBody>
                            <CardTitle tag="h5">Product 2</CardTitle>
                            <CardText>This is a description of the product.</CardText>
                            <Button color="primary">Add to Cart</Button>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
            <Row className="mt-5">
                <Col md={4}>
                    <Card>
                        <img src="https://via.placeholder.com/300x200" alt="Product" className="card-img-top" />
                        <CardBody>
                            <CardTitle tag="h5">Product 3</CardTitle>
                            <CardText>This is a description of the product.</CardText>
                            <Button color="primary">Add to Cart</Button>
                        </CardBody>
                    </Card>
                </Col>
                <Col md={4}>
                    <Card>
                        <img src="https://via.placeholder.com/300x200" alt="Product" className="card-img-top" />
                        <CardBody>
                            <CardTitle tag="h5">Product 4</CardTitle>
                            <CardText>This is a description of the product.</CardText>
                            <Button color="primary">Add to Cart</Button>
                        </CardBody>
                    </Card>
                </Col>
                <Col md={4}>
                    <Card>
                        <img src="https://via.placeholder.com/300x200" alt="Product" className="card-img-top" />
                        <CardBody>
                            <CardTitle tag="h5">Product 5</CardTitle>
                            <CardText>This is a description of the product.</CardText>
                            <Button color="primary">Add to Cart</Button>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default Home;
