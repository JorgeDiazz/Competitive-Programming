package DTO;

import java.util.LinkedList;

public class Test {

    public static void main(String[] args) {

        Horario horario = new Horario();

        String[] datos = ("SIST0008-G01	SIV;BASE DE DATOS G01	9:00:00	10:59:59	X	 	 	 	 	 		\n"
                + "SIST0008-G01	SIV;BASE DE DATOS G01	9:00:00	10:59:59	 	 	X	 	 	 		\n"
                + "SIST0008-G02	SIV;BASE DE DATOS G02	9:00:00	10:59:59	 	 	 	 	X	 		\n"
                + "SIST0008-G02	SIV;BASE DE DATOS G02	11:00:00	12:59:59	X	 	 	 	 	 		\n"
                + "SIST0008-G03	SIV;BASE DE DATOS G03	11:00:00	12:59:59	 	 	X	 	 	 		\n"
                + "SIST0008-G03	SIV;BASE DE DATOS G03	11:00:00	12:59:59	 	 	 	 	X	 		\n"
                + "SIST0071-G01	SIV;REDES DE COMPUTACIÓN G01	9:00:00	10:59:59	 	 	 	X	 	 		\n"
                + "SIST0071-G01	SIV;REDES DE COMPUTACIÓN G01	9:00:00	10:59:59	 	X	 	 	 	 		\n"
                + "SIST0071-G02	SIV;REDES DE COMPUTACIÓN G02	14:00:00	15:59:59	X	 	 	 	 	 		\n"
                + "SIST0071-G02	SIV;REDES DE COMPUTACIÓN G02	14:00:00	15:59:59	 	 	X	 	 	 		\n"
                + "SIST1006-G01	SIV;SISTEMAS MULTIMEDIALES G01	11:00:00	12:59:59	 	 	 	X	 	 		\n"
                + "SIST1006-G01	SIV;SISTEMAS MULTIMEDIALES G01	11:00:00	12:59:59	 	X	 	 	 	 		\n"
                + "SIST1006-G02	SIV;SISTEMAS MULTIMEDIALES G02	9:00:00	10:59:59	X	 	 	 	 	 		\n"
                + "SIST1006-G02	SIV;SISTEMAS MULTIMEDIALES G02	9:00:00	10:59:59	 	 	X	 	 	 	\n"
                + "IELC0006-G01	IEV-SIV;ONDAS Y CAMPOS ELECTROMAG. G01	7:00:00	8:59:59	 	 	 	 	X	 		\n"
                + "IELC0006-G01	IEV-SIV;ONDAS Y CAMPOS ELECTROMAG. G01	7:00:00	8:59:59	 	 	X	 	 	 		\n"
                + "IELC0006-G02	IEV-SIV;ONDAS Y CAMPOS ELECTROMAG. G02	11:00:00	12:59:59	 	 	X	 	 	 		\n"
                + "IELC0006-G02	IEV-SIV;ONDAS Y CAMPOS ELECTROMAG. G02	11:00:00	12:59:59	X	 	 	 	 	 		\n"
                + "IELC0006-G03	IEV-SIV;ONDAS Y CAMPOS ELECTROMAG. G03	14:00:00	15:59:59	 	 	 	X	 	 		\n"
                + "IELC0006-G03	IEV-SIV;ONDAS Y CAMPOS ELECTROMAG. G03	14:00:00	15:59:59	X	 	 	 	 	 	\n"
                + "INDU0021-G01	INV;INGENIERÍA FINANCIERA G01	9:00:00	10:59:59	 	 	 	X	 	 		\n"
                + "INDU0021-G01	INV;INGENIERÍA FINANCIERA G01	9:00:00	10:59:59	X	 	 	 	 	 		\n"
                + "INDU0021-G02	INV;INGENIERÍA FINANCIERA G02	11:00:00	12:59:59	 	 	 	X	 	 		\n"
                + "INDU0021-G02	INV;INGENIERÍA FINANCIERA G02	11:00:00	12:59:59	X	 	 	 	 	 		\n"
                + "INDU0021-G03	INV;INGENIERÍA FINANCIERA G03	14:00:00	15:59:59	 	 	 	X	 	 		\n"
                + "INDU0021-G03	INV;INGENIERÍA FINANCIERA G03	14:00:00	15:59:59	 	X	 	 	 	 		\n"
                + "INDU0021-G04	INV;INGENIERÍA FINANCIERA G04	16:00:00	17:59:59	 	X	 	 	 	 		\n"
                + "INDU0021-G04	INV;INGENIERÍA FINANCIERA G04	16:00:00	17:59:59	 	 	 	X	 	 \n"
                + "DPMT0048-G01	DPMT;MATEMÁTICAS ESPECIALES G01	7:00:00	8:59:59	X	 	 	 	 	 		\n"
                + "DPMT0048-G01	DPMT;MATEMÁTICAS ESPECIALES G01	7:00:00	8:59:59	 	 	 	 	X	 		\n"
                + "DPMT0048-G02	DPMT;MATEMÁTICAS ESPECIALES G02	7:00:00	8:59:59	X	 	 	 	 	 		\n"
                + "DPMT0048-G02	DPMT;MATEMÁTICAS ESPECIALES G02	7:00:00	8:59:59	 	 	 	 	X	 ").split("\n");

        for (String dato : datos) {
            Asignatura asignatura = new Asignatura(dato);
            System.out.println(asignatura.getSQLañadirAsignatura());
        }

    }

}
