import SATResultsForm from "./components/SatResultsForm";
import SATResult from "./components/SATResult";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UpdateSatScoreForm from "./components/UpdateSatScore";

function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<SATResultsForm />} />
          <Route path="/result/:name" element={<SATResult />} />
          <Route path="/updateScore" element={<UpdateSatScoreForm />} />
        </Routes>
      </Router>

    </div>
  );
}

export default App;
