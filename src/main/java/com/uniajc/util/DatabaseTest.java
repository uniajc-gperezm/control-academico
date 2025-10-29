package com.uniajc.util;

import com.uniajc.config.DatabaseConfig;

public class DatabaseTest {
  public static void main(String[] args) {
    // Intenta cargar la configuración y conectar a la base de datos
    System.out.println("Iniciando prueba de conexión a la base de datos...");
    if (DatabaseConfig.testConnection()) {
      System.out.println("La base de datos está configurada y accesible correctamente.");
    } else {
      System.out.println("Hubo un problema al conectar con la base de datos. Revisa los mensajes de error anteriores.");
    }
    DatabaseConfig.closeConnection();
  }

}
