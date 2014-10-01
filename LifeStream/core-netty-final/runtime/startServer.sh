#!/bin/bash
#
# This script is used to start the server
#

export POKE_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo "** starting server from ${POKE_HOME} **"

cd ${POKE_HOME}

JAVA_MAIN='poke.server.Server'
#COMMENTED BELOW LINES BY SNEHAl
#JAVA_ARGS="${POKE_HOME}/server.conf"
#echo "** config: ${JAVA_ARGS} **"

# see http://java.sun.com/performance/reference/whitepapers/tuning.html
JAVA_TUNE='-Xms500m -Xmx1000m'

for ((  i = 1 ;  i <= $1;  i++  ))
do
    JAVA_ARGS="${POKE_HOME}/server$i.conf"
    LOG_FILE="server$i.log"
    echo "** config: ${JAVA_ARGS} **"
    java ${JAVA_TUNE} -cp .:${POKE_HOME}/../lib/'*':${POKE_HOME}/../classes ${JAVA_MAIN} ${JAVA_ARGS} > ${LOG_FILE}  2>&1 &
done
