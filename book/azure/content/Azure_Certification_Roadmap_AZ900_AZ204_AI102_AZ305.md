# Azure Certification Roadmap — AZ-900 → AZ-204 → AI-102 → AZ-305

## Recommended sequence

1. **AZ-900 — Microsoft Azure Fundamentals**
2. **AZ-204 — Developing Solutions for Microsoft Azure**
3. **AI-102 — Designing and Implementing a Microsoft Azure AI Solution**
4. **AZ-305 — Designing Microsoft Azure Infrastructure Solutions**

> **Important:** Microsoft can change exam objectives and certification status. Before starting each certification, verify the current exam guide on Microsoft Learn.

## Overall roadmap

| Certification | Suggested preparation | Daily time | Primary goal |
|---|---:|---:|---|
| AZ-900 | 14 days | 1.5–2 hrs/day | Azure foundation |
| AZ-204 | 8 weeks | 2–2.5 hrs/day | Azure application development |
| AI-102 | 8 weeks | 2–2.5 hrs/day | Azure AI engineering |
| AZ-305 | 8–10 weeks | 2–2.5 hrs/day | Azure architecture |

For your existing Java/backend architect experience, the biggest learning areas are Azure-specific services, identity/security, networking, managed services, and hands-on Azure implementation rather than programming fundamentals.

---

# 1. AZ-900 — Azure Fundamentals

## Target

**14 days / ~20–25 hours**

### Week 1 — Cloud + Azure fundamentals

### Day 1 — Cloud concepts
- What is cloud computing?
- Public, private and hybrid cloud
- IaaS, PaaS, SaaS
- Shared responsibility model
- Consumption-based model
- Benefits of cloud computing

**Practice:** 20–30 questions

### Day 2 — Cloud architecture
- Regions
- Region pairs
- Availability zones
- Datacenters
- Azure global infrastructure
- Resources and resource groups
- Subscriptions
- Management groups

**Practice:** 20–30 questions

### Day 3 — Azure compute
- Virtual Machines
- VM Scale Sets
- Availability Sets
- App Service
- Azure Functions
- Container Instances
- Azure Container Apps
- AKS basics

**Focus:** Understand *when to use which service*.

### Day 4 — Azure networking
- Virtual Network
- Subnets
- Public/private endpoints
- Network Security Groups
- Azure DNS
- VPN Gateway
- ExpressRoute
- Load Balancer
- Application Gateway
- Front Door

### Day 5 — Azure storage
- Storage accounts
- Blob Storage
- Files
- Queues
- Tables
- Storage tiers
- Redundancy: LRS, ZRS, GRS, GZRS
- Shared Access Signatures

### Day 6 — Azure databases
- Azure SQL
- Azure Database for PostgreSQL
- Azure Cosmos DB
- Managed database concepts
- Relational vs NoSQL selection

### Day 7 — Review
- Review Days 1–6
- Take a practice assessment
- Create a list of weak topics
- Re-study weak topics

---

## Week 2 — Security, governance and management

### Day 8 — Identity
- Microsoft Entra ID
- Authentication vs authorization
- MFA
- Conditional Access
- RBAC
- Managed identities
- Service principals

### Day 9 — Security
- Microsoft Defender for Cloud
- Azure Key Vault
- Network security
- Zero Trust basics
- Security posture

### Day 10 — Governance
- Azure Policy
- Resource locks
- Tags
- Management groups
- Subscriptions
- Azure Blueprints concepts

### Day 11 — Monitoring and management
- Azure Monitor
- Log Analytics
- Application Insights
- Alerts
- Service Health
- Azure Advisor

### Day 12 — Cost management
- Azure pricing concepts
- Cost Management
- Pricing Calculator
- Service Level Agreements
- Service lifecycle
- Reservations
- Azure savings concepts

### Day 13 — Full revision
- Review all domains
- Do 1–2 full practice tests
- Analyze every incorrect answer
- Revisit confusing Azure services

