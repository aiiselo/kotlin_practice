class ComplexNumber(
    private val rn: Double, // мнимая единица
    private val iu: Double
) {
    fun GetRn(): Double {
        return this.rn
    }

    fun GetIu(): Double {
        return this.iu
    }

    public operator fun plus(secondNum: ComplexNumber): ComplexNumber {
        return ComplexNumber(this.GetRn() + secondNum.GetRn(), this.GetIu() + secondNum.GetIu())
    }

    public operator fun times(secondNum: ComplexNumber): ComplexNumber {
        return ComplexNumber(
            this.GetRn() * secondNum.GetRn() - this.GetIu() * secondNum.GetIu(),
            this.GetRn() * secondNum.GetIu() + this.GetIu() * secondNum.GetRn()
        )
    }

    fun GetModule(): Double {
        val a_pow = Math.pow(this.GetRn(), 2.0)
        val b_pow = Math.pow(this.GetIu(), 2.0)
        return Math.sqrt(a_pow + b_pow)
    }

    fun GetPhi(): Double {
        val arg = this.GetIu() / this.GetRn()
        return Math.atan(arg)
    }

    fun ToTrigonometry() {
        println(
            this.GetRn().toString() + "+" + this.GetIu() +
                    "i = " + this.GetModule() + "*(cos" +
                    this.GetPhi() + " + i*sin" + this.GetPhi() + ")"
        )
    }
}

fun main(args: Array<String>) {
    val x = ComplexNumber(2.0, 4.0)
    val y = ComplexNumber(3.0, 5.0)
    println("X = " + x.GetRn() + " " + x.GetIu())
    println("Y = " + y.GetRn() + " " + y.GetIu())
    var z: ComplexNumber
    z = x + y
    println("x + y = " + z.GetRn() + " " + z.GetIu())
    z = x * y
    println("x - y = " + z.GetRn() + " " + z.GetIu())
    println("Module of X = " + x.GetModule())
    println("Phi of X = " + x.GetPhi())
    println("X to Trigonometry:")
    x.ToTrigonometry()
}
