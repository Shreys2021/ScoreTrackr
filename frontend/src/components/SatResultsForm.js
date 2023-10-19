import React, { useState } from 'react';
import './SATResultsForm.css';
import { useNavigate } from 'react-router-dom';

const SATResultsForm = () => {

    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [country, setCountry] = useState('');
    const [pincode, setPincode] = useState('');
    const [satScore, setSatScore] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const satScoreValue = parseInt(satScore);

        if (satScoreValue >= 1 && satScoreValue <= 100) {

            const passed = satScore > 30 ? 'Pass' : 'Fail';

            const formData = {
                name,
                address,
                city,
                country,
                pincode,
                satScore,
                passed,
            };

            fetch('http://localhost:8080/results', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            })
                .then((response) => {
                    if (response.status === 409) {

                        return response.text().then((text) => {
                            setErrorMessage(text);
                            throw new Error(text);
                        });
                    } else {
                        setErrorMessage('')
                        return response.json();
                    }
                })
                .then((data) => {
                    console.log('Form Data:', data);
                    navigate(`/result/${name}`);
                    setName('');
                    setAddress('');
                    setCity('');
                    setCountry('');
                    setPincode('');
                    setSatScore('');

                })
                .catch((error) => console.error('Error:', error));

            console.log('Form Data:', formData)
            console.log('result  :', passed)
        } else {
            setErrorMessage('SAT Score must be between 0 and 100 and ');
        }

    };

    return (
        <div className="form-container">
            <h2>Insert SAT Results</h2>
            <form onSubmit={handleSubmit}>
                {errorMessage && <p className="error-message">{errorMessage}</p>}
                <div className="input-field">
                    <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} required />
                </div>
                <div className="input-field">
                    <input type="text" placeholder="Address" value={address} onChange={(e) => setAddress(e.target.value)} required />
                </div>
                <div className="input-field">
                    <input type="text" placeholder="City" value={city} onChange={(e) => setCity(e.target.value)} required />
                </div>
                <div className="input-field">
                    <input type="text" placeholder="Country" value={country} onChange={(e) => setCountry(e.target.value)} required />
                </div>
                <div className="input-field">
                    <input type="text" placeholder="Pincode" value={pincode} onChange={(e) => setPincode(e.target.value)} required />
                </div>
                <div className="input-field">
                    <input type="number" placeholder="SAT Score" value={satScore} onChange={(e) => setSatScore(e.target.value)} required />
                </div>

                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default SATResultsForm;
