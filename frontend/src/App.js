import React, { useState } from "react";
import axios from "axios";
import SearchBar from "./components/SearchBar";
import AqiCard from "./components/AqiCard";
import "./App.css";

function App() {
  const [city, setCity] = useState("");
  const [aqiData, setAqiData] = useState(null);
  const [error, setError] = useState("");

  const fetchAqi = async () => {
    if (!city.trim()) {
      setError("Please enter a city name");
      return;
    }

    try {
      setError("");
      const res = await axios.get(`http://localhost:8080/api/aqi?city=${city}`);
      setAqiData(res.data);
    } catch (err) {
      setError("City not found or server error");
      setAqiData(null);
    }
  };

  return (
    <div className="container">
      <h1 className="title">🌍 Air Quality Index Search</h1>

      <SearchBar city={city} setCity={setCity} fetchAqi={fetchAqi} />

      {error && <p className="error">{error}</p>}

      {aqiData && <AqiCard data={aqiData} />}
    </div>
  );
}

export default App;
