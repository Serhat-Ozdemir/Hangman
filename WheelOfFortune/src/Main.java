import java.io.*;
import java.util.*;
public class Main {
	//Looks all words from compare stack and saves alphabetically smaller one in an another stack(Savestack) and pushes the biggest one in stack for hold sorted countries
	public static void increasingSortString(Stack compareStack, Stack saveStack, Stack sortedStack) {
		String biggerString = (String) compareStack.Pop();
			while(!compareStack.isEmpty()) {
				String initialWord = (String) compareStack.Pop();
				int compare = initialWord.compareTo(biggerString);
				if(compare < 0) 
					saveStack.Push(initialWord);
				else if(compare == 0) 
					saveStack.Push(initialWord);
				else if(compare > 0) {
					saveStack.Push(biggerString);
					biggerString = initialWord;
				}
			}
			sortedStack.Push(biggerString);
		}
	//Looks all elements from compare stack and saves numerically bigger one in an another stack(Savestack) and pushes the smallest one in stack for hold sorted names and scores
	public static void decreasingSortInteger(Stack compareStackScores, Stack saveStackScores, Stack sortedStackScores,Stack compareStackNames, Stack saveStackNames, Stack sortedStackNames) {
		String smallerScore = (String) compareStackScores.Pop();
		String nameOfSmallerScore = (String) compareStackNames.Pop();
			while(!compareStackScores.isEmpty()) {
				String initialWord = (String) compareStackScores.Pop();
				String initialName = (String) compareStackNames.Pop();
				int compare = Integer.valueOf(initialWord)-Integer.valueOf(smallerScore);
				if(compare < 0) {
					saveStackScores.Push(smallerScore);
					smallerScore = initialWord;
					saveStackNames.Push(nameOfSmallerScore);
					nameOfSmallerScore = initialName;
				}					
				else if(compare == 0) {
					saveStackScores.Push(initialWord);
					saveStackNames.Push(initialName);
				}

				else if(compare > 0) {
					saveStackScores.Push(initialWord);
					saveStackNames.Push(initialName);
				}

					
			}
			sortedStackScores.Push(smallerScore);
			sortedStackNames.Push(nameOfSmallerScore);
		}
	
	//Randomly chooses xth element saves popped data in temp stack and then replaces it in the proper stack
	public static String randomchoose(int randomNumber, Stack stackTochoosen, Stack tempStack) {
		String choosenWord = "";
		for(int i = 0; i<randomNumber;i++) {
			if(i==randomNumber-1) {
				choosenWord = stackTochoosen.Pop().toString();				
				int size = tempStack.Size();
				for(int j = 0; j< size ; j++)
					stackTochoosen.Push(tempStack.Pop());
			}
			else {
				tempStack.Push(stackTochoosen.Pop());
			}
			
		}
		return choosenWord;
	}
	//Prints game screen
	public static void gameSreenPrinter(Queue printedCountry,int wordcheck, int step, int score, int wheel, Stack letters, Stack tempStack ) {
		String tempstr = "";
		System.out.print("Word: ");
		for(int i = 0; i< printedCountry.Size(); i++) {
			tempstr = printedCountry.Dequeue().toString();
			System.out.print(tempstr);
			printedCountry.Enqueue(tempstr);
		}
		System.out.print("\t\t Step: " + step);
		System.out.print("\t\t Score: " + score);
		int size = letters.Size();
		System.out.print("\t\t");
		for(int j = 0; j<size; j++) {
			tempstr = letters.Pop().toString();
			System.out.print(tempstr);
			tempStack.Push(tempstr);
		}
		for(int j = 0; j<size; j++) {
			tempstr = tempStack.Pop().toString();
			letters.Push(tempstr);
		}
		if(wordcheck != printedCountry.Size()) {
			if(wheel == 0)
				System.out.println("\nWheel: Bunkrupt");
			else if(wheel == 1)
				System.out.println("\nWheel: Double Money");
			else
				System.out.println("\nWheel: " + wheel);
		}

			
		
	}

	

