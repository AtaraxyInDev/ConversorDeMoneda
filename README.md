# Conversor de Moneda

Este proyecto es un **Conversor de Moneda** que permite a los usuarios convertir entre diferentes monedas utilizando tasas de cambio actualizadas. La aplicación hace uso de una API para obtener las tasas de cambio en tiempo real y proporciona un menú interactivo para una experiencia de usuario sencilla.

## Características

- Conversión de monedas en tiempo real.
- Menú interactivo para seleccionar opciones.
- Visualización de tasas de cambio disponibles.
- Soporte para las monedas más comunes.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Gson**: Biblioteca para el procesamiento de JSON.

## Instalación

Para ejecutar este proyecto, sigue estos pasos:

1. Clona este repositorio:
   ```bash
   git clone https://github.com/AtaraxyInDev/ConversorDeMoneda.git
   ```
   
2. Navega al directorio del proyecto:
   ```bash
   cd ConversorDeMoneda
   ```

3. Asegúrate de tener Java instalado en tu sistema. Puedes descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

4. Compila y ejecuta el proyecto:
   ```bash
   javac -cp lib/gson-2.11.0.jar src/*.java -d out
   java -cp out:lib/gson-2.11.0.jar Main
   ```

## Uso

Al iniciar la aplicación, se presentará un menú interactivo con las siguientes opciones:

1. **Convertir moneda**: Permite al usuario seleccionar la moneda a convertir y la cantidad.
2. **Ver tasas de cambio**: Muestra las tasas de cambio disponibles.

## Desarrollado por

Este proyecto ha sido desarrollado por **AtaraxInDev** como parte del programa **Oracle One Next Education** con **Alura Latam**.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
