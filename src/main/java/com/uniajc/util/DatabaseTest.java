package com.uniajc.util;

import com.uniajc.config.DatabaseConfig;

/**
 * Utilidad para probar la conexión a la base de datos con HikariCP.
 *
 * <p>Este programa realiza las siguientes operaciones:</p>
 * <ul>
 *   <li>Inicializa el pool de conexiones HikariCP</li>
 *   <li>Prueba la conexión a la base de datos</li>
 *   <li>Muestra estadísticas del pool</li>
 *   <li>Cierra el pool al finalizar</li>
 * </ul>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 2.0.0
 */
public class DatabaseTest {

  public static void main(String[] args) {
    System.out.println("=".repeat(60));
    System.out.println("TEST DE CONEXIÓN - Sistema Académico Universidad AJC");
    System.out.println("=".repeat(60));
    System.out.println();

    try {
      // Paso 1: Inicializar el pool de conexiones
      System.out.println("[1/3] Inicializando pool de conexiones HikariCP...");
      DatabaseConfig.initialize();
      System.out.println("      Pool inicializado correctamente");
      System.out.println();

      // Paso 2: Probar la conexión
      System.out.println("[2/3] Probando conexión a la base de datos...");
      boolean connectionSuccess = DatabaseConfig.testConnection();

      if (connectionSuccess) {
        System.out.println("      Conexión exitosa");
        System.out.println();

        // Paso 3: Mostrar estadísticas del pool
        System.out.println("[3/3] Estadísticas del pool:");
        System.out.println("      " + DatabaseConfig.getPoolStats());
        System.out.println();

        System.out.println("=".repeat(60));
        System.out.println("RESULTADO: La base de datos está configurada correctamente");
        System.out.println("=".repeat(60));

      } else {
        System.err.println("      Conexión fallida");
        System.err.println();
        System.err.println("=".repeat(60));
        System.err.println("RESULTADO: Hubo un problema al conectar con la base de datos");
        System.err.println("=".repeat(60));
        System.err.println();
        System.err.println("Verifica:");
        System.err.println("  1. MySQL está ejecutándose");
        System.err.println("  2. La base de datos 'academico-v2' existe");
        System.err.println("  3. Las credenciales en config.properties son correctas");
        System.err.println("  4. El puerto 3306 está disponible");
      }

    } catch (Exception e) {
      System.err.println();
      System.err.println("=".repeat(60));
      System.err.println("ERROR: Excepción durante la prueba");
      System.err.println("=".repeat(60));
      System.err.println("Mensaje: " + e.getMessage());
      e.printStackTrace();

    } finally {
      // Cerrar el pool de conexiones
      System.out.println();
      System.out.println("Cerrando pool de conexiones...");
      DatabaseConfig.shutdown();
      System.out.println("Test finalizado");
    }
  }

}
