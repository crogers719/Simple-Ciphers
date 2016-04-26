/*
 * Colleen Rogers
 * Caesar Cipher
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class caesar {

	  public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	  static String encrypt(String text, int shift) {
			// Convert to char array.
			char[] buffer = text.toCharArray();
			
			
			// Loop over characters.
			for (int i = 0; i < buffer.length; i++) {
				boolean upper=false;
				if (buffer[i]!=' ' && buffer[i]!='.'){
					if (Character.isUpperCase(buffer[i])){
						upper=true;
						buffer[i]= Character.toLowerCase(buffer[i]);
					}
					
		            int charPosition = ALPHABET.indexOf(buffer[i]);

		            int keyVal = (shift + charPosition) % 26;

		            char replaceVal = ALPHABET.charAt(keyVal);
					if (upper){
						buffer[i]= Character.toUpperCase(replaceVal);
					}
					else{
					buffer[i] = replaceVal;
					}
				}
			}
			// Return final string.
			return new String(buffer);
	  }
			
			  static String decrypt(String text, int shift) {
					// Convert to char array.
					char[] buffer = text.toCharArray();
					
					
					// Loop over characters.
					for (int i = 0; i < buffer.length; i++) {
						boolean upper=false;
						if (buffer[i]!=' '&& buffer[i]!='.'){
							if (Character.isUpperCase(buffer[i])){
								upper=true;
								buffer[i]= Character.toLowerCase(buffer[i]);
							}
							
				            int charPosition = ALPHABET.indexOf(buffer[i]);

				            int keyVal = (charPosition - shift) % 26;

				            if (keyVal < 0)

				            {

				                keyVal = ALPHABET.length() + keyVal;

				            }

				            char replaceVal = ALPHABET.charAt(keyVal);
							if (upper){
								buffer[i]= Character.toUpperCase(replaceVal);
							}
							else{
							buffer[i] = replaceVal;
							}
						}
					}
					// Return final string.
					return new String(buffer);
		    }

		    public static void main(String[] args) throws FileNotFoundException {
		    	Scanner kbd = new Scanner (System.in);
		   
		    	
		    	
		    	System.out.println("Please enter the key");
		    	String inputKey = kbd.next();
		    	char key = inputKey.charAt(0);
		    	int shift=-1;
		    	for (int pos=0; pos<ALPHABET.length(); pos++){
		    		if (key==ALPHABET.charAt(pos)){
		    			shift = pos;
		    			break;
		    		}
		    	}
		    	
		    	String fileContents;
		    	String fileName;
		    	
		    	System.out.println("*******CAESAR CIPHER********");
		    	System.out.println("e-to encrypt");
		    	System.out.println("d-to decrypt");
		    	System.out.println("q-to quit");
		    	String inputChoice;
		    	char choice;
		    	inputChoice=kbd.next();
		    	choice=inputChoice.charAt(0);
		    	
		    	while (choice!='q'){
		    		if (choice=='e'){
		    			//encrypt message
		    		 	System.out.println("Enter a filename");
				    	fileName = kbd.next();
				    	try {
				    		fileContents=readFile(fileName);
				    	}
				    	finally{
				    		
				    	}
		    			
		    			System.out.println("Plaintext: "+ fileContents );

				    	String encrypted = encrypt(fileContents, shift);
				    	System.out.println("Encrypted: " + encrypted +"\n\n");
		    		}
		    		else if (choice=='d'){
		    			//decrypt message
		    			System.out.println("Enter a filename");
				    	fileName = kbd.next();
				    	try {
				    		fileContents=readFile(fileName);
				    	}
				    	finally{
				    		
				    	}
		    			
		    		 	System.out.println("Ciphertext: " + fileContents);
				    	String decrypted = decrypt (fileContents, shift);
				    	System.out.println ("Decrypted = " +decrypted +"\n\n");
		    		}
		    		else{
		    			System.out.println("invalid input");
		    		}
			    	System.out.println("e-to encrypt");
			    	System.out.println("d-to decrypt");
			    	System.out.println("q-to quit");
			    	
			    	
			    	inputChoice=kbd.next();
			    	choice=inputChoice.charAt(0);
		    		
		    		
		    		
		    	}
		    	
		    	
		    	System.out.println("\nGOODBYE!\n");
	
			
		    	
	
		    }
	public static String readFile (String fileName)throws FileNotFoundException{
		 String content=null;
    	 try{
    		 content = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
    	 }
    	 finally{
    		
    	 }
    	 return content;
	}
}