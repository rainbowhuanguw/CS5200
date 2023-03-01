# 10 questions (draft):


# user: 
# [Youyu] List top 10 people who wrote the most reviews?

# [Youwei] What users have created tips for Asian restaurants? Provide username and counts. 
SELECT UserName, COUNT(Tips.UserId) AS CNT FROM Tips
  LEFT JOIN YelpUsers ON Tips.UserId = YelpUsers.UserId
  LEFT JOIN Categories ON Tips.RestaurantId = Categories.RestaurantId
  WHERE categories like "%Asian%"
  GROUP BY Tips.UserId
  ORDER BY CNT DESC;

# restaurants 
# [YuYang]What cities in the US have the most number of restaurants with average ratings of 4 and above? 

# [YuYang]What are the top 10 rated restaurants in Seattle? 

# [YiCheng]What are the top 100 tipped Asian restaurants since Jan 2022?

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


# location related 
# [Min]How many restaurants are within 1-mile walking distance of our Airbnb location and have a rating of 4.5 or higher?

# [Min]What are the top 10 vegetarian restaurants in the Greater Seattle Area?
