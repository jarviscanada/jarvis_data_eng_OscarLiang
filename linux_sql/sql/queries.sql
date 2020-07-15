--Query to group hosts by number of CPUs and sort by memory in descending order.
SELECT 
  cpu_number, id AS host_id, total_mem
FROM
  host_info
ORDER BY 
  cpu_number ASC, total_mem DESC;

--Query to find the average used memory in a 5-minute period
SELECT 
  id,
  hostname,
  date_trunc('hour', u.timestamp) + date_part('minute', u.timestamp)::int / 5 * interval '5 min' AS interval,
  AVG((total_mem - memory_free) * 100 / total_mem)::int AS percentage_used
FROM
  host_info i JOIN
    host_usage u ON
    i.id = u.host_id
GROUP BY
  id,
  interval
ORDER BY
  id,
  interval;

--Query to detect server failure, defined as outputting less than 3 data points in a 5-minute interval
SELECT
  host_id,
  date_trunc('hour', timestamp) + date_part('minute', timestamp)::int / 5 * interval '5 min' AS ts,
  COUNT(*) as num_data_points
FROM
  host_usage
GROUP BY
  host_id, ts
HAVING
  COUNT(*) < 3;
