package Question;

import java.io.*;
import java.util.Scanner;
public class InputOutput
{
    static Scanner takeInput = new Scanner(System.in);
    static Integer numberOfPeople;
    public static void writeIntoInputFile(File input)
    {
        String name;
        Integer age;
        System.out.println("How many person data you want to enter in your file?");
        numberOfPeople = takeInput.nextInt();
        try
        {
            FileWriter inputFileWriter = new FileWriter(input);
            //entering data into input file
            for (Integer counter = 1; counter <= numberOfPeople; counter++)
            {
                System.out.println("Enter the name of person" + counter);
                name = takeInput.nextLine();
                name = takeInput.nextLine();
                System.out.println("Enter the age of person" + counter);
                age = takeInput.nextInt();
                inputFileWriter.write(name + ", " + age + "\n");
            }
            inputFileWriter.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File does not exists!");
        }
        catch (IOException exception)
        {
            System.out.println("IO exception occurred!");
        }

    }

    public static void writeToOutputFile(File input, File output)
    {
        try
        {
            FileReader inputFileReader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputFileReader);
            FileWriter outputFileWriter = new FileWriter(output);
            String details;
            Integer totalAge = 0;
            /*in the loop given below i am reading from input file and after performing the manipulations adding
            data to the output file*/
            while ((details = bufferedReader.readLine()) != null)
            {
                String detailsOfPerson[] = details.split(","); //splits the string arount , and store into array
                String nameOfperson = detailsOfPerson[0].trim(); // trims to seperate name and age
                Integer ageOfperson = Integer.parseInt(detailsOfPerson[1].trim());
                totalAge += ageOfperson;
                outputFileWriter.write(nameOfperson + " (" + ageOfperson + ")\n");
            }
            //calculating average age
            Double averageAge = (double) (totalAge / numberOfPeople);
            System.out.println("The average age of all the people is: " + averageAge);
            outputFileWriter.write("The average age of all the people is: " + averageAge+"\n");
            outputFileWriter.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("File does not exists!");
        }
        catch (IOException exception)
        {
            System.out.println("IO exception occurred!");
        }
    }
    public static void main(String[] args)
    {
        System.out.println("enter the name of input file");
        String inputFile = takeInput.nextLine();
        System.out.println("enter the name of output file");
        String outputFile = takeInput.nextLine();
        File input = new File(inputFile);
        File output = new File(outputFile);
        try
        {
            input.createNewFile();
            if (output.exists())
            {
                System.out.println("file already exists!! Do you want to override it(1/0)?");
                Integer permission = takeInput.nextInt();
                if (permission == 1)
                {
                    output.createNewFile();
                }
                else if (permission == 0)
                {
                    System.out.println("Do not override the output file!!");
                }
            }
            else
            {
                output.createNewFile();
            }
        }
        catch (IOException e)
        {
            System.out.println("We could not complete file creation due to an abnormal condition occurred!");
        }
        writeIntoInputFile(input);
        writeToOutputFile(input, output);
    }
}
