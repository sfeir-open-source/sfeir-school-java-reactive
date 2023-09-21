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
**NATHAN**
- BDD: **Driver Non obligatoire**, Opérations non-bloquantes et gestion du backpressure.
- Webflux: Framework utilisant reactor Création d'APIs REST réactives et intégration Spring.
- Reactive Stream: **base pour reactor**, Interopérabilité avec d'autres frameworks conformes.
- Websocket: Idéal pour les apps en temps réel, optimise latence et ressources.
