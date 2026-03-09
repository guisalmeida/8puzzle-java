CREATE DATABASE IF NOT EXISTS eight_puzzle;

CREATE TABLE player (
	id SERIAL PRIMARY KEY,
	name varchar(50),
	moves integer,
	winner boolean,
	board_id integer
);

CREATE TABLE board (
	id SERIAL PRIMARY KEY,
	tile_top_left integer,
	tile_top_center integer,
	tile_top_right integer,
	tile_center_left integer,
	tile_center integer,
	tile_center_right integer,
	tile_bottom_left integer,
	tile_bottom_center integer,
	tile_bottom_right integer
);

ALTER TABLE player
	ADD CONSTRAINT board_id_fk
	FOREIGN KEY (board_id)
	REFERENCES board (id)
	ON UPDATE CASCADE
	ON DELETE NO ACTION;


SELECT * FROM board b
INNER JOIN player p
ON b.id = p.board_id
WHERE p.id = 1;

select * from player;
