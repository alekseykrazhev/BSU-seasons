-- a --
CREATE OR REPLACE PROCEDURE get_cargo_by_category(p_category_code NUMBER) AS
BEGIN
    SELECT *
    FROM Груз
    WHERE SUBSTR(TO_CHAR(Код_груза), 1, 1) = TO_CHAR(p_category_code);
END;
-- a --


-- b --
CREATE OR REPLACE PROCEDURE CALCULATE_SHIPPING_COST(p_sender_code IN NUMBER, p_rate IN NUMBER) AS
    v_total_cost NUMBER;
BEGIN
    SELECT SUM(Количество * p_rate) INTO v_total_cost
    FROM Груз
    WHERE Отправитель = p_sender_code;
    DBMS_OUTPUT.PUT_LINE('Общая стоимость перевозки грузов отправителя ' || p_sender_code || ': ' || v_total_cost);
END;
-- b --


-- c --
CREATE OR REPLACE PROCEDURE OPTIMAL_LOAD(p_truck_number IN NUMBER) AS
  v_truck_capacity NUMBER;
  v_current_load   NUMBER;
BEGIN
  SELECT грузоподъемность INTO v_truck_capacity FROM Грузовик WHERE Номер = p_truck_number;

  SELECT NVL(SUM(Вес), 0) INTO v_current_load FROM Груз WHERE Грузовик = p_truck_number;

  SELECT *
  FROM Груз
  WHERE Грузовик IS NULL
    AND Вес + v_current_load <= v_truck_capacity
    ORDER BY ВЕС desc;
END OPTIMAL_LOAD;
-- c --


-- d --
CREATE OR REPLACE PROCEDURE CARGO_TRANSPORT_REPORT AS
BEGIN
  FOR rec IN (SELECT o.Наименование AS Отправитель,
                     p.Наименование AS Пункт_отправления,
                     SUM(g.Количество * g.Единица_изм) AS Объем_грузов,
                     COUNT(DISTINCT g.Грузовик) AS Количество_транспортных_единиц
              FROM Груз g
              JOIN Отправители o ON g.Отправитель = o.Код_отправителя
              JOIN Пункты p ON g.Код_груза = p.Код_пункта
              GROUP BY o.Наименование, p.Наименование)
  LOOP
    DBMS_OUTPUT.PUT_LINE(rec.Отправитель || ', ' || rec.Пункт_отправления || ', ' || rec.Объем_грузов || ', '
                             || rec.Количество_транспортных_единиц);
  END LOOP;
END;
-- d --


-- trigger --
CREATE OR REPLACE TRIGGER check_cargo_limit
BEFORE INSERT ON Груз
FOR EACH ROW
DECLARE
    cargo_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO cargo_count
    FROM Груз
    WHERE ОТПРАВИТЕЛЬ = :NEW.ОТПРАВИТЕЛЬ;

    IF cargo_count >= 100 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Limit of cargos reached for this sender.');
    END IF;
END;
-- trigger --
