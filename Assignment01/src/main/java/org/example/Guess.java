package org.example;

import java.util.Random;
import java.util.Scanner;
import org.example.board;
public class Guess {
	
	//PRE: Inputs a Position between A1 and H8
	public static String PlayerGuess() {
		Scanner Input = new Scanner(System.in);
		String answer = null;
		boolean i = true;

		//Checks if valid input according to PRE-condition
		System.out.println("Type in new Guess: ");
		while(i) {

			String pos = Input.nextLine();
			//CHECK FOR A10 INPUT WETHER IT WORKS
			if((int) pos.charAt(0) < 65 || (int) pos.charAt(0) > 74
					|| (int) pos.charAt(1) < 49 || (int) pos.charAt(1) > 57) {
				System.out.println("Please type in a correct Position");
			}

			else if(!ValidShot(pos)) {
				System.out.println("Please type in a valid Position");
				
			}

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
	

	public static boolean ValidShot(String pos) {
		if(board.GotSunk(pos) || board.GotHit(pos)) {
			return false;
		}
		else return true;
		
	}

	//PRE: Generates an Input Position between A1 and H8
	public String ComputerGuess() {
		Random rnd = new Random();
		char P1 = (char) (65 + rnd.nextInt(10));
		char P2 = (char) ('1' + rnd.nextInt(10));

		
		String Canswer = P1 + Character.toString(P2);
		
		/*Sets the guess
		Block.setGuess(Canswer);
		Block.setStatus(Canswer);
		
				PROBLEM: Dont know instance of Block if the Position is meant for an
				instance (e.g Board1)
		*/
		
		return Canswer;
	}
	
	
	

}
