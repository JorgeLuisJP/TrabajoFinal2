package com.example.trabajofinal2;

public class HelperClassAgente {


    String name, email, placa, username, password;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa){this.placa = placa; }
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public HelperClassAgente(String name, String email, String placa, String username, String password) {
        this.name = name;
        this.email = email;
        this.placa = placa;
        this.username = username;
        this.password = password;
    }
    public HelperClassAgente() {
    }


}
