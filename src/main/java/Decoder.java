import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {

    //Compile pattern here for efficiency.  And use to check for non alphanumeric characters in the encoded string.
    private static Pattern pattern = Pattern.compile("^[A-Z0-9]*$");
    //Initialize my decoder map so I can fill it.
    private static HashMap<String, String> decoderMap = new HashMap<>();
    //Fill my decoder map with (key: morde code , value: string representation of the character) pairs.
    static{
        decoderMap.put(".-", "A");   decoderMap.put("-...", "B");  decoderMap.put("-.-.", "C");
        decoderMap.put("-..", "D");  decoderMap.put(".", "E");     decoderMap.put("..-.", "F");
        decoderMap.put("--.", "G");  decoderMap.put("....", "H");  decoderMap.put("..", "I");
        decoderMap.put(".---", "J"); decoderMap.put("-.-", "K");   decoderMap.put(".-..", "L");
        decoderMap.put("--", "M");   decoderMap.put("-.", "N");    decoderMap.put("---", "O");
        decoderMap.put(".--.", "P"); decoderMap.put("--.-", "Q");  decoderMap.put(".-.", "R");
        decoderMap.put("...", "S");  decoderMap.put("-", "T");     decoderMap.put("..-", "U");
        decoderMap.put("...-", "V"); decoderMap.put(".--", "W");   decoderMap.put("-..-", "X");
        decoderMap.put("-.--", "Y"); decoderMap.put("--..", "Z");  decoderMap.put("-----", "0");
        decoderMap.put(".----", "1");decoderMap.put("..---", "2"); decoderMap.put("...--", "3");
        decoderMap.put("....-", "4");decoderMap.put(".....", "5"); decoderMap.put("-....", "6");
        decoderMap.put("--...", "7");decoderMap.put("---..", "8"); decoderMap.put("----.", "9");
    }

    public static String decode (String encoded) {
        if (!pattern.matcher(encoded).find()){
            throw new IllegalArgumentException();
        }
        String output = "";
        String currentChar = "";
        char[] chars = encoded.toCharArray();
        char[] twoMostCommon = findTwoMostCommon(chars);
        char dit = twoMostCommon[0];
        char space = twoMostCommon[1];
        int spaceCount = 0;
        int ditCount = 0;
        int multiple = findMultiple(space, dit, chars);
        space = findSpace(chars, space, dit);
        if (space == dit){
            dit = twoMostCommon[1];
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == dit) {
                if (spaceCount == 3 * multiple){
                    if (decoderMap.get(currentChar) == null){
                        spaceCount = 0;
                        ditCount = 0;
                        char temp = space;
                        space = dit;
                        dit = temp;
                        i = -1;
                        output = "";
                        currentChar = "";
                        continue;
                    }
                    output += decoderMap.get(currentChar);
                    currentChar = "";
                }
                if (spaceCount == 7 * multiple){
                    if (decoderMap.get(currentChar) == null){
                        spaceCount = 0;
                        ditCount = 0;
                        char temp = space;
                        space = dit;
                        dit = temp;
                        i = -1;
                        output = "";
                        currentChar = "";
                        continue;
                    }
                    output += decoderMap.get(currentChar);
                    currentChar = "";
                    output += " ";
                }
                spaceCount = 0;
                ditCount++;
            }
            else if (chars[i] == space){
                if (ditCount == multiple && spaceCount < multiple){
                    currentChar += ".";
                }
                else if (ditCount == 3 * multiple && spaceCount < multiple) {
                    currentChar += "-";
                }
                ditCount = 0;
                spaceCount++;
            }
        }
        if (ditCount == multiple){
            currentChar += ".";
        }
        else if (ditCount == 3 * multiple){
            currentChar += "-";
        }
        output += decoderMap.get(currentChar);
        return output;
    }

    private static char findSpace(char[] chars, char space, char dit) {
        int spaceCount = 0;
        int ditCount = 0;
        int max = 0;
        char maxC = ' ';
        for(char c : chars){
            if (c == space){
                spaceCount++;
                if (ditCount >= max){
                    maxC = dit;
                    max = ditCount;
                }
                ditCount = 0;
            }
            else if (c == dit){
                ditCount++;
                if (spaceCount >= max){
                    maxC = space;
                    max = spaceCount;
                }
                spaceCount = 0;
            }
        }
        if (spaceCount >= max){
            return space;
        }
        if (ditCount >= max){
            return dit;
        }
        return maxC;
    }

    private static char[] findTwoMostCommon(char[] chars) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        char[] output = new char[2];
        int max = 0;
        char maxC = ' ';
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if (entry.getValue() > max){
                maxC = entry.getKey();
                max = entry.getValue();
            }
        }
        max = 0;
        output[0] = maxC;
        map.remove(maxC);
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if (entry.getValue() > max){
                maxC = entry.getKey();
                max = entry.getValue();
            }
        }
        output[1] = maxC;
        return output;
    }

    private static int findMultiple(char space, char dit, char[] chars) {
        int spaceCount = 0;
        int ditCount = 0;
        int min = Integer.MAX_VALUE;
        for(char c : chars){
            if (c == space){
                spaceCount++;
                if (ditCount != 0 && ditCount < min){
                    min = ditCount;
                }
                ditCount=0;
            }
            else {
                ditCount++;
                if (spaceCount != 0 && spaceCount < min){
                    min = spaceCount;
                }
                spaceCount = 0;
            }
        }
        return min;
    }
}
