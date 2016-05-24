import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileToCharacters {
    public static ArrayList<Node> convertFileToCharacters() throws FileNotFoundException, IOException{
        ArrayList<Node> characters = new ArrayList<>();
        File file = new File ("characters.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        for(String line; (line = br.readLine()) != null;){
            String[] s = line.split(" = ");
            String name = s[0];
            ArrayList<String> codes = new ArrayList<>();
            for(int i = 1; i+1<s.length; i++){
                codes.add(s[i]);     
            }
            Node character = new Node(name, codes);  
            characters.add(character);
        }
        
        // int i = 0;
        // while (i < characters.size()){
        //     System.out.println("Name: " + characters.get(i).name);
        //     System.out.print("Codes: ");
        //     for (int j = 0; j < characters.get(i).codes.size(); j++) {
        //         System.out.print(characters.get(i).codes.get(j) + " ");
        //     }
        //     i++;
        //     System.out.println("\n");
        // }
        
        return characters;
    }
    // public static void main(String[] args) throws IOException {
    //     convertFileToCharacters();
    // }
}
