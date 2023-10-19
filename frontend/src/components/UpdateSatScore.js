import React, { useState } from 'react';
import './SATResultsForm.css';
import { useNavigate } from 'react-router-dom';

function UpdateSatScoreForm() {
    const [name, setName] = useState('');
    const [newSatScore, setNewSatScore] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleUpdateSatScore = (e) => {
        e.preventDefault();

        fetch(`http://localhost:8080/results/update?name=${name}&newSatScore=${newSatScore}`, {
            method: 'PUT',
        })
            .then((response) => {
                if (response.status === 409) {
                    return response.text().then((text) => {
                        setErrorMessage(text);
                        throw new Error(text);
                    });
                } else if (response.status === 200) {
                } else {
                    setErrorMessage('')
                    return response.json();
                }
            })
            .then((data) => {
                navigate(`/result/${name}`);
                console.log('Updated SAT score:', data);
            })
            .catch((error) => console.error('Error updating SAT score:', error));
    };


    return (
        <div className="form-container">
            <h2>Update SAT Results</h2>
            <form onSubmit={handleUpdateSatScore}>
                {errorMessage && <p className="error-message">{errorMessage}</p>}
                <div className="input-field">
                    <input type="text" placeholder="Candidate Name" value={name} onChange={(e) => setName(e.target.value)} required />
                </div>
                <div className="input-field">
                    <input type="number" placeholder="New SAT Score" value={newSatScore} onChange={(e) => setNewSatScore(e.target.value)} required />
                </div>
                <button type="submit">Update SAT Score</button>
            </form>
        </div>
    );
}

export default UpdateSatScoreForm; 
