// JLambda
// For use with "An Introduction To Functional Programming Through Lambda Calculus"
// by Greg Michaelson.

// λ
// <expression> ::= <name> | <body> | <application>
// <function> ::= λ<name>.<body>
// The name is the bound variable of the function.
// <body> ::= <expression>
// <application> ::= (<function expression> <argument expression>)
// <function expression> ::= <expression>
// <argument expression> ::= <expression>

// Normal order: replace occurances of name in the body with the unevaluated
// argument and then evaluate the result.
// Applicative order: replace occurance of name in the body with the evaluated
// argument (the value) and the evaluate the result.


import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.regex.*;

class LambdaInterpreter {
    ArrayList<LambdaDefinition> definitions;

    LambdaInterpreter() {
        this.definitions = new ArrayList<LambdaDefinition>();
    }

    public String evaluate(String line) {
        Pattern labelPattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*");
        String result = new String();
        String label = new String();
        int cursor = 0;

        // See if it is a definition.
        if(line.substring(0, 3).equals("def ")) {
            cursor += 4;
            // Get the label.
            Matcher labelMatcher = labelPattern.matcher(line);
            if(labelMatcher.find()) {
                label = labelMatcher.group();
            } else {
                // There is an error.
                return "Error - unable to parse label in definition.\n"+line;
            }
            // Consume the '='.
            // Get the function definition.

        }
        //
        return result;
    }
}

class LambdaDefinition implements Comparable<LambdaDefinition> {
    String label;

    LambdaDefinition() {
        this.label = new String();
    }

    // This allows searching a list.
    @Override
    public int compareTo(LambdaDefinition other) {
        return this.label.compareTo(other.label);
    }

    // This converts a definition to string directly for the sake of printing, etc.
    @Override
    public String toString() {
        String result = new String();

        return result;
    }
}


public class jLambda implements Comparable{

    public static void main(String[] args) {
        LambdaInterpreter interpreter = new LambdaInterpreter();
        String readLine = new String();
        String result = new String();
        Scanner keyboardInput = new Scanner(System.in);

        // Load definition files.
        init(args, interpreter);

        // Loop.
        while(!result.equals("Exiting.")) {
            // Read.
            readLine = keyboardInput.nextLine;

            // Evaluate.
            result = interpreter.evaluate(readLine, definitions);

            // Print.
            System.out.println(result);
        }
    }

    private static void init(String[] args, LambdaInterpreter interpreter) {
        // Load file(s).
        if(args.length > 0) {
            if(args[0].charAt(0) == '+') {
                // Load default file and another.
                try {
                    loadFile("default.lambda", interpreter);
                    loadFile(args[0].substring(1), interpreter);
                } catch(Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            } else {
                // Load only the alternative file.
                try {
                    loadFile(args[0].substring(1), interpreter);
                } catch(Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
         } else {
                // Load only the alternative file.
                try {
                    loadFile(args[0].substring(1), interpreter);
                } catch(Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
         }
    }


    public static void loadFile(String fileName, LambdaInterpreter interpreter) throws Exception {
        try(File diskFile = new File(fileName);) {
            if(!diskFile.exists()) {
                System.out.prinlnt("File "+fileName+" was not found and could not be loaded.");
                return;
            }

            Scanner fileScanner = new Scanner(diskFile);
            String currentLine = new String();

            while(fileScanner.hasNextLine()) {
                // Get a new line from the file.
                currentLine = fileScanner.nextLine();
                currentLine.trim(); // Depad.

                // Check to see if it is a comment or blank line.
                if(currentLine.charAt(0) == '#' || currentLine.isEmpty())
                    continue;

                // Otherwise, add the definition.
                interpreter.evaluate(currentLine, definitions);
            }
        }
    }
}
