# Signum – AI Powered Insurance Claim Processing System

## Overview

Signum is an AI-powered Insurance Claim Processing System designed to simplify the insurance lifecycle for both customers and administrators. The platform enables users to purchase insurance policies, submit claims, upload supporting documents, and track claim status, while providing intelligent assistance through AI-driven policy recommendations, claim guidance, and fraud analysis.

The system combines a modern React frontend with a Spring Boot backend and a dedicated AI microservice to deliver a scalable and intelligent insurance management solution.

---

## Live Application

Frontend Deployment:
https://insurance-frontend-woad.vercel.app/

AI Service Repository:
https://github.com/sanjay-1009/Signum_AI_Service

---

## Features

### Customer Module

* User registration and authentication
* Browse available insurance policies
* Purchase insurance policies
* View purchased policies
* Submit insurance claims
* Upload supporting claim documents
* Track claim status in real time
* AI-powered policy recommendations
* AI chatbot for policy and claim assistance

### Administrator Module

* Secure administrator login
* Manage insurance policies
* Review submitted claims
* Approve or reject insurance claims
* Monitor customer information
* View claim analytics and reports
* AI-assisted fraud detection and claim analysis

### AI Capabilities

* Insurance policy recommendation
* Intelligent claim eligibility guidance
* Policy-related question answering
* Document assistance
* AI-based claim fraud detection
* Natural language conversational support

---

## System Architecture

```
React Frontend
       │
       ▼
Spring Boot REST API
       │
       ├──────────────► MySQL Database
       │
       └──────────────► AI Service
                           │
                           ▼
                  Large Language Model
```

---

## Technology Stack

### Frontend

* React.js
* Bootstrap
* Tailwind CSS
* Axios
* React Router
* HTML5
* CSS3
* JavaScript (ES6)

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* RESTful APIs
* Maven

### Database

* MySQL

### AI Service

* Python
* FastAPI
* LangChain
* FAISS
* Sentence Transformers
* Hugging Face
* Groq API
* Large Language Models (LLMs)

### Development Tools

* Eclipse IDE
* Visual Studio Code
* Postman
* Git
* GitHub
* Maven
* Vercel

---

## Project Structure

```
Signum
│
├── frontend
│   ├── React Application
│   ├── Bootstrap
│   ├── Tailwind CSS
│   └── User Interface
│
├── backend
│   ├── Spring Boot
│   ├── REST APIs
│   ├── Business Logic
│   └── Database Integration
│
└── AI Service
    ├── FastAPI
    ├── RAG Pipeline
    ├── Policy Recommendation
    ├── Fraud Analysis
    └── Chatbot
```

---

## Installation

### Clone the Repository

```bash
git clone https://github.com/sanjay-1009/Signum-Insurance-System
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

### Backend

```bash
cd backend
mvn spring-boot:run
```

### AI Service

Clone the AI service repository:

```bash
git clone https://github.com/sanjay-1009/Signum_AI_Service
```

Follow the setup instructions provided in the AI service repository.

---

## API Integration

The frontend communicates with the Spring Boot backend using REST APIs. The backend interacts with the AI service to provide:

* Policy recommendations
* Insurance query assistance
* Claim eligibility analysis
* Fraud detection
* AI chatbot responses

---

## Security Features

* User authentication
* Role-based authorization
* Secure REST APIs
* Input validation
* Document upload validation
* Secure claim processing workflow

---

## Future Enhancements

* OCR-based document verification
* Face verification during claim submission
* Predictive claim approval
* Email and SMS notifications
* Cloud storage integration
* Multi-language chatbot support
* Mobile application

---

## Contributors

Sanjay G

---

## License

This project is developed for academic and educational purposes.
