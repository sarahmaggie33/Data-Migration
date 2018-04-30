DROP TABLE feature_source;
DROP TABLE feature_date;
DROP TABLE feature_info;
DROP TABLE feature_county cascade constraints;
DROP TABLE feature_state cascade constraints;

CREATE TABLE feature_state (
state_numeric NUMBER PRIMARY KEY, 
state_alpha VARCHAR2(2));

CREATE TABLE feature_county (
county_numeric NUMBER,
state_numeric NUMBER
REFERENCES feature_state(state_numeric),
county_name VARCHAR2(30),
CONSTRAINT county_id PRIMARY KEY (county_numeric, state_numeric));


CREATE TABLE feature_info (
feature_id NUMBER PRIMARY KEY, 
feature_name VARCHAR2(100), 
feature_class VARCHAR2(50),
state_numeric NUMBER
REFERENCES feature_state(state_numeric),
map_name VARCHAR2(50), 
elev_in_ft NUMBER, 
date_created VARCHAR(12));

CREATE TABLE feature_date (
feature_date_id NUMBER NOT NULL, 
PRIMARY KEY (feature_date_id), 
feature_id NUMBER
REFERENCES feature_info(feature_id), 
date_edited VARCHAR(12));

CREATE TABLE feature_source (
feature_source_id NUMBER NOT NULL PRIMARY KEY, 
feature_id NUMBER
REFERENCES feature_info(feature_id), 
source_lat_dms VARCHAR2(15), 
source_long_dms VARCHAR2(15));