<!-- .slide: class="" -->

# Opérateurs combinatoires

- _`mergeWith`_ : fusionne deux flux en un seul flux, émettant les éléments dès qu'ils sont disponibles, sans garantie d'ordre
```java[]
Flux<String> someWords = Flux.just("brown", "fox", "jumps");
Flux<String> mergedWords = Flux.just("the", "quick")
  .mergeWith(someWords);
```

- _`concatWith`_ : concatène deux flux l'un derrière l'autre pour former un seul flux continu
```java[]
Flux<String> someWords = Flux.just("brown", "fox", "jumps");
Flux<String> concatenatedWords = Flux.just("the", "quick")
  .concatWith(someWords); // "the quick brown fox jumps"
```

- _`zipWith`_ : combine les éléments de deux flux en utilisant une fonction pour créer des paires ou des tuples
```java[]
Flux<Shape> shapes = Flux.just(Shape.SQUARE, Shape.CIRCLE);
Flux<Color> colors = Flux.just(Color.GREEN, Color.RED);
Flux<Figure> figures = shapes.zipWith(colors,
    (shape, color) -> new Figure(shape, color)); // [{SQUARE, GREEN}, {CIRCLE, RED}]
```
<!-- .element: class="list-fragment" -->

Variantes des opérateurs _`zip`_, _`merge`_, _`concat`_, etc
<!-- .element: class="fragment" -->

Notes:

* MERGEWITH : 
    - mélange 2 flux, 
    - les émissions les plus rapides passent en premier,
    - pas de garantie d'ordre

* CONCATWITH : 
    - prolonge un flux avec un autre flux, 
    - maintient l'ordre de chacun des 2 flux

* ZIPWITH : 
    - combine 2 flux, 
    - créés des pairs à partir des signaux émis par ces 2 flux
