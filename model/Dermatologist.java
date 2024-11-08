package model;

import java.util.*;

public class Dermatologist {
    private int id;
    private String name;
    private Map<String, List<String>> weeklySchedule;

    public Dermatologist(int id, String name) {
        this.id = id;
        this.name = name;
        this.weeklySchedule = new HashMap<>();
        initializeSchedule();
    }

    private void initializeSchedule() {
        weeklySchedule.put("Monday", generateTimeSlots("10:00 AM", "01:00 PM"));
        weeklySchedule.put("Wednesday", generateTimeSlots("02:00 PM", "05:00 PM"));
        weeklySchedule.put("Friday", generateTimeSlots("04:00 PM", "08:00 PM"));
        weeklySchedule.put("Saturday", generateTimeSlots("09:00 AM", "01:00 PM"));
    }

    private List<String> generateTimeSlots(String start, String end) {
        List<String> timeSlots = new ArrayList<>();
        try {
            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            startTime.set(Calendar.HOUR, Integer.parseInt(start.split(":")[0]));
            startTime.set(Calendar.MINUTE, Integer.parseInt(start.split(":")[1].split(" ")[0]));
            startTime.set(Calendar.AM_PM, start.endsWith("AM") ? Calendar.AM : Calendar.PM);
            endTime.set(Calendar.HOUR, Integer.parseInt(end.split(":")[0]));
            endTime.set(Calendar.MINUTE, Integer.parseInt(end.split(":")[1].split(" ")[0]));
            endTime.set(Calendar.AM_PM, end.endsWith("AM") ? Calendar.AM : Calendar.PM);

            while (startTime.before(endTime)) {
                timeSlots.add(String.format("%02d:%02d %s",
                    startTime.get(Calendar.HOUR) == 0 ? 12 : startTime.get(Calendar.HOUR),
                    startTime.get(Calendar.MINUTE),
                    startTime.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"));
                startTime.add(Calendar.MINUTE, 15);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeSlots;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    public Map<String, List<String>> getWeeklySchedule() {
        return weeklySchedule;
    }
}
