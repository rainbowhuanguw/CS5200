# CS5200

Spring 2023 Database class repo

## Usage

The `data` stores CSV files.

For `load.sql`, Please change the path to csv based on your environment, and choose `'\n'` or `'\r\n'`.

You may also need to use either `LOCAL` or `IGNORE` to load the csv.

```sql

LOAD DATA LOCAL INFILE "example.csv" INTO TABLE Example
LOAD DATA INFILE "example.csv" IGNORE INTO TABLE Example
```
