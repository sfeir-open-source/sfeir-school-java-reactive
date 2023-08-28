<!-- .slide: -->

# Flux<T>

* 0 à N éléments : Il émet un nombre quelconque d'éléments, y compris aucun.
* Asynchrone et non bloquant : Le consommateur n'attend pas et peut effectuer d'autres tâches en parallèle.
* Pipeline d'opérateurs : Nombreuses opérations et manipulations disponibles (map, filter, flatMap).
* Backpressure : Le flux gère la surcharge en écoutant la capacité de son consommateur.
