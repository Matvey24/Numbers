package com.perelman.numbers.calculator2.values.complex;

import static com.perelman.numbers.calculator2.values.complex.CVal.pow;

public class CMath {
  static CVal sqrt(CVal a){
    return pow(a, new CVal(1/2d));
  }
  static CVal cbrt(CVal a){
    if(a.im == 0)
      return new CVal(Math.cbrt(a.re));
    return pow(a, new CVal(1/3d));
  }
  static CVal negate(CVal a){
    return new CVal(-a.re, -a.im);
  }
  static CVal cos(CVal a){
    double mex = Math.exp(-a.im);
    double ex = Math.exp(a.im);
    double nre = (ex + mex) * Math.cos(a.re) / 2;
    double nim = (ex - mex) * Math.sin(a.re) / 2;
    return new CVal(nre, nim);
  }
  static CVal sin(CVal a){
    double mex = Math.exp(-a.im);
    double ex = Math.exp(a.im);
    double nre = (ex + mex) * Math.sin(a.re) / 2;
    double nim = (ex - mex) * Math.cos(a.re) / 2;
    return new CVal(nre, nim);
  }
}
