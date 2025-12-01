package ua.opnu;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== ЗАВДАННЯ 1: Предикат для простих чисел ===");
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        System.out.println("Чи є 5 простим? " + isPrime.test(5));
        System.out.println("Чи є 10 простим? " + isPrime.test(10));
        System.out.println("Чи є 17 простим? " + isPrime.test(17));
        System.out.println("Чи є 25 простим? " + isPrime.test(25));

        System.out.println("\n=== ЗАВДАННЯ 2: Фільтрація студентів ===");
        Student s1 = new Student("Ваулін", "Данило", "AI244", new int[]{90, 85, 100});
        Student s2 = new Student("Лампіга", "Владислав", "AI244", new int[]{60, 59, 70});
        Student s3 = new Student("Демешко", "Микита", "AI244", new int[]{40, 90, 80});
        Student s4 = new Student("Гунія", "Владислава", "AI244", new int[]{95, 98, 99});
        Student s5 = new Student("Штумбенко", "Роман", "AI245", new int[]{96, 91, 100});

        Student[] students = {s1, s2, s3, s4, s5};

        Predicate<Student> hasDebt = student -> {
            for (int mark : student.getMarks()) {
                if (mark < 60) return true;
            }
            return false;
        };

        Student[] debtors = filter(students, hasDebt);
        System.out.println("Студенти з боргами:");
        printArray(debtors);

        System.out.println("\n=== ЗАВДАННЯ 3: Фільтрація за двома умовами ===");
        Predicate<Student> isExcellent = student -> {
            for (int mark : student.getMarks()) {
                if (mark < 90) return false;
            }
            return true;
        };

        Predicate<Student> isGroupAI244 = student -> student.getGroup().equals("AI244");

        Student[] excellentFromAI244 = filterTwoConditions(students, isExcellent, isGroupAI244);
        System.out.println("Відмінники з групи AI-244");
        printArray(excellentFromAI244);

        System.out.println("\n=== ЗАВДАННЯ 4: Consumer (Прізвище + Ім'я) ===");
        Consumer<Student> printFullName = s ->
                System.out.println(s.getLastName().toUpperCase() + " " + s.getFirstName().toUpperCase());

        customForEach(students, printFullName);

        System.out.println("\n=== ЗАВДАННЯ 5: Predicate + Consumer ===");
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<Integer> printNumber = n -> System.out.println("Парне число: " + n);

        processArray(numbers, isEven, printNumber);

        System.out.println("\n=== ЗАВДАННЯ 6: Function (2^n) на 10 числах ===");
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        Integer[] powersInput = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (Integer n : powersInput) {
            System.out.println("2^" + n + " = " + powerOfTwo.apply(n));
        }

        System.out.println("\n=== ЗАВДАННЯ 7: Function (Stringify) ===");
        Function<Integer, String> numberToWord = n -> {
            switch (n) {
                case 0: return "нуль";
                case 1: return "один";
                case 2: return "два";
                case 3: return "три";
                case 4: return "чотири";
                case 5: return "п'ять";
                case 6: return "шість";
                case 7: return "сім";
                case 8: return "вісім";
                case 9: return "дев'ять";
                default: return "невідомо";
            }
        };

        Integer[] digits = {1, 5, 0, 9, 3};
        String[] words = stringify(digits, numberToWord);
        System.out.println("Результат перетворення: " + Arrays.toString(words));
    }

    // ДОПОМІЖНІ МЕТОДИ
    public static <T> T[] filter(T[] input, Predicate<T> p) {
        T[] result = Arrays.copyOf(input, input.length);
        int counter = 0;
        for (T item : input) {
            if (p.test(item)) {
                result[counter] = item;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }

    public static <T> T[] filterTwoConditions(T[] input, Predicate<T> p1, Predicate<T> p2) {
        T[] result = Arrays.copyOf(input, input.length);
        int counter = 0;
        for (T item : input) {
            if (p1.test(item) && p2.test(item)) {
                result[counter] = item;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }

    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.println(item);
        }
    }

    public static <T> void customForEach(T[] input, Consumer<T> action) {
        for (T item : input) {
            action.accept(item);
        }
    }

    public static <T> void processArray(T[] input, Predicate<T> condition, Consumer<T> action) {
        for (T item : input) {
            if (condition.test(item)) {
                action.accept(item);
            }
        }
    }

    public static String[] stringify(Integer[] input, Function<Integer, String> converter) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = converter.apply(input[i]);
        }
        return result;
    }
}
