package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        if (input == null || input.isEmpty()) {
            System.out.println("결과 : 0");
        }

        List<Integer> result = new ArrayList<>();

        List<String> delimiter = new ArrayList<>(DEFAULT_DELIMITERS);

        if (input.startsWith("//")) {
            int nl = input.indexOf("\\n");
            delimiter.add(input.substring(2, nl));

            input = input.substring(nl + 2);
        }

        String splitRegex = String.join("|", delimiter);
        String[] tokens = input.split(splitRegex, 0);
        for (String token : tokens) {
            String s = token.trim();
            if (s.isEmpty()) {
                continue;
            }

            result.add(Integer.parseInt(s));
        }

        int acc = 0;
        for (int n : result) {
            acc += n;
        }

        System.out.println("결과 : " + acc);
    }
}
