-- CREATE DATABASE Грузоперевозки;

-- USE Грузоперевозки;


CREATE TABLE Пункты (
    Код_пункта INT PRIMARY KEY,
    Наименование VARCHAR(50) NOT NULL
);

CREATE TABLE Отправители (
    Код_отправителя INT PRIMARY KEY,
    Наименование VARCHAR(50) NOT NULL,
    Расположение VARCHAR(50) NOT NULL,
    Код_пункта INT NOT NULL,
    FOREIGN KEY (Код_пункта) REFERENCES Пункты (Код_пункта) ON DELETE CASCADE
);

CREATE TABLE Грузовик (
    Номер INT PRIMARY KEY,
    Марка VARCHAR(50) NOT NULL,
    Грузоподъемность INT NOT NULL,
    Водитель VARCHAR(50) NOT NULL
);

CREATE TABLE Груз (
    Код_груза INT PRIMARY KEY,
    Отправитель INT NOT NULL,
    Наименование VARCHAR(50) NOT NULL,
    Единица_изм VARCHAR(50) NOT NULL,
    Количество INT NOT NULL,
    Вес INT NOT NULL,
    Грузовик INT NOT NULL,
    FOREIGN KEY (Отправитель) REFERENCES Отправители (Код_отправителя) ON DELETE CASCADE,
    FOREIGN KEY (Грузовик) REFERENCES Грузовик (Номер) ON DELETE CASCADE
);
