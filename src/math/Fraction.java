package math;

import java.util.Random;

public class Fraction {

    public int n;
    public int d;
    static Random random = new Random();

    public Fraction(int n, int d) {
        this.n = n;
        this.d = d;
    }

    public static Fraction simplify(int n, int d) {

        int gcd = gcd(n, d);
        n /= gcd;
        d /= gcd;
        return new Fraction(n, d);
    }

    private static Fraction generate(int limit, boolean properFraction){
        int n = random.nextInt(limit - 1) + 2;
        if(properFraction)limit = n;
//        else limit = 2 * limit;
        int d = random.nextInt(limit) + 1;
        return simplify(n, d);
    }

    private static Fraction add(Fraction a, Fraction b){
        int n = a.n * b.d + b.n * a.d;
        int d = a.d * b.d;
        return simplify(n, d);
    }

    private static Fraction minus(Fraction a, Fraction b){
        int n = a.n * b.d - b.n * a.d;
        int d = a.d * b.d;
        return simplify(n, d);
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
