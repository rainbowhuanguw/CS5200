USE aireats;

-- 8 --

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
    
-- 9 --

SELECT COUNT(*) AS 'Number of Restaurants'
FROM Restaurants
WHERE ST_DISTANCE_SPHERE(point(Restaurants.Latitude, Restaurants.Longitude), point(Airbnb.Latitude, Airbnb.Longitude)) <= 1609.34 /* Convert 1 mile to meters */
AND Stars >= 4.5;

-- 10 --

SELECT r.Name, r.Stars
FROM Restaurants r
JOIN Categories c ON r.RestaurantId = c.RestaurantId
WHERE c.Categories LIKE '%Vegetarian%' OR c.Categories LIKE '%Vegan%'
AND r.City = 'Seattle'
ORDER BY r.Stars DESC
LIMIT 10;




