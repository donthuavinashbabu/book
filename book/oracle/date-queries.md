# Current Timestamp in Oracle SQL

In Oracle SQL, the main options are:

```sql
-- Current timestamp with time zone (session time zone)
SELECT CURRENT_TIMESTAMP FROM dual;

-- Current timestamp with time zone (database server time zone)
SELECT SYSTIMESTAMP FROM dual;

-- Local timestamp without time zone (session)
SELECT LOCALTIMESTAMP FROM dual;

-- Current date and time as DATE type (database server)
SELECT SYSDATE FROM dual;
```

## Differences

| Function | Returns | Time zone basis |
|---|---|---|
| `SYSDATE` | `DATE` (second precision) | Database server OS |
| `SYSTIMESTAMP` | `TIMESTAMP WITH TIME ZONE` (fractional seconds) | Database server OS |
| `CURRENT_DATE` | `DATE` | Session time zone |
| `CURRENT_TIMESTAMP` | `TIMESTAMP WITH TIME ZONE` | Session time zone |
| `LOCALTIMESTAMP` | `TIMESTAMP` | Session time zone |

## Formatting the Output

```sql
SELECT TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI:SS.FF3 TZH:TZM') FROM dual;
-- e.g. 2026-11-10 14:32:05.123 +05:30
```

## Inserting as a Default Value

```sql
CREATE TABLE audit_log (
  id         NUMBER GENERATED ALWAYS AS IDENTITY,
  created_at TIMESTAMP DEFAULT SYSTIMESTAMP
);
```

## Tip

If your app and database are in different time zones (common with servers in the cloud), prefer `SYSTIMESTAMP` or `CURRENT_TIMESTAMP` deliberately, depending on whether you want the server's or the session's view of time. With Spring Boot/JDBC, setting the session time zone consistently (e.g., UTC) avoids surprises.
