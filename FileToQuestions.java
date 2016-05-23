import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileToQuestions {
    public static ArrayList<Question> convertFileToQuestions() throws FileNotFoundException, IOException{
        ArrayList<Question> listOfQuestions = new ArrayList<>();
        File file = new File ("questions.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        for(String line; (line = br.readLine()) != null;){
            String[] s = line.split(" = ");
            String idx = s[0];
            String qname = s[1];
            
            Question a = new Question(idx, qname);
            listOfQuestions.add(a);
        }
        
        // int l = 0;
        //     while(l < listOfQuestions.size()){
        //         System.out.println("index: " + listOfQuestions.get(l).index + "\n" +
        //                            "question: " + listOfQuestions.get(l).q + "\n");
        //         l++;
        //     }
        return listOfQuestions;
    } 
    public static void main(String[] args) throws IOException {
        convertFileToQuestions();
    }
}
