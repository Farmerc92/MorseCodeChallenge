import java.util.HashMap;
import java.util.HashSet;
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
        char dit = chars[0];
        char dah = findDah(dit, chars);
        int dahCount = 0;
        int ditCount = 0;
        int multiple = findMultiple(dah, dit, chars);
        for (char c : chars) {
            if (c == dit) {
                if (dahCount == 3 * multiple){
                    output += decoderMap.get(currentChar);
                    currentChar = "";
                }
                if (dahCount == 7 * multiple){
                    output += decoderMap.get(currentChar);
                    currentChar = "";
                    output += " ";
                }
                dahCount = 0;
                ditCount++;
            }
            else if (c == dah){
                if (ditCount == multiple && dahCount < multiple){
                    currentChar += ".";
                }
                else if (ditCount == 3 * multiple && dahCount < multiple) {
                    currentChar += "-";
                }
                ditCount = 0;
                dahCount++;
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

    private static int findMultiple(char dah, char dit, char[] chars) {
        int dahCount = 0;
        int ditCount = 0;
        int min = Integer.MAX_VALUE;
        for(char c : chars){
            if (c == dah){
                dahCount++;
                if (ditCount != 0 && ditCount < min){
                    min = ditCount;
                }
                ditCount=0;
            }
            else {
                ditCount++;
                if (dahCount != 0 && dahCount < min){
                    min = dahCount;
                }
                dahCount = 0;
            }
        }
        return min;
    }

    public static char findDah(char dit, char[] chars){
        char dah = ' ';
        for (char c : chars) {
            if (c != dit) {
                dah = c;
                break;
            }
        }
        return dah;
    }
}
