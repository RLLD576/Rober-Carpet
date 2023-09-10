package net.rober.robercarpet;

import carpet.settings.Rule;

public class RoberCarpetSettings {
    @Rule(desc="Lets dispensers convert dirt into clay with water bottles. Does nothing in 1.19+", category = {"dispenser", "rober"})
    public static boolean FarmableClay = false;

    @Rule(desc="Sets the game-ticks of delay when you sleep in a bed (Vanilla is 100)",category = "rober")
    public static int SleepingDelay = 100;

    @Rule(desc="The amount of ticks before thunderstorm that are needed for the mod to warn you about it",category = "rober")
    public static int ThunderWarn = 0;
}
