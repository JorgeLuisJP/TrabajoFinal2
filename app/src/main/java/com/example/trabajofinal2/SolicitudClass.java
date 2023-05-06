package com.example.trabajofinal2;

public class SolicitudClass {

    String usuario, nombre, latitud, longitud, pedido;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public SolicitudClass(String usuario, String nombre, String latitud, String longitud, String pedido) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.pedido = pedido;
    }

    public SolicitudClass() {
    }
}
