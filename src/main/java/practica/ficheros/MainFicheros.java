package practica.ficheros;

import java.io.*;

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
        }
        // 5. Crea dentro de datos un fichero de texto llamado usuarios.txt.
        File archivoDatos;
        try {
            archivoDatos = new File(carpetaDatos.getPath(), "usuarios.txt");
            archivoDatos.createNewFile();
            // 6. Comprueba si el fichero usuarios.txt existe, y muestra un mensaje apropiado.
            if (archivoDatos.exists()) {
                System.out.println("El archivo " + archivoDatos.getName() + " existe.");
            } else {
                System.out.println("El archivo " + archivoDatos.getName() + " no existe.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 7. Escribe en usuarios.txt los nombres de 5 usuarios (uno por línea).
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDatos.getPath()))) {
            String[] usuarios = {"Jepeto", "Mamerto", "Jorgito", "Jaimito", "Gerardo"};
            for  (int i = 0; i < usuarios.length; i++) {
                bw.write(usuarios[i]);
                bw.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 8. Lee y muestra por pantalla el contenido de usuarios.txt.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos.getPath()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 9. Añade dos usuarios más al final del fichero sin sobrescribir el contenido.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoDatos.getPath()))) {
            String[] usuarios = {"Ruperto", "Gustavo"};
            for  (int i = 0; i < usuarios.length; i++) {
                bw.write(usuarios[i]);
                bw.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 10. Muestra el número total de líneas (usuarios) en el fichero.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos.getPath()))) {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null) {
                contador++;
            }
            System.out.println("Hay " + contador + " líneas.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 11. Busca en el fichero si existe un usuario llamado "Carlos". Muestra un mensaje según se
        //encuentre o no.
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos.getPath()))) {
            String linea;
            boolean encontrado = false;
            while ((linea = br.readLine()) != null && !encontrado) {
                if (linea.contains("Carlos")) encontrado = true;
            }

            if (encontrado) {
                System.out.println("Hemos encontrado al usuario Carlos.");
            } else {
                System.out.println("No hemos encontrado al usuario Carlos.");
            }
            System.out.println("");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 12. Copia el fichero usuarios.txt en un nuevo fichero usuarios_copia.txt.
        File archivoBackup = new File(carpetaDatos, "usuarios_copia.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos.getPath()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoBackup))) {
            String linea;
            StringBuilder texto = new StringBuilder();
            while ((linea = br.readLine()) != null) {
                texto.append(linea);
            }
            bw.write(String.valueOf(texto));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 13. Renombra el fichero usuarios_copia.txt a usuarios_backup.txt.
        File archivoBackupRenombrado = new File(carpetaDatos, "usuarios_backup.txt");
        boolean renombrado = archivoBackup.renameTo(archivoBackupRenombrado);
        if (renombrado) System.out.println("Archivo renombrado correctamente.");
        else System.out.println("No se ha podido renombrar el archivo.");



    }
    // 14. Crea un fichero log.txt en la carpeta datos y escribe un registro con la fecha y hora de
    //cada operación realizada.
    private static void escribirLog(String contenido, File archivoLog) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoLog.getPath(),true))) {
            bw.write(contenido);
            bw.newLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
