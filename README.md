# Travel Agency :airplane:
> Restfull API MVC simulating travel agency application.
> Final project for Go For Java, Girl! bootcamp- 1st edition 2023 r.

## Table of Contents
* [General Information](#general-information)
* [Entity-Relationship Diagram](#entity-relationship-diagram)
* [Technologies Used](#technologies-used)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)


## General information
The application has such functionalities:

On the administrative side:
- Add trips into travel agency offer,
- Edit trips,
- Delete trips,
- Viewing list of trips by Continent, Country, Las-minute trips, Promoted trips,
- Searching trips by Continent, Country, Food option and Hotel standard
- View trip orders

From the user side:
- Viewing list of trips by Continent, Country, Las-minute trips, Promoted trips,
- Searching trips by Continent, Country, Food option and Hotel standard
- Creating trip orders
- Adding trip participants
- Viewing the list of orders

Both admin and user have different functionality at the same endpoints.

Admin:
- The default login is: admin
- Default password is: admin

User:
- The default login is: user
- Default password is: user

## Entity-Relationship Diagam

![ERD_Travel_Agency](https://github.com/mkozdroj/TravelAgency/assets/131193379/1a735358-bfa1-4021-8b4d-ac1593a81260)


## Technologies Used
- Java ver. 17
- Spring Boot 3.1.5 (with Spring MVC, Spring Security, Spring Web, Spring Data JPA, Spring Test...)
- H2 database
- Bootstrap 5.3.2
- Thymeleaf
- Lombok
- Maven
- Swagger 2.0.3


## Screenshots
Home Page with searching form 

![img.png](src%2Fmain%2Fresources%2Fstatic%2Fimg%2FReadme%2Fimg.png)

Page with admin view with the all trips

![img_1.png](src%2Fmain%2Fresources%2Fstatic%2Fimg%2FReadme%2Fimg_1.png)

Page with admin view with add trip form

![img_2.png](src%2Fmain%2Fresources%2Fstatic%2Fimg%2FReadme%2Fimg_2.png)

Page with user view with order trip form

![img_3.png](src%2Fmain%2Fresources%2Fstatic%2Fimg%2FReadme%2Fimg_3.png)

Page with user view with order details

![img_4.png](src%2Fmain%2Fresources%2Fstatic%2Fimg%2FReadme%2Fimg_4.png)


![Zrzut ekranu 2023-12-13 203435](https://github.com/mkozdroj/TravelAgency/assets/131193379/b24101df-c8cf-4480-89a8-12e94de4d8dd)
![Zrzut ekranu 2023-12-13 203515](https://github.com/mkozdroj/TravelAgency/assets/131193379/6a36391c-124b-414b-85e1-049b53cfc822)
![Zrzut ekranu 2023-12-13 203547](https://github.com/mkozdroj/TravelAgency/assets/131193379/d33d834c-2596-40d2-9bb1-e746f0a7be06)
![Zrzut ekranu 2023-12-13 203607](https://github.com/mkozdroj/TravelAgency/assets/131193379/a334222e-0a06-4e0a-969b-e0c3f9004e09)
![Zrzut ekranu 2023-12-13 203619](https://github.com/mkozdroj/TravelAgency/assets/131193379/fef07d60-d912-4365-baae-e4cd03fd12f8)



## Setup
- Clone this repository to your IDE.
- The project uses Maven as a build tool. 
- Initial database schemas and datas are incuded in file 'data.sql'
- The application runs on localhost: 8080 
- Database console is available : [http://localhost:8080/h2-console](url)
- You can check the functionality of the backend itself using swagger: [http://localhost:8080/swagger-ui.html](url) - please, use passwoerd mentioned in General Information section


## Room for Improvement
To do:
- Add more test
- Add validation in backend connected with entities
- Add Data tranfer objects


## Acknowledgements
- This project is a final project for Go for Java, Girl! bootcamp
- Many thanks to Mentors and everybody who helped us with this implementation


## Contact
Created by: Agnieszka Skrzypulec-Szota, Anna Margery, Marta Kozdrój
