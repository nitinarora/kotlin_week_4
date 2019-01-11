package rationals

import java.math.BigInteger

data class Rational(val numerator: BigInteger, val denominator: BigInteger)

infix fun Int.divBy(other: Int):Rational = Rational(this.toBigInteger(), other.toBigInteger())

fun hcf(first: BigInteger, second: BigInteger):BigInteger {
    if (second != BigInteger.ZERO)
        return (hcf(second, first % second))
    else
        return first
}

fun normalize(r: Rational): Rational {
    val hcf = hcf(r.numerator, r.denominator)
    return Rational(r.numerator/hcf, r.denominator/hcf)
}

operator fun Rational.plus(other: Rational) : Rational {
    val leftSide = normalize(this)
    val rightSide = normalize(other)

    if
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}