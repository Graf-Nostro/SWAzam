#!/bin/bash

cd SWAzamPeer/

# Build and deploy 5 peers
# ant deploy-all
# ant jar

cd ../scripts
./peer.sh add florian f01.wav
./peer.sh add florian f05.wav
./peer.sh add florian ss01.wav
./peer.sh add florian j01.wav
./peer.sh add kung ss01.wav
./peer.sh add kung ss02.wav
./peer.sh add stefan f01.wav
./peer.sh add stefan b01.wav
./peer.sh add andreas d01.wav
./peer.sh add adnan j01.wav
./peer.sh add adnan od01.wav
./peer.sh add adnan ss01.wav
./peer.sh add adnan f01.wav
