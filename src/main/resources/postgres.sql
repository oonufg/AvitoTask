CREATE DATABASE segments_service;

CREATE TABLE IF NOT EXISTS users(
	ID BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS segments(
	ID BIGSERIAL PRIMARY KEY,
	slug VARCHAR(128),
	UNIQUE(slug)
);

CREATE TABLE IF NOT EXISTS users_segments(
	user_id BIGINT REFERENCES users(id),
	segments_id BIGINT REFERENCES segments(id),
	time  TIMESTAMP
);