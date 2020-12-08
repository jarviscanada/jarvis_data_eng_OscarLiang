# Introduction
This Java app is written to be a simple version of the Linux command grep, which is used to search for string patterns in a file. The purpose of creating this app was to become familiar with the Java development environment and features, such as the use of IntelliJ IDE, JVM, and useful Java libraries for I/O and collections.

# Usage
`JavaGrep regex rootPath outFile`

e.g. `JavaGrep .*Romeo.*Juliet.* /path/to/file/shakespeare.txt ./out.txt`

# Pseudocode
Pseudocode of top-level function `process()`
```
matchedLines <- []
files <- listFiles(pathToDir)
for each file in files
	lines <- readLines(file)
    for each line in lines
    	if containsPattern(line)
        	matchedLines.add(line)
        end
    end
end
writeToFile(matchedLines)
```

# Performance Issues
The grep app stores all files from the directory into its memory; for large files or a large number of files, this can potentially cause an overflow in the heap memory, especially if we wish to run the program using lower memory to begin with.

# Improvements
- Improve memory usage by replacing the List of files with something else.
- It is unintuitive to include `.*` at the beginning and end of the regex string, so the code should automatically add that.
- Create more comprehensive usage documentation.
