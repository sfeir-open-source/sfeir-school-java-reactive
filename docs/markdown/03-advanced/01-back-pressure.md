<!-- .slide: class="transition bg-pink" -->
# Charge et mitigation

##==##

# Contre-pression

Un levier de rétroaction

Équilibrer la vitesse de production et de consommation de données:

un moyen pour le souscripteur:
- de signaler à l'émetteur une cadence trop élevée
- de définir sa stratégie de contrôle
    - mettre en pause l'émission
    - ignorer une partie des messages

Notes:
La production de signaux n'est pas nécessairement constante
(exemple d'une source type topic kafka)

Pouvoir réguler le rythme d'emission de signaux:

- quand on doit faire des IO (lecture/écriture) parfois trop longues
- empêcher de saturer la memoire du subscriber
- éviter que le système ne s'effondre sous un débit trop élevé pour lui

on va choisir comment on mitige la cadence

##==##
<!-- .slide: class="" -->

# Stratégies de contre-pression

- Erreur

- Tampon

- Ignorer


Notes:

Plusieurs approches pour endiguer la surcharge:
- déclencher une erreur
- mettre de côté le éléments pour temporiser, via un buffer
- ignorer des éléments, (drop)
    - voir prendre juste le dernier (last)
    - voir le dernier élément sur un périodicité choisie (échantillonage)

Peut être effectué au choix
- côté "producteur de données" (creation de flux/mono) : si besoin de stratégie commune à tous les subscriber ou imposée par le publisher
- côté "abonnés" (vers la souscription): besoin de stratégies spécifiques aux traitements par abonnés

##==##
<!-- .slide: class="" -->

# Opérateurs de contre-pression

## Définir une stratégie

- _`onBackpressureError`_ : une exception est déclenchée si le consommateur ne peut pas traiter les éléments aussi vite qu'ils sont produits
```java[]
source.getMessages()
    .onBackpressureError()
    .doOnError(error -> System.err.println("Surcharge détectée: " + error));
```

- _`onBackpressureBuffer`_ : stocker temporairement les éléments en attente
```java[]
source.getMessages()
    .onBackpressureBuffer(100, 
    signal -> System.err.println("Surcharge détectée: " + signal));
```

- _`onBackpressureDrop`_ : abandonner des éléments excédentaires
```java[]
source.getMessages()
    .onBackpressureDrop();
```

- _`onBackpressureLatest`_ : conserver uniquement la dernière valeur émise
```java[]
source.getMessages()
    .onBackpressureLatest();
```
<!-- .element: class="list-fragment" -->

Notes:

- onBackpressureError: 
    - une exception est déclenchée 
    - si le consommateur ne peut pas traiter les éléments à la vitesse à laquelle ils sont produits. 
    - Signifie que si le consommateur est trop lent, 
        - une exception sera levée pour signaler un problème de backpressure.

- onBackpressureBuffer: 
    - créer un tampon pour stocker temporairement les éléments en attente 
    - lorsque le consommateur ne peut pas les traiter immédiatement
    - possible de spécifier la taille du tampon en tant que paramètre. 

- onBackpressureDrop: 
   - les éléments excédentaires sont simplement abandonnés 
   - lorsqu'il y a une situation de backpressure. 
   - Signifie que si le consommateur ne peut pas suivre la production, 
    - les éléments non consommés sont ignorés.

- onBackpressureLatest:
    - conserver uniquement la dernière valeur émise 
    - Les valeurs précédentes sont remplacées par les nouvelles valeurs.

##==##
<!-- .slide: class="" -->

# Opérateurs de contre-pression

## Echantilloner


- _`limitRate`_ : spécifier un débit maximal auquel les éléments sont émis à partir du flux
```java[]
Flux.range(1, 100)
.limitRate(10) // 10 éléments à la fois
```

- _`sample`_ : prendre le dernier éléments émis au cours d'une période donnée
```java[]
topic.read()
.sample(Duration.ofSeconds(10)) // Le derniers éléments de chaque fenêtre de 10s
```
<!-- .element: class="list-fragment" -->


Notes:

Il est possible d'échantillonner soit même avec d'autres opérateurs

- limitRate :
limiter la vitesse de production afin que le consommateur puisse suivre.

- sample :
échantillonner les éléments émis à un rythme régulier défini par une période. 
prendre le dernier éléments émis au cours d'une période donnée

Y'en a d'autre:
- take / takeUntil
