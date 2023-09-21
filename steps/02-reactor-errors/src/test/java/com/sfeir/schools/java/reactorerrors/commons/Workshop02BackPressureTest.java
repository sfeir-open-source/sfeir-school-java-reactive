package com.sfeir.schools.java.reactorerrors.commons;

import org.junit.jupiter.api.Test;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class Workshop02BackPressureTest {

  // error strategy, detecting backpressure will cancel the subscription and throw on overflow error
  @Test
  void testOnBackpressureError() {
    AtomicInteger tick = new AtomicInteger();

    Flux<Integer> flux = Workshop02BackPressure.onBackpressureError()
      .doOnNext(a -> tick.getAndIncrement());

    // overflow failure occurs
    assertThrows(Exceptions.failWithOverflow().getClass(), flux::blockLast);
    // nothing was consumed
    assertEquals(0, tick.get());
  }

  // drop strategy, when backpressure occurs,
  @Test
  void testOnBackpressureDrop() {
    AtomicInteger tick = new AtomicInteger();

    Flux<Integer> flux = Workshop02BackPressure.onBackpressureDrop()
      .doOnNext(a -> tick.getAndIncrement());

    assertEquals(0, tick.get());

    List<Integer> values = flux.collectList().block();
    assertEquals(1, tick.get());
    assertEquals(1, values.size());
    assertEquals(List.of(5), values);
  }


  /*
    limitRate
   */

  @Test
  void testlimitRateToFewerThanFiveElements() {
    List<Long> values = Workshop02BackPressure.limitRateToFewerThanFiveElements()
      .collectList()
      .block();

    assertTrue(values.size() < 5);
    System.out.println("Values: " + values);
  }


  /*
    SAMPLE
   */

  @Test
  void testSampleFluxWithoutDelay() {
    List<Integer> values = Workshop02BackPressure.sampleFluxToASingleElementRemaining()
      .collectList()
      .block();

    assertEquals(1, values.size());
    assertEquals(List.of(20), values);
  }

  @Test
  void testSampleFewerThanTwentyElementsRemaining() {
    List<Long> values = Workshop02BackPressure.sampleFewerThanTwentyElementsRemaining()
      .collectList()
      .block();

    assertTrue(values.size() < 20);
  }

}
