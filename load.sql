LOAD DATA INFILE 'users.csv' INTO TABLE YelpUsers
  FIELDS TERMINATED BY ','
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;

LOAD DATA INFILE 'business.csv' INTO TABLE Restaurants
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (RestaurantId,Name,Address,City,State,Zip,Latitude,Longitude,Stars,@dummy,@dummy);
  
LOAD DATA local INFILE "cleaned_airbnb_listings_usa.csv" INTO TABLE Airbnb
  # Fields are not quoted.
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  # Windows platforms may need '\r\n'.#  # Windows platforms may need '\r\n'.
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (@dummy,AirbnbId, Name,@dummy,@dummy,@dummy,Neighborhood,latitude,longitude,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy,@dummy, State, City);

LOAD DATA INFILE 'hosts.csv' INTO TABLE Hosts
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA INFILE 'review_small.csv' INTO TABLE Reviews
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA INFILE 'tip.csv' IGNORE INTO TABLE Tips
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES
  (UserId,RestaurantId,Compliment_count,Date,Context);

LOAD DATA INFILE 'hours.csv' IGNORE INTO TABLE Hours
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES; 

LOAD DATA INFILE 'attributes_new.csv'  INTO TABLE Attributes
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES; 
  
LOAD DATA INFILE 'category.csv'  INTO TABLE categories
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES; 

LOAD DATA INFILE 'hours.csv' IGNORE INTO TABLE hours
  FIELDS TERMINATED BY ','
  ENCLOSED BY '"'
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES; 
