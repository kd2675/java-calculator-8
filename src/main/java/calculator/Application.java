package calculator;

import calculator.act.AddCalculator;
import calculator.biz.parse.*;
import calculator.biz.service.SumServiceImpl;
import calculator.biz.valid.ParsingValidatorImpl;
import calculator.utils.IOUtils;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        //I/O 분리: 입력 안내, 결과 출력 형식 맞추기
        try {
            String input = IOUtils.readLine();

            AddCalculator calculator = new AddCalculator(
                    new DelimiterParserPattern(),
                    new NumberParserImpl(),
                    new ParsingValidatorImpl(),
                    new SumServiceImpl()
            );

            int result = calculator.add(input);

            //I/O 분리: 입력 안내, 결과 출력 형식 맞추기
            IOUtils.printResult(result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
