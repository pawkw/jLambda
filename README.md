# jLambda

A lambda interpreter for use with "An Introduction To Functional Programming Through Lambda Calculus" by Greg Michaelson.

## Command line arguments

The default definitions file, `default.lambda`, is loaded when jLambda is started without any arguments. Adding a filename to the invocation will load that definition file in lieu of the default file. To load the default file and another program, add a '+' to the beginning of the filename.

Ex.:
- `java jLambda`
- `java jLambda myfile.lambda`
- `java jLambda +myfile.lambda`

In a definition file, the # character is the beginning of a comment that continues to the end of the line.

In addition to λ, you can use @ or the word lambda to designate a lambda expression.
