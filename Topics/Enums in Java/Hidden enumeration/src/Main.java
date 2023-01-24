public class Main {

    private static boolean startsWith(String s, String regex) {
        // return s.matches(regex);
        return s.startsWith(regex);
    }


    public static void main(String[] args) {
        int counter = 0;

        // write your code here
        enum Secret {
            STAR, CRASH, START
        };

        for (Secret secret : Secret.values()) {
            counter += startsWith(secret.name(), "STAR") ? 1 : 0;
        }


        System.out.println(counter);
    }
}

/* sample enum for inspiration
*/
