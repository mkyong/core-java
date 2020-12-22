package com.mkyong.io.csv;

import java.io.File;
import java.util.List;

public interface CsvFileReader {
    List<String[]> read(File file) throws Exception;
}