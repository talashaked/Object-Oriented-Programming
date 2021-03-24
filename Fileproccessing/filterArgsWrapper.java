package filesprocessing;
public class filterArgsWrapper {
    private boolean bool;
    private String str;
    private double mainVal;
    private double firstVal;
    private double secondVal;
    private boolean isNot;

    /**
     * this wrapper, wraps all values given to a filter method in a single object.
     * all filter methods uses this object inorder to check that the values given to them
     * are valid.
     */
    public filterArgsWrapper(){
        this.isNot = false; //by default, is not isn't called
    }

    /**
     * set the main val (double) of the wrapper.
     * @param mainval
     */
    public void setMainVal(double mainval){
        this.mainVal = mainval;
    }
    /**
     * set the boolean val of the wrapper.
     * @param bool1;
     */
    public void setBool(boolean bool1){
        this.bool = bool1;
    }

    /**
     * set two parameters for the wrapper - the first and the second val (two of them doubles).
     * @param val1;
     * @param val2;
     */
    public void setTwoVals(double val1, double val2){
        this.firstVal=val1;
        this.secondVal = val2;
    }
    /**
     * set the string of the wrapper.
     */
    public void setStr(String str1){
        this.str = str1;
    }

    /**
     * get the main value of the wrapper
     */
    public double getMainVal(){
        return this.mainVal;
    }

    /**
     * @return the firstVal attribute of the wrapper
     */
    public double getFirstVal(){
        return this.firstVal;
    }
    /**
     * @return the second double attribute of the wrapper
     */
    public double getSecondVal(){
        return this.secondVal;
    }
    /**
     * @return the string attribute of the wrapper
     */
    public String getStr(){
        return this.str;
    }

    /**
     * returns the boolean attribute of the wrapper
     */
    public boolean getBool(){
        return this.bool;
    }

    /**
     * changes the IsNot value to true.
     */
    public void setIsNot(){
        this.isNot = true;
    }

    /**
     * @return - returns the boolean value of is not.
     */
    public boolean getIsNot(){
        return this.isNot;
    }
}
