import java.io.*;
import java.util.Scanner;
public class fileTest {
    public static void main(String [] args) throws FileNotFoundException {
    	 String fileName = "temp.txt";
    	 String content=null;
    	 try{
    		 content = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
    	 }
    	 finally{
    		
    	 }
    	System.out.println(content);
    }
/*
        // The name of the file to open.
        String fileName = "temp.txt";
        String contents=null;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            contents=sb.toString();
        } finally {
            br.close();
        }
        
        System.out.println(contents);


        }
        8/
  /*
static String readFile(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
}*/
}