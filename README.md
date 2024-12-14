# Proyecto Trabajo Final UNFV

## Tecnologías Utilizadas
- **JDK 22**: Versión más reciente del kit de desarrollo de Java.
- **Apache NetBeans**: Entorno de desarrollo integrado (IDE) para Java.
- **Script SQL para Base de Datos (MySQL)**: Utilizado para la creación y gestión de la base de datos.
- **XAMPP**: Plataforma que incluye MySQL, utilizada como servidor local.
- **Driver MySQL**: Driver adjuntado para la conexión entre Java y MySQL.

![Pinguino Bailarín](PinguinoBailarin.gif)

---

## Clonar el Repositorio

Si deseas clonar este repositorio para trabajar en él de manera local, sigue estos pasos:

1. Abre tu terminal (o Git Bash en Windows).
2. Navega a la carpeta donde deseas clonar el proyecto.
3. Ejecuta el siguiente comando para clonar el repositorio:

   ```bash
   git clone https://github.com/cotanol/ProyectoAsistenciaUNFV.git
   ```

# Cómo Usar el Programa Gestor General de Laboratorios de la Facultad de Ingeniería Industrial y Sistemas

## Inicializar el Programa
1. Descargar / extraer el archivo RAR/ZIP.
2. Abrir XAMPP y pegar el script SQL en la pestaña "SQL".
3. Abrir NetBeans para poder abrir el proyecto.
4. Ejecutar el proyecto: clic derecho y seleccionar "Run File".

![penguin_walk](penguin_walk.gif)

## Login del Programa
1. Puedes ingresar como docente o administrador (`admin`). El administrador tiene acceso a todas las opciones, mientras que el docente solo puede acceder al botón de asistencia.
2. Datos de acceso:
   - **Acceso Admin**:
     - Usuario: `admin`
     - Contraseña: `123`
   - **Acceso Docente**:
     - Usuario: `docente`
     - Contraseña: `123`

---

## Instrucciones de las Secciones del Gestor General de Laboratorios

### Sección 1: Registro de Usuarios
1. Al iniciar, se encuentra en la sección "Registro de Usuarios".
2. Funciones disponibles:
   - Agregar, modificar o eliminar usuarios.
   - Asignar roles de administrador y docente.
   - Exportar datos a Excel (completos o filtrados por campo especificado en la caja de texto).
3. Ejemplo: Escribir "pasaporte" en la caja de texto filtrará la tabla. Luego, clic en "Exportar Excel" exportará los datos filtrados.

---

### Sección 2: Control de Asistencia
1. Busca una clase en el primer combobox para dar asistencia a los alumnos registrados.
2. Marca asistencia seleccionando los checkboxes en los registros correspondientes (requiere que la clase esté previamente creada).
3. Parte inferior:
   - Segundo combobox y tabla para seleccionar una clase y agregar alumnos con el botón "Agregar".
4. Botón "Configuración":
   - Acceso al gestor de estudiantes y al gestor para eliminar estudiantes de una clase.
   - **Gestor de Estudiantes**: Agregar, modificar o eliminar estudiantes por código, apellidos o nombres.
   - **Eliminar Estudiante de una Clase**: Buscar por código de alumno o clase, seleccionar registro y clic en "Eliminar".

---

### Sección 3: Control de Equipos
1. Tabla con los equipos disponibles en cada laboratorio y dos botones:
   - **Exportar Excel**: Exportar registros filtrados.
   - **Configuración**: Agregar, modificar o eliminar registros.

---

### Sección 4: Horarios de Laboratorios
1. Funciones:
   - Registrar clases.
   - Crear laboratorios y asignaturas desde el botón "Configuración".
2. Tabla principal:
   - Agregar horarios/clases.
   - Eliminar una clase seleccionando un registro y haciendo clic en "Eliminar".
3. Paneles de configuración:
   - Gestionar laboratorios y asignaturas: agregar, modificar o eliminar.

---

## Recomendaciones Finales
- Al crear un usuario docente, se recomienda usar nombres y apellidos completos para facilitar su búsqueda en los combobox al crear clases.
- **Ejemplo**: 
  - Nombres y apellidos: Iván Carlo Petrlik Azabache
  - Usuario: `ivanCarloPetrlikAzabache`, `ivanPetrlik`, `ivancarlopetrlikazabache` o `ivanpetrlik`.
