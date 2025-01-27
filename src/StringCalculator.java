import java.util.Scanner;

public class StringCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();
        try {
            String result = calculate(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculate(String expression) throws Exception {
        String[] parts = expression.split(" ");
        validateExpression(parts);

        String operand1 = extractOperand(parts[0]);
        String operator = parts[1];
        String operand2 = parts[2];

        switch (operator) {
            case "+":
                return add(operand1, operand2);
            case "-":
                return subtract(operand1, operand2);
            case "*":
                return multiply(operand1, operand2);
            case "/":
                return divide(operand1, operand2);
            default:
                throw new Exception("Неподдерживаемая операция");
        }
    }

    private static void validateExpression(String[] parts) throws Exception {
        if (parts.length != 3) {
            throw new Exception("Неверное выражение");
        }
    }

    private static String extractOperand(String operand) throws Exception {
        if (!operand.startsWith("\"") || !operand.endsWith("\"")) {
            throw new Exception("Операнд должен быть строкой в ковычках ");
        }
        operand = operand.substring(1, operand.length() - 1);
        if (operand.length() > 10) {
            throw new Exception("Длина строки не должна превышать 10 символов");
        }
        return operand;
    }

    private static String add(String operand1, String operand2) throws Exception {
        String secondOperand = extractOperand(operand2);
        return formatResult(operand1 + secondOperand);
    }

    private static String subtract(String operand1, String operand2) throws Exception {
        String secondOperand = extractOperand(operand2);
        return formatResult(operand1.replace(secondOperand, ""));
    }

    private static String multiply(String operand1, String operand2) throws Exception {
        int multiplier = parseIntegerOperand(operand2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < multiplier; i++) {
            sb.append(operand1);
        }
        return formatResult(sb.toString());
    }

    private static String divide(String operand1, String operand2) throws Exception {
        int divisor = parseIntegerOperand(operand2);
        int endIndex = Math.min(operand1.length(), operand1.length() / divisor);
        return formatResult(operand1.substring(0, endIndex));
    }

    private static int parseIntegerOperand(String operand) throws Exception {
        int number;
        try {
            number = Integer.parseInt(extractOperand(operand));
        } catch (NumberFormatException e) {
            throw new Exception("Операнд должен быть числом");
        }
        if (number < 1  || number > 10) {
            throw new Exception("Число должно быть от 1 до 10");
        }
        return number;
    }

    private static String formatResult(String result) {
        if (result.length() > 40) {
            return result.substring(0, 40) + "...";
        }
        return result;
    }
}