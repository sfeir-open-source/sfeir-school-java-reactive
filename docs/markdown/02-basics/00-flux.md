<!-- .slide: class="transition bg-pink" -->
# Les séquences de données

##==##
<!-- .slide: -->

# Flux<T>

* 0 à N éléments : Il émet un nombre quelconque d'éléments, y compris aucun.
* Asynchrone et non bloquant : Le consommateur n'attend pas et peut effectuer d'autres tâches en parallèle.
* Pipeline d'opérateurs : Nombreuses opérations et manipulations disponibles (map, filter, flatMap).
* Backpressure : Le flux gère la surcharge en écoutant la capacité de son consommateur.

Notes:
- peut émettre entre 0 et N éléments, où N peut être un grand nombre ou même infini. Peut également être vide
- l'asynchronicité et la non-bloquantitude sont des concepts clés.
- Lorsqu'un consommateur s'abonne à un flux. améliorer l'utilisation des ressources, réduit les temps d'attente et améliore la réactivité de l'application.
- Ex : map  permet de transformer chaque élément, filter de n'émettre que les éléments qui satisfont une condition donnée, et flatMap peut transformer un élément en un autre flux. Ces opérateurs peuvent être enchaînés pour créer des pipelines de traitement de données complexes mais compréhensibles.
- permet à un consommateur de flux de signaler au producteur combien d'éléments il peut gérer, évitant ainsi que le consommateur ne soit submergé par un flux de données trop rapide


