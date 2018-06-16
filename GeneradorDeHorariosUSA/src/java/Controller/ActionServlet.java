package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DAO;
import DTO.Asignatura;
import DTO.Horario;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author JorgeDíaz
 */
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            int semestre = Integer.parseInt(request.getParameter("semestre")); // Recibir el semestre que se envió por GET
            String carrera = request.getParameter("carrera"); // Recibir la carrera que se envió por GET

            StringBuilder jspResponse = new StringBuilder(); // Almacenará el código HTML de los checkbox

            for (Asignatura asignatura : DAO.obtenerAsignaturas(carrera, semestre)) {
                StringBuilder newLine = new StringBuilder();
                String nombreAsignatura = asignatura.getNombre().substring(0, asignatura.getNombre().lastIndexOf("G"));
                newLine.append("<label><input type='checkbox' name='checkbox' value='").append(asignatura.getCodigo()).append(" ").append(nombreAsignatura).append("'>").append(asignatura.getCodigo()).append(" ").append(nombreAsignatura).append("</label><br>"); // Se arma cada checkbox
                if (!jspResponse.toString().contains(newLine.toString())) {
                    jspResponse.append(newLine.toString());
                }
            }
            out.println(jspResponse.toString()); // Se envia a AJAX
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String[] stringAsignaturas = request.getParameter("asignaturas").split("_"); // Recibir las asignaturas seleccionadas
            // Recibir todos los datos de las asignaturas
            LinkedList<Asignatura> asignaturas = new LinkedList<>();
            if (!stringAsignaturas[0].isEmpty()) {
                for (String asignatura : stringAsignaturas) {
                    asignatura = asignatura.substring(asignatura.indexOf(" ") + 1);
                    asignaturas.addAll(DAO.obtenerAsignaturas(asignatura));
                }
            }
            // Se generan los posibles horarios en tablas html
            Horario horario = new Horario();
            asignaturas.forEach(horario::añadirAsignatura);
            LinkedList<String[][]> horarios = horario.generarHorarios();

            StringBuilder jspResponse = new StringBuilder();
            for (int i = 0, hora = 7; i < horarios.size(); i++, hora = 7) {

                // Obteniendo cada uno de los horarios
                String[][] matrix = horarios.get(i);

                // Creando el código HTML de cada horario
                jspResponse.append("<h3> Opción # ")
                        .append(i + 1)
                        .append("</h3><table class=\"table table-bordered\">")
                        .append("<thead><tr>")
                        .append("<th>Hora</th><th>Lunes</th><th>Martes</th><th>Miércoles</th>")
                        .append("<th>Jueves</th><th>Viernes</th><th>Sábado</th>")
                        .append("</tr></thead>")
                        .append("<tbody>");

                for (String[] row : matrix) {
                    jspResponse.append("<tr><td>").append(hora++).append("</td>");
                    for (String element : row) {
                        jspResponse.append("<td>");
                        jspResponse.append(element == null ? " " : element.substring(0, element.lastIndexOf(" ")) + "<i> " + element.substring(element.lastIndexOf(" ") + 1, element.length()) + "</i>");
                        jspResponse.append("</td>");
                    }
                    jspResponse.append("</tr>");
                }
                jspResponse.append("</tbody></table>");
            }

            boolean isEmpty = true;
            if (!horarios.isEmpty()) {
                for (String[] row : horarios.get(0)) {
                    if (Arrays.stream(row).anyMatch(e -> e != null)) {
                        isEmpty = false;
                        break;
                    }
                }
            }

            out.println(jspResponse.toString().isEmpty() || isEmpty ? "<h3 style=\"color:red; text-align:center;\"> ¡ No existe ningún horario que contenga todas las asignaturas seleccionadas !</h3>" : jspResponse + "<br>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
