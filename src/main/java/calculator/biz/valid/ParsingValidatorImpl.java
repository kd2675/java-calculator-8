package calculator.biz.valid;

import java.util.List;

public class ParsingValidatorImpl implements ParsingValidator {
    @Override
    public void validate(List<Long> input) {
        for (Long num : input) {
            if (num <= 0) {
                throw new IllegalArgumentException("양수 이외의 수는 허용되지 않습니다.");
            }
        }
    }
}
