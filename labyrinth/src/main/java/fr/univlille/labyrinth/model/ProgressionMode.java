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
        Level[] defaultLevels = new Level[3];

        defaultLevels[0] = new Level(1);
        Challenge firstStageFirstChall = new Challenge("Easy", 10, 10, 0.2, 14);
        Challenge firstStageSecondChall = new Challenge("Easy", 15, 12, 0.25, 18);
        Challenge firstStageThirdChall = new Challenge("Medium", 20, 15, 0.3, 20);
        defaultLevels[0].getChallenges()[0] = firstStageFirstChall;
        defaultLevels[0].getChallenges()[1] = firstStageSecondChall;
        defaultLevels[0].getChallenges()[2] = firstStageThirdChall;

        defaultLevels[1] = new Level(2);
        Challenge secondStageFirstChall = new Challenge("Medium", 25, 18, 0.35, 30);
        Challenge secondStageSecondChall = new Challenge("Medium", 30, 20, 0.4, 35);
        Challenge secondStageThirdChall = new Challenge("Hard", 35, 25, 0.45, 40);
        defaultLevels[1].getChallenges()[0] = secondStageFirstChall;
        defaultLevels[1].getChallenges()[1] = secondStageSecondChall;
        defaultLevels[1].getChallenges()[2] = secondStageThirdChall;

        defaultLevels[2] = new Level(3);
        Challenge thirdStageFirstChall = new Challenge("Hard", 40, 30, 0.5, 50);
        Challenge thirdStageSecondChall = new Challenge("Hard", 45, 35, 0.55, 60);
        Challenge thirdStageThirdChall = new Challenge("Hard", 50, 40, 0.6, 70);
        defaultLevels[2].getChallenges()[0] = thirdStageFirstChall;
        defaultLevels[2].getChallenges()[1] = thirdStageSecondChall;
        defaultLevels[2].getChallenges()[2] = thirdStageThirdChall;

        ProgressionMode.defaultProgress = new PlayerProgress(defaultLevels);
    }

    @Override
    public void start() {
    }

    /** 
     * @param chosenChallenge
     */
    public void createMaze(Challenge chosenChallenge) {
        int width = chosenChallenge.getWidth();
        int height = chosenChallenge.getHeight();
        double wallPercentage = chosenChallenge.getWallPercentage();
        int minPathLength = chosenChallenge.getDistanceBetweenEntryAndExit();
        setCurrentMaze(new Maze(width, height, wallPercentage, minPathLength));
    }

    /** 
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }
}
