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
            "indoor item 10", "indoor item 11", "indoor item 12",
    };

    private String[] outdoor = {
            "outdoor item 1", "outdoor item 2", "outdoor item 3",
            "outdoor item 4", "outdoor item 5", "outdoor item 6",
            "outdoor item 7", "outdoor item 8", "outdoor item 9",
            "outdoor item 10", "outdoor item 11", "outdoor item 12",
    };

    // Pulls items from the array depending on user selection of where to play
    public String getNextItem(int input){
        String item = "ERROR";
        if(input == 0) {
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(indoor.length);
            item = indoor[randomNumber];
        }
        if(input == 1){
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(outdoor.length);
            item = outdoor[randomNumber];
        }
        if(input == 2){
            String[] both = Stream.concat(Arrays.stream(indoor), Arrays.stream(outdoor))
                    .toArray(String[]::new);
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(both.length);
            item = both[randomNumber];
        }
        return item;
    }
}
