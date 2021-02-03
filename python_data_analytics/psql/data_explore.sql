-- Show table schema
\d+ retail;

-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Check # of records
SELECT COUNT(*) FROM retail;

-- number of clients (e.g. unique client ID)
SELECT COUNT(DISTINCT customer_id) FROM retail;

-- Invoice date range
SELECT MAX(invoice_date), MIN(invoice_date) FROM retail;

-- Number of SKU/merchants
SELECT COUNT(DISTINCT stock_code) FROM retail;

-- Average invoice amount
SELECT 
  AVG(invoice_cost)
FROM
  (SELECT
     SUM(quantity * unit_price) AS invoice_cost
   FROM retail
   WHERE quantity * unit_price > 0
   GROUP BY invoice_no) AS x;

-- Total revenue
SELECT SUM(quantity * unit_price) FROM retail;

-- Total revenue by month
SELECT
  DATE_TRUNC('month', invoice_date) AS invoice_month,
  SUM(quantity * unit_price)
FROM retail
GROUP BY invoice_month
ORDER BY invoice_month;

