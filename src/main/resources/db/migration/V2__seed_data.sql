-- V2__seed_data.sql
-- Corebank-lite: seed data mẫu để test API và luyện cursor pagination

-- 1. 50 USERS
DECLARE
BEGIN
    FOR i IN 1..50 LOOP
        INSERT INTO users (username, password_hash, full_name, role)
        VALUES (
            'user' || i,
            '$2a$10$placeholderBcryptHashXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX',
            'Nguyen Van ' || i,
            CASE WHEN MOD(i, 25) = 0 THEN 'ADMIN'
                 WHEN MOD(i, 10) = 0 THEN 'TELLER'
                 ELSE 'CUSTOMER' END
        );
    END LOOP;
    COMMIT;
END;
/

-- 2. 100 ACCOUNTS
DECLARE
    v_user_id NUMBER;
BEGIN
    FOR i IN 1..100 LOOP
        v_user_id := TRUNC(DBMS_RANDOM.VALUE(1, 51));
        INSERT INTO accounts (account_number, user_id, balance, status)
        VALUES (
            'ACC' || LPAD(i, 10, '0'),
            v_user_id,
            TRUNC(DBMS_RANDOM.VALUE(1000000, 500000000), 2),
            CASE WHEN MOD(i, 20) = 0 THEN 'LOCKED' ELSE 'ACTIVE' END
        );
    END LOOP;
    COMMIT;
END;
/

-- 3. 300 TRANSACTIONS
DECLARE
    v_from   NUMBER;
    v_to     NUMBER;
    v_status VARCHAR2(20);
BEGIN
    FOR i IN 1..300 LOOP
        v_from := TRUNC(DBMS_RANDOM.VALUE(1, 101));
        LOOP
            v_to := TRUNC(DBMS_RANDOM.VALUE(1, 101));
            EXIT WHEN v_to != v_from;
        END LOOP;

        v_status := CASE MOD(i, 10)
                        WHEN 0 THEN 'FAILED'
                        WHEN 1 THEN 'PENDING'
                        ELSE 'SUCCESS' END;

        INSERT INTO transactions
            (idempotency_key, from_account_id, to_account_id, amount,
             status, created_at, completed_at)
        VALUES (
            'SEED-' || TO_CHAR(SYSTIMESTAMP, 'YYYYMMDDHH24MISSFF') || '-' || i,
            v_from, v_to,
            TRUNC(DBMS_RANDOM.VALUE(10000, 50000000), 2),
            v_status,
            SYSTIMESTAMP - (i / 24),
            CASE WHEN v_status = 'SUCCESS'
                 THEN SYSTIMESTAMP - (i / 24) + (1 / 1440)
                 ELSE NULL END
        );
    END LOOP;
    COMMIT;
END;
/
