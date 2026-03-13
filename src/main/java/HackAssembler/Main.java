package HackAssembler;

import HackAssembler.library.AssemblerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AssemblerFactory assembler = new AssemblerFactory();

        if (args.length > 0) {
            String filename = args[0];

            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String binaryCode = assembler.assemble(line);

                    if(binaryCode != null) {
                        if(binaryCode.length() > 0) {
                            System.out.println(binaryCode);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Please enter a file path");
        }
    }
}

