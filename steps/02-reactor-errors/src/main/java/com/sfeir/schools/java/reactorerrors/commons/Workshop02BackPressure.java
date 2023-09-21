package com.sfeir.schools.java.reactorerrors.commons;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Workshop02BackPressure {

  /**
   * Dans un cas où un traitement est plus lent que le flux émet des valeurs
   * On souhaite que le flux produise plutôt une erreur
   */
  static Flux<Integer> onBackpressureError() {
    // produit un flux qui émet jusqu'à 10 valeurs
    Flux<Integer> numbers = Flux.range(1, 10);

    // on doit produire une erreur si le flux est consommé plus lentement qu'il est produit
    return numbers // déclencher une erreur automatiquement en cas de surcharge

      // le concatMap ici simule un traitement qui dure 100ms par éléments du flux, donc plus lent que l'émission
      .concatMap(a -> Mono.delay(Duration.ofMillis(100)).thenReturn(a));
  }

  /**
   * Dans un cas où un traitement est plus lent que le flux émet des valeurs
   * On souhaite ignorer des valeurs du flux pour suivre le rythme
   */
  static Flux<Integer> onBackpressureDrop() {
    // produit un flux de 5 élements, démarre à 5
    Flux<Integer> numbers = Flux.range(5, 10);

    return numbers // on ignore les éléments qu'on est pas en mesure de traiter à temps
      // le concatMap ici simule un traitement qui dure 100ms par éléments du flux, donc plus lent que l'émission
      .concatMap(a -> Mono.delay(Duration.ofMillis(100)).thenReturn(a))
    ;
  }

  /**
   * Dans un cas où un traitement est plus lent que le flux émet des valeurs
   * On choisit de limiter le nombre d'éléments qu'on prélève à la fois
   */
  static Flux<Long> limitRateToFewerThanFiveElements() {
    // Flux sans fin émettant à chaque ms
    Flux<Long> numbers = Flux.interval(Duration.ofMillis(1));

    return numbers //recevoir maximum 20 éléments à la fois
      // simuler 10ms de traitement par élément -> un "batch" => 200ms
      .concatMap(a -> Mono.delay(Duration.ofMillis(10)).thenReturn(a))
      .take(Duration.ofMillis(50)); // on préleve des éléments pendant un maximum 50ms
  }

  /**
   * Dans un cas où flux émet trop de données dans un certain laps de temps
   * On choisis d'échantilloner la lecture de valeur
   */
  static Flux<Integer> sampleFluxToASingleElementRemaining() {
    // produit un flux de 20 élements
    Flux<Integer> numbers = Flux.range(1, 20);

    return numbers // limiter le nombre d'élément par échantillonage, 1 seul doit rester
      ;
  }

  /**
   * Dans un cas où flux émet trop de données dans un certain laps de temps
   * On choisis d'échantilloner la lecture de valeur pour ne pas dépasser une limite
   */
  static Flux<Long> sampleFewerThanTwentyElementsRemaining() {
    // un Flux qui produit une valeur à chaque ms, l'émetteur se limite 100 éléments
    Flux<Long> numbers = Flux.interval(Duration.ofMillis(1)).take(100);

    return numbers // on échantillone avec une périodicité de sorte à garder au maximum que 20 valeurs
      ;
  }
}
