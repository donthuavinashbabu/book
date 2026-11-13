# NFRs - Non Functional Requirements

---

- Scalability
  - Design for horizontal and vertical scaling using load balancers, stateless services, and auto-scaling groups.
- Availability
- Durability
- Security
  - Implement authentication (OAuth2, SSO), authorization (RBAC/ABAC), and encryption (TLS, AES).
  - Secure coding practices, regular vulnerability scans, and penetration testing.

## Development

- Git flow
  - Use Git Flow, GitHub Flow, or trunk-based development.
  - Define PR rules, code reviews, and protected branches.
- IaC
  - Use Infrastructure as Code (Terraform) for reproducibility and version control.
  - Manage modules, workspaces, and remote state.
- Deployment strategy
  - Define blue-green, canary, or rolling deployments.
  - Ensure zero-downtime releases and rollback plans.
- Centralized configuration management
  - Manage environment-specific configs using Spring Cloud Config, Consul, or AWS Parameter Store.
- Local development environment
  - Provide Docker-based or script-based setup for consistent local environments.
- Data validation and consistency
  - Ensure data integrity across services and databases.
  - Use validation rules, constraints, and reconciliation jobs.

## Performance and monitoring

- Performance
  - Define SLAs for response time, throughput, and latency.
  - Use caching, connection pooling, and asynchronous processing.
- APM - Application performance monitoring
  - Monitor CPU, memory, response times, error rates, and throughput.
  - Tools: New Relic, Datadog, AppDynamics.
- DB performance monitoring
  - Track slow queries, locks, deadlocks, and connection pool usage.
  - Tools: Percona, pgBadger, AWS RDS Insights.
- API performance monitoring
  - Track latency, error rates, and usage per endpoint.
  - Tools: Postman Monitoring, Prometheus + Grafana.
- DB queries performance monitoring
  - Identify slow queries and optimize indexes or joins.
- Long running queries monitoring
  - Set thresholds and alerts for queries exceeding expected durations.
- Uptime monitoring
  - Use synthetic checks and uptime SLAs (e.g., 99.9%).
  - Tools: Pingdom, UptimeRobot, AWS Route 53 Health Checks.
- Live user count
  - Real-time tracking of active sessions or WebSocket connections.
- Day wise user count
  - Track daily active users (DAU), new signups, and retention.
- Fault tolerance
  - Ensure system resilience with retries, circuit breakers, fallback mechanisms, and redundancy.
- Alerting and on-call
  - Set up alerts for system anomalies and define escalation paths.
  - Tools: PagerDuty, Opsgenie, Prometheus Alertmanager.

## Testing

- Unit test execution with Git Actions
  - Automate unit, integration, and regression tests.
  - Include linting, security scans, and build validations.
- Automation testing
  - End-to-end test coverage using Selenium, Cypress, or Playwright.
- API automation testing
  - Use Postman, RestAssured, or Karate for automated API tests.
  - Include contract testing (e.g., Pact).
- Postman scripts for API testing
  - Maintain collections with pre-request scripts and test assertions.
- Code coverage metrics
  - Track % of code covered by unit, integration, and end-to-end tests.
  - Tools: JaCoCo, Istanbul, SonarQube.

## Operations

- Application version tracking
  - Maintain version metadata in logs, headers, or UI.
  - Use semantic versioning and changelogs.
- Log tracking
  - Use correlation IDs (transaction ID or trace ID) to trace requests across distributed systems.
  - Enable structured logging (e.g., JSON format).
  - Centralized log aggregation and visualization (Splunk, CloudWatch, or similar).
  - Enable alerting and anomaly detection.
- DB scripts maintenance
  - Version-controlled SQL scripts for schema changes and seed data.
  - Use Flyway or Liquibase.
- Audit logging
  - Track sensitive operations for compliance (e.g., GDPR, HIPAA).
  - Include user ID, timestamp, and action metadata.

## Documentation

- Functional documentation
  - Describe user-facing features, workflows, and business rules.
- Technical documentation
  - Cover architecture, design decisions, data flow, and dependencies.
- API list and search
  - Maintain an API catalog with descriptions, parameters, and examples.
  - Use Swagger/OpenAPI with search capabilities.
