--1
CREATE OR REPLACE FUNCTION count_participants(event_name IN VARCHAR2) RETURN NUMBER IS
  participant_count NUMBER;
BEGIN
  SELECT COUNT(DISTINCT org_id) INTO participant_count
  FROM event_participants
  WHERE event_participants.event_name = event_name;

  IF participant_count = 0 THEN
    DBMS_OUTPUT.PUT_LINE('Мероприятие не найдено.');
  END IF;

  RETURN participant_count;
END;
--1


--2
CREATE OR REPLACE FUNCTION count_regular_participants(event_name IN VARCHAR2) RETURN NUMBER IS
  participant_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO participant_count
  FROM event_participants
  WHERE event_participants.event_name = event_name AND event_participants.participant_type = 'рядовой участник';

  RETURN participant_count;
END;
--2


--3
CREATE OR REPLACE FUNCTION check_event_details(event_name IN VARCHAR2) RETURN BOOLEAN IS
  start_time event_schedule.start_time%TYPE;
  end_time event_schedule.end_time%TYPE;
  location event_schedule.location%TYPE;
  participants_count NUMBER;
BEGIN
  SELECT start_time, end_time, location INTO start_time, end_time, location
  FROM event_schedule
  WHERE event_schedule.event_name = event_name;

  IF end_time <= start_time THEN
    RETURN FALSE;
  END IF;

  SELECT COUNT(*) INTO participants_count
  FROM event_participants
  WHERE event_participants.event_name = event_name;

  IF participants_count > 100 THEN
    RETURN FALSE;
  END IF;

  SELECT COUNT(*) INTO participants_count
  FROM event_schedule
  WHERE event_schedule.location = location AND start_time < end_time AND end_time > start_time AND
        event_schedule.event_name != event_name;

  IF participants_count > 0 THEN
    RETURN FALSE;
  END IF;

  RETURN TRUE;
END;
--3