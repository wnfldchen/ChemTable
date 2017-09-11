package com.gmail.wnfldchen.chemtable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Winfield on 2017-04-06.
 */
public class csvIO {
    InputStream iStream;

    public csvIO(InputStream iStream){
        this.iStream = iStream;
    }

    public ArrayList<String[]> read(){
        ArrayList<String[]> resultList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                ArrayList<String> rowproc = new ArrayList<String>();
                for (String entry:row
                     ) {
                    rowproc.add(entry.replaceAll("\"",""));
                }
                resultList.add(rowproc.toArray(new String[rowproc.size()]));
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                iStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}
