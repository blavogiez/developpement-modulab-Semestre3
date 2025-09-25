**Système:** Labyrinthe
**Cas d’utilisation:** Déplacer joueur

**Acteur principal:** Utilisateur
**Déclencheur:** /
**Autres acteurs:** /

**Préconditions:** Être dans le labyrinthe
**Garanties en cas de succès:** Le système a déplacé le joueur à la case souhaitée
**Garanties minimales:** /

**Scénario nominal:**

    1. Le joueur appuie sur une flèche directionnelle
    2. Le système vérifie que la direction est accessible et déplace le joueur sur la case associée


**Scénario alternatifs:**
*A: Le joueur arrive sur la sortie*
    2(A): Le système affiche une notification annonçant que la sortie a été atteinte

*B: Le joueur veut se déplacer dans une case inaccessible*
    2(B): Le système affiche la position actuelle et pas la nouvelle position et revient en étape 1 du scénario principal

