import java.util.*;
import java.io.*;
import ExtendibleHashingScheme.*;

class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("...................MODULE-2..........DBMS-PROJECT...................");
		System.out.println("............SIMULATION..OF..EXTENDIBLE..HASHING..SCHEME.............");                          
		System.out.println("*****************************************************************************************");
		System.out.println("..Enter input in the following manner \"Globaldepth Localdepth Blockingfactor Numberforhashingfunctionmodulo\"..");
                System.out.println("Enter keys in the following way..........\"key operationtoperform\"");
		System.out.println("Operations to perform are Insert - I, Search - S, Delete - D");
		
		System.out.println("Type \"exit\" to stop the program");
		
		
		BufferedReader a = new BufferedReader(new InputStreamReader(System.in));
		int blockingFactor, globalDepth, localDepth, m, key;
		Directory presentation = null;

		System.out.print("=> Give the inputs : ");
		String b = a.readLine();
		StringTokenizer c = new StringTokenizer(b, " ");
		
		while(c.hasMoreTokens()){
			
			globalDepth = Integer.parseInt(c.nextToken());
			localDepth = Integer.parseInt(c.nextToken());
                        blockingFactor = Integer.parseInt(c.nextToken());
			m = Integer.parseInt(c.nextToken());
			presentation = new Directory(globalDepth, localDepth, blockingFactor);
			System.out.println(presentation);
		}

		

		do{
			System.out.print("=>");
			b = a.readLine();
			if(b != null && !b.equals("exit")){
				c = new StringTokenizer(b, " ");
				if(c.countTokens() < 2){
					System.out.println("Wrong input format");
				} else {
					key = Integer.parseInt(c.nextToken());
					switch(c.nextToken()){
						case "I": {
							System.out.println("Inserting " + key + ": ");
							presentation.insertKey(key);
							break;
						}
						case "S": {
							System.out.println("Searching " + key + ": ");
							if(presentation.searchKey(key)){
								System.out.println("Key is present");
							} else {
								System.out.println("Key is absent");
							}
							break;
						}
						case "D": {
							System.out.println("Deleting " + key + ": ");
							presentation.deleteKey(key);
							break;
						}
						default : {
							System.out.println("Operation does not exist");
							break;
						}
					}
				}
			}
			System.out.println(presentation);
		} while(!b.equals("exit"));

		
		System.out.println("The final distribution is as shown:\n" + presentation);
	}
}