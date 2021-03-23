class Matrix {
    var n: Int
    var m: Int
    var matrix: Array<IntArray>

    constructor(n: Int, m: Int) {
        this.n = n
        this.m = m
        matrix = Array(n) { IntArray(m) }
    }

    constructor(matrixArg: Array<IntArray>) {
        n = matrixArg.size
        m = matrixArg[0].size
        matrix = matrixArg
    }

    fun getElem(i: Int, j: Int): Int {
        return matrix[i][j]
    }

    fun setElem(i: Int, j: Int, value: Int) {
        matrix[i][j] = value
    }

    fun PrintMatrix() {
        for (i in 0 until n) {
            for (j in 0 until m) {
                print(matrix[i][j].toString() + " ")
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
            var current_value = 0
            for (i in 0 until this.n) {
                for (z in 0 until y.m) {
                    current_value = 0
                    for (j in 0 until y.n) { // идем по строкам второй матрицы
                        current_value += this.getElem(i, j) * y.getElem(j, z)
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

    fun findDeterminant(): Int {
        return if (this.n != this.m) {
            throw Exception("Матрица не квадратная!")
        } else {
            if (this.m == 2) {
                this.getElem(0, 0) * this.getElem(1, 1) - this.getElem(0, 1) * this.getElem(1, 0)
            } else {
                var res = 0
                for (i in 0 until this.m) {
                    val matrixNew = Matrix(this.n - 1, this.n - 1)
                    var iNew = 0
                    var jNew = 0
                    for (z in 1 until this.m) {
                        for (j in 0 until this.m) {
                            if (j != i) {
                                matrixNew.setElem(iNew, jNew, this.getElem(z, j))
                                jNew += 1
                                if (jNew == this.n - 1) {
                                    iNew += 1
                                    jNew = 0
                                }
                            }
                        }
                    }
                    if (i % 2 == 0) {
                        res += this.getElem(0, i) * matrixNew.findDeterminant()
                    } else {
                        res -= this.getElem(0, i) * matrixNew.findDeterminant()
                    }
                }
                return res
            }
        }
    }
}

fun main() {
    val firstMatrix = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))
    val secondMatrix = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6))
    val thirdMatrix = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))
    val MatrixDeterm = arrayOf(intArrayOf(1, 2, 3), intArrayOf(1, 5, 3), intArrayOf(1, 2, 66))
    val A = Matrix(firstMatrix)
    val B = Matrix(secondMatrix)
    val C = Matrix(thirdMatrix)
    val Det = Matrix(MatrixDeterm)
    println("A :")
    A.PrintMatrix()
    println("B :")
    B.PrintMatrix()
    println("C :")
    C.PrintMatrix()
    println("D = A+C :")
    (A + C).PrintMatrix()
    println("E = A-C :")
    (A - C).PrintMatrix()
    println("Transpose A :")
    A.transpose().PrintMatrix()
    println("F = A * B :")
    (A * B).PrintMatrix()
    println("Determinant of Det:")
    println(Det.findDeterminant())
}