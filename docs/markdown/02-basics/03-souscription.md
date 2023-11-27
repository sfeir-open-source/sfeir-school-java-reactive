<!-- .slide: class="two-column" -->
# Souscription

## Flux
* La souscription (subscribe) signifie démarrer l'exécution du flux de données et réagir aux éléments émis.

* Utilisation de la méthode subscribe() 
* Possibilités de fournir jusqu'à 4 lambdas

```java[]
Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

flux.subscribe(
    item -> System.out.println("Élément émis : " + item),
    error -> System.err.println("Erreur : " + error),
    () -> System.out.println("Flux terminé"),
    s -> s.request(2)
);
```
##--##
# <br>
## Mono
* Idem au Flux  

```java[]
Mono<String> mono = Mono.just("Hello, World!");

mono.subscribe(
    item -> System.out.println("Élément émis : " + item),
    error -> System.err.println("Erreur : " + error),
    () -> System.out.println("Mono terminé")
);

```

![center-w-1000](./assets/images/subscribeForFlux.svg)

Notes:
**NATHAN**
- Avant souscription, Flux est froid càd inactif
- 2 consumers puis un runnable puis un consumer
- 5 subscribe différents (sans, avec et avec )
- Souscription multiples possible
- Comme Flux Mono est Lazy: sans souscription ils ne font rien
