<!-- .slide: class="two-column" -->

# Intercepter les erreurs
# <br>
## onErrorReturn()
* Fournit une valeur par défaut lorsqu'une erreur est rencontrée

```java[]
Flux.just(1, 2, 0)
    .map(i -> 10 / i)
    .onErrorReturn(0)
    .subscribe(System.out::println, System.err::println);

```

##--##
# <br>
# <br>
## onErrorResume()
* Plus flexible que onErrorReturn()
* Permet de fournir un autre Flux ou Mono comme remplacement
```java[]
Flux.just(1, 2, 0)
    .map(i -> 10 / i)
    .onErrorResume(e -> Flux.just(-1))
    .subscribe(System.out::println, System.err::println);
```
