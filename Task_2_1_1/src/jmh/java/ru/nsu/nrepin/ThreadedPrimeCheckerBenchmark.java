package ru.nsu.nrepin;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
public class ThreadedPrimeCheckerBenchmark {
    private List<Integer> data;

    @Param({"1", "2", "3", "4"})
    private int threadCount;

    @Benchmark
    public void benchmarkThreaded(Blackhole bh) {

        PrimeChecker checker = new ThreadedPrimeChecker(threadCount);

        bh.consume(checker.checkList(data));
    }

    @Setup(Level.Trial)
    public void loadData() throws FileNotFoundException {
        try {
            data = TestDataReader.loadData();
        } catch (FileNotFoundException e) {
            throw new IOError(e);
        }
    }
}
