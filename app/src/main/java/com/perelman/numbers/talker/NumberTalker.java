package com.perelman.numbers.talker;

import com.perelman.numbers.talker.parts.HundredData;
import com.perelman.numbers.talker.parts.OnesData;
import com.perelman.numbers.talker.parts.ScaleData;
import com.perelman.numbers.talker.parts.TensData;

public class NumberTalker {
  private OnesData o;
  private TensData t;
  private HundredData h;
  private ScaleData s;
  private StringBuilder sb;
  public NumberTalker(){
    o = new OnesData();
    t = new TensData(o);
    h = new HundredData(t, o);
    s = new ScaleData(h);
    sb = new StringBuilder();
  }
  public String get(Decl d, Genus g, String count){
    
    if(count.equals("0") || count.equals("")){
      return o.get(d, g, 0);
    }
    int scales = (int)Math.ceil(count.length() / 3f);
    // определение разрядов
    int[] values = new int[scales];
    for(int i = 0; i < scales; ++i){
      int start = Math.max(count.length() - i * 3 - 3, 0);
      int end = count.length() - i * 3;
      values[i] = Integer.parseInt(count.substring(start,end));
    }
    
    for(int i = 0; i < scales - 1; ++i){
      int idx = scales - 1 - i;
      String str = s.get(d, g, values[idx], idx);
      if(!str.equals("")){
        sb.append(str);
        sb.append(" ");
      }
    }
    String str = s.get(d, g, values[0], 0);
    if(!str.equals("")){
      sb.append(str);
    }
    String text = sb.toString();
    sb.setLength(0);
    return text;
  }
}
