import java.util.Random;
import java.util.Scanner;
public class Guess {
	
	//PRE: Inputs a Position between A1 and H8
	public String Scan() {
		Scanner Input = new Scanner(System.in);
		String answer = null;
		boolean i = true;
		
		//Checks if valid input according to PRE-condition
		while(i) {
			System.out.println("Type in Position of new Guess: ");
			String pos = Input.nextLine();
			if((int) pos.charAt(0) < 65 || (int) pos.charAt(0) > 72
					|| (int) pos.charAt(1) < 49 || (int) pos.charAt(1) > 56) {
				System.out.println("Please type in a correct Position");
			}
			/*		UNCOMMENT TO SET IN EFFECT
			//Check if valid Shot
			else if(!ValidShot(pos)) {
				System.out.println("Please type in a valid Position");
				
			}
			*/
			else {
				i = false;
				answer = pos;	
			}
		}
		Input.close();
		
		/*Sets the guess     UNCOMMENT TO SET IN EFFECT
		Block.setGuess(answer);
		Block.setStatus(answer);
		
				PROBLEM: Dont know instance of Block if the Position is meant for an
				instance (e.g Board1)
		*/
		return answer;
	}
	
	/*			UNCOMMENT TO SET IN EFFECT
	//Checks wether Shot is valid
	private boolean ValidShot(String pos) {
		if(Board.isSunk(pos) || Board.isHit(pos)) {
			return false;
		}
		else return true;
		
	}
	*/
	//PRE: Generates an Input Position between A1 and H8
	public String ComputerGuess() {
		Random rnd = new Random();
		char P1 = (char) (65 + rnd.nextInt(8));
		char P2 = (char) ('1' + rnd.nextInt(8));
		
		String Canswer = Character.toString(P1) + Character.toString(P2);
		
		/*Sets the guess
		Block.setGuess(Canswer);
		Block.setStatus(Canswer);
		
				PROBLEM: Dont know instance of Block if the Position is meant for an
				instance (e.g Board1)
		*/
		
		return Canswer;
	}
	
	
	

}
