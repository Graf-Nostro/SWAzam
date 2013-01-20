#!/bin/bash

cd SWAzamPeer/

# Build and deploy 5 peers
mkdir -p bin/
ant deploy-all
ant jar

cd ../scripts
./peer.sh add florian "Leslie - Woods - 01 - The Good In Each Other.wav"
./peer.sh add florian "Leslie - Woods - 05 - Accommodation.wav"
./peer.sh add florian "Seife und Haut - Wide - 01 - Mutter.wav"
./peer.sh add florian "II - High Winter - 01 - High Winter.wav"
./peer.sh add kung "Seife und Haut - Wide - 01 - Mutter.wav"
./peer.sh add kung "Seife und Haut - Wide - 02 - Enterprise.wav"
./peer.sh add stefan "Leslie - Woods - 01 - The Good In Each Other.wav"
./peer.sh add stefan "PPNG - PPNG2 - 01 - Randy.wav"
./peer.sh add andreas "Verschwundene - MariGGGopa - 01 MariGGGopa.wav"
./peer.sh add adnan "II - High Winter - 01 - High Winter.wav"
./peer.sh add adnan "Observer Pattern - Rooms - 01 - Rooms.wav"
./peer.sh add adnan "Seife und Haut - Wide - 01 - Mutter.wav"
./peer.sh add adnan "Leslie - Woods - 01 - The Good In Each Other.wav"
