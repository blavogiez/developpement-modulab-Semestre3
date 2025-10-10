package fr.univlille.labyrinth.model;

public class ProgressionMode extends GameMode {
    private Player player;
    public static PlayerProgress defaultProgress;

    public ProgressionMode(String name) {
        // Player entered a name, that refers to a player in the database
        this.player = PlayerDatabase.loadPlayer(name);
    }

    // static default progress for new players (Belongs to factory module)
    // Only concerns the player ; Progress shall never be manipulated inside this class


    // execute at start to init
    static {
        ProgressionMode.initDefaultProgress();
    }

    public static void initDefaultProgress() {
        World[] defaultWorlds = new World[3];

        // first stage
        defaultWorlds[0] = new World(1);
        Challenge firstStageFirstChall = new Challenge("Easy", 10, 10, 20);
        Challenge firstStageSecondChall = new Challenge("Easy", 15, 12, 25);
        Challenge firstStageThirdChall = new Challenge("Medium", 20, 15, 30);
        defaultWorlds[0].getChallenges()[0] = firstStageFirstChall;
        defaultWorlds[0].getChallenges()[1] = firstStageSecondChall;
        defaultWorlds[0].getChallenges()[2] = firstStageThirdChall;

        // second stage
        defaultWorlds[1] = new World(2);
        Challenge secondStageFirstChall = new Challenge("Medium", 25, 18, 35);
        Challenge secondStageSecondChall = new Challenge("Medium", 30, 20, 40);
        Challenge secondStageThirdChall = new Challenge("Hard", 35, 25, 45);
        defaultWorlds[1].getChallenges()[0] = secondStageFirstChall;
        defaultWorlds[1].getChallenges()[1] = secondStageSecondChall;
        defaultWorlds[1].getChallenges()[2] = secondStageThirdChall;

        // third stage
        defaultWorlds[2] = new World(3);
        Challenge thirdStageFirstChall = new Challenge("Hard", 40, 30, 50);
        Challenge thirdStageSecondChall = new Challenge("Hard", 45, 35, 55);
        Challenge thirdStageThirdChall = new Challenge("Hard", 50, 40, 80);
        defaultWorlds[2].getChallenges()[0] = thirdStageFirstChall;
        defaultWorlds[2].getChallenges()[1] = thirdStageSecondChall;
        defaultWorlds[2].getChallenges()[2] = thirdStageThirdChall;

        ProgressionMode.defaultProgress = new PlayerProgress(defaultWorlds);
    }

    @Override
    public void start() {

    }

    public Maze createMaze(Challenge chosenChallenge) {
        int width = chosenChallenge.getWidth();
        int height = chosenChallenge.getHeight();
        int wallPercentage = chosenChallenge.getWallPercentage();
        // remonter plus haut pour le créer
        return null ;
    }

    public Player getPlayer() {
        return player;
    }
}
