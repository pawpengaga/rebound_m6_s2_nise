# Explicación 3

Comentario sobre la base de código centrándose en las `anotaciones` 

[Volver a inicio](README.md)

---

Voy a analizar tu proyecto detalladamente y explicar las anotaciones que se utilizan en cada archivo, para que puedas comprender mejor su propósito en el contexto de tu aplicación.

---

### **1. Anotaciones Utilizadas en el Proyecto**

#### **1.1. `@SpringBootApplication`**
- **Archivo**: `ReboundM6S2ErickRiveraApplication`
- Combina las siguientes tres anotaciones:
  - `@Configuration`: Marca esta clase como una fuente de configuración para Spring.
  - `@EnableAutoConfiguration`: Permite que Spring Boot configure automáticamente los beans según las dependencias incluidas en el proyecto.
  - `@ComponentScan`: Escanea los paquetes para encontrar y registrar componentes, servicios, y otras clases marcadas con anotaciones como `@Component`.

```java
@SpringBootApplication
public class ReboundM6S2ErickRiveraApplication implements CommandLineRunner {
    ...
}
```

Esta anotación es esencial para inicializar y ejecutar la aplicación Spring Boot.

---

#### **1.2. `@Component`**
- **Archivos**: 
  - `Calculadora`
  - `Profesor`
- Marca las clases como componentes que Spring gestionará automáticamente como beans. Esto las hace disponibles para la inyección de dependencias.

```java
@Component("calculadora")
public class Calculadora implements ICalculadora {
    ...
}
```

- El nombre del bean será `calculadora`. Esto es importante porque permite referirse a este bean específicamente usando `@Qualifier`.

```java
@Component("profesor")
public class Profesor {
    ...
}
```

Aquí el bean se llama `profesor`.

---

#### **1.3. `@Autowired`**
- **Archivos**: 
  - `Calculadora`
  - `Profesor`
  - `ReboundM6S2ErickRiveraApplication`
- Se utiliza para realizar la **inyección de dependencias** automáticamente.
- Permite que Spring busque e inyecte el bean adecuado en el punto donde está anotado.

```java
@Autowired
@Qualifier("listaAlumnos")
private List<Alumno> alumnos;
```

- En este caso, inyecta la lista de alumnos configurada en el bean `listaAlumnos`.

```java
@Autowired
@Qualifier("calculadora")
private ICalculadora calcula;
```

- Aquí se inyecta un bean que implementa la interfaz `ICalculadora`. En este caso, Spring encontrará el bean llamado `calculadora`.

---

#### **1.4. `@Qualifier`**
- **Archivos**:
  - `Calculadora`
  - `Profesor`
  - `ReboundM6S2ErickRiveraApplication`
- Especifica **qué bean exacto** debe inyectarse cuando hay varios candidatos.
- Se utiliza junto con `@Autowired`.

```java
@Qualifier("listaAlumnos")
@Autowired
private List<Alumno> alumnos;
```

- Indica que Spring debe inyectar el bean llamado `listaAlumnos`.

---

#### **1.5. `@Configuration`**
- **Archivo**: `ConfiguracionLista`
- Marca la clase como una fuente de configuración que define uno o más beans gestionados por Spring.

```java
@Configuration
public class ConfiguracionLista {
    ...
}
```

Esto permite definir beans manualmente.

---

#### **1.6. `@Bean`**
- **Archivo**: `ConfiguracionLista`
- Define un bean explícito que será gestionado por el contenedor de Spring.

```java
@Bean("listaAlumnos")
List<Alumno> getAlumnoList() {
    ...
}
```

El método devuelve una lista de alumnos que será registrada como un bean llamado `listaAlumnos`.

---

### **2. Flujo General del Proyecto**
1. **Definición de Beans y Configuración:**
   - La clase `ConfiguracionLista` define un bean explícito llamado `listaAlumnos`, que es una lista de objetos `Alumno`.

2. **Comportamiento de `Calculadora`:**
   - La clase `Calculadora` utiliza `@Autowired` y `@Qualifier` para obtener la lista de alumnos (`listaAlumnos`).
   - Implementa el método `calcularPromedio`, que recorre las notas de los alumnos y calcula sus promedios.

3. **Interacción del Profesor:**
   - La clase `Profesor` utiliza `@Autowired` y `@Qualifier` para obtener una instancia de `Calculadora` (`calculadora`).
   - Llama al método `calcularPromedio` y muestra los resultados.

4. **Punto de Entrada:**
   - En la clase principal, `ReboundM6S2ErickRiveraApplication`, se utiliza `@Autowired` y `@Qualifier` para inyectar el bean `Profesor`.
   - Durante la ejecución (`run`), llama al método `mostrarAlumnos`, que calcula y muestra los promedios.

---

### **Resumen**
- **`@Component`** y **`@Autowired`** trabajan juntas para permitir la inyección de dependencias.
- **`@Qualifier`** asegura que el bean correcto se inyecte cuando hay múltiples opciones.
- **`@Configuration`** y **`@Bean`** permiten definir beans manualmente para configuraciones específicas.
- La combinación de estas anotaciones facilita la gestión de dependencias y la modularidad del código.

Si tienes dudas sobre alguna parte del flujo o anotación, ¡avísame!