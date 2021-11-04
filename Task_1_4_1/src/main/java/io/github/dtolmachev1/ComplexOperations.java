package io.github.dtolmachev1;

/**
 * <p>Class for addition operation.</p>
 */
class ComplexAddition implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    ComplexAddition(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the sum of two complex numbers.</p>
     *
     * @return sum of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.add(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}

/**
 * <p>Class for subtraction operation.</p>
 */
class ComplexSubtraction implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    ComplexSubtraction(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the difference of two complex numbers.</p>
     *
     * @return difference of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.subtract(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}

/**
 * <p>Class for multiplication operation.</p>
 */
class ComplexMultiplication implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    ComplexMultiplication(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the product of two complex numbers.</p>
     *
     * @return product of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.multiply(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}

/**
 * <p>Class for division operation.</p>
 */
class ComplexDivision implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    ComplexDivision(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the quotient of two Complex numbers.</p>
     *
     * @return quotient of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.divide(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}

/**
 * <p>Class for natural logarithm operation.</p>
 */
class ComplexLogarithm implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    ComplexLogarithm(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the natural logarithm of a given number.</p>
     *
     * @return natural logarithm of left operand.
     */
    @Override
    public Complex eval() {
        return operand.log();
    }

    private final Complex operand;  // operand
}

/**
 * <p>Class for exponentiation operation.</p>
 */
class ComplexExponentiation implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    ComplexExponentiation(Complex leftOperand, Complex rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the power of two complex numbers.</p>
     *
     * @return power of left and right operands.
     */
    @Override
    public Complex eval() {
        return leftOperand.pow(rightOperand);
    }

    private final Complex leftOperand;  // left operand
    private final Complex rightOperand;  // right operand
}

/**
 * <p>Class for square root operation.</p>
 */
class ComplexSquareRoot implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    ComplexSquareRoot(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the square root of a given number.</p>
     *
     * @return square root of left operand.
     */
    @Override
    public Complex eval() {
        return operand.sqrt();
    }

    private final Complex operand;  // operand
}

/**
 * <p>Class for sine operation.</p>
 */
class ComplexSine implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    ComplexSine(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the sine of a given number.</p>
     *
     * @return sine of left operand.
     */
    @Override
    public Complex eval() {
        return operand.sin();
    }

    private final Complex operand;  // operand
}

/**
 * <p>Class for cosine operation.</p>
 */
class ComplexCosine implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    ComplexCosine(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the cosine of a given number.</p>
     *
     * @return cosine of left operand.
     */
    @Override
    public Complex eval() {
        return operand.cos();
    }

    private final Complex operand;  // operand
}

/**
 * <p>Class for tangent operation.</p>
 */
class ComplexTangent implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    ComplexTangent(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the tangent of a given number.</p>
     *
     * @return tangent of left operand.
     */
    @Override
    public Complex eval() {
        return operand.tan();
    }

    private final Complex operand;  // operand
}

/**
 * <p>Class for cotangent operation.</p>
 */
class ComplexCotangent implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    ComplexCotangent(Complex operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the cotangent of a given number.</p>
     *
     * @return cotangent of left operand.
     */
    @Override
    public Complex eval() {
        return operand.cot();
    }

    private final Complex operand;  // operand
}
