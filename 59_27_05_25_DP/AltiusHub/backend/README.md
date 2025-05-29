# My Express App

This project is a simple Express application for managing invoices. It provides a RESTful API to create, read, update, and delete invoices, along with their associated items and bill sundries.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Validation Rules](#validation-rules)

## Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd my-express-app
   ```

3. Install the dependencies:
   ```
   npm install
   ```

## Usage

To start the application, run:
```
npm start
```

The application will be running on `http://localhost:3000`.

## API Endpoints

- **Create an Invoice**
  - `POST /invoices`
  
- **Get All Invoices**
  - `GET /invoices`
  
- **Get Invoice by ID**
  - `GET /invoices/:id`
  
- **Update Invoice**
  - `PUT /invoices/:id`
  
- **Delete Invoice**
  - `DELETE /invoices/:id`

## Validation Rules

- Each invoice item must have an amount calculated as `quantity × price` and must be greater than 0.
- The total amount of the invoice is the sum of all item amounts plus the sum of all bill sundry amounts.
- Bill sundry amounts can be negative or positive.
- Each invoice number is auto-generated and unique. 

For more details, refer to the API documentation in the code.