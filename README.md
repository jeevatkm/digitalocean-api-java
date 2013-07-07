DigitalOcean API Client written in Java
---------------------------------------

Welcome to DigitalOcean's API Client written in Java. I have created a simple and meaningful wrapper methods for DigitalOcean's API. All of the RESTful that you find in [DigitalOcean API's][1] will be made available via simple java methods.


How to make use of client?
------------------------------------
* Maven Dependency
<pre><code>&lt;dependency>
    &lt;groupId>com.myjeeva.digitalocean&lt;/groupId>
    &lt;artifactId>api-client&lt;/artifactId>
    &lt;version>1.0&lt;/version>
&lt;/dependency></code></pre>

* Clone the repo [http://github.com/jeevatkm/digitalocean-api-java] and build it!


Documentation
-------------

See [JavaDocs][2]

Supported DigitalOcean API's
----------------------------

* **To be released in v1.2**
    * `SSH Key Methods`
        * List<SshKey> getAvailableSshKeys()
        * SshKey getSshKeyInfo(Integer sshKeyId)
        * SshKey addSshKey(String sshKeyName, String sshPublicKey)
        * SshKey editSshKey(Integer sshKeyId, String sshPublicKey)
        * Response deleteSshKey(Integer sshKeyId);

* * *

* **To be released in v1.1**
    * `Domain Methods` 
        * List<Domain> getAvailableDomains()
        * Domain getDomainInfo(Integer domainId)
        * Domain createDomain(String domainName, String ipAddress)
        * Domain deleteDomain(Integer domainId)
        * List<DomainRecord> getDomainRecords(Integer domainId)
        * DomainRecord getDomainRecord(Integer domainId, Integer recordId)
        * DomainRecord createDomainRecord(DomainRecord domainRecord)
        * DomainRecord editDomainRecord(DomainRecord domainRecord)
        * Response deleteDomainRecord(Integer domainId, Integer recordId) 

* * *

* **Released in v1.0**
    * `Droplets Methods`
        * List<Droplet> getAvailableDroplets()
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
        * List<Region> getAvailableRegions()
    * `Images Methods`
        * List<DropletImage> getAvailableImages()
        * DropletImage getImageInfo(Integer imageId)
        * Response deleteImage(Integer imageId)
        * Response transerImage(Integer imageId, Integer regionId)
        * List<DropletSize> getAvailableSizes()	


Issue Tracker
-------------

Please submit any bugs or annoyances on the [Issues][4]

License
-------

See *LICENSE.txt*


[1]: https://api.digitalocean.com/
[2]: 
[3]: 
[4]: https://github.com/jeevatkm/digitalocean-api-java/issues
