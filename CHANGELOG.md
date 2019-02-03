# Supported API's and Changelogs

* **Released in v2.17**
  * Added methods add/remove firewall for droplets [#91](https://github.com/jeevatkm/digitalocean-api-java/pull/91) @mashurex
  * Adapt new changes or enhancements of DO v2 APIs [#89](https://github.com/jeevatkm/digitalocean-api-java/pull/89) @jeevatkm
      * Added [Let's Encrypt](https://developers.digitalocean.com/documentation/changelog/api-v2/lets-encrypt-support-for-certificate-resources/) create certificate method and options
	  * Added `filesystem_type`, `filesystem_label`, `tags` attributes support to volume - [1](https://developers.digitalocean.com/documentation/changelog/api-v2/auto-formatting-support-for-volumes/), [2](https://developers.digitalocean.com/documentation/changelog/api-v2/add-volume-tagging/)
	  * Removed the [IP address validation](https://developers.digitalocean.com/documentation/changelog/api-v2/create-domains-without-providing-an-ip-address/)
	  * Added `last_tagged_uri` attribute and deprecated `last_tagged` attribute - [Tags](https://developers.digitalocean.com/documentation/changelog/api-v2/adding-tagged-uri/)
	  * Added new [create custom image(https://developers.digitalocean.com/documentation/changelog/api-v2/support-for-custom-images-and-image-tagging/)] API support
  * Bug fix: Add expose annotation to sources [#90](https://github.com/jeevatkm/digitalocean-api-java/pull/90) @mcjp78 
  * Bug fix: Updated the `size` attribute in the Volume to `integer` [#92](https://github.com/jeevatkm/digitalocean-api-java/pull/92) @bahrinka  

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
	* Applied latest API changes from DO, [Account object](https://developers.digitalocean.com/documentation/changelog/api-v2/add-status-to-account/) & [Snapshot status](https://developers.digitalocean.com/documentation/changelog/api-v2/deprecate-final-snaphots/)
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

