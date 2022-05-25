package com.program3.FileManager;

public interface Exporter<T>{
    void exportData(T object);
    boolean save = true;
}

