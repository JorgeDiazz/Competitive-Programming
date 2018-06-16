package DTO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class Horario {

    private final int TOTAL_HORAS;
    private final int TOTAL_DIAS;
    private LinkedList<Asignatura> listaAsignaturas;
    private LinkedList<String[][]> listaHorarios;
    private HashMap<String, Integer> horasMateria;

    public Horario() {
        this.TOTAL_HORAS = 14;
        this.TOTAL_DIAS = 6;
        this.listaAsignaturas = new LinkedList<>();
        this.listaHorarios = new LinkedList<>();
        this.horasMateria = new HashMap<>();
    }

    public void añadirAsignatura(Asignatura asignatura) {
        listaAsignaturas.add(asignatura);
    }

    public LinkedList<String[][]> generarHorarios() {
        // Se genera la matriz con todos los horarios especificados
        Object[][] matriz = new Object[TOTAL_HORAS][TOTAL_DIAS];
        listaAsignaturas.forEach(asignatura -> {
            for (int i = asignatura.getHoraInicio() - 7; i <= asignatura.getHoraFin() - 7; i++) {
                if (matriz[i][asignatura.getDiaClase()] == null) matriz[i][asignatura.getDiaClase()] = new LinkedList<>();
                ((LinkedList) matriz[i][asignatura.getDiaClase()]).add(asignatura);
                // Contar las horas de cada materia
                if (!horasMateria.containsKey(asignatura.toString())) horasMateria.put(asignatura.toString(), 0);
                horasMateria.replace(asignatura.toString(), horasMateria.get(asignatura.toString()) + 1);
            }
        });

        generarHorarios(matriz, new String[TOTAL_HORAS][TOTAL_DIAS], new LinkedList<>(), 0, 0);
        return listaHorarios;
    }

    private void generarHorarios(Object[][] matrizHorarios, String[][] posibleHorario, LinkedList<String> materiasUsadas, int lastI, int lastJ) {

        for (int j = lastJ; j < TOTAL_DIAS; j++) {
            for (int i = lastI; i < TOTAL_HORAS; i++) {
                if (matrizHorarios[i][j] != null) {
                    LinkedList<Asignatura> listaClases = (LinkedList) matrizHorarios[i][j];
                    for (Asignatura clase : listaClases) {
                        if (!materiasUsadas.contains(clase.getNombre())) {
                            String[][] tmpPosibleHorario = new String[matrizHorarios.length][matrizHorarios[i].length];
                            // Copiar la matriz para cambiar la dirección de memoria
                            for (int index = 0; index < tmpPosibleHorario.length; index++) 
                                System.arraycopy(posibleHorario[index], 0, tmpPosibleHorario[index], 0, posibleHorario[i].length);
                            // Poner la clase en los horarios que aparezca
                            for (int row = 0; row < matrizHorarios.length; row++) {
                                for (int column = 0; column < matrizHorarios[row].length; column++) {
                                    if (matrizHorarios[row][column] != null) {
                                        LinkedList<Asignatura> listaPosiblesClases = (LinkedList) matrizHorarios[row][column];
                                        for (Asignatura asignatura : listaPosiblesClases) {
                                            if (asignatura.toString().equals(clase.toString())) {
                                                tmpPosibleHorario[row][column] = clase.toString(); // Añadir la clase
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            LinkedList<String> tmpMateriasUsadas = new LinkedList<>(materiasUsadas);
                            tmpMateriasUsadas.add(clase.getNombre()); // Añadiendo la clase como usada
                            generarHorarios(matrizHorarios, tmpPosibleHorario, tmpMateriasUsadas, i + 1, j); // Poner otras clases
                        }
                    }
                }
            }
        }
        // Verificar que sea un horario válido para visualizar
        if (!tieneRepeticion(posibleHorario) && esCompleto(posibleHorario)) {
            String[][] horario = new String[14][6];
            for (int i = 0; i < horario.length; i++) 
                System.arraycopy(posibleHorario[i], 0, horario[i], 0, horario[i].length);
            listaHorarios.add(horario); // Añadiendo el horario
        }
    }

    private boolean tieneRepeticion(String[][] posibleHorario) {
        return listaHorarios.stream().anyMatch(horario -> Arrays.deepEquals(posibleHorario, horario));
    }

    private boolean esCompleto(String[][] horario) {
        HashMap<String, Integer> horasMateriaPuesta = new HashMap<>();
        HashMap<String, Boolean> tieneAparicion = new HashMap<>();
        listaAsignaturas.forEach(asignatura -> tieneAparicion.put(asignatura.getNombre(), false));
        // Verificar que aparezcan todas las asignaturas dentro del posible horario
        Arrays.stream(horario).forEach(row -> Arrays.stream(row).filter(element -> element != null)
                .forEach(element -> {
                    if (!horasMateriaPuesta.containsKey(element)) horasMateriaPuesta.put(element, 0);
                    horasMateriaPuesta.replace(element, horasMateriaPuesta.get(element) + 1);
                    tieneAparicion.entrySet().stream()
                            .filter(entry -> (element.contains(entry.getKey())))
                            .forEach(entry -> tieneAparicion.replace(entry.getKey(), true));
                }));

        return tieneAparicion.entrySet().stream().noneMatch(entry -> !entry.getValue()) && horasMateriaPuesta.entrySet().stream().noneMatch(entry -> horasMateria.containsKey(entry.getKey()) && !Objects.equals(horasMateriaPuesta.get(entry.getKey()), horasMateria.get(entry.getKey())));
    }

}
