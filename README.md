# GameArchive

## Entregas
### 8 de Abril 2024
- Creación de la estructura MVC del proyecto
- Primera versión de la estructura de la base de datos
- Register/Login mejorables pero funcionales
- Primera parte de configuración del perfil eligiendo plataformas
- Pruebas con las APIs de IGBD y Imgur

## Tecnologías
### Git
Software de control de versiones del proyecto.

### NetBeans
NetBeans es IDE en el que está creado este proyecto por su cómoda creación de interfaces gráficas utilizando JavaSwing.

### Maven
Maven da la estructura de paquetes de este proyecto y se encarga de descargar las dependecias del proyecto.

### FlatLaf
Librería que modifica los diseños de los componentes de JavaSwing para darle un aspecto más moderno a la aplicación.

### BCrypt
Se encarga de la función de encriptar las contraseñas de los usuarios.

### PostgreSQL
Este proyecto utiliza este motor de base de datos porque está creada en línea en la web supabase.com que ofrece un plan gratuito que te permite tener una base de datos de este motor.

### IGDB
IGBD es una web que ofrece todo tipo de información sobre videojuegos. En este proyecto se utiliza su API para obtener información sobre los juegos en las búsquedas, recomendaciones, etc.

### Imgur
Es una plataforma online que permite subir imágenes de manera anónima utilizando la API por lo que se usará para almacenar de manera gratuita las imágenes de perfil de los usuarios.

## Código
### MVC
La estructura del progrma es una mezcla entre el MVC explicado por Fernando y por Julián. Dentro del paquete principal `com.iglnierod.gamearchive` están los paquetes:

- `controller`: controla el modelo y las vistas.
- `main`: contiene la clase Main dónde se ejecuta el programa.
- `model`: modelo de datos y DAO encargado de hacer llamadas a la base de datos.
- `view`: vistas hechas en JavaSwing con los métodos necesarios para ser modificadas por los controladores. Un ejemplo son los listeners de botones o los datos que se muestran en `ProfileConfigDialog`.

### Variables de entorno
El fichero ``.env`` contiene los valores importantes como contraseña y usuario de la base de datos y claves de las APIs.

## Alcances realizados
- [X] Inicio de sesión
- [X] Registro de usuarios

- Personlalización del perfil
    - [ ] Elegir imagen
    - [X] Nombre de usuario
    - [X] Email
    - [ ] Descripción
    - [ ] Enlaces a redes sociales

- Búsqueda de juegos:
    - [ ] Por titulo
    - [ ] Por plataforma
    - [ ] Por géneros
    - [ ] Por valoración

- Ver información sobre juegos:
    - [ ] Portada
    - [ ] Descripción
    - [ ] Plataforma
    - [ ] Géneros

- [ ] Creación de listas

- Personalización de listas
    - [ ] Título
    - [ ] Descripción

- [ ] Añadir estados a juegos
- [ ] Valorar juegos
- [ ] Sistema de recomendación
- [ ] Sistema de recomendación aleatoria con parámetros
- [ ] Feed de últimos juegos añadidos por otros usuarios
- [ ] Búsqueda de usuarios
- [ ] Ver perfil de otros usuarios
- [ ] Exportar listas a otros formatos (XML, CSV, PDF, HTML)

## Entorno de desarrollo
| Nombre                      |Versión                                     |
|-----------------------------|---------------------------------------------|
| Product Version             | Apache NetBeans IDE 19                     |
| Java                        | 11.0.20; Java HotSpot(TM) 64-Bit Server VM 11.0.20+9-LTS-256 |
| Runtime                     | Java(TM) SE Runtime Environment 11.0.20+9-LTS-256 |
| System                      | Windows 11 version 10.0                    |

#### Referencias
| Referencia     | Enlace                                        |
|----------------|-----------------------------------------------|
| Git            | [git-scm.com](https://git-scm.com/)           |
| NetBeans       | [netbeans.apache.org](https://netbeans.apache.org/front/main/index.html) |
| Maven          | [maven.apache.org](https://maven.apache.org/) |
| FlatLaf        | [formdev.com/flatlaf](https://www.formdev.com/flatlaf/) |
| IGDB           | [igdb.com](https://www.igdb.com/)             |
| IGDB API       | [api-docs.igdb.com](https://api-docs.igdb.com/) |
| Imgur          | [imgur.com](https://www.imgur.com/)           |
| Imgur API      | [apidocs.imgur.com](https://apidocs.imgur.com/) |
| Supabase       | [supabase.com](https://supabase.com/)         |

###### Rodrigo Iglesias Nieto - Proyecto final DAM
