# !/bin/bash
# Bash script to collect the hardware information of the host computer

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
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{$1=$2=""; print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache" | awk '{print $3}' | egrep -o "[0-9]+" | xargs)
total_mem=$(cat /proc/meminfo | egrep "MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(date "+%Y-%m-%d %H:%M:%S")

# Create SQL statement
read -d '' statement << _END_
INSERT INTO host_info
VALUES
  (DEFAULT, '$hostname', $cpu_number, '$cpu_architecture', '$cpu_model', $cpu_mhz, $l2_cache, $total_mem, '$timestamp');
_END_

export PGPASSWORD="$psql_password"
psql -h "$psql_host" -p "$psql_port" -U "$psql_user" -d "$db_name" -c "$statement"
