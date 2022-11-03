package org.example.lab1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class JsonReader {
    BracketList bracketList;
    JsonReader(String path) {
        try {
            String jsonString = ReadFile(path);
            ObjectMapper mapper = new ObjectMapper();
            bracketList = mapper.readValue(jsonString, BracketList.class);
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    private @Nullable String ReadFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder stringBuilder = new StringBuilder();
        String s;
        while((s=br.readLine())!=null){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}

class BracketList {
    public ArrayList<Bracket> brackets;
    BracketList() {
        brackets = new ArrayList<>();
    }

    public TreeMap<String, Bracket> GetOpenBrackets() {
        TreeMap<String, Bracket> bracketsTree = new TreeMap<>();
        for (Bracket br : brackets) {
            bracketsTree.put(br.open, br);
        }
        return bracketsTree;
    }
    public TreeMap<String, Bracket> GetCloseBrackets() {
        TreeMap<String, Bracket> bracketsTree = new TreeMap<>();
        for (Bracket br : brackets) {
            bracketsTree.put(br.close, br);
        }
        return bracketsTree;
    }
}

class Bracket {
    enum Type {OPEN, CLOSE, EQUALS, NONE}
    public String open;
    public String close;

    public Type GetType(@NonNull String str) {
        if (str.equals(open)) {
            if (open.equals(close))
                return Type.EQUALS;
            else
                return Type.OPEN;
        }
        if (str.equals(close)) {
            return Type.CLOSE;
        }
        return Type.NONE;
    }
}