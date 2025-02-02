package com.pawpengaga;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * El comportammiento por defecto de los componentes es llamarse como la clase pero con la letra minuscula
 * 
 * Cosas que usa Spring
 * 
 * Repositorio: Algo que ofrece datos
 * Servicios: 
 * Componentes: Una clase que se va a poder usar, que tiene la facultad de poder inyectarse
 * 
 * La calculadora depende del alumno, es por eso que se usa el qualificador en el metodo main
 * 
 * El profesor dio una explicacion intesesante a partir de las 21:48
 * 
 */

@AllArgsConstructor
@Data
@Component("calculadora")
public class Calculadora implements ICalculadora {

  /**
   * @Qualifier da el nombre por el que nos referimos a la inyeccion de dependencias
   */

  @Qualifier("listaAlumnos")
  @Autowired
  private List<Alumno> alumnos;

  @Override
  public List<Alumno> calcularPromedio() {
    
    for (Alumno alumno : alumnos) {
      float sumNotas = 0;
      for (int i = 0; i < alumno.getNotas().size(); i++) {
        sumNotas += alumno.getNotas().get(i);
      }
      float promedio = sumNotas / (alumno.getNotas().size());
      alumno.setPromedio(promedio);
    }

    return alumnos;
    
  }

}
