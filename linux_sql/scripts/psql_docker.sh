# !/bin/bash
# Bash script for creating, starting, and stopping a psql docker container

# Command line arguments
action=$1
db_user=$2
db_pass=$3

# Usage statement
usage="Usage: ./scripts/psql_docker.sh start|stop|create [db_username][db_password]"

# If docker is not running, start docker
systemctl status docker || systemctl start docker

# Check if docker container exists (value is 2 if exists)
container_exists=$(docker container ls -a -f name=jrvs-psql | wc -l)

# Parse action
case $action in
    'create')
        if [ "$container_exists" = "2" ]; then
            echo "Error: container already exists. $usage" >&2
            exit 1
        fi

        if [ "$db_pass" = "" ]; then
            echo "Error: missing username and/or password. $usage" >&2
            exit 1
        fi

        # If no errors, create docker container
        docker volume create pgdata
        docker run --name jrvs-psql -e POSTGRES_PASSWORD=${db_pass} -e POSTGRES_USER=${db_user} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
        exit $?
        ;;
    'start')
        echo 'start'
        ;;
    'stop')
        echo 'stop'
        ;;
    *)
        echo "Error: invalid action. $usage" >&2
        exit 1
        ;;
esac
