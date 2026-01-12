# 🌍 Air Quality Index (AQI) Search Application

This is a full-stack web application developed as part of a coding assignment.  
The application allows users to search the **Air Quality Index (AQI)** of a city and displays detailed air quality information in a clean and user-friendly interface.

---

## 📌 Assignment Objective
Develop a search engine that:
- Allows users to search AQI by city name
- Displays meaningful air quality attributes
- Uses a backend service to fetch and cache AQI data
- Renders results using a frontend UI

---

## 🔗 AQI API Used
- **Provider:** AQICN (https://aqicn.org/api/)
- **Endpoint:**

---

## 🏗 System Architecture
React Frontend
↓
Spring Boot REST API
↓
AQICN External API
↓
Caffeine Cache (Performance Optimization)

---

## 🛠 Technology Stack

### Backend
- Java 21
- Spring Boot
- RESTful APIs
- Spring Cache
- Caffeine Cache (expiry & max size)

### Frontend
- React.js
- HTML, CSS, JavaScript
- Axios

---

## ✨ Features
- 🔍 Search air quality by city name
- 📊 Displays AQI value, category, dominant pollutant, and timestamp
- 🎨 AQI category interpretation (Good, Moderate, Poor, etc.)
- ⚡ Cached responses for faster repeated searches
- ❌ Graceful handling of invalid cities or API errors

---

## 🚀 Performance Optimization
- Vendor API responses are cached using **Caffeine Cache**
- Cache expiry time: **10 minutes**
- Maximum cache entries: **1000**
- Improves response time and reduces external API calls

---

## 🧪 How to Run the Application Locally

### 🔹 Backend (Spring Boot)
mvn clean package
java -jar target/aqisearch-1.0.0.jar

Backend runs on:
http://localhost:8080

🔹 Frontend (React)
cd frontend
npm install
npm start

Frontend runs on:
http://localhost:3000


📁 Project Structure
AQI-Search-Application/
├── backend/              # Spring Boot application
├── frontend/             # React frontend
├── screenshots/          # UI screenshots
├── README.md

⚠️ Notes

A valid AQICN API key is required to fetch AQI data.

Build artifacts (target/) are excluded from version control.

Cache settings can be adjusted via configuration.
