DECLARE
  F UTL_FILE.FILE_TYPE;
  
  v_count_id   ONG.ONG_ID%TYPE := 1;
  v_ongName    ONG.ONG_NAME%TYPE;
  v_activity   ONG.ACTIVITY_TYPE%TYPE;
  v_actSubtype ONG.ACTIVITY_SUBTYPE%TYPE;
  v_District   Varchar(50);
  v_check      number;
  V_LINE       VARCHAR(32767);
  v_location_id LOCATION.LOCATION_ID%TYPE;
BEGIN
    F := UTL_FILE.FOPEN ('DIR_NEDA', 'ONG.csv', 'R',32767 );
    
    IF UTL_FILE.IS_OPEN(F) THEN
      LOOP
          BEGIN
            UTL_FILE.GET_LINE(F, V_LINE, 32767 );
          IF V_LINE IS NULL 
          THEN
            EXIT;
          END IF;

          v_District         := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 1);
          v_ongName          := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 4);
          v_activity         := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 7);
          v_actSubtype       := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 8);
          
          SELECT COUNT(*) into v_check from location where DISTRICT = v_District;
          if v_check != 0
          then
            SELECT location_id into v_location_id from location where DISTRICT = v_district;
          
            INSERT INTO ONG VALUES
            (v_count_id, v_activity,v_ongName,v_actSubtype);
            
            INSERT INTO ONG_LOCATION VALUES
            (v_count_id,v_location_id);
            
            v_count_id := v_count_id + 1;
          
          end if;
          
          COMMIT;
          EXCEPTION
        
         WHEN NO_DATA_FOUND THEN EXIT;
         END;
      
      END LOOP;
    END IF;
    
    UTL_FILE.FCLOSE(F);
  END;