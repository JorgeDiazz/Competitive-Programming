package DAO;

import DTO.Asignatura;
import DTO.Carrera;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 *
 * @author Jorge Díaz
 */
public class DAO {

    private static Statement statement;

    static {
        try {
            /* HEROKU
            try {
            String host = "ec2-75-101-142-91.compute-1.amazonaws.com";
            String dataBase = "d97l4jpd7eb6me";
            String user = "tfqqifghgfxxay";
            String port = "5432";
            String password = "212e310953ca9bbe1f184da1ea684e26676884f618e3bb1f12aa19678f405016";
            String URL = "jdbc:postgresql://" + host + ":" + port + "/" + dataBase + "?sslmode=require";
            Connection conexion = DriverManager.getConnection(URL, user, password);
            statement = conexion.createStatement();
            } catch (SQLException ex) {
            }
             */

 /* LOCALHOST */
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/base?charSet=UTF-8", "postgres", "pass");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {

        }
    }

    public static LinkedList<Carrera> obtenerCarreras() {
        LinkedList<Carrera> listaCarreras = new LinkedList<>();
        try {
            ResultSet result = statement.executeQuery(Carrera.getSQLObtenerTodos());
            while (result.next()) {
                listaCarreras.add(new Carrera(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException ex) {
        }
        return listaCarreras;
    }

    public static LinkedList<Asignatura> obtenerAsignaturas(String carrera, int semestre) {
        LinkedList<Asignatura> listaAsignaturas = new LinkedList<>();
        try {
            LinkedList<Carrera> listaCarreras = obtenerCarreras(); // Obtener todas las carreras y conocer el codigo de la carrera deseada
            int codigoCarrera = listaCarreras.stream().filter(x -> x.getNombre().equals(carrera)).collect(Collectors.toList()).get(0).getCodigo();
            ResultSet result = statement.executeQuery(Asignatura.getSQLObtenerTodasPorSemestre(codigoCarrera, semestre));

            while (result.next()) {
                int semestreAsignatura = result.getInt(6);
                if (semestreAsignatura == semestre) {
                    listaAsignaturas.add(new Asignatura(result.getString(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5), semestreAsignatura, result.getInt(7), result.getInt(8)));
                }
            }
        } catch (SQLException ex) {
        }

        return listaAsignaturas;
    }

    public static LinkedList<Asignatura> obtenerAsignaturas(String nombre) {
        LinkedList<Asignatura> listaAsignaturas = new LinkedList<>();
        try {
            ResultSet result = statement.executeQuery(Asignatura.getSQLObtenerTodasPorNombre(nombre));
            while (result.next()) {
                listaAsignaturas.add(new Asignatura(result.getString(1), result.getString(2), nombre, result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8)));
            }
        } catch (SQLException ex) {
        }

        return listaAsignaturas;
    }

}
