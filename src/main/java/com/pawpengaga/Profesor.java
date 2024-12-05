package com.pawpengaga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component("profesor")
public class Profesor {

  @Qualifier("calculadora")
  @Autowired
  private ICalculadora calcula;

  public void mostrarAlumnos(){
    System.out.println(calcula.calcularPromedio().toString());
  }

}
