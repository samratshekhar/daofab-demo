import React, { useEffect, useState } from 'react';

const ParentList = () => {
    const [parents, setParents] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/parents')
            .then(response => response.json())
            .then(data => setParents(data))
            .catch(error => console.error(error));
    }, []);

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
                            <td>{parent.totalAmount}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ParentList;
