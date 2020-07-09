# !/bin/bash
# Bash script for collecting usage information to store in database

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
hostname=$(hostname -f)
timestamp=$(date "+%Y-%m-%d %H:%M:%S")
memory_free=$(free -k | egrep "Mem:" | awk '{print $4}' | xargs)
cpu_idle=$(vmstat | egrep "^\s*[0-9]+\s+" | awk '{print $15}')
cpu_kernel=$(vmstat | egrep "^\s*[0-9]+\s+" | awk '{print $14}')
disk_io=$(vmstat -d | egrep "sda" | awk '{print $10}' | xargs)
disk_available=$(df -BM | egrep "/dev/sda2" | awk '{print $4}' | egrep -o "[0-9]+" | xargs)

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
