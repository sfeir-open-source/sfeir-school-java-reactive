<!-- .slide: -->

# Dépendances:

* Reactor Netty : partie réseau et web basée sur le serveur Netty
* Reactor Test : Pour écrire des tests unitaires pour vos flux Reactor.
* Reactor Debug Agent : Pour améliorer le débogage des pipelines de flux.
* Cache :
  * Intégré à Reactor avec Mono.cache() et Flux.cache()
  * Ou alors avec la bibliothèque Caffeine pour des opérations plus complexes

Notes:
**NATHAN**
- Reactor Netty: Pour des apps réseau **haute performance**. Supporte **plusieurs protocoles**. Idéal pour gérer **beaucoup d'utilisateurs ou requêtes**.
- Reactor Test: Outil pour tester la robustesse du code réactif. Permet de **simuler des conditions de backpressure**.
- Reactor Debug Agent: Améliore le débogage en ajoutant des métadonnées aux flux. Aide à **localiser les erreurs**.
- Cache: **Économise** des ressources en mettant en cache les résultats d'opérations réactives.
- Caffeine: Configurable pour des politiques de cache avancées, comme l'expiration ou l'éviction.
