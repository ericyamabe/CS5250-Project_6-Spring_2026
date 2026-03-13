package HackAssembler.library.assemblers;

import java.util.HashMap;
import java.util.Map;

public class AInstruction extends Instruction {
    private static AInstruction instance;
    Map<String, String> labelMap = new HashMap<>();

    private AInstruction() {
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
    }

    public static AInstruction getInstance() {
        if (instance == null) {
            instance = new AInstruction();
        }
        return instance;
    }

    @Override
    public String convert(String instruction) {
        String numberString = instruction.replace("@", "").trim();
        String binaryString = "";

        if(this.labelMap.containsKey(numberString)) {
            binaryString = this.labelMap.get(numberString);
        } else {
            try {
                binaryString = this.convertToBinary(Integer.parseInt(numberString));

                if (binaryString.length() > 16) {
                    binaryString = binaryString.substring(binaryString.length() - 16);
                } else {
                    binaryString = String.format("%16s", binaryString).replace(' ', '0');
                }
            } catch (NumberFormatException e) {
                binaryString = "000000000000000";
            }
        }

        return binaryString;
    }

    public String convertToBinary(Integer number) {
        return Integer.toBinaryString(number);
    }

    @Override
    public void setLabel(String sanitizedLabel) {}

    @Override
    public Boolean isReservedLabel(String sanitizedLabel) {
        return true;
    }
}
