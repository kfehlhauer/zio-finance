# zio-finance (WIP)

## A collection of financial formulas that play well in the ZIO ecosystem.


Example
```
import zio._
import zio.console._
import com.kurtfehlhauer.ziofinance

object app extends zio.App {
  import TVM._

  def run(args: List[String]) =
    app.exitCode

  val investment = 405000
  val interestRate = 0.1
  val years = 1
  val periods = 100


  val app =
    for {
      fv1       <- fvLumpSumDiscrete(investment, interestRate, years)
      _         <- putStrLn(fv1.toDouble.toString())
      fv2       <- fvLumpSumContinous(investment, interestRate, years)
      _         <- putStrLn(fv2.toDouble.toString())
      fv3       <- fv(investment, interestRate, years, periods)
      _         <- putStr(fv3.toString())
    } yield ()
}
```

