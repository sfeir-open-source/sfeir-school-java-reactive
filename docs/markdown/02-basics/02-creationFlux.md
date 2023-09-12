<!-- .slide: -->

# Création d'un Flux :

* _`Flux.just()`_ : Crée un Flux avec les éléments fournis en paramètre.
```java[]
Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
```
* Flux.fromIterable() : Crée un Flux à partir d'une collection ou d'un itérable.
```java[]
List<String> strings = Arrays.asList("Hello", "World");
Flux<String> flux = Flux.fromIterable(strings);
```
* Flux.range() : Crée un Flux émettant une séquence d'entiers dans une plage donnée.
```java[]
Flux<Integer> flux = Flux.range(1, 5); // Émet : 1, 2, 3, 4, 5
```
* Flux.empty() : Crée un Flux vide, ne produisant aucun élément.
```java[]
Flux<String> flux = Flux.empty();
```
