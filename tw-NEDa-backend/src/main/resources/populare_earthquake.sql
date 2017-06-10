SET SERVEROUTPUT ON
DECLARE
  F UTL_FILE.FILE_TYPE;
  
  v_District         VARCHAR(50);
  v_eq_depth        EARTHQUAKE.EQ_DEPTH%TYPE;
  v_happened_on     varchar2(10);
  v_latitude        EARTHQUAKE.LATITUDE%TYPE;
  v_longitude       EARTHQUAKE.LONGITUDE%TYPE;
  v_magnitude       EARTHQUAKE.MAGNITUDE%TYPE;
  v_e_location_id   EARTHQUAKE.E_LOCATION_ID%TYPE;
  v_time            EARTHQUAKE.HAPPENED_ON%TYPE;
  V_LINE             VARCHAR(32767);
BEGIN
    F := UTL_FILE.FOPEN ('DIR_NEDA', 'Cutremure.csv', 'R',32767 );

    IF UTL_FILE.IS_OPEN(F) THEN
      LOOP
        BEGIN
          UTL_FILE.GET_LINE(F, V_LINE, 32767 );
          
          IF V_LINE IS NULL 
          THEN
            EXIT;
          END IF;
         
          v_happened_on      := REGEXP_SUBSTR(V_LINE,'[^,]+', 1,  1);
          v_latitude         := REGEXP_SUBSTR(V_LINE,'[^,]+', 1,  3);
          v_longitude        := REGEXP_SUBSTR(V_LINE,'[^,]+', 1,  4);
          v_magnitude        := REGEXP_SUBSTR(V_LINE,'[^,]+', 1,  5);
          v_District         := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 6);
        
          SELECT location_id into v_e_location_id from location where DISTRICT = v_District;

          v_eq_depth := ROUND(DBMS_RANDOM.value(low => 2, high => 300));    
          SELECT TO_TIMESTAMP (v_happened_on, 'DD/MM/RR') into v_time FROM DUAL;
         
          INSERT INTO EARTHQUAKE VALUES 
          ( eq_seq.nextval, v_eq_depth, v_time, v_latitude,v_e_location_id, v_longitude, v_magnitude);
          
  
          COMMIT;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN EXIT;
          END;
        
      END LOOP;
    END IF;
    UTL_FILE.FCLOSE(F);
  END;
  
  