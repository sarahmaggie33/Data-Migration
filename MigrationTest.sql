SELECT COUNT(feature_id)
FROM feature_info 
WHERE state_numeric = (SELECT state_numeric 
                        FROM feature_state 
                        WHERE state_alpha = "WY");
                        
SELECT feature_name, elevation_in_feet
FROM feature_info
WHERE elevation_in_feet = (SELECT MAX(elevation_in_feet)
                            FROM feature_info);
                            
SELECT feature_name, elevation_in_feet
FROM feature_info
WHERE elevation_in_feet > 13000;

SELECT feature_name, elevation_in_feet, primary_lat_dms, primary_long_dms
FROM feature_info JOIN feature_source ON feature_info.feature_id = feature_source.feature_id
WHERE feature_name = '%Devils Tower%';


--NEED LAST TEST STILL