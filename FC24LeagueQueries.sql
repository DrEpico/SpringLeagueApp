--USE EAFC24
--GO

---- create a leagues table 
--CREATE TABLE leagues (
--	--league_id is the identity column and primary key
--	--id starts from 1 and is incremented by 1
--    league_id INT IDENTITY(1,1) PRIMARY KEY,
--    league_name NVARCHAR(50)
--);

----league names are unknown by default except for...
--INSERT INTO leagues (league_name)
--VALUES ('unknown');

---- ...for top 5 European leagues, so populate the table with these league names
--INSERT INTO leagues (league_name)
--VALUES ('England Premier League'),
--		('France Ligue 1'),
--		('Germany Bundesliga'),
--		('Italy Serie A'),
--		('Spain La Liga')

---- create clubs table
---- this table allows more information to be attached to each club entity
--CREATE TABLE clubs (
--	--set primary key and auto generated id's
--    club_id INT IDENTITY(1,1) PRIMARY KEY,
--    club_name NVARCHAR(50),
--    league_id INT,
--	-- set PK-FK relationship with leagues table
--    FOREIGN KEY (league_id) REFERENCES leagues(league_id)
--);

---- insert every distinct club name from players table to the newly created clubs table
--INSERT INTO clubs (club_name)
--SELECT DISTINCT Club
--FROM players;

---- test command
--UPDATE clubs
--	SET league_id = CASE
--	WHEN club_name LIKE '%Barcelona SC%' THEN 1
--	ELSE league_id
--END;

---- updates league id for all Spanish top league clubs
--UPDATE clubs
--SET league_id = 6
--WHERE club_name IN (
--    'Athletic Club',
--    'Atlético de Madrid',
--    'CA Osasuna',
--    'Cádiz CF',
--    'D. Alavés',
--    'FC Barcelona',
--    'Getafe CF',
--    'Girona FC',
--    'Granada CF',
--    'Rayo Vallecano',
--    'RC Celta',
--    'RCD Mallorca',
--    'Real Betis',
--    'Real Madrid',
--    'Real Sociedad',
--    'Sevilla FC',
--    'UD Almería',
--    'UD Las Palmas',
--    'Valencia CF',
--    'Villarreal CF'
--);

---- check result
--SELECT *
--FROM clubs
--WHERE league_id = 6;

---- updates league id for all English top league clubs
--UPDATE clubs
--SET league_id = 2
--WHERE club_name IN (
--    'Arsenal',
--    'Aston Villa',
--    'Brentford',
--    'Brighton',
--    'Burnley',
--    'Chelsea',
--    'Crystal Palace',
--    'Everton',
--    'Luton Town',
--    'Liverpool',
--    'Manchester City',
--    'Manchester Utd',
--    'Newcastle Utd',
--	'Nott''m Forest',
--    'Southampton',
--    'Tottenham Hotspur',
--    'Watford',
--    'Westham Utd',
--    'Wolves',
--	'Fulham',
--	'Sheffield Utd'
--);

---- check result
--SELECT *
--FROM clubs
--WHERE league_id = 2;

---- set league id back to id for "unknown" league for these irrelavant clubs
--UPDATE clubs
--SET league_id = 1
--WHERE club_name IN (
--    'Leeds United',
--    'Norwich'
--);

---- updates league id for all Italian top league clubs
--UPDATE clubs
--SET league_id = 5
--WHERE club_name IN (
--    'Inter',
--    'Milan',
--    'Juventus',
--    'Bologna',
--    'Roma FC',
--    'Bergamo Calcio',
--    'Latium',
--    'Fiorentina',
--    'Napoli',
--    'Torino',
--    'AC Monza',
--    'Genoa',
--    'Lecce',
--    'Cagliari',
--    'Hellas Verona',
--    'Frosinone',
--    'Empoli',
--    'Udinese',
--    'Sassuolo',
--    'Salernitana'
--);

---- check result
--SELECT *
--FROM clubs
--WHERE league_id = 5;

---- updates league id for all German top league clubs
--UPDATE clubs
--SET league_id = 4
--WHERE club_name IN (
--    'Leverkusen',
--    'FC Bayern München',
--    'VfB Stuttgart',
--    'RB Leipzig',
--    'Borussia Dortmund',
--    'Frankfurt',
--    'SC Freiburg',
--    'FC Augsburg',
--    'TSG 1899 Hoffenheim',
--    'TSG Hoffenheim',
--    'SV Werder Bremen',
--    'VfL Wolfsburg',
--    'M''gladbach',
--    'Union Berlin',
--    'VfL Bochum',
--    '1. FSV Mainz 05',
--    '1. FC Köln',
--    'SV Darmstadt 98'
--);

---- check result
--SELECT *
--FROM clubs
--WHERE league_id = 4;

