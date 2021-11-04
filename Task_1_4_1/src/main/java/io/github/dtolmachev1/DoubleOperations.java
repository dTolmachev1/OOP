package io.github.dtolmachev1;

/**
 * <p>Class for addition operation.</p>
 */
class DoubleAddition implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    DoubleAddition(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the sum of two double numbers.</p>
     *
     * @return sum of left and right operands.
     */
    @Override
    public Double eval() {
        return leftOperand + rightOperand;
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}

/**
 * <p>Class for subtraction operation.</p>
 */
class DoubleSubtraction implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    DoubleSubtraction(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the difference of two double numbers.</p>
     *
     * @return difference of left and right operands.
     */
    @Override
    public Double eval() {
        return leftOperand - rightOperand;
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}

/**
 * <p>Class for multiplication operation.</p>
 */
class DoubleMultiplication implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    DoubleMultiplication(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the product of two double numbers.</p>
     *
     * @return product of left and right operands.
     */
    @Override
    public Double eval() {
        return leftOperand * rightOperand;
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}

/**
 * <p>Class for division operation.</p>
 */
class DoubleDivision implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    DoubleDivision(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the quotient of two double numbers.</p>
     *
     * @return quotient of left and right operands.
     */
    @Override
    public Double eval() {
        return leftOperand / rightOperand;
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}

/**
 * <p>Class for natural logarithm operation.</p>
 */
class DoubleLogarithm implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    DoubleLogarithm(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the natural logarithm of a given number.</p>
     *
     * @return natural logarithm of left operand.
     */
    @Override
    public Double eval() {
        return Math.log(operand);
    }

    private final Double operand;  // operand
}

/**
 * <p>Class for exponentiation operation.</p>
 */
class DoubleExponentiation implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param leftOperand left operand.
     * @param rightOperand right operand.
     */
    DoubleExponentiation(Double leftOperand, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * <p>Returns the power of two double numbers.</p>
     *
     * @return power of left and right operands.
     */
    @Override
    public Double eval() {
        return Math.pow(leftOperand, rightOperand);
    }

    private final Double leftOperand;  // left operand
    private final Double rightOperand;  // right operand
}

/**
 * <p>Class for square root operation.</p>
 */
class DoubleSquareRoot implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    DoubleSquareRoot(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the square root of a given number.</p>
     *
     * @return square root of left operand.
     */
    @Override
    public Double eval() {
        return Math.sqrt(operand);
    }

    private final Double operand;  // operand
}

/**
 * <p>Class for sine operation.</p>
 */
class DoubleSine implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    DoubleSine(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the sine of a given number.</p>
     *
     * @return sine of left operand.
     */
    @Override
    public Double eval() {
        return Math.sin(operand);
    }

    private final Double operand;  // operand
}

/**
 * <p>Class for cosine operation.</p>
 */
class DoubleCosine implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    DoubleCosine(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the cosine of a given number.</p>
     *
     * @return cosine of left operand.
     */
    @Override
    public Double eval() {
        return Math.cos(operand);
    }

    private final Double operand;  // operand
}

/**
 * <p>Class for tangent operation.</p>
 */
class DoubleTangent implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    DoubleTangent(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the tangent of a given number.</p>
     *
     * @return tangent of left operand.
     */
    @Override
    public Double eval() {
        return Math.tan(operand);
    }

    private final Double operand;  // operand
}

/**
 * <p>Class for cotangent operation.</p>
 */
class DoubleCotangent implements Operation {
    /**
     * <p>Constructor to initialize operation with given operands.</p>
     *
     * @param operand operand.
     */
    DoubleCotangent(Double operand) {
        this.operand = operand;
    }

    /**
     * <p>Returns the cotangent of a given number.</p>
     *
     * @return cotangent of left operand.
     */
    @Override
    public Double eval() {
        return 1.0 / Math.tan(operand);
    }

    private final Double operand;  // operand
}
