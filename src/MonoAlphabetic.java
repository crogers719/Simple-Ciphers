/*
 * Colleen Rogers
 * Monoalphabetic Cipher
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MonoAlphabetic {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	
	public static void main(String[] args) throws FileNotFoundException{
	    	Scanner kbd = new Scanner (System.in);
			   	
	    	
	    	System.out.println("Please enter the key word");
	    	String keyWord = kbd.next();
	    	
	    	keyWord.toLowerCase();
	    	
	    	String ciphertextAlphabet="";
	    	
	    	//fill in key word
	    	for (int i=0; i<keyWord.length(); i++){
	    		char current= keyWord.charAt(i);
	    		if (ciphertextAlphabet.indexOf(current)==-1){
	    			ciphertextAlphabet+=current;
	    		}
	    	}
	    	
	    	//fill in remaining letters in order
	    	for (int j=0; j<ALPHABET.length(); j++){
	    		char current=ALPHABET.charAt(j);
	    		if (ciphertextAlphabet.indexOf(current)==-1){
	    			ciphertextAlphabet+=current;
	    		}
	    	}
	    	
	    	
	    	String fileContents;
	    	String fileName;
	    	
	    	System.out.println("*******MONOALPHABETIC CIPHER********");
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

			    	String encrypted = encrypt(fileContents, ciphertextAlphabet);
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
			    	String decrypted = decrypt (fileContents, ciphertextAlphabet);
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
	
	 static String encrypt(String text, final String ciphertextAlphabet) {
	        char []buffer = text.toCharArray();
	        
	       // text = text.toUpperCase();
	        for (int i = 0, j = 0; i < buffer.length; i++) {
	        	boolean upper=false;
				if (buffer[i]!=' ' && buffer[i]!='.'){
					if (Character.isUpperCase(buffer[i])){
						upper=true;
						buffer[i]= Character.toLowerCase(buffer[i]);
					}
					
		            int charPosition = ALPHABET.indexOf(buffer[i]);


		            char replaceVal = ciphertextAlphabet.charAt(charPosition);
					if (upper){
						buffer[i]= Character.toUpperCase(replaceVal);
					}
					else{
					buffer[i] = replaceVal;
					}
				}
		
	        }
	        
	        return new String(buffer);
	    }
				/*
	            boolean upper=false;
	            
	        	if (Character.isUpperCase(buffer[i])){
					upper=true;
					buffer[i]= Character.toLowerCase(buffer[i]);
				}
	            char letter=buffer[i];
	            letter = (char) (letter + key.charAt(j) -2 * 'a');
				if (letter > 'z') {
					letter = (char) (letter - 26);
				} 	
				else if (letter < 'a') {
					letter = (char) (letter + 26);
				}
				if (upper){
					buffer[i]= Character.toUpperCase(letter);
				}
				else{
				buffer[i] = letter;
				}*/
				 
			
		
		// Return final string.
		
	            
	            
	            
	    /*        
	            if (c < 'a' || c > 'z') continue;
	            enChar= (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'a');
	            if (upper){
	            	enChar= Character.toUpperCase(enChar);
	            }
	            result+=enChar;
	            j = ++j % key.length();
	        }
	        return result;*/
	    
	 
	    static String decrypt(String text, final String ciphertextAlphabet) {
	        char []buffer = text.toCharArray();
	        
	        // text = text.toUpperCase();
	         for (int i = 0, j = 0; i < buffer.length; i++) {
	         	boolean upper=false;
	 			if (buffer[i]!=' ' && buffer[i]!='.'){
	 				if (Character.isUpperCase(buffer[i])){
	 					upper=true;
	 					buffer[i]= Character.toLowerCase(buffer[i]);
	 				}
	 				
	 	            int charPosition = ciphertextAlphabet.indexOf(buffer[i]);
      
		            char replaceVal = ALPHABET.charAt(charPosition);
	 
		            if (upper){
	 					buffer[i]= Character.toUpperCase(replaceVal);
	 				}
	 				else{
	 				buffer[i] = replaceVal;
	 				}
	 			}
	 			 
	         }
	         return new String(buffer); 
	    }
	    
	
}
