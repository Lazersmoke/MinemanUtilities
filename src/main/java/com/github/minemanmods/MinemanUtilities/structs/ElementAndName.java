package com.github.minemanmods.MinemanUtilities.structs;

public class ElementAndName<T> {

    private T element;
    private String name;

    public ElementAndName(T element, String name) {
        this.element = element;
        this.name = name;
    }

    public T getElement() {
        return this.element;
    }

    public String getName() {
        return this.name;
    }

}
