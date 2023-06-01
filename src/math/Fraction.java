package math;

import java.util.Objects;
import java.util.Random;

public class Fraction {

    public int n;
    public int d;
    static Random random = new Random();

    public Fraction(int n, int d) {
        this.n = n;
        this.d = d;
    }

    public static Fraction simplify(Fraction f) {
        int gcd = gcd(f.n, f.d);
        return new Fraction(f.n/gcd, f.d/gcd);
    }

    @Override
    public boolean equals(Object o) {
        if (this.getClass() != o.getClass()) return false;
        Fraction f = (Fraction) o;
        return n == f.n && d == f.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, d);
    }

    public String toString() {
        if (d == 1) return String.valueOf(n);
        return n + "/" + d;
    }

    public static Fraction fractionGenerate(int limit, boolean properFraction){
        int d = random.nextInt(limit - 1) + 2;
        if(properFraction)limit = d;
//        else limit = 2 * limit;
        int n = random.nextInt(limit) + 1;
        return simplify(new Fraction(n, d));
    }

    public static Fraction fAdd(Fraction a, Fraction b){
        if (a.d == b.d)return new Fraction(a.n + b.n, b.d);
        int n = a.n * b.d + b.n * a.d;
        int d = a.d * b.d;
        return new Fraction(n, d);
    }

    public static Fraction fMinus(Fraction a, Fraction b){
        if (a.d == b.d)return new Fraction(a.n - b.n, b.d);
        int n = a.n * b.d - b.n * a.d;
        int d = a.d * b.d;
        return new Fraction(n, d);
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
