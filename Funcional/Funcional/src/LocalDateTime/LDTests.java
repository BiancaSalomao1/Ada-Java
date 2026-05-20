package LocalDateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import static java.time.LocalTime.now;

public class LDTests {
    static void main() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("ldt " + ldt);
        LocalDateTime ldtTest = LocalDateTime.of(2026, 4, 29, 7, 30, 45);
        System.out.println("ldtTest " + ldtTest);

        //como interpreta os dados de entrada
        LocalDateTime ldtTest2 = LocalDateTime.parse("29-04-2026 07:30:45", java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        System.out.println("ldtTest2 " + ldtTest2);

        //como imprime os dados de saída
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("ldtTest2 " + ldtTest2.format(formatter));

        LocalDateTime ldt2 = ldt.plusDays(5).plusHours(2);
        System.out.println("ldt2 " + ldt2);

        LocalDateTime ldt3 = ldt.minusMinutes(15);
        System.out.println("ldt3 " + ldt3);

        LocalDateTime dataHoraInicio = LocalDateTime.of(2022, 12, 15, 1, 22, 43);
        LocalDateTime dataHoraFim = LocalDateTime.parse("2022-12-20T05:45:43");
        System.out.println("Recesso de " + dataHoraInicio + " ate " + dataHoraFim);


        String strDataFormatoBR = "25-06-1980 11:15";
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dataHoraBR = LocalDateTime.parse(strDataFormatoBR, formatoData);
        System.out.println("Data e hora extraidos do formato BR: " + dataHoraBR);

        DateTimeFormatter outroFormato = DateTimeFormatter.ofPattern("dd MM yy - HH, mm");
        System.out.println("Outros formatos: " + dataHoraBR.format(outroFormato));

        LocalDate dataNascimento = LocalDate.of(1985, 7, 22);

        DateTimeFormatter formatoDataNascimento =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DateTimeFormatter formatoDiaSemana =
                DateTimeFormatter.ofPattern("EEEE", new Locale("pt", "BR"));

        System.out.println("Data: " +
                formatoDataNascimento.format(dataNascimento));

        System.out.println("Dia da Semana: " +
                dataNascimento.format(formatoDiaSemana));
        System.out.println("Três dias antes: " +
                dataNascimento.minusDays(3));

        System.out.println("Três dias depois: " +
                dataNascimento.plusDays(3));

        LocalDateTime dateTime = LocalDateTime.now();

        ZonedDateTime zonedDateTimeSP = ZonedDateTime.of(dateTime, ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime zonedDateTimeAC = ZonedDateTime.of(dateTime, ZoneId.of("Brazil/Acre"));
        ZonedDateTime zonedDateTimePT = ZonedDateTime.of(dateTime, ZoneId.of("Europe/Lisbon"));

        System.out.println("Data hora fuso SP: " + zonedDateTimeSP);
        System.out.println("Data hora fuso AC: " + zonedDateTimeAC);
        System.out.println("Data hora fuso PT: " + zonedDateTimePT);

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");


        boolean isDaylightSaving = zoneId.getRules().isDaylightSavings(zonedDateTimeSP.toInstant());
        System.out.println("Em " + zonedDateTimeSP + " SP estava em horario de verao: " + isDaylightSaving);

        dateTime = LocalDateTime.parse("2018-12-01T05:00:00");
        zonedDateTimeSP = ZonedDateTime.of(dateTime, zoneId);
        isDaylightSaving = zoneId.getRules().isDaylightSavings(zonedDateTimeSP.toInstant());
        System.out.println("Em " + zonedDateTimeSP + " SP estava em horario de verao: " + isDaylightSaving);

        ZonedDateTime nextFridayDateTime = zonedDateTimeSP.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("A data da proxima SEXTA foi: " + nextFridayDateTime);

        LocalTime begins = LocalTime.of(12, 07, 10);
        LocalTime ends = LocalTime.of(14, 22, 37);
        System.out.println("Intervalo: " + begins + " - " + ends);

        long minutes = ChronoUnit.MINUTES.between(begins, ends);
        System.out.println("Total minutos: " + begins + " - " + ends);

        Duration duration = Duration.ofMinutes(minutes);
        System.out.println("Duracao do intervalo: " + duration);

        Duration duracao = Duration.ofHours(2).plusMinutes(15);

        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();

        System.out.println("Duração do intervalo: " +
                horas + " horas e " + minutos + " minutos");

        ZonedDateTime eventDateTime = ZonedDateTime.of(1998, 1, 13, 16, 45, 56, 0, ZoneId.of("America/Sao_Paulo"));
        Instant eventInstant = eventDateTime.toInstant();
        System.out.println("Data e hora do evento: " + eventDateTime);
        System.out.println("Instante do evento: " + eventInstant);

        System.out.println("\nNumero de segundos de 01-Janeiro-1970 ate o inicio do evento: " + eventInstant.getEpochSecond());


        Locale locBR = new Locale("pt", "BR");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("Formato longo: ");
        System.out.println("Brasil: " +
                zonedDateTime.format(
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG) //FULL, LONG, MEDIUM, SHORT
                                .withLocale(locBR)));
    }
}
