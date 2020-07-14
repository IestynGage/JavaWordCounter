 # JavaWord Counter
 The purpose of this project is to counts words and then output a string about the
 wordcount in the format of:
 >Word Count = 9
 >
 >Average word length 4.556
 >
 >Number of words of length 1 is 1
 >
 >Number of words of length 2 is 1 
 >
 >Number of words of length 3 is 1
 >
 >Number of words of length 4 is 2
 >
 >Number of words of length 5 is 2
 >
 >Number of words of length 7 is 1
 >
 >Number of words of length 10 is 1
 >
 >The most frequently occuring word length is 2,for word lengths of 4 & 5
 >
 
 # What Counts as a single word?
 All words, words with hyphens `-` , words with Apostrophe s `'s` , plain numbers, numbers with decimal, money and dates. Below is table to demonstrate
 few examples
String         | Counts as
------------   | -------------
Hello World    | 5 Letters 5 Letters  
Now).It		   | 3 Letters 2 letters
High-Five      | 9 letters
Goodbye.Hello  | 7 Letters 5 Letters
£10.00         | 6 Letters
10/10/2014     | 10 Letters
Tom's          | 5 Letters
Tom'           | 3 Letters
Tom's'         | 5 Letters
&              | 1 Letter

# How to get the program to work?

This is just simple single class maven project. For the program to work just give countTextFile function a file object that the program can access and it should work fine. 
For an example of this just look to the test class.

# Final Note

Comparing to other word counters such as Microsoft Word. I believe this program is more 'accurate' as it counts strings such as "Now).It" as two different words while other
program such as microsoft Office's Word counts "Now).It" as single word.

Could this program be made simpler? 
Yes however to count strings such as "end.The" or "Now).It" as two strings I am unable to make the program simpler.

Could this program process text faster?
You could possible increase the speed by some-how implementing multithreading however the program works quite quickly as it is, 
so unless your planning on counting libraries worth of books. Then it's unnessecary.