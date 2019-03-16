package io.monadplus.jmhexample

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

class MyBenchmark {

  @State(Scope.Thread)
  object X {
    val i = 10
  }

  @Setup(Level.Trial)
  def setup() =
    println("Set up")

  @TearDown(Level.Trial)
  def teardown() =
    println("Tear down")

  @Param(Array(1, 2, 3, 4))
  var size: Int = _

  @Benchmark
  @BenchmarkMode(Mode.Throughput)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  def testMethod(): Unit = {
    val a = 1
    val b = 2
    val sum = a + b
  }

  // Avoid dead call
  @Benchmark
  def void(blackhole: Blackhole): Unit = {
    val a = 1
    val b = 2
    val sum = a + b
    blackhole.consume(sum)
  }
}
