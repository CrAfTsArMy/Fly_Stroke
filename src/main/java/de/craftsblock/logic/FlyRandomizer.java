package de.craftsblock.logic;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class FlyRandomizer {

    private final ConcurrentHashMap<Integer, Boolean> flies = new ConcurrentHashMap<>();

    public FlyRandomizer() {
        Random random = new Random();
        for (int i = 0; i < 9; i++)
            flies.put(i, random.nextBoolean());
        System.out.println(flies);
    }

    public ConcurrentHashMap<Integer, Boolean> getFlies() {
        return flies;
    }

}
