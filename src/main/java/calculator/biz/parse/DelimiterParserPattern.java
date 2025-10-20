package calculator.biz.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterParserPattern implements DelimiterParser {
    private static final String[] DEFAULT_DELIMITERS = {",", ":"};
    private static final Pattern CUSTOM_PATTERN = Pattern.compile("^//(.+)\\\\n(.*)$");

    @Override
    public String[] parseDelimiters(String input) {
        if (input == null) {
            throw new IllegalArgumentException("parseDelimiters input is null");
        }

        Matcher m = CUSTOM_PATTERN.matcher(input);
        if (m.matches()) {
            String custom = m.group(1);
            if (custom.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
            }
            // 정규식 특수문자를 이스케이프 처리
            String escapedCustom = Pattern.quote(custom);
            return new String[] { escapedCustom, ",", ":" };
        }

        if (input.startsWith("//")) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다. (//구분자\\n본문)");
        }

        return DEFAULT_DELIMITERS;
    }

    @Override
    public String extractBody(String input) {
        if (input == null) {
            throw new IllegalArgumentException("extractBody input is null");
        }

        Matcher m = CUSTOM_PATTERN.matcher(input);
        if (m.matches()) {
            String body = m.group(2);
            if (body == null || body.isEmpty()) {
                throw new IllegalArgumentException("본문이 비어있습니다.");
            }
            return body;
        }

        if (input.startsWith("//")) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다. (//구분자\\n본문)");
        }

        return input;
    }
}
