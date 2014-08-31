Note
----
**Digital Ocean API v2 development is completed**

***2.0-SNAPSHOT version is pushed to OSS Snapshot Repo, give a try and share feedback via Issues.***

* * *

DigitalOcean API Client in Java
-------------------------------
[![Build Status](https://travis-ci.org/jeevatkm/digitalocean-api-java.svg?branch=master)](https://travis-ci.org/jeevatkm/digitalocean-api-java)

Welcome to DigitalOcean API Client written in Java. Created a simple and meaningful wrapper methods for [DigitalOcean RESTful APIs][1].

> Simple and Lightweight Library for Enterprise Application or Utilities Integration

How to make use of digitalocean-api-client?
-------------------------------------------
* **Application or Project Dependencies**
	* Maven dependency
	<pre>&lt;dependency>
    		&lt;groupId>com.myjeeva.digitalocean&lt;/groupId>
    		&lt;artifactId>digitalocean-api-client&lt;/artifactId>
    		&lt;version>2.0-SNAPSHOT&lt;/version>
		&lt;/dependency></pre>
	* Grails dependency
	<pre>compile 'com.myjeeva.digitalocean:digitalocean-api-client:2.0-SNAPSHOT'</pre>
	* Groovy Grape
	<pre>@Grapes( 
@Grab(group='com.myjeeva.digitalocean', module='digitalocean-api-client', version='2.0-SNAPSHOT') 
)</pre>
	* Scala SBT
	<pre>libraryDependencies += "com.myjeeva.digitalocean" % "digitalocean-api-client" % "2.0-SNAPSHOT"</pre>


Documentation
-------------
See [JavaDocs - DigitalOcean API Client in Java][2]

Examples
--------
Have a look on [DigitalOceanIntegrationTest][7] simple and easy to use.

Supported API's and Revision Logs
---------------------------------
* **Released in v2.0**
	* Actions
	<pre>
	Actions getAvailableActions(Integer pageNo)
	Actions	getAvailableDropletActions(Integer dropletId, Integer pageNo)
	Actions	getAvailableImageActions(Integer imageId, Integer pageNo)
	Action getActionInfo(Integer actionId)</pre>
	* Droplets
	<pre>
	Droplets getAvailableDroplets(Integer pageNo)
	Kernels	getAvailableKernels(Integer dropletId, Integer pageNo)
	Snapshots getAvailableSnapshots(Integer dropletId, Integer pageNo)
	Backups	getAvailableBackups(Integer dropletId, Integer pageNo)
	Droplet	getDropletInfo(Integer dropletId)
	Droplet	createDroplet(Droplet droplet)
	Boolean	deleteDroplet(Integer dropletId)
	</pre>
	* Droplet Actions
	<pre>
	Action powerCycleDroplet(Integer dropletId)
	Action powerOffDroplet(Integer dropletId)
	Action powerOnDroplet(Integer dropletId)
	Action rebootDroplet(Integer dropletId)
	Action rebuildDroplet(Integer dropletId, Integer imageId)
	Action renameDroplet(Integer dropletId, String name)
	Action resetDropletPassword(Integer dropletId)
	Action resizeDroplet(Integer dropletId, String size)
	Action restoreDroplet(Integer dropletId, Integer imageId)
	Action shutdownDroplet(Integer dropletId)
	Action takeDropletSnapshot(Integer dropletId)
	Action takeDropletSnapshot(Integer dropletId, String snapshotName)
	Action disableDropletBackups(Integer dropletId)
	Action enableDropletIpv6(Integer dropletId)
	Action enableDropletPrivateNetworking(Integer dropletId)
	Action changeDropletKernel(Integer dropletId, Integer kernelId)
	</pre>
	* Sizes
	<pre>Sizes getAvailableSizes(Integer pageNo)</pre>
	* Regions
	<pre>Regions getAvailableRegions(Integer pageNo)</pre>
	* Domains
	<pre>
	Domains	getAvailableDomains(Integer pageNo)
	Domain getDomainInfo(String domainName)
	Domain createDomain(Domain domain)
	Boolean	deleteDomain(String domainName)
	</pre>
	* Domain Records
	<pre>
	DomainRecords getDomainRecords(String domainName)
	DomainRecord getDomainRecordInfo(String domainName, Integer recordId)
	DomainRecord createDomainRecord(String domainName, DomainRecord domainRecord)
	DomainRecord updateDomainRecord(String domainName, Integer recordId, String name)
	Boolean	deleteDomainRecord(String domainName, Integer recordId)
	</pre>
	* Keys
	<pre>
	Keys getAvailableKeys(Integer pageNo)
	Key	getKeyInfo(Integer sshKeyId)
	Key	getKeyInfo(String fingerprint)
	Key	createKey(Key newKey)
	Key	updateKey(Integer sshKeyId, String newSshKeyName)
	Key	updateKey(String fingerprint, String newSshKeyName)
	Boolean	deleteKey(Integer sshKeyId)
	Boolean	deleteKey(String fingerprint)
	</pre>


Issue Tracker
-------------
Please submit any bugs or annoyances on the [Issues][3]

Author
------
Jeevanandam M. - jeeva@myjeeva.com

License
-------
See [LICENSE.txt][6]


[1]: https://developers.digitalocean.com
[2]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/2.0-SNAPSHOT/
[3]: https://github.com/jeevatkm/digitalocean-api-java/issues
[4]: https://oss.sonatype.org/content/repositories/snapshots/com/myjeeva/digitalocean/digitalocean-api-client/
[5]: http://myjeeva.com
[6]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/LICENSE.txt
[7]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/src/test/java/com/myjeeva/digitalocean/DigitalOceanIntegrationTest.java
[8]: http://search.maven.org/remotecontent?filepath=com/myjeeva/digitalocean/digitalocean-api-client/1.5/digitalocean-api-client-1.5.jar
[9]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/src/test/java/com/myjeeva/digitalocean/DigitalOceanMockTest.java
[10]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/2.0-SNAPSHOT/com/myjeeva/digitalocean/DigitalOcean.html
