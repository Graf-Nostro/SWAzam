#!/bin/bash

#############################################################################################
# USAGE                                                                                     #
# ========================================================================================= #
#                                                                                           #
# List songs in the library of a peer:                                                      #
#   ./scripts/peer.sh list florian                                                          #
#                                                                                           #
# Add a song to the library of a peer (songs must already be in the library directory):     #
#   ./scripts/peer.sh add florian f01.wav                                                   #
#                                                                                           #
# Remove a song from the library of a peer:                                                 #
#   ./scripts/peer.sh remove florian f01.wav                                                #
#                                                                                           #
#############################################################################################

BASEDIR=$(cd "$(dirname "$0")/.."; pwd)

java -jar -Dlibrary-directory="$BASEDIR/SWAzamPeer/library" $BASEDIR/SWAzamPeer/bin/SWAzamPeer.jar $1 $2 "$3"
