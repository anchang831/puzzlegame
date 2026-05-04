package Util;

import java.util.ArrayList;
import java.util.Random;

public class CodeUtil {

    public static String getCode() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i=0;i<26;i++) {
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }

        StringBuilder result = new StringBuilder();
        Random r = new Random();
        for (int i=0;i<4;i++) {
            int randomIndex = r.nextInt(list.size());
            char c = list.get(randomIndex);
            result.append(c);
        }

        int number = r.nextInt(10);
        result.append(number);

        char[] chars = result.toString().toCharArray();
        int index = r.nextInt(chars.length);
        char temp = chars[4];
        chars[4] = chars[index];
        chars[index] = temp;

        return new String(chars);

    }
}
