<p align="center">
  <h1 align="center">DigitalOcean API Client for Java</h1>
  <p align="center"><a href="https://travis-ci.org/jeevatkm/digitalocean-api-java"><img src="https://img.shields.io/travis/jeevatkm/digitalocean-api-java/master.svg?style=flat-square" alt="Build Status" /></a> <a href="https://github.com/jeevatkm/digitalocean-api-java/releases/latest"><img src="https://img.shields.io/badge/version-2.17-blue.svg?style=flat-square" alt="Version" /></a> <a href="https://docs.myjeeva.com/javadoc/digitalocean-api-client/2.17/" target="_blank"><img src="https://img.shields.io/badge/javadoc-reference-00bcd4.svg?style=flat-square" alt="Javadoc" /></a> <a href="LICENSE"><img src="https://img.shields.io/github/license/jeevatkm/digitalocean-api-java.svg?style=flat-square" alt="License" /></a> </p>
  <p align="center">Simple & Lightweight API client library for Enterprise Application or Utilities Integration around <a href="https://developers.digitalocean.com" target="_blank">DigitalOcean RESTful APIs</a>. You can use this library with project based (JVM hosted languages) on Java, Groovy, Scala, Clojure, etc.</p>
</p>
<p align="center">
Give your support by clicking Hearts on <a href="https://www.digitalocean.com/community/projects/api-client-in-java" target="_blank">DigitalOcean Developers Community</a>.
</p>

## News

  * [v2.17](https://github.com/jeevatkm/digitalocean-api-java/releases/tag/v2.17) released and tagged on Feb 03, 2019
  * [v2.16](https://github.com/jeevatkm/digitalocean-api-java/releases/tag/v2.16) released and tagged on Sep 03, 2018
  * [v2.15](https://github.com/jeevatkm/digitalocean-api-java/releases/tag/v2.15) released and tagged on May 5, 2018
  * [v2.14](https://github.com/jeevatkm/digitalocean-api-java/releases/tag/v2.14) released and tagged on Mar 6, 2018
  * [v2.13](https://github.com/jeevatkm/digitalocean-api-java/releases/tag/v2.13) eleased and tagged on Nov 18, 2017

## Getting Started

For handy use, DigitalOcean API Client library project dependency definition provided below or you wanna jar [Download it](http://search.maven.org/remotecontent?filepath=com/myjeeva/digitalocean/digitalocean-api-client/2.17/digitalocean-api-client-2.17.jar) from Maven central repo.

*Note: [master](https://github.com/jeevatkm/digitalocean-api-java) branch maps to v2 APIs and digitalocean turned off [v1 APIs](https://developers.digitalocean.com/documentation/changelog/api-v1/sunsetting-api-v1/) as on Nov 9, 2015 .*

**Maven dependency**
```xml
<dependency>
    <groupId>com.myjeeva.digitalocean</groupId>
    <artifactId>digitalocean-api-client</artifactId>
    <version>2.17</version>
</dependency>
```
**Gradle/Grails dependency**
```shell
compile 'com.myjeeva.digitalocean:digitalocean-api-client:2.17'
```
**Groovy Grape**
```groovy
@Grapes(
@Grab(group='com.myjeeva.digitalocean', module='digitalocean-api-client', version='2.17')
)
```
**Scala SBT**
```shell
libraryDependencies += "com.myjeeva.digitalocean" % "digitalocean-api-client" % "2.17"
```

**Note:** 

  * For Android projects, kindly include the `httpclient-android` library explicitly in your project dependencies.
  * Library `vx.x-SNAPSHOT` is [available](https://oss.sonatype.org/content/repositories/snapshots/com/myjeeva/digitalocean/digitalocean-api-client/) between the release version. Snapshot is update to with `master` branch.

* * *

## Getting Help

For API documentation see:

* [DigitalOcean API Client in Java](https://docs.myjeeva.com/javadoc/digitalocean-api-client/2.17/)

For Example usage see:

* Have a look at [DigitalOceanIntegrationTest](https://github.com/jeevatkm/digitalocean-api-java/blob/master/src/test/java/com/myjeeva/digitalocean/DigitalOceanIntegrationTest.java)

## Samples

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

## Reporting Issues

DigitalOcean API Client uses [GitHub’s integrated issue tracking system](https://github.com/jeevatkm/digitalocean-api-java/issues) to record bugs and feature requests. If you want to raise an issue, please follow the recommendations bellow:

* Before you log a bug, please search the issue tracker to see if someone has already reported the problem. If the issue doesn’t already exist, create a new issue.
* Please provide as much information as possible with the issue report, we like to know the version of DigitalOcean API Client that you are using.
* If you need to paste code, or include a stack trace use Markdown ``` escapes before and after your text.

## Supported API's and Changelogs

Refer to [CHANGELOG.md](CHANGELOG.md)

## Author

Jeevanandam M. - jeeva@myjeeva.com

## Contributors

Please refer to https://github.com/jeevatkm/digitalocean-api-java/graphs/contributors

## Contributing

1. Fork it
2. Create your feature branch - `git checkout -b my-new-feature`
3. Implement your changes 
4. Format your code with `./mvnw com.coveo:fmt-maven-plugin:format`
5. Check tests passig with `./mvnw verify`
6. Commit your changes - `git commit -am 'Added feature'`
7. Push to the branch - `git push origin my-new-feature`
9. Create new Pull Request

## License

DigitalOcean API Client - [MIT License](LICENSE).
