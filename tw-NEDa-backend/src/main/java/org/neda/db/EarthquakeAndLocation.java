package org.neda.db;

import org.neda.entity.Earthquake;
import org.neda.entity.Location;

public class EarthquakeAndLocation {
    private Earthquake earthquake;
    private Location location;

    public EarthquakeAndLocation() {
    }

    public EarthquakeAndLocation(Earthquake earthquake, Location location) {
        this.earthquake = earthquake;
        this.location = location;
    }

    public Earthquake getEarthquake() {
        return earthquake;
    }

    public void setEarthquake(Earthquake earthquake) {
        this.earthquake = earthquake;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
