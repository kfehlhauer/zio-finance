package com.kurtfehlhauer.ziofinance

package com.kurtfehlhauer

import zio._
import zio.console._
import zio.test._
import zio.test.Assertion._
import scala.math.BigDecimal

object TVMSpec extends DefaultRunnableSpec {
    def spec = suite("TVMSpec")(
        testM("Future Value - 0% interest 1 period, 1 year"){
            for {
                fv  <- TVM.fv(100,0.0, 1, 1)
            } yield assert(fv)(equalTo(BigDecimal(100.0))) 
        },
        testM("Future Value - 0% interest 1 periods, 0 year"){
            for {
                fv  <- TVM.fv(100,0.0, 1, 0).either.left
            } yield assert(fv)(equalTo("t - Number of years cannot be zero"))
        },
        // This test won't work because you get the left side returned
        // testM("Future Value - 0% interest 0 periods, 1 year"){
        //     for {
        //         fv  <- TVM.fv(100,0.0, 0, 1)
        //     } yield assert(fv)(equalTo(BigDecimal(1/0)))
        // },
        testM("Future Value(fvLumpSumDiscrete) - 0% interest 0 periods"){
            for {
                fv  <- TVM.fvLumpSumDiscrete(100,0.0,0)
            } yield assert(fv)(equalTo(BigDecimal(100.0))) 
        },
        testM("Future Value(fvLumpSumDiscrete) - 10% interest 4 periods"){
            for {
                fv  <- TVM.fvLumpSumDiscrete(405000,0.1,4)
            } yield assert(fv)(equalTo(BigDecimal(592960.5))) 
        },
        testM("Future Value(fvLumpSumContinous) - 10% interest 4 periods"){
            for {
                fv  <- TVM.fvLumpSumContinous(405000,0.1,4)
            } yield assert(fv)(equalTo(BigDecimal(604189.002544714))) 
        }


    )           
}