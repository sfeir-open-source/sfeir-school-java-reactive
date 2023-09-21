<!-- .slide: class="two-column" -->

# Logs d'erreurs
# <br>
## onErrorMap()
* Transforme une exception en une autre.

```java[]
Flux.just(1, 2, 0)
    .map(i -> 10 / i)
    .onErrorMap(original -> new CustomException("Une erreur est survenue", original))
    .subscribe(System.out::println, System.err::println);
```
##--##
# <br>
# <br>
## doOnError()
* Effectuez une action (comme la journalisation) en cas d'erreur, sans modifier le flux.
```java[]
Flux.just(1, 2, 0)
    .map(i -> 10 / i)
    .doOnError(e -> log.error("Erreur rencontrée", e))
    .subscribe(System.out::println, System.err::println);
```

Notes:
**NATHAN**

- Exception personnaliser : améliore débogage et gestion d'erreur 
- Convertion d'exception bas niveau en exception métier plus significative
- doOnError : ne résout pas l'erreur mais permet de réagir 
- Autre exemple : lancement d'alerte possible comme l'envoie d'un mail
