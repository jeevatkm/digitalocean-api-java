DigitalOcean API Client in Java
-------------------------------
[![Build Status](https://travis-ci.org/jeevatkm/digitalocean-api-java.svg?branch=master)](https://travis-ci.org/jeevatkm/digitalocean-api-java)

** DigitalOcean OAUTH API support coming soon **

Welcome to DigitalOcean API Client written in Java. Created a simple and meaningful wrapper methods for [DigitalOcean RESTful APIs][1].

> Simple and Lightweight Library for Enterprise Application or Utilities Integration

Give your support by providing Hearts here [DigitalOcean API Client in Java](https://www.digitalocean.com/community/projects/api-client-in-java) :)

**Note:** Since API v2 is in Beta stage; So I'm keeping library in SNAPSHOT version.

Getting Started
---------------
DigitalOcean API Client library per version project dependencies:

* **For v2:** maps to branch [master][11]
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

* * *

* **For v1:** maps to branch [api-v1][12]
	* [Click here][12] to see detailed information

* * *


Getting Help
------------
For API documentation see:

* [DigitalOcean API Client in Java][2]

For Example usage see:

* Have a look at [DigitalOceanIntegrationTest][7]

Samples
-------
* Creating a DigitalOcean Client in three simple ways!
<pre>// Way one, just pass on authToken
DigitalOcean apiClient = new DigitalOceanClient(authToken);</pre>
<pre>// Way two, pass on version number & authToken
DigitalOcean apiClient = new DigitalOceanClient("v2", authToken);</pre>
<pre>// Way three, pass on version number, authToken & httpClient
// Go ahead and customize httpClient attributes for requirements
HttpClient httpclient = new DefaultHttpClient();  
DigitalOcean apiClient = new DigitalOceanClient("v2", authToken, httpClient);
</pre>

* Let's invoke the method(s) as per need via <code>apiClient</code>
<pre>// Fetching all the available droplets from control panel 
Droplets droplets = apiClient.getAvailableDroplets(pageNo);</pre>
<pre>// Fetching all the available kernels for droplet
Kernels kernels = apiClient.getAvailableKernels(dropletId, pageNo);</pre>
<pre>// Create a new droplet
Droplet newDroplet = new Droplet();
newDroplet.setName("api-client-test-host");
newDroplet.setSize(new Size("512mb")); // setting size by slug value
newDroplet.setRegion(new Region("sgp1")); // setting region by slug value; sgp1 => Singapore 1 Data center
newDroplet.setImage(new Image(1601)); // setting by Image Id 1601 => centos-5-8-x64 also available in image slug value
newDroplet.setEnableBackup(Boolean.TRUE);
newDroplet.setEnableIpv6(Boolean.TRUE);
newDroplet.setEnablePrivateNetworking(Boolean.TRUE);
// Adding SSH key info
List&lt;Key> keys = new ArrayList&lt;Key>();
keys.add(new Key(6536653));
keys.add(new Key(6536654));
newDroplet.setKeys(keys);
// Adding Metadata API - User Data
newDroplet.setUserData(" &lt; YAML Content > "); // Follow DigitalOcean documentation to prepare user_data value
Droplet droplet = apiClient.createDroplet(newDroplet);</pre> 
<pre>// Fetch droplet information 
Droplet droplet = apiClient.getDropletInfo(dropletId);</pre> 
<pre>// Fetch Available Plans/Sizes supported by DigitalOcean
Sizes sizes = apiClient.getAvailableSizes(pageNo);</pre> 
<pre>// Fetch Available Regions supported by DigitalOcean
Sizes sizes = apiClient.getAvailableRegions(pageNo);</pre>

* Accessing <code>RateLimit</code> header values in the return object
<pre>Droplets droplets = getAvailableDroplets(1);
RateLimit rateLimit = droplets.getRateLimit();</pre>
<pre>Actions actions = getAvailableActions(2);
RateLimit rateLimit = actions.getRateLimit();</pre>
<pre>Domain domain = getDomainInfo("myjeeva.com");
RateLimit rateLimit = domain.getRateLimit();</pre>
<pre>Droplet droplet = getDropletInfo(10000001);
RateLimit rateLimit = droplet.getRateLimit();</pre>

Reporting Issues
----------------
DigitalOcean API Client uses [GitHub’s integrated issue tracking system][3] to record bugs and feature requests. If you want to raise an issue, please follow the recommendations bellow:

* Before you log a bug, please search the issue tracker to see if someone has already reported the problem. If the issue doesn’t already exist, create a new issue.
* Please provide as much information as possible with the issue report, we like to know the version of DigitalOcean API Client that you are using.
* If you need to paste code, or include a stack trace use Markdown ``` escapes before and after your text.

Supported API's and Revision Logs
---------------------------------
* **Released in v2.0-SNAPSHOT**
	* Account
	<pre>Account getAccountInfo()</pre>
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
	Images getAvailableImages(Integer pageNo)
	Images getAvailableImages(Integer pageNo, ActionType type)
	Image getImageInfo(Integer imageId)
	Image getImageInfo(String slug)
	Image updateImage(Image image)
	Delete deleteImage(Integer imageId)
	Action transferImage(Integer imageId, String regionSlug)
	Images getUserImages(Integer pageNo)
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
	DomainRecords getDomainRecords(String domainName)
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

Author
------
Jeevanandam M. - jeeva@myjeeva.com

License
-------
The DigitalOcean API Client is released under [MIT License][6].


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
[11]: https://github.com/jeevatkm/digitalocean-api-java
[12]: https://github.com/jeevatkm/digitalocean-api-java/tree/api-v1
