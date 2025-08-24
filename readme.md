## SOLID Principles
This project adheres to SOLID principles in the following ways:
#### Single Responsibility Principle (SRP): 
Each class has a single responsibility. For example, WeatherRepository is only responsible for fetching weather data, and HomeViewModel manages the state for the home screen.
#### Open/Closed Principle (OCP): 
The use of a repository and dependency injection allows for extending functionality (e.g., adding a local data source) without modifying existing classes.
#### Liskov Substitution Principle (LSP): 
WeatherState is a sealed class, and its subclasses can be used interchangeably where WeatherState is expected.
#### Interface Segregation Principle (ISP): 
OpenWeatherApiService is a small, specific interface.
#### Dependency Inversion Principle (DIP): 
High-level modules (ViewModels) depend on abstractions (Repository) rather than concrete implementations. Hilt handles the injection of these dependencies.