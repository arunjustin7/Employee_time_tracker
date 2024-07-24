# 🕒 Employee Time Tracker

## Project Summary

This application is designed for employees to capture and track their tasks and working hours efficiently. 

### Features
- 📋 **Task Management**: Add, edit, and delete tasks with details like Employee name, Role, Project, Date, Time duration, Task Category, and Description.
  - Example: 
    - John Doe, 25/04/2022 10-11AM, Meeting, Standup meeting with the team.
    - John Doe, 25/04/2022 11-01PM, Training, Hands-on training on Python.

- 🔐 **Role-Based Access Control (RBAC)**: Different access levels for Associates and Admins.

- 📊 **Duration Display**: View total working hours on a Daily, Weekly, and Monthly basis.

- 📈 **Charts for Associates**: 
  - Daily tasks/hours as a Pie chart.
  - Weekly and monthly charts as Bar charts.

- 📉 **Admin Insights**: View charts for selected Projects/Employees on a Daily, Weekly, and Monthly basis.

## Technical Considerations

- ✅ Users can perform actions based on their roles.
- 🚫 Duplicate entries for the same date and time are not allowed.
- ⏰ Task duration is limited to a maximum of 8 hours.

## Instructions

1. 📝 **Design Document**: Create a very high-level design document.
2. 🛠️ **Bill of Materials (BOM)**: List of software & hardware to be used.
3. 📦 **Modularized Code**: Design and implement well-structured code.
4. 📊 **Database Schema**: Design a normalized database schema.
5. 🔒 **Authentication**: Only authenticated users should access the application.

## Getting Started

To get started with the Employee Time Tracker, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/employee-time-tracker.git
   
2. **Import the project into Eclipse IDE:**
    - Open Eclipse IDE
    - File -> Import -> Existing Maven Projects -> Select the cloned repository

3. **Set up MySQL Database:**
    - Open MySQL Workbench
    - Create a new database called `banking`
    - Import the SQL script from the `database` directory to set up the tables

4. **Configure Tomcat Server:**
    - Download and install Tomcat 9
    - In Eclipse, go to Servers view, right-click -> New -> Server -> Apache -> Tomcat v9.0 Server
    - Add your project to the server

5. **Update Database Connection Details:**
    - Open `Dbconnection.java` in `com.Bank` package
    - Update the database URL, username, and password as per your MySQL configuration

6. **Run the Project:**
    - Right-click on the project -> Run As -> Run on Server
    - Open a web browser and go to `http://localhost:8080/banking-application`
