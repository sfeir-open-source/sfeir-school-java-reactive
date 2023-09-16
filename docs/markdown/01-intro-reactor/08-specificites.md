<!-- .slide: -->

# Les spécificités de ProjectReactor:

* Réactivité optimisée : Conçu spécifiquement pour la programmation réactive, adapté à la gestion asynchrone des flux de données
* Intégration Spring : Intégration fluide avec Spring via Spring WebFlux
* Types distincts : Utilisation de Flux pour flux de 0 à N éléments et Mono pour flux de 0 ou 1 élément
* Création simplifiée
* Fonctionnalités : Backpressure, gestion des erreurs
* Interopérabilité : Intégration avec d'autres bibliothèques réactives telles que RxJava.


Notes:
- Réactivité optimisée : optimisant ainsi l'utilisation des ressources système et améliorant les performances. essentielle pour des applications à haute concurrence ou à grande échelle.
- Intégration Spring : permet une cohérence et une intégration facile si vous travaillez déjà avec la pile technologique Spring.
- Types distincts : Project Reactor propose deux types principaux pour gérer les flux de données. Ces types rendent l'API plus claire et plus prévisible.
- Création simplifiée : Reactor offre diverses méthodes pour créer des flux de manière simple et intuitive. Creation  à partir d'une liste, d'un générateur, d'une fonction asynchrone, etc. Ceci simplifie beaucoup la création et la manipulation des flux de données.
- Backpressure: fonctionnalité clé qui permet de contrôler le flux de données entre le producteur et le consommateur. Cela évite que le consommateur soit inondé de données qu'il ne peut pas traiter rapidement.
- Gestion des erreurs: ensemble complet d'opérateurs pour gérer les erreurs et les exceptions, ce qui vous permet de créer des chaînes de traitement robustes.
- Interopérabilité : pas isolé; convertir un Flux ou un Mono en un Observable ou un Single de RxJava et vice versa. Utile si plusieurs bibliothèques réactives 
