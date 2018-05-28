package com.siteminder.email.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailContact {
    private String name;

    @Email
    @NotEmpty
    private String email;

    public EmailContact() {
    }

    public EmailContact(String email) {
        this.email = email.trim();
    }

    public EmailContact(String name, String email) {
        this.name = name.trim();
        this.email = email.trim();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailContact that = (EmailContact) o;

        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return name != null ? name + "<" + email + ">" : email;
    }
}
