<!-- .slide: -->

# Mono<T>

* 0 à 1 élément : Il émet un élément unique, soit aucun élement.
* Asynchrone et non bloquant : Comme pour le Flux, les opérations sont asynchrones et non bloquantes.
* Pipeline d'opérateurs : Des opérations communes avec le Flux.
  Utilisation commune : Ils sont utiles lorsque vous attendez un résultat unique d'une opération asynchrone.

Notes:
- Exemple : parfait pour un résultat unique de BDD
- généralement lorsqu'on attend une seule valeur
- Comparaison avec l'optional 
