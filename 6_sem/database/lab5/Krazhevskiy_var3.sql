-- 1 --

CREATE OR REPLACE TRIGGER emp_update_trg
BEFORE UPDATE ON emp
FOR EACH ROW
DECLARE
    v_hour NUMBER;
BEGIN
    SELECT TO_NUMBER(TO_CHAR(SYSDATE, 'HH24')) INTO v_hour FROM dual;
    IF v_hour < 9 OR v_hour >= 18 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Обновление данных о сотрудниках в нерабочее время запрещено');
    END IF;
END;

-- 2 --

CREATE OR REPLACE TRIGGER scust_update_trg
AFTER UPDATE ON scust
FOR EACH ROW
BEGIN
    INSERT INTO h_scust (scust_id, updated_column, old_value, new_value, update_time)
    VALUES (:OLD.scust_id, 'SCUST.' || UPPER(:REFERENCING_COLUMN_NAME), :OLD.REFERENCING_COLUMN_NAME, :NEW.REFERENCING_COLUMN_NAME, SYSDATE);
END;

-- 3 --

CREATE OR REPLACE TRIGGER sord_insert_trg
BEFORE INSERT ON sord
FOR EACH ROW
DECLARE
    v_scust_id NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_scust_id FROM scust WHERE scust_id = :NEW.scust_id;
    IF v_scust_id = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Невозможно добавить заказ. Нет информации о заказчике в таблице SCUST');
    END IF;
END;

-- 4 --

CREATE OR REPLACE TRIGGER sprod_update_trg
AFTER UPDATE ON sprod
FOR EACH ROW
DECLARE
    v_ord_id NUMBER;
BEGIN
    IF :OLD.quantity >= 10 AND :NEW.quantity < 10 THEN
        SELECT MAX(ord_id) + 1 INTO v_ord_id FROM ord_prod;
        INSERT INTO ord_prod (ord_id, prod_id, quantity)
        VALUES (v_ord_id, :NEW.prod_id, 10 - :NEW.quantity);
    END IF;
END;

-- 5 --

CREATE OR REPLACE TRIGGER sals_update_trg
BEFORE UPDATE ON sals
FOR EACH ROW
BEGIN
    IF :NEW.min_salary < 600 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Минимальная зарплата не может быть установлена ниже 600');
    END IF;
END;

-- 6 --

CREATE OR REPLACE TRIGGER emp_insert_update_trg
BEFORE INSERT OR UPDATE OF job_id, salary ON emp
FOR EACH ROW
DECLARE
    v_min_salary sals.min_salary%TYPE;
    v_max_salary sals.max_salary%TYPE;
BEGIN
    SELECT min_salary, max_salary INTO v_min_salary, v_max_salary FROM sals WHERE job_id = :NEW.job_id;
    IF :NEW.salary < v_min_salary THEN
        :NEW.salary := v_min_salary;
    ELSIF :NEW.salary > v_max_salary THEN
        :NEW.salary := v_max_salary;
    END IF;
END;

-- 7 --

CREATE OR REPLACE TRIGGER scust_insert_trg
AFTER INSERT ON scust
FOR EACH ROW
BEGIN
    INSERT INTO cr_scust (scust_id, create_time)
    VALUES (:NEW.scust_id, SYSDATE);
END;

-- 8 --

CREATE OR REPLACE TRIGGER sitem_insert_trg
AFTER INSERT ON sitem
FOR EACH ROW
DECLARE
    v_sum NUMBER;
BEGIN
    SELECT SUM(quantity * price) INTO v_sum FROM sord WHERE ord_id = :NEW.ord_id;
    UPDATE sord SET total_amount = v_sum WHERE ord_id = :NEW.ord_id;
END;