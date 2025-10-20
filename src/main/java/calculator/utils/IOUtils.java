package calculator.utils;

import camp.nextstep.edu.missionutils.Console;

import java.util.NoSuchElementException;

public class IOUtils {
    private IOUtils() {}

    public static void printResult(String msg) {
        System.out.println("결과 : " + msg);
    }

    public static void printResult(int msg) {
        System.out.println("결과 : " + msg);
    }

    public static void printResult(long msg) {
        System.out.println("결과 : " + msg);
    }

    public static String readLine() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        
        try {
            String input = Console.readLine();
            return input != null ? input : "";
        } catch (NoSuchElementException e) {
            // 입력이 없는 경우 빈 문자열 반환
            return "";
        }
    }
}
