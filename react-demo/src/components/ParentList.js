import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ParentList = () => {
    const [parents, setParents] = useState([]);
    const [redirectToChild, setRedirectToChild] = useState(null);
    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize] = useState(2);

    const navigate = useNavigate();

    const indexOfLastParent = currentPage * pageSize;
    const indexOfFirstParent = indexOfLastParent - pageSize;
    const currentParents = parents.slice(indexOfFirstParent, indexOfLastParent);



    useEffect(() => {
        if (redirectToChild) {
            navigate(
                `/child/${redirectToChild.id}`,
                {
                    state: {
                        sender: redirectToChild.sender,
                        receiver: redirectToChild.receiver,
                        totalAmount: redirectToChild.totalAmount
                    }
                }
            );
        }
    }, [redirectToChild, navigate]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/parents?page=${currentPage}`)
            .then(response => response.json())
            .then(data => setParents(data))
            .catch(error => console.error(error));
    }, [currentPage]);

    const handleTotalPaidAmountClick = parent => {
        setRedirectToChild(parent);
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
            <div className="pagination">
                <button
                    onClick={() => setCurrentPage(currentPage - 1)}
                    disabled={currentPage === 0}
                >
                    Previous
                </button>
                <button
                    onClick={() => setCurrentPage(currentPage + 1)}
                    disabled={indexOfLastParent >= parents.length}
                >
                    Next
                </button>
            </div>

        </div>
    );
};

export default ParentList;
