import java.time.LocalDate;

public class MainApplication {
    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2025, 8, 1);
        int dayOfWeek =  today.getDayOfWeek().getValue();
        int monthSize = today.lengthOfMonth();

        System.out.println("ПН  ВТ  СР  ЧТ  ПТ  СБ  ВС");

        StringBuilder emptyLine = new StringBuilder();
        for (int i = 0; i < ( dayOfWeek - 1); i++) {
            emptyLine.append("    ");
        }

        System.out.print(emptyLine);

        for (int i = 0, dayOfMonth = today.getDayOfMonth(); dayOfMonth <= monthSize; i++) {
            for (int j = (i == 0 ? dayOfWeek - 1 : 0); j < 7 && dayOfMonth <= monthSize; j++) {
                System.out.printf("%2d  ", dayOfMonth);
                dayOfMonth++;
            }
            System.out.println();
        }

    }
}
