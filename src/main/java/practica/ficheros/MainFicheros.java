package practica.ficheros;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainFicheros {
    // 1. Crea un proyecto Java llamado GesƟonFicheros.
    // 2. Dentro del proyecto, crea un paquete practica.ficheros.
    // 3. En ese paquete, crea una clase principal MainFicheros.
    public static void main(String[] args) {
        // 4. Crea un objeto File que represente la carpeta datos. Si no existe, debe crearse.
        File carpetaDatos = new File("src/main/java/practica/ficheros/datos");

        // 14. Crea un fichero log.txt en la carpeta datos y escribe un registro con la fecha y hora de
        //cada operación realizada.
        File archivoLog = new File(carpetaDatos, "log.txt");

        if (!carpetaDatos.exists()) {
            carpetaDatos.mkdirs();
            escribirLog(marcaDeTiempoConMensaje("Se ha creado la carpeta de datos en la ruta: " + carpetaDatos.getPath()), archivoLog);
        }
        // 5. Crea dentro de datos un fichero de texto llamado usuarios.txt.
        File archivoDatos = new File(carpetaDatos.getPath(), "usuarios.txt");
        try {
            boolean creadoArchivoDatos = archivoDatos.createNewFile();
            if (creadoArchivoDatos) escribirLog(marcaDeTiempoConMensaje("Se ha creado el archivo de datos de los usuarios en la ruta: " + archivoDatos.getPath()), archivoLog);
            // 6. Comprueba si el fichero usuarios.txt existe, y muestra un mensaje apropiado.
            if (archivoDatos.exists()) {
                escribirLog(marcaDeTiempoConMensaje("El archivo " + archivoDatos.getName() + " existe."), archivoLog);
            } else {
                escribirLog(marcaDeTiempoConMensaje("El archivo " + archivoDatos.getName() + " no existe."), archivoLog);
            }
        } catch (IOException e) {
            escribirLog(marcaDeTiempoConMensaje("Ha habido un error durante la creación del archivo " + archivoDatos.getName() + "."), archivoLog);
        }
        // 7. Escribe en usuarios.txt los nombres de 5 usuarios (uno por línea).
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDatos.getPath()))) {
            String[] usuarios = {"Jepeto", "Mamerto", "Jorgito", "Jaimito", "Gerardo"};
            for  (int i = 0; i < usuarios.length; i++) {
                bw.write(usuarios[i]);
                bw.newLine();
                escribirLog(marcaDeTiempoConMensaje("Usuario " + usuarios[i] + " agregado al archivo de usuarios " + archivoDatos.getName() + "."), archivoLog);
            }
        } catch (Exception e) {
            escribirLog(marcaDeTiempoConMensaje("Ha habido un error durante la creacioón del archivo " + archivoDatos.getName() + "."), archivoLog);
        }
        // 8. Lee y muestra por pantalla el contenido de usuarios.txt.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos.getPath()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            escribirLog(marcaDeTiempoConMensaje("Ha habido un error durante la lectura del archivo " + archivoDatos.getName() + "."), archivoLog);
        }

        // 9. Añade dos usuarios más al final del fichero sin sobrescribir el contenido.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDatos.getPath()))) {
            escribirLog(marcaDeTiempoConMensaje("Eliminados todos los usuarios del archivo " + archivoDatos.getName() + "."), archivoLog);
            String[] usuarios = {"Ruperto", "Gustavo"};
            for  (int i = 0; i < usuarios.length; i++) {
                bw.write(usuarios[i]);
                bw.newLine();
                escribirLog(marcaDeTiempoConMensaje("Usuario " + usuarios[i] + " agregado al archivo de usuarios " + archivoDatos.getName() + "."), archivoLog);
            }
        } catch (IOException e) {
            escribirLog(marcaDeTiempoConMensaje("Ha habido un error durante la escritura del archivo " + archivoDatos.getName() + "."), archivoLog);
        }

        // 10. Muestra el número total de líneas (usuarios) en el fichero.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos.getPath()))) {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null) {
                contador++;
            }
            System.out.println("\nHay " + contador + " líneas.");
        } catch (IOException e) {
            escribirLog(marcaDeTiempoConMensaje("Ha habido un error durante la lectura del archivo " + archivoDatos.getName() + "."), archivoLog);
        }

        // 11. Busca en el fichero si existe un usuario llamado "Carlos". Muestra un mensaje según se
        //encuentre o no.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos.getPath()))) {
            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null && !encontrado) {
                if (linea.contains("Carlos")) {
                    encontrado = true;
                    escribirLog(marcaDeTiempoConMensaje("Usuario Carlos encontrado." ), archivoLog);
                }
            }

            if (encontrado) {
                System.out.println("\nHemos encontrado al usuario Carlos.");
            } else {
                System.out.println("\nNo hemos encontrado al usuario Carlos.");
            }
        } catch (IOException e) {
            escribirLog(marcaDeTiempoConMensaje("Ha habido un error durante la lectura del archivo " + archivoDatos.getName() + "."), archivoLog);
        }

        // 12. Copia el fichero usuarios.txt en un nuevo fichero usuarios_copia.txt.
        File archivoBackup = new File(carpetaDatos, "usuarios_copia.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos.getPath()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoBackup))) {
            String linea;
            StringBuilder texto = new StringBuilder();
            escribirLog(marcaDeTiempoConMensaje("Leyendo datos del archivo " + archivoDatos.getName() + "."), archivoLog);
            while ((linea = br.readLine()) != null) {
                texto.append(linea);
            }
            escribirLog(marcaDeTiempoConMensaje("Escribiendo el contenido del archivo " + archivoDatos.getName() + " al archivo " + archivoBackup.getName() + "."), archivoLog);
            bw.write(String.valueOf(texto));
        } catch (IOException e) {
            escribirLog(marcaDeTiempoConMensaje("Ha habido un error durante la copia del archivo " + archivoDatos.getName() + "."), archivoLog);
        }

        // 13. Renombra el fichero usuarios_copia.txt a usuarios_backup.txt.
        File archivoBackupRenombrado = new File(carpetaDatos, "usuarios_backup.txt");
        boolean renombrado = archivoBackup.renameTo(archivoBackupRenombrado);
        if (renombrado) {
            System.out.println("Archivo renombrado correctamente.");
            escribirLog(marcaDeTiempoConMensaje("Se ha renombrado el archivo " + archivoBackup.getName() + " a " + archivoBackupRenombrado.getName() + "."), archivoLog);
        } else {
            System.out.println("No se ha podido renombrar el archivo.");
            escribirLog(marcaDeTiempoConMensaje("No se ha podido renombrar el archivo " + archivoBackup.getName() + " a " + archivoBackupRenombrado.getName() + "."), archivoLog);
        }

        // 16. Llama al método anterior para borrar usuarios_backup.txt.
        if (borrarArchivo(archivoBackupRenombrado)) escribirLog(marcaDeTiempoConMensaje("Se ha eliminado el archivo " + archivoBackupRenombrado.getName() + "."), archivoLog);
        else escribirLog(marcaDeTiempoConMensaje("No se ha podido eliminar el archivo " + archivoBackupRenombrado.getName() + "."), archivoLog);

        // 17. Crea un objeto File que represente un directorio reportes. Si no existe, créalo.
        File carpetaReportes = new File("src/main/java/practica/ficheros/reportes");
        if (!carpetaReportes.exists()) {
            escribirLog(marcaDeTiempoConMensaje("Carpeta " + carpetaReportes.getName()) + "no existe.", archivoLog);
            if (carpetaReportes.mkdirs()) {
                escribirLog(marcaDeTiempoConMensaje("Carpeta " + carpetaReportes.getName()) + "creada correctamente.", archivoLog);
            } else {
                escribirLog(marcaDeTiempoConMensaje("No se ha podido crear la carpeta " + carpetaReportes.getName()) + " correctamente.", archivoLog);
            }
        } else escribirLog(marcaDeTiempoConMensaje("Carpeta " + carpetaReportes.getName()) + "existe.", archivoLog);

        // 18. Muestra la lista de todos los ficheros de la carpeta datos.
        File[] archivos = carpetaDatos.listFiles();
        System.out.println("\nArchivos de la carpeta " + carpetaDatos.getName() +":");
        escribirLog(marcaDeTiempoConMensaje("Mostrando archivos de la carpeta " + carpetaDatos.getName() + "."), archivoLog);
        for (int i = 0; i < archivos.length; i++) {
            System.out.println(archivos[i].getName());
        }

        // 19. Muestra la ruta absoluta del fichero usuarios.txt.
        escribirLog(marcaDeTiempoConMensaje("\nMostrando ruta absoluta del archivo " + archivoDatos.getName() + "."), archivoLog);
        System.out.println(archivoDatos.getAbsolutePath());




    }
    // 14. Crea un fichero log.txt en la carpeta datos y escribe un registro con la fecha y hora de
    //cada operación realizada.
    private static void escribirLog(String contenido, File archivoLog) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoLog.getPath(),true))) {
            bw.write(contenido);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Hubo un fallo en el método de escritura del archivo log.");
            System.out.println(e);
        }
    }

    private static String marcaDeTiempoConMensaje(String mensaje) {
        StringBuilder mensajeFinal = new StringBuilder();
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:n");
        mensajeFinal.append(fecha.format(fechaFormateada));
        mensajeFinal.append(" - ");
        mensajeFinal.append(mensaje);
        return mensajeFinal.toString();
    }

    // 15. Implementa un método que borre un fichero cuyo nombre se pase como parámetro.
    private static boolean borrarArchivo(File archivoABorrar) {
        return archivoABorrar.delete();
    }
}