---- updates league id for all French top league clubs
--UPDATE clubs
--SET league_id = 3
--WHERE club_name IN (
--    'Paris SG',
--    'OM',
--    'OL',
--    'AS Monaco',
--    'RC Lens',
--    'LOSC Lille',
--    'Strasbourg',
--    'FC Nantes',
--    'Stade Rennais',
--    'Montpellier',
--    'OGC Nice',
--    'Stade Rennais FC', 
--	'Havre AC', 
--	'FC Metz', 
--	'Toulouse FC', 
--	'FC Lorient', 
--	'Stade Brestois 29', 
--	'Clermont Foot 63'
--);

----check result
--SELECT *
--FROM clubs
--WHERE league_id = 3;

---- debug command 
--SELECT *
--FROM players 
--WHERE name LIKE N'Antonio Rüdiger';
--SELECT *
--FROM clubs
--WHERE club_id = 67;

---- add club_id to players table
--ALTER TABLE players
--ADD club_id INT;

---- populate clubs table from Club column on players table
--UPDATE players
--SET club_id = (
--    SELECT club_id
--    FROM clubs
--    WHERE clubs.club_name = players.Club
--);

---- create the foreign key constraint
--ALTER TABLE players
--ADD CONSTRAINT FK_players_clubs
--FOREIGN KEY (club_id)
--REFERENCES clubs (club_id);

---- debug/check results
--SELECT * FROM clubs 
--ORDER BY league_id DESC

---- debug/check results
--SELECT * FROM players;
--SELECT DISTINCT column1 FROM players; 
---- column1 (the row that could potentially be used as the ID column for players table) had a couple of thousand rows less 
---- which shows not all players have distict column1 values which makes it not suitable for being used as the identity column

---- add identity-primary key column to players table
--ALTER TABLE players
--ADD player_id INT IDENTITY(1,1) PRIMARY KEY;

---- create a new table with optimised column order
--CREATE TABLE players_new (
--    player_id INT IDENTITY(0001,1) PRIMARY KEY NOT NULL,
--    name VARCHAR(50) NOT NULL,
--	nation VARCHAR(50) NOT NULL,
--	club_id INT NOT NULL,
--	original_club VARCHAR(50) NOT NULL,
--	position VARCHAR(50) NOT NULL,
--	age tinyint NOT NULL,
--	overall tinyint NOT NULL,
--	pace tinyint NOT NULL,
--	shooting tinyint NOT NULL, 
--	passing tinyint NOT NULL,
--	dribbling tinyint NOT NULL,
--	defending tinyint NOT NULL,
--	physicality tinyint NOT NULL,
--	acceleration tinyint NOT NULL,
--	sprint tinyint NOT NULL,
--	positioning tinyint NOT NULL,
--	finishing tinyint NOT NULL,
--	shot tinyint NOT NULL,
--	long tinyint NOT NULL,
--	volleys tinyint NOT NULL,
--	penalties tinyint NOT NULL,
--	vision tinyint NOT NULL,
--	crossing tinyint NOT NULL, 
--	free tinyint NOT NULL,
--	curve tinyint NOT NULL,
--	agility tinyint NOT NULL,
--	balance tinyint NOT NULL,
--	reactions tinyint NOT NULL, 
--	ball tinyint NOT NULL,
--	composure tinyint NOT NULL,
--	interceptions tinyint NOT NULL,
--	heading tinyint NOT NULL,
--	def tinyint NOT NULL,
--	standing tinyint NOT NULL,
--	sliding tinyint NOT NULL, 
--	jumping tinyint NOT NULL,
--	stamina tinyint NOT NULL, 
--	strength tinyint NOT NULL, 
--	aggression tinyint NOT NULL,
--	att_work_rate NVARCHAR(50) NOT NULL,
--	def_work_rate NVARCHAR(50) NOT NULL,
--	preferred_foot NVARCHAR(50) NOT NULL,
--	weak_foot tinyint NOT NULL,
--	skill_moves tinyint NOT NULL,
--	URL NVARCHAR(100) NOT NULL,
--	gender NVARCHAR(50) NOT NULL,
--	GK tinyint,
--);

---- insert values from original players table into new players table
--INSERT INTO players_new (
--    name,
--    nation,
--    club_id,
--    original_club,
--    position,
--    age,
--    overall,
--    pace,
--    shooting,
--    passing,
--    dribbling,
--    defending,
--    physicality,
--    acceleration,
--    sprint,
--    positioning,
--    finishing,
--    shot,
--    long,
--    volleys,
--    penalties,
--    vision,
--    crossing,
--    free,
--    curve,
--    agility,
--    balance,
--    reactions,
--    ball,
--    composure,
--    interceptions,
--    heading,
--    def,
--    standing,
--    sliding,
--    jumping,
--    stamina,
--    strength,
--    aggression,
--    att_work_rate,
--    def_work_rate,
--    preferred_foot,
--    weak_foot,
--    skill_moves,
--    URL,
--    gender,
--    GK
--)
--SELECT --auto generated command to save time
--    [Name],
--    [Nation],
--    [club_id],
--    [OriginalClub],
--    [Position],
--    [Age],
--    [Overall],
--    [Pace],
--    [Shooting],
--    [Passing],
--    [Dribbling],
--    [Defending],
--    [Physicality],
--    [Acceleration],
--    [Sprint],
--    [Positioning],
--    [Finishing],
--    [Shot],
--    [Long],
--    [Volleys],
--    [Penalties],
--    [Vision],
--    [Crossing],
--    [Free],
--    [Curve],
--    [Agility],
--    [Balance],
--    [Reactions],
--    [Ball],
--    [Composure],
--    [Interceptions],
--    [Heading],
--    [Def],
--    [Standing],
--    [Sliding],
--    [Jumping],
--    [Stamina],
--    [Strength],
--    [Aggression],
--    [Att_work_rate],
--    [Def_work_rate],
--    [Preferred_foot],
--    [Weak_foot],
--    [Skill_moves],
--    [URL],
--    [Gender],
--    [GK]
--FROM [dbo].[players];

