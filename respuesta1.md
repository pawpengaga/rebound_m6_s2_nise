# Explicación 1

Comentario sobre la base de código

[Volver a inicio](README.md)

---

En esta aplicación, el **flujo de inyección de dependencias (IoC)** y el uso de anotaciones en **Spring Boot** funciona de la siguiente manera:

---

### **1. Configuración inicial: `@SpringBootApplication`**
La clase principal está marcada con `@SpringBootApplication`, que combina:
- `@Configuration`: Permite registrar beans manualmente.
- `@ComponentScan`: Detecta automáticamente clases anotadas con `@Component`, `@Service`, etc.
- `@EnableAutoConfiguration`: Configura Spring Boot automáticamente.

Esta anotación inicia el escaneo de componentes y el registro de beans cuando ejecutas la app.

---

### **2. Registro manual de beans: `@Bean`**
En **`ConfiguracionLista`**:
- La anotación `@Bean("listaAlumnos")` crea un bean llamado `listaAlumnos`, que es una lista de alumnos.
- Este bean estará disponible para ser inyectado donde sea necesario.
```java
@Bean("listaAlumnos")
List<Alumno> getAlumnoList() {
    // Retorna la lista de alumnos.
}
```

---

### **3. Marcado de componentes: `@Component`**
Clases como **`Calculadora`** y **`Profesor`** están marcadas con `@Component`, lo que las convierte en beans gestionados por Spring:
- `@Component("calculadora")`: Define un bean llamado `calculadora`.
- `@Component("profesor")`: Define un bean llamado `profesor`.

Spring los detecta automáticamente y los gestiona.

---

### **4. Inyección de dependencias: `@Autowired` y `@Qualifier`**
- **`@Autowired`**: Indica a Spring que debe inyectar automáticamente una dependencia. 
- **`@Qualifier("beanName")`**: Especifica el nombre exacto del bean que se debe inyectar (útil cuando hay múltiples candidatos).

Ejemplo en `Calculadora`:
```java
@Qualifier("listaAlumnos")
@Autowired
private List<Alumno> alumnos;
```
Aquí, se inyecta el bean llamado `listaAlumnos` en la lista `alumnos`.

En **`Profesor`**:
```java
@Qualifier("calculadora")
@Autowired
private ICalculadora calcula;
```
Se inyecta el bean `calculadora` que implementa `ICalculadora`.

---

### **5. Flujo del programa**
1. **El contenedor de Spring inicia:**
   - Escanea las clases anotadas (`@Component`, `@Bean`).
   - Registra y configura los beans necesarios.
2. **Creación de beans:**
   - `listaAlumnos` se registra con `@Bean`.
   - `calculadora` y `profesor` se registran con `@Component`.
3. **Inyección de dependencias:**
   - Spring inyecta automáticamente las dependencias (`alumnos` en `Calculadora` y `calculadora` en `Profesor`).
4. **Ejecución:**
   - En el método `run` de la clase principal, se llama a `profesor.mostrarAlumnos()`, que utiliza `calculadora.calcularPromedio()` para calcular y mostrar los promedios.

---

### **Diagrama resumido**
```
ReboundM6S2ErickRiveraApplication
    -> @Autowired Profesor
        -> @Autowired ICalculadora (Calculadora)
            -> @Autowired List<Alumno> (listaAlumnos)
```

---

### **Conceptos clave de las anotaciones**
1. **`@Component`**: Marca una clase para ser registrada como bean.
2. **`@Bean`**: Método que registra un bean manualmente en la configuración.
3. **`@Autowired`**: Inyecta automáticamente un bean.
4. **`@Qualifier`**: Especifica cuál bean inyectar si hay más de uno.

Esto te permite desacoplar clases y delegar la gestión de instancias a Spring.