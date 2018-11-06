package com.scoverage

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType}

import scala.math.random


object Example {

  def main(args: Array[String]) {

    val sparkSession: SparkSession =
      SparkSession.builder
        .appName("ztt_etl")
        .getOrCreate()

    import sparkSession.implicits._

    val slices = if (args.length > 0) args(0).toInt else 2
    val n = math.min(100000L * slices, Int.MaxValue).toInt // avoid overflow
    val count = sparkSession.sparkContext.parallelize(1 until n, slices).map { i =>
      val x = random * 2 - 1
      val y = random * 2 - 1
      if (x * x + y * y < 1) 1 else 0
    }.reduce(_ + _)
    println("Pi is roughly " + 4.0 * count / n)
    sparkSession.stop()

  }
}