--Query to group hosts by number of CPUs and sort by memory in descending order.
SELECT 
  cpu_number, id AS host_id, total_mem
FROM
  host_info
ORDER BY 
  cpu_number ASC, total_mem DESC;
