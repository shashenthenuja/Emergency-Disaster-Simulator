package edu.curtin.emergencysim.file;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;

/* *******************************************************************
* File:       End.java
* Author:     G.G.T.Shashen
* Created:    26/06/2022
* Modified:   2/06/2022
* Desc:       File Read class implmentation
***********************************************************************/
public class FileRead {
    private List<DisasterData> list;
    private String fileName;
    private static Logger logger = Logger.getLogger(FileRead.class.getName());

    public FileRead(String fileName) {
        list = new ArrayList<>();
        this.fileName = fileName;
    }

    // Method to read file data from input
    public void readFile() throws FileNotFoundException, IOException, FileReadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.trim().length() > 0) { // ignore blank lines
                    // Check if the input contains regex pattern
                    Matcher m = Pattern.compile("(?<time>[0-9]+) (?<type>fire|flood|chemical) (?<location>.+)")
                            .matcher(line);
                    if (!m.matches()) {
                        logger.warning("File is Invalid!");
                        throw new FileReadException("File is Invalid!");
                    }
                    String time = m.group("time");
                    String type = m.group("type");
                    String location = m.group("location");
                    // Add the read data to the list
                    list.add(new DisasterData(Long.parseLong(time), type, location));
                }
                line = reader.readLine();
            }
        }
        // Check if list is unique and sort the list
        if (isListUnique(list) == false) {
            throw new FileReadException("File is Invalid!");
        } else {
            Collections.sort(list);
            logger.info("File Sorted : time");
        }
    }

    // Method to check if the list values are distinct
    public boolean isListUnique(List<DisasterData> list) {
        boolean unique;
        // Add data to hash set since hashset cannot add duplicates
        HashSet<String> hset = new HashSet<>();
        for (DisasterData data : list) {
            hset.add(data.getType() + data.getLocation());
        }
        // Check if the hashset length == list length
        if (hset.size() == list.size()) {
            unique = true;
        } else {
            unique = false;
            logger.warning("File contains duplicate/invalid data!");
        }
        return unique;
    }

    // Method to get list
    public List<DisasterData> getList() {
        return list;
    }

}
