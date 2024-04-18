CREATE TABLE IF NOT EXISTS drivers (
    id BIGINT PRIMARY KEY,
    document VARCHAR(255),
    birthdate DATE
);

CREATE TABLE IF NOT EXISTS cars (
    id BIGINT PRIMARY KEY,
    model VARCHAR(255),
    manufacturer VARCHAR(255),
    "year" VARCHAR(4),
    fipe_value FLOAT
);

CREATE TABLE IF NOT EXISTS customer (
    id BIGINT PRIMARY KEY,
    driver_id BIGINT,
    FOREIGN KEY (driver_id) REFERENCES drivers(id)
);

CREATE TABLE IF NOT EXISTS car_drivers (
    id BIGINT PRIMARY KEY,
    is_main_driver BOOLEAN,
    driver_id BIGINT,
    car_id BIGINT,
    FOREIGN KEY (driver_id) REFERENCES drivers(id),
    FOREIGN KEY (car_id) REFERENCES cars(id)
);

CREATE TABLE IF NOT EXISTS claims (
    id BIGINT PRIMARY KEY,
    event_date DATE,
    driver_id BIGINT,
    car_id BIGINT,
    FOREIGN KEY (driver_id) REFERENCES drivers(id),
    FOREIGN KEY (car_id) REFERENCES cars(id)
);

CREATE TABLE IF NOT EXISTS insurances (
    id BIGINT PRIMARY KEY,
    creation_dt TIMESTAMP,
    updated_dt TIMESTAMP,
    is_active BOOLEAN,
    customer_id BIGINT,
    car_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (car_id) REFERENCES cars(id)
);

INSERT INTO drivers (id, document, birthdate) VALUES (1, '123456789', '1995-01-01');
INSERT INTO drivers (id, document, birthdate) VALUES (2, '987654321', '1990-01-01');
INSERT INTO drivers (id, document, birthdate) VALUES (3, '456789123', '1985-01-01');

INSERT INTO cars (id, model, manufacturer, "year", fipe_value) VALUES (1, 'Model S', 'Tesla', '2020', 100000.00);
INSERT INTO cars (id, model, manufacturer, "year", fipe_value) VALUES (2, 'Model 3', 'Tesla', '2019', 50000.00);
INSERT INTO cars (id, model, manufacturer, "year", fipe_value) VALUES (3, 'Model X', 'Tesla', '2021', 150000.00);

INSERT INTO customer (id, driver_id) VALUES (1, 1);
INSERT INTO customer (id, driver_id) VALUES (2, 2);
INSERT INTO customer (id, driver_id) VALUES (3, 3);

INSERT INTO car_drivers (id, is_main_driver, driver_id, car_id) VALUES (1, true, 1, 1);
INSERT INTO car_drivers (id, is_main_driver, driver_id, car_id) VALUES (2, false, 2, 2);
INSERT INTO car_drivers (id, is_main_driver, driver_id, car_id) VALUES (3, true, 3, 3);

INSERT INTO claims (id, event_date, driver_id, car_id) VALUES (1, '2022-01-01', 1, 1);
INSERT INTO claims (id, event_date, driver_id, car_id) VALUES (2, '2022-02-01', 2, 2);
INSERT INTO claims (id, event_date, driver_id, car_id) VALUES (3, '2022-03-01', 3, 3);

INSERT INTO insurances (id, creation_dt, updated_dt, is_active, customer_id, car_id) VALUES (1, '2022-01-01', '2022-01-01 00:00:00', true, 1, 1);
INSERT INTO insurances (id, creation_dt, updated_dt, is_active, customer_id, car_id) VALUES (2, '2022-02-01', '2022-02-01 00:00:00', true, 2, 2);
INSERT INTO insurances (id, creation_dt, updated_dt, is_active, customer_id, car_id) VALUES (3, '2022-03-01', '2022-03-01 00:00:00', true, 3, 3);