# Employee Time Tracker

## Project Overview

Employee Time Tracker is a web application designed to help employees capture and track their tasks and working hours efficiently. It provides a user-friendly interface for logging work activities, generating reports, and visualizing time allocation across projects and tasks.

## Features

- **Task Management**: Add, edit, and delete task details including:
  - Employee name
  - Role
  - Project
  - Date
  - Time duration
  - Task Category
  - Description

- **Role-Based Access Control (RBAC)**: 
  - Associate user role
  - Admin user role

- **Time Tracking**:
  - Display total duration (hours) for daily, weekly, and monthly views

- **Data Visualization**:
  - For Associates:
    - Daily tasks/hours as a Pie chart
    - Weekly and monthly data as Bar charts
  - For Admins:
    - View charts by Project or Employee (optional)
    - Daily, Weekly, and Monthly breakdowns

## Technical Considerations

- Role-based action permissions
- Prevention of duplicate entries for the same date and time
- Maximum task duration limit of 8 hours
- Authenticated access only

## Tech Stack

### Frontend
- React.js
- Chart.js for data visualization
- Tailwind CSS for styling

### Backend
- Node.js with Express.js
- MongoDB for database
- Mongoose as ODM

### Authentication
- JSON Web Tokens (JWT)

### Deployment
- Docker
- AWS EC2 for hosting

## Getting Started

### Prerequisites
- Node.js (v14 or later)
- MongoDB
- Docker (optional)

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/your-username/employee-time-tracker.git
   ```

2. Install dependencies:
   ```
   cd employee-time-tracker
   npm install
   ```

3. Set up environment variables:
   Create a `.env` file in the root directory and add the following:
   ```
   MONGODB_URI=your_mongodb_connection_string
   JWT_SECRET=your_jwt_secret
   ```

4. Run the application:
   ```
   npm run dev
   ```

## Database Schema

The database schema is normalized and includes the following main collections:

- Users
- Tasks
- Projects
- Roles

(Detailed schema design to be added)

## API Endpoints

- `/api/auth` - Authentication routes
- `/api/tasks` - Task management routes
- `/api/projects` - Project management routes
- `/api/reports` - Reporting and analytics routes

(Detailed API documentation to be added)

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

