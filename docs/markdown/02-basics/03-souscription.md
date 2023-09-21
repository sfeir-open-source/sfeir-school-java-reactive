<!-- .slide: class="two-column" -->
# Souscription

## Flux
* La souscription (subscribe) signifie démarrer l'exécution du flux de données et réagir aux éléments émis.

* Utilisation de la méthode subscribe() 
* Possibilités de fournir des lambdas

```java[]
Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

flux.subscribe(
    item -> System.out.println("Élément émis : " + item),
    error -> System.err.println("Erreur : " + error),
    () -> System.out.println("Flux terminé")
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

Notes:
**NATHAN**
- Avant souscription, Flux est froid càd inactif
- 3 lambdas possibles, 1 pour les éléments, 1 erreurs, 1 fin
- Souscription multiples possible
- Comme Flux Mono est Lazy: sans souscription ils ne font rien
