package fr.univlille.labyrinth.model;

public class ProgressionMode extends GameMode {
    private Player player;
    public static PlayerProgress defaultProgress;

    public ProgressionMode() {
        // Player entered a name, that refers to a player in the database
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
        Challenge firstStageFirstChall = new Challenge("Easy", 10, 10, 0.2, 6);
        Challenge firstStageSecondChall = new Challenge("Easy", 15, 12, 0.25, 8);
        Challenge firstStageThirdChall = new Challenge("Medium", 20, 15, 0.3, 10);
        defaultWorlds[0].getChallenges()[0] = firstStageFirstChall;
        defaultWorlds[0].getChallenges()[1] = firstStageSecondChall;
        defaultWorlds[0].getChallenges()[2] = firstStageThirdChall;

        // second stage
        defaultWorlds[1] = new World(2);
        Challenge secondStageFirstChall = new Challenge("Medium", 25, 18, 0.35, 30);
        Challenge secondStageSecondChall = new Challenge("Medium", 30, 20, 0.4, 35);
        Challenge secondStageThirdChall = new Challenge("Hard", 35, 25, 0.45, 40);
        defaultWorlds[1].getChallenges()[0] = secondStageFirstChall;
        defaultWorlds[1].getChallenges()[1] = secondStageSecondChall;
        defaultWorlds[1].getChallenges()[2] = secondStageThirdChall;

        // third stage
        defaultWorlds[2] = new World(3);
        Challenge thirdStageFirstChall = new Challenge("Hard", 40, 30, 0.5, 50);
        Challenge thirdStageSecondChall = new Challenge("Hard", 45, 35, 0.55, 60);
        Challenge thirdStageThirdChall = new Challenge("Hard", 50, 40, 0.8, 75);
        defaultWorlds[2].getChallenges()[0] = thirdStageFirstChall;
        defaultWorlds[2].getChallenges()[1] = thirdStageSecondChall;
        defaultWorlds[2].getChallenges()[2] = thirdStageThirdChall;

        ProgressionMode.defaultProgress = new PlayerProgress(defaultWorlds);
    }

    @Override
    public void start() {
    }

    public void createMaze(Challenge chosenChallenge) {
        int width = chosenChallenge.getWidth();
        int height = chosenChallenge.getHeight();
        double wallPercentage = chosenChallenge.getWallPercentage();
        int minPathLength = chosenChallenge.getDistanceBetweenEntryAndExit();
        setCurrentMaze(new Maze(width, height, wallPercentage, minPathLength));
    }

    public Player getPlayer() {
        return player;
    }
}
