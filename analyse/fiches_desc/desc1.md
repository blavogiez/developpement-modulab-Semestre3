**Système:** Labyrinthe
**Cas d’utilisation:** Lancer mode progression

**Acteur principal:** Utilisateur
**Déclencheur:** /
**Autres acteurs:** /

**Préconditions:** / (avoir lancé le jeu)
**Garanties en cas de succès:** L'utilisateur arrive sur le mode progression
**Garanties minimales:** /

**Scénario nominal:**

    1. L'utilisateur sélectionne la fonctionnalité "Lancer mode progression".
    2. Le système affiche un menu où l'utilisateur peut rentrer son nom
    3. L'utilisateur rentre son nom
    4. Le système vérifie que le nom est valide, sauvegarde son profil dans la base de données et lance le mode progression
    
**Scénario alternatifs:**

*A: Le système détecte une erreur lors de la vérification*
    4(A): Le système affiche une notification (de quelles mauvaises informations il s'agit), puis revient en étape 2 du scénario principal

*B: Le système détecte une occurence du nom dans la base de données (charger partie)*
    4(B): Le système démarre le Cas d'utilisation "Charger profil"
