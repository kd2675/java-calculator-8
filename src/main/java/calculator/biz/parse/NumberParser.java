package calculator.biz.parse;

import java.util.List;

public interface NumberParser {
    /**********************************************************************************************
     * @Method 설명 : 숫자 추출
     * @작성일 : 2025. 10. 15.
     * @작성자 : 김도영
     * @변경이력 :
     **********************************************************************************************/
    List<Integer> parseNumbers(String body, String[] delimiters);
}
