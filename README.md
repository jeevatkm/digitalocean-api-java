Note
----
**Digital Ocean API v2 development is in progress**

***Periodically 2.0-SNAPSHOT version is pushed to OSS Snapshot Repo, give a try and share feedback via Issues.***

* * *

DigitalOcean API Client in Java
-------------------------------
[![Build Status](https://travis-ci.org/jeevatkm/digitalocean-api-java.svg?branch=master)](https://travis-ci.org/jeevatkm/digitalocean-api-java)

Welcome to DigitalOcean's API Client written in Java. I have created a simple and meaningful wrapper methods for DigitalOcean's API. All of the RESTful that you find in [DigitalOcean API's][1] will be made available via simple java method(s).

> Simple and Lightweight Library for Enterprise Application/Utilities Integration

How to make use of digitalocean-api-client?
------------------------------------
* **Application/Project Dependencies**
	* Maven dependency
	<pre><code>&lt;dependency>
    		&lt;groupId>com.myjeeva.digitalocean&lt;/groupId>
    		&lt;artifactId>digitalocean-api-client&lt;/artifactId>
    		&lt;version>2.0-SNAPSHOT&lt;/version>
		&lt;/dependency></code></pre>
	* Grails dependency
	<pre><code>compile 'com.myjeeva.digitalocean:digitalocean-api-client:2.0-SNAPSHOT'</code</pre>
	* Groovy Grape
	<pre><code>@Grapes( 
@Grab(group='com.myjeeva.digitalocean', module='digitalocean-api-client', version='2.0-SNAPSHOT') 
)</code></pre>
	* Scala SBT
	<pre><code>libraryDependencies += "com.myjeeva.digitalocean" % "digitalocean-api-client" % "2.0-SNAPSHOT"</code></pre>


Documentation
-------------
...***development is-in-progress***...

Examples
--------
Have a look on [DigitalOceanIntegrationTest][7] simple and easy to use.

Supported DigitalOcean API's and Functionalities
------------------------------------------------

...***development is-in-progress***...


Issue Tracker
-------------
Please submit any bugs or annoyances on the [Issues][3]

Author
------
Jeevanandam M. - jeeva@myjeeva.com ([myjeeva.com][5])

License
-------
See [LICENSE.txt][6]


[1]: https://developers.digitalocean.com
[2]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.5/
[3]: https://github.com/jeevatkm/digitalocean-api-java/issues
[4]: https://oss.sonatype.org/content/repositories/snapshots/com/myjeeva/digitalocean/digitalocean-api-client/
[5]: http://myjeeva.com
[6]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/LICENSE.txt
[7]: https://github.com/jeevatkm/digitalocean-api-java/blob/tree/v2.0/src/test/java/com/myjeeva/digitalocean/DigitalOceanIntegrationTest.java
[8]: http://search.maven.org/remotecontent?filepath=com/myjeeva/digitalocean/digitalocean-api-client/1.5/digitalocean-api-client-1.5.jar
[9]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.5/com/myjeeva/digitalocean/pojo/Event.html
[10]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.5/com/myjeeva/digitalocean/pojo/Backup.html
[11]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.5/com/myjeeva/digitalocean/pojo/Snapshot.html
[12]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.5/com/myjeeva/digitalocean/pojo/Droplet.html
[13]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.5/com/myjeeva/digitalocean/pojo/Response.html
[14]: https://github.com/jeevatkm/digitalocean-api-java/issues/1
[15]: https://github.com/jeevatkm/digitalocean-api-java/issues/5
[16]: https://github.com/jeevatkm/digitalocean-api-java/issues/3
[17]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/src/test/java/com/myjeeva/digitalocean/DigitalOceanMockTest.java
[18]: https://github.com/jeevatkm/digitalocean-api-java/issues/6
[19]: https://github.com/jeevatkm/digitalocean-api-java/issues/7
[20]: https://github.com/jeevatkm/digitalocean-api-java/issues/9
[21]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.5/com/myjeeva/digitalocean/pojo/DropletStatus.html
