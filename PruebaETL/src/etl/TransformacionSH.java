package etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

///fichero final.csv debe tener: correo, nombre apellidos

public class TransformacionSH {

      public static void main(String[] args) {
            BufferedReader reader;
            FileWriter myWriter;
            try {
                  reader = new BufferedReader(new FileReader("C:\\Users\\cesar\\eclipse-workspace\\PruebaETL\\src\\etl\\Vista3.csv"));
                  myWriter = new FileWriter("GHL.csv");
                  
                  String line = reader.readLine();
                  myWriter.write("Phone,Email,First Name,Last Name,Business Name,Source,Additional Emails,Additional Phones,Notes,Tags");
                  myWriter.write(System.getProperty("line.separator"));
                  int z=0;
                  while (line != null) {
                        if(line.length()>0) {
                        	line=cleanLine(line);
                        	line=cleanLine(line);
                        	line=cleanLine(line);
                        	line=cleanLine(line);
                        	line=cleanLine(line);
                        	line=cleanLine(line);
                        	line=cleanLine(line);
                        	System.out.println(line.split(",").length+" "+ ++z);
                        	//System.out.println(line);
                        	  String mail=line.split(",")[4];
                        	  String nombre=line.split(",")[1];
                        	  String lastname=line.split(",")[2];
                        	  lastname=lastname.length() ==0?"SinApellido":lastname;
                              String numTelf=line.split(",")[3].replace("-", "").replace(" ","" );
                              numTelf=numTelf.contains("+")?numTelf:"+34"+numTelf;
                              String Coment=(line.split(",").length-1 > 18)?line.split(",")[18]:"";
                              String Closer=(line.split(",").length-1 > 14)?line.split(",")[14]:"";
                              String Product="";
                              String leads=(line.split(",").length-1 > 9)?line.split(",")[9]:"";
                              myWriter.write(numTelf+","+mail+","+nombre+","+lastname+","+Product+","+leads+",,,"+Coment+","+Closer);
                              myWriter.write(System.getProperty("line.separator"));
                        }
                        line = reader.readLine();
                  }
                  reader.close();
                  myWriter.close();
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }
      
      public static String cleanLine(String line) {
    	  if (line.contains("\"")) {
    		  int startIndex=line.indexOf("\"");
    		  String line2=line.substring(startIndex+1);
    		  int endIndex=startIndex+line2.indexOf("\"");
    		  String wordToReplace=line.substring(startIndex, endIndex+2);
    		  //System.out.println(wordToReplace);
    		  String replaced=wordToReplace.replace(",", "");
    		  replaced=replaced.replace("\"", "");
    		  
    		  line=line.replace(wordToReplace, replaced);

    			  return line;  

    	  }
    	  else {
    		  return line;  
    	  }
      }
}
