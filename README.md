# SpringTrader
## Search investment assets · Add to your portfolio · Track profits and losses

Investment portfolio tracker made with Spring Boot. Spring Trader utilizes spring security to implement JSON Web Token authentication. It aggregates stock data from multiple sources (currently AlphaVantage, Finnhub, and Polygon) using dependency injection and interfaces to decouple the application from the data. 
 

## To start:
### 1. Clone repo.
### 2. Create a new file, 'application.properties' in the resources directory
### 3. Copy the following to application.properties:
```
server.port=${port:8080}
spring.application.name = Spring Trader
spring.thymeleaf.cache = false
spring.thymeleaf.enabled=true 
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.jsp
server.error.path=/error
server.error.whitelabel.enabled=false 
server.servlet.context-path=/SpringTrader
alphavantage.api.key=[]
finnhub.api.key=[]
polygon.api.key=[]
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.security.oauth2.resourceserver.jwt.jwk-set-uri: <JWK_ENDPOINT>
```
### 4. Replace the brackets with API keys:
   #### AlphaVantage free API key: https://www.alphavantage.co/
```
alphavantage.api.key=[put key here without brackets]
```
   #### Finnhub free API key: https://finnhub.io/
```
finnhub.api.key=[put key here without brackets]
```
   #### Polygon free API key: https://polygon.io/
```
polygon.api.key=[put key here without brackets]
```
### 5. Run the project and go to http://localhost:8080/SpringTrader/
### 6. Login to test user account:
```
username: user
password: pass
```
