package net.rober.robercarpet;

import carpet.settings.Rule;

public class RoberCarpetSettings {

    @Rule(desc="Sets the game-ticks of delay when you sleep in a bed (Vanilla is 100)",category = "rober")
    public static int SleepingDelay = 100;

    @Rule(desc="The amount of ticks before thunderstorm that are needed for the mod to warn you about it",category = "rober")
    public static int ThunderWarn = 0;
    @Rule(desc="Reintroduce the 1.12 falling block behavior",category={"rober","falling-block"})
    public static boolean OldFallingBehavior = false;

    @Rule(desc="Age in gameticks at which falling blocks die, -1 for infinity. (Vanilla is 600)",category = {"rober","falling-block"})
    public static int FallingBlockDieAge = 600;

    @Rule(desc="Falling blocks over walls would not have friction with the floor as in 1.12",category = {"rober","falling-block"})
    public static boolean FallingBlockNoFrictionWithWalls = false;
}