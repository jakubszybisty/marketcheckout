# marketcheckout
H2 Database + JPA + Spring Boot + JUnit + Mockito

# To build project and run tests

mvn clean install

# To run project on localhost

mvn spring-boot:run

# To checkout your basket

POST / http://localhost:8080/api/v1/basket
Example payload in payload.json file

# Database
Existing Items and SpecialPromotions are localized in the marketcheckout\src\main\resources\import.sql file.

# Crucial classes and implementation choices

SpecialPromotion - entity responsible for declaring promotion interactions between two items

com.jszybisty.marketcheckout.service.discount - package responsible for applying discounts depending on quantity of bought items of certain type

com.jszybisty.marketcheckout.service.specialpromotions - package responsible for promotions based on interactions between items in basket 

BasketBuilder - elastic and flexible class for creating Baskets. Designed to easly turn on/off given promotions and discounts.

I assumed items passed to basket will not be counted based on type. I decided to ignore not existing items passed to checkout controller rather than validate them.
The reason I decided to do that is that I imagine items would be 'scanned / validated' before making its way to the basket.