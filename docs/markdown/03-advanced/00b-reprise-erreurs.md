<!-- .slide: class="two-column" -->

# Récupération et reprise
# <br>
## retry()
* Lorsqu'une erreur se produit, on tente de se réabonner au Flux ou Mono un certain nombre de fois 

```java[]
Flux.just(1, 2, 0)
    .map(i -> 10 / i)
    .retry(1)
    .subscribe(System.out::println, System.err::println);

```
##--##
# <br>
# <br>
## retryWhen()
* Permet une logique de reprise plus complexe, comme des délais exponentiels entre les tentatives.

```java[]
Flux.just(1, 2, 0)
    .map(i -> 10 / i)
    .retryWhen(Retry.backoff(2, Duration.ofSeconds(1)))
    .subscribe(System.out::println, System.err::println);

```

Notes: 
- Utile pour les opérations qui peuvent échouer
- Ex : appels réseau
- retry() sans arg, réessaie à l'infini attention
- retryWhen délai exponentielle pour éviter de surcharger 
- backOff augmente le temps entre chaque tentative jusqu'à un max
