SET SERVEROUTPUT ON
DECLARE
  F UTL_FILE.FILE_TYPE;
  
  v_District         VARCHAR(50);
  v_VDC_Municipality VARCHAR(50);
  v_result           number;
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
         
          v_District         := REGEXP_SUBSTR(V_LINE, '[^,]+', 1, 6);
          SELECT COUNT(*) INTO v_result from LOCATION WHERE DISTRICT = v_district;
          if v_result = 0
          then
               INSERT INTO location VALUES(dept_seq.nextval,   v_District, 'ceva');
               DBMS_OUTPUT.PUT_LINE(dept_seq.nextval);
          end if;
          
          COMMIT;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN EXIT;
          END;
        
      END LOOP;
    END IF;
    UTL_FILE.FCLOSE(F);
  END;
  
  UPDATE LOCATION
  SET LOCATION.MUNICIPALITY = 'Bidur'
  WHERE LOCATION.DISTRICT like '%Nuwakot%';
  
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Bhimeshwar'  
  WHERE LOCATION.DISTRICT like '%Dolakha%';
  
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Chautara'  
  WHERE LOCATION.DISTRICT like '%Sindhupalchok%';
  
  
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Nilkantha'  
  WHERE LOCATION.DISTRICT like 'Dhading';
  
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Dhunche'  
  WHERE LOCATION.DISTRICT like 'Rasuwa';
  
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Chautara'  
  WHERE LOCATION.DISTRICT like '%Sindhupalchowk%';
  
    
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Tibet'  
  WHERE LOCATION.DISTRICT like 'Tibet';
  
    
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Besisahar'  
  WHERE LOCATION.DISTRICT like '%Lamjung%';
  
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Manthali'  
  WHERE LOCATION.DISTRICT like '%Ramechhap%';
  
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Dhulikhel'  
  WHERE LOCATION.DISTRICT like '%Kavre%';
  
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Lalitput'  
  WHERE LOCATION.DISTRICT like 'Lalitput';
  
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Lalitpur'  
  WHERE LOCATION.DISTRICT like 'Lalitpur';
  
  
   UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Gorkha'  
  WHERE LOCATION.DISTRICT like '%Gorkha%';
 
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Kathmandu'  
  WHERE LOCATION.DISTRICT like '%Kathmandu%';
  
   UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Chautara'  
  WHERE LOCATION.DISTRICT like '%Sindupalchok%';
  
  
   UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Siddhicharan'  
  WHERE LOCATION.DISTRICT like 'Okhaldhunga';
 
 
   UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Sindupalchowk'  
  WHERE LOCATION.DISTRICT like 'Sindupalchowk';
 
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'kavre'  
  WHERE LOCATION.DISTRICT like 'kavre';
 
 
  UPDATE LOCATION 
  SET LOCATION.MUNICIPALITY = 'Kabre'  
  WHERE LOCATION.DISTRICT like 'Kabre';
 
 

  
  