package HackAssembler.library.assemblers;

public abstract class Instruction {
    public abstract String convert(String instruction);
    public abstract void setLabel(String label);
    public abstract Boolean isReservedLabel(String sanitizedLabel);
}
