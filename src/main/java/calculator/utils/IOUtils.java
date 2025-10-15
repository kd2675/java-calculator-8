package calculator.utils;

import camp.nextstep.edu.missionutils.Console;

public class IOUtils {
    private IOUtils() {}

    public static void printResult(String msg) {
        System.out.println("결과 : " + msg);
    }

    public static void printResult(int msg) {
        System.out.println("결과 : " + msg);
    }

    public static String readLine() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");

        return Console.readLine();
    }
}
