import java.util.*;

public class Soylution {

    public static void main(String[] args) {
        ArrayList<Values> poly = new ArrayList<Values>();

        boolean done = false;
        Scanner scan = new Scanner(System.in);

        while(!done) {
            try {
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
            catch(NumberFormatException nfe) {
                System.err.println("You inputed your stuff wrong. Plz " + 
                    "input your last value again.");
            }
        }
        System.out.println();
        System.out.println();

        Polynomial p = new Polynomial(poly);
        System.out.println("Polynomial = ");
        for(Values v : p.getValues()) {
            System.out.print(v.getCoefficient() + "x^" + v.getExponent() + " ");
        }

        System.out.println();
        
        System.out.println("Finding possible zeros:");
        ArrayList<Double> ps =findPossibleZeros(p, 1000000);
        for(Double i : ps) {
            //System.out.println("Trying x = " + i);
            Double solution = halley(p,i);
            //3.14159265358979323846...
            if(solution.isNaN() || solution.isInfinite()) {
                continue;
            }
            else {
                if(Math.abs(p.evaulate(solution)) < 0.001) { 
                    System.out.println(solution);
                }
            }
        }

    }

    public static ArrayList<Double> findPossibleZeros(Polynomial p, 
        double range) {
        ArrayList<Double> possibleSolutions = new ArrayList<Double>();
        double a = -range;
        while(a < range) {
            double sol1 = Math.abs(p.evaulate(a));
            if(sol1 <= 2) {
                possibleSolutions.add(sol1); 
            }
            a += .1;
        }
        return possibleSolutions;
    }

    public static double halley(Polynomial p, double guess) {
        Polynomial dp = p.derivative();
        Polynomial d2p = dp.derivative();
        double valP = p.evaulate(guess);
        while(Math.abs(valP) > 0.001) {
            double valDP = dp.evaulate(guess);
            double valD2P = d2p.evaulate(guess);
            guess = guess - (2*valP*valDP)/(2*valD2P*valD2P-valP*valD2P);
            valP = p.evaulate(guess);
        }
        return guess;
    }
}
