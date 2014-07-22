DigitalOcean API Client in Java (for v1 only)
---------------------------------------------
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
    		&lt;version>1.5&lt;/version>
		&lt;/dependency></code></pre>
	* Grails dependency
	<pre><code>compile 'com.myjeeva.digitalocean:digitalocean-api-client:1.5'</code</pre>
	* Groovy Grape
	<pre><code>@Grapes( 
@Grab(group='com.myjeeva.digitalocean', module='digitalocean-api-client', version='1.5') 
)</code></pre>
	* Scala SBT
	<pre><code>libraryDependencies += "com.myjeeva.digitalocean" % "digitalocean-api-client" % "1.5"</code></pre>

* **For using Snapshot Releases available from [OSS - Sonatype Snapshot Repo][4]**
<pre><code>Follow above application/project dependencies guidelines and then
just replace version number to 1.6-SNAPSHOT</code></pre>

* **Need of jar, just click here [digitalocean-api-client-x.x.jar][8] - Maven Centeral Repository**

* **Clone/Download the repo [http://github.com/jeevatkm/digitalocean-api-java] and build it!**


Documentation
-------------
See [JavaDocs - DigitalOcean API Client in Java][2]

Examples
--------
Have a look on [DigitalOceanIntegrationTest][7] and [DigitalOceanMockTest][17], simple and easy to use.

Supported DigitalOcean API's and Functionalities
------------------------------------------------
I have been skipping below three functionality for while, kindly let me know if you need them. Then I will plan out in upcoming release.
* Batch API's for Bulk operation
* Caching DigitalOcean elements and refreshing periodically 
* Input Validation at API client level

* * *

* **Released in v1.5**
	* Added more user-friendly error message for 404 responses by pulse00 [#6][18]
	* Added [DropletStatus][21] and Added few handy methods in Droplet class by pulse00 [#7][19]
		* public boolean isActive()
		* public boolean isNew()
		* public boolean isOff()
		* public boolean isArchived()
	* Enhancements to the library [#9][20]
		* `createdDate` datatype updated to native type `Date`
		* Logging bridge update: slf4j-jdk14 ==> slf4j-api

* * *
	
* **Released in v1.4** [2 enhancement & 1 bug]
	* Added new POJOs [Backup][10], [Snapshot][11]
	* Added/Updated attributes in [Droplet][12] Class
		* `backupsActive` datatype changed to native type
		* Added following attributes
			* IP Address for [#1][14]
			* Private IP Address for [#5][15]
			* Locked for [#5][15]
			* Created At for [#5][15]
			* [Backup][10] for [#5][15]
			* and [Snapshot][11] attributes for [#5][15]
	* Accepted pull request for [#3][16]
	
* * *
	
* **Released in v1.3**
	* Added new POJO [Event][9]
	* Added two new attributes in [Response][13] Class
		* `error_message`
		* `event`
	* `Events Methods '/events/*'`
		* Response getEventProgress(Integer eventId)
	
* * *

* **Released in v1.2**
	* Gson Type Tokens optimized
	* Corrected Typo error of <code>transerImage</code> method refactored to <code>transferImage</code>
    * `SSH Key Methods '/ssh_keys/*'`
        * List&lt;SshKey> getAvailableSshKeys()
        * SshKey getSshKeyInfo(Integer sshKeyId)
        * SshKey addSshKey(String sshKeyName, String sshPublicKey)
        * SshKey editSshKey(Integer sshKeyId, String newSshPublicKey)
        * Response deleteSshKey(Integer sshKeyId);

* * *

* **Released in v1.1**
	* Optimized and smaller memory footprint
	* Maven Group Id to be changed to <code>com.myjeeva.digitalocean</code>, for better understanding and grouping
    * `Domain Methods '/domains/*'` 
        * List&lt;Domain> getAvailableDomains()
        * Domain createDomain(String domainName, String ipAddress)
        * Domain getDomainInfo(Integer domainId)
        * Response deleteDomain(Integer domainId)
        * List&lt;DomainRecord> getDomainRecords(Integer domainId)
        * DomainRecord createDomainRecord(DomainRecord domainRecord)
        * DomainRecord getDomainRecordInfo(Integer domainId, Integer recordId)
        * DomainRecord editDomainRecord(DomainRecord domainRecord)
        * Response deleteDomainRecord(Integer domainId, Integer recordId)

* * *

* **Released in v1.0**
	* <code>Slug</code> Attribute supported in Image, Size and Region
    * `Droplets Methods '/droplets/*'`
        * List&lt;Droplet> getAvailableDroplets()
        * Droplet createDroplet(Droplet droplet)
        * Droplet createDroplet(Droplet droplet, String sshKeyIds)
        * Droplet getDropletInfo(Integer dropletId)
        * Response rebootDroplet(Integer dropletId)
        * Response powerCyleDroplet(Integer dropletId)
        * Response shutdownDroplet(Integer dropletId)
        * Response powerOffDroplet(Integer dropletId)
        * Response powerOnDroplet(Integer dropletId)
        * Response resetDropletPassword(Integer dropletId)
        * Response resizeDroplet(Integer dropletId, Integer sizeId)
        * Response takeDropletSnapshot(Integer dropletId)
        * Response takeDropletSnapshot(Integer dropletId, String snapshotName)
        * Response restoreDroplet(Integer dropletId, Integer imageId)
        * Response rebuildDroplet(Integer dropletId, Integer imageId)
        * Response enableDropletBackups(Integer dropletId)
        * Response disableDropletBackups(Integer dropletId)
        * Response renameDroplet(Integer dropletId, String name)
        * Response deleteDroplet(Integer dropletId)
    * `Region Methods '/regions/*'`
        * List&lt;Region> getAvailableRegions()
    * `Images Methods '/images/*'`
        * List&lt;DropletImage> getAvailableImages()
        * DropletImage getImageInfo(Integer imageId)
        * Response deleteImage(Integer imageId)
        * Response transerImage(Integer imageId, Integer regionId)
    * `Sizes Methods '/sizes/*'` 
        * List&lt;DropletSize> getAvailableSizes()	


Issue Tracker
-------------
Please submit any bugs or annoyances on the [Issues][3]

Author
------
Jeevanandam M. - jeeva@myjeeva.com ([myjeeva.com][5])

License
-------
See [LICENSE.txt][6]


[1]: https://api.digitalocean.com/
[2]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.5/
[3]: https://github.com/jeevatkm/digitalocean-api-java/issues
[4]: https://oss.sonatype.org/content/repositories/snapshots/com/myjeeva/digitalocean/digitalocean-api-client/
[5]: http://myjeeva.com
[6]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/LICENSE.txt
[7]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/src/test/java/com/myjeeva/digitalocean/DigitalOceanIntegrationTest.java
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
