package DTO;

import java.io.Serializable;

/**
 *
 * @author Jorge Díaz
 */
public class Dia implements Serializable {

    private int numero;
    private String nombre;

    public Dia(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
