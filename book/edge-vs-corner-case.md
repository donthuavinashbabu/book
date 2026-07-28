# Edge Case vs Corner Case

The terms **edge case** and **corner case** are related, but they are not exactly the same.

| Edge Case | Corner Case |
|-----------|-------------|
| A situation at the boundary of valid input or operating conditions. | A rare situation where **multiple edge cases occur together**. |
| Tests one extreme condition. | Tests a combination of two or more extreme conditions. |
| More common in testing. | Less common and usually more difficult to identify. |
| Example: Empty input, maximum length string, minimum integer value. | Example: Empty input + maximum timeout + network failure occurring simultaneously. |

## Example 1: Login System

### Edge Cases
- Username is empty.
- Password is empty.
- Password length is exactly the maximum allowed (e.g., 128 characters).
- Username contains Unicode characters.

Each of these tests a single boundary or unusual condition.

### Corner Cases
- Username is empty **and** the database is temporarily unavailable.
- Password is at the maximum length **and** the account is locked.
- User logs in exactly when the password expires.

These combine multiple unusual conditions.

---

## Example 2: Pagination API

Suppose an API supports:
- `page >= 1`
- `pageSize <= 100`

### Edge Cases
- `page = 1`
- `pageSize = 100`
- No data returned
- Last page

### Corner Cases
- `page = 1`, `pageSize = 100`, and the database returns zero records.
- Last page while records are being deleted concurrently.
- Maximum page size with network latency and cache expiration.

---

## Example 3: Banking Application

### Edge Cases
- Transfer amount = ₹0
- Transfer amount = account balance
- Maximum daily transfer limit

### Corner Cases
- Transfer exactly equal to the daily limit **while** another transfer is being processed simultaneously.
- Account balance changes due to interest credit at the exact moment of transfer.

---

## Remember it this way

- **Edge case = One extreme condition.**
- **Corner case = Multiple extreme conditions happening together.**

As a software architect or tester, you should:

1. Write unit tests for normal and edge cases.
2. Add integration and system tests for corner cases, especially involving concurrency, distributed systems, network failures, retries, and timing issues.
