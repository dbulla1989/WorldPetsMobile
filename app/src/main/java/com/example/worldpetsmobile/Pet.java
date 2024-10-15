package com.example.worldpetsmobile;

public class Pet {
    private int imageResourceId;
    private String name;
    private String breed;
    private int age;

    public Pet(int imageResourceId, String name, String breed, int age) {
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }
}

