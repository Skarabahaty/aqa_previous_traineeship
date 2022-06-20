package models.user;

import org.json.JSONObject;

import java.util.Objects;

public class Adress {
    public Adress(JSONObject adress) {

        this.zipcode = adress.getString("zipcode");
        this.suite = adress.getString("suite");
        this.city = adress.getString("city");
        this.street = adress.getString("street");
        this.geo = new Geo(adress.getJSONObject("geo"));
    }

    private final String zipcode;
    private final String suite;
    private final String city;
    private final String street;
    private final Geo geo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return zipcode.equals(adress.zipcode) &&
                suite.equals(adress.suite) &&
                city.equals(adress.city) &&
                street.equals(adress.street) &&
                geo.equals(adress.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipcode, suite, city, street, geo);
    }
}
