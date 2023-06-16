import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ParentList = () => {
    const [parents, setParents] = useState([]);
    const [redirectToChildId, setRedirectToChildId] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        if (redirectToChildId) {
            navigate(`/child/${redirectToChildId}`);
        }
    }, [redirectToChildId, navigate]);

    useEffect(() => {
        fetch('http://localhost:8080/api/parents')
            .then(response => response.json())
            .then(data => setParents(data))
            .catch(error => console.error(error));
    }, []);

    const handleTotalPaidAmountClick = parentId => {
        setRedirectToChildId(parentId);
    };

    return (
        <div>
            <h1>Parent List</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Sender</th>
                        <th>Receiver</th>
                        <th>Total Amount</th>
                    </tr>
                </thead>
                <tbody>
                    {parents.map(parent => (
                        <tr key={parent.id}>
                            <td>{parent.id}</td>
                            <td>{parent.sender}</td>
                            <td>{parent.receiver}</td>
                            <td onClick={() => handleTotalPaidAmountClick(parent)}>
                                {parent.totalAmount}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ParentList;
