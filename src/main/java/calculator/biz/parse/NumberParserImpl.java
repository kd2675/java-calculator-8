package calculator.biz.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NumberParserImpl implements NumberParser {
    @Override
    public List<Long> parseNumbers(String body, String[] delimiters) {
        if (body == null || body.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> result = new ArrayList<>();

        String splitRegex = Arrays.stream(delimiters)
                .map(delimiter -> "(" + delimiter + ")")
                .collect(Collectors.joining("|"));

        String[] tokens = body.split(splitRegex, 0);
        for (String token : tokens) {
            String s = token.trim();
            if (s.isEmpty()) {
                continue;
            }

            try {
                result.add(Long.parseLong(s));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값 : " + s);
            }
        }

        return result;
    }
}
