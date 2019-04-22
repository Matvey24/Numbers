package com.perelman.numbers.talker.parts;


import com.perelman.numbers.talker.Decl;
import com.perelman.numbers.talker.Genus;

import static com.perelman.numbers.talker.Decl.I;
import static com.perelman.numbers.talker.Genus.HE;

public class HundredData {
  private TensData td;
  private OnesData od;

  public HundredData(TensData td, OnesData od) {
    this.td = td;
    this.od = od;
  }

  private String[][] data =
      new String[][] {
        {"сто", "двести", "триста", "четыреста"},
        {"сот", "ста"},
        {"стам"},
        {"стами"},
        {"стах"}
      };

  public String get(Decl d, Genus g, int count) {
    int h = count / 100;
    int t = count % 100;
    if (h == 0) return td.get(d, g, t);
    else if (t == 0) return getHundred(d, h);
    String pref = getHundred(d, h);
    String suff = td.get(d, g, t);
    return pref + " " + suff;
  }

  private String getHundred(Decl d, int count) {
    if (count == 1) {
      if (d == I) {
        return data[0][0];
      } else return data[1][1];
    }
    if (d == I) {
      switch (count) {
        case 2:
        case 3:
        case 4:
          return data[0][count - 1];
        default:
          String prefix = od.get(d, HE, count);
          String suff = data[1][0];
          return prefix + suff;
      }
    }
    String prefix = od.get(d, HE, count);
    String suff = data[d.ordinal()][0];
    return prefix + suff;
  }
}
