import java.util.Scanner;

public class Calculator {

    private double firstOperand = 0.0;
    private double secondOperand = 0.0;
    private String operation = "";
    private boolean exitFlag = false;
    private boolean resetFlag = false;
    private Scanner scanner = new Scanner(System.in);
    private String[] operations = new String[]{"+", "-", "*", "/"};

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в калькулятор");
        System.out.println("  Список допустимых операций: +,-,*,/");
        System.out.println("  Для выхода из калькулятора нажмите s");
        System.out.println("  Для сброса калькулятора нажмите C\n");

        Calculator calc = new Calculator();
        calc.start();
    }

    private void start() {

        do {
            readOperand(1);
            if (exitFlag) break;
            else if (resetFlag) {
                resetCalculator();
                continue;
            }
            do {
                readOperation();
                if (exitFlag) break;
                else if (resetFlag) {
                    resetCalculator();
                    break;
                }
                readOperand(2);
                if (exitFlag) break;
                else if (resetFlag) {
                    resetCalculator();
                    break;
                }
                calculate();
            } while (true);
        } while (!exitFlag);
    }

    private void resetCalculator() {
        firstOperand = 0.0;
        secondOperand = 0.0;
        resetFlag = false;
        System.out.println("Выполнен сброс калькулятора");
    }


    private void readOperand(int order) {
        String inStr = "";
        do {
            System.out.println("Введите операнд №" + order);
            inStr = scanner.next().toString();
            if (inStr.equals("s")) {
                exitFlag = true;
                break;
            } else if (inStr.equals("C")) {
                resetFlag = true;
                break;
            } else if (!isNumeric(inStr)) System.out.println("Операнд должен быть числом");
            else if (order == 1) firstOperand = Double.parseDouble(inStr);
            else secondOperand = Double.parseDouble(inStr);
        } while (!isNumeric(inStr));
    }

    private void readOperation() {
        do {
            System.out.println("Введите операцию из списка (+,-,*,/):");
//            operation = scanner.next().charAt(0);
            operation = scanner.next().toString();

        } while (!validateOperation(operation));
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean validateOperation(String operation) {
        boolean isValid = false;

        for (String op : operations) {
            if (op.equals(operation))
                isValid = true;
        }
        if (operation.equals("s")) {
            exitFlag = true;
            isValid = true;
        } else if (operation.equals("C")) {
            resetFlag = true;
            isValid = true;
        }
        if (!isValid) System.out.println("Введена неверная операция");
        return isValid;
    }

    private void calculate() {
        switch (operation) {
            case "+":
                firstOperand += secondOperand;
                break;
            case "-":
                firstOperand -= secondOperand;
                break;
            case "*":
                firstOperand *= secondOperand;
                break;
            case "/":
                firstOperand /= secondOperand;
                break;
        }
        System.out.println("Результат = " + firstOperand);
    }
}