### Day 14 — Exam preparation
- Final practice test
- Target consistently high scores
- Review service comparisons
- Review terminology
- Schedule/take AZ-900 when ready

## AZ-900 must-know comparisons

Be able to explain:

- VM vs App Service vs Functions
- Containers vs VMs
- AKS vs Container Apps
- Blob vs Files vs Queue vs Tables
- Azure SQL vs Cosmos DB
- Load Balancer vs Application Gateway vs Front Door
- NSG vs Azure Firewall
- Authentication vs authorization
- RBAC vs Azure Policy
- Region vs Availability Zone
- LRS vs ZRS vs GRS vs GZRS
- IaaS vs PaaS vs SaaS

---

# 2. AZ-204 — Developing Solutions for Microsoft Azure

## Target

**8 weeks / ~110–125 hours**

Because you already have strong Java/Spring Boot experience, spend less time on basic programming and more time implementing Azure services.

## Week 1 — Azure application development fundamentals

- Azure SDK concepts
- Azure CLI
- Azure PowerShell
- Azure Resource Manager
- App Service
- App Service configuration
- Deployment slots
- Application settings
- Managed identities
- Configuration/secrets

**Hands-on:**
Build and deploy a small Spring Boot REST API to Azure App Service.

## Week 2 — Azure compute

- App Service
- Azure Functions
- Durable Functions concepts
- Container Apps
- Azure Container Registry
- Container deployment
- Scaling
- Autoscaling

**Hands-on:**
Create:
- REST API
- Function
- Containerized service

Deploy all three and compare their use cases.

## Week 3 — Storage

- Blob Storage
- Blob lifecycle
- Blob access tiers
- SAS
- Azure Files
- Queue Storage
- Table Storage
- Storage SDK
- Event-based processing

**Hands-on:**
Build a document-upload service using Blob Storage and a queue-driven processor.

## Week 4 — Databases and caching

- Azure SQL
- Cosmos DB
- Partitioning
- Consistency models
- Cosmos DB APIs
- Redis concepts
- Data access from Java

**Hands-on:**
Create a Spring Boot service using:
- Azure SQL
- Cosmos DB
- Redis

## Week 5 — Authentication, authorization and security

- Microsoft Entra ID
- OAuth 2.0
- OpenID Connect
- Managed identities
- RBAC
- Key Vault
- App authentication
- Secure configuration

**Hands-on:**
Secure your Spring Boot API with Entra ID and access secrets through Key Vault.

## Week 6 — Messaging and event-driven architecture

- Service Bus
- Queue vs Topic
- Event Grid
- Event Hubs
- Dead-letter queues
- Retry
- Idempotency
- Asynchronous processing

**Hands-on:**
Build:

`REST API → Service Bus → Worker → Database`

## Week 7 — Monitoring and deployment

- Application Insights
- Azure Monitor
- Logs
- Metrics
- Alerts
- Deployment slots
- CI/CD concepts
- GitHub Actions / Azure DevOps concepts
- Infrastructure as Code basics

**Hands-on:**
Deploy your application through CI/CD and configure monitoring.

## Week 8 — Exam preparation

- Review all objectives
- Microsoft Learn assessment
- Practice exams
- Scenario-based questions
- Hands-on revision
- Identify weak services
- Final mock exams

### AZ-204 focus

Do not just memorize service names.

For every Azure service, learn:

1. What problem does it solve?
2. When should I use it?
3. What are the alternatives?
4. How does it scale?
5. How is it secured?
6. How does a Java application integrate with it?

---

# 3. AI-102 — Azure AI Engineer

## Target

**8 weeks / ~110–125 hours**

This certification fits particularly well with your planned AI/LLM/agentic-AI learning.

## Week 1 — Azure AI fundamentals

- Azure AI services
- Azure AI Foundry concepts
- AI workloads
- Responsible AI
- Authentication
- Azure AI SDKs
- REST APIs

## Week 2 — Azure AI Vision

- Image analysis
- OCR
- Document/image understanding
- Face-related concepts
- Computer vision workloads

**Hands-on:**
Create an image/document analysis application.

