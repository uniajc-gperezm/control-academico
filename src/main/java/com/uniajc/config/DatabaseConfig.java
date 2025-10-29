package com.uniajc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de configuración para gestionar conexiones a la base de datos MySQL.
 * Implementa el patrón Singleton para una única conexión.
 */

public class DatabaseConfig {

  private static Connection connection = null;
  private static String url;
  private static String user;
  private static String password;

  // Bloque estático - se ejecuta una sola vez al cargar la clase
  static {
    try {
      // Cargar configuración desde el archivo properties
      url = ConfigLoader.getProperty("database.url");
      user = ConfigLoader.getProperty("database.user");
      password = ConfigLoader.getProperty("database.password");

      // Cargar el driver de MySQL (opcional en JDBC 4.0+, pero es buena práctica)
      Class.forName("com.mysql.cj.jdbc.Driver");

      System.out.println("✓ Driver MySQL cargado correctamente");
    } catch (ClassNotFoundException e) {
      System.err.println("✗ Error: No se encontró el driver de MySQL");
      System.err.println("  Asegúrate de tener mysql-connector-java en el classpath");
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("✗ Error al cargar la configuración de la base de datos");
      e.printStackTrace();
    }
  }

  /**
   * Obtiene una conexión a la base de datos.
   * Si no existe una conexión activa, crea una nueva.
   *
   * @return Connection objeto de conexión a la BD
   * @throws SQLException si hay error al conectar
   */
  public static Connection getConnection() throws SQLException {
    try {
      // Si la conexión es null o está cerrada, crear una nueva
      if (connection == null || connection.isClosed()) {
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("✓ Conexión establecida a: " + url);
      }
      return connection;
    } catch (SQLException e) {
      System.err.println("✗ Error al conectar a la base de datos");
      System.err.println("  URL: " + url);
      System.err.println("  Usuario: " + user);
      System.err.println("  Verifica tus credenciales en config.properties");
      throw e;
    }
  }

  /**
   * Cierra la conexión a la base de datos.
   */
  public static void closeConnection() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
        System.out.println("✓ Conexión cerrada correctamente");
      }
    } catch (SQLException e) {
      System.err.println("✗ Error al cerrar la conexión");
      e.printStackTrace();
    }
  }

  /**
   * Método para probar la conexión (útil para debugging)
   *
   * @return true si la conexión es exitosa, false en caso contrario
   */
  public static boolean testConnection() {
    try {
      Connection conn = getConnection();
      boolean isValid = conn.isValid(5); // Timeout de 5 segundos
      if (isValid) {
        System.out.println("✓ Test de conexión exitoso");
      }
      return isValid;
    } catch (SQLException e) {
      System.err.println("✗ Test de conexión fallido");
      e.printStackTrace();
      return false;
    }
  }

}
