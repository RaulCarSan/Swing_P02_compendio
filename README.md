# Gestión de Apartamentos Andarax

## Descripción del proyecto
Aplicación de escritorio desarrollada en **Java Swing** para la gestión de apartamentos turísticos de la empresa ficticia.  
Permite dar de alta pisos introduciendo datos del arrendador y del inmueble mediante una interfaz gráfica completa, accesible y validada.

El proyecto ha sido realizado como práctica del ciclo formativo de **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

## Contenido de la publicación
- Código fuente Java del proyecto
- Interfaces gráficas desarrolladas con Swing
- Recursos gráficos (iconos e imágenes)
- Ejecutable de la aplicación (JAR)
- Fichero README con la documentación básica

## Desarrollo del proyecto
La aplicación está compuesta por varias ventanas:

### Ventana Principal
- Menú superior con opciones:
  - **Archivo → Salir**
  - **Registro → Alta Pisos / Baja Pisos**
  - **Ayuda → Acerca de**
- Botones de acceso rápido para:
  - Alta de pisos
  - Baja de pisos (no implementado)

### Ventana de Alta de Pisos
Formulario completo que permite introducir:

#### Datos del arrendador
- Nombre
- Apellidos
- DNI (validado por expresión regular)
- Teléfono (validado numéricamente)

#### Datos del inmueble
- Dirección
- Provincia
- Fechas de alta y fin de disponibilidad
- Número de huéspedes, dormitorios, baños y camas
- Tipo de cama
- Gestión de niños y edad
- Galería de imágenes del piso
- Estado de conservación del inmueble

#### Precio mínimo
El precio mínimo se calcula automáticamente en función de:
- Número y tipo de camas
- Número de baños
- Presencia de niños

#### Resumen
- Pestaña con resumen del arrendador
- Pestaña con resumen del inmueble
- Actualización automática de los datos

#### Botones de acción
- **Imprimir a documento**: vuelca los datos al resumen
- **Nuevo**: limpia el formulario
- **Guardar**: valida y guarda el registro

## Despliegue

### Windows
1. Descargar el archivo `Swing_P02_Compendio.jar`
2. Hacer doble clic sobre el archivo  
   *(es necesario tener Java instalado)*

### Línea de comandos
1. Abrir una terminal o consola
2. Situarse en la carpeta donde se encuentre el `.jar`
3. Ejecutar:
   ```bash
   java -jar Swing_P02_Compendio.jar
   
## Construido con
- Java SE
- Swing
- Eclipse IDE
- Git
- GitHub

## Versionado
El proyecto utiliza **Git** como sistema de control de versiones.  
Repositorio alojado en **GitHub**, permitiendo el seguimiento de cambios y la gestión del código fuente.

## Autores
- **Raúl Carmona Sánchez**

## Licencia
Este proyecto se distribuye bajo una licencia de **uso educativo y académico**, sin fines comerciales.
