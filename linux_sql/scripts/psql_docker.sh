# !/bin/bash
# Bash script for creating, starting, and stopping a psql docker container

# Command line arguments
action=$1
db_user=$2
db_pass=$3

# If docker is not running, start docker
systemctl status docker || systemctl start docker

case $action in
    'create')
        echo 'create'
        ;;
    'start')
        echo 'start'
        ;;
    'stop')
        echo 'stop'
        ;;
    *)
        echo 'invalid'
        ;;
esac
