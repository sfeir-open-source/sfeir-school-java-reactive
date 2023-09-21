<!-- .slide: class="" -->

# Création d'un Flux :

- _`Flux.just()`_ : Crée un Flux avec les éléments fournis en paramètre.
```java[]
Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
```
- _`Flux.fromIterable()`_ : Crée un Flux à partir d'une collection ou d'un itérable.
```java[]
List<String> strings = Arrays.asList("Hello", "World");
Flux<String> flux = Flux.fromIterable(strings);
```
- _`Flux.range()`_ : Crée un Flux émettant une séquence d'entiers dans une plage donnée.
```java[]
Flux<Integer> flux = Flux.range(1, 5); // Émet : 1, 2, 3, 4, 5
```
- _`Flux.empty()`_ : Crée un Flux vide, ne produisant aucun élément.
```java[]
Flux<String> flux = Flux.empty();
```
 <!-- .element: class="list-fragment" -->

Notes:
**NATHAN**
- create 
- Flux.fromArray(): À partir d'un tableau.
- Flux.generate(): Utilise un générateur synchrone.
- Flux.fromStream(): À partir d'un Stream Java 8+.
- Flux.from(): À partir d'une source Reactive Streams.
- Flux.interval(): Émet un élément à chaque période spécifiée.



