BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

--
--DROP TABLE IF EXISTS users, tournament_teams, player_team, matches, players, tournaments, teams, locations, sports;
--DROP SEQUENCE IF EXISTS seq_user_id;
--
--
--CREATE SEQUENCE seq_user_id
--  INCREMENT BY 1
--  NO MAXVALUE
--  NO MINVALUE
--  CACHE 1;
--
--
--CREATE TABLE users (
--	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
--	username varchar(50) NOT NULL UNIQUE,
--	password_hash varchar(200) NOT NULL,
--	role varchar(50) NOT NULL,
--	CONSTRAINT PK_user PRIMARY KEY (user_id)
--);
--
--INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
--
--CREATE TABLE teams
--(
--	team_id SERIAL PRIMARY KEY,
--	team_name varchar(200) NOT NULL UNIQUE,
--	team_size int NOT NULL
--
--);
--CREATE TABLE locations
--(
--	location_id SERIAL PRIMARY KEY,
--	state_name VARCHAR(100),
--	city_name VARCHAR(100)
--);
--CREATE TABLE sports
--(
--	sport_id SERIAL PRIMARY KEY,
--	sport_name VARCHAR(100)
--
--);
--CREATE TABLE players
--(
--	player_id SERIAL PRIMARY KEY,
--	user_id int NOT NULL,
--	player_name VARCHAR(200),
--	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
--
--);
--CREATE TABLE tournaments
--(
--	tournament_id SERIAL PRIMARY KEY,
--	tournament_name VARCHAR(200),
--	num_of_teams int NOT NULL,
--	start_date date NOT NULL,
--	end_date date,
--	sport_id int,
--
--	CONSTRAINT fk_sport_id FOREIGN KEY (sport_id) REFERENCES sports (sport_id)
--
--);
--
--CREATE TABLE matches
--(
--	match_id SERIAL PRIMARY KEY,
--	tournament_id int NOT NULL,
--	start_date date NOT NULL,
--	start_time time NOT NULL,
--	home_team_id int,
--	away_team_id int,
--	location_id int,
--
--	CONSTRAINT fk_tournament_id FOREIGN KEY (tournament_id) REFERENCES tournaments (tournament_id),
--	CONSTRAINT fk_home_team FOREIGN KEY (home_team_id) REFERENCES teams (team_id),
--	CONSTRAINT fk_away_team FOREIGN KEY (away_team_id) REFERENCES teams (team_id),
--	CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES locations (location_id)
--);
--CREATE TABLE player_team
--(
--	player_id int NOT NULL,
--	team_id int NOT NULL,
--
--	CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES players (player_id),
--	CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES teams (team_id)
--
--);
--CREATE TABLE tournament_teams
--(
--	team_id int NOT NULL,
--	tournament_id int NOT NULL,
--
--	CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES teams (team_id),
--	CONSTRAINT fk_tournament_id FOREIGN KEY (tournament_id) REFERENCES tournaments (tournament_id)
--);
--
--INSERT INTO sports (sport_name)
--VALUES ('Basketball'),
--		('Football'),
--		('Cricket'),
--		('Ping-Pong'),
--		('Soccer'),
--		('Nascar'),
--		('Rock-Paper-Scissors'),
--		('Arm Wrestling'),
--		('Dog-Show'),
--		('Baseball'),
--		('Golf'),
--		('Hockey'),
--		('Poker'),
--		('Tennis');
--
--INSERT INTO tournaments (tournament_name, num_of_teams, start_date, end_date, sport_id)
--VALUES ('March Madness', 64, '03/01/2022', '04/04/2022', 1),
--		('Turkey Bowl', 16, '11/11/2022', '12/12/2022', 2),
--		('Dog-R-Us', 4, '05/08/2024', '05/09/2024', 9);
--
--INSERT INTO locations(state_name, city_name)
--VALUES				 ('New York', 'New York City'),
--					 ('Ohio', 'Columbus'),
--					 ('Pennsylvania', 'Pittsburgh'),
--					 ('Texas', 'Austin'),
--					 ('Texas', 'Houston'),
--					 ('Texas', 'El Paso'),
--					 ('Georgia', 'Atlanta');


COMMIT TRANSACTION;
