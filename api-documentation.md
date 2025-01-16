# Online Shopping System API Documentation

This document provides detailed information about the REST APIs available in the Online Shopping System.

## Table of Contents
- [Authentication](#authentication)
- [Customer Management](#customer-management)
- [Product Management](#product-management)
- [Order Management](#order-management)
- [Notification System](#notification-system)

## Authentication

### Create Account
Creates a new customer account with specified balance and messaging preferences.

- **URL**: `/api/createAccount`
- **Method**: `POST`
- **Request Body**:
```json
{
  "customerData": {
    "balance": 1000.0,
    "preferredStrategy": ["SMS"]
  },
  "loginData": {
    "username": "ahmed",
    "password": "123456"
  }
}
```
- **Success Response**: 
  - **Code**: 200
  - **Content**: `"Account created successfully!"`
- **Error Response**:
  - **Code**: 400
  - **Content**: `"User already exists"` or `"Please sign out first!"`

### Login
Authenticates a user and creates a session.

- **URL**: `/api/login`
- **Method**: `POST`
- **Request Body**:
```json
{
    "username": "ahmed",
    "password": "123456"
}
```
- **Success Response**:
  - **Code**: 200
  - **Content**: `"Login successful!"`
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Username or password is not true"` or `"Please sign out first!"`

### Logout
Ends the current user session.

- **URL**: `/api/logout`
- **Method**: `POST`
- **Success Response**:
  - **Code**: 200
  - **Content**: `"You have logged out successfully!"`
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Please login in!"`

## Product Management

### Get Products List
Retrieves all available products in the system.

- **URL**: `/api/getProductsList`
- **Method**: `GET`
- **Success Response**:
  - **Code**: 200
  - **Content**: List of products with details including serial number, name, vendor, category, price, and quantity

### Get Product Quantity
Retrieves the quantity of a specific product.

- **URL**: `/products/getProductQuantity`
- **Method**: `GET`
- **Query Parameters**: `serialnumber=[string]`
- **Success Response**:
  - **Code**: 200
  - **Content**: Product quantity as integer
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Product is not exist"`

## Order Management

### Create Simple Order
Creates a new simple order for the current user.

- **URL**: `/customers/createSimpleOrder`
- **Method**: `POST`
- **Request Body**:
```json
{
    "4": 1,
    "5": 2,
    "6": 3
}
```
- **Success Response**:
  - **Code**: 200
  - **Content**: `"Order created successfully! with id: XXX"`
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Please login in"` or `"Order was not created"`

### Create Compound Order
Creates a compound order combining current user's order with friends' orders.

- **URL**: `/customers/createCompoundOrder`
- **Method**: `POST`
- **Request Body**:
```json
{
    "selectedProducts": {
        "1": 1,
        "2": 3,
        "5": 2
    },
    "simpleOrders": {
        "mariam": 103248
    }
}
```
- **Success Response**:
  - **Code**: 200
  - **Content**: `"Order created successfully! with id: XXX"`

### Checkout Order
Processes payment for an order and updates its status.

- **URL**: `/customers/checkout`
- **Method**: `POST`
- **Query Parameters**: `orderID=[integer]`
- **Success Response**:
  - **Code**: 200
  - **Content**: `"Order placed, Thank you for using our service with amount: [amount]"`
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Error please try again !"`

### Ship Order
Initiates shipping for an order.

- **URL**: `/customers/shipOrder`
- **Method**: `GET`
- **Query Parameters**: `orderID=[integer]`
- **Success Response**:
  - **Code**: 200
  - **Content**: `"Order is shipped!"`
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Error please try again !"`

### Cancel Order
Cancels an order and refunds the customer.

- **URL**: `/customers/cancelOrder`
- **Method**: `GET`
- **Query Parameters**: `orderID=[integer]`
- **Success Response**:
  - **Code**: 200
  - **Content**: `"Order is canceled successfully!"`
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Error please try again !"`

### Cancel Shipment
Cancels the shipment of an order within the allowed timeframe.

- **URL**: `/customers/cancelShipment`
- **Method**: `GET`
- **Query Parameters**: `orderID=[integer]`
- **Success Response**:
  - **Code**: 200
  - **Content**: `"Order's shipment is canceled successfully!"`
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Your Order's shipment can't be cancelled"`

### Get Order Details
Retrieves detailed information about an order.

- **URL**: `/customers/getOrder`
- **Method**: `GET`
- **Query Parameters**: `orderID=[integer]`
- **Success Response**:
  - **Code**: 200
  - **Content**: Array of order details

## Notification System

### Get All Notifications
Retrieves all notifications in the system.

- **URL**: `/notification/getNotifications`
- **Method**: `GET`
- **Success Response**:
  - **Code**: 200
  - **Content**: Array of notifications

### Get User Notifications
Retrieves notifications for the current user.

- **URL**: `/notification/getMyNotification`
- **Method**: `GET`
- **Success Response**:
  - **Code**: 200
  - **Content**: Array of user's notifications
- **Error Response**:
  - **Code**: 400
  - **Content**: `"Please sign in first!"`

### Get Statistics
Several endpoints for notification statistics:

- Most Notified Email: `/notification/getMostNotifiedEmail`
- Most Notified Phone Number: `/notification/getMostNotifiedPhoneNumber`
- Most Used Template: `/notification/getMostNotifiedTemplate`
- **Method**: `GET`
- **Success Response**:
  - **Code**: 200
  - **Content**: Statistical information based on the endpoint

## Common Error Responses

All endpoints that require authentication will return:
- **Code**: 400
- **Content**: `"Please login in"` if the user is not authenticated

Internal server errors:
- **Code**: 500
- **Content**: Error message describing the issue
