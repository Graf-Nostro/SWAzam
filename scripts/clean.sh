cd SWAzamPeer/
ant clean
rm -rf library/*.dat
rm -rf build/

cd ../SWAzamServer/
ant clean
rm -rf bin/
rm -rf build/

cd ../SWAzamClient
ant clean
rm -rf bin/
