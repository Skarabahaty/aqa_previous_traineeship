package models.user;

import org.json.JSONObject;

import java.util.Objects;

public class User {

    public User(JSONObject jsonObject) {
        this.website = jsonObject.getString("website");
        this.phone = jsonObject.getString("phone");
        this.name = jsonObject.getString("name");
        this.id = jsonObject.getInt("id");
        this.email = jsonObject.getString("email");
        this.username = jsonObject.getString("username");
        this.adress = new Adress(jsonObject.getJSONObject("address"));
        this.company = new Company(jsonObject.getJSONObject("company"));
    }

    private final String website;
    private final String phone;
    private final String name;
    private final int id;
    private final String email;
    private final String username;
    private final Adress adress;
    private final Company company;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                website.equals(user.website) &&
                phone.equals(user.phone) &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                username.equals(user.username) &&
                adress.equals(user.adress) &&
                company.equals(user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(website, phone, name, id, email, username, adress, company);
    }
}
