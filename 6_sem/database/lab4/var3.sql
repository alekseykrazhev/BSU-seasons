-- Создаем пакет "Calendar"

CREATE OR REPLACE PACKAGE Calendar AS

  -- Типы данных
  TYPE ParticipantType IS RECORD(
    id NUMBER,
    name VARCHAR2(100),
    role VARCHAR2(50)
  );

  TYPE EventType IS RECORD(
    id NUMBER,
    name VARCHAR2(100),
    location VARCHAR2(100),
    start_date DATE,
    end_date DATE
  );

  -- Процедуры и функции
  PROCEDURE RegisterParticipant(p_id IN NUMBER, p_name IN VARCHAR2, p_role IN VARCHAR2);
  PROCEDURE AddEvent(p_id IN NUMBER, p_name IN VARCHAR2, p_location IN VARCHAR2, p_start_date IN DATE, p_end_date IN DATE);
  FUNCTION GetEvents(p_start_date IN DATE, p_end_date IN DATE) RETURN SYS_REFCURSOR;
  FUNCTION GetEventsForParticipant(p_id IN NUMBER, p_start_date IN DATE, p_end_date IN DATE) RETURN SYS_REFCURSOR;
  FUNCTION GetEventsForOrganization(p_name IN VARCHAR2, p_start_date IN DATE, p_end_date IN DATE) RETURN SYS_REFCURSOR;

END Calendar;
/

-- Создаем тело пакета "Calendar"

CREATE OR REPLACE PACKAGE BODY Calendar AS

  -- Процедура для регистрации участника мероприятия
  PROCEDURE RegisterParticipant(p_id IN NUMBER, p_name IN VARCHAR2, p_role IN VARCHAR2) IS
  BEGIN
    -- Формируем новую запись участника
    INSERT INTO Participants(id, name, role) VALUES(p_id, p_name, p_role);
    
    -- Создаем расписание мероприятий для участника
    INSERT INTO ParticipantSchedule(participant_id, event_id) SELECT p_id, id FROM Events;
    COMMIT;
  END;

  -- Процедура для добавления мероприятия
  PROCEDURE AddEvent(p_id IN NUMBER, p_name IN VARCHAR2, p_location IN VARCHAR2, p_start_date IN DATE, p_end_date IN DATE) IS
  BEGIN
    -- Формируем новое мероприятие
    INSERT INTO Events(id, name, location, start_date, end_date) VALUES(p_id, p_name, p_location, p_start_date, p_end_date);
    
    -- Добавляем мероприятие в расписание участников
    INSERT INTO ParticipantSchedule(participant_id, event_id) SELECT id, p_id FROM Participants;
    COMMIT;
  END;

  -- Функция для получения списка мероприятий по заданному периоду
  FUNCTION GetEvents(p_start_date IN DATE, p_end_date IN DATE) RETURN SYS_REFCURSOR IS
    events SYS_REFCURSOR;
  BEGIN
    OPEN events FOR
    SELECT * FROM Events WHERE start_date >= p_start_date AND end_date <= p_end_date;
    RETURN events;
  END;

  -- Функция для получения списка мероприятий для заданного участника по заданному периоду
  FUNCTION GetEventsForParticipant(p_id IN NUMBER, p_start_date IN DATE, p_end_date IN DATE) RETURN SYS_REFCURSOR IS
    events SYS_REFCURSOR;
  BEGIN
    OPEN events FOR
    SELECT e.* FROM Events e 
    INNER JOIN ParticipantSchedule ps ON e.id = ps.event_id
    WHERE ps.participant_id = p_id AND e.start_date >= p_start_date AND e.end_date <= p_end_date;
    RETURN events;
  END;

  -- Функция для получения списка мероприятий для заданной организации по заданному периоду
  FUNCTION GetEventsForOrganization(p_name IN VARCHAR2, p_start_date IN DATE, p_end_date IN DATE) RETURN SYS_REFCURSOR IS
    events SYS_REFCURSOR;
  BEGIN
    OPEN events FOR
    SELECT e.* FROM Events e 
    INNER JOIN OrganizationSchedule os ON e.id = os.event_id
    WHERE os.organization_name = p_name AND e.start_date >= p_start_date AND e.end_date <= p_end_date;
    RETURN events;
  END;

END Calendar;
/
