package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Shape;
import com.sfeir.schools.java.reactorbasics.commons.services.ShapeProvider;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class WorkshopSubscribe {

  public static Disposable subscribeShapeIntoSymbol() {
    return ShapeProvider.getConstantShapes()
      .subscribe(shape -> System.out.println(shape.getSymbol()));
  }

}
