DigitalOcean API Client written in Java
---------------------------------------

Welcome to DigitalOcean's API Client written in Java. I have created a simple and meaningful wrapper methods for DigitalOcean's API. All of the RESTful that you find in [DigitalOcean API's][1] will be made available via simple java method(s).

> Simple and Lightweight Library for Enterprise Application/Utilities Integration

How to make use of digitalocean-api-client?
------------------------------------
* Maven Dependency
<pre><code>&lt;dependency>
    &lt;groupId>com.myjeeva.digitalocean&lt;/groupId>
    &lt;artifactId>digitalocean-api-client&lt;/artifactId>
    &lt;version>1.2&lt;/version>
&lt;/dependency></code></pre>

* Snapshot Maven Dependency ([OSS Sonatype Snapshot Repo][4])
<pre><code>&lt;dependency>
    &lt;groupId>com.myjeeva.digitalocean&lt;/groupId>
    &lt;artifactId>digitalocean-api-client&lt;/artifactId>
    &lt;version>1.3-SNAPSHOT&lt;/version>
&lt;/dependency></code></pre>


* Download Binary [digitalocean-api-client-1.2.jar][8]

* Clone/Download the repo [http://github.com/jeevatkm/digitalocean-api-java] and build it!


Documentation
-------------
See [JavaDocs - DigitalOcean API Client written in Java][2]

Examples
--------
Have a look on [DigitalOceanTest.java][7], simple and easy to understand.

Supported DigitalOcean API's and Functionalities
------------------------------------------------
* **To be released in v2.0**
	* Data Validation API against DigitalOcean
	* Meaningful messages and reduce request failure
	
* * *

* **To be released in v1.3**
	* Batch API's for Bulk operation
	* Input Validation at API client level
	
* * *

* **Released in v1.2**
	* Gson Type Tokens optimized
	* Corrected Typo error of <code>transerImage</code> method refactored to <code>transferImage</code>
    * `SSH Key Methods`
        * List&lt;SshKey> getAvailableSshKeys()
        * SshKey getSshKeyInfo(Integer sshKeyId)
        * SshKey addSshKey(String sshKeyName, String sshPublicKey)
        * SshKey editSshKey(Integer sshKeyId, String newSshPublicKey)
        * Response deleteSshKey(Integer sshKeyId);

* * *

* **Released in v1.1**
	* Optimized and smaller memory footprint
	* Maven Group Id to be changed to <code>com.myjeeva.digitalocean</code>, for better understanding and grouping
    * `Domain Methods` 
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
    * `Droplets Methods`
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
    * `Region Methods`
        * List&lt;Region> getAvailableRegions()
    * `Images Methods`
        * List&lt;DropletImage> getAvailableImages()
        * DropletImage getImageInfo(Integer imageId)
        * Response deleteImage(Integer imageId)
        * Response transerImage(Integer imageId, Integer regionId)
    * `Sizes Methods` 
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
[2]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/1.1/
[3]: https://github.com/jeevatkm/digitalocean-api-java/issues
[4]: https://oss.sonatype.org/content/repositories/snapshots/
[5]: http://myjeeva.com
[6]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/LICENSE.txt
[7]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/src/test/java/com/myjeeva/digitalocean/DigitalOceanTest.java
[8]: https://www.box.com/s/q4s3r4galsgqug21tnfv