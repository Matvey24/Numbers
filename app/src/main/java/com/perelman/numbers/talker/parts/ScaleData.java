package com.perelman.numbers.talker.parts;

import com.perelman.numbers.talker.Decl;
import com.perelman.numbers.talker.Genus;

import static com.perelman.numbers.talker.Decl.I;
import static com.perelman.numbers.talker.Genus.HE;
import static com.perelman.numbers.talker.Genus.SHE;

public class ScaleData {
  private HundredData hd;

  public ScaleData(HundredData hd) {
    this.hd = hd;
  }

  private String[] scales = new String[] {
        "миллион","миллиард","триллион",
        "квадриллион","квинтиллион","секстиллион",
        "септиллион","октиллион","нониллион",
        "дециллион","эндекалион","додекаллион"
      };

  private String[][] data = new String[][] {
        {"", "а", "ов"},
        {"а", "ов"},
        {"у", "ам"},
        {"ом", "ами"},
        {"е", "ах"}};
  private String thous = "тысяч";
  private String[][] data2 = new String[][] {
        {"а", "и", ""},
        {"и", ""},
        {"е", "ам"},
        {"ей", "ами"},
        {"е", "ах"}};

  public String get(Decl d, Genus g, int count, int scale) {
    if (count == 0) return "";
    if (scale == 0) return hd.get(d, g, count);
    int th = count % 100;

    int decl = d.ordinal();
    int idx;
    idx:{
      
      if (d == I) {
        if(th > 10 && th < 20){
          idx = 2;
          break idx;
        }
        th %= 10;
        switch (th) {
          case 1:
            idx = 0;
            break;
          case 2:
          case 3:
          case 4:
            idx = 1;
            break;
          default:
            idx = 2;
        }
      } else {
        if(th > 10 && th < 20){
          idx = 1;
          break idx;
        }
        th %= 10;
        if (th == 1) idx = 0;
        else idx = 1;
      }
    }
    if (scale == 1) {
      String suff = thous + data2[decl][idx];
      return hd.get(d, SHE, count) + " " + suff;
    } else {
      String suff = scales[scale - 2] + data[decl][idx];
      return hd.get(d, HE, count) + " " + suff;
    }
  }
}
