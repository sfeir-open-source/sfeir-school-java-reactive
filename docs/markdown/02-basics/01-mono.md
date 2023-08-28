<!-- .slide: -->

# Mono<T>

* 0 à 1 éléments : Il émet un élément unique, soit aucun élement.
* Asynchrone et non bloquant : Comme pour le Flux, les opérations sont asynchrones et non bloquantes.
* Pipeline d'opérateurs : Même opérations que pour un Flux.
  Utilisation commune : Ils sont utiles lorsque vous attendez un résultat unique d'une opération asynchrone.
