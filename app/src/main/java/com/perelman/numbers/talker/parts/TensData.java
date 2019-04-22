package com.perelman.numbers.talker.parts;

import com.perelman.numbers.talker.Decl;
import com.perelman.numbers.talker.Genus;

import static com.perelman.numbers.talker.Decl.I;
import static com.perelman.numbers.talker.Genus.*;

public class TensData {
  private OnesData od;

  public TensData(OnesData od) {
    this.od = od;
  }

  private String[][] data =
      new String[][] {
        {"десять", "двадцать", "тридцать", "сорок", "девяносто"},
        {"десяти", "двадцати", "тридцати", "сорока", "девяноста"},
        {"десятью", "двадцатью", "тридцатью", "сорока", "девяносто"}
      };
  private String[] data2 = new String[] {"надцать", "надцати", "надцатью"};

  public String getTwenty(Decl d, int count) {
    String prefix;
    switch (count) {
      case 2:
        prefix = od.get(I, SHE, 2);
        break;
      case 1:
      case 3:
        prefix = od.get(I, HE, count);
        break;
      default:
        prefix = od.get(I, HE, count);
        prefix = prefix.substring(0, prefix.length() - 1);
    }
    String suff;
    switch (d) {
      case I:
        suff = data2[0];
        break;
      case T:
        suff = data2[2];
        break;
      default:
        suff = data2[1];
    }
    return prefix + suff;
  }

  public String get(Decl d, Genus g, int count) {
    int t = count / 10;
    int o = count % 10;
    if (t == 1 && o != 0) return getTwenty(d, count - 10);
    if (t == 0) {
      return od.get(d, g, o);
    } else if (o == 0) {
      return getTen(d, t);
    }
    String ten = getTen(d, t);
    String one = od.get(d, g, o);
    return ten + " " + one;
  }

  private String getTen(Decl d, int count) {
    int decl;
    switch (d) {
      case I:
        decl = 0;
        break;
      case T:
        decl = 2;
        break;
      default:
        decl = 1;
    }
    switch (count) {
      case 5:
      case 6:
      case 7:
      case 8:
        String prefix = od.get(d, HE, count);
        String suff;
        if (d == I) {
          suff = data[0][0].substring(0, data[0][0].length() - 1);
        } else {
          suff = data[decl][0];
        }
        return prefix + suff;
      case 9:
        return data[decl][4];
      default:
        return data[decl][count - 1];
    }
  }
}
