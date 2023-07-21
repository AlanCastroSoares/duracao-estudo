import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DuracaoEstudo {
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final DateTimeFormatter FORMATADOR_HORA = DateTimeFormatter.ofPattern("HH:mm").
            localizedBy(new Locale("pt","BR"));
    public static final int DURAÇÃO_TRABALHADA_NECESSÁRIA = 6;

    public static void main(String[] args) {
        LocalTime horaInicial;
        LocalTime horalFinal;
        List<Duration> tempo = new ArrayList();
        Duration tempoInical = Duration.ZERO;
        int período = 1;


        do {
            System.out.println("------------------------------------------");
            horaInicial = requistarHora("Digite a hora inicial: ");
            horalFinal = requistarHora("Digite a hora final: ");
            Duration tempoTrabalhado = cacularHoraTrabalhada(horaInicial, horalFinal);
            System.out.println((período++) + "º Perído com duração: " + tempoTrabalhado);
            tempo.add(tempoTrabalhado);
            System.out.println("Deseja continuar?");

        } while ("sim".equalsIgnoreCase(ENTRADA.nextLine()));

        Duration valorTotal = tempo.stream()
                .reduce(tempoInical,Duration::plus);

        System.out.printf("O total de horas estudas: %s", valorTotal);
    }

    public static LocalTime requistarHora(String string) {
        while (true) {
            try {
                System.out.println(string);
                String dataTexto = ENTRADA.nextLine();

                return LocalTime.parse(dataTexto, FORMATADOR_HORA);
            } catch (DateTimeParseException e) {
                System.out.println("Hora inválida. Tente novamente.");
            }
        }
    }

    public static Duration cacularHoraTrabalhada(LocalTime horaInicial, LocalTime horalFinal ) {
        Duration duracao = Duration.between(horaInicial,horalFinal);
        if (duracao.isNegative()) {
            throw new RuntimeException("A hora final não pode ser maior do que a hora inicial.");
        }
        return duracao;
    }
}


