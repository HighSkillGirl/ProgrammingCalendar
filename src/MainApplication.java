import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;

public class MainApplication {
    public static void main(String[] programmingDays) {

        LocalDate today = LocalDate.now();

        String calendarPath = String.format("out/%s_%d.txt", today.getMonth().toString().toLowerCase(), today.getYear());

        try (PrintStream out = new PrintStream(new FileOutputStream(calendarPath))) { // создает файл заново
            today = today.minusDays(today.getDayOfMonth() - 1);
            int dayOfWeek = today.getDayOfWeek().getValue();
            StringBuilder calendar = createCalendar(today, dayOfWeek);

            Arrays.stream(programmingDays)
                  .forEach(day -> { int dayStartIndex = calendar.indexOf(day) + day.length();
                                           calendar.replace(dayStartIndex, dayStartIndex + 1, "*");
                  });
            out.print(calendar);

        } catch (FileNotFoundException e) {
            System.out.println("Директория не найдена");
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
}
