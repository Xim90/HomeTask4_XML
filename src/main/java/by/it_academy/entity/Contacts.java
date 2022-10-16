package by.it_academy.entity;

import java.util.Objects;

public class Contacts {
    private String address;
    private String tel;
    private String email;
    private String url;

    public Contacts() {
    }

    public Contacts(String address, String tel, String email, String url) {
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "\tContacts:\n" +
                "\t\taddress: " + address + '\n' +
                "\t\ttel: " + tel + '\n' +
                "\t\temail: " + email + '\n' +
                "\t\turl: " + url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacts contacts)) return false;

        if (!Objects.equals(address, contacts.address)) return false;
        if (!Objects.equals(tel, contacts.tel)) return false;
        if (!Objects.equals(email, contacts.email)) return false;
        return Objects.equals(url, contacts.url);
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
