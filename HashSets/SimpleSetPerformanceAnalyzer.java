
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {
    SimpleSet[] collectionSets;
    private static String[] collectionNames = {"Open HashSet", "Closed HashSet", "TreeSet", "LinkedList", "HashSet"};
    private static String[] dataset1 = Ex4Utils.file2array
            ("C:\\Users\\talas\\IdeaProjects\\ex04-fortest\\src\\data1.txt");
    private static String[] dataset2 = Ex4Utils.file2array
            ("C:\\Users\\talas\\IdeaProjects\\ex04-fortest\\src\\data2.txt");
    private static String[] strSet1 = {"hi", "-13170890158"};
    private static String[] strSet2 = {"23", "hi"};

    /**
     * initializes all the simplesets contained in the simples sets array, in order to do tests on 5 different
     * simpleSets
     */
    public void Initializer() {
        this.collectionSets = new SimpleSet[5];
        this.collectionSets[0] = new OpenHashSet();
        this.collectionSets[1] = new ClosedHashSet();
        this.collectionSets[2] = new CollectionFacadeSet(new TreeSet<String>());
        this.collectionSets[3] = new CollectionFacadeSet(new LinkedList<String>());
        this.collectionSets[4] = new CollectionFacadeSet(new HashSet<String>());
    }

    /**
     * this test adds datasets to each collection and prints the duration for it
     * @param dataset - the dataset to add
     */
    public void addingDataSetsTime(int dataset) {
        if (dataset == 1) {
            this.addingToSet(dataset1);

        } else {
            this.addingToSet(dataset2);
        }

    }

    /**
     * this function does the actual adding of all the items to each simpleSet
     * @param pointer - the dataSet relevant
     */
    private void addingToSet(String[] pointer) {
        int i = 0;
        for (SimpleSet curSet : this.collectionSets) {
            long startTime = System.nanoTime() / 1000000;
            for (String word : pointer) {
                curSet.add(word);
            }
            System.out.println("dataset: " + datasetsName(pointer) + " collection type: " + collectionNames[i] +
                    "time duration to add: " + ((System.nanoTime() / 1000000) - startTime) + " ms");
            i++;
        }
    }

    /**
     * test a set of words whether they are contained in a specific collection
     * @param datasetInitialized - which dataset was initialized so that we will know what set of strings
     *                           to run the test on.(optional 1/2)
     * @param collection - the collection to run the test on. 1- openHashSet,2- closedHashSet,3- TreeSet,
     *                   4 - LinkedList, 5- HashSet
     */
    public void containTester(int datasetInitialized, int collection) {
        if (collection == 3) {
            if (datasetInitialized == 1) {
                containRunForLinkedList(strSet1);
            } else {
                containRunForLinkedList(strSet2);
            }
        } else {
            if (datasetInitialized == 1) {
                containRunner(strSet1, collectionSets[collection]);
            } else {
                containRunner(strSet2, collectionSets[collection]);
            }
        }
    }

    /**
     * given a string list and a collection, this test measures how much times will it take to run
     * 70000 times the contain function for a specific string. prints results as messages.
     * @param strListToCheck - the string list to check the contains on
     * @param collection - the collection to run the tests on
     */
    private void containRunner(String[] strListToCheck, SimpleSet collection) {
        long startTime = 0;
        for (String word : strListToCheck) {
            for (int i = 0; i < 140000; i++) {
                if (i == 70000) {
                    startTime = System.nanoTime();
                }
                collection.contains(word);
            }
            System.out.println("test for word: " + word + " in " + collectionsName(collection) + ": "
                    + (((System.nanoTime() - startTime)) / 70000) + " ns");
        }
    }

    /**
     * @param pointer - the dataset
     * @return - returns a string of the dataset for the print
     */
    private String datasetsName(String[] pointer){
        if (pointer==dataset1){
            return "dataset1";
        }
        else{
            return "dataset2";
        }
    }

    /**
     * @param collection - the collection relevant
     * @return - returns a string of the collection name for the print
     */
    private String collectionsName(SimpleSet collection){
        for(int i=0; i<5;i++){
            if(collectionSets[i]==collection){
                return collectionNames[i];
            }
        }
    return "";
    }

    /**
     * checks on the linkes list, the runtime of the contain function for a specific set of strings.
     * @param strListToCheck - which string set to check this on, decided by the dataset the linked
     *                       list was initialized with
     */
    private void containRunForLinkedList(String[] strListToCheck) {
        for (String word : strListToCheck) {
            long startTime = System.nanoTime();
            for (int i = 0; i < 70000; i++) {
                this.collectionSets[3].contains(word);

            }
            System.out.println("test for word: " + word + " in " + collectionNames[3] + ": "
                    + (((System.nanoTime() - startTime)) / 70000) + "ns");
        }
    }

    /**
     * the main func, calls the tests.
     * @param args
     */
    public static void main(String[] args) {
        SimpleSetPerformanceAnalyzer tester = new SimpleSetPerformanceAnalyzer();
        tester.Initializer();
        tester.addingDataSetsTime(1);
        for (int i = 0; i < 5; i++) {
            tester.containTester(1, i);
        }
        tester.Initializer();
        tester.addingDataSetsTime(2);
        for (int i = 0; i < 5; i++) {
            tester.containTester(2, i);
        }
    }
}

