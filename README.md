# Customers

Sprint Boot Rest Api Microservices for Customers Management

### Description

Simple crud implementing Hexagonal Architecture & DDD

## Getting Started

### Customer Structure:

```json
{
  "id": "aa806be4-752e-4312-9e87-0542d798cacf",
  "tenantId": "bb566be4-562e-4647-9e87-0542d798cacf",
  "name": "Gustavo",
  "surname": "Hornos",
  "email": "hornosg@gmail.com",
  "address": {
    "street": "Example",
    "number": 1952,
    "betweenStreets": "a, b",
    "city": "Luján",
    "postalCode": 6700,
    "state": "Buenos Aires",
    "country": "Argentina"
  },
  "phone": {
    "countryCode": 54,
    "areaCode": 11,
    "number": 65457212
  },
  "profile": "https://www.gravatar.com/avatar/3adff532f804e003f01acbad2da6db70?d=identicon",
  "type": "eventual"
}
```


### Database

* MongoDB Atlas

### Environment variables

* DATABASE_NAME
* DATABASE_URL

## Author

[@hornosg](https://www.linkedin.com/in/gustavohornos/)