---- debug line
--SELECT *
--FROM dbo.players_new
--SELECT *
--FROM dbo.players

---- drop old players table with original column order
--DROP TABLE players;

---- create the foreign key constraint
--ALTER TABLE players_new
--ADD CONSTRAINT FK_players_clubs
--FOREIGN KEY (club_id)
--REFERENCES clubs (club_id);

---- rename new players table and task is complete
--EXEC sp_rename 'players_new', 'players';

---- create managers table which contains relevant information about the users of the app
--CREATE TABLE managers (
--manager_id int IDENTITY(001,1) PRIMARY KEY NOT NULL,
--name nvarchar(30) NOT NULL,
--origin_id nvarchar(32) NOT NULL
--)

---- add required columns
--ALTER TABLE clubs 
--ADD budget int NULL,
--manager_id nvarchar(50) NULL,
--season_wins int NULL,
--season_draws int NULL,
--season_losses int NULL,
--total_wins int NULL,
--total_draws int NULL,
--total_losses int NULL

---- fix datatype
--ALTER TABLE clubs
--ALTER COLUMN manager_id INT;

---- create the foreign key constraint between clubs and managers tables
--ALTER TABLE clubs
--ADD CONSTRAINT FK_clubs_managers
--FOREIGN KEY (manager_id)
--REFERENCES managers (manager_id);

---- add columns to players table
--ALTER TABLE players 
--ADD weekly_wage int NULL,
--market_value int NULL,
--season_goals int NULL,
--season_assists int NULL,
--season_cleansheets int NULL,
--total_goals int NULL,
--total_assists int NULL,
--total_cleansheets int NULL,
--skill_points int NULL;

--CREATE TABLE career_stats (
--	stats_id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
--	weekly_wage int NULL,
--	market_value int NULL,
--	season_goals int NULL,
--	season_assists int NULL,
--	season_cleansheets int NULL,
--	total_goals int NULL,
--	total_assists int NULL,
--	total_cleansheets int NULL,
--	skill_points int NULL,
--	performance_rating varchar(200)
--);

--INSERT INTO career_stats(
--	weekly_wage, 
--	market_value, 
--	season_goals, 
--	season_assists, 
--	season_cleansheets,
--	total_goals,
--	total_assists,
--	total_cleansheets,
--	skill_points
--	)
--	SELECT player_id, 
--	weekly_wage,
--	market_value, 
--	season_goals, 
--	season_assists, 
--	season_cleansheets,
--	total_goals,
--	total_assists,
--	total_cleansheets,
--	skill_points
--	FROM players;

--ALTER TABLE players 
--DROP COLUMN weekly_wage,
--	market_value, 
--	season_goals, 
--	season_assists, 
--	season_cleansheets,
--	total_goals,
--	total_assists,
--	total_cleansheets,
--	skill_points

--ALTER TABLE players
--ADD stats_id int;

--ALTER TABLE players
--ADD CONSTRAINT FK_career_stats
--FOREIGN KEY (stats_id)
--REFERENCES career_stats (stats_id);

--ALTER TABLE managers
--ADD league_admin BIT DEFAULT 'FALSE';

SELECT * FROM clubs 
WHERE league_id = 2

-- search for club
SELECT * FROM clubs 
WHERE club_name LIKE '%Leicester%'

-- show players of a club
SELECT * 
FROM clubs 
JOIN players ON clubs.club_id = players.club_id 
WHERE clubs.club_id = 258;

-- show managers and corresponding clubs
SELECT c.club_id, c.club_name, m.manager_id, m.origin_id, m.name FROM managers as m JOIN clubs as c ON m.manager_id = c.manager_id

SELECT * FROM clubs 
WHERE manager_id IS NOT NULL;

SELECT * FROM managers;

UPDATE clubs
SET manager_id = 31
WHERE club_name LIKE '%Leicester%';

UPDATE clubs
SET league_id = 1
WHERE club_name LIKE 'Southampton'

--DELETE FROM managers;

DELETE FROM managers
WHERE manager_id = 35;

SELECT * FROM clubs
WHERE league_id = 6

SELECT c.club_id, c.club_name, m.manager_id, m.origin_id, m.name FROM managers as m JOIN clubs as c ON m.manager_id = c.manager_id














