package HackAssembler.library.assemblers;

import HackAssembler.library.AssemblerFactory;

import java.util.HashMap;
import java.util.Map;

public class CInstruction extends Instruction {
    private static CInstruction instance;
    Map<String, String> destinationMap = new HashMap<>();
    Map<String, String> jumpMap = new HashMap<>();
    Map<String, String> instructionMap = new HashMap<>();
    String instruction;

    private CInstruction() {
        destinationMap.put("0", "000");
        destinationMap.put("null", "000");
        destinationMap.put("M", "001");
        destinationMap.put("D", "010");
        destinationMap.put("MD", "011");
        destinationMap.put("A", "100");
        destinationMap.put("AM", "101");
        destinationMap.put("AD", "110");
        destinationMap.put("AMD", "111");

        jumpMap.put("null", "000");
        jumpMap.put("JGT", "001");
        jumpMap.put("JEQ", "010");
        jumpMap.put("JGE", "011");
        jumpMap.put("JLT", "100");
        jumpMap.put("JNE", "101");
        jumpMap.put("JLE", "110");
        jumpMap.put("JMP", "111");

        instructionMap.put("0", "101010");
        instructionMap.put("1", "111111");
        instructionMap.put("-1", "111010");
        instructionMap.put("D", "001100");
        instructionMap.put("A", "110000");
        instructionMap.put("!D", "001101");
        instructionMap.put("!A", "110001");
        instructionMap.put("-D", "001111");
        instructionMap.put("-A", "110011");
        instructionMap.put("D+1", "011111");
        instructionMap.put("A+1", "110111");
        instructionMap.put("D-1", "001110");
        instructionMap.put("A-1", "110010");
        instructionMap.put("D+A", "000010");
        instructionMap.put("D-A", "010011");
        instructionMap.put("A-D", "000111");
        instructionMap.put("D&A", "000000");
        instructionMap.put("D|A", "010101");


    }

    public static CInstruction getInstance() {
        if (instance == null) {
            instance = new CInstruction();
        }
        return instance;
    }

    @Override
    public String convert(String instruction) {
        this.instruction = instruction;
        String convertedInstruction = "";

        if(instruction.contains("=")) {
            convertedInstruction = this.convertDestinationInstruction();
        } else if(instruction.contains(";")) {
            convertedInstruction = this.convertJumpInstruction();
        }

        return convertedInstruction;
    }

    private String convertDestinationInstruction() {
        String[] parts = instruction.split("=");
        String destination = "";
        String cInstructuction = "";
        String a = this.setA();
        destination = this.convertDestination(parts[0]);

        if(parts.length == 2) {
            if(a == "1") {
                parts[1] = parts[1].replace("M", "A");
            }
            cInstructuction = this.convertInstruction(parts[1]);
        }

        String fullInstruction = "111" + a + cInstructuction + destination + "000";
        return fullInstruction;
    }

    private String convertJumpInstruction() {
        String[] parts = this.instruction.split(";");
        String destination = "";
        String jump = "";
        String a = this.setA();
        destination = this.convertDestination(parts[0].trim());

        if(parts.length == 2) {
            jump = this.convertJump(parts[1].trim());
        }

        String fullInstruction = "111" + a + "000000" + destination + jump;
        return fullInstruction;
    }

    private String convertDestination(String parts) {
        return this.destinationMap.get(parts.trim());
    }

    private String convertInstruction(String parts) {
        return this.instructionMap.get(parts.trim());
    }

    private String convertJump(String parts) {
        return this.jumpMap.get(parts.trim());
    }

    private String setA() {
        if(instruction.contains("M")) {
            return "1";
        }

        return "0";
    }

    @Override
    public void setLabel(String sanitizedLabel) {}

    @Override
    public Boolean isReservedLabel(String sanitizedLabel) {
        return true;
    }
}
