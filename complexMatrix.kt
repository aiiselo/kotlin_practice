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

    public operator fun minus(secondNum: ComplexNumber): ComplexNumber {
        return ComplexNumber(this.GetRn() - secondNum.GetRn(), this.GetIu() - secondNum.GetIu())
    }

    public operator fun times(secondNum: ComplexNumber): ComplexNumber {
        return ComplexNumber(
            this.GetRn() * secondNum.GetRn() - this.GetIu() * secondNum.GetIu(),
            this.GetRn() * secondNum.GetIu() + this.GetIu() * secondNum.GetRn()
        )
    }
}

class Matrix {
    var n: Int
    var m: Int
    var matrix: Array<Array<ComplexNumber>>

    constructor(n: Int, m: Int) {
        this.n = n
        this.m = m
        matrix = Array(n) { Array(m) { ComplexNumber(0.0, 0.0) } }
    }

    constructor(matrixArg: Array<Array<ComplexNumber>>) {
        n = matrixArg.size
        m = matrixArg[0].size
        matrix = matrixArg
    }

    fun getElem(i: Int, j: Int): ComplexNumber {
        return matrix[i][j]
    }

    fun setElem(i: Int, j: Int, value: ComplexNumber) {
        matrix[i][j] = value
    }

    fun printMatrix() {
        for (i in 0 until n) {
            for (j in 0 until m) {
                print("${matrix[i][j].GetRn()}+${matrix[i][j].GetIu()}i ")
            }
            println()
        }
    }

    public operator fun plus (y: Matrix): Matrix {
        return if (this.m != y.m || this.n != y.n) {
            throw Exception("Матрицы отличаются размерами")
        } else {
            val res = Matrix(this.n, this.m)
            for (i in 0 until res.n) {
                for (j in 0 until res.m) {
                    res.setElem(i, j, this.getElem(i, j) + y.getElem(i, j))
                }
            }
            res
        }
    }

    public operator fun minus(y: Matrix): Matrix {
        return if (this.m != y.m || this.n != y.n) {
            throw Exception("Матрицы отличаются размерами")
        } else {
            val res = Matrix(this.n, this.m)
            for (i in 0 until res.n) {
                for (j in 0 until res.m) {
                    res.setElem(i, j, this.getElem(i, j) - y.getElem(i, j))
                }
            }
            res
        }
    }

    public operator fun times(y: Matrix): Matrix {
                return if (this.m != y.n) {
            throw Exception("N != M")
        } else {
            val res = Matrix(this.n, y.m)
            var current_value = ComplexNumber(0.0,0.0)
            for (i in 0 until this.n) {
                for (z in 0 until y.m) {
                    var current_value = ComplexNumber(0.0,0.0)
                    for (j in 0 until y.n) { // идем по строкам второй матрицы
                        current_value = current_value + this.getElem(i, j) * y.getElem(j, z)
                    }
                    res.setElem(i, z, current_value)
                }
            }
            res
        }
    }

    fun transpose():Matrix {
        val res = Matrix(this.m, this.n)
        for (i in 0 until this.n) {
            for (j in 0 until this.m) {
                res.setElem(j, i, this.getElem(i, j))
            }
        }
        return res
    }
}

fun main(args: Array<String>) {
    var a = ComplexNumber(2.0, 4.0)
    var b = ComplexNumber(3.0, 5.0)
    var c = ComplexNumber(4.0, 6.0)
    var d = ComplexNumber(5.0, 8.0)

    var firstMatrix = arrayOf(arrayOf(a, b), arrayOf(c,d))
    var matrixA = Matrix(firstMatrix)

    var secondMatrix = arrayOf(arrayOf(a, b), arrayOf(c,d))
    var matrixB = Matrix(secondMatrix)

    println("A :")
    matrixA.printMatrix()
    println("B :")
    matrixB.printMatrix()

    println("D = A+C :")
    (matrixA + matrixB).printMatrix()
    println("E = A-C :")
    (matrixA - matrixB).printMatrix()
    println("Transpose A :")
    matrixA.transpose().printMatrix()
    println("F = A * B :")
    (matrixA * matrixB).printMatrix()
}
