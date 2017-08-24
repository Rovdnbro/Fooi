package hogent.fooi.util;


public class Calculator {
    public Double[] calculateTips (Double bill/*, int percentage*/){
        Double basis  = (bill / 100) ;
        Double[]tips = {basis * 10, basis * 15, basis * 20/*, calculateFlexTip(bill, percentage)*/};
        return tips;
    }

    public double calculateFlexTip(Double bill, int percentage){
        Double basis  = (bill / 100);
        return basis * percentage;
    }
}

