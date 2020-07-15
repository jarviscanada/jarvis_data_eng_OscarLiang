#!/bin/bash
# Bash script for collecting usage information to store in database

# Function to parse information from a command
parse_info () {

  string_to_parse=$1
  search_line=$2
  awk_command=$3

  echo "$string_to_parse" | egrep "$search_line" | awk "$awk_command"
}

# Arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Usage statement
usage="Usage: scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password"

if [ "$#" != "5" ]; then
    echo "Error: invalid number of arguments. $usage"
    exit 1
fi

# Parse hardware info
mem_info=$(free -k)
cpu_info=$(vmstat)
disk_info=$(vmstat -d)
disk_space=$(df -BM)
hostname=$(hostname -f)
timestamp=$(date "+%Y-%m-%d %H:%M:%S")
memory_free=$(parse_info "$mem_info" "Mem:" '{print $4}' | xargs)
cpu_idle=$(parse_info "$cpu_info" "^\s*[0-9]+\s+" '{print $15}')
cpu_kernel=$(parse_info "$cpu_info" "^\s*[0-9]+\s+" '{print $14}')
disk_io=$(parse_info "$disk_info" "sda" '{print $10}' | xargs)
disk_available=$(parse_info "$disk_space" "/dev/sda2" '{print $4}' | egrep -o "[0-9]+" | xargs)

# Construct SQL statement
read -d '' statement << _END_
INSERT INTO host_usage
SELECT 
  '$timestamp', 
  id, 
  $memory_free, 
  $cpu_idle, 
  $cpu_kernel, 
  $disk_io, 
  $disk_available 
FROM 
  host_info 
WHERE 
  hostname = '$hostname';
_END_

export PGPASSWORD="$psql_password"
psql -h "$psql_host" -p "$psql_port" -U "$psql_user" -d "$db_name" -c "$statement"
exit $?
