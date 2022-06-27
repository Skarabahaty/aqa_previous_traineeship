package models.user;

import java.util.Objects;

public class Address {

    public Address(String zipcode,
                   String suite,
                   String city,
                   String street,
                   Geo geo) {
        this.zipcode = zipcode;
        this.suite = suite;
        this.city = city;
        this.street = street;
        this.geo = geo;
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
