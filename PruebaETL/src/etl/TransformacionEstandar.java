package etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

///fichero final.csv debe tener: correo, nombre apellidos

public class TransformacionEstandar {

      public static void main(String[] args) {
            BufferedReader reader;
            FileWriter myWriter;
            try {
                  reader = new BufferedReader(new FileReader("C:\\Users\\cesar\\eclipse-workspace\\PruebaETL\\src\\etl\\Vista2.csv"));
                  myWriter = new FileWriter("GHL.csv");
                  
                  String line = reader.readLine();
                  myWriter.write("Phone,Email,First Name,Last Name,Business Name,Source,Additional Emails,Additional Phones,Notes,Tags");
                  myWriter.write(System.getProperty("line.separator"));
                  int z=0;
                  while (line != null) {
                        if(line.length()>0) {
                        	line=cleanLine(line);
                        	line=cleanLine(line);
                        	//System.out.println(line.split(",").length+" "+ ++z);
                        	//System.out.println(line);
                        	  String mail=line.split(",")[0];
                        	  String nombre=line.split(",")[1];
                        	  String firstname="";
                        	  String lastname="";
                        	  int nnombre=nombre.split("\\s+").length;
                        	  if (nnombre>1) {
                        		  firstname=nombre.split("\\s+")[0];
                        		  lastname=nombre.split("\\s+")[1];
                        	  }
                              String numTelf=line.split(",")[2].replace("-", "").replace(" ","" );
                              String Coment=(line.split(",").length-1 > 5)?line.split(",")[5]:"";
                              String Closer=(line.split(",").length-1 > 12)?line.split(",")[12]:"";
                              String Product=(line.split(",").length-1 > 13)?line.split(",")[13]:"";
                              String leads=(line.split(",").length-1 > 15)?line.split(",")[15]:"";
                              myWriter.write(numTelf+","+mail+","+firstname+","+lastname+","+Product+","+leads+",,,"+Coment+","+Closer);
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
    		  System.out.println(wordToReplace);
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
