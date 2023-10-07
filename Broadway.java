import java.io.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.text.NumberFormat;
import java.util.TreeMap;
public class Broadway {
    public Broadway(){
        File file = new File("/Users/arnavgoel/IdeaProjects/BroadwayShows/src/Broadway2022.csv");
        ArrayList<Show> shows = new ArrayList<Show>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            boolean first = true;
            while ((text = reader.readLine()) != null) {
                if(first){
                    first = false;
                }
                else{
                    //System.out.println(text);
                    String[] line = text.split(",");
                    String theDate = line[0];
                    String theName = line[1];
                    String theType = line[2];
                    String theTheatre = line[3];
                    int theGross = Integer.parseInt(line[4]);
                    int theAttendance = Integer.parseInt(line[5]);
                    double thePCap = Double.parseDouble(line[6]);
                    Show show = new Show(theDate, theName, theType, theTheatre, theGross, theAttendance, thePCap);
                    shows.add(show);
                }
            }
            //System.out.println(shows);
            grossByMonth(shows);
            grossByType(shows);
            attendanceByType(shows);
            grossByShowPerWeek(shows);
            attendanceByShowPerWeek(shows);
            grossByTheatre(shows);
            attendanceByMonth(shows);
            attendanceByTheatre(shows);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public static void grossByMonth(ArrayList<Show> shows){
        TreeMap<Integer,Long> map = new TreeMap<>();
        for (Show show : shows) {
            int month = show.getMonth();
            long gross = show.getGrossAmt();
            if (!map.containsKey(month))
                map.put(month, gross);
            else map.put(month, map.get(month) + gross);
        }
        System.out.printf("%-20s%s%n", "Month", "Gross Income");
        for(int month: map.keySet()){
            long amount = map.get(month);
            Locale current = Locale.getDefault();
            NumberFormat format = NumberFormat.getCurrencyInstance(current);
            System.out.printf("%-20s%s%n", month, format.format(amount));
        }
    }

    public static void grossByType(ArrayList<Show> shows){
        TreeMap<String,Long> map = new TreeMap<>();
        for (Show show : shows) {
            String type = show.getType();
            long gross = show.getGrossAmt();
            if (!map.containsKey(type))
                map.put(type, gross);
            else map.put(type, map.get(type) + gross);
        }
        System.out.println("\n" + String.format("%-20s%s", "Type", "Gross Income"));
        for(String type: map.keySet()){
            long amount = map.get(type);
            Locale current = Locale.getDefault();
            NumberFormat format = NumberFormat.getCurrencyInstance(current);
            System.out.printf("%-20s%s%n", type, format.format(amount));
        }
    }

    public static void attendanceByType(ArrayList<Show> shows){
        TreeMap<String,Integer> map = new TreeMap<>();
        for (Show show : shows) {
            String type = show.getType();
            int attendance = show.getAttendance();
            if (!map.containsKey(type))
                map.put(type, attendance);
            else map.put(type, map.get(type) + attendance);
        }
        System.out.println("\n" + String.format("%-20s%s", "Type", "Attendance"));
        for(String type: map.keySet()) {
            int attendance = map.get(type);
            System.out.printf("%-20s%s%n", type, attendance);
        }
    }

    public static void grossByShowPerWeek(ArrayList<Show> shows){
        TreeMap<String,Long> map = new TreeMap<>();
        for(Show s: shows){
            if(!map.containsKey(s.getName()))
                map.put(s.getName(), s.getGrossAmt());
            else map.put(s.getName(),map.get(s.getName())+s.getGrossAmt());
        }
        System.out.println();
        System.out.printf("%-80s%s%n", "Name of Show", "Gross Income per Week");
        for(String type: map.keySet()){
            long amount = map.get(type);
            Locale current = Locale.getDefault();
            NumberFormat format = NumberFormat.getCurrencyInstance(current);
            System.out.printf("%-80s%s%n", type, format.format(amount));
        }
    }

    public static void attendanceByShowPerWeek(ArrayList<Show> shows){
        TreeMap<String,Integer> map = new TreeMap<>();
        for(Show s: shows){
            if(!map.containsKey(s.getName()))
                map.put(s.getName(), s.getAttendance());
            else map.put(s.getName(),map.get(s.getName())+s.getAttendance());
        }
        System.out.println();
        System.out.printf("%-80s%s%n", "Name of Show", "Attendance per Week");
        for(String type: map.keySet()){
            long amount = map.get(type);
            Locale current = Locale.getDefault();
            NumberFormat format = NumberFormat.getCurrencyInstance(current);
            String amt = format.format(amount);
            System.out.printf("%-80s%s%n", type, amt.substring(1, amt.length()-3));
        }
    }

    //Add-on 1: Gross by Theatre
    public static void grossByTheatre(ArrayList<Show> shows) {
        TreeMap<String, Long> map = new TreeMap<String, Long>();
        for (Show show : shows) {
            String t = show.getTheatre();
            Long g = show.getGrossAmt();
            if (!map.containsKey(t))
                map.put(t, g);
            else map.put(t, map.get(t) + g);
        }
        System.out.println();
        System.out.printf("%-30s%s%n", "Theatre", "Gross Amount");
        for (String type : map.keySet()) {
            long amount = map.get(type);
            Locale current = Locale.getDefault();
            NumberFormat format = NumberFormat.getCurrencyInstance(current);
            System.out.printf("%-30s%s%n", type, format.format(amount));
        }
    }

    //Add-on 2: Attendance by month
    public static void attendanceByMonth(ArrayList<Show> shows){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (Show show : shows) {
            int m = show.getMonth();
            int attendance = show.getAttendance();
            if (!map.containsKey(m))
                map.put(m, attendance);
            else map.put(m, map.get(m) + attendance);
        }
        System.out.println();
        System.out.printf("%-20s%s%n", "Month", "Attendance");
        for(Integer type: map.keySet()){
            long amount = map.get(type);
            Locale current = Locale.getDefault();
            NumberFormat format = NumberFormat.getCurrencyInstance(current);
            String amt = format.format(amount);
            System.out.printf("%-20s%s%n", type, amt.substring(1, amt.length()-3));
        }
    }

    //Add-on 3: Attendance by theatre
    public static void attendanceByTheatre(ArrayList<Show> shows){
        TreeMap<String,Integer> map = new TreeMap<>();
        for (Show show : shows) {
            String t = show.getTheatre();
            int attendance = show.getAttendance();
            if (!map.containsKey(t))
                map.put(t, attendance);
            else map.put(t, map.get(t) + attendance);
        }
        System.out.println();
        System.out.printf("%-20s%s%n", "Theatre", "Attendance");
        for(String type: map.keySet()){
            long amount = map.get(type);
            Locale current = Locale.getDefault();
            NumberFormat format = NumberFormat.getCurrencyInstance(current);
            String amt = format.format(amount);
            System.out.printf("%-20s%s%n", type, amt.substring(1, amt.length()-3));
        }
    }

    static class Show{
        String date;
        String name;
        String type;
        String theatre;
        long grossAmt;
        int attendance;
        double pCap;
        public Show(String date, String name, String type, String theatre, long grossAmt, int attendance, double pCap){
            this.date = date;
            this.name = name;
            this.type = type;
            this.theatre = theatre;
            this.grossAmt = grossAmt;
            this.attendance = attendance;
            this.pCap = pCap;
        }
        public String getDate(){
            return date;
        }
        public String getName(){
            return name;
        }
        public String getType(){
            return type;
        }
        public String getTheatre(){
            return theatre;
        }
        public long getGrossAmt(){
            return grossAmt;
        }
        public int getAttendance(){
            return attendance;
        }
        public double getPercentageCap(){
            return pCap;
        }
        public int getMonth(){
            String[] dateSplit = date.split("/");
            int month = Integer.parseInt(dateSplit[0]);
            return month;
        }
        public String toString(){
            return "Date: " + date + " Name: " + name + " Type: " + type + " Theatre: " + theatre + " Gross Amount: " + grossAmt + " Attendance: " + attendance + " Percent Capacity: " + pCap + "\n";
        }
    }
    public static void main(String[] args){
        Broadway broadway = new Broadway();
    }
}

