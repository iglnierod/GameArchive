## Cargar las variables de entorno en NetBeans
1. Abrir el proyecto
2. Click derecho > Propiedades
3. Categories: Actions > Actions: Run project
4. Set properties: (abajo del todo) escribir:
```txt
Env.DB_HOST=
Env.DB_NAME=
Env.DB_PORT=
Env.DB_USER=
Env.DB_PASSWORD=
```

## Crear schema en PostgreSQL
```sql
CREATE TABLE users (
    username TEXT PRIMARY KEY,
    email TEXT NOT NULL,
    password TEXT NOT NULL
);
```

## Crear schema en MySQL
```sql
CREATE TABLE users (
    username varchar(100) PRIMARY KEY,
    email varchar(100) NOT NULL,
    password varchar(100) NOT NULL
);
```

## Información sobre el código
Uso el patrón MVC que nos ha enseñado Fernando en la asignatura de Interfaces. El código que se ejecuta al presionar el botón Register está en ``RegisterController`` y el del Login se encuentra en ``LoginController``.

En el modelo está la clase User y Database. **User tiene las propiedades del usuario y se encarga de encriptar la contraseña**. Database simplemente tiene la información de la base de datos que carga de las variables de entorno.

Las llamadas a la base de datos están en el DAO que se encuentra en el modelo. El método add() se llama al pulsar Register y login() al presionar el botón Login.

### Dependencias del proyecto
- PostgreSQL JDBC
- MySQL JDBC
- BCrypt
- FlatLaf

##### pom.xml
```xml
<dependencies>
        <!-- FlatLaf -->
        <!-- https://mvnrepository.com/artifact/com.formdev/flatlaf -->
        <dependency>
            <groupId>com.formdev</groupId>
            <artifactId>flatlaf</artifactId>
            <version>3.4.1</version>
        </dependency>
        
        <!-- Bcrypt -->
        <!-- https://mvnrepository.com/artifact/org.mindrot/jbcrypt -->
        <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
        
        <!--  PostgreSQL -->
        <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.3</version>
        </dependency>
        
        <!-- MySQL -->
        <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.3.0</version>
        </dependency>
    </dependencies>
```