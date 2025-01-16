# Orders and Notifications Management System

A sophisticated e-commerce system implementing various design patterns for flexible order management and dynamic notification handling.

## 🚀 Features
- Product Management & Inventory
- User Account Management
- Simple & Compound Order Processing
- Dynamic Notification System
- Shipment Management
- Order Cancellation
- Real-time Statistics

## 🏗️ Architecture & Design Patterns

### 1. Repository Pattern
Used for data persistence and domain object management.

**Implementation Components:**
- `CustomersRepo`: Customer data management
- `ProductsRepo`: Product inventory management
- `NotificationQueueService`: Notification queue handling


### 2. Factory Pattern
Implements flexible order creation system.

**Implementation Components:**
- `OrderFactory`: Creates different order types dynamically


### 3. Decorator Pattern
Enables dynamic notification strategy customization.

**Implementation Components:**
- `INotifier`: Base notification interface
- `BaseNotification`: Abstract decorator class
- `EmailDecorator`: Email notification implementation
- `SMSDecorator`: SMS notification implementation

## 🚀 Getting Started

### Prerequisites
- Java JDK 11 or higher
- Maven
- Spring Boot
- Git

### Installation
```bash
# Clone the repository
git clone [repository-url]

# Navigate to project directory
cd [project-name]

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```
## 📚 Documentation
For detailed API documentation including all endpoints, request/response formats, and usage examples, please refer to our [API Documentation](api-documentation.md).

The API documentation covers:
- Authentication endpoints
- Order management operations
- Notification system
- Product management
- Statistics and monitoring
- Error handling

## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.
