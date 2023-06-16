import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const ChildPage = () => {
    const { parentId } = useParams();
    const [children, setChildren] = useState([]);

    useEffect(() => {
        fetch(`/api/children?parentId=${parentId}`)
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
                            <td>{child.sender}</td>
                            <td>{child.receiver}</td>
                            <td>{child.totalAmount}</td>
                            <td>{child.paidAmount}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ChildPage;
