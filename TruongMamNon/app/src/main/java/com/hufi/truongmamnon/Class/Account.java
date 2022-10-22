package com.hufi.truongmamnon.Class;

public class Account {
    int id;
    String email;
    String password;
    int quyen;

    public Account(int id, String email, String password, int quyen) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.quyen = quyen;
    }

    public Account(String email, String password, int quyen) {
        this.email = email;
        this.password = password;
        this.quyen = quyen;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", quyen=" + quyen +
                '}';
    }
}
