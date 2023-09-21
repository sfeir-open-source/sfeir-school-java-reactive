<!-- .slide: -->

# C'est quoi la programmation réactive ?

Réactive : réagit à des évènements

Repose sur une émission de données à partir d'une ou plusieurs sources

Changement de paradigme
  * _Synchrone_ -> _Asynchrone_

Notes:
**SYLVAIN**

Reactive -> qui réagit à des événenement ou des changements

Evenement peut être de la données

on est en réaction à la réception de données ou de messages depuis 1 ou plusieurs sources

se retrouve donc notablement dans la prog
  - orienté "événements" 
  - orienté "messages"

##==##
<!-- .slide: -->
# Efficacité et Parallélisation: 

Meilleurs utilisations des ressources

Réduction des temps d'attentes

Parallélisation des tâches automatique

Notes:
**SYLVAIN**
- on optimise les IOs en les rendant non-bloquants
- on libère du temps processeur pour effectuer d'autre opérations pendant l'attente
- gestion automatique de threads
 
La programmation réactive offre 
- une manière dynamique 
- et efficace 

de gérer les opérations et les événements, 

en exploitant la parallélisation et en réduisant les délais, et est symbolisée par des concepts tels que future, promise, et plus encore.


##==##
<!-- .slide: -->
# Principes d'utilisation

~_Exécution_~ -> _Souscription_

_`Publisher`_ émet des signaux vers _`Subscriber`_

Les _`Subscribers`_ opèrent selon les signaux. 

![center-w-800](./assets/images/publisher_subscriber_signal.png)

Notes:
**SYLVAIN**

On parle de "souscription" plus que d'éxecution.

A l'exécution des bloc de code vous construisez les chaines réactive, 

mais le comportement décrit par la chaine survient lors de souscription à des événements

##==##
<!-- .slide: class="with-code"-->
# Principes d'utilisation

Les données se présentent sous forme de flux

![center-w-600](./assets/images/flux_schema_cleared.png)

Vaste gamme d'opérateurs appliquables à ce flux

  * _`map`, `flatmap`, `filter`, `or`, `zip`, `reduce`, `groupBy`, ..._


Notes:
**SYLVAIN**
- représentation en séquence
- émet plusieurs types de signaux
  * _valeur_
  * _erreur_
  * _vide_
  * _terminé_
