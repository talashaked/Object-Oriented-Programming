public abstract class SimpleHashSet implements SimpleSet {
    protected static float DEFAULT_HIGHER_CAPACITY = 0.75f;
    protected static float DEFAULT_LOWER_CAPACITY = 0.25f;
    protected static int INITIAL_CAPACITY = 16;
    protected float higherCapacity;
    protected float lowerCapacity;

    /**
     *
     */
    protected SimpleHashSet(){
        this.higherCapacity = DEFAULT_HIGHER_CAPACITY;
        this.lowerCapacity = DEFAULT_LOWER_CAPACITY;
    }

    /**
     *
     * @param upperLoadFactor
     * @param lowerLoadFactor
     */
    protected SimpleHashSet(float upperLoadFactor,float lowerLoadFactor){
        this.higherCapacity = upperLoadFactor;
        this.lowerCapacity = lowerLoadFactor;
    }
    /*
    returns the current capacity of the table
     */
    public abstract int capacity();

    protected int clamp(int index){
        return index & (this.capacity()-1);
    }

    protected float getLowerLoadFactor(){
        return this.lowerCapacity;
    }

    protected float getUpperLoadFactor(){
        return this.higherCapacity;
    }

}
