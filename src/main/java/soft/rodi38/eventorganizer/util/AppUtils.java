package soft.rodi38.eventorganizer.util;

import java.util.Random;

public class AppUtils {
    private static final String CHARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;
    private static final Random RANDOM = new Random();
    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i< LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERES.length());
            sb.append(CHARACTERES.charAt(index));
        }
        return sb.toString();
    }
}
