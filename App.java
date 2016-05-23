import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
       System.out.println("app starting...");
       Scanner s = new Scanner (System.in);

        ArrayList<Question> ListOfQuestions = new ArrayList<>();
        ArrayList <Node> Characters = new ArrayList<>();
       
        try{
            ListOfQuestions = FileToQuestions.convertFileToQuestions();
            Characters = FileToCharacters.convertFileToCharacters();
        }catch(IOException e){
            System.out.println("Error. File not found.");
        }

        boolean found = false;
        ArrayList<String> yeses = new ArrayList<>();
        ArrayList<String> noes = new ArrayList<>();
        yeses.add("P");

        int ctr = 0;
        while(!found){ //while the person is not yet found

            while(!(Characters.isEmpty())){
                Random rand = new Random();
                int n = rand.nextInt(Characters.size()) + 0;
                Node guess = Characters.get(n);
               
                boolean y = Collections.disjoint(guess.codes,yeses); //returns false if there are same elements between the two lists
                boolean x = Collections.disjoint(guess.codes,noes); //returns true if there are no same elements between the two lists
                
                if (y == false && x == true){
                    for (int i = 1; i < guess.codes.size(); i++) {
                        int k = i;
                        String questionIdx = "";
                        int j = 0;
                        boolean questionFound = false;
                            while(!questionFound && k < guess.codes.size()){
                                questionIdx = (guess.codes).get(k);
                              
                                    for (j = 0; j < ListOfQuestions.size(); j++) {
                                        if (questionIdx.equals(ListOfQuestions.get(j).index)){
                                            System.out.println(ListOfQuestions.get(j).q);
                                            ListOfQuestions.remove(j);
                                            questionFound = true;
                                            break;
                                        }
                                    } 
                                k++;
                            }
                            System.out.print("\nEnter Answer: ");
                                String answer = s.next();
                                if (answer.equals("yes")){
                                    yeses.add(questionIdx);
                                }
                                else if (answer.equals("no")){
                                    noes.add(questionIdx);
                                    Characters.remove(n);
                                    break;
                                }
                                else if (answer.equals("idk")){

                                }
                                else{
                                    System.out.println("Enter again");
                                    String answer = s.next();
                                }
                            
                            if(k >= guess.codes.size()){
                                found = true;
                                System.out.println("\n1. YOU ARE THINKING OF: " + guess.name);
                                Characters.clear();
                                break;
                            }
                    }
                    if (noes.isEmpty()){
                        found = true;
                        System.out.println("\n2. YOU ARE THINKING OF: " + guess.name);
                        Characters.clear();
                        break;
                    }  
                }
                else if(y == false && x == false) {
                    Characters.remove(n);
                }
            }
        }
    }
}