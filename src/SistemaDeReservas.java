import java.util.Scanner;

public class SistemaDeReservas {

    static final int CAPACIDADE = 10;
    static Reserva[] reservas = new Reserva[CAPACIDADE];
    static int quantidade = 0;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    novaReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    buscarPorNome();
                    break;
                case 4:
                    ordenarPorDias();
                    break;
                case 5:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha de 1 a 5.");
            }
        } while (opcao != 5);

        scanner.close();
    }


    static void exibirMenu() {
        System.out.println("\n===== RESERVAS DO HOTEL =====");
        System.out.println("1 - Nova reserva");
        System.out.println("2 - Listar reservas");
        System.out.println("3 - Buscar reserva por nome do hóspede");
        System.out.println("4 - Ordenar reservas por número de dias (decrescente)");
        System.out.println("5 - Sair");
    }


    static void novaReserva() {

        if (quantidade >= CAPACIDADE) {
            System.out.println("Capacidade máxima atingida (" + CAPACIDADE + " reservas).");
            return;
        }

        String nome = lerTexto("Nome do hóspede: ");
        String tipo = lerTexto("Tipo do quarto (Standard, Luxo, Presidencial): ");

        int dias;
        do {
            dias = lerInteiro("Número de dias (mínimo 1): ");
            if (dias < 1) {
                System.out.println("Valor inválido! O mínimo é 1 dia.");
            }
        } while (dias < 1);

        double diaria;
        do {
            diaria = lerDecimal("Valor da diária (maior que 0): ");
            if (diaria <= 0) {
                System.out.println("Valor inválido! A diária deve ser maior que zero.");
            }
        } while (diaria <= 0);

        reservas[quantidade] = new Reserva(nome, tipo, dias, diaria);
        quantidade++;
        System.out.println("Reserva cadastrada com sucesso!");
    }

    static void listarReservas() {
        if (quantidade == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }
        System.out.println("\n--- Reservas cadastradas ---");
        for (int i = 0; i < quantidade; i++) {
            System.out.println((i + 1) + ") " + reservas[i]); // usa toString()
        }
    }

    static void buscarPorNome() {
        if (quantidade == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        String termo = lerTexto("Digite parte do nome do hóspede: ").toLowerCase();
        boolean encontrou = false;

        for (int i = 0; i < quantidade; i++) {
            if (reservas[i].getNomeHospede().toLowerCase().contains(termo)) {
                System.out.println(reservas[i]);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma reserva encontrada para \"" + termo + "\".");
        }
    }

    static void ordenarPorDias() {
        if (quantidade < 2) {
            System.out.println("É preciso ter pelo menos 2 reservas para ordenar.");
            return;
        }

        for (int i = 0; i < quantidade - 1; i++) {
            for (int j = 0; j < quantidade - 1 - i; j++) {
                if (reservas[j].getNumeroDias() < reservas[j + 1].getNumeroDias()) {
                    Reserva temporaria = reservas[j];
                    reservas[j] = reservas[j + 1];
                    reservas[j + 1] = temporaria;
                }
            }
        }

        System.out.println("Reservas ordenadas por número de dias (maior para menor):");
        listarReservas();
    }


    static String lerTexto(String mensagem) {
        String texto;
        do {
            System.out.print(mensagem);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("O campo não pode ficar vazio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    static double lerDecimal(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico válido.");
            }
        }
    }
}

