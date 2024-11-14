package it.unimi.di.sweng.lab02;

public class BowlingGame implements Bowling {

    private int score;
    private int[][] rolls = new int[11][2];
    private int frame = 0;
    private byte roll = 0;
    private boolean spare = false;
    private boolean strike = false;
    private int strikeCounter;


    @Override
    public void roll(int pins) {
        rolls[frame][roll] = pins;
        score += pins;

        if (spare) {
            score += pins;
            spare = false;
        }

        if (strike) {
            score += pins;
            if (strikeCounter == 1) {
                strike = false;
            }
            strikeCounter--;
        }

        //gestione spare
        if (roll == 1 && rolls[frame][0] + pins == 10) {
            spare = true;

            //gestione strike
        } else if (roll == 0 && pins == 10) {
            strike = true;
            strikeCounter = 2;
        }

        //in caso di strike si passa direttamente al prossimo frame tranne per l'ultimo frame
        if (strike && strikeCounter == 2 && frame != 9) {
            frame++;
        } else if (roll == 1) {
            frame++;
            roll = 0;
        } else {
            roll++;
        }

		return;
    }

    @Override
    public int score() {
		return this.score;
    }
}
