# SpringTrader
Invenstment portfolio tracker made with Spring Boot 

To start:
1. Clone repo.
2. Create a new file, 'application.properties' in the resources directory
3. Copy the following to application.properties:
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
4. Replace the brackets with API keys:
  4. AlphaVantage free API key: https://www.alphavantage.co/
  4. Finnhub free API key: https://finnhub.io/
  4. Polygon free API key: https://polygon.io/
