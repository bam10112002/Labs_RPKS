package Lab_1;

import java.util.*;
import Lab_interface.Lab_I;

public class Lab_1 implements Lab_I{
    @Override
    public void Run()
    {
        Map<Character,Token> dict = setTokenDict();
        String input = "(dfg)dfg(dg(df)(_)ddd)";
        Stack<Token> st = new Stack<Token>();

        for (int i = 0; i < input.length(); i++)
        {
            Token token = dict.get(input.charAt(i));
            if (token != null)
            {
                if (token.type == Token.Type.OPEN) {
                    st.add(token);
                }
                else
                {
                    if (st.empty())
                    {
                        System.out.println("ERROR");
                        return;
                    }
                    Token topToken = st.pop();
                    if (topToken.conjugate.ch != token.ch)
                    {
                        System.out.println("ERROR");
                        return;
                    }
                }
            }
        }
        if (st.empty())
            System.out.println("OK");
        else
            System.out.println("ERROR");
    }
    public Map<Character,Token> setTokenDict()
    {
        Map<Character,Token> dict = new HashMap<Character, Token>();
        dict.put(')' ,new Token(Token.Type.CLOSE, ')', null));
        dict.put('(' ,new Token(Token.Type.OPEN, '(', dict.get(')')));
        return dict;
    }

}
