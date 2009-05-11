import java.util.ArrayList;
import java.util.Scanner;

public class Polynomial {
    private ArrayList<Values> poly; 

    public Polynomial(ArrayList<Values> poly) {
        this.poly = poly;
    }

    public double evaulate(double x) {
        double answer = 0;
        for(Values v : poly) {
            answer += v.getCoefficient()*Math.pow(x,v.getExponent());
        }
        return answer;
    }

    public Polynomial derivative() {
        ArrayList<Values> newPoly = new ArrayList<Values>();
        ArrayList<Values> currPoly = getValues();
        for(Values v : currPoly) {
            Values newV = new Values(v.getCoefficient()*v.getExponent(), 
                v.getExponent()-1);
            newPoly.add(newV);
        }
        return new Polynomial(newPoly);
    }

    public ArrayList<Values> getValues() {
        return poly;
    }

/**
    public static void main(String[] args) throws Exception {
        ArrayList<Values> poly = new ArrayList<Values>();

        System.out.println("Enter polynomials in the following format:");
        System.out.println("Coefficient = <your input>");
        System.out.println("Exponent = <your input>");
        System.out.println("Done (y/n) = <y/n>");

        boolean done = false;
        Scanner scan = new Scanner(System.in);

        while(!done) {
            System.out.print("Coefficient = ");
            double a = Double.parseDouble(scan.nextLine());
            System.out.print("Exponent = ");
            int n = Integer.parseInt(scan.nextLine());
            System.out.print("Done (y/n) = ");
            String s = scan.nextLine().trim().toLowerCase();
            if(s.equals("y")) {
                done = true;
            }
            poly.add(new Values(a,n));
        }

        for(int i = 0; i < poly.size(); i++) {
            Values v = poly.get(i);
            double a = v.getCoefficient();
            int n = v.getExponent();
            System.out.print(a + "x^" + n + " "); 
        }
        System.out.println();
    }
    */


}
