import java.io.*;

public class Main {
    static char operation = ' ';

    public static void main(String[] args) {
        String line = inputLine();
        System.out.println(calc(line));
    }

    public static String calc(String input) {
        int firstNum, secondNum;
        int result;
        input = input.replaceAll("\\r\\n", "");
        input = input.replaceAll(" ", "");

        if (!isOneOperator(input)) {
            try {
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } catch (Exception e) {
                System.out.println(e);
                return "";
            }
        }

        String[] number = input.split("[-+/*]");

        if (number[0].contains(".") || number[1].contains(".")) {
            try {
                throw new Exception("Калькулятор умеет работать только с целыми числами");
            } catch (Exception e) {
                System.out.println(e);
                return "";
            }
        }

        if (number[0].matches("\\d*") && number[1].matches("\\d*")) {
            firstNum = Integer.parseInt(number[0]);
            secondNum = Integer.parseInt(number[1]);
            if (firstNum > 10 || firstNum < 1 || secondNum > 10 || secondNum <1) {
                try {
                    throw new Exception("формат математической операции не удовлетворяет заданию - цифры 1 до 10");
                } catch (Exception e) {
                    System.out.println(e);
                    return "";
                }
            }
            result = mathematicalOperations(firstNum, secondNum, operation);
            return String.valueOf(result);

        } else if (number[0].matches("\\D*") && number[1].matches("\\D*") &&
                isRomanNum(number[0]) && isRomanNum(number[1])) {
            firstNum = convertRomanToInt(number[0]);
            secondNum = convertRomanToInt(number[1]);
            if (firstNum > 10 || firstNum < 1 || secondNum > 10 || secondNum <1) {
                try {
                    throw new Exception("формат математической операции не удовлетворяет заданию - цифры 1 до 10");
                } catch (Exception e) {
                    System.out.println(e);
                    return "";
                }
            }
            result = mathematicalOperations(firstNum, secondNum, operation);
            if (result <= 0) {
                try {
                    throw new IllegalArgumentException("в римской системе нет отрицательных чисел");
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                    return "";
                }
            } else {
                return convertNumToRoman(result);
            }

        } else if ((number[0].matches("\\D*") && isRomanNum(number[0])  && number[1].matches("\\d*")) ||
                (number[0].matches("\\d*") && number[1].matches("\\D*") && isRomanNum(number[1]))) {
            try {
                throw new Exception("используются одновременно разные системы счисления");
            } catch (Exception e) {
                System.out.println(e);
                return "";
            }
        }  else {
            try {
                throw new Exception("введенное выражение не удовлетворяет условию");
            } catch (Exception e) {
                System.out.println(e);
                return "";
            }
        }
    }

    private static String inputLine (){
        System.out.println("Введите выражение с целыми числами от 1 до 10 или два римских числа от I до X");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = console.readLine();
            console.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private static boolean isOneOperator(String input) {
        int numOfOper = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+') {
                numOfOper++;
                operation = input.charAt(i);
            } else if (input.charAt(i) == '-') {
                numOfOper++;
                operation = input.charAt(i);
            } else if (input.charAt(i) == '*') {
                numOfOper++;
                operation = input.charAt(i);
            } else if (input.charAt(i) == '/') {
                numOfOper++;
                operation = input.charAt(i);
            }
        }
        if (numOfOper > 1) return false;
        else if (numOfOper == 0) return false;
        else return true;
    }

    private static boolean isRomanNum (String str){
        switch (str){
            case "I" :
            case "II" :
            case "III" :
            case "IV" :
            case "V" :
            case "VI" :
            case "VII" :
            case "VIII" :
            case "IX" :
            case "X" : return true;
            default : return false;
        }
    }
    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[numArabian];
    }

    private static int convertRomanToInt(String romanNum) {

        switch (romanNum){
            case "I" : return 1;
            case "II" : return 2;
            case "III" : return 3;
            case "IV" : return 4;
            case "V" : return 5;
            case "VI" : return 6;
            case "VII" : return 7;
            case "VIII" : return 8;
            case "IX" : return 9;
            case "X" : return 10;
            default : return 0;
        }
    }

    private static int mathematicalOperations(int firstNum, int secondNum, char op) {
        int result = 0;
            if (op == '+') {
                result = firstNum + secondNum;
            } else if (op == '-') {
                result = firstNum - secondNum;
            } else if (op == '*') {
                result = firstNum * secondNum;
            } else if (op == '/') {
                try{
                    result = firstNum / secondNum;;
                } catch (ArithmeticException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Делить на ноль нельзя!");
                }
            } else {
                throw new IllegalArgumentException("Не верный знак операции");
            }
        return result;
    }
}

