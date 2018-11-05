<!-- Author: Jorge Andrés Díaz Naranjo -->
<!-- Disclaimer, lo creó un estudiante, es un simulador, contador de visitas, busca mejorar el proceso de armar el horario sin demora, no controla cupos, es una prueba piloto, no compromete la escuela ni el horario definitivo que se generará por SAP, encuesta: parecio util, que cambiaria, que agregaria, disminución de tiempo?, mostrar primeros 10, y pedir si quiere más, no es el horario definitivo -->
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
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


        <style type="text/css">

            @import url(https://fonts.googleapis.com/css?family=Acme);
            @import url(https://fonts.googleapis.com/css?family=ABeeZee);

            h1 { font-family: 'Acme'; }
            h4 { font-family: 'ABeeZee'; }
            p { margin: 10px; }
            h1 { font-style: italic; }
            #vineta { font-size: 14px; }
            #condiciones { text-align: justify; }
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
            .video-container {
                position: relative;
                padding-bottom: 56.25%;
                padding-top: 30px; height: 0; overflow: hidden;
            }

            .video-container iframe,
            .video-container object,
            .video-container embed {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
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

                $('#myModal').modal('toggle');
                $('#myModal').modal('show');
                $('#encuesta').attr("disabled", true);

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
                            var class_value = $(this).attr('value').replace(new RegExp(" ", 'g'), "_").replace(/\+/g, "").replace(/\./g, "").replace(/\,/g, "");
                            if ($(this).is(':checked')) {
                                $("ul").append("<li class='list-group-item " + class_value + "'>" + $(this).attr('value') + "</li>");
                            } else {
                                $("ul").find("." + class_value).remove();
                            }
                        });
                    });
                });

                $('#generar').click(function () {
                    $('#generar').attr("disabled", true);
                    $('#encuesta').attr("disabled", false);
                    $("#semifooter").append("<div class='loader'></div>");
                    var li_string = "";
                    $('li').each(function (i, li) {
                        li_string += $(li).text() + "_";
                    });

                    var asignaturas_string = li_string.slice(0, -1);
                    $.post('ActionServlet', {asignaturas: asignaturas_string}, function (response) {
                        $("#horarios").empty();
                        $("#horarios").append(response);
                        $('#generar').attr("disabled", false);
                        $("#semifooter").find(".loader").remove();
                        alert('Por favor llena la encuesta después de saber que horario elegir :)');
                    });
                });
            });
        </script>

        <title>Simulador Generador de Horarios</title>
    </head>

    <body>

        <div class="jumbotron text-center">
            <h1>Simulador Generador de Horarios</h1>
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
                                    <input align="center" type="number" class="form-control" name="semestre" id="semestre" min="1" max="10" required/>
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

            </div> 

            <div id="semifooter" style="text-align: center">                
                <button onclick="location.href = 'https://goo.gl/forms/vju84e8cdN9xMjUm2'" type="button" class="btn btn-success" id="encuesta" style="margin-right: 10px">Encuesta</button>
                <button type="button" class="btn btn-primary" style="margin-left: 10px" id="generar">Generar</button>     
            </div>

            <div id="horarios" style="margin-top: 20px;">
                <!-- Se actualiza mediante Javascript -->
            </div>
        </div>


        <div id="footer">
            <div class="container">
                <p class="muted credit" style="color: #666666">¿Necesitas ayuda? <a href="" data-toggle="modal" data-target="#myModal2">Clic Aquí</a></p>
                <p class="muted credit" style="color: #666666; text-align: right;">Aplicación web realizada por <a href="https://www.facebook.com/JorgeAndresDiazN" target="_blank">Jorge Díaz.</a></p>
            </div>
        </div>

        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" style="background-color: #FFA500">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title"><span class="glyphicon glyphicon-warning-sign"></span> &nbsp; Atención</h4>
                    </div>
                    <div class="modal-body">
                        <p><b>Estimado Estudiante, no olvide tener en cuenta que este simulador:</b></p><br>
                        <ul>                            
                            <li><p id="condiciones">Busca mejorar el proceso de elaboración de su horario.</p></li>
                            <li><p id="condiciones">Se encuentra en una prueba piloto y no tiene en cuenta los cupos disponibles en cada asignatura.</p></li>
                            <li><p id="condiciones">Está elaborado por un estudiante de la Universidad Sergio Arboleda.</p> </li>
                            <li><p id="condiciones">No compromete de ninguna forma a la Universidad Sergio Arboleda y/o sus escuelas.</p> <br></li>
                        </ul>
                        <div class="modal-footer">
                            <p style="text-align: center"><i><b>El proceso de matrícula formal se realiza en la plataforma SAP</b></i></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="myModal2" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times</button>
                        <h4 class="modal-title">Pasos Para Generar Tu Horario</h4>
                    </div>
                    <div class="modal-body">
                        <div class="video-container">
                            <iframe width="560" height="315" src="https://www.youtube.com/embed/cK93HKaF6bc" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script type="text/javascript" src="https://counter4.whocame.ovh/private/counter.js?c=lpbbfc4498lk382aeudsfdtpaz22mlgl&down=async" async></script><noscript><a href="https://www.contadorvisitasgratis.com" title="Contador de Visitas"><img src="https://counter4.whocame.ovh/private/contadorvisitasgratis.php?c=lpbbfc4498lk382aeudsfdtpaz22mlgl" border="0" title="Contador de Visitas" alt="Contador de Visitas"></a></noscript><a title="Contador de Visitas"><img src="https://counter4.whocame.ovh/private/contadorvisitasgratis.php?c=lpbbfc4498lk382aeudsfdtpaz22mlgl" border="0" title="Contador de Visitas" alt="Contador de Visitas"></a>                                


    </body>

</html>