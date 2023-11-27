<!-- .slide: class="" -->

# Opérateurs filtrant

- _`filter`_ : filtrer les éléments d'un flux selon une condition
```java[]
Flux<Integer> evenNumbers = Flux.just(1, 2, 3, 4, 5, 6, 7, 8)
    .filter(number -> number % 2 == 0);
```
- ![center-w-800](./assets/images/filterForFlux.svg)
<!-- .element: class="list-fragment" -->

##==##
<!-- .slide: class="" -->

# Opérateurs filtrant


- _`distinct`_ : élimine les doublons
```java[]
Flux<Integer> distinctNumbers = Flux.just(1, 1, 2, 3, 3, 3, 4, 5, 5)
    .distinct();
```

- _`all`_ : vérifie si tous les éléments d'un flux satisfont une condition donnée
```java[]
Mono<Boolean> allMatch = Flux.just(2, 4, 6, 8)
    .all(number -> number % 2 == 0);
```

- _`any`_ : vérifie si au moins un des éléments d'un flux satisfait une condition donnée
```java[]
Mono<Boolean> anyMatches = Flux.just(1, 2, 3, 5, 7)
    .all(number -> number % 2 == 0);
```
 <!-- .element: class="list-fragment" -->

Notes:
**SYLVAIN**
* FILTER : 
filtrer les éléments d'un flux en fonction d'une condition
les éléments qui ne satisfont pas la condition sont supprimés du flux

* DISTINCT : 
supprime les doublons du flux, garde un seul élément

* ALL : 
teste que tous les éléments du flux satisfont la condition donnée

* ANY
teste qu'au moins un élément du flux valide la condition donnée
