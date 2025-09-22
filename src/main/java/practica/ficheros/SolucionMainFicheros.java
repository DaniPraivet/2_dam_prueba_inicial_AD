package practica.ficheros;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;

public class SolucionMainFicheros {

    public static void main(String[] args) {
        try {
            // 4. Crear carpeta datos
            File carpeta = new File("datos");
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }

            // 5. Crear fichero usuarios.txt
            File usuarios = new File(carpeta, "usuarios.txt");
            if (!usuarios.exists()) {
                usuarios.createNewFile();
            }

            // 6. Comprobar existencia
            if (usuarios.exists()) {
                System.out.println("El fichero usuarios.txt existe.");
            } else {
                System.out.println("El fichero no existe.");
            }

            // 7. Escribir usuarios iniciales
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(usuarios))) {

                bw.write("Ana\nJuan\nLucía\nPedro\nMarta\n");
            }

            // 8. Leer fichero
            try (BufferedReader br = new BufferedReader(new FileReader(usuarios))) {
                String linea;
                System.out.println("Contenido inicial:");
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            // 9. Añadir usuarios
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(usuarios, true))) {
                bw.write("Carlos\nSoİa\n");
            }

            // 10. Contar líneas
            int contador = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(usuarios))) {
                while (br.readLine() != null) {
                    contador++;
                }
            }
            System.out.println("Número total de usuarios: " + contador);

            // 11. Buscar usuario "Carlos"
            boolean encontrado = false;
            try (BufferedReader br = new BufferedReader(new FileReader(usuarios))) {
                String linea;
                while ((linea = br.readLine()) != null) {

                    if (linea.equalsIgnoreCase("Carlos")) {
                        encontrado = true;
                        break;
                    }
                }
            }
            System.out.println(encontrado ? "Carlos está en la lista." : "Carlos no se encuentra.");

            // 12. Copiar fichero
            Path origen = usuarios.toPath();
            Path copia = Paths.get("datos/usuarios_copia.txt");
            Files.copy(origen, copia, StandardCopyOption.REPLACE_EXISTING);

            // 13. Renombrar fichero
            File copiaFile = new File("datos/usuarios_copia.txt");
            File backupFile = new File("datos/usuarios_backup.txt");
            copiaFile.renameTo(backupFile);

            // 14. Crear log.txt
            try (BufferedWriter log = new BufferedWriter(new FileWriter("datos/log.txt", true))) {
                log.write("Operaciones realizadas: " + LocalDateTime.now() + "\n");
            }

            // 15. Método para borrar
            borrarFichero("datos/usuarios_backup.txt");

            // 17. Crear directorio reportes
            File reportes = new File("reportes");
            if (!reportes.exists()) {
                reportes.mkdir();
            }

            // 18. Listar ficheros de datos
            System.out.println("Archivos en carpeta datos:");
            for (File f : carpeta.listFiles()) {
                System.out.println(f.getName());
            }

            // 19. Ruta absoluta
            System.out.println("Ruta absoluta de usuarios.txt: " + usuarios.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }

    // 15. Método para borrar un fichero
    public static void borrarFichero(String nombre) {
        File f = new File(nombre);
        if (f.exists()) {
            if (f.delete()) {
                System.out.println("Fichero " + nombre + " borrado correctamente.");
            } else {
                System.out.println("No se pudo borrar " + nombre);
            }
        }
    }
}