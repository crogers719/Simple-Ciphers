
/*
 * Colleen Rogers
 * Tiny Encryption Algorithm (TEA)
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class tea {
	
	//constants
	public final static int E= 0x9E3779B9;
	public final static int BLOCKSIZE  = 32;
	public final static int D = 0xC6EF3720;

	public static int[] keyArray = new int[4];


	
	public static void main(String[] args) throws FileNotFoundException {
	
		Scanner kbd=new Scanner (System.in);
		
		System.out.println("Enter key (must be at least 16 ASCII characters long)");
		String inputKey=kbd.nextLine();
		
		
		setKey(inputKey.getBytes());

		String fileContents;
    	String fileName;
    	
    	System.out.println("*******TEA CIPHER********");
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
		    	byte[] plaintext = fileContents.getBytes();
    			
    			System.out.println("Plaintext: "+ fileContents );

    			byte[] encrypted  = encipher(plaintext);
    			
    			String ciphertext = new String (encrypted);    			
		    	System.out.println("Encrypted: " + ciphertext +"\n\n");
		    	
		    	
		    	
		    	byte[] decrypted  = decipher(encrypted);
		    	
		    		    	
		    	String plaitext = new String (decrypted);    			
		    	System.out.println("Decrypted: " + plaitext +"\n\n");
		    	
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
		    	
		    	byte[] ciphertext = fileContents.getBytes();
		    	System.out.println("Ciphertext: "+ fileContents );
		    	byte[] decrypted  = decipher(ciphertext);
		    	
		    		    	
		    	String plaintext = new String (decrypted);    			
		    	System.out.println("Decrypted: " + plaintext +"\n\n");
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
	
	public static void setKey(byte[] key) {
		if (key.length < 16)
			throw new RuntimeException("ERROR: Key must be atleast 16 bytes long");
		
		// Split the key into four blocks(K0, K1, K2, K3)
		for (int offset=0, i=0; i<4; i++) {
			keyArray[i] = ((key[offset++] & 0xff)) |
			((key[offset++] & 0xff) <<  8) |
			((key[offset++] & 0xff) << 16) |
			((key[offset++] & 0xff) << 24);
		}
	}

	
	public static byte[] encipher(byte[] plaintext) {
		//find size with padding by splitting plaintext into 8 segments and if not a multiple of 8, add 2
		int paddedSize = ((plaintext.length/8) + (((plaintext.length%8)==0)?0:1)) * 2;
		
		//create keyArray of the paddedSize+1
		int[] keyArray = new int[paddedSize + 1];
		
		//first element of the array contains the length of the plaintext
		keyArray[0] = plaintext.length;
		
		//Packs plaintext into keyArray with offset of 1
		ship(plaintext, keyArray, 1);
		
		//perform encryption
		encrypt(keyArray);
		
		//recieve by returns byte[] from keyArray with offset 1 
		return recieve(keyArray, 0, keyArray.length * 4);
	}


	public static byte[] decipher(byte[] crypt) {
		
		int[] keyArray = new int[crypt.length / 4];
		ship(crypt, keyArray, 0);
		decrypt(keyArray);
		return recieve(keyArray, 1, keyArray[0]);
	}
	
	public static void ship(byte[] source, int[] destination, int offset) {
		//offset refers to destination offset
		
		int i = 0, shift = 24;
		
		destination[offset] = 0;
		while (i<source.length) {
			destination[offset] |= ((source[i] & 0xff) << shift);
			if (shift==0) {
				shift = 24;
				offset++;
				if (offset<destination.length) destination[offset] = 0;
			}
			else {
				shift -= 8;
			}
			offset++;
		}
	}
	public static byte[] recieve(int[] source, int offset, int destLength) {
		//offset refers to source offset
		byte[] destination = new byte[destLength];
		int count = 0;
		for (int j = 0; j < destLength; j++) {
			destination[j] = (byte) ((source[offset] >> (24 - (8*count))) & 0xff);
			count++;
			if (count == 4) {
				count = 0;
				offset++;
			}
		}
		return destination;
	}
	
	public static void encrypt(int[] buffer) {

		int i, v0, v1, sum, n;
		i = 1;
		while (i<buffer.length) {
			n = BLOCKSIZE;
			v0 = buffer[i];
			v1 = buffer[i+1];
			sum = 0;
			while (n-->0) {
				sum += E;
				v0  += ((v1 << 4 ) + keyArray[0] ^ v1) + (sum ^ (v1 >>> 5)) + keyArray[1];
				v1  += ((v0 << 4 ) + keyArray[2] ^ v0) + (sum ^ (v0 >>> 5)) + keyArray[3];
			}
			buffer[i] = v0;
			buffer[i+1] = v1;
			i+=2;
		}
	}
	
	public static void decrypt(int[] buffer) {

		int i, v0, v1, sum, n;
		i = 1;
		while (i<buffer.length) {
			n = BLOCKSIZE;
			v0 = buffer[i]; 
			v1 = buffer[i+1];
			sum = D;
			while (n--> 0) {
				v1  -= ((v0 << 4 ) + keyArray[2] ^ v0) + (sum ^ (v0 >>> 5)) + keyArray[3];
				v0  -= ((v1 << 4 ) + keyArray[0] ^ v1) + (sum ^ (v1 >>> 5)) + keyArray[1];
				sum -= E;
			}
			buffer[i] = v0;
			buffer[i+1] = v1;
			i+=2;
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
}

/*
 * 
 NOTES
 Bit Sift operators:
 
 ~    Unary bitwise complement
<<      Signed left shift
>>      Signed right shift
>>>     Unsigned right shift
&       Bitwise AND
^       Bitwise exclusive OR
|       Bitwise inclusive OR
 */