package calculator;

import calculator.act.AddCalculator;
import calculator.biz.parse.*;
import calculator.biz.service.SumServiceImpl;
import calculator.biz.valid.ParsingValidatorImpl;
import calculator.utils.IOUtils;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = IOUtils.readLine();

        AddCalculator calculator = new AddCalculator(
                new DelimiterParserImpl(),
                new NumberParserImpl(),
                new ParsingValidatorImpl(),
                new SumServiceImpl()
        );

        int result = calculator.add(input);

        IOUtils.printResult(result);
    }
}
