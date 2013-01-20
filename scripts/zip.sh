#!/bin/bash

./scripts/clean.sh
zip -9 -r swa-assignment2-group01-swazam.zip ./* -x "\*.git\* \*.DS_Store\* \*.metadata\*"
