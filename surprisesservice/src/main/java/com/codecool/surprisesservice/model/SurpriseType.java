package com.codecool.surprisesservice.model;

public enum SurpriseType {
    KITTEN, PUNISHMENT, FUNNYIMGS;

    public Surprise getDefault() {
        Surprise surprise = null;
        switch (this) {
            case KITTEN:
                surprise =  Surprise.builder()
                        .src("https://upload.wikimedia.org/wikipedia/commons/6/66/An_up-close_picture_of_a_curious_male_domestic_shorthair_tabby_cat.jpg")
                        .type("jpg")
                        .surpriseType("cat")
                        .build();
                break;
            case FUNNYIMGS:
                surprise = Surprise.builder()
                        .src("https://www.unilad.co.uk/wp-content/uploads/2018/10/disaster-girl-thats-meme-fb.jpg")
                        .type("jpg")
                        .surpriseType("funnyImg")
                        .build();
                break;
            case PUNISHMENT:
                surprise = Surprise.builder()
                        .src("https://memestatic.fjcdn.com/pictures/Bad_027fcf_6561414.jpg")
                        .type("jpg")
                        .surpriseType("punishment")
                        .build();
        }
        return surprise;
    }
}
