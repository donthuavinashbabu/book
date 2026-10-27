# What is server?

Machine where our application runs.

Maintaining 1 server for our application is risky. It can be SPOF (Single Point Of Failure). so we need multiple servers. Managing multiple servers need huge maintenance like noise, power supply, cooling systems, security, high speed network connectivity etc. So we need a remote location to keep these many servers. This remote location is called data centers

## Challenges of managing data centers?

Data center is secure location keep many 1000s of servers

Cloud is simply someone else's infrastructure (like Amazon AWS, Microsoft Azure, Google GCP) that we rent.

AWS, Azure, GCP are cloud providers. They built massive data centers with hundreds & thousands of servers.

We rent infrastructure instead of buying it

### Key question: Why buy infrastructure when we can rent

## Core concepts of any cloud:

- On demand access: Rent resources (like servers, storage, databases) instantly
- Elasticity: Increase or decrease resources instantly based on demand
- Pay as you go: Only pay for what we use (like electicity meter bill). In the cloud, launch server then start paying. Terminate server then stop paying
- No CapEx - details below
- OpEx - details below

## Cloud Advantages:

- Pay for use:
  - CapEx / Capital Expenditure / Capital expense: Ahead of time expenditure (like setup data center) is called Capital Expenditure or Capital expense
  - OpEx / Operation Expenditure / Variable Expense: With cloud, companies no need to do CapEx. Instead they do invest for OpEx
- Economies of scale:
  - Buying infrastructure in large quantities reduces cost per unit. Cloud providers buys millions of disks, so they will get for cheaper price.
- Stop guessing capacity:
  - No need to guess for servers capacity using cloud elasticity feature
- Increased speed and agility:
  - Any new idea, develop and deploy to production. Because servers are ready any time. Faster to market
- Go global in minutes / Global Reach / Geo Distribution:
  - With multiple regions around the world, we can deploy to near by regions to the user, so users get low latency
- Avoid undifferentiated heavy lifting:
  - Business don't need to spend time or energy for maintaining servers, because cloud providers takes care of it. So business concentrates on improving their business

When Azure launched, it was called Windows azure. Later renamed to Microsoft Azure

---

## Regions and Zones - (also called Availability zones, simply AZ or AZs):

- one or more servers is Data Center
- one or more Data centers is Availability zones (AZ)
- one or more AZs is region
- Region - geo graphic location

---

## How to choose region:
- Shortcut: CLPS (Compliance, Latency, Pricing, Service availability)
- Compliance:
  - consider laws before choosing servers outside (like outside the country)
- Latency:
  - Choose to redue latency
- Pricing:
  - Cost of service changes region to region
- Service availability:
  - Not all services are not available in all regions

---

## Some example regions:

- (US) East US - 3 AZs
- (US) West Central US - NA
- (South America) Brazil South - 3 AZs
- (Europe) West Europe - 3 AZs
- (Asia Pacific) South East Asia - 3 AZs

---
# Naming Conventions
* ARM Template - Azure Resource Management Template