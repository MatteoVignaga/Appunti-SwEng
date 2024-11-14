package it.unimi.di.sweng.lab03;

import java.util.ArrayList;
import java.util.List;

public class ForthInterpreter implements Interpreter {
    List<Integer> stack = new ArrayList<>();

    @Override
    public void input(String program) throws IllegalArgumentException {
        if (program.isEmpty()) return;
        String[] tokens = program.split("\\s+|\\n");

        for (String token : tokens) {
            if (token.equals("+")) {
                somma();
            } else if (token.equals("*")) {
                moltiplica();
            } else if (token.equals("-")) {
                sottrai();
            } else if (token.equals("/")) {
                dividi();
            } else if (token.matches("\\d+")) {
                stack.add(Integer.parseInt(token));
            } else if (token.equals("dup")) {
                dup();
            } else if (token.equals("swap")) {
                swap();
            } else if (token.equals("drop")) {
                drop();
            } else if (token.length() > 1) {
                throw new IllegalArgumentException("Token error '" + token + "'");
            }
        }

    }

    private void drop() {
        if (stack.isEmpty()) throw new IllegalArgumentException("Stack Underflow");
        stack.remove(stack.size()-1);
    }

    private void swap() {
        if (stack.size() < 2) throw new IllegalArgumentException("Stack Underflow");
        Integer top = stack.remove(stack.size()-1);
        Integer second = stack.remove(stack.size()-1);
        stack.add(top);
        stack.add(second);
    }

    private void dup() {
        if (stack.isEmpty()) throw new IllegalArgumentException("Stack Underflow");
        stack.add(stack.get(stack.size()-1));
    }

    private void dividi() {
        if (stack.size() < 2) throw new IllegalArgumentException("Stack Underflow");
        Integer e1 = stack.remove(stack.size()-1);
        Integer e2 = stack.remove(stack.size()-1);
        stack.add(e2 / e1);
    }

    private void sottrai() {
        if (stack.size() < 2) throw new IllegalArgumentException("Stack Underflow");
        Integer e1 = stack.remove(stack.size()-1);
        Integer e2 = stack.remove(stack.size()-1);
        stack.add(e2 - e1);
    }

    private void moltiplica() {
        if (stack.size() < 2) throw new IllegalArgumentException("Stack Underflow");
        Integer e1 = stack.remove(stack.size()-1);
        Integer e2 = stack.remove(stack.size()-1);
        stack.add(e1 * e2);
    }

    private void somma() {
        if (stack.size() < 2) throw new IllegalArgumentException("Stack Underflow");
        Integer e1 = stack.remove(stack.size()-1);
        Integer e2 = stack.remove(stack.size()-1);
        stack.add(e1 + e2);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Integer n : stack) {
            builder.append(n);
            builder.append(" ");
        }
        builder.append("<- Top");
        return builder.toString();
    }
}
