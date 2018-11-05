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
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/horarios", "postgres", "pass");
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
            ResultSet result = statement.executeQuery(Asignatura.getSQLObtenerGruposPorSemestre(codigoCarrera, semestre));
            while (result.next()) {
                listaAsignaturas.add(new Asignatura(result.getString(1), result.getInt(2), result.getString(3), result.getInt(4), result.getInt(5), result.getInt(6), result.getInt(7)));
            }
        } catch (SQLException ex) {
        }

        return listaAsignaturas;
    }

    public static LinkedList<Asignatura> obtenerAsignaturas(String codigoAsignatura) {
        LinkedList<Asignatura> listaAsignaturas = new LinkedList<>();
        try {
            ResultSet result = statement.executeQuery(Asignatura.getSQLObtenerTodasPorCodigo(codigoAsignatura));
            while (result.next()) { 
                Asignatura asig = new Asignatura(result.getString(1), result.getInt(2), result.getString(3), result.getInt(4), result.getInt(5), 0, result.getInt(6));
                listaAsignaturas.add(asig);
            }
        } catch (SQLException ex) {
        }

        return listaAsignaturas;
    }

}
