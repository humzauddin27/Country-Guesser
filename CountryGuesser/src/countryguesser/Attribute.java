
package countryguesser;

public class Attribute {
    
    boolean attributeVal = false;
    String attributeDescription;
    String question;
    
    //constructor
    public Attribute(String desc) {
    
        this.attributeDescription = desc;
        getQuestion(this.attributeDescription);
    }
    
    //
    public void setValToTrue(){
        this.attributeVal = true;
    }
    
    
    //idea for this method is courtesy of Octavio Harris again. With his new way of sorting, this was the most logical
    //way for our group to go. some of the questions are poorly worded though due to the question making being slightly flawed, but it works.
    public void getQuestion (String desc){
        String first, last;
        
        first = this.attributeDescription.substring(0, desc.indexOf(' '));
        last = desc.substring(desc.indexOf(' '), desc.length());
        
        if (first.equalsIgnoreCase("country")) {
        
            question = "Is the country " + last;
        
        }
        
        else if (first.equalsIgnoreCase("is")){
            question = first + " your country " + last;
        }
        
        else if (first.equalsIgnoreCase("in")) {
            
            question = "Is the country " + first + last;
           
        }
        
        else if (first.equalsIgnoreCase("border")){
            
            question = "Does the country " + first + last;
        }
        
        else if (first.equalsIgnoreCase("capital")){
            question = "Is the " + first + " of your country " + last;
            
        }
        
    }
    
    public void askQuestion(){
        System.out.println(question);
    }
    
}