## Week 3 — Azure AI Language

- Text analytics
- Sentiment
- Entity recognition
- Key phrase extraction
- Language detection
- Summarization
- Conversational language understanding

**Hands-on:**
Build a text-analysis REST API.

## Week 4 — Azure AI Search

This is especially important for your RAG interests.

Learn:

- Search indexes
- Indexers
- Data sources
- Skillsets
- Semantic search
- Vector search
- Hybrid search
- Filters
- Chunking
- Embeddings

**Hands-on:**

Build:

`Documents → Chunking → Embeddings → Azure AI Search → Retrieval`

## Week 5 — Generative AI

- Azure OpenAI concepts
- Generative AI
- Prompt engineering
- Chat completions
- Embeddings
- Function/tool calling concepts
- Grounding
- Content safety
- Model deployment concepts

**Hands-on:**

Build a basic RAG chatbot.

## Week 6 — Document Intelligence + knowledge extraction

- Document Intelligence
- Prebuilt models
- Custom extraction
- OCR
- Forms/documents
- Knowledge mining concepts

**Hands-on:**

Build:

`PDF → Document Intelligence → Structured data → Search`

## Week 7 — AI application integration

- AI SDKs
- REST APIs
- Authentication
- Monitoring
- Evaluation
- Responsible AI
- Production considerations

Integrate AI into a Spring Boot backend.

## Week 8 — Exam preparation

- Review exam objectives
- Practice assessments
- Scenario questions
- Azure AI service selection
- RAG revision
- Search/vector search revision
- Final mock exams

### AI-102 focus for your career

Go beyond the exam.

After AI-102, continue into:

- LLM architecture
- RAG
- GraphRAG
- Agentic AI
- LangChain
- LangGraph
- MCP
- Tool calling
- Multi-agent systems
- Evaluation
- AI observability
- AI security
- Production AI architecture

---

# 4. AZ-305 — Designing Microsoft Azure Infrastructure Solutions

## Target

**8–10 weeks / ~120–150 hours**

This should be treated as an architecture certification, not simply another Azure fundamentals exam.

## Week 1 — Architecture methodology

- Azure Well-Architected Framework
- Reliability
- Security
- Cost optimization
- Operational excellence
- Performance efficiency
- Architecture trade-offs

## Week 2 — Identity and governance architecture

- Microsoft Entra ID
- RBAC
- Managed identities
- Management groups
- Subscriptions
- Azure Policy
- Governance
- Landing zones

## Week 3 — Networking architecture

Deep dive:

- VNets
- Subnets
- NSGs
- Azure Firewall
- VPN Gateway
- ExpressRoute
- Private Link
- Private Endpoints
- Load Balancer
- Application Gateway
- Front Door
- DNS

Design multi-region network architectures.

## Week 4 — Compute architecture

- VMs
- VM Scale Sets
- App Service
- Functions
- AKS
- Container Apps
- Scaling
- Availability
- Disaster recovery

Learn how to select compute based on workload requirements.

## Week 5 — Storage architecture

- Blob Storage
- Files
- Data Lake concepts
- Storage redundancy
- Lifecycle management
- Backup
- Disaster recovery

Practice architecture scenarios.

## Week 6 — Database architecture

- Azure SQL
- Cosmos DB
- PostgreSQL
- Redis
- Database scaling
- Replication
- Partitioning
- Consistency
- Backup
- DR

Focus heavily on **choosing the correct database for a scenario**.

## Week 7 — Integration and messaging

- Service Bus
- Event Grid
- Event Hubs
- API Management
- Logic Apps
- Integration architecture
- Event-driven architecture

## Week 8 — Security + reliability

- Zero Trust
- Defender for Cloud
- Key Vault
- Network security
- Backup
- Site Recovery
- Availability
- Multi-region architecture
- RTO/RPO

## Week 9 — Architecture case studies

Practice complete designs:

### Scenario 1
Global e-commerce application

### Scenario 2
Banking API platform

### Scenario 3
Document-processing platform

