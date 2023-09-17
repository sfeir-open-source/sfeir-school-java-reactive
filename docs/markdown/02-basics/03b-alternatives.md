<!-- .slide: class="two-column" -->
# Consommation

## doOn()
* D'autres opérateurs existent pour agir sur les événements du flux sans l'interrompre.
  * doOnNext : lorsqu'un élément est émis.
  * doOnError : lorsqu'une erreur survient.
  * doOnComplete : lorsque le flux est complété, respectivement.

```java[]
Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

flux
    .doOnNext(item -> System.out.println("Élément émis : " + item))
    .doOnError(error -> System.err.println("Erreur : " + error))
    .doOnComplete(() -> System.out.println("Flux terminé"))
    .subscribe();
```
##--##
# <br>
## block()
* Utilisé pour synchroniquement bloquer le flux ou mono
* Renvoie la dernière valeur émise
* /!\ à utiliser avec attention car déconseillé dans une architecture réactive

```java[]
Mono<String> mono = Mono.just("Hello, World!");
String result = mono.block();
System.out.println(result);
```

Notes:
- doOn : utile débogage et side-effects
- doOnNext jorunalisation, mersure perf
- doOnError gestion erreur, alerte
- DoOnComplete libération ressources, signalement tache finis
- block() antipattern, précaution
- code legacy, ou avec biblio non reactives
- trompeur return last value
- risque de blocage, doit être une exception 
