package com.uniajc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase de configuración para gestionar el pool de conexiones a la base de datos MySQL.
 * Utiliza HikariCP para proporcionar un pool de conexiones eficiente y thread-safe.
 *
 * <p>Esta clase implementa el patrón Singleton para el DataSource y proporciona
 * métodos estáticos para obtener conexiones del pool.</p>
 *
 * <p><b>Uso:</b></p>
 * <pre>
 * // Inicializar el pool (usualmente en Main.java)
 * DatabaseConfig.initialize();
 *
 * // Obtener una conexión
 * try (Connection conn = DatabaseConfig.getConnection()) {
 *     // Usar la conexión
 * } // Se cierra automáticamente
 *
 * // Cerrar el pool al finalizar la aplicación
 * DatabaseConfig.shutdown();
 * </pre>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class DatabaseConfig {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
  private static HikariDataSource dataSource;
  private static volatile boolean initialized = false;

  /**
   * Constructor privado para prevenir instanciación.
   * Esta clase solo debe usarse de forma estática.
   */
  private DatabaseConfig() {
    throw new IllegalStateException("Utility class - do not instantiate");
  }

  /**
   * Inicializa el pool de conexiones con HikariCP.
   * Este método debe ser llamado una vez al inicio de la aplicación.
   * Es thread-safe y puede ser llamado múltiples veces sin efecto adverso.
   *
   * @throws RuntimeException si ocurre un error al configurar el pool
   */
  public static synchronized void initialize() {
    if (initialized) {
      logger.warn("DatabaseConfig ya está inicializado. Ignorando llamada duplicada.");
      return;
    }

    try {
      logger.info("Inicializando pool de conexiones HikariCP...");

      // Cargar configuración desde config.properties
      String jdbcUrl = ConfigLoader.getProperty("database.url");
      String username = ConfigLoader.getProperty("database.user");
      String password = ConfigLoader.getProperty("database.password");

      // Configuración del pool (con valores por defecto si no están en properties)
      String maxPoolSize = ConfigLoader.getProperty("db.pool.maximumPoolSize", "10");
      String minIdle = ConfigLoader.getProperty("db.pool.minimumIdle", "2");
      String connectionTimeout = ConfigLoader.getProperty("db.pool.connectionTimeout", "30000");
      String idleTimeout = ConfigLoader.getProperty("db.pool.idleTimeout", "600000");
      String maxLifetime = ConfigLoader.getProperty("db.pool.maxLifetime", "1800000");

      // Configurar HikariCP
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(jdbcUrl);
      config.setUsername(username);
      config.setPassword(password);

      // Pool settings
      config.setMaximumPoolSize(Integer.parseInt(maxPoolSize));
      config.setMinimumIdle(Integer.parseInt(minIdle));
      config.setConnectionTimeout(Long.parseLong(connectionTimeout));
      config.setIdleTimeout(Long.parseLong(idleTimeout));
      config.setMaxLifetime(Long.parseLong(maxLifetime));

      // Connection properties
      config.setAutoCommit(true);
      config.setConnectionTestQuery("SELECT 1");
      config.setPoolName("AcademicoPool");

      // Performance optimizations
      config.addDataSourceProperty("cachePrepStmts", "true");
      config.addDataSourceProperty("prepStmtCacheSize", "250");
      config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      config.addDataSourceProperty("useServerPrepStmts", "true");
      config.addDataSourceProperty("useLocalSessionState", "true");
      config.addDataSourceProperty("rewriteBatchedStatements", "true");
      config.addDataSourceProperty("cacheResultSetMetadata", "true");
      config.addDataSourceProperty("cacheServerConfiguration", "true");
      config.addDataSourceProperty("elideSetAutoCommits", "true");
      config.addDataSourceProperty("maintainTimeStats", "false");

      // Crear el DataSource
      dataSource = new HikariDataSource(config);
      initialized = true;

      logger.info("Pool de conexiones inicializado correctamente");
      logger.info("  URL: {}", jdbcUrl);
      logger.info("  Pool size: {} (min: {})", maxPoolSize, minIdle);

    } catch (Exception e) {
      logger.error("Error al inicializar el pool de conexiones", e);
      throw new RuntimeException("No se pudo inicializar el pool de conexiones", e);
    }
  }

  /**
   * Obtiene una conexión del pool.
   *
   * <p><b>IMPORTANTE:</b> La conexión debe cerrarse después de usarse,
   * preferiblemente usando try-with-resources para asegurar su devolución al pool.</p>
   *
   * <pre>
   * try (Connection conn = DatabaseConfig.getConnection()) {
   *     // Usar la conexión
   * } // Se devuelve automáticamente al pool
   * </pre>
   *
   * @return Connection del pool de conexiones
   * @throws SQLException si no se puede obtener una conexión
   * @throws IllegalStateException si el pool no ha sido inicializado
   */
  public static Connection getConnection() throws SQLException {
    if (!initialized || dataSource == null) {
      throw new IllegalStateException(
        "El pool de conexiones no ha sido inicializado. Llama a DatabaseConfig.initialize() primero."
      );
    }

    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      logger.error("Error al obtener conexión del pool", e);
      throw e;
    }
  }

  /**
   * Cierra el pool de conexiones y libera todos los recursos.
   * Este método debe ser llamado al finalizar la aplicación.
   * Es seguro llamarlo múltiples veces.
   */
  public static synchronized void shutdown() {
    if (dataSource != null && !dataSource.isClosed()) {
      logger.info("Cerrando pool de conexiones...");
      dataSource.close();
      initialized = false;
      dataSource = null;
      logger.info("Pool de conexiones cerrado correctamente");
    }
  }

  /**
   * Verifica si el pool de conexiones está activo y funcionando.
   *
   * @return true si el pool está activo y puede proporcionar conexiones, false en caso contrario
   */
  public static boolean isInitialized() {
    return initialized && dataSource != null && !dataSource.isClosed();
  }

  /**
   * Prueba la conexión a la base de datos obteniendo una conexión del pool
   * y ejecutando una consulta de prueba.
   *
   * @return true si la conexión es exitosa, false en caso contrario
   */
  public static boolean testConnection() {
    if (!initialized) {
      logger.warn("No se puede probar la conexión: el pool no está inicializado");
      return false;
    }

    try (Connection conn = getConnection()) {
      boolean isValid = conn.isValid(5); // Timeout de 5 segundos
      if (isValid) {
        logger.info("Test de conexión exitoso");
      } else {
        logger.warn("Test de conexión falló: conexión no válida");
      }
      return isValid;
    } catch (SQLException e) {
      logger.error("Test de conexión falló con excepción", e);
      return false;
    }
  }

  /**
   * Obtiene estadísticas del pool de conexiones.
   * Útil para monitoreo y debugging.
   *
   * @return String con las estadísticas del pool
   */
  public static String getPoolStats() {
    if (!initialized || dataSource == null) {
      return "Pool no inicializado";
    }

    return String.format(
      "HikariCP Pool Stats - Active: %d, Idle: %d, Waiting: %d, Total: %d",
      dataSource.getHikariPoolMXBean().getActiveConnections(),
      dataSource.getHikariPoolMXBean().getIdleConnections(),
      dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection(),
      dataSource.getHikariPoolMXBean().getTotalConnections()
    );
  }

}
