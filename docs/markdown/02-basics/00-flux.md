<!-- .slide: class="transition bg-pink" -->
# Publier et souscire

##==##
<!-- .slide: -->

# Flux<T>

* 0 à N éléments : Il émet un nombre quelconque d'éléments, y compris aucun.
* Asynchrone et non bloquant : Le consommateur n'attend pas et peut effectuer d'autres tâches en parallèle.
* Pipeline d'opérateurs : Nombreuses opérations et manipulations disponibles (map, filter, flatMap).
* Backpressure : Le flux gère la surcharge en écoutant la capacité de son consommateur.

Notes:
**NATHAN**
- Émet 0 à N éléments, peut être vide ou infini
- Asynchrone et non-bloquant : concept clés 
- Abonnement améliore performance et réactivité.
- Opérateurs comme map (transforme chaque élément), filter, flatMap (en un autre flux) pour des pipelines de données.
- Contrôle de la vitesse du flux entre producteur et consommateur.
