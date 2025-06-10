# PharmacyProject

## Descripción

**PharmacyProject** es una aplicación Java con interfaz gráfica orientada a la gestión de órdenes dentro de una farmacia. El sistema permite validar, confirmar y mostrar detalles de las órdenes realizadas, asegurando un flujo controlado y amigable para el usuario final.

## Características

- Validación de datos antes de procesar una orden.
- Manejo de excepciones personalizadas mediante `OrderException`.
- Interfaz gráfica con formularios de ingreso y confirmación (`OrderForm`, `ConfirmationWindow`).
- Separación clara de responsabilidades a través de clases como `OrderService` y `Order`.

## Estructura del Proyecto

```
PharmacyProject/
├── Order.java                  # Modelo que representa una orden
├── OrderService.java           # Servicio de negocio para validar órdenes
├── OrderForm.java              # Formulario de ingreso de datos
├── ConfirmationWindow.java     # Ventana de confirmación de órdenes válidas
├── OrderException.java         # Excepción personalizada para validaciones
└── PharmacyProject.java        # Clase principal que inicia la aplicación
```

## Requisitos

- Java 8 o superior
- IDE recomendado: Eclipse, IntelliJ IDEA o NetBeans
- No se requieren dependencias externas (uso de Java Swing para GUI)

## Ejecución del Proyecto

1. Clona o descarga el repositorio del proyecto.
2. Abre el proyecto en Eclipse IDE.
3. Ejecuta la clase `PharmacyProject.java` como aplicación Java.
4. Interactúa con el formulario para realizar y confirmar órdenes.

## Manejo de errores

El sistema maneja errores mediante una clase personalizada llamada `OrderException`, la cual permite mostrar mensajes específicos al usuario cuando se detectan inconsistencias en los datos ingresados.

```java
try {
    orderService.validateOrder(order);
} catch (OrderException ex) {
    showError(ex.getMessage());
} catch (Exception ex) {
    showError("Error confirmando orden");
}
```

## Autor

Irma Lizeth Estrada Tobar  
Estudiante de la Facultad de Ingeniería  
Fundación Universitaria Internacional de La Rioja - UNIR
