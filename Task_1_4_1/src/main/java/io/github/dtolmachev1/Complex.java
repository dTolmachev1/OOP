package io.github.dtolmachev1;

/**
 * <p>Class for representing complex numbers.</p>
 */
public class Complex extends Number implements Comparable<Complex> {
    /**
     * <p>Constructor to initialize number with zero values.</p>
     */
    Complex() {
        this(0, 0);
    }

    /**
     * <p>Constructor to initialize number with only real part.</p>
     *
     * @param re real part.
     */
    Complex(double re) {
        this(re, 0);
    }

    /**
     * <p>Constructor to initialize number with real and imaginary part.</p>
     *
     * @param re real part.
     * @param im imaginary part.
     */
    Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    /**
     * <p>Constructor to initialize number with the given one.</p>
     *
     * @param complex number which value should be copied.
     */
    Complex(Complex complex) {
        this(complex.re, complex.im);
    }

    /**
     * <p>Constructor to initialize number with a given string.</p>
     *
     * @param value string to be parsed.
     * @throws NumberFormatException if the string does not contain a parsable number.
     */
    Complex(String value) throws NumberFormatException {
        value = preprocessString(value);  // preprocessing input string for parsing
        if (value.isEmpty() || (value.indexOf('i') != -1 && value.indexOf('i') != value.length() - 1)) {
            throw new NumberFormatException();
        }
        int idx = value.length() - 1;  // index of beginning of an imaginary part
        while (idx > 0 && ((value.charAt(idx) != '-' && value.charAt(idx) != '+') || "Ee".indexOf(value.charAt(idx - 1)) != -1)) {
            idx--;
        }
        if (idx != 0 || (value.charAt(value.length() - 1) != 'I' && value.charAt(value.length() - 1) != 'i')) {
            this.re = Double.parseDouble(value.substring(0, idx != 0 ? idx : value.length()));
        } else this.re = 0;
        if (value.charAt(value.length() - 1) == 'I' || value.charAt(value.length() - 1) == 'i') {
            this.im = Double.parseDouble(value.substring(idx, value.length() - 1));
        } else this.im = 0;
    }

    /**
     * <p>Creates new complex number from given real part.</p>
     *
     * @param re real part.
     * @return newly created complex number.
     */
    public static Complex valueOf(double re) {
        return new Complex(re);
    }

    /**
     * <p>Creates new complex number from given real and imaginary parts.</p>
     *
     * @param re real part.
     * @param im imaginary part.
     * @return newly created complex number.
     */
    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    /**
     * <p>Creates new complex number from a given one.</p>
     *
     * @param complex number to be copied.
     * @return newly created complex number.
     */
    public static Complex valueOf(Complex complex) {
        return new Complex(complex);
    }

    /**
     * <p>Creates new complex number from given string.</p>
     *
     * @param value string to be parsed.
     * @return newly created complex number.
     * @throws NumberFormatException if the string does not contain a parsable number.
     */
    public static Complex valueOf(String value) throws NumberFormatException {
        return new Complex(value);
    }

    /**
     * <p>Returns value of the given complex number in the string form representation.</p>
     *
     * @return string representing given complex number.
     */
    public String toString() {
        String value = "";  // to be returned
        if (re != 0) {
            value += Double.toString(re);
            if (im > 0) {
                value += '+';
            }
        }
        if (im != 0) {
            value += Double.toString(im) + 'i';
        }
        value = preprocessString(value);
        return value.length() != 0 ? value : "0";
    }

    /**
     * <p>Checks whether given number belongs to the complex plane, i.e. has an imaginary part.</p>
     *
     * @return <code>true</code> if this number has non-zero imaginary part or <code>false</code> otherwise.
     */
    public boolean isComplex() {
        return im != 0;
    }

    /**
     * <p>Returns the real part of given number.</p>
     *
     * @return real part of it's number.
     */
    public double getReal() {
        return re;
    }

    /**
     * <p>Returns the imaginary part of given number.</p>
     *
     * @return imaginary part of it's number.
     */
    public double getImaginary() {
        return im;
    }

    /**
     * <p>Returns the sum of given complex numbers.</p>
     *
     * @param value right operand.
     * @return sum of these numbers.
     */
    public Complex add(Complex value) {
        return valueOf(this.re + value.re, this.im + value.im);  // (a+bi) + (c+di) = ((a+c) + (b+d)i)
    }

    /**
     * <p>Returns the difference of given complex numbers.</p>
     *
     * @param value right operand.
     * @return difference of these numbers.
     */
    public Complex subtract(Complex value) {
        return valueOf(this.re - value.re, this.im - value.im);  // (a+bi) - (c+di) = ((a-c) - (b-d)i)
    }

