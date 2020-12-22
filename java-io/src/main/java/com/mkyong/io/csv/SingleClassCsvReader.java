package com.mkyong.io.csv;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class SingleClassCsvReader {

    public static final char LF = '\n';
    public static final char CR = '\r';
    public static final char COMMA = ',';
    public static final char DOUBLE_QUOTE = '"';

    /**
     * Core implementation for reading csv-values from a {@link java.io.Reader}
     * @param reader Source for reading.
     * @param delimiter Delimiter character. Default is a comma(,).
     * @param quote Quote character. Default is a double quote char(").
     * @return List of read Csv lines.
     * @throws Exception If an error happens whilst reading.
     */
    public static List<List<String>> read(Reader reader, char delimiter, char quote) throws Exception{
        if(reader == null){
            throw new Exception("missing reader",
                    new IllegalArgumentException(new NullPointerException("reader")));
        }
        if(delimiter == ' '){
            delimiter = COMMA;
        }
        if(quote == ' '){
            quote = DOUBLE_QUOTE;
        }
        BufferedReader buff = null;
        try {
            //Create an underlying reader to keep original reader's reference
            buff = new BufferedReader(reader);

            List<List<String>>  csv = new LinkedList<List<String>>();
            String line = null;
            while((line = buff.readLine()) != null){
                csv.add(readLine(line, delimiter, quote));
            }
            return csv;
        }catch(Exception e){
            throw e;
        }finally{
            if(buff != null){
                try{
                    buff.close();
                    buff = null;
                }catch(Exception e){
                    throw e;
                }
            }
        }
    }

    /**
     * Core implementation for reading csv-values within text line.
     * @param line Csv text line
     * @param delimiter Delimiter character. Default is a comma(,).
     * @param quote Quote character. Default is a double quote char(").
     * @return List of read Csv values.
     */
    public static List<String> readLine(String line, char delimiter, char quote) {
        if(line == null)
            line = "";
        if(delimiter == ' ')
            delimiter = COMMA;
        if(quote == ' ')
            quote = DOUBLE_QUOTE;

        List<String> values = new LinkedList<String>();
        char[] chars = line.toCharArray();

        StringBuffer value = new StringBuffer("");
        boolean quoted = false;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(CR == c)
                continue;

            if(LF == c) {
                values.add(value.toString());
                value = new StringBuffer();
                break;
            }

            if(quoted) {
                if(quote == c) {
                    if((i+1) < chars.length && chars[i+1] == quote) {
                        value.append(quote);
                        i++;
                    }else {
                        quoted = false;
                    }
                }else if(delimiter == c) {
                    value.append(c);
                }else {
                    value.append(c);
                }
            }else {
                if(quote == c) {
                    quoted = true;
                    if(
                            ((i+1) < chars.length && chars[i+1] == delimiter) &&
                                    ((i-1) >= 0   && chars[i-1] != delimiter)
                    ) {
                        quoted = false;
                        value.append(c);
                    }

                } else if(delimiter == c) {
                    values.add(value.toString());
                    value = new StringBuffer();
                } else{
                    value.append(c);
                }
            }

        }

        if(value.length()>0) {
            values.add(value.toString());
        }

        return values;
    }

}
