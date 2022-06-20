package models.user;

import org.json.JSONObject;

import java.util.Objects;

public class Geo {
    public Geo(JSONObject geo) {
        this.lng = geo.getString("lng");
        this.lat = geo.getString("lat");
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
}
