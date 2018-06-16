package DTO;

import java.io.Serializable;

/**
 *
 * @author Jorge Díaz
 */
public class Carrera implements Serializable {

    private int codigo;
    private String nombre;

    public Carrera(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String getSQLObtenerTodos() {
        return "SELECT * FROM carrera";
    }
}
