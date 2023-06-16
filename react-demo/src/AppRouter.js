import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ParentList from './components/ParentList';
import ChildPage from './components/ChildPage';

const AppRouter = () => {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<ParentList />} />
                <Route exact path="/child/:parentId" element={<ChildPage />} />
            </Routes>
        </Router>
    );
};

export default AppRouter;