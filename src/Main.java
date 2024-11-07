import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Vaga vaga = new Vaga();
    public static Veiculo veiculo = new Veiculo();
    public static Estacionamento estacionamento = new Estacionamento();


    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastroVagas();
                    break;
                case 2:
                    cadastroVeiculos();
                    break;
                case 3:
                    registroCheckin();
                    break;
                case 4:
                    registroCheckout();
                    break;
                case 5:
                    relatorioVagasOcupadas();
                    break;
                case 6:
                    historicoPermanencia();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        }




    }
    public static void exibirMenu () {
        System.out.println("\n sistema de estacionamento: ");
        System.out.println("1. Cadastrar Vagas");
        System.out.println("2. Cadastrar Veículos");
        System.out.println("3. Registrar Check-in");
        System.out.println("4. Registrar Check-out");
        System.out.println("5. Relatório de vagas ocupadas");
        System.out.println("6. Histórico de Permanência ");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }



    public static void cadastroVagas() {
        System.out.println(" CADASTRO DE VAGAS ");

        while (true) {
            try {

                System.out.print("Número da vaga (ou 0 para sair): ");
                int numeroVaga = scanner.nextInt();

                if (numeroVaga == 0) {
                    break;
                }


                boolean vagaExistente = estacionamento.getListaDeVaga().stream()
                        .anyMatch(v -> v.getNumero() == numeroVaga);
                if (vagaExistente) {
                    System.out.println("Já existe uma vaga com este número!");
                    continue;
                }


                System.out.print("Tamanho da vaga (1=pequeno, 2=médio, 3=grande): ");
                int tamanho = scanner.nextInt();
                if (tamanho < 1 || tamanho > 3) {
                    System.out.println("Tamanho inválido! Use 1, 2 ou 3.");
                    continue;
                }


                System.out.print("A vaga está ocupada? (S/N): ");
                String ocupadoInput = scanner.next().trim().toUpperCase();
                boolean ocupado = ocupadoInput.equals("S");


                Vaga novaVaga = new Vaga(numeroVaga, tamanho, ocupado);
                estacionamento.getListaDeVaga().add(novaVaga);

                System.out.println("Vaga cadastrada com sucesso!");
                System.out.println(novaVaga);

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um valor numérico.");
                scanner.nextLine();
            }

            System.out.println();
        }

        System.out.println("Cadastro de vagas concluído.");

    }
    public static void cadastroVeiculos() {
        System.out.println(" CADASTRO DE VEÍCULOS ");

        while (true) {
            try {

                System.out.print("Placa do veículo (ou 0 para sair): ");
                int placa = scanner.nextInt();
                scanner.nextLine();

                if (placa == 0) {
                    break;
                }


                boolean veiculoExistente = estacionamento.getListaDeVeiculo().stream()
                        .anyMatch(v -> v.getPlaca() == placa);
                if (veiculoExistente) {
                    System.out.println("Já existe um veículo com esta placa!");
                    continue;
                }


                System.out.print("Modelo do veículo: ");
                String modelo = scanner.nextLine().trim();
                if (modelo.isEmpty()) {
                    System.out.println("O modelo não pode estar vazio.");
                    continue;
                }


                System.out.print("Tamanho do veículo (1=pequeno, 2=médio, 3=grande): ");
                int tamanho = scanner.nextInt();
                if (tamanho < 1 || tamanho > 3) {
                    System.out.println("Tamanho inválido! Use 1, 2 ou 3.");
                    continue;
                }

                Veiculo novoVeiculo = new Veiculo(placa, modelo, tamanho);
                estacionamento.getListaDeVeiculo().add(novoVeiculo);

                System.out.println("\nVeículo cadastrado com sucesso!");
                System.out.println("Placa: " + novoVeiculo.getPlaca());
                System.out.println("Modelo: " + novoVeiculo.getModelo());
                System.out.println("Tamanho: " + novoVeiculo.getTamanho());

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Insira um valor válido.");
                scanner.nextLine();
            }

            System.out.println();
        }

        System.out.println("Cadastro de veículos concluído.");
    }
    public static void registroCheckin() {
        System.out.println(" REGISTRO DE CHECK-IN ");


        System.out.println("Digite a placa do veículo:");
        int placa = scanner.nextInt();


        Veiculo veiculoEncontrado = null;
        for (Veiculo v : estacionamento.getListaDeVeiculo()) {
            if (v.getPlaca() == placa) {
                veiculoEncontrado = v;
                break;
            }
        }

        if (veiculoEncontrado == null) {
            System.out.println("Veículo não encontrado! Por favor, cadastre o veículo primeiro.");
            return;
        }


        if (veiculoEncontrado.getVaga() != null) {
            System.out.println("Este veículo já está estacionado na vaga " + veiculoEncontrado.getVaga().getNumero());
            return;
        }
        System.out.println("\nVagas disponíveis para este veículo (tamanho " + veiculoEncontrado.getTamanho() + "):");
        boolean vagasDisponiveis = false;
        for (Vaga v : estacionamento.getListaDeVaga()) {
            if (!v.isOcupada() && v.getTamanho() == veiculoEncontrado.getTamanho()) {
                System.out.println("Vaga número: " + v.getNumero());
                vagasDisponiveis = true;
            }
        }

        if (!vagasDisponiveis) {
            System.out.println("Não há vagas disponíveis para este tamanho de veículo!");
            return;
        }


        System.out.println("\nDigite o número da vaga desejada:");
        int numeroVaga = scanner.nextInt();


        Vaga vagaSelecionada = null;
        for (Vaga v : estacionamento.getListaDeVaga()) {
            if (v.getNumero() == numeroVaga) {
                vagaSelecionada = v;
                break;
            }
        }


        if (vagaSelecionada == null) {
            System.out.println("Vaga não encontrada!");
            return;
        }

        if (vagaSelecionada.isOcupada()) {
            System.out.println("Esta vaga já está ocupada!");
            return;
        }


        if (vagaSelecionada.getTamanho() != veiculoEncontrado.getTamanho()) {
            System.out.println("O tamanho do veículo não é compatível com esta vaga!");
            return;
        }


        LocalTime horaAtual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        System.out.println("Hora de check-in (HH:mm:ss) ou pressione enter para usar hora atual:");
        scanner.nextLine();
        String checkinInput = scanner.nextLine().trim();

        LocalTime horaCheckin;
        try {
            if (checkinInput.isEmpty()) {
                horaCheckin = horaAtual;
            } else {
                horaCheckin = LocalTime.parse(checkinInput, formatter);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Hora inválida! Usando hora atual.");
            horaCheckin = horaAtual;
        }


        veiculoEncontrado.setCheckin(horaCheckin);
        veiculoEncontrado.setVaga(vagaSelecionada);
        vagaSelecionada.setOcupada(true);

        System.out.println("\nCheck-in realizado com sucesso!");
        System.out.println("Veículo placa: " + veiculoEncontrado.getPlaca());
        System.out.println("Vaga número: " + vagaSelecionada.getNumero());
        System.out.println("Horário de entrada: " + horaCheckin.format(formatter));

    }
    public static void registroCheckout() {
        System.out.println(" REGISTRO DE CHECK-OUT ");

        System.out.println("Digite a placa do veículo:");
        int placa = scanner.nextInt();


        Veiculo veiculoEncontrado = null;
        for (Veiculo v : estacionamento.getListaDeVeiculo()) {
            if (v.getPlaca() == placa) {
                veiculoEncontrado = v;
                break;
            }
        }


        if (veiculoEncontrado == null) {
            System.out.println("Veículo não encontrado no sistema!");
            return;
        }

        if (veiculoEncontrado.getVaga() == null) {
            System.out.println("Este veículo não está estacionado!");
            return;
        }

        if (veiculoEncontrado.getCheckin() == null) {
            System.out.println("Não há registro de check-in para este veículo!");
            return;
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime horaAtual = LocalTime.now();


        System.out.println("\nHora de check-out (HH:mm:ss) ou pressione ENTER para usar hora atual:");
        scanner.nextLine();
        String checkoutInput = scanner.nextLine().trim();

        LocalTime horaCheckout;
        try {
            if (checkoutInput.isEmpty()) {
                horaCheckout = horaAtual;
            } else {
                horaCheckout = LocalTime.parse(checkoutInput, formatter);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Hora inválida! Usando hora atual.");
            horaCheckout = horaAtual;
        }


        LocalTime horaCheckin = veiculoEncontrado.getCheckin();
        Duration duracao = Duration.between(horaCheckin, horaCheckout);

        long horas = duracao.toHours();
        long minutos = duracao.toMinutesPart();
        long segundos = duracao.toSecondsPart();


        double valorAPagar;
        if (duracao.toMinutes() <= 60) {
            valorAPagar = 5.00;
        } else if (duracao.toMinutes() <= 180) {
            valorAPagar = 10.00;
        } else {
            valorAPagar = 15.00;
        }


        System.out.println("\n RESUMO DO CHECK-OUT ");
        System.out.println("Placa do veículo: " + veiculoEncontrado.getPlaca());
        System.out.println("Vaga utilizada: " + veiculoEncontrado.getVaga().getNumero());
        System.out.println("Horário de entrada: " + horaCheckin.format(formatter));
        System.out.println("Horário de saída: " + horaCheckout.format(formatter));
        System.out.printf("Tempo de permanência: %02d:%02d:%02d%n", horas, minutos, segundos);
        System.out.printf("Valor a pagar: R$ %.2f%n", valorAPagar);


        veiculoEncontrado.getVaga().setOcupada(false);
        veiculoEncontrado.setVaga(null);
        veiculoEncontrado.setCheckin(null);

        System.out.println("\nCheck-out realizado com sucesso!");
    }

    public static void relatorioVagasOcupadas() {
        System.out.println("\n  RELATÓRIO DE VAGAS OCUPADAS  ");
        boolean temVagasOcupadas = false;

        System.out.println("\nVagas atualmente ocupadas:");
        System.out.println("----------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-15s%n",
                "Vaga Nº", "Tamanho", "Placa", "Modelo");
        System.out.println("----------------------------------------");

        for (Vaga vaga : estacionamento.getListaDeVaga()) {
            if (vaga.isOcupada()) {
                temVagasOcupadas = true;

                Veiculo veiculoOcupante = estacionamento.getListaDeVeiculo().stream()
                        .filter(v -> v.getVaga() != null && v.getVaga().getNumero() == vaga.getNumero())
                        .findFirst()
                        .orElse(null);

                String tamanhoVaga = switch (vaga.getTamanho()) {
                    case 1 -> "Pequeno";
                    case 2 -> "Médio";
                    case 3 -> "Grande";
                    default -> "Desconhecido";
                };

                if (veiculoOcupante != null) {
                    System.out.printf("%-10d %-15s %-15d %-15s%n",
                            vaga.getNumero(),
                            tamanhoVaga,
                            veiculoOcupante.getPlaca(),
                            veiculoOcupante.getModelo());
                }
            }
        }

        if (!temVagasOcupadas) {
            System.out.println("Não há vagas ocupadas no momento.");
        }
        System.out.println("----------------------------------------");
    }

    public static void historicoPermanencia() {
        System.out.println("\n  HISTÓRICO DE PERMANÊNCIA  ");
        System.out.println("Digite a placa do veículo (ou 0 para voltar):");

        try {
            int placa = scanner.nextInt();
            if (placa == 0) return;

            Veiculo veiculo = estacionamento.getListaDeVeiculo().stream()
                    .filter(v -> v.getPlaca() == placa)
                    .findFirst()
                    .orElse(null);

            if (veiculo == null) {
                System.out.println("Veículo não encontrado!");
                return;
            }

            System.out.println("\nInformações do Veículo:");
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("Modelo: " + veiculo.getModelo());

            if (veiculo.getCheckin() != null) {
                System.out.println("\nEstadia Atual:");
                System.out.println("Check-in: " + veiculo.getCheckin().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

                if (veiculo.getVaga() != null) {
                    System.out.println("Vaga: " + veiculo.getVaga().getNumero());
                }


                Duration permanencia = Duration.between(veiculo.getCheckin(), LocalTime.now());
                long horas = permanencia.toHours();
                long minutos = permanencia.toMinutesPart();

                System.out.printf("Tempo de permanência atual: %02d:%02d%n", horas, minutos);


                double valorEstimado;
                long minutosTotal = permanencia.toMinutes();
                if (minutosTotal <= 60) {
                    valorEstimado = 5.00;
                } else if (minutosTotal <= 180) {
                    valorEstimado = 10.00;
                } else {
                    valorEstimado = 15.00;
                }
                System.out.printf("Valor estimado até o momento: R$ %.2f%n", valorEstimado);
            } else {
                System.out.println("\nVeículo não está estacionado no momento.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um número válido para a placa.");
            scanner.nextLine();
        }
    }
    }

