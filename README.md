# CampusCore - (Spring Boot Student & Academic ERP System)

A comprehensive **Spring Boot full stack project** for managing student and academic information with clean layered architecture, form validation, pagination, sorting, and secure role-based authentication.

## 📋 Overview

CampusCore is a modern Spring Boot application designed to provide a robust platform for educational institutions. The system handles student records, faculty management, course enrollments, attendance tracking, and grade management through an intuitive web interface with role-based dashboards for Students, Faculty, and Heads of Department (HODs).

**Note**: This repository is maintained for educational purposes and follows best practices for Spring Boot application development.

## 🛠️ Tech Stack

- **Language**: Java 21
- **Framework**: Spring Boot 4.0.0
- **Web Layer**: Spring MVC + Thymeleaf
- **Persistence**: Spring Data JPA (Hibernate) with PostgreSQL
- **Security**: Spring Security 6 with role-based authentication
- **Validation**: Hibernate Validator (Bean Validation 3.0)
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Additional Libraries**: 
  - ModelMapper (for DTO-Entity mapping)
  - Thymeleaf Extras SpringSecurity6 (for Spring Security integration in Thymeleaf)
  - PostgreSQL JDBC Driver

## ⚙️ Configuration

### Database Setup
1. Install PostgreSQL and create a database named `campuscore` (or update the name in `application.properties` if preferred)
2. Update credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/campuscore
   spring.datasource.username=your_postgres_username
   spring.datasource.password=your_postgres_password
   ```

### Running the Application

The project includes a custom PowerShell script for easy execution:

#### Using the provided script (Recommended)
```powershell
.\run.ps1
```
This script:
- Sets JAVA_HOME to JDK 21
- Configures the PATH
- Launches the Spring Boot application
- Automatically opens the browser at http://localhost:8080 after startup

#### Manual Execution
```powershell
# Set Java 21 environment
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.12.1"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

# Run the application
.\mvnw.cmd spring-boot:run
```

## 🌟 Features

### Core Functionality
- **Authentication & Authorization**: Secure login/logout with role-based access control (ADMIN, FACULTY, HOD, STUDENT)
- **Student Management**: Complete CRUD operations for student records
- **Faculty Management**: Manage instructor/professor information
- **Course Management**: Handle course offerings and enrollment
- **Attendance Tracking**: Record and monitor student attendance
- **Grade Management**: Store and calculate student grades
- **Department & Role Organization**: Organizational structure management

### Technical Features
- **Form Validation**: Server-side validation using Hibernate Validator
- **Pagination & Sorting**: Efficient data browsing with customizable page sizes
- **Secure Authentication**: Spring Security 6 with BCrypt password hashing
- **Role-Based Access Control**: Different access levels for ADMIN, FACULTY, HOD, STUDENT roles
- **Responsive UI**: Mobile-friendly Thymeleaf templates with Bootstrap styling
- **Global Exception Handling**: Centralized error management
- **Clean Architecture**: Clear separation between Controllers, Services, Repositories, Entities, and DTOs
- **Automatic Database Schema Updates**: Hibernate handles schema evolution

### Security Features
- User authentication with encrypted passwords
- Role-based authorization (ADMIN, FACULTY, HOD, STUDENT)
- Protected endpoints based on user roles
- CSRF protection
- Secure HTTP headers

## 📂 Project Structure

```
src/main/java/com/campuscore/
├── collegeerp/             # College ERP module (core academic entities)
│   ├── config/             # Security and data initialization configurations
│   ├── controller/         # REST controllers for academic modules
│   ├── dto/                # Data Transfer Objects for academic modules
│   ├── entity/             # JPA entities representing academic database tables
│   ├── handler/            # Custom authentication success handlers
│   ├── repository/         # Spring Data JPA repositories for academic modules
│   └── service/            # Business logic services for academic modules
└── studentmanagement/      # Student management module
    ├── config/             # Application configurations
    ├── controller/         # REST controllers for student management
    ├── dto/                # Data Transfer Objects for student management
    ├── entity/             # JPA entities representing student database tables
    ├── exception/          # Global exception handling
    ├── repository/         # Spring Data JPA repositories for student management
    └── service/            # Business logic services for student management
```

## 🚀 Development Setup

### Prerequisites
- JDK 21 (included in the run script configuration)
- PostgreSQL 12+
- Maven (provided via Maven Wrapper - mvnw)
- PowerShell 5.1+ (Windows) or compatible shell

### IDE Recommendations
- **IntelliJ IDEA** (Ultimate or Community Edition)
- **Eclipse IDE** for Java Developers
- **VS Code** with Java Extension Pack

### Building the Project
```powershell
.\mvnw.cmd clean package
```
The compiled JAR will be available in the `target/` directory.

## 🔌 API Endpoints

### Authentication
- `GET /login` - Display login page
- `POST /login` - Process login attempt
- `GET /logout` - Logout user

### Student Management
- `GET /students` - List all students (paginated)
- `GET /students/new` - Show form to create new student
- `POST /students` - Create new student
- `GET /students/{id}/edit` - Show form to edit student
- `PUT /students/{id}` - Update existing student
- `DELETE /students/{id}` - Delete student

### Similar endpoints exist for:
- Faculty (`/faculty`)
- Departments (`/departments`)
- Courses (`/courses`)
- Enrollments (`/enrollments`)
- Attendance (`/attendance`)
- Grades (`/grades`)

## 🧪 Testing

The project includes test configurations for:
- Unit tests with JUnit 5
- Integration tests using Spring Boot Test
- Security testing with Spring Security Test
- Thymeleaf template testing

Run tests with:
```powershell
.\mvnw.cmd test
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- PostgreSQL community for the robust database system
- Thymeleaf team for the natural template engine
- All contributors to the open-source libraries used in this project

---
*Last updated: September 2026*