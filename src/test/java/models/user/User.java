package models.user;

import models.Returnable;

import java.util.Objects;

public class User implements Returnable {

    private final String website;
    private final String phone;
    private final String name;
    private final int id;
    private final String email;
    private final String username;
    private final Address address;
    private final Company company;

    public User(String website,
                String phone,
                String name,
                int id,
                String email,
                String username,
                Address address,
                Company company) {
        this.website = website;
        this.phone = phone;
        this.name = name;
        this.id = id;
        this.email = email;
        this.username = username;
        this.address = address;
        this.company = company;
    }

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
                address.equals(user.address) &&
                company.equals(user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(website, phone, name, id, email, username, address, company);
    }

    @Override
    public String toString() {
        return "User{" +
                "website='" + website + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", address=" + address +
                ", company=" + company +
                '}';
    }
}
