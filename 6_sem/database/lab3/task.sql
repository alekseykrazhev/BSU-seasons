--1
CREATE OR REPLACE PROCEDURE register_participant(
    p_firstname IN VARCHAR2,
    p_lastname IN VARCHAR2,
    p_email IN VARCHAR2,
    p_organization IN VARCHAR2,
    p_event IN VARCHAR2
) IS
  org_id NUMBER;
BEGIN
  -- проверяем, есть ли указанная организация в базе данных
  SELECT organization_id INTO org_id FROM organizations WHERE organization_name = p_organization;

  -- если организация не найдена, добавляем ее в таблицу organizations
  IF org_id IS NULL THEN
    INSERT INTO organizations(organization_name) VALUES(p_organization);
    SELECT organizations_seq.CURRVAL INTO org_id FROM DUAL;
  END IF;

  -- добавляем запись о новом участнике в таблицу participants
  INSERT INTO participants(firstname, lastname, email, organization_id, event_name)
  VALUES(p_firstname, p_lastname, p_email, org_id, p_event);

  DBMS_OUTPUT.PUT_LINE('Участник ' || p_firstname || ' ' || p_lastname || ' зарегистрирован на мероприятие ' || p_event);
END;
--1

--2
CREATE OR REPLACE PROCEDURE redistribute_participants(
    p_event_name IN VARCHAR2
) IS
  v_event_id NUMBER;
  v_event_location VARCHAR2(100);
  v_avg_participants NUMBER;
  v_excess_participants NUMBER;
  v_participants_to_remove NUMBER;
  v_event_list VARCHAR2(1000) := '';
BEGIN
  -- получаем идентификатор и место проведения мероприятия
  SELECT event_id, location INTO v_event_id, v_event_location FROM events WHERE event_name = p_event_name;

  -- получаем среднее количество участников мероприятий, проходящих в месте проведения
  SELECT AVG(participant_count) INTO v_avg_participants FROM events WHERE location = v_event_location;

  -- получаем количество участников, которое нужно добавить к мероприятию, чтобы достичь среднего значения
  v_excess_participants := CEIL(v_avg_participants) - (SELECT participant_count FROM events WHERE event_id = v_event_id);

  -- если количество участников уже превышает среднее значение, выходим из процедуры
  IF v_excess_participants <= 0 THEN
    DBMS_OUTPUT.PUT_LINE('Количество участников мероприятия ' || p_event_name || ' уже достаточно');
    RETURN;
  END IF;

  -- получаем список мероприятий в том же месте проведения, где количество участников больше среднего значения
  SELECT event_name INTO v_event_list FROM events WHERE location = v_event_location AND participant_count > v_avg_participants;

  -- получаем количество участников, которое нужно удалить из этих мероприятий
  v_participants_to_remove := CEIL(v_excess_participants / LENGTH(REGEXP_REPLACE(v_event_list, '[^,]', '')));

  -- обновляем количество участников в целевом мероприятии
  UPDATE events SET participant_count = participant_count + v_excess_participants WHERE event_id = v_event_id;

  -- перераспределяем участников из других мероприятий
  FOR event_name IN (SELECT REGEXP_SUBSTR(v_event_list, '[^,]+', 1, LEVEL) event_name FROM DUAL CONNECT BY REGEXP_SUBSTR(v_event_list, '[^,]+', 1, LEVEL) IS NOT NULL) LOOP
    UPDATE events SET participant_count = participant_count - v_participants_to_remove WHERE event_name = event_name.event_name;
    DBMS_OUTPUT.PUT_LINE(v_participants_to_remove || ' участников было перераспределено с мероприятия ' || event_name.event_name);
  END LOOP;
END;
--2

--3
CREATE OR REPLACE PROCEDURE archive_events IS
  archive_count NUMBER := 0;
BEGIN
  -- добавляем сведения о мероприятиях, проведенных более года назад, в архив
  INSERT INTO event_archive(event_name, num_participants, last_location)
  SELECT event_name, num_participants, location
  FROM events
  WHERE event_date < ADD_MONTHS(SYSDATE, -12);

  -- удаляем записи об архивированных мероприятиях из таблицы events
  DELETE FROM events
  WHERE event_date < ADD_MONTHS(SYSDATE, -12);

  -- получаем количество архивных записей
  SELECT COUNT(*) INTO archive_count FROM event_archive;

  -- выводим количество архивных записей
  DBMS_OUTPUT.PUT_LINE('Архивировано ' || archive_count || ' мероприятий.');
END;
--3