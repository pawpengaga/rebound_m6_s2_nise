# Explicación 2

Comentario sobre la inyección de dependencias

[Volver a inicio](README.md)

---


### **¿Qué es la Inyección de Dependencias (DI)?**

La **inyección de dependencias (Dependency Injection, DI)** es un patrón de diseño que consiste en **proveer objetos necesarios (dependencias)** a una clase desde **fuera de ella**, en lugar de que la clase cree esos objetos por sí misma.

### **Ejemplo de problema sin DI**
Supón que tienes una clase `Profesor` que depende de una clase `Calculadora`:

```java
public class Profesor {
    private Calculadora calculadora;

    public Profesor() {
        this.calculadora = new Calculadora(); // Creamos la dependencia aquí.
    }

    public void mostrarAlumnos() {
        List<Alumno> alumnos = calculadora.calcularPromedio();
        System.out.println(alumnos);
    }
}
```

**Problemas de este enfoque:**
1. **Acoplamiento fuerte:** 
   - `Profesor` está acoplado a `Calculadora` porque crea la instancia.
   - Si cambias cómo funciona `Calculadora`, debes modificar `Profesor`.
   
2. **Dificultad para probar:**
   - No puedes reemplazar `Calculadora` fácilmente por una versión "mock" para pruebas.
   
3. **Falta de flexibilidad:**
   - No puedes sustituir `Calculadora` por otra implementación (ej., `CalculadoraAvanzada`) sin cambiar el código de `Profesor`.

---

### **Solución con Inyección de Dependencias**

Con DI, **no es la clase la que crea sus dependencias, sino que se le pasan desde fuera (inyección)**.

```java
public class Profesor {
    private Calculadora calculadora;

    // La dependencia es inyectada en el constructor.
    public Profesor(Calculadora calculadora) {
        this.calculadora = calculadora;
    }

    public void mostrarAlumnos() {
        List<Alumno> alumnos = calculadora.calcularPromedio();
        System.out.println(alumnos);
    }
}
```

**Ventajas:**
1. **Desacoplamiento:** 
   - `Profesor` no sabe cómo se construye `Calculadora`, solo la usa.
   - Puedes cambiar la implementación de `Calculadora` sin modificar `Profesor`.
   
2. **Fácil de probar:**
   - Puedes inyectar una versión "mock" de `Calculadora` para pruebas.
   
3. **Flexibilidad:**
   - Puedes inyectar cualquier clase que implemente la misma interfaz (`ICalculadora`).

---

### **DI en Spring**
En Spring, DI se hace automáticamente con la ayuda de anotaciones como `@Autowired`. Spring se encarga de:
1. Crear los objetos necesarios.
2. Resolver las dependencias.
3. Inyectar las dependencias donde se necesiten.

En tu aplicación, la inyección ocurre así:
```java
@Qualifier("calculadora") // Especificamos el bean a inyectar
@Autowired // Spring inyecta automáticamente esta dependencia
private ICalculadora calcula;
```

- `Profesor` no sabe cómo se construye `Calculadora`.
- Solo indica que necesita algo que implemente `ICalculadora`.
- Spring crea `Calculadora` (usando `@Component`) y la inyecta.

---

### **Implicancias clave de la Inyección de Dependencias**
1. **Desacoplamiento:** 
   - Las clases no dependen directamente de otras clases concretas. Usan **interfaces** o instancias que se pasan dinámicamente.

2. **Facilidad para cambiar implementaciones:**
   - Cambiar de `Calculadora` a `CalculadoraAvanzada` solo requiere modificar la configuración del bean, no el código de la clase.

3. **Pruebas unitarias más simples:**
   - Puedes inyectar implementaciones simuladas (mocks) para probar.

4. **Control centralizado:**
   - El contenedor de Spring administra todos los objetos (beans), lo que mejora el control sobre las dependencias.

---

### **Resumiendo**
La inyección de dependencias es como **delegar la responsabilidad de "buscar los recursos necesarios" al framework** (en este caso, Spring). Esto simplifica el desarrollo, hace el código más flexible y reduce el acoplamiento.