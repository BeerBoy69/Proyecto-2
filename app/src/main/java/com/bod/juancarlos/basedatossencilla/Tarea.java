package com.bod.juancarlos.basedatossencilla;

public class Tarea {
    private int mID,foto,dificultad;
    private String nombre,fecha,hora,categoria;
    private boolean completado;

    /*Elementos que componen la base de datos tales como la foto, la dificultad, la categoria, la hora, la fecha,*/
    /*entre otros. Tambien estan los tipos de datos de los mismos.*/
    public Tarea(){}

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Tarea(int mID, String nombre, String fecha, String hora, boolean completado) {
        this.mID = mID;
        this.nombre = nombre;
        this.fecha = fecha;
        this.completado = completado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}
