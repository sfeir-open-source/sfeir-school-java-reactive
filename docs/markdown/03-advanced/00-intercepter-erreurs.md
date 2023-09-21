<!-- .slide: class="two-column" -->

# Intercepter les erreurs
# <br>
## onErrorReturn()
* Fournit une valeur par défaut lorsqu'une erreur est rencontrée

```java[]
Flux.just(1, 2, 0)
    .map(i -> 10 / i)
    .onErrorReturn(0)
    .subscribe(System.out::println);

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
    .subscribe(System.out::println);
```

Notes:
**NATHAN**

- onErrorReturn : maintiens le flux actif, éviter fin abrupte
- Utile si on ne se soucit pas de la nature de l'erreur
- onErrorResume : par ex permet d'émettre un autre Flux correctif
- introduire une logique métier complexe dans le traitement des erreurs
