package calculator.biz.parse;

public class DelimiterParserImpl implements DelimiterParser {
    private static final String[] DEFAULT_DELIMITERS = {",", ":"};

    @Override
    public String[] parseDelimiters(String input) {
        if (input == null) {
            throw new IllegalArgumentException("parseDelimiters input is null");
        }

        if (!input.startsWith("//")) {
            return DEFAULT_DELIMITERS;
        }

        int nl = input.indexOf("\\n");
        if (nl < 0) {
            throw new IllegalArgumentException("input custom delimiter 형식이 올바르지 않습니다.");
        }

        return new String[] { input.substring(2, nl), ",", ":" };
    }

    @Override
    public String extractBody(String input) {
        if (input == null) {
            throw new IllegalArgumentException("extractBody input is null");
        }

        if (!input.startsWith("//")) {
            return input;
        }

        int nl = input.indexOf("\\n");
        if (nl < 0) {
            throw new IllegalArgumentException("커스텀 구분자 형식 오류");
        }

        return input.substring(nl + 2);
    }
}
