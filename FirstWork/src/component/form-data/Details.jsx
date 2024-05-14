import React, { useState, useEffect } from 'react';
import axiosInstance from "../../axiosInstance.js";
import Navbar from "../../commons/Navbar.jsx";

const TableComponent = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axiosInstance.get('/get-formData');
            setData(response.data.response);
            setLoading(false);
        } catch (error) {
            setError('Error fetching data');
            setLoading(false);
        }
    };

    return (
        <div>
            <Navbar />
            {error && <p>{error}</p>}
            {loading ? (
                <p>Loading...</p>
            ) : (
                <table style={{ borderCollapse: 'collapse', width: '100%', marginTop: '20px' }}>
                    <thead>
                    <tr style={{ backgroundColor: '#f2f2f2' }}>
                        <th style={{ border: '1px solid #ddd', padding: '8px', fontWeight: 'bold', textAlign: 'left' }}>ID</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', fontWeight: 'bold', textAlign: 'left' }}>Phone Number</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', fontWeight: 'bold', textAlign: 'left' }}>Date of Birth</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', fontWeight: 'bold', textAlign: 'left' }}>Job Type</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', fontWeight: 'bold', textAlign: 'left' }}>Gender</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', fontWeight: 'bold', textAlign: 'left' }}>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    {data.map((item) => (
                        <tr key={item.email} style={{ backgroundColor: (data.indexOf(item) % 2 === 0) ? '#f2f2f2' : 'white' }}>
                            <td style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>{item.id}</td>
                            <td style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>{item.phoneNumber}</td>
                            <td style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>{item.dateOfBirth}</td>
                            <td style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>{item.jobType}</td>
                            <td style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>{item.gender}</td>
                            <td style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>{item.email}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default TableComponent;
