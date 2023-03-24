# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS AirEats;
USE AirEats;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Tips;
DROP TABLE IF EXISTS Hours;
DROP TABLE IF EXISTS Attributes;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS YelpUsers;
DROP TABLE IF EXISTS Airbnb;
DROP TABLE IF EXISTS Hosts;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Restaurants;
CREATE TABLE YelpUsers (
  UserId VARCHAR(22),
  UserName VARCHAR(255),
  review_count int,
  yelping_since datetime,
  useful int,
  funny int,
  cool int,
  fans int,
  average_stars decimal(4, 2),
  compliment_hot int,
  compliment_more int,
  compliment_profile int,
  compliment_cute int,
  compliment_list int,
  compliment_note int,
  compliment_plain int,
  compliment_cool int,
  compliment_funny int,
  compliment_writer int,
  compliment_photos int,
  CONSTRAINT pk_YelpUsers_UserId PRIMARY KEY (UserId)
);
CREATE TABLE Hosts (
  HostId Int,
  HostName VARCHAR(255),
  CONSTRAINT pk_Hosts_HostId PRIMARY KEY (HostId)
);
CREATE TABLE Airbnb (
  AirbnbId VARCHAR(255),
  HostId Int,
  Name VARCHAR(255),
  City VARCHAR(255),
  Neighborhood VARCHAR(255),
  State VARCHAR(22),
  Latitude DECIMAL(10, 7),
  Longitude DECIMAL(10, 7),
  CONSTRAINT pk_Airbnb_AirbnbId PRIMARY KEY (AirbnbId),
  CONSTRAINT fk_Airbnb_HostId FOREIGN KEY (HostId) REFERENCES Hosts(HostId) ON UPDATE CASCADE ON DELETE SET NULL
);
CREATE TABLE Users (
  UserName VARCHAR(255),
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  Email VARCHAR(255),
  Phone VARCHAR(20),
  AirbnbId VARCHAR(255),
  CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName),
  CONSTRAINT fk_Airbnb_AirbnbId FOREIGN KEY (AirbnbId) REFERENCES Airbnb(AirbnbId) ON UPDATE CASCADE ON DELETE SET NULL
);
CREATE TABLE Restaurants(
  RestaurantId VARCHAR(22),
  Name VARCHAR(255),
  Address VARCHAR(255),
  City VARCHAR(255),
  State VARCHAR(10),
  Zip VARCHAR(10),
  Latitude DECIMAL(10, 7),
  Longitude DECIMAL(10, 7),
  Stars DECIMAL(2, 1),
  CONSTRAINT pk_Restaurant_RestaurantId PRIMARY KEY (RestaurantId)
);
CREATE TABLE Reviews (
  ReviewId VARCHAR(255),
  UserId VARCHAR(255),
  RestaurantId VARCHAR(255),
  Stars INT,
  #Might remove content field
  Useful DECIMAL(7, 1) NOT NULL,
  Funny DECIMAL(7, 1) NOT NULL,
  Cool DECIMAL(7, 1) NOT NULL,
  Content TEXT,
  Date datetime,
  CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
  CONSTRAINT fk_Reviews_RestaurantId FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId) ON UPDATE CASCADE ON DELETE SET NULL
);
CREATE TABLE Tips(
  TipId INT AUTO_INCREMENT,
  UserId VARCHAR(22),
  RestaurantId VARCHAR(22),
  Compliment_count INT,
  Date datetime,
  Context TEXT,
  CONSTRAINT pk_Tips_TipId PRIMARY KEY (TipId),
  CONSTRAINT fk_Tips_RestaurantId FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId) ON UPDATE CASCADE ON DELETE SET NULL
);
CREATE TABLE Recommendations(
  RecommendationId INT AUTO_INCREMENT,
  RestaurantId VARCHAR(22),
  UserId VARCHAR(22),
  CONSTRAINT pk_Recommendations_RecommendationId PRIMARY KEY (RecommendationId),
  CONSTRAINT fk_Recommendations_RestaurantId FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId) ON UPDATE CASCADE ON DELETE SET NULL
);
CREATE TABLE Hours(
  RestaurantId VARCHAR(255),
  Monday VARCHAR(255),
  Tuesday VARCHAR(255),
  Wednesday VARCHAR(255),
  Thursday VARCHAR(255),
  Friday VARCHAR(255),
  Saturday VARCHAR(255),
  Sunday VARCHAR(255),
  CONSTRAINT pk_Hours_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_Hours_RestaurantId FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Attributes(
  RestaurantId VARCHAR(255),
  Attributes TEXT,
  CONSTRAINT pk_Attributes_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_Attributes_RestaurantId FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Categories(
  RestaurantId VARCHAR(255),
  Categories TEXT,
  CONSTRAINT pk_Categories_RestaurantId PRIMARY KEY (RestaurantId),
  CONSTRAINT fk_Categories_RestaurantId FOREIGN KEY (RestaurantId) REFERENCES Restaurants(RestaurantId) ON UPDATE CASCADE ON DELETE CASCADE
);
