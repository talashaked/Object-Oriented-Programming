package filesprocessing;

import filesprocessing.Exceptions.badInputException;
import filesprocessing.Exceptions.badSubsectionNameException;

import java.io.*;
import java.util.*;

public class DirectoryProcessor {
    ArrayList<String> commandFileAsStrings;
    File[] currentDirectory;

    /**
     * this directory processor is a class, receiving a array of files(which is as directory),
     * and a string leading to a command file.
     * if the command file isn't even a file an IO exception is thrown.
     * @throws IOException;
     */
    DirectoryProcessor(String commandF, File[] directory) throws IOException {
        BufferedReader tempCommandFile = checkCommandFile(commandF);
        this.commandFileAsStrings = commandFileConverter(tempCommandFile);
        this.currentDirectory = directory;
    }

    /**
     * this function checks the command file, and if valid creates a buffered reader out of it.
     */
    private BufferedReader checkCommandFile(String commandFile) throws FileNotFoundException{
        Reader file;
        file = new FileReader(commandFile);
        return new BufferedReader(file);
    }

    /**
     * this function converts the command file to a array list of strings.
     * @param tempCommandFile - the buffered reader command file
     * @return - the array list of the command file lines as strings
     * @throws IOException - if there was a problem with the command file
     */
    private ArrayList<String> commandFileConverter(BufferedReader tempCommandFile) throws IOException {
        ArrayList<String> commandFileStringLines = new ArrayList<>();
        String result;
        while ((result=tempCommandFile.readLine()) != null) {
            commandFileStringLines.add(result);
        }
        return commandFileStringLines;
    }

    /**
     * this function runs a parser on the command file arrayList, and then on processes each section
     * that is returns.
     * @throws badSubsectionNameException - if a bad section was given in the command file, an error
     * is raised.
     */
    public void processCommandonDirectory() throws badSubsectionNameException {
        commandFileParser parser = new commandFileParser(this.commandFileAsStrings);
        ArrayList<sectionProcessor> sectionArr = parser.commandFileSectionCreator();
        for(sectionProcessor section: sectionArr){
            section.process(this.currentDirectory);
        }
    }




public static void main(String[] args) {
    final String msg1 = "There is a bad subsection name in the command file. Please fix it, and run the program again.";
    final String msg2 = "the command file is unaccessable. Please fix this problem, then run again.";
    final String msg3 = "bad args given";
    try{
        if (args.length==2) {
            File dir = new File(args[0]);
            File[] directory = dir.listFiles();
            DirectoryProcessor proccessor = new DirectoryProcessor(args[1], directory);
            proccessor.processCommandonDirectory();
        }
        else{
            throw new badInputException();
        }
    }
    catch (badSubsectionNameException e1){
        System.err.println("ERROR: "+msg1);
    }
    catch (IOException error2){
        System.err.println("ERROR: "+msg2);
    }
    catch (badInputException e){
        System.err.println("ERROR: "+msg3);
    }
}
}
