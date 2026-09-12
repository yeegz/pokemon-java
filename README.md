# Pokémon Terminal

A Java terminal game built around encounters, turn-based combat and capturing Pokémon. The project separates game flow, domain models, services and terminal presentation.

## Run locally

Install a Java Development Kit, then compile from the repository root:

```sh
javac -d out -sourcepath src src/com/pokemon/game/Main.java
java -cp out com.pokemon.game.Main
```

Follow the numbered terminal menus to find a wild Pokémon, view your collection, fight, catch or run.

## Source layout

- `controller/GameController.java` — game flow.
- `model/` — Pokémon and attack models.
- `service/` — combat, capture, levelling and scoring.
- `database/PokemonRepository.java` — Pokémon data.
- `ui/GameUI.java` — terminal menus and input handling.
- `util/RNG.java` — random number helper.

All packages are under `src/com/pokemon/game/`. This is a fan-made Java learning project.

[More work by Yousof Selim](https://yeegz.github.io)
