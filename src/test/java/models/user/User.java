package models.user;

import java.util.HashMap;
import java.util.Objects;

public class User  {


    private String website;
    private String phone;
    private String name;
    private int id;
    private String email;
    private String username;
    private Address address;
    private Company company;

    public User() {
    }

    public User(Object userObject) {
        HashMap<String, Object> map = (HashMap<String, Object>) userObject;
        website = (String) map.getOrDefault("website", null);
        phone = (String) map.getOrDefault("phone", null);
        name = (String) map.getOrDefault("name", null);
        id = (int) (long) map.getOrDefault("id", null);
        email = (String) map.getOrDefault("email", null);
        username = (String) map.getOrDefault("username", null);
        setAddress(map.getOrDefault("address", null));
        setCompany(map.getOrDefault("company", null));
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Object address) {
        this.address = new Address(address);
    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Object company) {
        this.company = new Company(company);
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
