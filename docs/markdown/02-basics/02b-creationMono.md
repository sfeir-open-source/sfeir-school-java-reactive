<!-- .slide: class="" -->

# Création d'un Mono :

- _`Mono.just()`_ : Crée un Mono avec la valeur fournie en paramètre.
```java[]
Mono<Integer> mono = Mono.just(42);
```
- _`Mono.empty()`_ : Crée un Mono vide, ne produisant aucun élément.
```java[]
Mono<String> mono = Mono.empty();
```
- _`Mono.defer()`_ : Crée un Mono à partir d'une logique de génération différée. Utile pour des opérations paresseuses.
```java[]
Mono<Integer> mono = Mono.defer(() -> Mono.just(heavyCalculation()));
```
- _`Mono.fromCallable()`_ : Crée un Mono à partir d'une méthode Callable, généralement utilisée pour des opérations potentiellement lancées.
```java[]
Mono<String> mono = Mono.fromCallable(() -> fetchDataFromExternalService());
```
 <!-- .element: class="list-fragment" -->

Notes:
**NATHAN**
- Mono.justOrEmpty() à partir d'un optional
- Mono.fromSupplier() : à partir d'une tache asynchrone
- Mono.fromFuture() 
- Mono.from(Flux.just("Hello", "From", "Publisher").next()) a partir d'un flux
- Mono.error(new RuntimeException("Une erreur s'est produite"));
