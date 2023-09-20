<!-- .slide: class="transition bg-pink" -->
# Les opérateurs

##==##
<!-- .slide: -->

# Les opérateurs

Les opérateurs de Reactor permettent de
- manipuler et transformer la donnée
- trier les résultats
- composer une chaine complexe et flexible

Notes:

Outre la souscription, les opérateurs existent pour manipuler et transformer les séquences.

à la manière des Streams en java,

on peut combiner plusieurs opérateurs pour les manipuler avec une grande flexibilité

et effectuer des traitement complexes et lisibles

##==##
<!-- .slide: class="" -->

# Opérateurs de transformation

- _`map`_ : transformer chaque élément d'un flux en un autre élément en appliquant une fonction
```java[]
Flux<Integer> squaredNumbers = Flux.just(1, 2, 3, 4, 5)
    .map(number -> number * number);
```

- _`flatMap`_ : transformer chaque élément d'un flux en un autre flux, puis de fusionner ces flux résultants en un seul flux
```java[]
Function<Integer, Flux<Double>> mapper = x -> Flux.just(Math.sqrt(x));
Flux<Double> doubleFlux = Flux.just(1, 2, 3)
    .flatMap(mapper);
```

- _`groupBy`_ : regrouper les éléments d'un flux en fonction d'un critère de regroupement
```java[]
Flux<GroupedFlux<Integer, Integer>> groupedByParity = Flux.just(1, 2, 3, 4, 5)
    .groupBy(number -> number % 2);
```
 <!-- .element: class="list-fragment" -->

Notes:

* MAP : 
    - permet de transformer chaque élément d'un flux en un autre élément en appliquant une fonction

* FLATMAP : 
    - permet de transformer chaque élément d'un flux en un autre flux
    - puis de fusionner ces flux résultants en un seul flux. 
    - utile si besoin d'opérations asynchrones pour chaque élément du flux.

* GROUPBY : 
    - regroupe les éléments d'un flux en fonction d'un critère de regroupement
    - créé un sous-flux pour chaque groupe distinct



