## Balanced Brackets
The balanced bracket problem is one where the user is tasked with check if brackets match. This requires checking if the order of brackets is correct, as well as if each opening character has a closing character.

### Examples
- [x] () is balanced
- [x] [()] is balanced
- [x] (){} is balanced
- [ ] [({)] is NOT balanced - the opening squiggly bracket has no matching close

### Approaches
Below are 4 approaches, with a brief explanation on each, the advantages and disadvantages, and what the average time was for 10 runs using that method. Testing data conists of 100,000 strings of up to 1,000 characters.

### [Standard if-else](https://github.com/mrmattkennedy/Balanced-Brackets/blob/main/src/Balancers.java#L29)
This method is the simplest to understand and the easiest to write. It first creates a Stack data structure to keep track of opening parentheses, then begins looping over each character.

For each character in the string, it checks if the character is an opening parenthesis or bracket. If it is, it is stored in the stack.

If the character is a closing parenthesis or bracket, then 2 checks occur. First, check if the stack is empty - if it is, then the brackets are not balanced. Next, if there is something in the stack, pop the top item, and check if the popped item is an opening character that matches the current closing character we are looking at.

If either checks fail, then the string is not balanced.

If every character is checked and the stack is empty after everything, then the string is balanced.

Some advantages of this approach are simplicity in understanding, reading, and writing it, as well as speed. For just a few cases with the brackets, these if statements are fairly quick. 

A strong disadvantage of this approach, however, is expandability. If we ever want to try and balance other characters in the future, we have to manually add in checks for those characters and make sure everything works correctly. This code is not easily abstractable.


**Average timing of 10 runs**: 685ms


### [HashMap](https://github.com/mrmattkennedy/Balanced-Brackets/blob/main/src/Balancers.java#L82)
This method is significantly easier to abstract, and still fairly simple to read, write, and understand. Instead of using a check for each type of bracket, we just create a static hash map at the top of the class.

To check for opening and closing characters, we can check our sets of keys and values indepdendently. If an opening character is found, it is added to the stack, and if a closing character is found, we can check the mapping to see if the top item on the stack matches the mapping.

This approach is extremely easy to expand using this mapping, we don't have to modify the balancing method if we want to add other characters, just the hash map.

However, at a smaller number of key/value pairs, hash maps are actually out performed by simple if-else checks. The hash map has to fetch hashcode, caluclate distance and position, fetch the unnderlying data and compare items. At a larger volume of items to check, the hash map may likely be faster, but in this context, it is not.


**Average timing of 10 runs**: 1826ms


### [HashMap Optimized](https://github.com/mrmattkennedy/Balanced-Brackets/blob/main/src/Balancers.java#L125)
Here, we are still using a hash map in roughly the same fashion as the prior method, but with some improvements. This method showcases how ordering of if-else statements and logic can make a large difference.

In this method, we again start by going over each character and looking for opening characters. However, instead of then adding opening characters to the stack, then checking if we found a closing character. instead we use only one other else-if - we first check if the stack is empty, and if it is not, we check if the item on the stack matches the current character.

This approach removes several if statements from the prior method, as well as removing a "contains" call which can take time to check for contents.

Although this method is 2x as fast as the prior hash map approach, it is still not quite as fast as the simple if-else statements.


**Average timing of 10 runs**: 963ms


### [If-else, no stack](https://github.com/mrmattkennedy/Balanced-Brackets/blob/main/src/Balancers.java#L155)
Lastly, we have the fastest method, and likely the hardest to read, write, and understand. Instead of using a stack and a hash map, here we use a basic array with an integer to point to the end of the currently-used elements. This array pointer is the variable `currentEndIdx`.

As we loop over each character, if an opening is found, we add it to the array and increment the array pointer by 1. If it wasn't an opening character, we check if the array pointer is 0, and return false if it is.

If it wasn't an opening character, and the array pointer is not 0, then we check if it is a matching close character. We get the most recent item stored in the array, and check if the character matches as a closer. If it does not match, the string is not balanced.

The purpose of choosing an array instead of a stack is likely due to the thread-safety of a stack - because stacks are thread-safe, insertions are slightly slower, even though time complexity for an insertion in a stack is O(1). Arrays are not as inherently thread-safe, so they are faster.

Although this method is the fastest, it sacrifices quite a bit in terms of future usage with it's difficulty to modify.


**Average timing of 10 runs**: 477ms
