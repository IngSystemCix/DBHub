
# DBHub

**DBHub** es una biblioteca Java diseñada para simplificar la conexión y operación con diversas bases de datos. Compatible con múltiples motores, esta biblioteca facilita la integración de bases de datos en tus aplicaciones Java.

# Programadores

- [Juan Bladimir Romero Collazos](https://github.com/IngSystemCix)
- [Jaime Adolfo López Scipión](https://github.com/Uskrat)

## Bases de Datos Soportadas

DBHub es compatible con los siguientes sistemas de bases de datos:

- **MySQL**
- **PostgreSQL**
- **SQLite**
- **SQLServer**
- **Oracle**
- **MongoDB**
- **Redis**
- **H2**

## Características Principales

- **Conexión Simplificada:** Conéctate fácilmente a cualquier base de datos soportada utilizando configuraciones mínimas.
- **Compatibilidad Multibase de Datos:** Maneja múltiples bases de datos en un solo proyecto sin complicaciones.
- **Configuración Centralizada:** Utiliza un archivo `.env` para gestionar las credenciales y configuraciones de las bases de datos de manera segura.

## Uso

### Ejemplo de Conexión

```java
package pe.edu.utp.DBHub;

import pe.edu.utp.DBHub.db.*;

public class app {
    public static void main(String[] args) {
        SQLServerConnector sqlServerConnector = new SQLServerConnector();
        sqlServerConnector.connect("localhost", "1433", "yourDatabase", "yourUser", "yourPassword");
        sqlServerConnector.disconnect();
    }
}
```

### Documentación

Para obtener más información sobre cómo utilizar DBHub, consulta la documentación oficial en [este enlace](), donde encontrarás ejempencias y guías detalladas para integrar esta biblioteca en tus proyectos Java.

## Notas Importantes

> [!TIP]
> El archivo `.env` debera ser creado en la ruta `src/main/resources` de tu proyecto Java. Este archivo debe contener las credenciales y configuraciones de las bases de datos que desees utilizar en tu aplicación.

> [!NOTE]  
> DBHub ha sido diseñada para facilitar la integración con diferentes motores de bases de datos, como MySQL, PostgreSQL, SQLite, entre otros. La simplicidad y flexibilidad de esta biblioteca te permiten enfocarte en la lógica de tu aplicación sin preocuparte por los detalles de implementación de las conexiones a bases de datos.

> [!WARNING]
> Al crear el archivo `.env`, es crucial recordar que este archivo contiene información sensible, como credenciales de acceso a bases de datos. **No subas el archivo `.env` a repositorios públicos** para evitar riesgos de seguridad.
