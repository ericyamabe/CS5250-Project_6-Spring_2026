package HackAssembler.library;

import HackAssembler.library.assemblers.AInstruction;
import HackAssembler.library.assemblers.CInstruction;
import HackAssembler.library.assemblers.Instruction;
import HackAssembler.library.assemblers.LInstruction;

public class AssemblerFactory {
    private String label = "";

    public String assemble(String instruction) {
        String instructionType = this.getInstructionType(instruction);
        Instruction instruct = null;

        switch (instructionType) {
            case "A_INSTRUCTION":
                instruct = AInstruction.getInstance();
                break;
            case "L_INSTRUCTION":
                instruct = LInstruction.getInstance();
                if(!this.label.isEmpty()) {
                    instruct = LInstruction.getInstance();
                    instruct.setLabel(this.label);
                    this.label = "";
                } else {
                    String sanitizedLabel = instruction.replace("(", "").replace(")", "");

                    if(instruct.isReservedLabel(sanitizedLabel)) {
                        instruct.setLabel(sanitizedLabel);
                    } else {
                        this.label = sanitizedLabel;
                    }
                }
                break;
            case "C_INSTRUCTION" :
                instruct = CInstruction.getInstance();
                break;
            default:
                break;
        }

        if(instruct != null) {
            String Output = instruct.convert(instruction);
            return Output;
        }

        return "";
    }

    public String getInstructionType(String instruction) {
        if (instruction.contains("//")) {
            return "COMMENT";
        } else if (instruction.contains("(") || !this.label.isEmpty()) {
            return "L_INSTRUCTION";
        } else if (instruction.contains("@") && this.label.isEmpty()) {
            return "A_INSTRUCTION";
        } else {
            return "C_INSTRUCTION";
        }
    }
}
