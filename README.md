# Travel-planner

A data-centric web application that helps users plan trips by bringing together
flights, accommodations, restaurants, sights, and weather forecasts for a chosen
destination all in one place.

## Tech Stack

**Backend:** Java 25, Spring Boot 4, Spring Data JPA, Spring Security (JWT), PostgreSQL,
OpenAPI-generated interfaces (contract-first)

**Frontend:** React (Vite)

## Project Structure

```
travel-planner/
├── backend/    # Spring Boot API
└── frontend/   # React (Vite) app
```

## Getting Started

### Prerequisites
- Java 25 (JDK)
- Node.js 22+
- PostgreSQL (local instance, or a connection string to a hosted instance)

### Backend Setup

1. `cd backend`
2. Set the following environment variables (e.g. in your IDE's run configuration):

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/travel_planner
SPRING_DATASOURCE_USERNAME=<your db username>
SPRING_DATASOURCE_PASSWORD=<your db password>
OPENWEATHER_API_KEY=<your OpenWeatherMap key>
IGNAV_API_KEY=<your Ignav key>
GOOGLE_API_KEY=<your Google Places key>
TRAVEL_PLANNER_JWT_SECRET=<a Base64-encoded secret>
TRAVEL_PLANNER_JWT_EXPIRATION_MS=3600000
ADMIN_PASSWORD=<password for the seeded admin account>
```

3. Run: `./mvnw spring-boot:run` (or run `BackendApplication` from your IDE)
4. The API is available at `http://localhost:8080/api`

### Frontend Setup

1. `cd frontend`
2. `npm install`
3. `npm run dev`
4. The app is available at `http://localhost:5173`

## Running Tests

**Backend (unit + integration tests):**

```
cd backend
./mvnw test
```

**Frontend:**

```
cd frontend
npm test
```

## API Documentation

The API contract is defined in `backend/src/main/resources/openapi/travel-planner-api.yaml`
(contract-first — Spring interfaces are generated from this spec at build time).

## Deployment

- **Backend:** deployed on [Render](https://render.com)
- **Database:** [Neon](https://neon.tech) (managed PostgreSQL)
- **Frontend:** deployed on [Cloudflare Pages](https://pages.cloudflare.com)

## Authentication

The app uses JWT-based authentication with role-based access control (`ROLE_USER`,
`ROLE_ADMIN`). New registrations receive `ROLE_USER` by default; an admin account
is seeded automatically on first startup.
