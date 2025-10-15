package calculator.biz.service;

import java.util.List;

public class SumServiceImpl implements SumService{
    @Override
    public int sum(List<Integer> numbers) {
        int acc = 0;
        for (int n : numbers) {
            acc += n;
        }

        return acc;
    }
}
