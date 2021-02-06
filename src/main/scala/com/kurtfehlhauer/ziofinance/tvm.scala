package com.kurtfehlhauer.ziofinance

import zio._
import scala.math.{BigDecimal, E, pow}
import java.math.MathContext

/**
  * TVM is a collection of ''Time Value of Money' functions.
  */
object TVM {

  /** Sets the decimal precision to 15 digits. This is inclusive of both the left and right to the decimal place.
    */
  val rMc = new MathContext(15)

  /** Returns the future value of a lump sum for a given present value, interest rate,
    *  number of periods and number of years.
    *
    * @param pv Present ralue
    * @param r Interest rate
    * @param n Number of compounding periods per year
    * @param t Number of years
    * @return The Future Value
    */
  def fv(
      pv: BigDecimal,
      r: Double,
      n: Long,
      t: Long
  ): IO[String, BigDecimal] = {
    if (t != 0) {
      ZIO.succeed((pv * pow((1 + (r / t)), n * t)).round(rMc))
    } else {
      ZIO.fail("t - Number of years cannot be zero")
    }
  }

  /** Returns the discrete compounding of a future value of a lump sum for a given present value, interest rate,
    *  and number of periods.
    *
    * @param pv Present value
    * @param r Interest rate
    * @param n Number of periods (Year, Months, Days)
    * @return
    */
  def fvLumpSumDiscrete(pv: BigDecimal, r: Double, n: Long): UIO[BigDecimal] =
    ZIO.succeed(
      (pv * pow((1 + r), n)).round(rMc)
    )

    def fvLumpSumContinous(pv: BigDecimal, r: Double, n: Long): UIO[BigDecimal] =
    ZIO.succeed(
      (pv * pow(E, (r*n))).round(rMc)
    )

}
