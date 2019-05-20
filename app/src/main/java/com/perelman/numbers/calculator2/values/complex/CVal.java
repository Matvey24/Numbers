package com.perelman.numbers.calculator2.values.complex;

import androidx.annotation.Nullable;

public class CVal {
    private static final CVal ZERO = new CVal(0);
    private static final CVal TWO = new CVal(2);
    private static final CVal TEN = new CVal(10);
    private static final CVal ONE = new CVal(1);
    static final CVal I = new CVal(0, 1);
    static final CVal PI = new CVal(Math.PI);
    static final CVal E = new CVal(Math.E);
    final double re;
    final double im;

    CVal(double re, double im) {
        this.re = re;
        this.im = im;
    }

    CVal(double re) {
        this.re = re;
        this.im = 0;
    }

    static CVal sum(CVal a, CVal b) {
        return new CVal(a.re + b.re, a.im + b.im);
    }

    static CVal diff(CVal a, CVal b) {
        return new CVal(a.re - b.re, a.im - b.im);
    }

    static CVal mul(CVal a, CVal b) {
        double nre = a.re * b.re - a.im * b.im;
        double nim = a.re * b.im + a.im * b.re;
        return new CVal(nre, nim);
    }

    static CVal divide(CVal a, CVal b) {
        double v = len2d(b);
        double nre = (a.re * b.re + a.im * b.im) / v;
        double nim = (b.re * a.im - a.re * b.im) / v;
        return new CVal(nre, nim);
    }

    private static double len2d(CVal a) {
        return a.re * a.re + a.im * a.im;
    }

    private static double modD(CVal a) {
        return Math.sqrt(len2d(a));
    }

    static CVal mod(CVal a) {
        return new CVal(modD(a));
    }

    static CVal exp(CVal a) {
        double ex = Math.exp(a.re);
        double nre = ex * Math.cos(a.im);
        double nim = ex * Math.sin(a.im);
        return new CVal(nre, nim);
    }

    static CVal ln(CVal a) {
        double nre = Math.log(len2d(a)) / 2;
        double nim = Math.atan2(a.im, a.re);
        if (nim <= -Math.PI) {
            nim += 2 * Math.PI;
        }
        return new CVal(nre, nim);
    }

    static CVal pow(CVal a, CVal b) {
        if (a.equals(ZERO) || b.equals(ZERO))
            return ONE;
        return exp(mul(b, ln(a)));
    }

    static CVal log(CVal a, CVal b) {
        return divide(ln(a), ln(b));
    }

    static CVal lg(CVal a) {
        return log(a, TEN);
    }

    static CVal ld(CVal a) {
        return log(a, TWO);
    }

    @Override
    public String toString() {
        if (im == 0) {
            return dts(re);
        }
        if (re == 0) {
            if (im == 1)
                return "i";
            if (im == -1)
                return "-i";
            return dts(im) + "i";
        }
        if (im < 0) {
            if (im == -1)
                return dts(re) + " - i";
            return dts(re) + " - " + dts(-im) + "i";
        } else {
            if (im == 1)
                return dts(re) + " + i";
            return dts(re) + " + " + dts(im) + "i";
        }
    }

    private static String dts(double a) {
        if (a % 1.0 == 0.0) {
            return String.valueOf((int) (a));
        }
        return String.valueOf(a);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof CVal) {
            return this.re == ((CVal) obj).re && this.im == ((CVal) obj).im;
        }
        return false;
    }
}
