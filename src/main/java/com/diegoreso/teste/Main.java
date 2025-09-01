package main.java.com.diegoreso.teste;

import main.java.com.diegoreso.teste.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    private static DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static NumberFormat formatterSalario = NumberFormat.getInstance(new Locale("pt", "BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {

        List<Funcionario> funcionarios = adicionarFuncionario();

        removerFuncionario(funcionarios, "João");

        imprimirFuncionarios(funcionarios);

        System.out.println("\nApós aumento de 10% no salário:\n");
        aplicarAumento(funcionarios, new BigDecimal("10"));
        imprimirFuncionarios(funcionarios);

        System.out.println("\nAgrupamento por função:\n");
        agruparFuncionariosPorFuncao(funcionarios);

        System.out.println("\nFuncionários que fazem aniversário no mês 10:\n");
        imprimirFuncionariosMesAniversario(funcionarios, 10);

        System.out.println("\nFuncionários que fazem aniversário no mês 12:\n");
        imprimirFuncionariosMesAniversario(funcionarios, 12);

        System.out.println("\nFuncionário com maior idade:\n");
        imprimirFuncionarioComMaiorIdade(funcionarios);

        System.out.println("\nFuncionários por ordem Alfabética:\n");
        imprimirFuncionariosOrdemAlfabetica(funcionarios);

        System.out.println("\nTotal Salários dos Funcionários:\n");
        imprimirTotalSalariosFuncionarios(funcionarios);

        System.out.println("\nTotal Salários Mínimos por Funcionário:\n");
        imprimirSalariosMinimos(funcionarios);

    }

    private static List<Funcionario> adicionarFuncionario() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        return funcionarios;
    }

    private static void removerFuncionario(List<Funcionario> funcionarios, String nomeFuncionario) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nomeFuncionario));
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        if (funcionarios.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║          Nenhum funcionário           ║");
            System.out.println("║              encontrado!              ║");
            System.out.println("╚═══════════════════════════════════════╝");
            return;
        }

        System.out.printf("%-10s | %-15s | %-10s | %-10s%n",
                "Nome",
                "Data Nascimento",
                "Salário",
                "Função");
        System.out.println("-----------------------------------------------------------");

        for (Funcionario funcionario : funcionarios) {
            String dataNascimentoFormatada = funcionario.getDataNascimento().format(formatterData);
            String salarioFormatado = formatterSalario.format(funcionario.getSalario());

            System.out.printf("%-10s | %-15s | %-10s | %-10s%n",
                    funcionario.getNome(),
                    dataNascimentoFormatada,
                    salarioFormatado,
                    funcionario.getFuncao());
        }
    }

    private static void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario()
                    .multiply(percentual)
                    .divide(new BigDecimal("100"));

            BigDecimal novoSalario = funcionario.getSalario()
                    .add(aumento)
                    .setScale(2, RoundingMode.HALF_UP);
            funcionario.setSalario(novoSalario);
        }
    }

    private static void agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(groupingBy(Funcionario::getFuncao));

        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            List<Funcionario> listaFuncionarios = entry.getValue();
            imprimirFuncionarios(listaFuncionarios);
            System.out.println();
        }
    }

    private static void imprimirFuncionariosMesAniversario(List<Funcionario> funcionarios, int mes) {
        List<Funcionario> funcionariosPorMesAniversario = funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
                .toList();

        imprimirFuncionarios(funcionariosPorMesAniversario);
    }

    private static void imprimirFuncionarioComMaiorIdade(List<Funcionario> funcionarios) {
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .ifPresent(funcionarioMaisVelho -> {
                    int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();

                    System.out.printf("%-10s | %-10s%n", "Nome", "Idade");
                    System.out.println("---------------------");

                    System.out.printf("%-10s | %s anos%n",
                            funcionarioMaisVelho.getNome(),
                            idade);
                });

        if (funcionarios.isEmpty()) {
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║          Nenhum funcionário           ║");
            System.out.println("║              encontrado!              ║");
            System.out.println("╚═══════════════════════════════════════╝");
        }
    }

    private static void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        List<Funcionario> listaFuncionarios = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .toList();
        imprimirFuncionarios(listaFuncionarios);
    }

    private static void imprimirTotalSalariosFuncionarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total: " + totalSalarios);
    }

    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosComSalarioMinimo=  new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(SALARIO_MINIMO,1,RoundingMode.HALF_UP);
            funcionariosComSalarioMinimo.add(new Funcionario(funcionario.getNome(), funcionario.getDataNascimento(), salariosMinimos, funcionario.getFuncao()));
        }
        imprimirFuncionarios(funcionariosComSalarioMinimo);
    }
}