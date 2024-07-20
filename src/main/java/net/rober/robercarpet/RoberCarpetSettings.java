package net.rober.robercarpet;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Arrays;

public class RoberCarpetSettings {
    @Rule(desc="Lets dispensers convert dirt into clay with water bottles", category = {"dispenser", "rober"})
    public static boolean FarmableClay = false;

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

    @Rule(desc="If using keep inventory gamerule, the player xp wil be reset on death (vanilla if keep inventory off)",category = "rober")
    public static boolean KeepInventoryResetXP = false;

    @Rule(desc="Send feedback to the player of the action of the rule SleepInBedSetRespawn", category = "rober")
    public static boolean SendSetRespawnFeedback = false;

    private static final String[] SleepInBedSetsRespawnOptions = new String[] { "always","never", "sneaking", "no-sneaking" };
    @Rule(desc="Sleeping you will only set respawn when trying to sleep in a bed",category ="rober",options = {"never", "sneaking", "no-sneaking", "always"},validate = {SleepInBedSetRespawnValidator.class})
    public static String SleepInBedSetsRespawn = "always";
    private static class SleepInBedSetRespawnValidator extends Validator<String> {
        @Override
        public String validate(ServerCommandSource serverCommandSource, ParsedRule<String> parsedRule, String s, String s2){
            if((serverCommandSource == null || parsedRule.get().equals(s))&& Arrays.asList(SleepInBedSetsRespawnOptions).contains(s)){
                SleepInBedSetsRespawn = s;
                return s;
            }
            return "never";
        }
    }


}
