package src.main.java.oose.ooad.quebble.adapter;

import java.util.Random;

public class RandomWoordenboekAdapter implements IWoordenboekAdapter {

    public boolean checkWord(String word) {
        Random rd = new Random(); // creating Random object
        return rd.nextBoolean(); // displaying a random boolean
    }

}
