# Currency Converter Application

This repository contains two versions of a Currency Converter application implemented in Java.

## Version 1 (v1)

### Overview
The first version is a basic currency converter that uses hardcoded exchange rates. It follows the MVC (Model-View-Controller) architectural pattern.

### Structure
```
v1/
├── controller/
│   └── CurrencyController.java
├── model/
│   └── CurrencyModel.java
└── view/
    └── CurrencyView.java
```

### Features
- Basic currency conversion using predefined exchange rates
- Simple command-line interface
- MVC architecture implementation
- Supports conversion between common currencies
- Local currency rate calculations

### Technologies Used
- Java
- Swing for GUI
- Basic Java libraries

## Version 2 (v2)

### Overview
The second version is an enhanced implementation that uses real-time exchange rates from the Free Currency API. It maintains the MVC architecture while adding modern features and improvements.

### Structure
```
v2/
├── src/
│   └── main/
│       └── java/
│           └── v2/
│               └── com/
│                   └── currency/
│                       └── converter/
│                           ├── controller/
│                           │   └── CurrencyController.java
│                           ├── model/
│                           │   └── CurrencyModel.java
│                           ├── view/
│                           │   └── CurrencyView.java
│                           └── Main.java
└── pom.xml
```

### Features
- Real-time currency exchange rates using Free Currency API
- Modern Maven project structure
- Enhanced user interface
- Support for more currencies
- Live API integration
- Error handling and validation
- Proper dependency management

### Technologies Used
- Java 11
- Maven for dependency management
- JSON processing (org.json:json:20231013)
- Swing for GUI
- RESTful API integration

### Building and Running
To build and run version 2:
1. Navigate to the v2 directory
2. Run `mvn clean package` to build the project
3. Run `java -jar target/currency-converter-1.0-SNAPSHOT-jar-with-dependencies.jar`

## Requirements
- Java 11 or higher
- Maven (for v2)
- Internet connection (for v2's real-time rates)

## Key Differences Between Versions

1. **Project Structure**
   - v1: Simple directory structure
   - v2: Maven-based project structure with proper package organization

2. **Exchange Rates**
   - v1: Hardcoded exchange rates
   - v2: Real-time rates from Free Currency API

3. **Dependencies**
   - v1: No external dependencies
   - v2: Managed through Maven (JSON processing, etc.)

4. **Build System**
   - v1: Manual compilation
   - v2: Maven-based build system

5. **Features**
   - v1: Basic currency conversion
   - v2: Enhanced features with real-time data and better error handling

## Development
Both versions follow clean code practices and the MVC pattern, making them maintainable and extensible. Version 2 represents a significant improvement in terms of functionality and modern development practices. 