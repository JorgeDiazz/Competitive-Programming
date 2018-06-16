package DTO;

import java.io.Serializable;

public class Asignatura implements Serializable {

    private String codigo;
    private String grupo;
    private String nombre;
    private int horaInicio;
    private int horaFin;
    private int semestre;
    private int diaClase;
    private int codigoCarrera;

    // Cuando se extrae la información mediante la copia de uSergioArboleda
    public Asignatura(String informacion) {
        String[] datos = informacion.split("\t"), codigoGrupo = datos[0].split("-");
        codigo = codigoGrupo[0];
        grupo = codigoGrupo[1];
        nombre = datos[1];
        horaInicio = Integer.parseInt(datos[2].substring(0, datos[2].indexOf(':')));
        horaFin = Integer.parseInt(datos[3].substring(0, datos[3].indexOf(':')));
        //semestre
        diaClase = obtenerDiaClase(datos);
        //codigoCarrera
    }

    public Asignatura(String codigo, String grupo, String nombre, int horaInicio, int horaFin, int semestre, int diaClase, int codigoCarrera) {
        this.codigo = codigo;
        this.grupo = grupo;
        this.nombre = nombre.trim();
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.semestre = semestre;
        this.diaClase = diaClase;
        this.codigoCarrera = codigoCarrera;
    }

    private int obtenerDiaClase(String[] datos) {
        for (int i = 4; i < datos.length; i++) {
            if (datos[i].equals("X")) {
                return i - 4;
            }
        }
        return -1;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public int getDiaClase() {
        return diaClase;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public static String getSQLObtenerTodasPorNombre(String nombre) {
        return "SELECT * FROM asignatura WHERE nombre like '" + nombre + "%'";
    }

    public static String getSQLObtenerTodasPorSemestre(int codigoCarrera, int semestre) {
        return "SELECT * FROM asignatura WHERE codigo_carrera = " + codigoCarrera + " and semestre = " + semestre;
    }

    public String getSQLañadirAsignatura() {
        return "INSERT INTO asignatura VALUES ('" + codigo + "','" + grupo + "','" + nombre + "'," + horaInicio + "," + horaFin + "," + 5 + "," + diaClase + ",0);";
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.grupo;
    }

}
