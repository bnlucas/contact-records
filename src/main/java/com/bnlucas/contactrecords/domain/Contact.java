package com.bnlucas.contactrecords.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.language.Metaphone;

import java.util.Objects;

public class Contact {

    private static final Metaphone metaphone = new Metaphone();

    private long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonIgnore
    private String encodedFirstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonIgnore
    private String encodedLastName;

    private String company;

    @JsonIgnore
    private String encodedCompany;

    private String email;

    @JsonProperty("address1")
    private String addressOne;

    @JsonIgnore
    private String encodedAddressOne;

    @JsonProperty("address2")
    private String addressTwo;

    private String zip;

    private String city;

    @JsonIgnore
    private String encodedCity;

    @JsonProperty("state_long")
    private String stateLong;

    @JsonIgnore
    private String encodedStateLong;

    private String state;

    private String phone;

    public long getId() {
        return id;
    }

    public Contact setId(String id) {
        this.id = Long.valueOf(id);
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Contact setFirstName(String firstName) {
        this.firstName = firstName;
        this.encodedFirstName = metaphone.encode(firstName);

        return this;
    }

    public String getEncodedFirstName() {
        return encodedFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact setLastName(String lastName) {
        this.lastName = lastName;
        this.encodedLastName = metaphone.encode(lastName);

        return this;
    }

    public String getEncodedLastName() {
        return encodedLastName;
    }

    public String getCompany() {
        return company;
    }

    public Contact setCompany(String company) {
        this.company = company;
        this.encodedCompany = metaphone.encode(company);

        return this;
    }

    public String getEncodedCompany() {
        return encodedCompany;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public Contact setAddressOne(String addressOne) {
        this.addressOne = addressOne;

        if (addressOne != null) {
            // Remove all integers before encoding to make more generic metaphone token
            this.encodedAddressOne = metaphone.encode(addressOne.replaceAll("\\d",""));
        }

        return this;
    }

    public String getEncodedAddressOne() {
        return encodedAddressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public Contact setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
        return this;
    }

    public String getZip() {
        return zip;
    }

    public Contact setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Contact setCity(String city) {
        this.city = city;
        this.encodedCity = metaphone.encode(city);

        return this;
    }

    public String getEncodedCity() {
        return encodedCity;
    }

    public String getStateLong() {
        return stateLong;
    }

    public Contact setStateLong(String stateLong) {
        this.stateLong = stateLong;
        this.encodedStateLong = metaphone.encode(stateLong);
        return this;
    }

    public String getEncodedStateLong() {
        return encodedStateLong;
    }

    public Contact setEncodedStateLong(String encodedStateLong) {
        this.encodedStateLong = encodedStateLong;
        return this;
    }

    public String getState() {
        return state;
    }

    public Contact setState(String state) {
        this.state = state;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Contact setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", addressOne='" + addressOne + '\'' +
                ", addressTwo='" + addressTwo + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", stateLong='" + stateLong + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(encodedFirstName, encodedLastName, encodedAddressOne, zip, encodedCity, encodedStateLong, state, phone);
    }
}
