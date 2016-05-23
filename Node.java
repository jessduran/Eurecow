import java.util.*;

public class Node {
    public String name;
    public ArrayList <String> codes;
    
    Node() {
        name = null;
        codes = new ArrayList<>();

    }
    
    Node(String name, ArrayList <String> codes) {
        this.name = name;
        this.codes = codes;
    }
    
    Node(Node n) {
        this.name = name;    
        for (String s : n.codes) {
            codes.add(s);
        }
    }
    
    public void addCode (String code){
        if (!(codes.contains(code))){
            codes.add(code);
        }
    }
    
    public void removeCode (String code){
        if (code.contains(code)){
            codes.remove(code);
        }
    }
}

