package com.bctecnica.houseHunt;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class ItemsToFind {

    // Arrays for indoor/outdoor items to find
    private String[] indoor = {
            "A cushion", "A candle", "A tv remote", "A book", "Some kind of ornament",
            "A bag", "A disc", "A photo", "A cable", "A pair of sunglasses",
            "A money box", "A statue", "A plaster", "Something battery powered", "A key",
            "A slipper", "A black shoe", "A colourful shoe", "A plant", "A coat",
            "A hat", "A scarf", "A pair of headphones", "A glove", "A handbag",
            "A piece of paper", "Some toilet roll", "A sticker", "A spoon", "A saucepan",
            "A cup or mug", "A tea towel", "A bowl", "A jug", "A sieve",
            "Some kitchen roll", "Some tin foil", "A bin bag", "A cooking tray", "A whisk",
            "A chopsticks", "A small plate", "A roll of tape", "A battery", "A ball",
            "A bottle", "A helmet", "A rolling pin", "A pair of tongs", "Something you can eat",
            "A tub", "A place mat", "A headband", "A scrunchy", "A hair clip",
            "A towel", "A magazine", "A pen", "A pencil", "A felt tip pen",
            "A rubber", "A glue stick", "A pencil case", "Something made of wood", "Something cold",
            "A soft toy", "A hard toy", "A doll", "A stuffed toy", "A animal toy",
            "A piece of lego", "Something with wheels", "A box", "A controller", "A toy",
            "Something that smells", "A belt", "A magnet", "A toothbrush", "Some toothpaste",
            "A bar of soap", "A hair brush", "A comb", "A bootle of shampoo", "Something from a t.v. show ",
            "Something with lights", "A toy car", "A ring", "Some jewelry ", "Something for the bath",
            "A pillow", "A t-shirt", "Something warm to wear", "something that tells the time", "A pair of trousers",
            "A white sock", "A black sock", "A colored sock", "Something you would wear at night", "A toy",
            "Something soft", "Something hard", "Something heavy", "Something white", "Something black",
            "Something yellow", "Something green", "Something blue", "Something orange", "Something red",
            "A shiny object", "Something gold", "Something purple", "Something animal shaped", "Something pink",
            "Something with buttons on it", "A round object", "A tissue", "A letter", "A pair of glasses",
            "Something made of rubber", "A coin", "A stuffed toy", "A music box", "A hanger",
            "Something used for exercise", "A crayon", "A pair of glasses", "Something clear", "Something bouncy",
            "Something loud", "Something rough", "Something cold", "Something warm", "Something with letters on it",
            "Something with numbers on it"
    };

    private String[] outdoor = {
            "An acorn", "A feather", "A clover", "A pine cone", "A branch",
            "A Y-shaped twig", "A stone", "A heart shaped rock", "A yellow flower", "A white flower",
            "A green leaf", "A brown leaf", "A blade of glass", "A piece of bark", "A seed",
            "A dandelion", "A coloured flower", "A shell", "A smooth rock", "something spiky",
            "A bug", "Some seaweed", "A pebble", "Something small", "Something you bought with you",
            "A feather"
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
            item = indoor[randomNumber];
        }
        if(input == 1){
            int randomNumber = randomGenerator.nextInt(outdoor.length);
            item = outdoor[randomNumber];
        }
        if(input == 2){
            int randomNumber = randomGenerator.nextInt(both.length);
            item = both[randomNumber];
        }
        return item;
    }

}
