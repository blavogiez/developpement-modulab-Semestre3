**Système:** Labyrinthe
**Cas d’utilisation:** Charger profil

**Acteur principal:** Utilisateur
**Déclencheur:** /
**Autres acteurs:** /

**Préconditions:** L'utilisateur est dans le menu de saisie du nom ("Lancer le mode progression")
**Garanties en cas de succès:** Le système charge le profil associé au nom et l'utilisateur reprend sa progression
**Garanties minimales:** /

**Scénario nominal:**

    1. L'utilisateur a rentré un nom déjà existant dans la base de données lors du **Cas d'utilisation** "Lancer le mode progression" (suite de ce cas, ayant déjà vérifié l'occurence)
    2. Le système affiche un menu où l'utilisateur peut choisir de "Reprendre la partie existante" ou "Écraser la partie existante".
    3. L'utilisateur sélectionne "Reprendre la partie"
    4. Le système charge le profil associé au nom depuis la base de données et l'utilisateur reprend sa progression

**Scénario alternatifs:** /

*A: L'utilisateur choisit "Écraser la partie existante"*
    4(A): Le système écrase la partie existante, sauvegarde le nouveau dans la base de données et lance le mode progression
