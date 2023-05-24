import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void addStudentData(String[] studentData, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename, true);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeNext(studentData);
        csvWriter.close();
    }

    public static void addingBatchStudents(String fileName) throws IOException, CsvValidationException {
        FileReader fileReader = new FileReader("/app/data/batch/" + fileName);
        CSVReader reader = new CSVReader(fileReader);
        CSVWriter writer = new CSVWriter(new FileWriter("/app/database/data.csv", true));

        String[] line;
        while ((line = reader.readNext()) != null) {
            writer.writeNext(line);
        }

        reader.close();
        writer.close();
    }

    public static void main(String[] args) throws IOException, CsvValidationException {

        System.out.println("1- Add student data\n" + "2- Add batch students data");
        Scanner input = new Scanner(System.in);

        if (input.hasNextInt()) {
            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            String filename = "/app/database/data.csv";
            System.setProperty("au.com.bytecode.opencsv.separator", ";");

            if (choice == 1) {
                System.out.print("Enter the name: ");
                String name = input.nextLine();

                System.out.print("Enter the ID: ");
                String id = input.nextLine();

                System.out.print("Enter the courses: ");
                String courses = input.nextLine();

                String[] studentData = {name, id, courses};
                addStudentData(studentData, filename);
            } else if (choice == 2) {
                File directory = new File("/app/data/batch/");
                File[] fileList = directory.listFiles();
                for (File file : fileList) {
                    if (file.isFile() && file.getName().contains("_verified.csv")) {
                        System.out.println(file.getName());
                    }
                }

                System.out.print("Enter the file name: ");
                String fileName = input.nextLine();
                addingBatchStudents(fileName);
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}
