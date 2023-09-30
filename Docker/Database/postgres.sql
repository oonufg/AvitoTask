\c segments_service;


CREATE TABLE IF NOT EXISTS users(
	ID BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS segments(
	ID BIGSERIAL PRIMARY KEY,
	slug VARCHAR(128),
	UNIQUE(slug)
);

CREATE TABLE IF NOT EXISTS users_segments(
	user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
	segment_id BIGINT REFERENCES segments(id) ON DELETE CASCADE,
	action VARCHAR(15) CHECK (action = 'adding' OR action = 'removal') NOT NULL,
	timestamp  TIMESTAMP NOT NULL,
	expired_timestamp TIMESTAMP
);
