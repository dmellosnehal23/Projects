#!/bin/bash

kill `ps | grep "poke.server.Server" | grep "java" | awk '{print $1}'`