    /**
     * <p>Returns the product of given complex numbers.</p>
     *
     * @param value right operand.
     * @return product of these numbers.
     */
    public Complex multiply(Complex value) {
        return valueOf(this.re * value.re - this.im * value.im, this.im * value.re + this.re * value.im);  // (a+bi) \times (c+di) = ((a \times c - b \times d) + (b \times c + a \times d)i)
    }

    /**
     * <p>Returns the quotient of given complex numbers.</p>
     *
     * @param value right operand.
     * @return quotient of these numbers.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public Complex divide(Complex value) {
        double denominator = Math.pow(value.re, 2) + Math.pow(value.im, 2);
        return valueOf((this.re * value.re + this.im * value.im) / denominator, (this.im * value.re - this.re * value.im) / denominator);  // \frac{a+bi}{c+di} = \frac{(a \times c + b \times d) + (b \times c - a \times d)i}{c^2 + d^2}
    }

    /**
     * <p>Returns natural logarithm of a given complex number.</p>
     *
     * @return natural logarithm of a given number.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public Complex log() {
        double rho = this.abs();
        double theta = Math.atan2(this.re, this.im);
        return this.im != 0 || this.re < 0 ? valueOf(rho, theta) : valueOf(Math.log(this.re));  // \ln{(a+bi)} = (\sqrt{a^2+b^2} + \arctg{(\frac{b}{a})}i)
    }

    /**
     * <p>Returns given complex number raised to the specified complex power.</p>
     *
     * @param value right operand.
     * @return given number raised to the specified power.
     */
    @SuppressWarnings({"SpellCheckingInspection", "DuplicateExpressions"})
    public Complex pow(Complex value) {
        if (this.im != 0 || (this.re <= 0 && value.im != 0)) {
            double rho = this.abs();
            double theta = Math.atan2(this.re, this.im);
            return valueOf(Math.pow(rho, value.re) * Math.exp(-1 * value.im * theta) * Math.cos(value.im * Math.log(rho) + value.re * theta), Math.sin(value.im * Math.log(rho) + value.re * theta));  // (a+bi)^{c+di} = (((\sqrt{a^2+b^2})^c \times e^{-d \times \arctg{(\frac{b}{a})}} \times \cos{(d \times \ln{(\sqrt{a^2+b^2})}+c \times \arctg{(\frac{b}{a})})}) + (\sin{(d \times \ln{(\sqrt{a^2+b^2})}+c \times \arctg{(\frac{b}{a})})})i) \forall z=(a+bi), w=(c+di) \colon z, w \in \mathbb{C}
        }
        return value.im != 0 ? valueOf(Math.pow(this.re, value.re) * Math.cos(value.im * Math.log(this.re)), Math.sin(value.im * Math.log(this.re))) : valueOf(Math.pow(this.re, value.re));  // a^{c+di} = ((a^c \times \cos{(d \times \ln{(a)})}) + (\sin{(d \times \ln{(a)})})i) \forall a, w==(c+di) \colon a \in \mathbb{R}^+, w \in \mathbb{C}
    }

    /**
     * <p>Returns square root of a given complex number.</p>
     *
     * @return square root of a given number.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public Complex sqrt() {
        int signum = this.im < 0 ? -1 : 1;
        return this.im != 0 || this.re < 0 ? valueOf(Math.sqrt((this.re + this.abs()) / 2), signum * Math.sqrt((-this.re + this.abs()) / 2)) : valueOf(Math.sqrt(this.re));  // \sqrt{a+bi} = \frac{(\sqrt{a+\sqrt{a^2+b^2}}) + (\sgn{(b)} \times \sqrt{-a+\sqrt{a^2+b^2}})i}{2}
    }

    /**
     * <p>Returns sine of a given complex number.</p>
     *
     * @return sine of a given number.
     */
    public Complex sin() {
        return this.im != 0 ? valueOf(Math.sin(this.re) * Math.cosh(this.im), Math.cos(this.re) * Math.sinh(this.im)) : valueOf(Math.sin(this.re));  // \sin{(a+bi)} = ((\sin{(a)} \times \ch{(b)}) + (\cos{(a)} \times \sh{(b)})i)
    }

    /**
     * <p>Returns cosine of a given complex number.</p>
     *
     * @return cosine of a given number.
     */
    public Complex cos() {
        return this.im != 0 ? valueOf(Math.cos(this.re) * Math.cosh(this.im), -1 * Math.sin(this.re) * Math.sinh(this.im)) : valueOf(Math.cos(this.re));  // \cos{(a+bi)} = ((\cos{(a)} \times \ch{(b)}) - (\sin{(a)} \times \sh{(b)})i)
    }

