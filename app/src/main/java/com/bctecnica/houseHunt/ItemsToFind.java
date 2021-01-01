package com.bctecnica.houseHunt;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class ItemsToFind {

    // Arrays for indoor/outdoor items to find
    private String[] indoor = {
            "indoor item 1", "indoor item 2", "indoor item 3",
            "indoor item 4", "indoor item 5", "indoor item 6",
            "indoor item 7", "indoor item 8", "indoor item 9",
            "indoor item 10", "indoor item 11", "indoor item 12"
    };

    private String[] outdoor = {
            "acorn", "feather", "clover", "pine cone",
            "branch", "Y-shaped twig", "stone", "heart shaped rock",
            "yellow flower", "white flower", "green leaf", "brown leaf",
            "blade of glass", "piece of bark", "seed", "dandelion",
            "colourful flower", "shell", "smooth rock", "something spikey"
    };

    // Combined array of both inside/outside[]
    private String[] both = Stream.concat(Arrays.stream(indoor), Arrays.stream(outdoor))
            .toArray(String[]::new);

    // Pulls items from the correct array depending on user selection of where to play
    public String getNextItem(int input){
        String item = "ERROR";
        Random randomGenerator = new Random();
        if(input == 0) {
            int randomNumber = randomGenerator.nextInt(indoor.length);
            item = "A " + indoor[randomNumber];
        }
        if(input == 1){
            int randomNumber = randomGenerator.nextInt(outdoor.length);
            item = "A " + outdoor[randomNumber];
        }
        if(input == 2){
            int randomNumber = randomGenerator.nextInt(both.length);
            item = "A " + both[randomNumber];
        }
        return item;
    }

}
