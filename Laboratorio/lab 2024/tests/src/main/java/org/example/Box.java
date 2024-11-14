package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private List<String> boxContent;

    public Box() { }

    public void setContentList(List<String> content) {
        this.boxContent = content;
    }

    @Override
    public String toString() {
        return boxContent.toString();
    }
}