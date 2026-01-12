import React from "react";

const SearchBar = ({ city, setCity, fetchAqi }) => {
  return (
    <div className="search-box">
      <input
        type="text"
        value={city}
        placeholder="Enter city name"
        onChange={(e) => setCity(e.target.value)}
      />
      <button onClick={fetchAqi}>Search</button>
    </div>
  );
};

export default SearchBar;
