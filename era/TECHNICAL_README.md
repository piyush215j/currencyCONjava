# Currency Converter - Technical Documentation

## Code Architecture

Both versions of the application follow the Model-View-Controller (MVC) pattern:

### Model
- Handles data and business logic
- Manages currency conversion calculations
- Maintains exchange rates (static or API-based)

### View
- Manages the user interface
- Displays conversion results
- Handles user input validation

### Controller
- Coordinates between Model and View
- Processes user actions
- Updates View based on Model changes

## Version 1 Implementation Details

### CurrencyModel.java
```java
Key Components:
- HashMap<String, Double> exchangeRates: Stores currency exchange rates
- Methods:
  - convert(): Performs currency conversion
  - getExchangeRate(): Retrieves exchange rate for currency pair
  - updateExchangeRate(): Updates static exchange rates
```

### CurrencyView.java
```java
Key Components:
- JFrame-based GUI
- Input fields for:
  - Amount
  - Source currency
  - Target currency
- Components:
  - JComboBox for currency selection
  - JTextField for amount input
  - JButton for conversion
  - JLabel for results display
```

### CurrencyController.java
```java
Key Components:
- Handles user interactions
- Methods:
  - actionPerformed(): Processes button clicks
  - updateView(): Updates display with conversion results
  - validateInput(): Ensures valid user input
```

## Version 2 Implementation Details

### Enhanced Features

#### CurrencyModel.java
```java
Improvements:
- Real-time API integration
- Dynamic exchange rate fetching
- JSON response parsing
- Rate caching mechanism

Key Methods:
- fetchExchangeRates(): Gets rates from API
- updateRates(): Refreshes cached rates
- convert(): Enhanced conversion with live rates
```

#### CurrencyView.java
```java
Enhancements:
- Modern Swing UI
- Error message handling
- Loading indicators
- Status updates
- Responsive layout

Components:
- Enhanced input validation
- Real-time rate display
- Last updated timestamp
- Error message panel
```

#### CurrencyController.java
```java
New Features:
- API error handling
- Rate refresh management
- Asynchronous operations
- Input sanitization

Key Methods:
- handleApiResponse(): Processes API responses
- refreshRates(): Triggers rate updates
- handleErrors(): Manages error scenarios
```

### API Integration (v2)

```java
Implementation Details:
- Uses org.json for JSON processing
- HTTP connection management
- Rate limiting handling
- Error response processing
```

## Data Flow

### Version 1
1. User inputs amount and selects currencies
2. Controller validates input
3. Model performs conversion using static rates
4. View displays results

### Version 2
1. User inputs amount and selects currencies
2. Controller checks for rate freshness
3. Model fetches current rates if needed
4. Conversion performed with live rates
5. View updates with results and rate info

## Error Handling

### Version 1
- Basic input validation
- Number format checking
- Currency availability verification

### Version 2
- Comprehensive error handling:
  - API connection failures
  - Invalid responses
  - Rate limits
  - Network timeouts
  - Invalid input formats
  - Currency pair validation

## Configuration

### Version 1
```java
Static Configuration:
- Predefined currency pairs
- Fixed exchange rates
- UI layout parameters
```

### Version 2
```java
Maven Configuration (pom.xml):
<dependencies>
    <!-- JSON Processing -->
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20231013</version>
    </dependency>
</dependencies>

Runtime Configuration:
- API endpoint configuration
- Rate refresh intervals
- Cache duration settings
- UI customization options
```

## Best Practices Implemented

1. **Code Organization**
   - Package structure
   - Separation of concerns
   - Clear class responsibilities

2. **Error Handling**
   - Try-catch blocks
   - User-friendly error messages
   - Graceful degradation

3. **Performance**
   - Rate caching
   - Efficient data structures
   - Resource cleanup

4. **Maintainability**
   - Clear documentation
   - Consistent naming
   - Code modularity

5. **Testing Considerations**
   - Testable methods
   - Dependency injection
   - Mock-friendly design

## Building and Testing

### Version 1
```bash
# Compilation
javac v1/model/*.java v1/view/*.java v1/controller/*.java

# Running
java v1.Main
```

### Version 2
```bash
# Maven Build
mvn clean package

# Running
java -jar target/currency-converter-1.0-SNAPSHOT-jar-with-dependencies.jar

# Testing
mvn test
```

## Common Issues and Solutions

1. **API Connection Issues**
   - Solution: Implement retry mechanism
   - Fallback to cached rates

2. **Rate Limit Exceeded**
   - Solution: Implement rate limiting
   - Queue requests

3. **Invalid Input**
   - Solution: Enhanced validation
   - Clear error messages

4. **UI Responsiveness**
   - Solution: Asynchronous operations
   - Progress indicators

## Future Enhancements

1. **Technical Improvements**
   - Database integration
   - Historical rate tracking
   - Rate prediction
   - Multiple API support

2. **Feature Additions**
   - Currency alerts
   - Rate graphs
   - Batch conversions
   - Favorite currencies

3. **UI Enhancements**
   - Dark mode
   - Mobile responsiveness
   - Customizable themes
   - Accessibility features 