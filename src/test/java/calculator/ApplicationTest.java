package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

    // 기본 기능 테스트
    @Test
    void 빈_문자열_입력시_0_반환() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 쉼표_구분자로_숫자_합산() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 콜론_구분자로_숫자_합산() {
        assertSimpleTest(() -> {
            run("1:2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 쉼표와_콜론_혼합_사용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 공백이_포함된_숫자_처리() {
        assertSimpleTest(() -> {
            run(" 1 , 2 , 3 ");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 큰_숫자_합산() {
        assertSimpleTest(() -> {
            run("1000,2000,3000");
            assertThat(output()).contains("결과 : 6000");
        });
    }

    // 커스텀 구분자 테스트
    @Test
    void 커스텀_구분자로_여러_숫자_합산() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자와_기본_구분자_혼합() {
        assertSimpleTest(() -> {
            run("//;\\n1;2,3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    // 특수문자 구분자 테스트
    @Test
    void 점_구분자_사용() {
        assertSimpleTest(() -> {
            run("//.\\n1.2.3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 파이프_구분자_사용() {
        assertSimpleTest(() -> {
            run("//|\\n10|20|30");
            assertThat(output()).contains("결과 : 60");
        });
    }

    @Test
    void 별표_구분자_사용() {
        assertSimpleTest(() -> {
            run("//*\\n5*5*5");
            assertThat(output()).contains("결과 : 15");
        });
    }

    @Test
    void 물음표_구분자_사용() {
        assertSimpleTest(() -> {
            run("//?\\n1?2?3?4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 플러스_구분자_사용() {
        assertSimpleTest(() -> {
            run("//+\\n100+200+300");
            assertThat(output()).contains("결과 : 600");
        });
    }

    @Test
    void 대괄호_구분자_사용() {
        assertSimpleTest(() -> {
            run("//[\\n1[2[3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 캐럿_구분자_사용() {
        assertSimpleTest(() -> {
            run("//^\\n10^20^30");
            assertThat(output()).contains("결과 : 60");
        });
    }

    @Test
    void 달러_구분자_사용() {
        assertSimpleTest(() -> {
            run("//$\\n5$10$15");
            assertThat(output()).contains("결과 : 30");
        });
    }

    // 예외 상황 테스트
    @Test
    void 음수_입력시_예외_발생() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자가_아닌_값_입력시_예외_발생() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1,a,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 잘못된_커스텀_구분자_형식_예외_발생() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//;"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_없이_본문만_있는_경우_예외_발생() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//\\n1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 본문_없이_커스텀_구분자만_있는_경우_예외_발생() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//;\\n"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    // 경계값 테스트
    @Test
    void 단일_숫자_입력() {
        assertSimpleTest(() -> {
            run("42");
            assertThat(output()).contains("결과 : 42");
        });
    }

    @Test
    void 영_입력시_예외_발생() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("0"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 영만_여러_개_입력시_예외_발생() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("0,0,0"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 매우_큰_숫자들의_합() {
        assertSimpleTest(() -> {
            run("1000000,2000000,3000000");
            assertThat(output()).contains("결과 : 6000000");
        });
    }
}
