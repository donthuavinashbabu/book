# Azure
------
- [Folders](#folders)
- [Files](#files)
- [References](#references)
---
# Folders
_No subfolders_
---
## Files
* [Create resource group](create-resource-group.md)
* [Create resource group configuration](create-resource-group.tf)
* [Create resource group example](#create-resource-group-example)
---
## References
* [azurerm provider](https://registry.terraform.io/providers/hashicorp/azurerm/latest)
---
# Create resource group example

[create-resource-group.md](create-resource-group.md) and [create-resource-group.tf](create-resource-group.tf) create one Azure resource group.

The configuration pins `hashicorp/azurerm` to `3.83.0`. Copy that provider block from the azurerm registry **USE PROVIDER** button. The provider sets `subscription_id`, `tenant_id`, `client_id`, and `client_secret` to placeholder values and enables `features {}`.

`azurerm_resource_group.myRg001` creates a group named `001-my-rg` in `southindia`.

From this folder:

```
terraform init
terraform plan -out main.tfplan
terraform apply
```