	public static void main(String[] args) throws Exception {
		Stack unsortedcountries = new Stack(197), sortedcountries = new Stack(197),stackForCompareCountries = new Stack(197);//Stacks for manipulate countries
		Stack tempStack = new Stack (197);//Stack to be used when chosen a random element of array
		Stack letters = new Stack (26);//Letters
		Stack wheel = new Stack(8);//Wheel
		Stack unsortedScores =  new Stack(100), sortedScores = new Stack(100),stackForCompareScores = new Stack(100);//Stacks for manipulate scores
		Stack unsortedNames = new Stack(100), sortedNames = new Stack(100), stackForSaveScores = new Stack(100);//Stacks for manipulate names
		String choosenWord = "";//To be used for random choose
		int size = 0;//For determine length of for loops
		int wordcheck = 0, step = 1,score =0, correctguess =0;//For gameplay
		String tempLetter ="";//For determine if letter exist in choosen country
		
		
		String scannedWord = "";//For reading files
		Random rand = new Random();	
		
		//Reading file, removing punctuations and spaces and adding stack to countries
		File file = new File("Countries.txt");
		Scanner scan = new Scanner(file);
		String compareCountry = "";
		while(scan.hasNextLine())
		{
			scannedWord = scan.nextLine();
			scannedWord = scannedWord.replace(" ", "");
			scannedWord = scannedWord.replaceAll("\\p{Punct}", "");
			unsortedcountries.Push(scannedWord);
		}		
		scan.close();
		
		//For sorting countries
		Stack compareFlag = unsortedcountries, saveFlag = stackForCompareCountries, tempFlag = null;//Flags are used to change compare stack when one is empty due to popping in sort function
		size = unsortedcountries.Size();
		for(int i =0 ;i<size;i++) {//Changes stack to be looked for all words
			increasingSortString(compareFlag, saveFlag,sortedcountries);
			tempFlag = compareFlag;
			compareFlag = saveFlag;
			saveFlag = tempFlag;
		}
		//Adding letters
		for(int i = 90; i>64; i--) {
			letters.Push((char)i);
		}
		//Adds wheel
		for(int i =0; i < 8;i++) {
			switch(i){
			//case(0): wheel.Push(0);break;//bankrupt
			case(1): wheel.Push(1);break;//double money
			case(2): wheel.Push(10);break;
			case(3): wheel.Push(50);break;
			case(4): wheel.Push(100);break;
			case(5): wheel.Push(250);break;
			case(6): wheel.Push(500);break;
			case(7): wheel.Push(1000);break;
			}
		}
		//Taking player's name
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your name:");
		String playerName = sc.nextLine();
	
		//Randomly chooses country	
		int randomCountry = rand.nextInt(1,sortedcountries.Size()+1);
		System.out.println("Randomly Generated Number: "+ randomCountry);				
		choosenWord = randomchoose(randomCountry, sortedcountries, tempStack).toUpperCase(Locale.ENGLISH);	
		//Initialize choosen country queue and display queue
		Queue choosenCountry = new Queue(choosenWord.length()), queueForDisplay = new Queue(choosenWord.length());
		for(int i = 0; i <choosenWord.length(); i ++) {
			choosenCountry.Enqueue(choosenWord.charAt(i));
			queueForDisplay.Enqueue('-');
		}
		//Random wheel
		int randomWheel = rand.nextInt(1,wheel.Size()+1);
		int wheelvalue = Integer.valueOf(randomchoose(randomWheel, wheel, tempStack));
		wheel.Push(wheelvalue);		
		gameSreenPrinter(queueForDisplay,wordcheck, step, score, wheelvalue, letters, tempStack);
		step++;
		//Gameplay
		
		while(wordcheck!=choosenWord.length()) {
			//int randomLetter = rand.nextInt(1,letters.Size()+1);//Random letter choose
			//String choosenLetter = randomchoose(randomLetter, letters, tempStack);
			Scanner scn = new Scanner(System.in);
			String choosenLetter = "";
			System.out.println("Make a guess:");
			choosenLetter = scn.nextLine();
			choosenLetter = choosenLetter.toUpperCase(Locale.ENGLISH);
			
			//Makes a full loop to maintain country name in the circular queue
			for(int i =0; i< choosenCountry.Size();i++) {
				tempLetter = choosenCountry.Dequeue().toString();
				if(choosenLetter.equals(tempLetter)) {//If letter exist
					queueForDisplay.Dequeue();
					queueForDisplay.Enqueue(tempLetter);
					wordcheck++;//For determine if the word completed
					correctguess++;//For determine player score
				}

				else if(!queueForDisplay.Peek().toString().equalsIgnoreCase("-")) {//If there is a letter already opened
					tempLetter = queueForDisplay.Dequeue().toString();
					queueForDisplay.Enqueue(tempLetter);
				}
				else {//If there is no letter that openede before
					queueForDisplay.Dequeue();
					queueForDisplay.Enqueue("-");
				}					
				choosenCountry.Enqueue(tempLetter);					
			}
			if(wheelvalue != 0 && wheelvalue !=1)//For score calculation without bankrupt and double money
				score = score + correctguess*wheelvalue;
			else {//Score calculation for bankrupt and double money
				if(wheelvalue == 1 &&correctguess>0)//Doupble money
					score = score*2;			
				else if(wheelvalue == 0)
					score = 0;;
			}
			randomWheel = rand.nextInt(1,wheel.Size()+1);
			wheelvalue = Integer.valueOf(randomchoose(randomWheel, wheel, tempStack));		
			wheel.Push(wheelvalue);
			gameSreenPrinter(queueForDisplay,wordcheck, step, score, wheelvalue, letters, tempStack);
			step++;
			correctguess =0;
			
			Thread.sleep(250);
			
		}
		//Writing and adding names and scores to the stacks and file
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("HighScoreTable.txt", true)));
	    out.print("\n"+ playerName + " " + score);
	    out.close();
		file = new File("HighScoreTable.txt");
		scan = new Scanner(file);		
		while(scan.hasNextLine())
		{
			scannedWord = scan.nextLine();
			unsortedScores.Push(scannedWord.substring(scannedWord.indexOf(" ")+1));
			unsortedNames.Push(scannedWord.substring(0,scannedWord.indexOf(" ")));
			
		}
		//Sorting names and scores
		Stack compareFlagScores = unsortedScores, saveFlagScores = stackForCompareScores,compareFlagNames = unsortedNames, saveFlagNames = stackForSaveScores;
		tempFlag = null;
		size = unsortedScores.Size();
		for(int i =0 ;i<size;i++) {
			decreasingSortInteger(compareFlagScores, saveFlagScores,sortedScores, compareFlagNames, saveFlagNames,sortedNames);
			tempFlag = compareFlagScores;
			compareFlagScores = saveFlagScores;
			saveFlagScores = tempFlag;
			tempFlag = compareFlagNames;
			compareFlagNames = saveFlagNames;
			saveFlagNames = tempFlag;
		}
		//Printing names and scores
		size = sortedScores.Size();
		if(size>10)
			size =10;
		for(int i =0; i<= size ;i++) {
			System.out.print(sortedNames.Pop() + " " );
			System.out.println(sortedScores.Pop());
		}

			
		scan.close();
		

	}

}
