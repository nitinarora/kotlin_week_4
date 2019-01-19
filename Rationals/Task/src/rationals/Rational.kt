package rationals

import java.math.BigInteger

data class Rational(val numerator: BigInteger, val denominator: BigInteger) {
    override fun toString(): String {

        val (normNumerator, normDenominator) = normalize(this)

        return when (normDenominator) {
            BigInteger.ONE -> normNumerator.toString()
            else -> normNumerator.toString() + "/" + normDenominator.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val normalizedOther: Rational = normalize(other as Rational)
        return (this.numerator == normalizedOther.numerator && this.denominator == normalizedOther.denominator)
    }

    override fun hashCode(): Int {
        var result = numerator.hashCode()
        result = 31 * result + denominator.hashCode()
        return result
    }
}

infix fun Number.divBy(other: Number): Rational {
    val num: BigInteger = when {
        this is Int -> this.toBigInteger()
        this is Long -> this.toBigInteger()
        else -> this as BigInteger
    }
    val denom: BigInteger = when (other) {
        is Int -> other.toBigInteger()
        is Long -> other.toBigInteger()
        else -> other as BigInteger
    }

    return normalize(Rational(num, denom))
}

fun String.toRational(): Rational = when {
    !this.contains("/") -> Rational(this.toBigInteger(), "1".toBigInteger())
    else -> Rational(substringBefore('/').toBigInteger(), substringAfter('/').toBigInteger())
}

fun lcm(first: BigInteger, second: BigInteger): BigInteger = (first * second) / first.gcd(second)

fun normalize(r: Rational): Rational {
    val num: BigInteger = when {
        r.numerator < BigInteger.ZERO -> -r.numerator
        else -> r.numerator
    }

    val hcf = num.gcd(r.denominator)
    return Rational(r.numerator / hcf, r.denominator / hcf)
}

operator fun Rational.plus(other: Rational): Rational {
    val leftSide = normalize(this)
    val rightSide = normalize(other)

    val leftDenominator = leftSide.denominator
    val rightDenominator = rightSide.denominator

    val lcmDenominators = lcm(leftDenominator, rightDenominator)

    val factorLeft = lcmDenominators / leftDenominator
    val factorRight = lcmDenominators / rightDenominator

    return normalize(Rational((leftSide.numerator * factorLeft) + (rightSide.numerator * factorRight),
            lcmDenominators))
}

operator fun Rational.minus(other: Rational): Rational {
    val leftSide = normalize(this)
    val rightSide = normalize(other)

    val leftDenominator = leftSide.denominator
    val rightDenominator = rightSide.denominator

    val lcmDenominators = lcm(leftDenominator, rightDenominator)

    val factorLeft = lcmDenominators / leftDenominator
    val factorRight = lcmDenominators / rightDenominator

    return normalize(Rational((leftSide.numerator * factorLeft) - (rightSide.numerator * factorRight),
            lcmDenominators))
}

operator fun Rational.times(other: Rational): Rational =
        normalize(Rational(this.numerator * other.numerator, this.denominator * other.denominator))

operator fun Rational.div(other: Rational): Rational =
        normalize(Rational(this.numerator * other.denominator, this.denominator * other.numerator))

operator fun Rational.unaryMinus(): Rational {
    val normalizedFraction = normalize(this)
    return Rational(-normalizedFraction.numerator, normalizedFraction.denominator)
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
//    println(half < twoThirds)

//    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}