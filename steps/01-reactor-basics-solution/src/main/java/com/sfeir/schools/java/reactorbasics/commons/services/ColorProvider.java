package com.sfeir.schools.java.reactorbasics.commons.services;

import com.sfeir.schools.java.reactorbasics.commons.domain.Color;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.sfeir.schools.java.reactorbasics.commons.domain.Color.*;

public class ColorProvider {

  private final Random RANDOM = new Random();

  public static Flux<Color> getConstantColors() {
    return Flux.just(RED, GREEN, GREEN, YELLOW);
  }


  public Flux<Color> getRandomColors(int sizeLimit) {
    return getInfiniteRandomColors()
      .take(sizeLimit);
  }

  public Flux<Color> getInfiniteRandomColors() {
    return Flux
      .generate(sink -> sink.next(randomColor()))
      .cast(Color.class);
  }

  public Color randomColor() {
    int randomIndex = RANDOM.nextInt(Color.values().length);
    return Color.values()[randomIndex];
  }

  public List<Color> randomListColor(int size) {
    List<Color> randomColorList = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      randomColorList.add(randomColor());
    }

    return randomColorList;
  }
}
