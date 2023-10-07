import java.lang.Math;
public class NumericalMethods {
    public static void main(String[] args){
        double t0 = 0.0;
        double y0 = 1.0;
        double tf = 1.0;
        double h = 0.1;
        euler(t0, tf, h, y0);
        //System.out.println("\n");
        improvedEuler(t0, tf, h, y0);
        rungeKutta(t0, tf, h, y0);
    }

    static void euler(double currT, double tf, double h, double currY){
        if(currT <= tf){
            System.out.println("t = " + round(currT, 3) + ":\t y = " + round(currY, 5));
            double f_n = function(currT, currY);
            double y_n = currY + (f_n * h);
            currT+=h;
            euler(currT, tf, h, y_n);
        }
    }

    static void improvedEuler(double currT, double tf, double h, double currY){
        if(currT <= tf+h){
            System.out.println("t = " + round(currT, 3) + ":\t y = " + round(currY, 5));
            double f_n = function(currT, currY);
            double f_n2 = function(currT+h, currY+(h*f_n));
            double y_n = currY + (h/2.0) * (f_n + f_n2);
            currT+=h;
            improvedEuler(currT, tf, h, y_n);
        }
    }

    static void rungeKutta(double currT, double tf, double h, double currY){
        if(currT <= tf+h){
            System.out.println("t = " + round(currT, 3) + ":\t y = " + round(currY, 5));
            double f_n = function(currT, currY);
            double f_n2 = function(currT+0.5*h, currY+0.5*h*f_n);
            double f_n3 = function(currT+0.5*h, currY+0.5*h*f_n2);
            double f_n4 = function(currT+h, currY+h*f_n3);
            double y_n = currY + (h/6.0) * (f_n + 2*f_n2 + 2*f_n3 + f_n4);
            currT+=h;
            rungeKutta(currT, tf, h, y_n);
        }
    }
    static double function(double t, double y){
        return 2*y-3*t;
    }

    static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


}