package com.perelman.numbers.talker.parts;

import com.perelman.numbers.talker.Decl;
import com.perelman.numbers.talker.Genus;

import static com.perelman.numbers.talker.Decl.I;
import static com.perelman.numbers.talker.Genus.*;

public class OnesData {
  private String[][] data =
      new String[][] {
        {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
        {"нуля", "одного", "двух", "трех", "четырех", "пяти", "шести", "семи", "восьми", "девяти"},
        {"нулю", "одному", "двум", "трем", "четырем"},
        {"нулем","одним","двумя","тремя","четырьмя","пятью",
"шестью","семью","восемью","девятью"},
        {"нуле", "одном"}
      };
  private String[][] data2 =
      new String[][] {
        {"одна", "одно", "две"},
        {"одной"}
      };

  public String get(Decl d, Genus g, int count) {
    if (g == SHE) {
      if (count == 1) {
        if (d == I) return data2[0][0];
        else return data2[1][0];
      } else if (count == 2 && d == I)
        return data2[0][2];
    } else if (g == IT && count == 1 && d == I) {
      return data2[0][1];
    }
    switch(d){
      case I:
        return data[0][count];
      case RO:
        return data[1][count];
      case D:
        if(count < 5)
          return data[2][count];
        else
          return data[1][count];
      case T:
        if(count < 5)
          return data[3][count];
        else
          return data[0][count] + "ю";
      case P:
        if(count < 2)
          return data[4][count];
        else 
          return data[1][count];
      default:
        return "no";
    }
  }
}
