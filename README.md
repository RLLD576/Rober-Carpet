# Rober-Carpet
A carpet extension made by Rob3r8
Initialy made for having the 1.19 clay mechanic in 1.17 for my survival, but then continued for other rules

Maintaned versions: `1.17.1` , `1.18.2`,  `1.19.4`, `1.20.6`, `1.21`  
In order to maintain all the versions in a single branch, the [Stonecutter](https://stonecutter.kikugie.dev/) gradle plugin is used (similar to preprocessor)

If you have any request, go to the pinned issue and comment, for regular issues, just create another one

# Rober Carpet Rules

### farmableClay
Lets dispensers convert dirt into clay with water bottles
* Type: `boolean`
* Default value: `false`
* Required options: `true`, `false`
* Categories: `rober`, `dispenser`

### sleeping delay

Sets the game-ticks of delay when you sleep in a bed (Vanilla is 100)

* Type: `integer`
* Default value: `100` (Vanilla)
* Categories: `rober`

### thunderWarn
The amount of ticks before thunderstorm that are needed for the server to warn you about it (-1 for no warning)
* Type: `integer`
* Default value: `0` (Doesn't alert)
* Categories: `rober`

### OldFallingBehavior
Reintroduce the 1.12 falling block behavior with walls
* Type: `boolean`
* Default value: `false`
* Categories: `rober`,`falling-block`

### FallingBlockDieAge
Age in gameticks at which falling blocks die, -1 for infinity.
* Type: `integer`
* Default value: `600` (Vanilla)
* Categories: `rober`,`falling-block`
* 
### FallingBlockNoFrictionWithWalls
Falling blocks over walls would not have friction with the floor as in 1.12
* Type: `boolean`
* Default value: `false`
* Categories: `rober`,`falling-block`

### SleepInBedSetsRespawn
Sleeping you will only set respawn when trying to sleep in a bed
* Type: `string`
* Default value: `always`
* Opitons: `always`, `never`, `sneaking`, `no-sneaking`
* Categories: `rober`