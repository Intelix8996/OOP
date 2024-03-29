package ru.nsu.nrepin;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * A class for benchmarking sequential and ParallelStream checkers.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
public class PrimeCheckerBenchmark {

    private List<Integer> data;

    /**
     * Benchmarks SequentalPrimeChecker.
     *
     * @param bh black hole
     */
    @Benchmark
    public void benchmarkSequental(Blackhole bh) {

        PrimeChecker checker = new SequentalPrimeChecker();

        bh.consume(checker.checkList(data));
    }

    /**
     * Benchmarks ParallelStreamPrimeChecker.
     *
     * @param bh black hole
     */
    @Benchmark
    public void benchmarkParallelStream(Blackhole bh) {

        PrimeChecker checker = new ParallelStreamPrimeChecker();

        bh.consume(checker.checkList(data));
    }

    /**
     * Loads data for benchmark.
     *
     * @throws FileNotFoundException if file is not found
     */
    @Setup(Level.Trial)
    public void loadData() throws FileNotFoundException {
        try {
            data = TestDataReader.loadData();
        } catch (FileNotFoundException e) {
            throw new IOError(e);
        }
    }
}
