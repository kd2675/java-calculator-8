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
            return new String[] { custom, ",", ":" };
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
            return m.group(2);
        }

        return input;
    }
}
