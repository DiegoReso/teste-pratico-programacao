package main.java.com.diegoreso.teste;

import main.java.com.diegoreso.teste.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    private static DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static NumberFormat formatterSalario = NumberFormat.getInstance(new Locale("pt", "BR"));

    public static void main(String[] args) {

        List<Funcionario> funcionarios = adicionarFuncionario();

        removerFuncionario(funcionarios, "João");

        imprimirFuncionarios(funcionarios);

        System.out.println("\nApós aumento de 10% no salário:\n");
        aplicarAumento(funcionarios, new BigDecimal("10"));
        imprimirFuncionarios(funcionarios);
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
}