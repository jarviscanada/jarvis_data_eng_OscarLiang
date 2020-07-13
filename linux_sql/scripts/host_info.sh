# !/bin/bash
# Bash script to collect the hardware information of the host computer

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
usage="Usage: ./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password"

if [ "$#" != "5" ]; then
    echo "Error: invalid number of arguments. $usage"
    exit 1
fi

# Parse hardware info
lscpu_out=$(lscpu)
memory_info=$(cat /proc/meminfo)
hostname=$(hostname -f)
cpu_number=$(parse_info "$lscpu_out" "^CPU\(s\):" '{print $2}' | xargs)
cpu_architecture=$(parse_info "$lscpu_out" "^Architecture:" '{print $2}' | xargs)
cpu_model=$(parse_info "$lscpu_out" "^Model name:" '{$1=$2=""; print$0}' | xargs)
cpu_mhz=$(parse_info "$lscpu_out" "^CPU MHz:" '{print $3}' | xargs)
l2_cache=$(parse_info "$lscpu_out" "^L2 cache" '{print $3}' | egrep -o "[0-9]+" | xargs)
total_mem=$(parse_info "$memory_info" "MemTotal:" '{print $2}' | xargs)
timestamp=$(date "+%Y-%m-%d %H:%M:%S")

# Create SQL statement
read -d '' statement << _END_
INSERT INTO host_info
VALUES
  (DEFAULT, '$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');
_END_

export PGPASSWORD="$psql_password"
psql -h "$psql_host" -p "$psql_port" -U "$psql_user" -d "$db_name" -c "$statement"
exit $?
