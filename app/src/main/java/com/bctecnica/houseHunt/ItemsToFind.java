package com.bctecnica.houseHunt;

import java.util.Random;

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

    // Used to create a random item to find
    public String getFact(){
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(indoor.length);
        return indoor[randomNumber];
    }
}
