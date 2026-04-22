# NFRs - Non Functional Requirements
---
# Basic Lities
* Availability
* Scalability
* Durability
---
# ✅ Comprehensive Non-Functional Requirements (NFRs) List

## 🔐 Security
- Implement authentication (OAuth2, SSO), authorization (RBAC/ABAC), and encryption (TLS, AES).
- Secure coding practices, regular vulnerability scans, and penetration testing.

## 📈 Scalability
- Design for horizontal and vertical scaling using load balancers, stateless services, and auto-scaling groups.

## ⚙️ Fault Tolerance
- Ensure system resilience with retries, circuit breakers, fallback mechanisms, and redundancy.

## 🚀 Performance
- Define SLAs for response time, throughput, and latency.
- Use caching, connection pooling, and asynchronous processing.

## 🧾 Log Tracking with Transaction ID or Trace ID
- Use correlation IDs to trace requests across distributed systems.
- Enable structured logging (e.g., JSON format).

## 📊 Logs to Splunk / CloudWatch / Other
- Centralized log aggregation and visualization.
- Enable alerting and anomaly detection.

## 🌿 GitHub or Bitbucket Branching Strategies
- Use Git Flow, GitHub Flow, or trunk-based development.
- Define PR rules, code reviews, and protected branches.

## 🤖 GitHub Actions for Test Execution
- Automate unit, integration, and regression tests.
- Include linting, security scans, and build validations.

## ☁️ Infrastructure Creation with Terraform
- Use Infrastructure as Code (IaC) for reproducibility and version control.
- Manage modules, workspaces, and remote state.

## 📉 Application Performance Monitoring (APM)
- Monitor CPU, memory, response times, error rates, and throughput.
- Tools: New Relic, Datadog, AppDynamics.

## 🛢️ DB Performance Monitoring
- Track slow queries, locks, deadlocks, and connection pool usage.
- Tools: Percona, pgBadger, AWS RDS Insights.

## 🧾 Application Version Tracking
- Maintain version metadata in logs, headers, or UI.
- Use semantic versioning and changelogs.

## 👥 Live Users Count
- Real-time tracking of active sessions or WebSocket connections.

## 📅 Day-wise Users Count Metrics
- Track daily active users (DAU), new signups, and retention.

## ⚙️ API Performance Monitoring
- Track latency, error rates, and usage per endpoint.
- Tools: Postman Monitoring, Prometheus + Grafana.

## 🧪 Automation Testing - Full Functionality
- End-to-end test coverage using Selenium, Cypress, or Playwright.

## 🧪 API Automation Testing
- Use Postman, RestAssured, or Karate for automated API tests.
- Include contract testing (e.g., Pact).

## 🧾 Postman Scripts for API Testing
- Maintain collections with pre-request scripts and test assertions.

## 🧬 DB Scripts Maintenance
- Version-controlled SQL scripts for schema changes and seed data.
- Use Flyway or Liquibase.

## 🐢 DB Queries Performance Monitoring
- Identify slow queries and optimize indexes or joins.

## ⏱️ Long Running Queries Monitoring
- Set thresholds and alerts for queries exceeding expected durations.

## 📚 Functional Documentation
- Describe user-facing features, workflows, and business rules.

## 🛠️ Technical Documentation
- Cover architecture, design decisions, data flow, and dependencies.

## 🔍 API List and Search
- Maintain an API catalog with descriptions, parameters, and examples.
- Use Swagger/OpenAPI with search capabilities.

---

# 🆕 Suggested Additions

## 🧪 Code Coverage Metrics
- Track % of code covered by unit, integration, and end-to-end tests.
- Tools: JaCoCo, Istanbul, SonarQube.

## 🔄 Deployment Strategy
- Define blue-green, canary, or rolling deployments.
- Ensure zero-downtime releases and rollback plans.

## 🧯 Alerting & On-call Management
- Set up alerts for system anomalies and define escalation paths.
- Tools: PagerDuty, Opsgenie, Prometheus Alertmanager.

## 🧰 Centralized Configuration Management
- Manage environment-specific configs using Spring Cloud Config, Consul, or AWS Parameter Store.

## 🧑‍💻 Local Development Environment
- Provide Docker-based or script-based setup for consistent local environments.

## 🧪 Data Validation & Consistency
- Ensure data integrity across services and databases.
- Use validation rules, constraints, and reconciliation jobs.

## 🔐 Audit Logging
- Track sensitive operations for compliance (e.g., GDPR, HIPAA).
- Include user ID, timestamp, and action metadata.

## 🌐 Uptime Monitoring
- Use synthetic checks and uptime SLAs (e.g., 99.9%).
- Tools: Pingdom, UptimeRobot, AWS Route 53 Health Checks.