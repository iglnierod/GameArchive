# GameArchive

## Entregas

### 5 de Junio 2024

#### developing branch

- Ver actividad reciente en `Community`
- Ver información del perfil
- Buscar usuarios
- Ver perfil de otros usuarios
- Exportar listas
  - JSON
  - XML
  - HTML 
- Importar listas
  - JSON
  - XML
- Pestaña `Home`:
  - Juegos recomendados basado en favoritos
  - Juegos aleatorios mejor valorados de IGDB
- Juego aleatorio basado en parámetros
- Añadir estados a juegos (quiero jugar, jugando, jugado)

### 25 de Mayo 2024
##### main branch

##### developing branch

- Marcar juego como favorito
- Ver juegos similares dentro de un juego
- Valorar juegos
- Ver valoraciones de otros usarios en el juego

### 14 de Mayo 2024

##### main branch

- Establecer limite de resultados en búsqueda
- Filtrar por valoración mínima de IGDB.com
- Filtrar por plataformas
- GameDialog: Ventana de información sobre juego
- Apartado MyLists de la pantalla principal
- Crear, ver, editar, eliminar listas
- Añadir juegos a listas

##### developing branch

- Inicio de guardar juegos como favoritos
  - Crear lista `Favoritos` al crear un usuario

### 1 de Mayo 2024

##### main branch

- Nueva pantalla Home con menú
- Creado panel de vista de juegos al buscar
- Inicio de la búsqueda de juegos
- Inicio de filtros en la búsqueda

### 20 de Abril 2024

##### main branch

- Creada pantalla de configuración del perfil después de registrarse
- Guardadas las plataformas del usuario en la tabla `client_platform`
- Creada clase sesión para guardar los datos del usuario logeado
- Añadido checkbox en Login para guardar la sesión

##### developing branch

- Modificada la pantalla Home
- Añadido Log out y Quit en Menu Bar de Home
- Creado el modelo para Game
- Inicio del DAO para Game
- Creado paquete `model.api.igdb` con funcionalidades para las llamadas
- Añadida dependecia Unirest para las llamadas a la API
- Primeras pruebas de llamada a la API (por consola): `main.Main`

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

### Unirest

Librería simple y ligera para realizar solicitures HTTP.

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
- [X] Guardar la sesión del usuario

- Personlalización del perfil
  - [ ] Elegir imagen
  - [X] Nombre de usuario
  - [X] Email
  - [X] Descripción

- Búsqueda de juegos:
  - [X] Por titulo
  - [X] Por plataforma
  - [X] Por géneros
  - [X] Por valoración

- Ver información sobre juegos:
  - [X] Portada
  - [X] Descripción
  - [X] Plataforma
  - [X] Géneros

- Listas
  - [X] Crear
  - [X] Editar
  - [X] Eliminar
  - [X] Añadir juegos
  - Personalización de listas
    - [X] Título
    - [X] Descripción

- [X] Marcar juego como favorito
- [X] Valorar juegos
- [X] Juegos similares
- [X] Juegos recomenados basados en favoritos
- [X] Feed de últimos juegos añadidos por otros usuarios
- [X] Ver perfil del usuario logeado
  - [X] Ver información general (nombre, descripcion, plataformas)
  - [X] Ver listas del usuario
  - [X] Ver valoraciones del usuario
  - [X] Ver última actividad del usuario
- [X] Ver perfil de otros usuarios
- [X] Búsqueda de usuarios
- [X] Exportar listas
  - [X] JSON
  - [X] XML
  - [X] HTML
- [X] Importar listas
  - [X] JSON
  - [X] XML
- [X] Añadir estados a juegos
- [X] Sistema de recomendación aleatoria con parámetros

## Entorno de desarrollo

| Nombre                      |Versión                                      |
|-----------------------------|---------------------------------------------|
| Product Version             | Apache NetBeans IDE 19                      |
| Java                        | 11.0.20; Java HotSpot(TM) 64-Bit Server VM 11.0.20+9-LTS-256 |
| Runtime                     | Java(TM) SE Runtime Environment 11.0.20+9-LTS-256 |
| System                      | Windows 11 version 10.0, Ubuntu 22.04-LTS   |

### Referencias

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
