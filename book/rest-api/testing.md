# Types of API Testing

- **Smoke Testing**  
  Run right after a deploy to confirm critical endpoints (e.g., login, checkout, health checks) still respond.

- **Functional Testing**  
  Checks whether an endpoint does what the business expects, not just returning a clean 200 with incorrect data.

- **Contract Testing**  
  Protects the agreement between services. If a consumer depends on a field, type, or status code, the provider must not change it without warning.

- **Integration Testing**  
  Covers the full workflow across systems. For example, an order endpoint may rely on inventory, payment, and notifications — any of those dependencies can fail.

- **Regression Testing**  
  Ensures existing behavior remains intact when new changes are added. For instance, changes in discount logic should not break checkout totals or order history.

- **Load Testing**  
  Evaluates how the system holds up under expected traffic.

- **Stress Testing**  
  Pushes the system until something breaks.

- **Security Testing**  
  Examines authentication, access control, unsanitized input, and error leakage.

- **Fuzz Testing**  
  Sends unexpected or invalid inputs to the API to uncover bugs missed by normal test cases.
