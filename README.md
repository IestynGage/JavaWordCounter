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
High-Five      | 9 letters
Goodbye.Hello  | 7 Letters 5 Letters
£10.00         | 6 Letters
10/10/2014     | 10 Letters
Tom's          | 5 Letters
Tom'           | 3 Letters
Tom's'         | 5 Letters
&              | 1 Letter

# How to get program to work?

This is just simple single class maven project. For the program to work just give countTextFile function a file object that the program can access and it should work fine. For an example of this just look to the test class.
