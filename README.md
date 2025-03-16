ENGLISH BELOW

# ✈️ Lennu planeerimise ja lennukis istekohtade soovitamise veebirakendus

See on rakendus, kus kasutajad saavad:
- Vaadata saadaolevaid lende.
- Otsida lende vastavalt eelistustele.
- Vaadata ja valida istekohti valitud lennule.
- Soovi korral muuta oma istekohti.

📂 Projekti Struktuur
- **Taustsüsteem (Spring Boot)**: Asub juurkaustas ja `src` kaustas.
- **Kasutajaliides (Vue.js)**: Asub kaustas `frontend/lennureis-ui`.

---

⚙️ Eeldused
- **Java 21**
- **Gradle**
- **Node.js** (v18 või uuem)
- **npm**
- **PostgreSQL** (andmebaasiks)

---

🚀 Projekti ülesseadmine

### 1. Repo kloonimine
```bash
git clone https://github.com/Kirotar/lennureis
cd lennureis
```

### 2. Taustsüsteemi seadistamine (Spring Boot)
- Veendu, et PostgreSQL töötab.
- Konfigureeri keskkonnamuutujad (vaata Konfiguratsiooni sektsiooni).
- Ehita ja käivita taustsüsteem:
```bash
./gradlew bootRun
```

### 3. Kasutajaliidese seadistamine (Vue.js)
```bash
cd frontend/lennureis-ui
npm install
npm run dev
```

---

🛢️ Andmebaasi Seadistamine
- Loo andmebaas käsitsi:
```sql
CREATE DATABASE flight_booking;
```
- Veendu, et PostgreSQL töötab.
- Flyway käivitab migratsioonid automaatselt, luues skeemi, tabelid ja esialgsed andmed taustsüsteemi käivitamisel.

---

⚙️ Konfiguratsioon

### Taustsüsteem (`application.properties`)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flight_booking
spring.datasource.username=postgres
spring.datasource.password=student123
spring.datasource.driver-class-name=org.postgresql.Driver
```

### Kasutajaliides (`.config.ts`)
```plaintext
VITE_API_URL=http://localhost:8080/flight
```

### Vue Proxy Konfiguratsioon (`vite.config.ts`)
```javascript
export default defineConfig({
  server: {
    port: 5173,
    proxy: {
      '/api': 'http://localhost:8080',
    },
  },
});
```

---

# ✈️ Flight Seat Selection App

This is an application where users can:
- View available flights.
- Search for flights based on preferences.
- View and select seats for a chosen flight.
- Change their selected seats if needed.

## 📂 Project Structure
- **Backend (Spring Boot)**: Located in the root and `src` directory.
- **Frontend (Vue.js)**: Located in the `frontend/lennureis-ui` directory.

---

## ⚙️ Prerequisites
- **Java 21**
- **Gradle**
- **Node.js** (v18 or above)
- **npm**
- **PostgreSQL** (for the database)

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/Kirotar/lennureis
cd lennureis
```

### 2. Backend Setup (Spring Boot)
- Ensure PostgreSQL is running.
- Configure environment variables (see the Configuration section).
- Build and run the backend:
```bash
./gradlew bootRun
```

### 3. Frontend Setup (Vue.js)
```bash
cd frontend/lennureis-ui
npm install
npm run dev
```

---

## 🛢️ Database Setup
- Create the database manually:
```sql
CREATE DATABASE flight_booking;
```
- Ensure PostgreSQL is running.
- Flyway will automatically run migrations to create the schema, tables, and initial data when the backend starts.

---

## ⚙️ Configuration

### Backend (`application.properties`)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flight_booking
spring.datasource.username=postgres
spring.datasource.password=student123
spring.datasource.driver-class-name=org.postgresql.Driver
```

### Frontend (`.config.ts`)
```plaintext
VITE_API_URL=http://localhost:8080/flight
```

### Vue Proxy Configuration (`vite.config.ts`)
```javascript
export default defineConfig({
  server: {
    port: 5173,
    proxy: {
      '/api': 'http://localhost:8080',
    },
  },
});
```
