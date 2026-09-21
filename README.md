# God Mode ⚡

**Dedicated to my 6-year-old son — the bravest adventurer I know.** ;)

Survival god mode for Minecraft — you simply cannot die. No creative mode needed:
mine, build, fight and explore knowing the worst that can happen is being left
standing at half a heart.

## How it works

Out of the box the mod makes **every player** immortal: anything that would kill
you is cancelled instead. Global immortality is controlled by a gamerule:

```
/gamerule godmode:godlike_players false
```

With the gamerule off, immortality becomes per-player, controlled by the `/god`
commands below.

## Commands

All commands require permission level 4 (singleplayer with cheats enabled, or a
server operator).

| Command | Effect |
| --- | --- |
| `/god` | Toggle god mode for yourself |
| `/god <players>` | Toggle god mode for other players |
| `/god check [player]` | Ask whether a player is currently a god |
| `/god list` | List all online players with god mode enabled |

The god flag is saved with the player, so it survives relogging and — naturally —
death.

## Requirements

Minecraft **26.2** or **26.1.2**, on either loader:

- **NeoForge** jar: [NeoForge](https://neoforged.net/) 26.2.0.72+ on 26.2
  (26.1.2.78+ on 26.1.2) and
  [Kotlin for Forge](https://www.curseforge.com/minecraft/mc-mods/kotlin-for-forge) 6.3.0+
- **Fabric** jar: [Fabric Loader](https://fabricmc.net/) 0.19+,
  [Fabric API](https://modrinth.com/mod/fabric-api) and
  [Fabric Language Kotlin](https://modrinth.com/mod/fabric-language-kotlin)

Every release ships a Fabric and a NeoForge jar per supported Minecraft version —
pick the one matching your game, e.g. `godmode-fabric-1.1.1+26.2.jar`.

## Building from source

```
./gradlew build
```

The jars land in `versions/<mc-version>-<loader>/build/libs/`, e.g.
`versions/26.2-fabric/build/libs/`. To build just one target:

```
./gradlew :26.2-neoforge:build
```

## Credits

Icon for this mod has been designed using resources from Flaticon.com.
