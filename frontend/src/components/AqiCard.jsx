import React from "react";

const getCategoryColor = (category) => {
  switch (category) {
    case "Good":
      return "#4CAF50";
    case "Moderate":
      return "#FFEB3B";
    case "Poor":
      return "#FF9800";
    case "Very Poor":
      return "#FF5722";
    default:
      return "#9C27B0"; // Severe
  }
};

const AqiCard = ({ data }) => {
  const bgColor = getCategoryColor(data.category);

  return (
    <div className="aqi-card" style={{ borderLeft: `10px solid ${bgColor}` }}>
      <h2>{data.city}</h2>
      <p><strong>AQI:</strong> {data.aqi}</p>
      <p><strong>Category:</strong> {data.category}</p>
      <p><strong>Dominant Pollutant:</strong> {data.dominantPollutant}</p>
      <p><strong>Timestamp:</strong> {data.timestamp}</p>
    </div>
  );
};

export default AqiCard;
