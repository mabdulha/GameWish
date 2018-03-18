package com.example.moham.gamewish.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by moham on 18/03/2018.
 */

public class GameTest {

    Game gta = new Game("GTA V", "Rockstar Games", "Action-Adventure");

    @Test
    public void testCreate() {
        assertEquals("GTA V", gta.name);
        assertEquals("Rockstar Games", gta.dev);
        assertEquals("Action-Adventure", gta.genre);
    }
}
