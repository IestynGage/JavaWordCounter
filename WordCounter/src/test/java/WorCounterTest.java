import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;

class WorCounterTest {

    String countLocalTextFile(String filename){
        if(filename==null){
            return null;
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        IestynWordCounter wc = new IestynWordCounter();

        return wc.countTextFile(file);
    }


    @Test
    void countWordEmptyTest(){
        String countWords = this.countLocalTextFile("Empty.txt");

        Assertions.assertTrue(countWords.startsWith("Word Count = 0"));
    }

    @Test
    void countWordNullTest(){
        String countWords = this.countLocalTextFile(null);

        Assertions.assertTrue(countWords==null);
    }

    @Test
    void countWordHelloWordTest(){
        String countWords = this.countLocalTextFile("HelloWorld.txt");

        Assertions.assertTrue(countWords.startsWith("Word Count = 2"));
    }

    @Test
    void countWordMoneyAndDateTest(){
        String countWords = this.countLocalTextFile("MoneyAndDate.txt");

        Assertions.assertTrue(countWords.startsWith("Word Count = 6"));
    }

    @Test
    void countWordLoremIspumTest(){
        String countWords = this.countLocalTextFile("LoremIspum.txt");
        Assertions.assertTrue(countWords.startsWith("Word Count = 69"));
    }

    @Test
    void countWordMessTest(){
        String countWords = this.countLocalTextFile("exampleMess.txt");
        Assertions.assertTrue(countWords.startsWith("Word Count = 5"));
    }

    @Test
    void countWordSimpleTest(){
        String countWords = this.countLocalTextFile("exampleSimple.txt");
        Assertions.assertTrue(countWords.startsWith("Word Count = 9"));
    }

    @Test
    void countWordTwoFrequentTest(){
        String countWords = this.countLocalTextFile("TwoEqualFrequentWord.txt");
        Assertions.assertTrue(countWords.startsWith("Word Count = 8"));
    }

    @Test
    void countWordBibleTest(){
        String countWords = this.countLocalTextFile("exampleBible.txt");
        System.out.println(countWords);
    }

}
