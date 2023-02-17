# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS AirEats;
USE AirEats;

DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Tips;
DROP TABLE IF EXISTS Friends;
DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Airbnb;
DROP TABLE IF EXISTS Host;

/**
CREATE TABLE Users (
  UserId VARCHAR(22),
  UserName VARCHAR(255),
  Password VARCHAR(255) NOT NULL,
  FirstName VARCHAR(255) NOT NULL,
  LastName VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  Phone VARCHAR(255),
  CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId)
);
**/
CREATE TABLE Users (
  UserId VARCHAR(22),
  UserName VARCHAR(255),
  review_count int,
  yelping_since datetime,
  useful int,
  funny int,
  cool int,
  fans int,
  average_stars decimal(4,2),
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
  compliment_photos int
);


CREATE TABLE Airbnb (
	AirbnbId INT,
    Name VARCHAR(255),
    City VARCHAR(255),
    Neighborhood VARCHAR(255),
    State VARCHAR(22),
    Latitude DECIMAL(10,7) NOT NULL,
    Longitude DECIMAL(10,7) NOT NULL,
    CONSTRAINT pk_Airbnb_AirbnbId PRIMARY KEY (AirbnbId)
);
CREATE TABLE Host (
	HostId Int,
    HostName VARCHAR(22),
	AirbnbId INT,
    CONSTRAINT pk_Host_HostId PRIMARY KEY (HostId)
);
CREATE TABLE Restaurants(
	RestaurantId INT,
    Name VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    State CHAR(2),
    Zip CHAR(5) NOT NULL,
    Latitude DECIMAL(10,7) NOT NULL,
    Longitude DECIMAL(10,7) NOT NULL,
    Stars DECIMAL(2,1) NOT NULL,
	CONSTRAINT pk_Restaurant_RestaurantId PRIMARY KEY (RestaurantId)
);
CREATE TABLE Friends(
	UserId VARCHAR(22),
    FriendId VARCHAR(22),
    CONSTRAINT pk_Friends_AirbnbId PRIMARY KEY (UserId),
	CONSTRAINT fk_Friends_FriendId FOREIGN KEY (FriendId)
		REFERENCES Users(UserId)
		ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE Reviews (
	ReviewId INT AUTO_INCREMENT,
	Stars INT,
	Date TIMESTAMP,
	#Might remove content field
	Content VARCHAR(1024),
	Useful DECIMAL(2,1) NOT NULL,
	Funny DECIMAL(2,1) NOT NULL,
	Cool DECIMAL(2,1) NOT NULL,
	RestaurantId INT,
	CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
	CONSTRAINT fk_Reviews_RestaurantId FOREIGN KEY (RestaurantId)
		REFERENCES Restaurants(RestaurantId)
		ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Tips(
	TipId INT,
    UserId VARCHAR(22),
    Content VARCHAR(255),
    Date TIMESTAMP,
    ComplimentCount INT,
    RestaurantId INT,
	CONSTRAINT pk_Tips_TipId PRIMARY KEY (TipId),
	CONSTRAINT fk_Tips_RestaurantId FOREIGN KEY (RestaurantId)
		REFERENCES Restaurants(RestaurantId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Recommendations(
	RecommendationId INT,
	RestaurantId INT,
    UserId VARCHAR(22),
	CONSTRAINT pk_Recommendations_RecommendationId PRIMARY KEY (RecommendationId),
	CONSTRAINT fk_Recommendations_RestaurantId FOREIGN KEY (RestaurantId)
		REFERENCES Restaurants(RestaurantId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

LOAD DATA INFILE 'users.csv' INTO TABLE users
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

LOAD DATA INFILE 'business.csv' INTO TABLE restaurants
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
LOAD DATA local INFILE "airbnb_listings_usa.csv" INTO TABLE Airbnb
  # Fields are not quoted.
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@dummy,AirbnbId, Name,@dummy,@dummy,@dummy,Neighborhood,latitude,longitude,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy, State, City);



select *
from restaurants;




