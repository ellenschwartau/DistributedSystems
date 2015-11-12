package de.fhwedel.ueb03;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class XmlRpc {

    private static List<String> records = new ArrayList<String>();

    public static void getRecord(PrintStream out, int index) {
        if (index < records.size()) {
            out.print(records.get(index).toCharArray());
        } else {
            out.print(new String("No entry with index " + index).toCharArray());
        }
        out.flush();
    }

    public static void addRecord(int i, String record, PrintStream out) {
        if (i <= records.size()) {
            records.add(i, record);
            out.print(new String("Record saved at index: " + i).toCharArray());
        } else {
            out.print(new String("Index out of bound").toCharArray());
        }
        out.flush();
    }

    public static void getSize(PrintStream out) {
        out.print(new String("Saved Records" + records.size()).toCharArray());
    }

}
