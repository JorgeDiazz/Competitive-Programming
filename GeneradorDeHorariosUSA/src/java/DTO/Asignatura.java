package DTO;

import java.io.Serializable;

public class Asignatura implements Serializable {

    private String codigo;
    private int grupo;
    private String nombre;
    private int horaInicio;
    private int horaFin;
    private int semestre;
    private int diaClase;

    public Asignatura(String codigo, int grupo, String nombre, int horaInicio, int horaFin, int semestre, int diaClase) {
        this.codigo = codigo;
        this.grupo = grupo;
        this.nombre = nombre.trim();
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.semestre = semestre;
        this.diaClase = diaClase;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getGrupo() {
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

    public static String getSQLObtenerTodasPorCodigo(String codigoAsignatura) {
        return "select distinct codigo_asignatura, numero_grupo, nombre, hora_inicio, hora_fin, numero_dia_clase from grupo natural join (select * from asignatura_carrera natural join (select codigo as codigo_asignatura, nombre from asignatura) as t1) as t1 natural join clase where codigo_asignatura = " + codigoAsignatura;
    }

    public static String getSQLObtenerGruposPorSemestre(int codigoCarrera, int semestre) {
        return "select distinct codigo_asignatura, numero_grupo, nombre, hora_inicio, hora_fin, semestre, numero_dia_clase, codigo_carrera from grupo natural join (select * from asignatura_carrera natural join (select codigo as codigo_asignatura, nombre from asignatura) as t1) as t1 natural join clase where codigo_carrera = " + codigoCarrera + " and semestre = " + semestre + " order by nombre";
    }

    @Override
    public String toString() {
        return this.nombre + " G" + (this.grupo < 10 ? "0" + this.grupo : this.grupo);
    }

}
