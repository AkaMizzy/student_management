# Student Management System (Système de Gestion des Étudiants)

A Java Swing-based student management system for educational institutions that handles student records, grades, and attendance tracking.

## Features

### 1. Student Management (Gestion des Étudiants)
- Add new students with details (ID, name, gender, program, phone, birth date)
- Modify existing student information
- Delete student records
- Search students by ID, name, or program
- Sort student list by various criteria

### 2. Grade Management (Gestion des Notes)
- Record grades for different modules (Java, XML, UML, NodeJS)
- Automatic calculation of general average
- Automatic grade classification (Très Bien, Bien, Assez Bien, Passable)
- Search and update grade records
- Sort grades by module or average

### 3. Attendance Tracking (Gestion des Absences)
- Record student absences with date and duration
- Mark absences as justified or unjustified
- Track absences by module
- Search and update attendance records

## Technical Details

### Prerequisites
- Java Development Kit (JDK)
- MySQL Database
- MySQL Connector/J
- JCalendar Library

### Database Structure
- `edata`: Student personal information
- `etudiant_note`: Student grades
- `etudiant_absence`: Attendance records
- `user`: User authentication data

### Libraries Used
- Java Swing for GUI
- MySQL for database
- JCalendar for date handling

### Setup Instructions
1. Install MySQL and create a database named 'etudata'
2. Import the database schema from 'etudata.sql'
3. Configure database connection in dbConnect.java
4. Add required libraries (MySQL Connector/J, JCalendar)
5. Compile and run the project

### Default Login
- Email: hamza@gmail.com
- Password: 1234

## Project Structure
```
gestions_des_etudiants/
│
├── src/
│   ├── Main.java          # Application entry point
│   ├── Login.java         # Authentication interface
│   ├── Home.java          # Main dashboard
│   ├── Etudiants.java     # Student management
│   ├── Note.java          # Grade management
│   ├── Absence.java       # Attendance management
│   ├── Table.java         # Table styling utility
│   └── dbConnect.java     # Database connection utility
│
├── etudata.sql           # Database schema
└── README.md             # Project documentation
```

## User Interface
- Modern and intuitive interface
- Consistent styling across all windows
- Responsive table displays
- User-friendly forms for data entry
- Comprehensive search and sort functionality

## Security Features
- User authentication required
- Password recovery option
- Session management

## Contributors
- Project maintained by [Your Name]

## License
This project is intended for educational purposes.
