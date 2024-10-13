import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.assertEquals;

public class TextModifierTest {

    private void simulateInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    public void testExample1() {
        simulateInput("генрих1  играет+2   л-июбит0школу");
        assertEquals("генрих играет! илюбитшколу 3", TextModifier.textModifier());
    }

    @Test
    public void testExample2() {
        simulateInput("Я ю-лбю-л джаву   всем сердцем+");
        assertEquals("Я люблю джаву всем сердцем!", TextModifier.textModifier());
    }

    @Test
    public void testNoDigits() {
        simulateInput("Пример текста с  пробелами и+ знаком+");
        assertEquals("Пример текста с пробелами и! знаком!", TextModifier.textModifier());
    }

    @Test
    public void testOnlyDigits() {
        simulateInput("123456");
        assertEquals("21", TextModifier.textModifier());  // Убрали начальный пробел
    }

    @Test
    public void testSpecialCharacters() {
        simulateInput("!@# -$%");
        assertEquals("!@#$ %", TextModifier.textModifier());
    }

    @Test
    public void testMultipleSpacesOnly() {
        simulateInput("     ");
        assertEquals("", TextModifier.textModifier());
    }

    @Test
    public void testNoSpacesOrSymbols() {
        simulateInput("Тест");
        assertEquals("Тест", TextModifier.textModifier());
    }

    @Test
    public void testSinglePlus() {
        simulateInput("Тест+");
        assertEquals("Тест!", TextModifier.textModifier());
    }

    @Test
    public void testMinusAtStart() {
        simulateInput("-Тест");
        assertEquals("Тест", TextModifier.textModifier());
    }

    @Test
    public void testSingleMinus() {
        simulateInput("гени-ус"); // Ввод данных для теста
        assertEquals("генуис", TextModifier.textModifier()); // Проверка результата
    }

    @Test
    public void testMultipleSpaces() {
        simulateInput("hello    world"); // Ввод данных для теста
        assertEquals("hello world", TextModifier.textModifier()); // Проверка результата
    }

    @Test
    public void testOnlyLetters() {
        simulateInput("abcd efgh");
        assertEquals("abcd efgh", TextModifier.textModifier());
    }

    @Test
    public void testLeadingAndTrailingSpaces() {
        simulateInput("   hello world  ");
        assertEquals("hello world", TextModifier.textModifier());
    }

    @Test
    public void testNullInput() {
        simulateInput(" ");
        assertEquals("", TextModifier.textModifier());
    }

    @Test
    public void testEmptyInput() {
        simulateInput(" ");
        assertEquals("", TextModifier.textModifier());
    }
}