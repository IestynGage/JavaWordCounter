import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @Class IestynWordCounter
 * @Purpose To read a text file and produce string about the word count.
 *
 *
 * @author Iestyn Gage
 * @Date 13/07/2020
 */
public class IestynWordCounter {

    private final String regex = "[a-zA-Z]*|[a-zA-Z]*'s|[a-zA-Z]+-[a-zA-Z]+|\\d+|[£|$|€]?+\\d*.?+\\d+|([0-2][0-9]|(3)[0-1])(/)(((0)[0-9])|((1)[0-2]))(/)\\d{4}|&";
    //String that holds regex format for : word of any length OR word's OR number OR Money OR Date OR &
    private final String edgeCaseRegex = "[" + regex + "]*[.|,|']";
    //String that holds regex format for : word of any length with dot
    /**
     * This function counts the words of a plain text file. Once counted it returns string about
     * the counted words such as:
     *  -Total Word Count,
     *  -number of words of each legnth,
     *  -The most fequently occuring word length and the amount of times it occured
     *
     * @param file
     * @return String about the word count of the text file
     */
    public String countTextFile(File file){
        if(file==null){
            return null;
        }

        HashMap<Integer,Integer> wordList = countWords(file);

        return outputData(wordList);
    }

    /**
     * This compares each word with the regex and edgeCaseRegex. If it matches then it adds the word to the hashMap, otherwise
     * the funcion manually counts the word. Once all words have been matched it returns HashMap.
     *
     * @param file to be counted
     * @return HashMap of : WordLength, Total Word Length Count
     */
    private HashMap<Integer,Integer> countWords(File file){
        HashMap<Integer,Integer> wordList = new HashMap<Integer, Integer>();

        try {
            Scanner sc = new Scanner(file);
            sc.useDelimiter("[,|\\n|!|\\?|\"|,|\\(|\\)|:|;|\\s|+|*|=]+");
            Pattern stringPattern = Pattern.compile(regex);
            Pattern edgeCasePattern = Pattern.compile(edgeCaseRegex);

            while (sc.hasNext()){
                String string = sc.next();

                if(stringPattern.matcher(string).matches()){
                    this.addStringToHash(string, wordList);
                } else if(edgeCasePattern.matcher(string).matches()){
                    string = string.replaceAll("\\.","");
                    this.addStringToHash(string, wordList);
                } else {
                    // DEBUG LINE: Use to see what reaches edge cases
                    // System.out.println("Edge Case Found:" + string);
                    ArrayList<String> edgeCaseWords = manualCount(string);
                    for (String word:edgeCaseWords) {
                        this.addStringToHash(word,wordList);
                    }
                }
            }
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }

        return wordList;
    }

    /**
     * This function counts the string length and add it to hash table
     *
     * @param string to be counted
     * @param wordList HashMap to add the new score to
     */
    private void addStringToHash(String string, HashMap<Integer,Integer> wordList){
        // DEBUG LINE: Use to see what classified as a word
        //System.out.println(string);
        Integer wordLength = string.length();
        if(wordLength>0 && wordList.computeIfPresent(wordLength,(key, value) -> value + 1)==null) {
            wordList.put(wordLength,1);
        }
    }

    /**
     * Manually counts amount of repeated letters & numbers, resets count when symbol is found.
     *
     * @param string to be counted
     * @return ArrayList of each seperate word found.
     */
    private ArrayList<String> manualCount(String string){
        // DEBUG LINE: Use to see what input been given to manualCount
        //System.out.println("ManualCount: " + string);
        ArrayList<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        for (Character character:string.toCharArray()) {
            if( (character.charValue()> 47 && character.charValue()<58) || character.charValue()>64 && character.charValue()<91
            || (character.charValue()>96&&character.charValue()<123) || character.charValue() == 38){
                currentWord.append(character);
            } else {
                // DEBUG LINE: Use to see what manualCount classified as a word
                //System.out.println("ManualCount: " + currentWord);
                words.add(currentWord.toString());
                currentWord = new StringBuilder();
            }
        }

        if(!currentWord.toString().isEmpty()){
            // DEBUG LINE: Use to see what manualCount classified as a word
            //System.out.println("ManualCount: " +currentWord);
            words.add(currentWord.toString());
        }

        return words;
    }

    /**
     * Reads Hashmap in format of (WordLength, Total Word Length Count) and produces string about the data with the information of:
     *  -Total Word Count,
     *  -number of words of each legnth,
     *  -The most fequently occuring word length and the amount of times it occured
     *
     * @param wordList
     * @return
     */
    private String outputData(HashMap<Integer,Integer> wordList){
        StringBuilder stringBuilder = new StringBuilder();
        Double letterCount = new Double(0);
        Integer wordCount = new Integer(0);
        Iterator<Map.Entry<Integer, Integer>> iterator = wordList.entrySet().iterator();
        Integer highestCount = 0;
        ArrayList<Integer> highestCountNumbers = new ArrayList<>();

        while(iterator.hasNext()){
            Map.Entry<Integer,Integer> wordCountTuple = iterator.next();
            letterCount = letterCount + (wordCountTuple.getKey()*wordCountTuple.getValue());
            wordCount = wordCount + wordCountTuple.getValue();

            if(highestCount<wordCountTuple.getValue()){
                highestCountNumbers = new ArrayList<>();
                highestCount = wordCountTuple.getValue();
                highestCountNumbers.add(wordCountTuple.getKey());
            } else if(highestCount.equals(wordCountTuple.getValue())){
                highestCount = wordCountTuple.getValue();
                highestCountNumbers.add(wordCountTuple.getKey());
            }

            stringBuilder.append("Number of words of length " + wordCountTuple.getKey() + " is " + wordCountTuple.getValue() + "\n");
        }

        stringBuilder.append("The most frequently occuring word length is ");

        stringBuilder.append(highestCount);
        stringBuilder.append(", for word length of ");
        if(highestCountNumbers.isEmpty()){
            stringBuilder.append("0");
        } else {
            stringBuilder.append(highestCountNumbers.get(0));
            for (int i = 1; i < highestCountNumbers.size(); i++) {
                stringBuilder.append(" & ");
                stringBuilder.append(highestCountNumbers.get(i));
            }
        }
        stringBuilder.append("\n");
        Double averageWordLength = new Double(0);

        if(wordCount!=0){
            averageWordLength = letterCount/wordCount;
        }
        DecimalFormat df = new DecimalFormat("###.###");
        stringBuilder.insert(0,"\nAverage word Length = " + df.format(averageWordLength)  + "\n");
        stringBuilder.insert(0,"Word Count = " + wordCount);

        return stringBuilder.toString();
    }
}
