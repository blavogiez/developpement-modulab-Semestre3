**Système:** Labyrinthe
**Cas d’utilisation:** Déplacer joueur

**Acteur principal:** Utilisateur
**Déclencheur:** /
**Autres acteurs:** /

**Préconditions:** Avoir lancé une partie
**Garanties en cas de succès:** Le joueur a réussi à se déplacer
**Garanties minimales:** Le joueur ne se déplace pas

**Scénario nominal:**

    1. Le joueur appui sur une fleche directionnel
    2. Le système affiche le nouvelle position du joueur


**Scénario alternatifs:**
*A: Le joueur arrive sur la sortie*
    2(A) Le système affiche un message pour annoncer que la sortie a été atteinte 
*B: Le joueur veut se déplacer dans un mur*
    2(B) Le système affiche la position actuelle et pas la nouvelle position

