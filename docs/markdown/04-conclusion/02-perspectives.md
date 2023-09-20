<!-- .slide: -->

# Perspectives

Test unitaires

Assignation d'exécuteur

Des "best practices" à creuser ?

Approfondir les dépendances existantes

Notes:
**SYLVAIN**

- approfondir les méthodes spécifique pour tester

- assigner un exécuteur 
    - attacher un pool de thread au opérations réactives
    - optimiser les temps de traitement
    - laisser le framework piloter les phase de vie des threads
    - choisir des pool adapté à son usage (plus ou moins élastiques)

- best practices
    - coupler avec de la programmation fonctionnelle
    - bien utiliser les opération combinatoires
    - intégration orienté-événement framework

- dépendances
    - notamment Netty (dans une époque où on APIse tout)