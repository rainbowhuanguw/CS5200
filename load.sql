SELECT COUNT(*) FROM Restaurants;

LOAD DATA LOCAL INFILE '/Users/rainbowh/Desktop/CS5200_database/project/git/business.csv'
INTO TABLE table_name
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS;