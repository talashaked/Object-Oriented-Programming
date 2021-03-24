package filesprocessing;

import filesprocessing.Exceptions.ValueException;

public class orderArgsWrapper {
    final String reverseString = "REVERSE";
    private boolean isReversed;
    private String orderType;

    /**
     * this constructor created a wrapper, and checks which values where given.
     * @param words - the string values given to convert into the wrapper values.
     * @throws ValueException - if the values don't fir the requirements
     */
    public orderArgsWrapper(String[] words) throws ValueException{
        int wordsLength = words.length;
        if(wordsLength==2) {
            if (words[1].equals(reverseString)) {
                this.isReversed = true;
                wordsLength--;
            }
        }
        if (wordsLength==1){
            this.orderType=words[0];
        }
        else{
            throw new ValueException();
        }

    }

    /**
     * @return - the string of the order
     */
    public String getOrder(){
        return this.orderType;
    }

    /**
     * @return - if in the order requirements reversed was required.
     */
    public boolean getIsReversed(){
        return this.isReversed;
    }
}
