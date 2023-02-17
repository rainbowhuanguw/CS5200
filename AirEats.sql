# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS AirEats;
USE AirEats;

DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Tips;
DROP TABLE IF EXISTS Hours;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS YelpUsers;
DROP TABLE IF EXISTS Airbnb;
DROP TABLE IF EXISTS Host;
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

CREATE TABLE Users (
  UserName VARCHAR(255),
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  Email VARCHAR(255),
  Phone VARCHAR(20),
  AirbnbId INT,
  CONSTRAINT pk_Users_UserName PRIMARY KEY (UserName),
  CONSTRAINT fk_Airbnb_AirbnbId FOREIGN KEY (AirbnbId)
		REFERENCES Airbnb(AirbnbId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Host (
	HostId Int,
    HostName VARCHAR(255),
    CONSTRAINT pk_Host_HostId PRIMARY KEY (HostId)
);
CREATE TABLE Restaurants(
	RestaurantId VARCHAR(22),
    Name VARCHAR(255),
    Address VARCHAR(255),
    City VARCHAR(255),
    State VARCHAR(10),
    Zip VARCHAR(10) NOT NULL,
    Latitude DECIMAL(10,7) NOT NULL,
    Longitude DECIMAL(10,7) NOT NULL,
    Stars DECIMAL(2,1) NOT NULL,
	CONSTRAINT pk_Restaurant_RestaurantId PRIMARY KEY (RestaurantId)
);

CREATE TABLE Reviews (
	ReviewId VARCHAR(255),
    UserId VARCHAR(255),
    RestaurantId VARCHAR(255),
	Stars INT,
	# Date TIMESTAMP,
	#Might remove content field
	Useful DECIMAL(7,1) NOT NULL,
	Funny DECIMAL(7,1) NOT NULL,
	Cool DECIMAL(7,1) NOT NULL,
	Content TEXT,
    Date datetime,
	CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
	CONSTRAINT fk_Reviews_RestaurantId FOREIGN KEY (RestaurantId)
		REFERENCES Restaurants(RestaurantId)
		ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Tips(
    UserId VARCHAR(22),
    RestaurantId VARCHAR(22),
    Compliment_count INT,
    Date datetime,
    Context TEXT,
	CONSTRAINT pk_Tips_TipId PRIMARY KEY (UserId, RestaurantId, Date),
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

CREATE TABLE Hours(
	RestaurantId VARCHAR(255),
    Monday VARCHAR(255),
    Tuesday VARCHAR(255),
    Wednesday VARCHAR(255),
    Thurday VARCHAR(255),
    Friday VARCHAR(255),
    Saturday VARCHAR(255),
    Sunday VARCHAR(255),
    CONSTRAINT pk_Hours_RestaurantId PRIMARY KEY (RestaurantId),
    CONSTRAINT fk_Hours_RestaurantId FOREIGN KEY (RestaurantId)
		REFERENCES Restaurants(RestaurantId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

LOAD DATA INFILE 'users.csv' INTO TABLE YelpUsers
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;

LOAD DATA INFILE 'business.csv' INTO TABLE restaurants
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (RestaurantId,Name,Address,City,State,Zip,Latitude,Longitude,Stars,@dummy,@dummy);
  
LOAD DATA local INFILE "airbnb_listings_usa.csv" INTO TABLE Airbnb
  # Fields are not quoted.
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@dummy,AirbnbId, Name,@dummy,@dummy,@dummy,Neighborhood,latitude,longitude,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy, State, City);

LOAD DATA INFILE 'hosts.csv' INTO TABLE host
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA INFILE 'review_small.csv' INTO TABLE reviews
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
 LOAD DATA INFILE 'tip.csv' IGNORE INTO TABLE tips
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;


LOAD DATA INFILE 'hours.csv'  INTO TABLE hours
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES; 
  
select *
from host;

select count(*) as count_reviews
from reviews;

select count(*) as count_users
from users;

select count(*) as count_tips
from tips;

