## Day 1 - Notes

The challenge: https://adventofcode.com/2023/day/1

### Challenge Objective

* Parse a text file for numbers 0-9 in both digit and word format.
* Find the first and last number of each line in the file.
* Concatenate these numbers to get the calibration value.
* Sum all the calibration values to get the puzzle solution.
  
### My Approach
#### Part 1

* The first challenge was parsing the file itself as I had not yet learned how to read files. I did however know how to parse console input so I followed a similar approach, creating a file object and then using a Scanner to read the file.
* Next, I simply needed to get the digits in each line, but I did not know how to check each digit from the String. Eventually, I settled on splitting the string into a character array because all the digits would be only a single length. Once I had the array of characters, it was easy to check each character for digit values by looping through the characters, using the isDigit() method, and adding only those values that returned True to a new string. 
* Once I had all the digits as a single string, the next challenge was to get the first and last digits. In this case, I deleted every character between the first and last from the string. In retrospect, I could have probably just accessed the first and last digits directly, but this method avoided having to concatenate the strings manually.
* The final challenge was to convert the string digit to an integer so it could be added to a sum value. I use the parseInt() method to convert the digit into a usable number.

#### Part 2

* The second part of this puzzle required the parsing of numbers as words. This proved to be a bit more tricky than the first part. My approach of splitting each line into a character array would not work in this case because word numbers cover multiple characters, so I had to rethink my approach.
* As before, I parse the file by reading each line with a Scanner but I add each line to a String ArrayList instead of dealing with each line directly. This will let me manipulate each string later.
* To get the digits I create an array containing each number value I will look for in the string. We then iterate through each digit, looking for a matching substring in the line. If a match is found at the current index of the string, I add the digit to a new list. I make sure to signify the current index as the starting point each time when searching the line so that duplicate matches can be addressed, otherwise indexOf() will only find the first match.
* The resulting list of digit matches can then be parsed to get the first and last digits. This time I use a switch to check the value of the first digit using getFirst() to get the first entry in the list. I use a try..catch statement to try and parse the string as an integer, this lets me catch if the string is a number word and use the switch to convert the number to an int.
* I do the same for the last digit using getLast().
* I then have two integers that I need to concatenate. To do this I convert both values into strings and then concatenate them, parsing the result back into an int.
* The final challenge is to get the sum of these values. For this, I iterate through each line calling the methods to get the numbers and sum the first and last digits on the line. I then add the returned value to a new variable sum. When all lines have been iterated through, the cumulative sum should be the puzzle solution.

### What did I learn?

* Parsing the file at the beginning of the puzzle into a usable format is very important. Trying to parse the file whilst also performing changes gets very confusing, very quickly.
* Splitting each operation into separate methods helps to make it easier to debug when trying to figure out why the code isn't working.
* Reading how a method works is pretty important, I spent a while wondering why my indexOf() method was not returning all the digits until I realized by default it only returns the first match. 
