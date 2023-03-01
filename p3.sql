SELECT City, Count(*) as RestaurantCount
FROM Restaurants
WHERE Stars >= 4.0
GROUP BY City
ORDER BY RestaurantCount DESC
LIMIT 1
