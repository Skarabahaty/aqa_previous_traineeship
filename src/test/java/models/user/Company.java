package models.user;

import org.json.JSONObject;

import java.util.Objects;

public class Company {
    public Company(JSONObject jsonObject) {
        this.bs = jsonObject.getString("bs");
        this.catchPhrase = jsonObject.getString("catchPhrase");
        this.name = jsonObject.getString("name");
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
}
