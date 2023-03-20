# 10 questions (draft):
Use AirEats;

# user: 
# [Youyu] List top 10 people who wrote the most reviews?
SELECT re.UserId, count(ReviewId) as total_reviews, u.UserName
FROM reviews re
JOIN YelpUsers u ON u.UserId = re.UserId
GROUP BY re.UserId
ORDER BY count(ReviewId) DESC
LIMIT 10;

# [Youwei] What are the average numbers of tips created by users who have created tips for Asian restaurants?
SELECT AVG(UserTip.CNT) AS Average FROM (
  SELECT UserId, COUNT(*) AS CNT 
    FROM Tips
      LEFT JOIN Categories ON Tips.RestaurantId = Categories.RestaurantId
      WHERE categories like "%Asian%"
    GROUP BY Tips.UserId
  ) AS UserTip;

# restaurants 
# [YuYang]What cities in the US have the most number of restaurants with average ratings of 4 and above? 

SELECT City, Count(*) as RestaurantCount
FROM Restaurants
WHERE Stars >= 4.0
GROUP BY City
ORDER BY RestaurantCount DESC
LIMIT 1;

# [YuYang]What cities have more than 100 Restaurants in California? 

SELECT City, Count(*) AS RestaurantCount
FROM Restaurants
WHERE State = "CA"
GROUP BY City
HAVING RestaurantCount > 100
ORDER BY RestaurantCount DESC;

# [YiCheng]What are the top 10 tipped Asian restaurants since Jan 2015?
SELECT Restaurants.Name, COUNT(Tips.TipId) AS NumTips
FROM Tips
INNER JOIN Restaurants ON Tips.RestaurantId = Restaurants.RestaurantId
INNER JOIN Categories ON Restaurants.RestaurantId = Categories.RestaurantId
WHERE Categories.Categories LIKE '%Asian%' AND Tips.Date >= '2015-01-01'
GROUP BY Restaurants.RestaurantId
ORDER BY COUNT(Tips.TipId) DESC
LIMIT 10;

# [Youwei] List the type of the top 10 most popular restaurant based on the total number of reviews and tips. 
SELECT Restaurants.Name, Categories, SUM(CNT) AS TOTAL FROM
  (SELECT RestaurantId,COUNT(*) AS CNT FROM Reviews
     GROUP BY RestaurantId
   UNION ALL
   SELECT RestaurantId,COUNT(*) AS CNT FROM Tips
     GROUP BY RestaurantId) AS ReviewTip 
  LEFT JOIN Categories 
    ON ReviewTip.RestaurantId = Categories.RestaurantId
  LEFT JOIN Restaurants
    ON ReviewTip.RestaurantId = Restaurants.RestaurantId
  WHERE categories like "%Restaurant%"
  GROUP BY ReviewTip.RestaurantId
  ORDER BY TOTAL DESC
  LIMIT 10;

# [Caihong]What are the top 10 highly rated restaurant types?
SELECT AVG(Reviews.Stars) AS average_stars, COUNT(*) AS review_count, Categories.categories
FROM Reviews
INNER JOIN Restaurants
ON Reviews.RestaurantId = Restaurants.RestaurantId
INNER JOIN Categories
ON Categories.RestaurantId = Reviews.RestaurantId
WHERE Categories.categories like '%Restaurant%'
GROUP BY Categories.categories
HAVING review_count > 10
ORDER BY average_stars DESC
LIMIT 10;

# [Min]What is the percentage of restaurants opening 24/7?
SELECT
	COUNT(*) AS total_restaurants,
	SUM(CASE WHEN Hours.Monday = '0:0-0:0' AND
              	Hours.Tuesday = '0:0-0:0' AND
              	Hours.Wednesday = '0:0-0:0' AND
              	Hours.Thursday = '0:0-0:0' AND
              	Hours.Friday = '0:0-0:0' AND
              	Hours.Saturday = '0:0-0:0' AND
              	Hours.Sunday = '0:0-0:0'
         	THEN 1 ELSE 0 END) AS restaurants_open_24_7,
	(SUM(CASE WHEN Hours.Monday = '0:0-0:0' AND
               	Hours.Tuesday = '0:0-0:0' AND
               	Hours.Wednesday = '0:0-0:0' AND
               	Hours.Thursday = '0:0-0:0' AND
               	Hours.Friday = '0:0-0:0' AND
               	Hours.Saturday = '0:0-0:0' AND
               	Hours.Sunday = '0:0-0:0'
          	THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS percentage_open_24_7
FROM
	Restaurants
INNER JOIN
	Hours
ON
	Restaurants.RestaurantId = Hours.RestaurantId;

# location related 
# [Min]How many restaurants within 1-mile walking distance of the Airbnb location (longitude: -119.686624, latitude:34.4274939) have ratings of 4.5 or higher?
SELECT COUNT(*) AS 'Number of Restaurants'
FROM Restaurants
WHERE ST_DISTANCE_SPHERE(point(Restaurants.Longitude, Restaurants.Latitude), point(-119.686624, 34.4274939)) <= 1609.34 /* Convert 1 mile to meters */
AND Stars >= 4.5;

# [Min]What are the top 10 most reviewed vegetarian restaurants rating above 4?
SELECT r.Name, r.Stars, COUNT(*) AS Review_Count
FROM Restaurants r
JOIN Categories c 
ON r.RestaurantId = c.RestaurantId
JOIN Reviews 
ON r.RestaurantId = Reviews.RestaurantId
WHERE (c.Categories LIKE '%Vegetarian%' OR c.Categories LIKE '%Vegan%')
AND r.Stars > 4
GROUP BY r.Name, r.Stars
ORDER BY Review_Count DESC
LIMIT 10;
