package calculator.biz.service;

import java.util.List;

public class SumServiceImpl implements SumService{
    @Override
    public long sum(List<Long> numbers) {
        long acc = 0;
        for (long n : numbers) {
            acc += n;
        }

        return acc;
    }
}
