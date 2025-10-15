package calculator.biz.valid;

import java.util.List;

public class ParsingValidatorImpl implements ParsingValidator {
    @Override
    public void validate(List<Integer> input) {
        for (Integer num : input) {
            if (num < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            }
        }
    }
}
