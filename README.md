# Book Rental API

## Development Setup

### H2 Database (Development)
1. Use H2 in-memory database (default profile):
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
```

### PostgreSQL Database (Production)
1. Start PostgreSQL:
```bash
docker-compose up -d
```

2. Run with PostgreSQL profile:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

3. pgAdmin: http://localhost:80
```
Email: myuser@email.com
Password: pgadminpassword
```

## API Documentation
Swagger UI: http://localhost:8080/swagger-ui/index.html 