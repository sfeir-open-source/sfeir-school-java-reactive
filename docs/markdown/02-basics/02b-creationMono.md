<!-- .slide: -->

# Création d'un Mono :

* Mono.just() : Crée un Mono avec la valeur fournie en paramètre.
```java[]
Mono<Integer> mono = Mono.just(42);
```
* Mono.empty() : Crée un Mono vide, ne produisant aucun élément.
```java[]
Mono<String> mono = Mono.empty();
```
* Mono.defer() : Crée un Mono à partir d'une logique de génération différée. Utile pour des opérations paresseuses.
```java[]
Mono<Integer> mono = Mono.defer(() -> Mono.just(heavyCalculation()));
```
* Mono.fromCallable() : Crée un Mono à partir d'une méthode Callable, généralement utilisée pour des opérations potentiellement lancées.
```java[]
Mono<String> mono = Mono.fromCallable(() -> fetchDataFromExternalService());
```
