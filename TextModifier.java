import java.util.Scanner;

public class TextModifier {

    public static String textModifier() {
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();

        // Проверяем на пустую строку или строку, состоящую только из пробелов
        if (inputText == null || inputText.trim().isEmpty()) {
            return ""; // Возвращаем пустую строку
        }

        StringBuilder result = new StringBuilder();
        int sumOfDigits = 0; // Сумма цифр, найденных в строке
        boolean previousWasSpace = false; // Флаг для отслеживания пробелов

        // Обрабатываем каждый символ входной строки
        for (int i = 0; i < inputText.length(); i++) {
            char currentChar = inputText.charAt(i);

            // Обработка пробела
            if (currentChar == ' ') {
                previousWasSpace = handleSpace(result, previousWasSpace);
                continue;
            }

            previousWasSpace = false; // Сбрасываем флаг, если текущий символ не пробел

            // Обработка знака минус
            if (currentChar == '-') {
                i = handleMinus(inputText, result, i); // Обработка знака минус
                continue;
            }

            // Обработка знака плюс
            if (currentChar == '+') {
                result.append('!'); // Меняем знак плюс на восклицательный
                continue;
            }

            // Суммируем цифры
            if (Character.isDigit(currentChar)) {
                sumOfDigits += Character.getNumericValue(currentChar); // Обновляем сумму цифр
                continue;
            }

            // Добавляем текущий символ к результату
            result.append(currentChar);
        }

        // Добавляем сумму цифр в конец итоговой строки, если она больше нуля
        if (sumOfDigits > 0) {
            result.append(" ").append(sumOfDigits);
        }

        // Удаляем лишние пробелы в начале и конце строки и возвращаем результат
        return result.toString().trim();
    }

    // Метод для обработки пробелов: предотвращает добавление подряд идущих пробелов
    private static boolean handleSpace(StringBuilder result, boolean previousWasSpace) {
        if (!previousWasSpace) {
            result.append(' '); // Добавляем пробел в результат
            return true; // Обновляем флаг
        }
        return previousWasSpace; // Возвращаем текущее значение флага
    }

    // Метод для обработки знака минус: меняет местами символы слева и справа от знака
    private static int handleMinus(String inputText, StringBuilder result, int index) {
        // Проверяем, что минус не находится на начале или конце строки
        if (index > 0 && index < inputText.length() - 1) {
            char left = result.charAt(result.length() - 1); // Получаем последний символ результата
            char right = inputText.charAt(index + 1); // Получаем символ справа от минуса
            result.setLength(result.length() - 1); // Удаляем последний символ из результата
            result.append(right).append(left); // Меняем их местами
            return index + 1; // Пропускаем следующий символ
        }
        return index; // Возвращаем индекс, если минус был в неверной позиции
    }

    public static void main(String[] args) {
        System.out.println(textModifier()); // Выводим итоговый результат
    }
}
