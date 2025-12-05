# ✈️ AirLink Reservation Portal

AirLink Reservation Portal is a **Spring Boot backend application** for managing airline flight booking operations.  
It provides RESTful APIs for booking flights, managing passengers, processing payments, and handling flight schedules with structured response handling, DTOs, and Enum-based status management.

---

## 🚀 Features

### 🛫 Flight Management
- Add, update, delete, and retrieve flight details
- Real-time availability tracking

### 👤 Passenger Management
- Add passenger information during booking
- Retrieve passengers by flight or booking ID

### 🧾 Booking Management
- Create bookings with seat and passenger details
- Automatic booking confirmation based on payment status
- Cascade delete (deletes passenger + payment along with booking)

### 💳 Payment Management
- Supports multiple payment statuses (PAID, PENDING, FAILED)
- Linked payment records for every booking

### 📦 Response Wrapper
- Custom response structure with status, message & object

---

## 🛠 Tech Stack

| Category | Technology |
|---------|------------|
| Language | Java 17 (or your version) |
| Framework | Spring Boot |
| Build Tool | Maven |
| Database | MySQL |
| ORM | Hibernate / JPA |
| API Layer | REST Controllers |
| IDE | IntelliJ / Eclipse / VS Code |
| Version Control | Git & GitHub |

---

## 📂 Project Structure

src/main/java/com/jsp/AirLink
│── controller
│── service
│── dao
│── repository
│── dto
│── model
│── enums
│── exception
│── resource
└── AirLinkReservationPortalApplication.java

