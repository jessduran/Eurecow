import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class eurecow{
		static ArrayList <Question> ListOfQuestions = new ArrayList<>();
        static ArrayList <Node> Characters = new ArrayList<>();
        static ArrayList <String> yesCodes = new ArrayList<>();
        static ArrayList <String> noCodes = new ArrayList<>();
        static int n, x;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

        /* reading the character list and question list*/
        try{
            ListOfQuestions = FileToQuestions.convertFileToQuestions();
            Characters = FileToCharacters.convertFileToCharacters();
        }catch(IOException e){
            System.out.println("Error. File not found.");
        }

        boolean found = false;

      	yesCodes.add("P");
      	do{
      		while(!(Characters.isEmpty())){

      			Node guessChara = guessCharacter();

      			boolean y = Collections.disjoint(guessChara.codes,yesCodes); //returns false if there are same elements between the two lists
                boolean x = Collections.disjoint(guessChara.codes,noCodes); //returns true if there are no same elements between the two lists

      			if(y == false && x == true){
      				for (int i = 1, j = i; i <= guessChara.codes.size()-1; i++) {
	      				String questIdx = "";
	      				boolean qFound = false;

	      				while(!(qFound) && j < guessChara.codes.size()){
	      					questIdx = (guessChara.codes).get(j);
	      					String question = getQuestion(questIdx);
	      					while(question.equals("none")){
	      						if(j > guessChara.codes.size()){
	      							break;
	      						}else{
		      						questIdx = (guessChara.codes).get(j);
		      						question = getQuestion(questIdx);
	      						}
	      						j++;

	      						
	      					}
	      					System.out.println("\n");
	      					System.out.println(question);
	      					qFound = true;
	      					j++;
	      				}
	      					String ans = "";
	      					do{
		      					System.out.print("Enter answer: ");
		      					ans = scan.next();
		      					if(ans.equals("yes")){
									yesCodes.add(questIdx);
									break;
								}else if(ans.equals("no")){
									noCodes.add(questIdx);
									Characters.remove(n);
									break;
								}else if(ans.equals("idk")){
									break;
		      					}
		      					else{
		      						System.out.println("yes, no or idk only");
		      					}
		      				}while(!(ans.equals("yes")) || !(ans.equals("no")) || !(ans.equals("idk")));

		      				if(ans.equals("no")){
		      					break;
		      				}
      					
      					if(j >= guessChara.codes.size()){
	                        found = true;
	                        System.out.println("\nYOU ARE THINKING OF: " + guessChara.name);
	                        Characters.clear();
	                        break;
                    	}
      				}

      				if (Characters.size() == 1) {
                        found = true;
                        System.out.println("\nYOU ARE THINKING OF: " + guessChara.name);
                        Characters.clear();
                        break;
                     }
                            
      			}else if(y == false && x == false){
      				Characters.remove(n);
      			}else if(y == true && x == false){
      				Characters.remove(n);
      			}
      		}

      		if(Characters.isEmpty()){
      			found = true;
      			break;
      		}
      	} while(!(found));



	}

	public static Node guessCharacter(){
		Random rand = new Random();
		n = rand.nextInt(Characters.size()) + 0;
		return Characters.get(n);
	}
	public static String getQuestion(String qIdx){
		String quest="";
		for (int j = 0; j < ListOfQuestions.size()-1; j++) {
			if(qIdx.equals(ListOfQuestions.get(j).index)){
				quest = ListOfQuestions.get(j).q;
				ListOfQuestions.remove(j);
				return quest;
			}
		}
		return "none";
	}
}