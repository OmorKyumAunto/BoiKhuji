package com.example.boikhuji;

public class User {
    public String name,email,nid,phone;
    public User(){

    }
    public User(String name, String email, String nid, String phone){
        this.name = name;
        this.email = email;
        this.nid=nid;
        this.phone= phone;
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

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
