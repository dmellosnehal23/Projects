#!/bin/bash
#
# This script is used to poke the server
#

export POKE_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo "** start oking server from ${POKE_HOME} **"

cd ${POKE_HOME}

JAVA_MAIN='poke.demo.Jab'

# see http://java.sun.com/performance/reference/whitepapers/tuning.html
JAVA_TUNE='-Xms500m -Xmx1000m'

ports[1]="5570"
ports[2]="5571"
ports[3]="5572"
ports[4]="5573"

for ((  i = 1 ;  i <= $1;  i++  ))
do
    JAVA_ARGS="jab${i} ${ports[${i}]}"
    LOG_FILE="poke$i.log"
    echo "** config: ${JAVA_ARGS} **"
    java ${JAVA_TUNE} -cp .:${POKE_HOME}/../lib/'*':${POKE_HOME}/../classes ${JAVA_MAIN} ${JAVA_ARGS} > ${LOG_FILE}  2>&1 &
done
