package calculator.biz.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SumService 인터페이스 테스트")
class SumServiceTest {

    static Stream<SumService> sumServiceProvider() {
        return Stream.of(
                new SumServiceImpl()
        );
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("숫자 리스트의 합을 계산한다")
    void sum_default(SumService service) {
        // given
        List<Long> numbers = Arrays.asList(1L, 2L, 3L);

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(6L);
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("빈 리스트의 합은 0이다")
    void sum_emptyList(SumService service) {
        // given
        List<Long> numbers = Collections.emptyList();

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(0L);
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("단일 숫자의 합은 자기 자신이다")
    void sum_one(SumService service) {
        // given
        List<Long> numbers = Collections.singletonList(5L);

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(5L);
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("음수를 포함한 합을 계산한다")
    void sum_minus(SumService service) {
        // given
        List<Long> numbers = Arrays.asList(-1L, 2L, 3L);

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(4L);
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("0을 포함한 합을 계산한다")
    void sum_zero(SumService service) {
        // given
        List<Long> numbers = Arrays.asList(0L, 1L, 2L, 3L);

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(6L);
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("큰 숫자들의 합을 계산한다")
    void sum_bigNumber(SumService service) {
        // given
        List<Long> numbers = Arrays.asList(1000L, 2000L, 3000L);

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(6000L);
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("백만 단위 숫자들의 합을 계산한다")
    void sum_millionValue(SumService service) {
        // given
        List<Long> numbers = Arrays.asList(1000000L, 2000000L, 3000000L);

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(6000000L);
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("Integer.MAX_VALUE를 초과하는 합을 계산한다")
    void sum_biggerValue(SumService service) {
        // given
        List<Long> numbers = Arrays.asList(3000000000L, 3000000000L);

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(6000000000L);
    }

    @ParameterizedTest
    @MethodSource("sumServiceProvider")
    @DisplayName("100억 단위 합을 계산한다")
    void sum_biggestValue(SumService service) {
        // given
        List<Long> numbers = Arrays.asList(10000000000L, 20000000000L, 30000000000L);

        // when
        long result = service.sum(numbers);

        // then
        assertThat(result).isEqualTo(60000000000L);
    }
}