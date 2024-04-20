import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class HippodromeTest {
    @Test
    void horsesNullListArgument() {
        Exception nullHorseException = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null));
        Assertions.assertEquals("Horses cannot be null.", nullHorseException.getMessage());
    }

    @Test
    void horsesEmptyListArgument() {
        List<Horse> horses = new ArrayList<>();
        Exception nullHorseException = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(horses));
        Assertions.assertEquals("Horses cannot be empty.", nullHorseException.getMessage());
    }

    List<Horse> horses = List.of(
            new Horse("Bucephalus", 2.4),
            new Horse("Ace of Spades", 2.5),
            new Horse("Zephyr", 2.6),
            new Horse("Blaze", 2.7),
            new Horse("Lobster", 2.8),
            new Horse("Pegasus", 2.9),
            new Horse("Cherry", 3),
            new Horse("Thunderhoof", 2.4),
            new Horse("Midnight Star", 2.5),
            new Horse("Blaze", 2.6),
            new Horse("Shadowdancer", 2.7),
            new Horse("Whispering Wind", 2.8),
            new Horse("Stardust", 2.9),
            new Horse("Silvermoon", 3),
            new Horse("Swiftstride", 2.4),
            new Horse("Spiritfire", 2.5),
            new Horse("Golden Mane", 2.6),
            new Horse("Velvet Dreamer", 2.7),
            new Horse("Moonshadow", 2.8),
            new Horse("Shadowfax", 3.1),
            new Horse("Black Beauty", 2.8),
            new Horse("Pegasus", 3.5),
            new Horse("Fury", 2.9),
            new Horse("Stormy", 2.6),
            new Horse("Blizzard", 2.7),
            new Horse("Whirlwind", 3.2),
            new Horse("Thunderbolt", 3.4),
            new Horse("Rapidash", 3.3),
            new Horse("Galadriel", 2.5),
            new Horse("Amber Glow", 3.1)
    );

    @Test
    void getHorses() {
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {

        List<Horse> mockedHorses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            Horse horse = mock(Horse.class);
            mockedHorses.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();
        for (Horse horse : mockedHorses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner() {
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get(), hippodrome.getWinner());
    }

}
