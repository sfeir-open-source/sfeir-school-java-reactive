<!-- .slide: -->

# C'est quoi la programmation réactive ?

Réactive : réagit à des évènements

Repose sur une émission de données à partir d'une ou plusieurs sources

Changement de paradigme
  * _Synchrone_ -> _Asynchrone_

Notes:
se retrouve notablement dans 
  - orienté "événements" 
  - orienté "messages"

##==##
<!-- .slide: -->
# Efficacité et Parallélisation: 

Meilleurs utilisations des ressources

Réduction des temps d'attentes

Parallélisation des tâches automatique

Notes:

- on optimise les IOs en les rendant non-bloquants
- on libère du temps processeur pour effectuer d'autre opérations pendant l'attente
- gestion automatique de threads
 
La programmation réactive offre 
- une manière dynamique 
- et efficace 

de gérer les opérations et les événements, 

en exploitant la parallélisation et en réduisant les délais, et est symbolisée par des concepts tels que future, promise, et plus encore.


##==##
# Principes d'utilisation

~_Exécution_~ -> _Souscription_

_`Publisher`_ émet des signaux vers _`Subscriber`_

Les _`Subscriber`_ opèrent selon les signaux. 

![center-w-800](./assets/images/publisher_subscriber_signal.png)


##==##
<!-- .slide: class="with-code"-->
# Principes d'utilisation

Les données se présentent sous forme de flux

![center-w-600](./assets/images/flux_schema_cleared.png)

Vaste gamme d'opérateurs appliquables à ce flux

  * _`map`, `flatmap`, `filter`, `or`, `zip`, `reduce`, `groupBy`, ..._


Notes:
- représentation en séquence
- émet plusieurs types de signaux
  * _valeur_
  * _erreur_
  * _vide_
  * _terminé_
