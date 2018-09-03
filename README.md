# DigitalOcean API Client  [![Build Status](https://travis-ci.org/jeevatkm/digitalocean-api-java.svg?branch=master)](https://travis-ci.org/jeevatkm/digitalocean-api-java) [![Version](https://img.shields.io/badge/version-2.16-blue.svg)](https://github.com/jeevatkm/digitalocean-api-java/releases/latest) [![License](https://img.shields.io/github/license/jeevatkm/digitalocean-api-java.svg)](LICENSE)

***v2.16 [released](https://github.com/jeevatkm/digitalocean-api-java/releases/latest) and tagged on Sep 03, 2018***

Simple & Lightweight API client library for Enterprise Application or Utilities Integration around [DigitalOcean RESTful APIs][1]. You can use this library with project based (JVM hosted languages) on Java, Groovy, Scala, Clojure, etc.

Give your support by clicking Hearts on [DigitalOcean Developers Community](https://www.digitalocean.com/community/projects/api-client-in-java) :)

# Getting Started

For handy use, DigitalOcean API Client library project dependency definition provided below or you wanna jar [Download it][16] from Maven central repo.

*Note: [master][11] branch maps to v2 APIs and digitalocean turned off [v1 APIs](https://developers.digitalocean.com/documentation/changelog/api-v1/sunsetting-api-v1/) as on Nov 9, 2015 .*

**Maven dependency**
```xml
<dependency>
    <groupId>com.myjeeva.digitalocean</groupId>
    <artifactId>digitalocean-api-client</artifactId>
    <version>2.16</version>
</dependency>
```
**Gradle/Grails dependency**
```shell
compile 'com.myjeeva.digitalocean:digitalocean-api-client:2.16'
```
**Groovy Grape**
```groovy
@Grapes(
@Grab(group='com.myjeeva.digitalocean', module='digitalocean-api-client', version='2.16')
)
```
**Scala SBT**
```shell
libraryDependencies += "com.myjeeva.digitalocean" % "digitalocean-api-client" % "2.16"
```

**Note:** For Android projects, kindly include the `httpclient-android` library explicitly in your project dependencies.

* * *

# Getting Help

For API documentation see:

* [DigitalOcean API Client in Java][2]

For Example usage see:

* Have a look at [DigitalOceanIntegrationTest][7]

# Samples

**Creating a DigitalOcean Client in three simple ways!**
```java
// Way one, just pass on authToken
DigitalOcean apiClient = new DigitalOceanClient(authToken);

// Way two, pass on version number & authToken
DigitalOcean apiClient = new DigitalOceanClient("v2", authToken);

// Way three, pass on version number, authToken & httpClient
// Go ahead and customize httpClient attributes for requirements
CloseableHttpClient httpClient = HttpClients.createDefault();
DigitalOcean apiClient = new DigitalOceanClient("v2", authToken, httpClient);
```

**Let's invoke the method(s) as per need via apiClient**
```java
// Fetching all the available droplets from control panel
Droplets droplets = apiClient.getAvailableDroplets(pageNo, perPage);

// Fetching all the available kernels for droplet
Kernels kernels = apiClient.getAvailableKernels(dropletId, pageNo, perPage);

// Create a new droplet
Droplet newDroplet = new Droplet();
newDroplet.setName("api-client-test-host");
newDroplet.setSize(new Size("512mb")); // setting size by slug value
newDroplet.setRegion(new Region("sgp1")); // setting region by slug value; sgp1 => Singapore 1 Data center
newDroplet.setImage(new Image(1601)); // setting by Image Id 1601 => centos-5-8-x64 also available in image slug value
newDroplet.setEnableBackup(Boolean.TRUE);
newDroplet.setEnableIpv6(Boolean.TRUE);
newDroplet.setEnablePrivateNetworking(Boolean.TRUE);

// Adding SSH key info
List<Key> keys = new ArrayList<Key>();
keys.add(new Key(6536653));
keys.add(new Key(6536654));
newDroplet.setKeys(keys);

// Adding Metadata API - User Data
newDroplet.setUserData(" < YAML Content > "); // Follow DigitalOcean documentation to prepare user_data value
Droplet droplet = apiClient.createDroplet(newDroplet);


// Creating multiple droplets
Droplet droplet = new Droplet();
droplet.setNames(Arrays.asList("sub-01.example.com", "sub-02.example.com"));
droplet.setSize("512mb");
droplet.setImage(new Image("ubuntu-14-04-x64"));
droplet.setRegion(new Region("nyc1"));
Droplets droplets = apiClient.createDroplets(droplet);

// Fetch droplet information
Droplet droplet = apiClient.getDropletInfo(dropletId);

// Fetch Available Plans/Sizes supported by DigitalOcean
Sizes sizes = apiClient.getAvailableSizes(pageNo);

// Fetch Available Regions supported by DigitalOcean
Regions regions = apiClient.getAvailableRegions(pageNo);
```

**Accessing `RateLimit` header values from return object. This is applicable for all requests**.
```java
Droplets droplets = getAvailableDroplets(1, 20);
RateLimit rateLimit = droplets.getRateLimit();

Actions actions = getAvailableActions(2, 40);
RateLimit rateLimit = actions.getRateLimit();

Domain domain = getDomainInfo("myjeeva.com");
RateLimit rateLimit = domain.getRateLimit();

Droplet droplet = getDropletInfo(10000001);
RateLimit rateLimit = droplet.getRateLimit();
```

# Reporting Issues

DigitalOcean API Client uses [GitHub’s integrated issue tracking system][3] to record bugs and feature requests. If you want to raise an issue, please follow the recommendations bellow:

* Before you log a bug, please search the issue tracker to see if someone has already reported the problem. If the issue doesn’t already exist, create a new issue.
* Please provide as much information as possible with the issue report, we like to know the version of DigitalOcean API Client that you are using.
* If you need to paste code, or include a stack trace use Markdown ``` escapes before and after your text.

# Supported API's and Revision Logs

* **Released in v2.16**
  * Added new endpoint support `/v2/firewalls/*` [#83](https://github.com/jeevatkm/digitalocean-api-java/issues/83), PR [#85](https://github.com/jeevatkm/digitalocean-api-java/pull/85), PR [#87](https://github.com/jeevatkm/digitalocean-api-java/pull/87) (@andreybleme)
      * Firewalls
        <pre>
		Firewalls getAvailableFirewalls(Integer pageNo, Integer perPage)
		Firewall getFirewallInfo(String firewallId)
        Firewall createFirewall(Firewall firewall)        
        Firewall updateFirewall(Firewall firewall)
        Delete deleteFirewall(String firewallId)
        </pre>
  * Links and Meta information made available on POJO [#84](https://github.com/jeevatkm/digitalocean-api-java/issues/84) @jeevatkm
  * Added method `getAvailableDropletsByTagName` [#86](https://github.com/jeevatkm/digitalocean-api-java/issues/86) #jeevatkm
* **Released in v2.15**
  * Added CAA support in `DomainRecord` [#81](https://github.com/jeevatkm/digitalocean-api-java/issues/81) @jeevatkm
  * Bug fix: Pagination fix for `getAvailableRegions`, `getAvailableSizes`, `getAvailableDomains`, and `getAvailableKeys` [#80](https://github.com/jeevatkm/digitalocean-api-java/issues/80) @jeevatkm
* **Released in v2.14**
  * Added TTL field to `DomainRecord` PR [#78](https://github.com/jeevatkm/digitalocean-api-java/pull/78) @rpardini
  * Added disk resize option when resizing a droplet PR [#76](https://github.com/jeevatkm/digitalocean-api-java/pull/76) @majidalfifi
  * Bug fix: Priority in DomainRecord should be an Integer PR [#79](https://github.com/jeevatkm/digitalocean-api-java/pull/79) @rpardini
* **Released in v2.13**
  * Added support for creating volumes from a snapshot ID PR [#75](https://github.com/jeevatkm/digitalocean-api-java/issues/75) @benesch
  * DigitalOcean Rate-Limit headers handled gracefully PR [#74](https://github.com/jeevatkm/digitalocean-api-java/issues/74) @benesch
* **Released in v2.12**
  * Added monitoring attribute to Create droplet [#70](https://github.com/jeevatkm/digitalocean-api-java/issues/70) @jeevatkm
  * Bug fix: Not able to get Image (Snapshot) Size [#68](https://github.com/jeevatkm/digitalocean-api-java/issues/68) @jeevatkm
  * Added new endpoint support `/v2/certificates/*` [#71](https://github.com/jeevatkm/digitalocean-api-java/issues/71) @jeevatkm
      * Certificates
        <pre>
        Certificates getAvailableCertificates(Integer pageNo, Integer perPage)
        Certificate createCertificate(Certificate certificate)
        Certificate getCertificateInfo(String certificateId)
        Delete deleteCertificate(String certificateId)
        </pre>
* **Released in v2.11**
  * Added new endpoint support `/v2/load_balancers/*` PR [#66](https://github.com/jeevatkm/digitalocean-api-java/issues/66) @tlehoux
      * Load Balancers
        <pre>
        LoadBalancers getAvailableLoadBalancers(Integer pageNo, Integer perPage)
        LoadBalancer createLoadBalancer(LoadBalancer loadBalancer)
        LoadBalancer getLoadBalancerInfo(String loadBalancerId)        
        LoadBalancer updateLoadBalancer(LoadBalancer loadBalancer)
        Delete deleteLoadBalancer(String loadBalancerId)
        Response addDropletsToLoadBalancer(String loadBalancerId, List<Integer> dropletIds)
        Delete removeDropletsFromLoadBalancer(String loadBalancerId, List<Integer> dropletIds)
        Response addForwardingRulesToLoadBalancer(String loadBalancerId, List<ForwardingRules> forwardingRules)
        Delete removeForwardingRulesFromLoadBalancer(String loadBalancerId, List<ForwardingRules> forwardingRules)
        </pre>
* **Released in v2.10**
  * Removed unsupported API `/v2/tags/$TAG_NAME` refer [DO changelog](https://developers.digitalocean.com/documentation/changelog/api-v2/deprecating-update-tag/)
* **Released in v2.9**
  * Added new API support delete droplet by tag name [#61](https://github.com/jeevatkm/digitalocean-api-java/issues/61)
    * Droplet
      <pre>
      Delete deleteDropletByTagName(String tagName)
      </pre>
* **Released in v2.8**
  * Added new endpoint support `/v2/snapshots/*` [#58](https://github.com/jeevatkm/digitalocean-api-java/issues/58), PR [#57](https://github.com/jeevatkm/digitalocean-api-java/pull/57) (@samuelfac)
      * Snapshots
        <pre>
        Snapshots getAvailableSnapshots(Integer pageNo, Integer perPage)
        Snapshots getAllDropletSnapshots(Integer pageNo, Integer perPage)
        Snapshots getAllVolumeSnapshots(Integer pageNo, Integer perPage)
        Snapshot getSnaphotInfo(String snapshotId)
        Delete deleteSnapshot(String snapshotId)
        </pre>
      * Due to new endpoint `/v2/snapshots/*` introduced by DO, I had to refactor following methods to make it clean and meaningful
        <pre>
        getAvailableSnapshots(Integer dropletId, Integer pageNo, Integer perPage) ==> getDropletSnapshots(Integer dropletId, Integer pageNo, Integer perPage)
        getAvailableKernels(Integer dropletId, Integer pageNo, Integer perPage) ==> getDropletKernels(Integer dropletId, Integer pageNo, Integer perPage)
        getAvailableBackups(Integer dropletId, Integer pageNo) ==> getDropletBackups(Integer dropletId, Integer pageNo, Integer perPage)
        </pre>
  * Added `volumes` and `tags` attribute for create droplet [#56](https://github.com/jeevatkm/digitalocean-api-java/issues/56)
* **Released in v2.7**
  * Added new endpoint support `/v2/volumes/*` [#54](https://github.com/jeevatkm/digitalocean-api-java/issues/54), PR [#55](https://github.com/jeevatkm/digitalocean-api-java/pull/55) (@strokine)
      * Volumes
        <pre>
        Volumes getAvailableVolumes(String regionSlug)
        Volume createVolume(Volume volume)
        Volume getVolumeInfo(String volumeId)
        Volumes getVolumeInfo(String volumeName, String regionSlug)
        Delete deleteVolume(String volumeId)
        Delete deleteVolume(String volumeName, String regionSlug)
        </pre>
      * Volumes Actions
        <pre>
        Action attachVolume(Integer dropletId, String volumeId, String regionSlug)
        Action attachVolumeByName(Integer dropletId, String volumeName, String regionSlug)
        Action detachVolume(Integer dropletId, String volumeId, String regionSlug)
        Action detachVolumeByName(Integer dropletId, String volumeName, String regionSlug)
        Action resizeVolume(String volumeId, String regionSlug, Double sizeGigabytes)
        Actions getAvailableVolumeActions(String volumeId)
        Action getVolumeAction(String volumeId, Integer actionId)        
        </pre>
  * Gson library updated to v2.7
* **Released in v2.6**
  * Fix for delete droplet error [#52](https://github.com/jeevatkm/digitalocean-api-java/issues/52)
* **Released in v2.5**
  * Added new endpoint support `/v2/tags/*` [#48](https://github.com/jeevatkm/digitalocean-api-java/issues/48)
      * Tags
        <pre>
        Tags getAvailableTags(Integer pageNo, Integer perPage)
        Tag getTag(String name)
        Tag updateTag(String currentName, String newName)
        Delete deleteTag(String name)
        Response tagResources(String name, List<Resource> resources)
        Response untagResources(String name, List<Resource> resources)
        </pre>
  * Added new Attribute to the Image [#49](https://github.com/jeevatkm/digitalocean-api-java/issues/49)
  * Fix for incorrect time zone returned from getActionInfo [#47](https://github.com/jeevatkm/digitalocean-api-java/issues/47)
* **Released in v2.4**
  * `getDomainRecords` supports pagination params via PR [#46](https://github.com/jeevatkm/digitalocean-api-java/issues/46)
* **Released in v2.3**
	* `Action enableDropletBackups(Integer dropletId)`
	* `Droplets createDroplets(Droplet droplet)`
	* Added `floating_ip_limit` attribute into `Account` class
	* `updateDomainRecord()` method accepts `DomainRecord` as an input parameter
	* `per_page` parameter added to all the supported APIs
	* Floating IPs
	<pre>
	FloatingIPs getAvailableFloatingIPs(Integer pageNo, Integer perPage)
	FloatingIP createFloatingIP(Integer dropletId)
	FloatingIP createFloatingIP(String region)
	FloatingIP getFloatingIPInfo(String ipAddress)
	Delete deleteFloatingIP(String ipAddress)
	</pre>
	* Floating IPs Actions
	<pre>
	Action assignFloatingIP(Integer dropletId, String ipAddress)
	Action unassignFloatingIP(String ipAddress)
	Actions getAvailableFloatingIPActions(String ipAddress, Integer pageNo, Integer perPage)
	Action getFloatingIPActionInfo(String ipAddress, Integer actionId)
	</pre>
* **Released in v2.2**
	* Added compatibility for Android
	* Applied latest API changes from DO, [Account object][14] & [Snapshot status][15]
* **Released in v2.1**
	* Enhancements
		* Libs version upgraded to latest
		* Less objects generation
* **Released in v2.0**
	* Account
	<pre>Account getAccountInfo()</pre>
	* Actions
	<pre>
	Actions getAvailableActions(Integer pageNo, Integer perPage)
	Actions getAvailableDropletActions(Integer dropletId, Integer pageNo, Integer perPage)
	Actions getAvailableImageActions(Integer imageId, Integer pageNo, Integer perPage)
	Action getActionInfo(Integer actionId)</pre>
	* Droplets
	<pre>
	Droplets getAvailableDroplets(Integer pageNo, Integer perPage)
	Kernels getAvailableKernels(Integer dropletId, Integer pageNo, Integer perPage)
	Snapshots getAvailableSnapshots(Integer dropletId, Integer pageNo, Integer perPage)
	Backups	getAvailableBackups(Integer dropletId, Integer pageNo)
	Droplet	getDropletInfo(Integer dropletId)
	Droplet	createDroplet(Droplet droplet)
	Delete deleteDroplet(Integer dropletId)
	Droplets getDropletNeighbors(Integer dropletId, Integer pageNo)
	Neighbors getAllDropletNeighbors(Integer pageNo)
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
	Action resizeDroplet(Integer dropletId, String size, Boolean disk)
	Action restoreDroplet(Integer dropletId, Integer imageId)
	Action shutdownDroplet(Integer dropletId)
	Action takeDropletSnapshot(Integer dropletId)
	Action takeDropletSnapshot(Integer dropletId, String snapshotName)
	Action disableDropletBackups(Integer dropletId)
	Action enableDropletIpv6(Integer dropletId)
	Action enableDropletPrivateNetworking(Integer dropletId)
	Action changeDropletKernel(Integer dropletId, Integer kernelId)
	</pre>
	* Images
	<pre>
	Images getAvailableImages(Integer pageNo, Integer perPage)
	Images getAvailableImages(Integer pageNo, Integer perPage, ActionType type)
	Image getImageInfo(Integer imageId)
	Image getImageInfo(String slug)
	Image updateImage(Image image)
	Delete deleteImage(Integer imageId)
	Action transferImage(Integer imageId, String regionSlug)
	Action convertImage(Integer imageId)
	Images getUserImages(Integer pageNo, Integer perPage)
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
	Delete deleteDomain(String domainName)
	</pre>
	* Domain Records
	<pre>
	DomainRecords getDomainRecords(String domainName, Integer pageNo, Integer perPage)
	DomainRecord getDomainRecordInfo(String domainName, Integer recordId)
	DomainRecord createDomainRecord(String domainName, DomainRecord domainRecord)
	DomainRecord updateDomainRecord(String domainName, Integer recordId, String name)
	Delete deleteDomainRecord(String domainName, Integer recordId)
	</pre>
	* Keys
	<pre>
	Keys getAvailableKeys(Integer pageNo)
	Key	getKeyInfo(Integer sshKeyId)
	Key	getKeyInfo(String fingerprint)
	Key	createKey(Key newKey)
	Key	updateKey(Integer sshKeyId, String newSshKeyName)
	Key	updateKey(String fingerprint, String newSshKeyName)
	Delete deleteKey(Integer sshKeyId)
	Delete deleteKey(String fingerprint)
	</pre>


# Author

Jeevanandam M. - jeeva@myjeeva.com

# Contributing

1. Fork it
2. Create your feature branch - `git checkout -b my-new-feature`
3. Implement your changes and apply [Google Java Code Formatter][13]
4. Commit your changes - `git commit -am 'Added feature'`
5. Push to the branch - `git push origin my-new-feature`
6. Create new Pull Request

# License

DigitalOcean API Client - [MIT License][6].


[1]: https://developers.digitalocean.com
[2]: https://docs.myjeeva.com/javadoc/digitalocean-api-client/2.16/
[3]: https://github.com/jeevatkm/digitalocean-api-java/issues
[4]: https://oss.sonatype.org/content/repositories/snapshots/com/myjeeva/digitalocean/digitalocean-api-client/
[5]: https://myjeeva.com
[6]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/LICENSE
[7]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/src/test/java/com/myjeeva/digitalocean/DigitalOceanIntegrationTest.java
[8]: http://search.maven.org/remotecontent?filepath=com/myjeeva/digitalocean/digitalocean-api-client/1.5/digitalocean-api-client-1.5.jar
[9]: https://github.com/jeevatkm/digitalocean-api-java/blob/master/src/test/java/com/myjeeva/digitalocean/DigitalOceanMockTest.java
[10]: http://docs.myjeeva.com/javadoc/digitalocean-api-client/2.4-SNAPSHOT/com/myjeeva/digitalocean/DigitalOcean.html
[11]: https://github.com/jeevatkm/digitalocean-api-java
[12]: https://github.com/jeevatkm/digitalocean-api-java/tree/api-v1
[13]: https://raw.githubusercontent.com/darcyliu/google-styleguide/master/eclipse-java-google-style.xml
[14]: https://developers.digitalocean.com/documentation/changelog/api-v2/add-status-to-account/
[15]: https://developers.digitalocean.com/documentation/changelog/api-v2/deprecate-final-snaphots/
[16]: http://search.maven.org/remotecontent?filepath=com/myjeeva/digitalocean/digitalocean-api-client/2.16/digitalocean-api-client-2.16.jar
