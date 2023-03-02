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

# [YiCheng]What are the top 100 tipped Asian restaurants since Jan 2015?
SELECT Restaurants.Name, COUNT(Tips.TipId) AS NumTips
FROM Tips
INNER JOIN Restaurants ON Tips.RestaurantId = Restaurants.RestaurantId
INNER JOIN Categories ON Restaurants.RestaurantId = Categories.RestaurantId
WHERE Categories.Categories LIKE '%Asian%' AND Tips.Date >= '2015-01-01'
GROUP BY Restaurants.RestaurantId
ORDER BY COUNT(Tips.TipId) DESC
LIMIT 100;

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
SELECT AVG(Reviews.Stars) average_stars, Categories.category
FROM Reviews
INNER JOIN Restaurants
ON Reviews.RestaurantId = Restaurants.RestaurantId
INNER JOIN Categories
ON Categories.RestaurantId = Reviews.RestaurantId
WHERE Categories.category like '%Restaurant%'
GROUP BY Categories.category
ORDER BY average_stars DESC
LIMIT 10;

# [Min]What is the percentage of restaurants opening 24/7?


# location related 
# [Min]How many restaurants are within 1-mile walking distance of our Airbnb location and have a rating of 4.5 or higher?

# [Min]What are the top 10 vegetarian restaurants in the Greater Seattle Area?
