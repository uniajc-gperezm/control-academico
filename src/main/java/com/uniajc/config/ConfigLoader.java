package com.uniajc.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase para cargar propiedades desde el archivo config.properties.
 * Permite gestionar configuraciones sin hardcodear valores en el código.
 */
public class ConfigLoader {

  private static Properties properties = new Properties();
  private static final String CONFIG_FILE = "src/main/resources/config.properties";

  // Bloque estático - carga el archivo al inicializar la clase
  static {
    loadProperties();
  }

  /**
   * Carga las propiedades desde el archivo config.properties
   */
  private static void loadProperties() {
    try (InputStream input = new FileInputStream(CONFIG_FILE)) {
      properties.load(input);
      System.out.println("✓ Archivo de configuración cargado: " + CONFIG_FILE);
    } catch (IOException e) {
      System.err.println("✗ Error: No se pudo cargar " + CONFIG_FILE);
      System.err.println("  Asegúrate de:");
      System.err.println("  1. Copiar config.properties.example a config.properties");
      System.err.println("  2. Configurar tus credenciales de MySQL");
      System.err.println("  3. El archivo debe estar en la carpeta 'resources/'");
      e.printStackTrace();
    }
  }

  /**
   * Obtiene el valor de una propiedad.
   *
   * @param key nombre de la propiedad
   * @return valor de la propiedad, o null si no existe
   */
  public static String getProperty(String key) {
    String value = properties.getProperty(key);
    if (value == null) {
      System.err.println("⚠ Advertencia: Propiedad '" + key + "' no encontrada");
    }
    return value;
  }

  /**
   * Obtiene el valor de una propiedad con un valor por defecto.
   *
   * @param key          nombre de la propiedad
   * @param defaultValue valor por defecto si la propiedad no existe
   * @return valor de la propiedad, o defaultValue si no existe
   */
  public static String getProperty(String key, String defaultValue) {
    return properties.getProperty(key, defaultValue);
  }

  /**
   * Verifica si una propiedad existe.
   *
   * @param key nombre de la propiedad
   * @return true si existe, false en caso contrario
   */
  public static boolean hasProperty(String key) {
    return properties.containsKey(key);
  }
}
