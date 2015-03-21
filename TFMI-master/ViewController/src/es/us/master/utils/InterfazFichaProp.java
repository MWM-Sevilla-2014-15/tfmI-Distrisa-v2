package es.us.master.utils;

import java.io.IOException;
import java.io.InputStream;

import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * Clase que encapsula los metodos de Gestion de la Fichas de Propiedades
 * @author Carlos Rodriguez Lopez
 */
public class InterfazFichaProp {
    //PROPIEDADES-------------------------------------------------
    /**
     * Archivo que recoge los parametros constantes utilizadas por el Sistema (constantes, formatos, etc.)
     */
    private static final String propFiles = "conf.properties";
    //MÉTODOS------------------------------------------------------

    /**
     * Metodo para extraer cadenas correspondiente a los Archivos de Propiedades
     * @param propiedad Clave de propiedad que se busca en listado de propiedades
     * @return texto bloque de texto
     */
    public static String getProp(String propiedad) {
        // Propiedad a extraer del archivo
        String texto = null;
        InputStream flujo=null;
        Properties prop = new Properties();
        try {
            // Se establece la conexi�n con el archivos propiedades de menu
            flujo = InterfazFichaProp.class.getClassLoader().getResourceAsStream(propFiles);
            if (flujo == null) {
                System.out.println("Sorry, unable to find " + propFiles);
            }
            //load a properties file from class path, inside static method
            prop.load(flujo);
            texto = prop.getProperty(propiedad);
        } catch (InvalidPropertiesFormatException e) {
            System.out.println("Error de formato del archivo de entrada. No es un XML valido.");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error de E/S");
            //e.printStackTrace();
        } finally {
            if (flujo != null) {
                try {
                    flujo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return texto;
    }
}
