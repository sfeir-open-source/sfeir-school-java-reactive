package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import reactor.core.publisher.Flux;

public class WorkshopFluxTransformations {

  public static Flux<String> transformShapeIntoSymbol() {
    return ShapeProvider.getConstantShapes()
      .map(Shape::getSymbol);
  }
}
