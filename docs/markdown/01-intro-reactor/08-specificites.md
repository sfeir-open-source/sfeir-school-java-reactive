<!-- .slide: -->

# Les spécificités de ProjectReactor:

* Réactivité optimisée : Conçu spécifiquement pour la programmation réactive, adapté à la gestion asynchrone des flux de données
* Intégration Spring : Intégration fluide avec Spring via Spring WebFlux
* Types distincts : Utilisation de Flux pour flux de 0 à N éléments et Mono pour flux de 0 ou 1 élément
* Création simplifiée
* Fonctionnalités : Backpressure, gestion des erreurs
* Interopérabilité : Intégration avec d'autres bibliothèques réactives telles que RxJava.


Notes:
**NATHAN**
- Réactivité optimisée: Meilleure utilisation des ressources et performances accrues, idéal pour les applications à grande échelle.
- Intégration Spring: Cohérence et facilité d'intégration avec la pile Spring.
- Types distincts: Deux types principaux (Flux et Mono) pour une API plus claire.
- Création simplifiée: Méthodes variées pour créer facilement des flux.
- Backpressure: Contrôle du débit de données entre producteur et consommateur.
- Gestion des erreurs: Opérateurs dédiés pour des chaînes de traitement robustes.
- Interopérabilité: Conversion aisée entre Flux/Mono et les types de RxJava.
