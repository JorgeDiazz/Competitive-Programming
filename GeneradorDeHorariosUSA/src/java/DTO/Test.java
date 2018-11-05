package DTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String URL = "C:\\Users\\JorgeDíaz\\Desktop\\Reporte de planes ELECTIVAS ECEI.csv";
        BufferedReader in = new BufferedReader(new FileReader(URL));

        String line;
        in.readLine();
        while ((line = in.readLine()) != null) {

            String[] datos = line.split(";");

            /* ASIGNATURA 
            System.out.println("INSERT INTO asignatura VALUES(" + datos[5] + ",'" + datos[4] + "');");
             */
 /* ASIGNATURA_CARRERA  */
            String semestre = datos[3].replace("Ciclo ", "");
            System.out.println("INSERT INTO asignatura_carrera VALUES(" + datos[5] + "," + datos[0] + "," + semestre + ");");

            /* GRUPO  
            String grupo = datos[3].substring(datos[3].length() - 2);
            System.out.println("INSERT INTO grupo VALUES(" + grupo + "," + datos[35] + ");");
             */

 /* CLASE 
            int inicio = Integer.parseInt(datos[8].substring(0, datos[8].indexOf(':')));
            if (datos[8].contains("p") && inicio != 12) {
                inicio += 12;
            }

            int fin = Integer.parseInt(datos[9].substring(0, datos[9].indexOf(':'))) + 1;
            if (datos[9].contains("p") && fin != 12 && fin != 13) {
                fin += 12;
            }
            if (fin == 12) {
                fin--;
            }

            int numDia = 0;
            for (int i = 10; i < 16; i++) {
                if (datos[i].equals("true")) {
                    numDia = i - 10;
                }
            }

            String grupo = datos[3].substring(datos[3].length() - 2);
            System.out.println("INSERT INTO clase(numero_grupo, codigo_asignatura, hora_inicio, hora_fin, numero_dia_clase) VALUES(" + grupo + "," + datos[35] + "," + inicio + "," + fin + "," + numDia + ");");
             */
        }


        /*
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
         */
    }

}
