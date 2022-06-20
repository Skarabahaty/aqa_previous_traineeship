package models.user;

import java.util.HashMap;
import java.util.Objects;

public class Address {

    public Address(Object address) {
        HashMap<String, Object> address1 = (HashMap<String, Object>) address;

        this.zipcode = (String) address1.getOrDefault("zipcode", null);
        this.suite = (String) address1.getOrDefault("suite", null);
        this.city = (String) address1.getOrDefault("city", null);
        this.street = (String) address1.getOrDefault("street", null);
        this.geo = new Geo(address1.getOrDefault("geo", null));
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
        Address address = (Address) o;
        return zipcode.equals(address.zipcode) &&
                suite.equals(address.suite) &&
                city.equals(address.city) &&
                street.equals(address.street) &&
                geo.equals(address.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipcode, suite, city, street, geo);
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipcode='" + zipcode + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", geo=" + geo +
                '}';
    }
}
