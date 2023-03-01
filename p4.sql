SELECT City, Count(*) AS RestaurantCount
FROM Restaurants
WHERE State = "CA"
GROUP BY City
HAVING RestaurantCount > 100
ORDER BY RestaurantCount DESC