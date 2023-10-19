import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './SATResult.css';

function SATResult() {
    const [results, setResults] = useState([]);

    const { name } = useParams();

    useEffect(() => {
        fetch(`http://localhost:8080/results/${name}`)
            .then((response) => response.json())
            .then((data) => setResults(data))
            .catch((error) => console.error('Error fetching data:', error));
        console.log(results)
    }, [name]);

    if (!results) {
        return <div> Loading... </div>;
    }

    return (
        <div>
            <h2 className="result-heading">SAT Results</h2>
            <table className="results-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>Country</th>
                        <th>Pincode</th>
                        <th>SAT Score</th>
                        <th>Result</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{results.name}</td>
                        <td>{results.address}</td>
                        <td>{results.city}</td>
                        <td>{results.country}</td>
                        <td>{results.pincode}</td>
                        <td>{results.satScore}</td>
                        <td className={results.result === 'Pass' ? 'pass' : 'fail'}>{results.result}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}

export default SATResult;
