
package countryguesser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public final class Country {
    
    //setup 
    Attribute[] attributes = new Attribute[CountryGuesser.numQuestions];
    String countryName;
    boolean isStillCandidate = true;
    
    //constructor
    public Country (String n) throws IOException {
        this.countryName = n;
        setup();
        
    }

    //reading in questions and putting them into an array
    public void setup() throws IOException {
      
        FileReader f = new FileReader("QUESTIONS.txt");
        Scanner s = new Scanner(f);

        for (int i = 0; i < CountryGuesser.numQuestions; i++) {
            attributes[i] = new Attribute(s.nextLine());
        }
        
    }
    
    //reads in all the countries from the file, including all the attributes of the countries.
    public static Country[] readCountriesFromAFile() throws IOException {
        
        FileReader f = new FileReader("ATTRIBUTES.txt");
        Scanner s = new Scanner(f);
        String attribute = "0";
        
        Country[] Countries = new Country[CountryGuesser.numCountries];
        int numQuestions = CountryGuesser.numQuestions;
        
        String name = s.nextLine();
        
        for (int i = 0; i < Countries.length; i++) {
            
            Countries[i] = new Country(name);
            if (s.hasNextLine()) {
                attribute = s.nextLine();
            }
            
            
        
        //this is courtesy of Octavio Harris, another first-year university friend of mine who told me to use a new method of sorting the countries
        //our group understood this better, and with it we could actually get the program working.
            
        while (attribute.charAt(0) != '#') {

                for (int j = 0; j < numQuestions; j++) {
                    if (attribute.equalsIgnoreCase(Countries[i].attributes[j].attributeDescription)) {
                        Countries[i].attributes[j].setValToTrue();
                    }
                }
                
                if (!s.hasNext()){
                    break;
                }
                
                attribute = s.nextLine();
  
                //ignores empty lines
                while (attribute.length() == 0) {
                    attribute = s.nextLine();
                }

            }
        
            name = attribute.substring(2);


        }
        
        return Countries;
    }
    
    //sets the candidate to false, is a helper method
    public void setCandToFalse(){
        this.isStillCandidate = false;
        
    }

}
    
    
    
