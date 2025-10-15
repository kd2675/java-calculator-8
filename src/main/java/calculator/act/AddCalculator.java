package calculator.act;

import calculator.biz.parse.DelimiterParser;
import calculator.biz.parse.NumberParser;
import calculator.biz.service.SumService;

import java.util.List;

public class AddCalculator {
    private final DelimiterParser delimiterParser;
    private final NumberParser numberParser;
    private final SumService sumService;

    public AddCalculator(DelimiterParser delimiterParser,
                         NumberParser numberParser,
                         SumService sumService) {
        this.delimiterParser = delimiterParser;
        this.numberParser = numberParser;
        this.sumService = sumService;
    }

    public int add(String input) {
        //빈 문자열 입력 시 0 반환
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] delimiters = delimiterParser.parseDelimiters(input);
        String body = delimiterParser.extractBody(input);
        List<Integer> numbers = numberParser.parseNumbers(body, delimiters);

        int result = sumService.sum(numbers);

        return result;
    }
}
