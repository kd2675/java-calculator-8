package calculator.biz.parse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DelimiterParser 인터페이스 테스트")
class DelimiterParserTest {

    static Stream<DelimiterParser> delimiterParserProvider() {
        return Stream.of(
                new DelimiterParserImpl(),
                new DelimiterParserPattern()
        );
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("기본 구분자를 반환한다")
    void parseDelimiters_default(DelimiterParser parser) {
        // given
        String input = "1,2:3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).contains(",", ":");
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("null 입력 시 예외 발생")
    void parseDelimiters_null(DelimiterParser parser) {
        assertThatThrownBy(() -> parser.parseDelimiters(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("기본 입력의 본문을 그대로 반환한다")
    void extractBody_default(DelimiterParser parser) {
        // given
        String input = "1,2:3";

        // when
        String body = parser.extractBody(input);

        // then
        assertThat(body).isEqualTo("1,2:3");
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("본문 추출시 null이면 예외 발생")
    void extractBody_null(DelimiterParser parser) {
        assertThatThrownBy(() -> parser.extractBody(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("커스텀 구분자를 파싱한다")
    void parseDelimiters_customDelimiter(DelimiterParser parser) {
        // given
        String input = "//;\\n1;2;3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).anyMatch(d -> d.contains(";"));
        assertThat(delimiters).contains(",", ":");
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("커스텀 구분자에서 본문을 추출한다")
    void extractBody_customDelimiter(DelimiterParser parser) {
        // given
        String input = "//;\\n1;2;3";

        // when
        String body = parser.extractBody(input);

        // then
        assertThat(body).isEqualTo("1;2;3");
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("잘못된 커스텀 구분자 형식은 예외 발생")
    void parseDelimiters_incorrectFormat(DelimiterParser parser) {
        // given
        String input = "//;";

        // when & then
        assertThatThrownBy(() -> parser.parseDelimiters(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("점(.) 구분자를 파싱한다")
    void parseDelimiters_dotDelimiter(DelimiterParser parser) {
        // given
        String input = "//.\\n1.2.3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).anyMatch(d -> d.contains("."));
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("점(.) 구분자에서 본문을 추출한다")
    void extractBody_dotDelimiter(DelimiterParser parser) {
        // given
        String input = "//.\\n1.2.3";

        // when
        String body = parser.extractBody(input);

        // then
        assertThat(body).isEqualTo("1.2.3");
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("파이프(|) 구분자를 파싱한다")
    void parseDelimiters_pipeDelimiter(DelimiterParser parser) {
        // given
        String input = "//|\\n1|2|3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).anyMatch(d -> d.contains("|"));
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("별표(*) 구분자를 파싱한다")
    void parseDelimiters_asteriskDelimiter(DelimiterParser parser) {
        // given
        String input = "//*\\n1*2*3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).anyMatch(d -> d.contains("*"));
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("물음표(?) 구분자를 파싱한다")
    void parseDelimiters_questionMarkDelimiter(DelimiterParser parser) {
        // given
        String input = "//?\\n1?2?3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).anyMatch(d -> d.contains("?"));
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("플러스(+) 구분자를 파싱한다")
    void parseDelimiters_plusDelimiter(DelimiterParser parser) {
        // given
        String input = "//+\\n1+2+3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).anyMatch(d -> d.contains("+"));
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("대괄호([) 구분자를 파싱한다")
    void parseDelimiters_bracketDelimiter(DelimiterParser parser) {
        // given
        String input = "//[\\n1[2[3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).anyMatch(d -> d.contains("["));
    }

    @ParameterizedTest
    @MethodSource("delimiterParserProvider")
    @DisplayName("백슬래시(\\) 구분자를 파싱한다")
    void parseDelimiters_backslashDelimiter(DelimiterParser parser) {
        // given
        String input = "//\\\\n1\\2\\3";

        // when
        String[] delimiters = parser.parseDelimiters(input);

        // then
        assertThat(delimiters).anyMatch(d -> d.contains("\\"));
    }
}