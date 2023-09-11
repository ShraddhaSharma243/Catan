package com.teksystems.bootcamp.capstone2.misclleneous;

import static java.util.concurrent.ThreadLocalRandom.current;

public class Dice {
    public int rollDice(){
        final int MIN = 2;
        final int Max = 13;
        return 	current().nextInt(MIN,Max);
    }

}
