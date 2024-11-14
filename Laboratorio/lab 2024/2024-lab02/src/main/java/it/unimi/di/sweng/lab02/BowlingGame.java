package it.unimi.di.sweng.lab02;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame implements Bowling {
    private final List<Frame> frames = new ArrayList<>();

    @Override
    public void roll(int pins) {

        if (frames.isEmpty()) {
            frames.add(new Frame());
        }

        Frame lastFrame = frames.get(frames.size()-1);

        if (lastFrame.primo == null  ) {
            lastFrame.primo = pins;
            if(pins==10){
                lastFrame.secondo=0;
            }

        } else if (lastFrame.secondo == null) {
            lastFrame.secondo = pins;
        } else {
            Frame newFrame = new Frame();
            newFrame.primo = pins;
            if(pins==10){
                newFrame.secondo=0;
            }
            frames.add(newFrame);
        }
    }

    @Override
    public int score() {
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame f = frames.get(i);
            if (i < 10) score += f.primo + f.secondo;

            if (i < 10) {
                //check spare
                if (f.primo + f.secondo == 10 && f.primo != 10) {
                    Frame next = frames.get(i+1);
                    score += next.primo;
                }

                //check strike
                if (f.primo == 10) {
                    Frame next = frames.get(i+1);
                    if (next.primo != 10) {
                        score += next.primo + next.secondo;
                    } else {
                        Frame next_next = frames.get(i+2);
                        score += next.primo + next_next.primo;
                    }
                }
            }
        }
        return score;
    }

    private class Frame {
        Integer primo = null;
        Integer secondo = null;

        @Override
        public String toString() {
            return "[" + primo + ", " + secondo + "]";
        }
    }
}
