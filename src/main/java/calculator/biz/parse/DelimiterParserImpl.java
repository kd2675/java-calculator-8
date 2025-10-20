package calculator.biz.parse;

import java.util.regex.Pattern;

public class DelimiterParserImpl implements DelimiterParser {
    private static final String[] DEFAULT_DELIMITERS = {",", ":"};
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";
    private static final int PREFIX_LENGTH = 2;
    private static final int SUFFIX_LENGTH = 2;

    @Override
    public String[] parseDelimiters(String input) {
        validateInput(input, "parseDelimiters");

        if (!hasCustomDelimiter(input)) {
            return DEFAULT_DELIMITERS;
        }

        String customDelimiter = extractCustomDelimiter(input);
        String escapedCustom = Pattern.quote(customDelimiter);
        return new String[] { escapedCustom, ",", ":" };
    }

    @Override
    public String extractBody(String input) {
        validateInput(input, "extractBody");

        if (!hasCustomDelimiter(input)) {
            return input;
        }

        int delimiterEndIndex = getDelimiterEndIndex(input);
        return input.substring(delimiterEndIndex + SUFFIX_LENGTH);
    }

    private void validateInput(String input, String methodName) {
        if (input == null) {
            throw new IllegalArgumentException(methodName + " input is null");
        }
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    private String extractCustomDelimiter(String input) {
        int delimiterEndIndex = getDelimiterEndIndex(input);
        return input.substring(PREFIX_LENGTH, delimiterEndIndex);
    }

    private int getDelimiterEndIndex(String input) {
        int index = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        if (index < 0) {
            throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다. (//구분자\\n 형식이어야 합니다)");
        }
        return index;
    }
}
