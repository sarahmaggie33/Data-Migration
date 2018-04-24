CREATE TABLE feature_state (
state_numeric NUMBER PRIMARY KEY, 
state_alpha VARCHAR2(2));

CREATE TABLE feature_county (
county_numeric NUMBER PRIMARY KEY, 
county_alpha VARCHAR2(2), 
state_numeric NUMBER
REFERENCES feature_state(state_numeric));

CREATE TABLE feature_info (
feature_id NUMBER PRIMARY KEY, 
feature_name VARCHAR2(50), 
feature_class VARCHAR2(50),
state_numeric NUMBER
REFERENCES feature_state(state_numeric), 
county_numeric NUMBER
REFERENCES feature_county(county_numeric), 
map_name VARCHAR2(50), 
elevation_in_feet NUMBER, 
date_created DATE);

CREATE TABLE feature_date (
feature_date_id NUMBER NOT NULL, 
PRIMARY KEY (feature_date_id), 
feature_id NUMBER
REFERENCES feature_info(feature_id), 
date_edited DATE);

CREATE TABLE feature_source (
feature_source_id NUMBER NOT NULL PRIMARY KEY, 
feature_id NUMBER
REFERENCES feature_info(feature_id), 
source_lat_dms VARCHAR2(15), 
source_long_dms VARCHAR2(15));



