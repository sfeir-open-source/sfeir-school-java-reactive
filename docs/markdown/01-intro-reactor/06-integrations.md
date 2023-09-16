<!-- .slide: -->

# Intégrations :

* Base de données
  * R2DBC : pour utiliser reactor avec des BDD relationnelles
  * MongoDB Reactive streams
  * Cassandra Reactive
* Spring WebFlux : Une intégration naturelle pour créer des APIs réactives.
* Reactive Streams : Reactor est conforme à la spécification Reactive Streams.
* WebSocket : Vous pouvez gérer des connexions WebSocket de manière réactive.
* Kafka, RabbitMQ : Les clients réactifs pour ces services de messagerie sont disponibles.

Notes:
- BDD : vous pouvez effectuer des opérations sur la base de données de manière non-bloquante et avec la gestion du backpressure
- webflux : permettant de créer des APIs REST réactives facilement intégrées avec d'autres composants Spring.
- reactive stream : permet l'interopérabilité avec d'autres bibliothèques et frameworks qui respectent cette spécification.
- websocket : particulièrement utile pour des applications en temps réel où la latence et l'utilisation des ressources sont des préoccupations majeures
