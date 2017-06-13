drop table if exists users;
create table users (
  id integer primary key autoincrement,
  email varchar(255),
  mobile varchar(20),
  password varchar(255),
  name varchar(255)
);
drop table if exists games;
create table games (
  id integer primary key autoincrement,
  name varchar(255),
  description varchar(255),
  category varchar(255),
  thumbnail varchar(255),
  type integer,
  rate integer
);
drop table if exists comments;
create table comments (
  id integer primary key autoincrement,
  comment varchar(255),
  game_id integer,
  user_name varchar(25)
);
insert into `games` (name, description, thumbnail, category, type) VALUES ('Warcraft','Warcraft is a franchise of video games, novels, and other media created by Blizzard Entertainment.', 'warcraft.png', 'Strategy', '1'),  ('Overwatch', 'Overwatch is a multiplayer first-person shooter video game developed and published by Blizzard Entertainment', 'overwatch.jpg', 'MMORPG', '1'), ('Diablo', 'Diablo is an action role-playing hack and slash video game developed by Blizzard North', 'diablo.png', 'Action game', '1'), ('Battle Realms ', 'Battle Realms is a real-time strategy computer game published and released by Crave Entertainment and Ubisoft in 2001', 'Battle_Realms.jpg', 'Strategy', '1'), ('Battlefield 4 ', 'Battlefield 4™ is the genre-defining action blockbuster made from moments that blur the line between game and glory', 'BF4_450x197.jpg', 'Action', '0'), ('Gone Home ', 'Gone Home made a splash in 2013 with its quiet, introspective, mysterious gameplay', 'gone_home.jpg', 'Action', '0')