
package countryguesser;

import java.io.*;
import java.util.*;


public class CountryGuesser {

    static int numQuestions = 55; 
    static int numCountries = 50; 
    static int numRemaining = numCountries;
    static Attribute[] attributes = new Attribute[numQuestions];
    static Country[] countries;
    
    //reading in all the potential questions from a txt file
    public static void readAttributesFromAFile() throws IOException {
        
        FileReader f = new FileReader("QUESTIONS.txt");
        Scanner s = new Scanner(f);

        for (int i = 0; i < CountryGuesser.numQuestions; i++) {
            attributes[i] = new Attribute(s.nextLine());
        }
    }
    
    
    //this is a helper method for pickNextQuestion that goes through all the contries and determines which
    //question will eliminate the most countries/candidates.
    public static double checkTFBalance(int qNum) {
        double numWithAttribute = 0;
        double split;
        
        for (int i = 0; i < numCountries; i++) {
            
            if (countries[i].isStillCandidate) {
                if (countries[i].attributes[qNum].attributeVal == true) {
                    numWithAttribute++;
                }
            }
        }
        
        split = Math.abs((double)numRemaining / 2 - numWithAttribute);
        //System.out.println(splitIndex);
        return split;
    }

    //this is the method that picks the next question, it uses the helper method above to  find the question
    //that will eliminate the most candidates. 
    
    //This method was one that we had alot of trouble with; I asked my university friend, Fartash Ahmad
    //who is at UW for CS about it and he helped with the making of it. - Humza
    public static int pickNextQuestion(int qNum) {
        double split = 0;
        double min = checkTFBalance(0);
        int bestQuestion = 0;

        for (int i = 0; i < numQuestions; i++) {
            split = checkTFBalance(i);
            if (split <= min) {
                min = split;
                bestQuestion = i;
            }
        }
        //System.out.println(bestQuestion);
        return bestQuestion;
    }
    
    //gets user response and sets it to either true or false
    public static boolean getResponse(){
        
        Scanner n = new Scanner(System.in);
        String s = n.next();
        boolean r = false;
        
        if (s.equalsIgnoreCase("yes")){
            r = true;
        }
        else if (s.equalsIgnoreCase("no")){
            r = false;
        }
        
        return r;
    }
    
    //eliminates candidates if the response is no
    public static void elimCandidates(int qNum, boolean r){
        
        for (int i = 0; i < numCountries; i++) {
            if (countries[i].attributes[qNum].attributeVal != r) {
                countries[i].setCandToFalse();
            }
        }
    }
    
    //checks if the country is still a candidate; if so, adds a country to numRemaining
    public static void countRemCands(){
        numRemaining = 0;
        for (int i = 0; i < countries.length; i++) {
            if(countries[i].isStillCandidate == true){
                numRemaining++;
            }
            
        }
    }
    
    //used to display the index of the country that is left at the end of the program
    public static void displayFinalResult() {
        for (int i = 0; i < numCountries; i++) {
            if (countries[i].isStillCandidate) {
                System.out.println("Your country must be " + countries[i].countryName + "!");
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        
        
        System.out.println("Welcome to the Country Guesser Game!");
        System.out.println("Choose a country from the first link provided, and then follow the world map provided in the folder (filename WORLD MAP) and the user manual to aid you in answering the questions!");
        System.out.println("www.geohive.com/earth/area_top50.aspx");
        
        
        readAttributesFromAFile();
        
        countries = Country.readCountriesFromAFile();
        
        int questions = 0;
        boolean response;
        int questionNum;
        
        
        while (numRemaining > 1) {
            
            questions++;
            
            //if the question will eliminate the most candidates, ask the question
            questionNum = pickNextQuestion(questions);

            attributes[questionNum].askQuestion();

            response = getResponse();

            elimCandidates(questionNum, response);

            countRemCands();

        }
        
        displayFinalResult();
        
        
        
    }
}
