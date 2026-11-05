# Azure
------
- [Folders](#folders)
- [Files](#files)
- [References](#references)
---
# Folders
* [Fabric](fabric/README.md)
* [img](img/README.md)
* [VMs](vms/README.md)
---
## Files
* [Azure Certification Roadmap AZ-900 AZ-204 AI-102 AZ-305](Azure_Certification_Roadmap_AZ900_AZ204_AI102_AZ305.md)
* [Cloud Shell](cloud-shell.md)
* [Notes](notes.md)
* [Regions and availability zones](#regions-and-availability-zones)
---
## References
* Udemy - AZ-900 Microsoft Azure Fundamentals in a Weekend
* Youtube - https://www.youtube.com/watch?v=tDuruX7XSac&ab_channel=edureka%21
* Youtube - https://www.youtube.com/watch?v=0bNFkI_0jhc&ab_channel=Intellipaat
* Youtube - https://www.youtube.com/watch?v=TQl9sN3qs3M&ab_channel=Intellipaat
* Udemy - Microsoft Azure: From Zero to Hero - The Complete Guide
* Services - https://azure.microsoft.com/en-in/products
* azure calculator - https://azure.microsoft.com/en-au/pricing/calculator/
---
# Regions and availability zones

[Notes](notes.md) and [data center, availability zones, and region](img/data-center-az-regions.jpeg) describe the same layout.

One or more servers make a data center. One or more data centers make an availability zone. One or more availability zones make a region. A region is a geographic location.

The diagram shows one Azure region with availability zones 1, 2, and 3. Each zone is labeled as one or more data centers. Diverse fiber paths connect the zones.

Choose a region with CLPS:

* Compliance: laws that apply when servers are outside the country
* Latency: place the region close to users
* Pricing: cost changes from region to region
* Service availability: a service may be missing in a region

Example regions in the notes: East US (3 zones), West Central US (no zones listed), Brazil South (3), West Europe (3), and Southeast Asia (3).
