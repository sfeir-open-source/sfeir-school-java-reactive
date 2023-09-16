<!-- .slide: -->

# Dépendances:

* Reactor Netty : Un ensemble de connecteurs réseau asynchrones et non bloquants basés sur Netty et Project Reactor.
* Reactor Test : Pour écrire des tests unitaires pour vos flux Reactor.
* Reactor Debug Agent : Pour améliorer le débogage des pipelines de flux.
* Cache :
  * Intégré à Reactor avec Mono.cache() et Flux.cache()
  * Ou alors avec la bibliothèque Caffeine pour des opérations plus complexes

Notes:
- Reactor Netty : créer des applications réseau à haute performance, pour divers protocoles HTTP, WebSocket et autres. Idéal pour des systèmes avec de nombreux utilisateurs ou requêtes simultanées.
- Reactor Test : outil essentiel pour s'assurer que votre code réactif est robuste et prêt pour la production. Méthode pour vérifier les flux et mono et même simuler des conditions de backpressure
- Reactor Debug Agent : aide à améliorer le débogage des pipelines de flux. peut attacher des métadonnées supplémentaires aux flux afin d'identifier plus facilement où une erreur pourrait se produire.
- Cache : permettent de mettre en cache le résultat d'une opération réactive. Eviter les appels redondant ou des appels réseau couteux en ressource
- Caffeine : hautement configurable , par exemple, de définir des politiques d'expiration ou d'éviction du cache.
