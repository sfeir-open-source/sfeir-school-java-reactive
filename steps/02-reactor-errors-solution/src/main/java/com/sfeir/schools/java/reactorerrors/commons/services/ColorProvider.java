package com.sfeir.schools.java.reactorerrors.commons.services;

import com.sfeir.schools.java.reactorerrors.commons.domain.Color;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import static com.sfeir.schools.java.reactorerrors.commons.domain.Color.*;

public class ColorProvider {

  private static final Random random = new Random();
  public static Flux<Color> getConstantColors() {
    return Flux.just(RED, GREEN, GREEN, YELLOW);
  }


  public Flux<Color> getRandomError(int sizeLimit) {
    return Flux
      .create(sink -> {
        for (int i = 0; i < sizeLimit; i++) {
          if (i == 3) { // Simuler une exception
            sink.error(new RuntimeException("Une erreur s'est produite"));
            return;
          }
          sink.next(randomColor());
        }
        sink.complete();
      });
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
    int randomIndex = random
      .nextInt(Color.values().length);
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
