# Control Académico

> Proyecto inicial de ejemplo para la gestión de un sistema de control académico.

Este repositorio contiene un pequeño proyecto Java (Maven) con configuración externa a través de `config.properties` y ejemplos de carga de configuración y pruebas de conexión a base de datos.

## Contenido

- `pom.xml` - Configuración de Maven y dependencias.
- `src/main/java` - Código fuente del proyecto.
  - `com.uniajc` - Paquete principal.
    - `Main.java` - Punto de entrada de la aplicación (ejemplo).
    - `config/` - Clases para cargar la configuración (`ConfigLoader.java`, `DatabaseConfig.java`).
    - `util/` - Utilidades y pruebas (`DatabaseTest.java`).
- `src/main/resources` - Recursos del proyecto.
  - `config.properties` - Archivo de configuración (puede contener credenciales o parámetros de conexión). Hay un ejemplo `config.properties.example`.
- `src/test/java` - Tests unitarios.

## Requisitos

- Java 17 o superior (ajusta según `pom.xml`).
- Maven 3.9 o superior.+

## Configuración

Antes de ejecutar la aplicación, copia el ejemplo de configuración y edita los valores necesarios:

```powershell
copy .\src\main\resources\config.properties.example .\src\main\resources\config.properties
```

Edita `src/main/resources/config.properties` para configurar valores como la URL de la base de datos, usuario y contraseña. Por seguridad, evita subir credenciales reales al control de versiones.

En el directorio `target/classes` también hay una copia de `config.properties` generada por Maven durante la compilación; cuando empaquetes o despliegues, asegúrate de que las propiedades correctas estén incluidas en el classpath o proporcionadas mediante variables de entorno.

## Compilar

Desde la raíz del proyecto (donde está `pom.xml`):

```powershell
mvn -B package
```

Esto compila el proyecto y genera el artefacto en `target/`.

## Ejecutar

Si el proyecto genera una jar ejecutable, puedes ejecutarlo con:

```powershell
java -jar target\nombre-del-artifacto.jar
```

Alternativamente, desde el código fuente (clase `Main`) puedes ejecutar con Maven:

```powershell
mvn -q exec:java -Dexec.mainClass="com.uniajc.Main"
```

Reemplaza `nombre-del-artifacto.jar` por el nombre real que aparece en `target/` después del empaquetado.

## Ejecutar pruebas

Para ejecutar los tests unitarios:

```powershell
mvn test
```

## Notas sobre la configuración de la base de datos

Si usas `DatabaseTest` u otras utilidades que intentan conectar a la base de datos, asegúrate de que las credenciales y la URL en `config.properties` apunten a una instancia accesible. Para entornos de CI, considera usar variables de entorno o perfiles de Maven para evitar exponer secretos.

## Estructura del proyecto (resumen)

```
pom.xml
src/
  main/
    java/
      com/uniajc/
        Main.java
        config/
          ConfigLoader.java
          DatabaseConfig.java
        util/
          DatabaseTest.java
    resources/
      config.properties
      config.properties.example
test/
  java/

target/
```

## Contribuciones

1. Haz un fork del repositorio.
2. Crea una rama con la funcionalidad: `git checkout -b feat/nueva-funcionalidad`.
3. Haz commits pequeños y descriptivos.
4. Abre un Pull Request describiendo los cambios.

## Licencia

Agrega aquí la licencia del proyecto si aplica (por ejemplo, MIT, Apache 2.0). Si aún no la has decidido, considera añadir `LICENSE` en la raíz.

## Contacto

Para dudas o colaboración, contacta al mantenedor del repositorio.

---

Archivo generado automáticamente: README inicial creado por asistente.
