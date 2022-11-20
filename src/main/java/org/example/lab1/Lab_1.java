package org.example.lab1;


import org.example.labI.Lab_Interface;
import java.util.*;

public class Lab_1 implements Lab_Interface {
    @Override
    public void Run()
    {
        BracketList bracketList = SetBracketsList("src/main/resources/Lab_1/tokens.json"); // try catch
        TreeMap<String, Bracket> openBrackets = bracketList.GetOpenBrackets(); // NullPointerException
        TreeMap<String, Bracket> closeBrackets = bracketList.GetCloseBrackets(); // TreeMap -> HashMap

        String input = "(dfg)dfg(dg)(df)_|ddd)|";
        Stack<Bracket> openBracketStack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            String charI = ((Character)input.charAt(i)).toString(); // переделать на char
            Bracket closeBracket = closeBrackets.get(charI);
            Bracket openBracket = openBrackets.get(charI);
            if (closeBracket != null) { // Optional
                if (openBracketStack.peek().open.equals(closeBracket.open)) {
                    openBracketStack.pop();
                }
                else if (openBracket != null) {
                    openBracketStack.push(closeBracket);
                }
                else {
                    System.out.println("ERROR in char: " + charI + ", expected: " + openBracketStack.peek().close);
                    return;
                }
            }
            else if (openBracket != null) {
                openBracketStack.push(openBracket);
            }
        }

        if (openBracketStack.empty()) {
            System.out.println("OK");
        }
        else {
            System.out.println("ERROR, bracket " + openBracketStack.peek().open + " is not closed");
        }
    }
    private BracketList SetBracketsList(String path) {
        JsonReader reader = new JsonReader(path);
        return reader.bracketList;
    }

}
