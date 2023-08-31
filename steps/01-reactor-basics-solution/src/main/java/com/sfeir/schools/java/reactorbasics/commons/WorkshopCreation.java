package com.sfeir.schools.java.reactorbasics.commons;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import com.sfeir.schools.java.reactorbasics.commons.services.ColorProvider;
import reactor.core.publisher.Flux;

import java.util.List;

public class WorkshopCreation {

  public ColorProvider colorProvider;

  public Flux<Color> createFluxColorsWithThreeColors() {
    Color randomColor1 = colorProvider.randomColor();
    Color randomColor2 = colorProvider.randomColor();
    Color randomColor3 = colorProvider.randomColor();

    return Flux.just(randomColor1, randomColor2, randomColor3);
  }

  public Flux<Color> createFluxColorsWithList() {
    List<Color> listColors = colorProvider.randomListColor(3);

    return Flux.fromIterable(listColors);
  }

}
