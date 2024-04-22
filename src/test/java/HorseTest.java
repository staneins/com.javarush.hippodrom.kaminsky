import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    void horseNullNameArgument() {
        Exception nullHorseException = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 2.3, 25.0));
        Assertions.assertEquals("Name cannot be null.", nullHorseException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("argsMethod")
    void horseBlankNameArgument(String arg) {
        Exception nullHorseException = assertThrows(IllegalArgumentException.class, () ->
                new Horse(arg, 2.3, 25.0));
        Assertions.assertEquals("Name cannot be blank.", nullHorseException.getMessage());
    }
    static Stream<String> argsMethod() {
        return Stream.of("  ", "    ", " ", "   ", "");
    }

    @Test
    void horseNegativeSpeedArgument() {
        Exception nullHorseException = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Cherry", -1.8, 25.0));
        Assertions.assertEquals("Speed cannot be negative.", nullHorseException.getMessage());
    }

    @Test
    void horseNegativeDistanceArgument() {
        Exception nullHorseException = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Cherry", 1.8, -25.0));
        Assertions.assertEquals("Distance cannot be negative.", nullHorseException.getMessage());
    }

    Horse cherry = new Horse("Cherry", 2.8, 23.1);
    Horse sweetie = new Horse("Sweetie", 2.9);

    @Test
    void horseGetName() {
        String result = cherry.getName();
        assertEquals("Cherry", result);
    }

    @Test
    void horseGetSpeed() {
        double result = cherry.getSpeed();
        assertEquals(2.8, result);
    }

    @Test
    void horseGetDistance() {
        double result = cherry.getDistance();
        assertEquals(23.1, result);
        double secondResult = sweetie.getDistance();
        assertEquals(0, secondResult);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 1.0, 24324.23, 0.0})
    void horseMove(double random) {
        try (MockedStatic<Horse> example = Mockito.mockStatic(Horse.class)) {
            Horse mocky = new Horse("Mocky", 2.4, 23.1);
            example.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            mocky.move();
            example.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            assertEquals(23.1 + 2.4 * random, mocky.getDistance());
        }

    }

}
