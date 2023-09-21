<!-- .slide: class="transition bg-pink" -->

# Les risques

##==##

<!-- .slide: class="" -->
# Les risques

Dangers de l'opérateur _`block()`_

Les flux sont potentiellement infinis

Notes:
**NATHAN**
- bloquant : attend fin opération, contre productif 
- va a l'encontre du paradigme de prog reactive
- block:
rendre immédiate et synchrone le publisher: effets de bords
- Utile dans tests ou en intéraction avec une lib non reactive 

- l'infinité des flux:
opération TERMINALE ne verra jamais sa fin -> blocage du système 


##==##

<!-- .slide: class="" -->
# Les risques

Pas une solution miracle à utiliser partout

Plus grande complexité

Plus difficile à débugger et tester


Notes:
**SYLVAIN**