### Scenario 4
AI/RAG platform

### Scenario 5
Legacy application migration

For each scenario determine:

- Compute
- Network
- Database
- Storage
- Security
- Integration
- Monitoring
- Availability
- DR
- Cost

## Week 10 — Final preparation

- Microsoft Learn assessments
- Practice exams
- Architecture scenarios
- Review service-selection questions
- Review Well-Architected Framework
- Final mock exams

---

# Recommended hands-on project

Instead of doing isolated labs, build one platform throughout the roadmap.

## Project: AI Employee Assistant

Architecture:

```text
                   ┌─────────────────┐
                   │   Web / Client  │
                   └────────┬────────┘
                            │
                     ┌──────▼──────┐
                     │ API Gateway │
                     └──────┬──────┘
                            │
                 ┌──────────▼──────────┐
                 │    Spring Boot API  │
                 └──────────┬──────────┘
                            │
       ┌────────────────────┼─────────────────────┐
       │                    │                     │
 ┌─────▼─────┐       ┌──────▼─────┐       ┌──────▼─────┐
 │ Azure SQL │       │ Cosmos DB   │       │   Redis    │
 └───────────┘       └─────────────┘       └────────────┘
                            │
                     ┌──────▼──────┐
                     │ Service Bus │
                     └──────┬──────┘
                            │
                     ┌──────▼──────┐
                     │ AI Services │
                     └──────┬──────┘
                            │
                  ┌─────────▼─────────┐
                  │ Azure AI Search   │
                  │ Vector + Semantic │
                  └───────────────────┘
```

Use this single project to practice:

- AZ-900 → identify Azure services
- AZ-204 → implement the backend
- AI-102 → implement RAG/AI
- AZ-305 → design the production architecture

---

# Recommended weekly schedule

A sustainable schedule alongside a full-time job:

### Monday–Friday
**1.5–2 hours/day**

- 45 min theory
- 45 min hands-on
- 15–30 min questions/revision

### Saturday
**3 hours**

- Hands-on project
- Practice questions
- Architecture review

### Sunday
**Rest / optional 1 hour revision**

---

# Recommended learning resources

## Official Microsoft Learn

Use Microsoft Learn as the primary source because exam objectives can change.

- AZ-900: Azure Fundamentals
- AZ-204: Developing Solutions for Microsoft Azure
- AI-102: Azure AI Engineer
- AZ-305: Azure Solutions Architect

Before each exam, check the current Microsoft exam study guide and skills measured.

## Udemy

Use Udemy mainly for:

- Structured video explanations
- Additional hands-on labs
- Practice exams

When selecting a course, choose one that explicitly matches the **current exam version** rather than an older AZ-900/AZ-204/AI-102/AZ-305 course.

---

# Exam readiness checklist

Do not book the exam simply because you completed the videos.

Before booking, aim for:

- Microsoft Learn assessment: consistently strong
- 3+ practice exams completed
- Understand why every wrong answer is wrong
- Can explain major Azure services without notes
- Can select services from scenario requirements
- Completed hands-on labs
- Reviewed the current Microsoft exam skills outline

---

# Final roadmap

```text
AZ-900
  │
  │  14 days
  ▼
AZ-204
  │
  │  8 weeks
  ▼
AI-102
  │
  │  8 weeks
  ▼
AZ-305
  │
  │  8–10 weeks
  ▼
Azure + AI Architect
```

## Suggested priority for your broader career preparation

```text
Python
  ↓
AI/ML + LLMs
  ↓
RAG + GraphRAG + Document Indexing
  ↓
AI Agents + Agentic AI + MCP
  ↓
Azure
  ├── AZ-900
  ├── AZ-204
  ├── AI-102
  └── AZ-305
  ↓
Cloud Security
  ↓
System Design
  ↓
DSA
  ↓
Production AI Architecture
```

## Important note

Certifications should support your architecture and AI skills, not replace hands-on engineering. For your background, the strongest combination is to **build real Azure + Java + AI projects while preparing for the exams**.
