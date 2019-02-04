package com.zaleskix.app.models;

public class Instruction {

    private InstructionsKeywords instruction;
    private double number;

    public Instruction() {
    }

    public Instruction(InstructionsKeywords instruction, double number) {
        this.instruction = instruction;
        this.number = number;
    }

    public InstructionsKeywords getInstruction() {
        return instruction;
    }

    public void setInstruction(InstructionsKeywords instruction) {
        this.instruction = instruction;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }
}