    /**
     * <p>Returns tangent of a given complex number.</p>
     *
     * @return tangent of a given number.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public Complex tan() {
        double denominator = 1 + Math.pow(Math.tan(this.re), 2) * Math.pow(Math.tanh(this.im), 2);
        return this.im != 0 ? valueOf((Math.tan(this.re) - Math.tan(this.re) * Math.pow(Math.tanh(this.im), 2)) / denominator, (Math.tanh(this.im) + Math.pow(Math.tan(this.re), 2) * Math.tanh(this.im)) / denominator) : valueOf(Math.tan(this.re));  // \tg{(a+bi)} = \frac{(\tg{(a)}-\tg{(a)} \times \th^2{(b)}) + (\th{(b)}+\tg^2{(a)} \times \th{(b)})i}{1 + \tg^2{(a)} \times \th^2{(b)}}
    }

    /**
     * <p>Returns cotangent of a given complex number.</p>
     *
     * @return cotangent of a given number.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public Complex cot() {
        double denominator = Math.pow(1.0 / Math.tan(this.re), 2) + Math.pow(1.0 / Math.tanh(this.im), 2);
        return this.im != 0 ? valueOf(((1.0 / Math.tan(this.re)) * Math.pow(1.0 / Math.tanh(this.im), 2) - (1.0 / Math.tan(this.re))) / denominator, (-1 * Math.pow(1.0 / Math.tan(this.re), 2) * (1.0 / Math.tanh(this.im)) - (1.0 / Math.tanh(this.im))) / denominator) : valueOf(1.0 / Math.tan(this.re));  // \ctg{(a+bi)} = \frac{(\ctg{(a)} \times \cth^2{(b)}-\ctg{(a)}) - (\ctg^2{(a)} \times \cth{(b)}-\cth{(b)})i}{\ctg^2{(a)} + \cth^2{(b)}}
    }

    /**
     * <p>Returns the absolute value (or modulus) of a given complex number.</p>
     *
     * @return absolute value of this number.
     */
    public double abs() {
        return this.im != 0 ? Math.sqrt(Math.pow(this.re, 2) + Math.pow(this.im, 2)) : Math.abs(this.re);  // |a+bi| = \sqrt{a^2+b^2}
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>byte</code> after a narrowing primitive conversion.</p>
     *
     * @return the absolute value of this object converted to type <code>byte</code>.
     */
    public byte byteValue() {
        return (byte) abs();
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>short</code> after a narrowing primitive conversion.</p>
     *
     * @return the absolute value of this object converted to type <code>short</code>.
     */
    public short shortValue() {
        return (short) abs();
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as an <code>int</code> after a narrowing primitive conversion.</p>
     *
     * @return the absolute value of this object converted to type <code>int</code>.
     */
    public int intValue() {
        return (int) abs();
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>long</code> after a narrowing primitive conversion.</p>
     *
     * @return the absolute value of this object converted to type <code>long</code>.
     */
    public long longValue() {
        return (long) abs();
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>float</code> after a narrowing primitive conversion.</p>
     *
     * @return the absolute value of this object converted to type <code>float</code>.
     */
    public float floatValue() {
        return (float) abs();
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>double</code> after a narrowing primitive conversion.</p>
     *
     * @return the absolute value of this object converted to type <code>double</code>.
     */
    public double doubleValue() {
        return abs();
    }

    /**
     * <p>Compares this complex number with the given one.</p>
     *
     * @param complex number to compare.
     * @return <code>0</code> if the numbers are equaled, negative integer if first value is less than second, and positive integer if first value is greater than second.
     */
    public int compareTo(Complex complex) {
        return Double.compare(this.abs(), complex.abs());
    }

    /**
     * <p>Returns the smallest of two complex numbers.</p>
     *
     * @param value the second operand.
     * @return the smallest of <code>a</code> and <code>b</code>.
     */
    public Complex min(Complex value) {
        return this.compareTo(value) <= 0 ? this : value;
    }

    /**
     * <p>Returns the greatest of two complex numbers.</p>
     *
     * @param value the second operand.
     * @return the greatest of <code>a</code> and <code>b</code>.
     */
    public Complex max(Complex value) {
        return this.compareTo(value) <= 0 ? value : this;
    }

    private final double re;  // for storing real part
    private final double im;  // for storing imaginary part

    /* preprocesses given string for the further parsing */
    private String preprocessString(String string) {
        string = string.trim();  // removing unnecessary whitespaces
        string = string.replaceFirst("-[Ii]", "-1i");  // for imaginary part
        string = string.replaceFirst("\\+[Ii]", "+1i");  // for imaginary part
        string = string.replaceFirst("^[Ii]", "1i");  // for imaginary part
        return string;
    }
}
