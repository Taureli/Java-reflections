#!/bin/bash

for((i=1;i<=10;i++))
do
echo "Wykonanie: $i" >> outfile
java Main >> outfile
done