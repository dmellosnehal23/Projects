#
# start the server
#

import sys

from bottle import run
from moo import setup

if len(sys.argv) > 2:
    base = sys.argv[1]
    print base
    conf_fn = sys.argv[2]
    print conf_fn
    setup(base,conf_fn)

    run(host='localhost', port=8081)
else:
    print "usage:", sys.argv[0],"[base_dir] [conf file]"

