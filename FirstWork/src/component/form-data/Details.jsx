import React, { useState, useEffect } from 'react';
import axios from 'axios';
import axiosInstance from "../../axiosInstance.js";

const TableComponent = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axiosInstance.get('/get-formData');
            setData(response.data.response);
            console.log(response.data.response)
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <div>
            {data.length > 0 ? (
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Phone Number</th>
                        <th>Date of Birth</th>
                        <th>Job Type</th>
                        <th>Gender</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    {data.map((item) => (
                        <tr key={item.email}>
                            {/*<td>{item.id}</td>*/}
                            <td>{item.phoneNumber}</td>
                            <td>{item.dateOfBirth}</td>
                            <td>{item.jobType}</td>
                            <td>{item.gender}</td>
                            <td>{item.email}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    );
};

export default TableComponent;
