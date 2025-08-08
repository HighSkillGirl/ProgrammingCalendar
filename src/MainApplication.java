import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

public class MainApplication {
    public static void main(String[] programmingDays) {

        LocalDate today = LocalDate.now();

        String calendarPath = String.format("out/%s_%d.txt", today.getMonth().toString().toLowerCase(), today.getYear());

        File calendarFile = new File(calendarPath);

        if (calendarFile.exists()) {
            StringBuilder readCalendar = readCalendarFile(calendarPath);
            markProgrammingDays(programmingDays, readCalendar);
            writeUpdatedCalendarAsFile(calendarPath, readCalendar.toString());
        } else {
            today = today.minusDays(today.getDayOfMonth() - 1);
            int dayOfWeek = today.getDayOfWeek().getValue();
            StringBuilder calendar = createCalendar(today, dayOfWeek);

            markProgrammingDays(programmingDays, calendar);

            writeUpdatedCalendarAsFile(calendarPath, calendar.toString());
        }
    }

    private static StringBuilder createCalendar(LocalDate today, int dayOfWeek) {
        int monthSize = today.lengthOfMonth();

        StringBuilder calendar = new StringBuilder("ПН  ВТ  СР  ЧТ  ПТ  СБ  ВС\n");
        calendar.append("    ".repeat(Math.max(0, (dayOfWeek - 1))));

        for (int i = 0, dayOfMonth = today.getDayOfMonth(); dayOfMonth <= monthSize; i++) {
            for (int j = (i == 0 ? dayOfWeek - 1 : 0); j < 7 && dayOfMonth <= monthSize; j++) {
                calendar.append(String.format("%2d  ", dayOfMonth));
                dayOfMonth++;
            }
            calendar.append("\n");
        }
        return calendar;
    }

    private static StringBuilder readCalendarFile(String calendarPath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(calendarPath))) {
            StringBuilder calendarBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                calendarBuilder.append(line);
                calendarBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }

            return calendarBuilder;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void markProgrammingDays(String[] programmingDays, StringBuilder calendar) {
        Arrays.stream(programmingDays)
              .forEach(day -> {int dayStartIndex = calendar.indexOf(day) + day.length();
                                     calendar.replace(dayStartIndex, dayStartIndex + 1, "*"); });
    }

    private static void writeUpdatedCalendarAsFile(String calendarPath, String calendar) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(calendarPath))) {
            writer.print(calendar);
        } catch (FileNotFoundException e) {
            System.out.println("Что-то пошло не так");
        }
    }
}
