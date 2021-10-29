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
        if(value.isEmpty() || (value.indexOf('i') != -1 && value.indexOf('i') != value.length() - 1)) {
            throw new NumberFormatException();
        }
        int idx = value.length() - 1;  // index of beginning of an imaginary part
        while(idx > 0 && ((value.charAt(idx) != '-' && value.charAt(idx) != '+') || "Ee".indexOf(value.charAt(idx - 1)) != -1)) {
            idx--;
        }
        this.re = idx != 0 || (value.charAt(value.length() - 1) != 'I' && value.charAt(value.length() - 1) != 'i') ? Double.parseDouble(value.substring(0, idx != 0 ? idx : value.length())) : 0;
        this.im = value.charAt(value.length() - 1) == 'I' || value.charAt(value.length() - 1) == 'i' ? Double.parseDouble(value.substring(idx, value.length() - 1)) : 0;
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
        if(re != 0) {
            value += Double.toString(re);
            if(im > 0) {
                value += '+';
            }
        }
        if(im != 0) {
            value += Double.toString(im) + 'i';
        }
        value = preprocessString(value);
        return value.length() != 0 ? value : "0";
    }

    /**
     * <p>Returns the sum of given complex numbers.</p>
     *
     * @param a left operand.
     * @param b right operand.
     * @return sum of these numbers.
     */
    public static Complex add(Complex a, Complex b) {
        return valueOf(a.re + b.re, a.im + b.im);  // (a+bi) + (c+di) = ((a+c) + (b+d)i)
    }

    /**
     * <p>Returns the difference of given complex numbers.</p>
     *
     * @param a left operand.
     * @param b right operand.
     * @return difference of these numbers.
     */
    public static Complex subtract(Complex a, Complex b) {
        return valueOf(a.re - b.re, a.im - b.im);  // (a+bi) - (c+di) = ((a-c) + (b-d)i)
    }

    /**
     * <p>Returns the product of given complex numbers.</p>
     *
     * @param a left operand.
     * @param b right operand.
     * @return product of these numbers.
     */
    public static Complex multiply(Complex a, Complex b) {
        return valueOf(a.re * b.re - a.im * b.im, a.im * b.re + a.re * b.im);  // (a+bi) \times (c+di) = ((a \times c - b \times d) + (b \times c + a \times d)i)
    }

    /**
     * <p>Returns the quotient of given complex numbers.</p>
     *
     * @param a left operand.
     * @param b right operand.
     * @return quotient of these numbers.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static Complex divide(Complex a, Complex b) {
        double denominator = Math.pow(b.re, 2) + Math.pow(b.im, 2);
        return valueOf((a.re * b.re + a.im * b.im) / denominator, (a.im * b.re - a.re * b.im) / denominator);  // \frac{a+bi}{c+di} = \frac{(a \times c + b \times d) + (b \times c - a \times d)i}{c^2 + d^2}
    }

    /**
     * <p>Returns natural logarithm of a given complex number.</p>
     *
     * @param a operand.
     * @return natural logarithm of a given number.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static Complex log(Complex a) {
        double rho = abs(a);
        double theta = Math.atan2(a.re, a.im);
        return a.im != 0 || a.re < 0 ? valueOf(rho, theta) : valueOf(Math.log(a.re));  // \ln{(a+bi)} = (\sqrt{a^2+b^2} + \arctg{(\frac{b}{a})}i)
    }

    /**
     * <p>Returns given complex number raised to the specified complex power.</p>
     *
     * @param a left operand.
     * @param b right operand.
     * @return given number raised to the specified power.
     */
    @SuppressWarnings({"SpellCheckingInspection", "DuplicateExpressions"})
    public static Complex pow(Complex a, Complex b) {
        if(a.im != 0 || (a.re <= 0 && b.im != 0)) {
            double rho = abs(a);
            double theta = Math.atan2(a.re, a.im);
            return valueOf(Math.pow(rho, b.re) * Math.exp(-1 * b.im * theta) * Math.cos(b.im * Math.log(rho) + b.re * theta), Math.sin(b.im * Math.log(rho) + b.re * theta));  // (a+bi)^{c+di} = (((\sqrt{a^2+b^2})^c \times e^{-d \times \arctg{(\frac{b}{a})}} \times \cos{(d \times \ln{(\sqrt{a^2+b^2})}+c \times \arctg{(\frac{b}{a})})}) + (\sin{(d \times \ln{(\sqrt{a^2+b^2})}+c \times \arctg{(\frac{b}{a})})})i) \forall z=(a+bi), w=(c+di) \colon z, w \in \mathbb{C}
        }
        return b.im != 0 ? valueOf(Math.pow(a.re, b.re) * Math.cos(b.im * Math.log(a.re)), Math.sin(b.im * Math.log(a.re))) : valueOf(Math.pow(a.re, b.re));  // a^{c+di} = ((a^c \times \cos{(d \times \ln{(a)})}) + (\sin{(d \times \ln{(a)})})i) \forall a, w==(c+di) \colon a \in \mathbb{R}^+, w \in \mathbb{C}
    }

    /**
     * <p>Returns square root of a given complex number.</p>
     *
     * @param a operand.
     * @return square root of a given number.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static Complex sqrt(Complex a) {
        int sgn = a.im < 0 ? -1 : 1;
        return a.im != 0 || a.re < 0 ? valueOf(Math.sqrt((a.re + abs(a)) / 2), sgn * Math.sqrt((-a.re + abs(a)) / 2)) : valueOf(Math.sqrt(a.re));  // \sqrt{a+bi} = \frac{(\sqrt{a+\sqrt{a^2+b^2}}) + (\sgn{(b)} \times \sqrt{-a+\sqrt{a^2+b^2}})i}{2}
    }

    /**
     * <p>Returns sine of a given complex number.</p>
     *
     * @param a operand.
     * @return sine of a given number.
     */
    public static Complex sin(Complex a) {
        return a.im != 0 ? valueOf(Math.sin(a.re) * Math.cosh(a.im), Math.cos(a.re) * Math.sinh(a.im)) : valueOf(Math.sin(a.re));  // \sin{(a+bi)} = ((\sin{(a)} \times \ch{(b)}) + (\cos{(a)} \times \sh{(b)})i)
    }

    /**
     * <p>Returns cosine of a given complex number.</p>
     *
     * @param a operand.
     * @return cosine of a given number.
     */
    public static Complex cos(Complex a) {
        return a.im != 0 ? valueOf(Math.cos(a.re) * Math.cosh(a.im), -1 * Math.sin(a.re) * Math.sinh(a.im)) : valueOf(Math.cos(a.re));  // \cos{(a+bi)} = ((\cos{(a)} \times \ch{(b)}) - (\sin{(a)} \times \sh{(b)})i)
    }

    /**
     * <p>Returns tangent of a given complex number.</p>
     *
     * @param a operand.
     * @return tangent of a given number.
     */
    @SuppressWarnings({"SpellCheckingInspection", "DuplicateExpressions"})
    public static Complex tan(Complex a) {
        double denominator = 1 + Math.pow(Math.tan(a.re), 2) * Math.pow(Math.tanh(a.im), 2);
        return a.im != 0 ? valueOf((Math.tan(a.re) - Math.tan(a.re) * Math.pow(Math.tanh(a.im), 2)) / denominator, (Math.tanh(a.im) + Math.pow(Math.tan(a.re), 2) * Math.tanh(a.im)) / denominator) : valueOf(Math.tan(a.re));  // \tg{(a+bi)} = \frac{(\tg{(a)}-\tg{(a)} \times \th^2{(b)}) + (\th{(b)}+\tg^2{(a)} \times \th{(b)})i}{1 + \tg^2{(a)} \times \th^2{(b)}}
    }

    /**
     * <p>Returns cotangent of a given complex number.</p>
     *
     * @param a operand.
     * @return cotangent of a given number.
     */
    @SuppressWarnings({"SpellCheckingInspection", "DuplicateExpressions"})
    public static Complex cot(Complex a) {
        double denominator = Math.pow(1.0 / Math.tan(a.re), 2) + Math.pow(1.0 / Math.tanh(a.im), 2);
        return a.im != 0 ? valueOf(((1.0 / Math.tan(a.re)) * Math.pow(1.0 / Math.tanh(a.im), 2) - (1.0 / Math.tan(a.re))) / denominator, (-1 * Math.pow(1.0 / Math.tan(a.re), 2) * (1.0 / Math.tanh(a.im)) - (1.0 / Math.tanh(a.im))) / denominator) : valueOf(1.0 / Math.tan(a.re));  // \cth{(a+bi)} = \frac{(\ctg{(a)} \times \cth^2{(b)}-\ctg{(a)}) - (\ctg^2{(a)} \times \cth{(b)}-\cth{(b)})i}{\ctg^2{(a)} + \cth^2{(b)}}
    }

    /**
     * <p>Returns the absolute value (or modulus) of a given complex number.</p>
     *
     * @param a operand.
     * @return absolute value of this number.
     */
    public static double abs(Complex a) {
        return a.im != 0 ? Math.sqrt(Math.pow(a.re, 2) + Math.pow(a.im, 2)) : Math.abs(a.re);  // |a+bi| = \sqrt{a^2+b^2}
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>byte</code> after a narrowing primitive conversion.</p>
     * @return the absolute value of this object converted to type <code>byte</code>.
     */
    public byte byteValue() {
        return (byte) abs(this);
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>short</code> after a narrowing primitive conversion.</p>
     * @return the absolute value of this object converted to type <code>short</code>.
     */
    public short shortValue() {
        return (short) abs(this);
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as an <code>int</code> after a narrowing primitive conversion.</p>
     * @return the absolute value of this object converted to type <code>int</code>.
     */
    public int intValue() {
        return (int) abs(this);
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>long</code> after a narrowing primitive conversion.</p>
     * @return the absolute value of this object converted to type <code>long</code>.
     */
    public long longValue() {
        return (long) abs(this);
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>float</code> after a narrowing primitive conversion.</p>
     * @return the absolute value of this object converted to type <code>float</code>.
     */
    public float floatValue() {
        return (float) abs(this);
    }

    /**
     * <p>Returns the value of this <code>Complex</code> as a <code>double</code> after a narrowing primitive conversion.</p>
     * @return the absolute value of this object converted to type <code>double</code>.
     */
    public double doubleValue() {
        return abs(this);
    }

    /**
     * <p>Compares this complex number with the given one.</p>
     *
     * @param complex number to compare.
     * @return <code>0</code> if the numbers are equaled, negative integer if first value is less than second, and positive integer if first value is greater than second.
     */
    public int compareTo(Complex complex) {
        return Double.compare(abs(this), abs(complex));
    }

    /**
     * <p>Returns the smallest of two complex numbers.</p>
     *
     * @param a the first operand.
     * @param b the second operand.
     * @return the smallest of <code>a</code> and <code>b</code>.
     */
    public static Complex min(Complex a, Complex b) {
        return a.compareTo(b) <= 0 ? a : b;
    }

    /**
     * <p>Returns the greatest of two complex numbers.</p>
     *
     * @param a the first operand.
     * @param b the second operand.
     * @return the greatest of <code>a</code> and <code>b</code>.
     */
    public static Complex max(Complex a, Complex b) {
        return a.compareTo(b) <= 0 ? b : a;
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
