import React, { useEffect, useState } from 'react';
import { useParams, useLocation } from 'react-router-dom';

const ChildPage = () => {
    const { parentId } = useParams();
    const [children, setChildren] = useState([]);
    const { state } = useLocation();
    const { sender, receiver, totalAmount } = state;

    useEffect(() => {
        fetch(`http://localhost:8080/api/children?parentId=${parentId}`)
            .then(response => response.json())
            .then(data => setChildren(data))
            .catch(error => console.error(error));
    }, [parentId]);

    return (
        <div>
            <h2>Children for Parent ID: {parentId}</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Sender</th>
                        <th>Receiver</th>
                        <th>Total Amount</th>
                        <th>Paid Amount</th>
                    </tr>
                </thead>
                <tbody>
                    {children.map(child => (
                        <tr key={child.id}>
                            <td>{child.id}</td>
                            <td>{sender}</td>
                            <td>{receiver}</td>
                            <td>{totalAmount}</td>
                            <td>{child.paidAmount}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ChildPage;
