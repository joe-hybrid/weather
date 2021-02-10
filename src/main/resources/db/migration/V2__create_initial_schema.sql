CREATE TABLE weather
(
    id          uuid PRIMARY KEY,
    city        TEXT          NOT NULL,
    country     TEXT          NOT NULL,
    temperature NUMERIC(5, 2) not null,
    created_at  timestamp     not null,
    updated_at  timestamp
);
