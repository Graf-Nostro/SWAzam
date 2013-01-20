SWAzam Assignment2
==================

Ant Build Script
----------------

I added Ant build scripts to all three projects. To be able to deploy **SWAzamPeer** and **SWAzamServer** you need to manually start the Tomcat server. Before you can do that you have to add a user to the Tomcat configuration.

Open `conf/tomcat-users.xml` (inside your Tomcat installation directory) and add the following lines:

```xml
<tomcat-users>
    <user name="admin" password="secret" roles="manager-gui,manager-script,admin-gui,admin-script"/>
</tomcat-users>
```

Now you should be able to start the Tomcat server (there is also a `.bat` file for Windows users):

    /path/to/tomcat/bin/startup.sh

You can stop the server by calling:

    /path/to/tomcat/bin/shutdown.sh

Now you need to configure the build process. Therefore copy `build.properties.dist` inside each project directory and rename it to `build.properties`. This file is in `.gitignore` and therefore settings specifically to your system go there:

    cp SWAzamClient/build.properties.dist SWAzamClient/build.properties
    cp SWAzamPeer/build.properties.dist SWAzamPeer/build.properties
    cp SWAzamServer/build.properties.dist SWAzamServer/build.properties

You should now be able to compile and deploy the server components (you have to `cd` into the specific project directory):

    ant build-and-deploy

The GUI of the client can also be run with Ant:

    ant run


Software to install
-------------------

* [Tomcat 7](http://tomcat.apache.org/download-70.cgi) *(run INSTALDIR/startup.sh[bat] and check localhost:8080 in browser)*
* [Eclipse Plugin](http://www.mulesoft.com/tomcat-eclipse) *(choose Tomcat 7)*

To start the server run project on server you should see a index.html

- [SWAzamServer](http://localhost:8080/SWAzamServer/)
- [SWAzamPeer](http://localhost:8080/SWAzamPeer/)

I have created the basic layout of the project and added classes from the documention. The SWAzamSERVER/PEER is a Dynamic Web Project (for tomcat purpose).



