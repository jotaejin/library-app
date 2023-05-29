public class Start4 {
    public static void main(String[] args) {
        int k = 5;
        int m;

        m = square(k);

        System.out.println("m : " + m);
    }

    private static int square(int k) {
        int result;

        k = k*k;

        result = k;

        return result;
    }
}
