package com.example.finalseizureyarab.Models;

public class User {

     String firstName,lastName, password, email
            , city, country,  gender ,  national_id
                            ,  phone,birth_day ,token;

     String et_1,et_2,et_3,et_4,et_5,et_6;

    public User(String et_1, String et_2, String et_3, String et_4, String et_5, String et_6) {
        this.et_1 = et_1;
        this.et_2 = et_2;
        this.et_3 = et_3;
        this.et_4 = et_4;
        this.et_5 = et_5;
        this.et_6 = et_6;
    }

    public String getEt_1() {
        return et_1;
    }

    public void setEt_1(String et_1) {
        this.et_1 = et_1;
    }

    public String getEt_2() {
        return et_2;
    }

    public void setEt_2(String et_2) {
        this.et_2 = et_2;
    }

    public String getEt_3() {
        return et_3;
    }

    public void setEt_3(String et_3) {
        this.et_3 = et_3;
    }

    public String getEt_4() {
        return et_4;
    }

    public void setEt_4(String et_4) {
        this.et_4 = et_4;
    }

    public String getEt_5() {
        return et_5;
    }

    public void setEt_5(String et_5) {
        this.et_5 = et_5;
    }

    public String getEt_6() {
        return et_6;
    }

    public void setEt_6(String et_6) {
        this.et_6 = et_6;
    }

    public User(String s2) {
    }

    public User(String preferencesString2, String preferencesString1, String preferencesString, String sharedPreferencesString, String sharedPreferencesString2, String string2, String s1, String sharedPreferencesString1, String string1, String s, String string) {
        this.token = token;
    }

    public User(String firstName, String lastName, String password, String email
            , String city, String country, String string) {

        this.firstName=firstName;
        this.lastName=lastName;
        this.password =password;
        this.email = email;
        this.city=city;
        this.country=country;
        this.gender=gender;
        this.national_id=national_id;
        this.phone=phone;
        this.token = token;
        this.birth_day=birth_day;
    }




    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }
}
