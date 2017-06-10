DECLARE
  F UTL_FILE.FILE_TYPE;
  
  v_s_name     SUPPLIES.S_NAME%TYPE;
  v_s_category SUPPLIES.S_CATEGORY%TYPE;
  v_s_unit     SUPPLIES.S_UNIT%TYPE;
  v_s_amount   VARCHAR(50);
  v_count_id   SUPPLIES.SUPPLIES_ID%TYPE := 1;
  v_district   LOCATION.DISTRICT%TYPE;
  V_LINE       VARCHAR(32767);
  v_check      number;
  v_location_id LOCATION.LOCATION_ID%TYPE;
  v_supplies_id SUPPLIES.SUPPLIES_ID%TYPE;
BEGIN
    F := UTL_FILE.FOPEN ('DIR_NEDA', 'supplies.csv', 'R',32767 );
    
    IF UTL_FILE.IS_OPEN(F) THEN
      LOOP
          BEGIN
            UTL_FILE.GET_LINE(F, V_LINE, 32767 );
          IF V_LINE IS NULL 
          THEN
            EXIT;
          END IF;

          v_s_name              := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 1);
          v_s_category          := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 2);
          v_s_unit              := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 3);
          v_s_amount            := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 4);
          v_district            := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 8);
            
          --punem doar supplies care sunt in locatiile pe care le avem deja in db  
          SELECT COUNT(*) into v_check from location where DISTRICT = v_district;
          if v_check != 0
          then          
              SELECT location_id into v_location_id from location where DISTRICT = v_district;
              
              INSERT INTO SUPPLIES VALUES
              (v_count_id,v_s_amount,v_s_category,v_s_name,v_s_unit);
 
              INSERT INTO SUPPLIES_LOCATION VALUES
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
  
  