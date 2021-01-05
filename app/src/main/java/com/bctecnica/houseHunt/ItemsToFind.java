package com.bctecnica.houseHunt;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class ItemsToFind {

    // Arrays for indoor/outdoor items to find
    private String[] indoor = {
            "cushion", "candle", "remote", "book", "ornament",
            "bag", "disc", "photo", "cable", "sunglasses",
            "money box", "statue", "plaster", "phone/tablet", "key",
            "white shoe", "black shoe", "colourful shoe", "plant", "coat",
            "hat", "scarf", "headphones", "glove", "handbag",
            "piece of paper", "toilet roll", "sticker", "spoon", "saucepan",
            "cup", "tea towel", "bowl", "jug", "siv",
            "kitchen roll", "tin foil", "bin bag", "cooking tray", "whisk",
            "chopsticks", "small plate", "roll of tape", "battery", "ball",
            "bottle", "helmet", "rolling pin", "pair of tongs", "sweet",
            "tub", "place mat", "headband", "scrunchy", "hair clip",
            "towel", "magazine", "pen", "pencil", "felt tip pen",
            "rubber", "glue stick", "pencil case", "wooden object", "metal object",
            "soft toy", "hard toy", "doll", "cuddly animal", "animal toy",
            "lego", "something with wheels", "character", "controller", "thing that smells",
            "toy", "something that tells the time", "magnet", "toothbrush", "toothpaste",
            "bar of soap", "hair brush", "comb", "shampoo", "bath toy",
            "object with a light", "toy car", "ring", "necklace", "box",
            "pillow", "t-shirt", "jumper", "belt", "pair of trousers",
            "white sock", "black sock", "colored sock", "dressing gown/onesie", "toy",
            "soft object", "hard object", "heavy object", "white object", "black object",
            "yellow object", "green object", "blue object", "orange object", "red object",
            "shiny object", "gold object", "purple object", "soft object", "pink object",
            "thing with buttons", "round object", "tissue/wipe", "letter", "glasses",
            "thing made of rubber", "coin", "stuffed toy", "music box", "hanger",
    };

    private String[] outdoor = {
            "acorn", "feather", "clover", "pine cone", "branch",
            "Y-shaped twig", "stone", "heart shaped rock", "yellow flower", "white flower",
            "green leaf", "brown leaf", "blade of glass", "piece of bark", "seed",
            "dandelion", "colourful flower", "shell", "smooth rock", "something spikey",
            "bug", "seaweed", "pebble"
    };

    // Combined array of both inside/outside[]
    private String[] both = Stream.concat(Arrays.stream(indoor), Arrays.stream(outdoor))
            .toArray(String[]::new);

    // Gets item from the correct array depending on user selection of where to play
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
