package it.unimi.di.sweng.lab03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForthInterpreter implements Interpreter {
    List<Integer> stack = new ArrayList<>();
    Map<String, String> customCommands = new HashMap<>();

    @Override
    public void input(String program) throws IllegalArgumentException {
        if (program.isEmpty()) return;
        String[] values = program.split("\\s+|\\n");

        for(int i = 0; i <= values.length - 1; i++){
            String value = values[i];

            if (value.equals("+")){
                somma();
            } else if (value.equals("*")) {
                moltiplica();
            } else if (value.equals("-")) {
                sottrai();
            } else if (value.equals("/")) {
                dividi();
            } else if (value.equals("dup")) {
                duplica();
            } else if (value.equals("swap")) {
                swap();
            } else if (value.equals("drop")) {
                drop();
            } else if (value.equals(":")) {
                StringBuilder command = new StringBuilder();
                String commandName = values[i+1];
                int j = i+2;
                while (!values[j].equals(";")) {
                    command.append(values[j]).append(" ");
                    j++;
                }
                customCommands.put(commandName, command.toString());
                i = j;
            } else if (customCommands.containsKey(value)) {
                String command = customCommands.get(value);
                this.input(command);
            } else if (value.matches("\\d+")) {
                stack.add(Integer.parseInt(value));
            } else {
                throw new IllegalArgumentException("Token error: " + value);
            }
        }
    }

    private void drop() {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Stack Underflow");
        }
        stack.remove(stack.size() -1);
    }

    private void swap() {
        if (stack.size()<2) {
            throw new IllegalArgumentException("Stack Underflow");
        }
        Integer a = stack.remove(stack.size()-1);
        Integer b = stack.remove(stack.size()-1);
        stack.add(a);
        stack.add(b);
    }

    private void duplica() {
        if (stack.isEmpty()) return;
        Integer top = stack.get(stack.size()-1);
        stack.add(top);
    }

    private void dividi() {
        if (stack.size()<2) {
            throw new IllegalArgumentException("Stack Underflow");
        }
        Integer a = stack.remove(stack.size()-1);
        Integer b = stack.remove(stack.size()-1);
        stack.add((b/a));
    }

    private void sottrai() {
        if (stack.size()<2) {
            throw new IllegalArgumentException("Stack Underflow");
        }
        Integer a = stack.remove(stack.size()-1);
        Integer b = stack.remove(stack.size()-1);
        stack.add((b-a));
    }

    private void moltiplica() {
        if (stack.size()<2) {
            throw new IllegalArgumentException("Stack Underflow");
        }
        Integer a = stack.remove(stack.size()-1);
        Integer b = stack.remove(stack.size()-1);
        stack.add((a*b));
    }

    private void somma() {
        if (stack.size()<2) {
            throw new IllegalArgumentException("Stack Underflow");
        }
        Integer a = stack.remove(stack.size()-1);
        Integer b = stack.remove(stack.size()-1);
        stack.add((a+b));
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Integer value: stack){
            result.append(value);
            result.append(" ");
        }
        result.append("<- Top");
        return result.toString();
    }
}
