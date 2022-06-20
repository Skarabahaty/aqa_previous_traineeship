package models.user;

import java.util.HashMap;
import java.util.Objects;

public class Geo {


    public Geo(Object geo) {
        HashMap<String, String> map = (HashMap<String, String>) geo;
        lng = map.getOrDefault("lng", null);
        lat = map.getOrDefault("lat", null);
    }

    private final String lng;
    private final String lat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return lng.equals(geo.lng) &&
                lat.equals(geo.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lng, lat);
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
