package calculator.act;

import calculator.biz.parse.DelimiterParser;
import calculator.biz.parse.NumberParser;
import calculator.biz.service.SumService;
import calculator.biz.valid.ParsingValidator;

import java.util.List;

public class AddCalculator {
    private final DelimiterParser delimiterParser;
    private final NumberParser numberParser;
    private final ParsingValidator parsingValidator;
    private final SumService sumService;

    public AddCalculator(DelimiterParser delimiterParser,
                         NumberParser numberParser,
                         ParsingValidator parsingValidator,
                         SumService sumService) {
        this.delimiterParser = delimiterParser;
        this.numberParser = numberParser;
        this.parsingValidator = parsingValidator;
        this.sumService = sumService;
    }

    public long add(String input) {
        //빈 문자열 입력 시 0 반환
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] delimiters = delimiterParser.parseDelimiters(input);
        String body = delimiterParser.extractBody(input);
        List<Long> numbers = numberParser.parseNumbers(body, delimiters);

        parsingValidator.validate(numbers);

        long result = sumService.sum(numbers);

        return result;
    }
}
