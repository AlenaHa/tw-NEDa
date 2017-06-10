SET SERVEROUTPUT ON
DECLARE
  F UTL_FILE.FILE_TYPE;
  
  v_age_distribution         VARCHAR(256);
  v_district                 VARCHAR(256);
  v_gender                   VARCHAR(256);
  v_municipality             VARCHAR(256);
  v_count_id                 Number := 1;
  v_p_location_id            Number ;
  v_check                    number;
  v_check2                   number;
  V_LINE                     VARCHAR(32767);
BEGIN
    F := UTL_FILE.FOPEN ('DIR_NEDA', 'Population.csv', 'R',32767 );

    IF UTL_FILE.IS_OPEN(F) THEN
      LOOP
        BEGIN
          UTL_FILE.GET_LINE(F, V_LINE, 32767 );
          
          IF V_LINE IS NULL 
          THEN
            EXIT;
          END IF;
         
          v_age_distribution              := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 4);
          v_district                      := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 1);
          v_gender                        := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 5);
          
          SELECT COUNT(*) into v_check from location where LOWER(DISTRICT) = v_district;
      --    SELECT location_id into v_p_location_id from location where LOWER(DISTRICT) = v_district;
          if v_check != 0       
          then
                SELECT location_id into v_p_location_id from location where LOWER(DISTRICT) = v_district;
                
                SELECT COUNT(*) into v_check2 from population 
                where AGE_DISTRIBUTION = v_age_distribution and P_LOCATION_ID = v_p_location_id;
                
                DBMS_OUTPUT.PUT_LINE(v_check2);
                if v_check2 = 0
                then
                    INSERT INTO POPULATION VALUES
                    ( population_seq.nextval, v_age_distribution, v_gender, v_p_location_id);
                end if;
          end if;
          
          COMMIT;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN EXIT;
          END;
        
      END LOOP;
    END IF;
    UTL_FILE.FCLOSE(F);
  END;
  
  
  