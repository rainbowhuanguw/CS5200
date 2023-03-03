# 10 questions (draft):


# user: 
# [Youyu] List top 10 people who wrote the most reviews?
SELECT re.UserId, count(ReviewId) as total_reviews, u.UserName
FROM reviews re
JOIN users u ON u.UserId = re.UserId
GROUP BY UserId
ORDER BY count(ReviewId) DESC
LIMIT 10
;

# [Youwei] What users have created tips for Asian restaurants? Provide username and counts. 
SELECT UserName, COUNT(Tips.UserId) AS CNT FROM Tips
  LEFT JOIN YelpUsers ON Tips.UserId = YelpUsers.UserId
  LEFT JOIN Categories ON Tips.RestaurantId = Categories.RestaurantId
  WHERE categories like "%Asian%"
  GROUP BY Tips.UserId
  ORDER BY CNT DESC;

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

# [Caihong]For each restaurant type, what is the ratio of bad reviews (rating of 2 and below) to all of the reviews.

# [Min]What is the percentage of restaurants opening 24/7?
SELECT
    COUNT(*) AS total_restaurants,
    SUM(CASE WHEN Hours.Monday = '00:00-24:00' AND
                  Hours.Tuesday = '00:00-24:00' AND
                  Hours.Wednesday = '00:00-24:00' AND
                  Hours.Thursday = '00:00-24:00' AND
                  Hours.Friday = '00:00-24:00' AND
                  Hours.Saturday = '00:00-24:00' AND
                  Hours.Sunday = '00:00-24:00'
             THEN 1 ELSE 0 END) AS restaurants_open_24_7,
    (SUM(CASE WHEN Hours.Monday = '00:00-24:00' AND
                   Hours.Tuesday = '00:00-24:00' AND
                   Hours.Wednesday = '00:00-24:00' AND
                   Hours.Thursday = '00:00-24:00' AND
                   Hours.Friday = '00:00-24:00' AND
                   Hours.Saturday = '00:00-24:00' AND
                   Hours.Sunday = '00:00-24:00'
              THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS percentage_open_24_7
FROM
    Restaurants
INNER JOIN
    Hours
ON
    Restaurants.RestaurantId = Hours.RestaurantId;

# location related 
# [Min]How many restaurants are within 1-mile walking distance of our Airbnb location and have a rating of 4.5 or higher?
SELECT COUNT(*) AS 'Number of Restaurants'
FROM Restaurants
WHERE ST_DISTANCE_SPHERE(point(Restaurants.Latitude, Restaurants.Longitude), point(Airbnb.Latitude, Airbnb.Longitude)) <= 1609.34 /* Convert 1 mile to meters */
AND Stars >= 4.5;

# [Min]What are the top 10 vegetarian restaurants in the Greater Seattle Area?
SELECT r.Name, r.Stars
FROM Restaurants r
JOIN Categories c ON r.RestaurantId = c.RestaurantId
WHERE c.Categories LIKE '%Vegetarian%' OR c.Categories LIKE '%Vegan%'
AND r.City = 'Seattle'
ORDER BY r.Stars DESC
LIMIT 10;