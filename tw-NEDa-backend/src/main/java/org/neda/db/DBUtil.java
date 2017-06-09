package org.neda.db;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import org.neda.entity.Earthquake;
import org.neda.entity.Location;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class DBUtil {

    private String baseUrl = "http://localhost:8666/neda";
    private RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        DBUtil dbUtil = new DBUtil();
        dbUtil.populateLocationAndEarthquakesTable();
    }

    private void populateLocationAndEarthquakesTable() {

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("earthquakes.csv").getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                try {
                    String line = scanner.nextLine();
                    String[] split = line.split(",");

                    Random random = new Random();

                    // Hit the endpoint
                    Location location = new Location();
                    location.setDistrict(split[5]);
                    location.setMunicipality(split[6]);

                    Earthquake earthquake = new Earthquake();
                    earthquake.setLocalizationId(random.nextLong());
                    earthquake.setLatitude(new Double(split[2]));
                    earthquake.setLongitude(new Double(split[3]));
                    earthquake.setMagnitude(new Double(split[4]));

                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date happenedOn = dateFormat.parse(split[0]);
                    earthquake.setHappenedOn(happenedOn);

                    EarthquakeAndLocation earthquakeAndLocation = new EarthquakeAndLocation(earthquake, location);

                    postObject(earthquakeAndLocation, "/earthquakes/e-l");
                    System.out.println("Inserted location: " + location.getLocationId());
                }
                catch (Exception e) {
                    System.out.println("failed this one");
                }
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void postObject(Object object, String endpointsUrl) {
        URI targetUrl = UriComponentsBuilder.fromUriString(baseUrl).path(endpointsUrl).build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Object> requestEntity = new HttpEntity<>(object, headers);

        try {
        restTemplate.exchange(
                    targetUrl, HttpMethod.POST, requestEntity,
                    String.class);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
