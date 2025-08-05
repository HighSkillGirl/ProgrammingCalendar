import java.time.LocalDate;
import java.util.Arrays;

public class MainApplication {
    public static void main(String[] programmingDays) {

        LocalDate today = LocalDate.of(2025, 8, 1);
        int dayOfWeek =  today.getDayOfWeek().getValue();
        StringBuilder calendar = getCalendar(today, dayOfWeek);

        Arrays.stream(programmingDays)
              .forEach(day -> {
                  int dayStartIndex = calendar.indexOf(day) + day.length();
                  calendar.replace(dayStartIndex, dayStartIndex + 1, "*");
              });

        System.out.print(calendar);
    }

    private static StringBuilder getCalendar(LocalDate today, int dayOfWeek) {
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
}
