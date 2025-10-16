package calculator.biz.valid;

import java.util.List;

public interface ParsingValidator {
    /**********************************************************************************************
     * @Method 설명 : 파싱된 리스트 검증
     * 양수만 허용(음수, 숫자 아님, 형식 오류 시 IllegalArgumentException)
     * 예외 발생 시 애플리케이션 종료 흐름 처리(메시지 출력 선택 사항)
     * @작성일 : 2025. 10. 15.
     * @작성자 : 김도영
     * @변경이력 :
     **********************************************************************************************/
    void validate(List<Long> input);
}
