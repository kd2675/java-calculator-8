package calculator.biz.parse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("NumberParser 인터페이스 테스트")
class NumberParserTest {

    static Stream<NumberParser> numberParserProvider() {
        return Stream.of(
                new NumberParserImpl()
        );
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("쉼표로 구분된 숫자를 파싱한다")
    void parseNumbers_commaDelimiter(NumberParser parser) {
        // given
        String body = "1,2,3";
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly(1L, 2L, 3L);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("여러 구분자로 구분된 숫자를 파싱한다")
    void parseNumbers_defaultDelimiters(NumberParser parser) {
        // given
        String body = "1,2:3";
        String[] delimiters = {",", ":"};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly(1L, 2L, 3L);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("공백이 포함된 숫자를 파싱한다")
    void parseNumbers_trim(NumberParser parser) {
        // given
        String body = " 1 , 2 , 3 ";
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly(1L, 2L, 3L);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("빈 문자열은 빈 리스트를 반환한다")
    void parseNumbers_emptyInput(NumberParser parser) {
        // given
        String body = "";
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("null 입력은 빈 리스트를 반환한다")
    void parseNumbers_null(NumberParser parser) {
        // given
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(null, delimiters);

        // then
        assertThat(numbers).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("숫자가 아닌 값이 있으면 예외 발생")
    void parseNumbers_notNumber(NumberParser parser) {
        // given
        String body = "1,a,3";
        String[] delimiters = {","};

        // when & then
        assertThatThrownBy(() -> parser.parseNumbers(body, delimiters))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("음수를 포함하여 파싱한다")
    void parseNumbers_minusInput(NumberParser parser) {
        // given
        String body = "-1,2,3";
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly(-1L, 2L, 3L);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("커스텀 구분자로 숫자를 파싱한다")
    void parseNumbers_customDelimiter(NumberParser parser) {
        // given
        String body = "1;2;3";
        String[] delimiters = {";"};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly(1L, 2L, 3L);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("Integer.MAX_VALUE를 파싱한다")
    void parseNumbers_maxValue(NumberParser parser) {
        // given
        String body = String.valueOf(Integer.MAX_VALUE);
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly((long) Integer.MAX_VALUE);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("Integer.MIN_VALUE를 파싱한다")
    void parseNumbers_minValue(NumberParser parser) {
        // given
        String body = String.valueOf(Integer.MIN_VALUE);
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly((long) Integer.MIN_VALUE);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("매우 큰 숫자 여러 개를 파싱한다")
    void parseNumbers_bigValue(NumberParser parser) {
        // given
        String body = "1000000,2000000,3000000";
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly(1000000L, 2000000L, 3000000L);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("Integer.MAX_VALUE를 초과하는 숫자를 파싱한다")
    void parseNumbers_biggerValue(NumberParser parser) {
        // given
        String body = "9223372036854775807"; // Long.MAX_VALUE
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly(9223372036854775807L);
    }

    @ParameterizedTest
    @MethodSource("numberParserProvider")
    @DisplayName("100억 단위 숫자를 파싱한다")
    void parseNumbers_biggestValue(NumberParser parser) {
        // given
        String body = "10000000000,20000000000,30000000000";
        String[] delimiters = {","};

        // when
        List<Long> numbers = parser.parseNumbers(body, delimiters);

        // then
        assertThat(numbers).containsExactly(10000000000L, 20000000000L, 30000000000L);
    }
}