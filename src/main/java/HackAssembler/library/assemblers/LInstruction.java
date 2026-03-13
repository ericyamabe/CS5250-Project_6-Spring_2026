package HackAssembler.library.assemblers;

import java.util.HashMap;
import java.util.Map;

public class LInstruction extends Instruction {
    private static LInstruction instance;
    Map<String, String> labelMap = new HashMap<>();
    private String label;

    private LInstruction() {
        labelMap.put("SP", "0000000000000000");
        labelMap.put("LCL", "0000000000000001");
        labelMap.put("ARG", "0000000000000010");
        labelMap.put("THIS", "0000000000000011");
        labelMap.put("THAT", "0000000000000100");
        labelMap.put("R0", "0000000000000000");
        labelMap.put("R1", "0000000000000001");
        labelMap.put("R2", "0000000000000010");
        labelMap.put("R3", "0000000000000011");
        labelMap.put("R4", "0000000000000100");
        labelMap.put("R5", "00000000000000101");
        labelMap.put("R6", "00000000000000110");
        labelMap.put("R7", "00000000000000111");
        labelMap.put("R8", "00000000000001000");
        labelMap.put("R9", "00000000000001001");
        labelMap.put("R10", "00000000000001010");
        labelMap.put("R11", "00000000000001011");
        labelMap.put("R12", "00000000000001100");
        labelMap.put("R13", "00000000000001101");
        labelMap.put("R14", "00000000000001110");
        labelMap.put("R15", "00000000000001111");
        labelMap.put("SCREEN", "0100000000000000");
        labelMap.put("KBD", "0110000000000000");
        labelMap.put("END", "0000000000000000");
    }

    public static LInstruction getInstance() {
        if (instance == null) {
            instance = new LInstruction();
        }
        return instance;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean isReservedLabel(String label) {
        return this.labelMap.containsKey(label);
    }

    @Override
    public String convert(String instruction) {
        instruction = instruction.replace("(", "").replace(")", "").replace("@", "").strip();
        String convertedInstruction = "";

        if(this.labelMap.containsKey(this.label)) {
            convertedInstruction = this.labelMap.get(this.label);
        } else {
            if(this.label != null) {
                this.labelMap.put(this.label, this.labelMap.get(instruction));
            }

            convertedInstruction = this.labelMap.get(instruction);
            this.labelMap.put(this.label, convertedInstruction);
        }

        return convertedInstruction;
    }
}
