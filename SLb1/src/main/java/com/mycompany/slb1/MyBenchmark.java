package com.mycompany.slb1;

import org.openjdk.jmh.annotations.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@State(Scope.Benchmark)
public class MyBenchmark {

    private List<Integer> num;


    @Setup(Level.Trial)
    public void setup() {
        num = SLb1.Generate(10_000_000, 1, 100);
    }

    @Benchmark
    public long benchmarkSum() {
        return SLb1.Sum(num);
    }

    @Benchmark
    public double benchmarkAverage() {
        return SLb1.Average(num);
    }

    @Benchmark
    public double benchmarkDeviation() {
        return SLb1.Deviation(num);
    }

    @Benchmark
    public List<Integer> benchmarkMultiply() {
        return SLb1.Multiply(num);
    }

    @Benchmark
    public List<Integer> benchmarkFilter() {
        return SLb1.Filter(num);
    }
}
