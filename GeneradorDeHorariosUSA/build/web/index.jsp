<!-- Author: Jorge Andrés Díaz Naranjo -->

<%@page import="java.io.IOException"%>
<%@page import="java.io.Reader"%>
<%@page import="java.io.FileReader"%>
<%@page import="DTO.Asignatura"%>
<%@page import="java.util.LinkedList"%>
<%@page import="DTO.Carrera"%>
<%@page import="DAO.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="http://code.jquery.com/jquery-latest.js"></script>

        <style type="text/css">

            @import url(https://fonts.googleapis.com/css?family=Acme);
            @import url(https://fonts.googleapis.com/css?family=ABeeZee);

            h1 { font-family: 'Acme'; }
            h4 { font-family: 'ABeeZee'; }
            p { margin: 10px; }
            h1 { font-style: italic; }
            .checkbox { margin-left: 10px; }
            .loader {
                border: 16px solid #f3f3f3;
                border-radius: 50%;
                border-top: 16px solid #3498db;
                width: 120px;
                height: 120px;
                -webkit-animation: spin 2s linear infinite; 
                animation: spin 2s linear infinite;
                position: absolute;
                top: 48%;
                left: 45%;
            }
            @-webkit-keyframes spin {
                0% { -webkit-transform: rotate(0deg); }
                100% { -webkit-transform: rotate(360deg); }
            }
            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }


        </style>

        <script>
            $(document).ready(function () {

                $('#submit').click(function () {
                    var carrera = $("#combobox option:selected").text();
                    var semestre = $("#semestre").val();

                    $.get('ActionServlet', {carrera: carrera, semestre: semestre}, function (response) {
                        $(".checkbox").empty();
                        $(".checkbox").append(response);

                        var checkboxes = $("input:checkbox").map(function () {
                            return this;
                        }).get();

                        var array_li = [];
                        $('li').each(function (i, li) {
                            array_li.push($(li));
                        });

                        for (var i = 0; i < checkboxes.length; i++) {
                            var text_cb = $(checkboxes[i]).val();
                            for (var j = 0; j < array_li.length; j++) {
                                if ($(array_li[j]).text() === text_cb) {
                                    $(checkboxes[i]).prop('checked', true);
                                }
                            }
                        }

                        $('input[name=checkbox]').change(function () {
                            var class_value = $(this).attr('value').replace(new RegExp(" ", 'g'), "_").replace(";", "").replace(".", "");
                            if ($(this).is(':checked')) {
                                $("ul").append("<li class='list-group-item " + class_value + "'>" + $(this).attr('value') + "</li>");
                            } else {
                                $("ul").find("." + class_value).remove();
                            }
                        });
                    });
                });

                $('#generar').click(function () {
                    $("#semifooter").append("<div class='loader'></div>");
                    var li_string = "";
                    $('li').each(function (i, li) {
                        li_string += $(li).text() + "_";
                    });

                    var asignaturas_string = li_string.slice(0, -1);
                    $.post('ActionServlet', {asignaturas: asignaturas_string}, function (response) {
                        $("#horarios").empty();
                        $("#horarios").append(response);
                        $("#semifooter").find(".loader").remove();
                    });
                });
            });
        </script>

        <title>Generador de Horarios</title>
    </head>

    <body>

        <div class="jumbotron text-center">
            <h1>Generador de Horarios</h1>
        </div>


        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading" > <h4>Ingrese sus Datos</h4> </div> <br>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-4">Carrera:</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="combobox" name="carrera">
                                        <%
                                            LinkedList<Carrera> listaCarreras = DAO.obtenerCarreras();
                                            for (Carrera carrera : listaCarreras) {
                                        %>

                                        <option><%=carrera.getNombre()%></option>

                                        <%}%>

                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-4">Semestre:</label>
                                <div class="col-sm-7">          
                                    <input align="center" type="number" class="form-control" name="semestre" id="semestre" min="2" max="10" required/>
                                </div>
                            </div>
                            <div class="form-group">        
                                <div class="col-sm-offset-5 col-sm-12">
                                    <button type="button" id="submit" class="btn btn-default">Aceptar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>


                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Asignaturas a Cursar</h4> </div>
                        <p>Seleccione todas las asignaturas que quiere cursar en el semestre seleccionado:</p>
                        <form>

                            <div class="checkbox">
                                <!-- Se actualiza mediante Javascript -->
                            </div>
                        </form>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h4>Asignaturas Seleccionadas</h4> </div>
                        <ul class="list-group">
                            <!-- Se actualiza mediante Javascript -->
                        </ul>
                    </div>
                </div>

            </div> <!-- end row -->
            <div id="semifooter">
                <button type="button" class="btn btn-primary center-block" id="generar">Generar</button>     
            </div>
            <div id="horarios" style="margin-top: 20px;">
                <!-- Se actualiza mediante Javascript -->
            </div>
        </div>


        <div id="footer">
            <div class="container">
                <p class="muted credit" style="color: #666666">¿Necesitas ayuda? <a href="#">Clic Aquí</a></p> 
                <p class="muted credit" style="color: #666666; text-align: right;">Aplicación web realizada por <a href="https://www.facebook.com/JorgeAndresDiazN" target="_blank">Jorge Díaz.</a></p>
            </div>
        </div>

    </body>

</html>