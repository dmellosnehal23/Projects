#!/bin/bash

kill `ps | grep "poke.demo.Jab" | grep "java" | awk '{print $1}'`
