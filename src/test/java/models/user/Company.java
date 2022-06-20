package models.user;

import java.util.HashMap;
import java.util.Objects;

public class Company {


    public Company(Object company) {

        HashMap<String, Object> map = (HashMap<String, Object>) company;
        bs = (String) map.getOrDefault("bs", null);
        catchPhrase = (String) map.getOrDefault("catchPhrase", null);
        name = (String) map.getOrDefault("name", null);
    }

    private final String bs;
    private final String catchPhrase;
    private final String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return bs.equals(company.bs) &&
                catchPhrase.equals(company.catchPhrase) &&
                name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bs, catchPhrase, name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "bs='" + bs + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
