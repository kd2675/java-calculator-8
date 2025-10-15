package calculator;

import calculator.act.AddCalculator;
import calculator.biz.parse.DelimiterParserImpl;
import calculator.biz.parse.NumberParserImpl;
import calculator.biz.service.SumServiceImpl;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        //I/O 분리: 입력 안내, 결과 출력 형식 맞추기
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        AddCalculator calculator = new AddCalculator(
                new DelimiterParserImpl(),
                new NumberParserImpl(),
                new SumServiceImpl()
        );

        int result = calculator.add(input);

        //I/O 분리: 입력 안내, 결과 출력 형식 맞추기
        System.out.println("결과 : " + result);
    }
}
