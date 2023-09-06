package com.sfeir.schools.java.reactorerrors.commons.services;

import com.sfeir.schools.java.reactorerrors.commons.domain.Color;
import com.sfeir.schools.java.reactorerrors.commons.domain.Figure;
import com.sfeir.schools.java.reactorerrors.commons.domain.Shape;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.random.RandomGenerator;

import static com.sfeir.schools.java.reactorerrors.commons.domain.Shape.*;

public class FigureProvider {

  private final AtomicBoolean hasThrown = new AtomicBoolean(false);
  ColorProvider colorProvider = new ColorProvider();
  ShapeProvider shapeProvider = new ShapeProvider();

  public Flux<Figure> get5FiguresWithConditionalError() {
    return Flux.create(sink -> {
      for (int i = 0; i < 5; i++) {
        if (i == 3 && !hasThrown.get()) { // Générer une exception au 4e élément seulement lors du premier appel
          hasThrown.set(true);  // Mettre à jour le statut pour ne pas lancer l'exception à nouveau
          sink.error(new RuntimeException("Erreur générée lors du premier appel"));
          return;
        }

        // Générer une figure aléatoire
        Shape shape = shapeProvider.randomShape();
        Color color = colorProvider.randomColor();
        Figure figure = new Figure(shape, color);

        // Émettre la figure
        sink.next(figure);
      }
      sink.complete();
    });
  }
}
