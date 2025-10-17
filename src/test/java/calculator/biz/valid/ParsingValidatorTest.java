package calculator.biz.valid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ParsingValidator 인터페이스 테스트")
class ParsingValidatorTest {

    static Stream<ParsingValidator> validatorProvider() {
        return Stream.of(
                new ParsingValidatorImpl()
        );
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("모든 양수는 검증을 통과한다")
    void validate_number(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(1L, 2L, 3L, 4L, 5L);

        // when & then
        assertThatCode(() -> validator.validate(numbers))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("0이 있으면 검증을 실패한다")
    void validate_zero(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(0L, 1L, 2L, 3L);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("빈 리스트는 검증을 실패한다")
    void validate_emptyList(ParsingValidator validator) {
        // given
        List<Long> numbers = Collections.emptyList();

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어있을 수 없습니다");
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("null 리스트는 검증을 실패한다")
    void validate_nullList(ParsingValidator validator) {
        // given
        List<Long> numbers = null;

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("null일 수 없습니다");
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("리스트 내 null 요소가 있으면 검증을 실패한다")
    void validate_nullElement(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(1L, null, 3L);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("null일 수 없습니다");
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("첫 번째 요소가 null이면 검증을 실패한다")
    void validate_firstNull(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(null, 2L, 3L);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("인덱스 0");
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("마지막 요소가 null이면 검증을 실패한다")
    void validate_lastNull(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(1L, 2L, null);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("인덱스 2");
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("음수가 포함되면 예외 발생")
    void validate_minusValue(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(1L, -2L, 3L);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("첫 번째가 음수이면 예외 발생")
    void validate_minusFirst(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(-1L, 2L, 3L);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("마지막이 음수이면 예외 발생")
    void validate_minusLast(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(1L, 2L, -3L);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, -10L, -100L, -999L})
    @DisplayName("다양한 음수값에 대해 예외 발생")
    void validate_minusAll(long negativeNumber) {
        // given
        ParsingValidator validator = new ParsingValidatorImpl();
        List<Long> numbers = Arrays.asList(1L, negativeNumber, 3L);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("모두 음수이면 예외 발생")
    void validate_minusAll(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(-1L, -2L, -3L);

        // when & then
        assertThatThrownBy(() -> validator.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("큰 양수도 검증을 통과한다")
    void validate_bigMinusAll(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(1000L, 2000L, 3000L);

        // when & then
        assertThatCode(() -> validator.validate(numbers))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("validatorProvider")
    @DisplayName("Long 타입 큰 양수도 검증을 통과한다")
    void validate_bigValue(ParsingValidator validator) {
        // given
        List<Long> numbers = Arrays.asList(10000000000L, 20000000000L, 30000000000L);

        // when & then
        assertThatCode(() -> validator.validate(numbers))
                .doesNotThrowAnyException();
    }
}