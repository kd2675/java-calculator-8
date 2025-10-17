package calculator.biz.valid;

import java.util.List;

public class ParsingValidatorImpl implements ParsingValidator {
    @Override
    public void validate(List<Long> input) {
        validateNotNull(input);
        validateNotEmpty(input);
        validateAllPositive(input);
    }

    private void validateNotNull(List<Long> input) {
        if (input == null) {
            throw new IllegalArgumentException("입력 리스트는 null일 수 없습니다.");
        }
    }

    private void validateNotEmpty(List<Long> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("입력 리스트는 비어있을 수 없습니다.");
        }
    }

    private void validateAllPositive(List<Long> input) {
        for (int i = 0; i < input.size(); i++) {
            Long num = input.get(i);
            validateElementNotNull(num, i);
            validatePositive(num, i);
        }
    }

    private void validateElementNotNull(Long num, int index) {
        if (num == null) {
            throw new IllegalArgumentException(
                String.format("인덱스 %d의 값은 null일 수 없습니다.", index)
            );
        }
    }

    private void validatePositive(Long num, int index) {
        if (num <= 0) {
            throw new IllegalArgumentException(
                String.format("양수 이외의 수는 허용되지 않습니다. (인덱스: %d, 입력값: %d)", index, num)
            );
        }
    }
}
