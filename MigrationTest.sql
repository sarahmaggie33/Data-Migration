--#1
SELECT COUNT(feature_id)
FROM feature_info 
WHERE state_numeric = (SELECT state_numeric 
                        FROM feature_state 
                        WHERE state_alpha = "WY");

--#2
SELECT feature_name, elev_in_ft
FROM feature_info
WHERE elev_in_ft = (SELECT MAX(elev_in_ft)
                            FROM feature_info);

--#3                            
SELECT feature_name, elev_in_ft
FROM feature_info
WHERE elevation_in_feet > 13000;

--#4
SELECT feature_name, elev_in_ft, primary_lat_dms, primary_long_dms
FROM feature_info JOIN feature_source ON feature_info.feature_id = feature_source.feature_id
WHERE feature_name = '%Devils Tower%';

--#5
--selecting the county with the largest number of features
--joining feature_info, feature_state, feature_county
SELECT county_name, COUNT(feature_id)
FROM feature_info JOIN feature_state ON feature_info.state_numeric = feature_state.state_numeric
JOIN feature_county ON feature_county.state_numeric = feature_state.state_numeric
WHERE state_alpha = 'WY' AND (SELECT MAX(COUNT(feature_id)

--selecting the county with the smallest number of features
--joining feature_info, feature_state, feature_county
SELECT county_name, COUNT(feature_id)
FROM feature_info JOIN feature_state ON feature_info.state_numeric = feature_state.state_numeric
JOIN feature_county ON feature_county.state_numeric = feature_state.state_numeric
WHERE state_alpha = 'WY' AND 

--#6
SELECT county_name, feature_name
FROM feature_info JOIN feature_county ON feature_info.county_numeric = feature_county.county_numeric
WHERE feature_name = '%post office%';