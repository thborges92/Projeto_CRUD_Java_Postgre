package VIEW;

import SERVICES.CONTROLLERS.*;
import SERVICES.Get;

import java.text.ParseException;

public class Menu {

    public static void inicio() throws ParseException {

        while(true) {

            System.out.println("\n\n======================================");
            System.out.println("Academia Bori Tequi");
            System.out.println("======================================");
            System.out.println("\t(0) SAIR");
            System.out.println("\t(1) Alunos");
            System.out.println("\t(2) Professores");
            System.out.println("\t(3) Atividades");
            System.out.println("\t(4) Turmas");
            System.out.println("\t(5) Matrículas");
            System.out.print("Escolha uma opção: nº ");

            int op = Get.inteiro();

            switch (op) {

                default:

                    System.err.println("\nOpção inválida!");
                    break;

                case 0:

                    System.exit(2);

                case 1:

                    alunos();
                    break;

                case 2:

                    professores();
                    break;

                case 3:

                    atividades();
                    break;

                case 4:

                    turmas();
                    break;

                case 5:

                    matriculas();
                    break;

            }

        }

    }

    private static void alunos() throws ParseException {

        System.out.println("\n\n=============Alunos============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.println("\t(3) Relatório por NOME do Aluno");
        System.out.println("\t(4) Relatório por MATRÍCULA do Aluno");
        System.out.println("\t(5) Relatório por IDADE do Aluno");
        System.out.println("\t(6) Relatório dos alunos ANIVERSARIANTES DO MÊS");
        System.out.println("\t(7) Excluir");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                System.out.println("+++++CADASTRO DE ALUNOS+++++");
                AlunoController.cadastrar();
                break;

            case 2:

                System.out.println("\n\n=======RELATÓRIO GERAL DE ALUNOS=========");
                AlunoController.imprimirLista(AlunoController.getAll());
                break;

            case 3:

                System.out.print("\n\nInforme o nome do aluno: ");
                String buscanome = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE ALUNOS POR NOME=========");
                AlunoController.imprimirLista(AlunoController.getStudentByName(buscanome));
                break;

            case 4:

                System.out.println("\n\n=======RELATÓRIO DE ALUNOS POR MATRÍCULA=========");
                AlunoController.imprimirListaAlunoReg(AlunoController.getStudentByRegistration());
                break;

            case 5:

                System.out.println("\n\n=======RELATÓRIO DE ALUNOS POR IDADE=========");
                AlunoController.imprimirLista(AlunoController.getStudentByAge());
                break;

            case 6:

                System.out.print("\n\nInforme o mês de aniversário (em números, ex: 01): ");
                int buscaaniversario = Get.inteiro();

                System.out.println("\n\n=======RELATÓRIO DE ANIVERSARIANTES DO MÊS=========");
                AlunoController.imprimirLista(AlunoController.getStudentByBirthday(buscaaniversario));
                break;

            case 7:

                System.out.print("\n\nInforme o nome do aluno a ser excluído: ");
                String excluinome = Get.texto();

                if (AlunoController.deleteByName(excluinome)) {

                    System.out.println("\nAluno excluído com sucesso.");

                } else {

                    System.out.println("\nFalha ao excluir o aluno.");

                }
                break;

        }

    }

    private static void professores() {

        System.out.println("\n\n=============Professores============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.println("\t(3) Relatório por NOME do Professor");
        System.out.println("\t(4) Relatório por ATIVIDADES do Professor");
        System.out.println("\t(5) Excluir");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                System.out.println("+++++CADASTRO DE PROFESSORES+++++");
                ProfessorController.cadastrar();
                break;

            case 2:

                System.out.println("\n\n=======RELATÓRIO GERAL DE PROFESSORES=========");
                ProfessorController.imprimirLista(ProfessorController.getAll());
                break;

            case 3:

                System.out.print("\n\nInforme o nome do professor: ");
                String buscanome = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE PROFESSORES POR NOME=========");
                ProfessorController.imprimirLista(ProfessorController.getCoachByName(buscanome));
                break;

            case 4:

                System.out.print("\n\nInforme o nome da atividade: ");
                String buscaatividade = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE PROFESSORES POR ATIVIDADES=========");
                ProfessorController.imprimirListaProfAtv(ProfessorController.getCoachByActivity(buscaatividade));
                break;

            case 5:

                System.out.print("\n\nInforme o nome do professor a ser excluído: ");
                String excluinome = Get.texto();

                if (ProfessorController.deleteByName(excluinome)) {

                    System.out.println("\nProfessor excluído com sucesso.");

                } else {

                    System.out.println("\nFalha ao excluir o professor.");

                }
                break;

        }

    }

    private static void atividades() {

        System.out.println("\n\n=============Atividades============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.println("\t(3) Relatório de Atividade por TURNO");
        System.out.println("\t(4) Excluir");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                System.out.println("+++++CADASTRO DE ATIVIDADES+++++");
                AtividadeController.cadastrar();
                break;

            case 2:

                System.out.println("\n\n=======RELATÓRIO GERAL DE ATIVIDADES=========");
                AtividadeController.imprimirLista(AtividadeController.getAll());
                break;

            case 3:

                System.out.print("\n\nInforme o turno desejado: ");
                String buscaturno = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE ATIVIDADES POR TURNO=========");
                AtividadeController.imprimirLista(AtividadeController.getActivityByShift(buscaturno));
                break;

            case 4:

                System.out.print("\n\nInforme o turno que deseja excluir a atividade: ");
                String excluiatividade = Get.texto();

                if (AtividadeController.deleteByShift(excluiatividade)) {

                    System.out.println("\nAtividade excluída com sucesso.");

                } else {

                    System.out.println("\nFalha ao excluir a atividade.");

                }
                break;

        }

    }

    private static void turmas() {

        System.out.println("\n\n=============Turmas============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.println("\t(3) Relatório por NOME DE TURMA");
        System.out.println("\t(4) Relatório de turmas por ATIVIDADES");
        System.out.println("\t(5) Relatório de turmas por PROFESSOR");
        System.out.println("\t(6) Excluir");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                System.out.println("+++++CADASTRO DE TURMAS+++++");
                TurmaController.cadastrar();
                break;

            case 2:

                System.out.println("\n\n=======RELATÓRIO GERAL DE TURMAS=========");
                TurmaController.imprimirLista(TurmaController.getAll());
                break;

            case 3:

                System.out.print("\n\nInforme o nome da turma: ");
                String buscanome = Get.texto();

                System.out.println("\n\n=======RELATÓRIO POR NOME DE TURMAS=========");
                TurmaController.imprimirLista(TurmaController.getClassByName(buscanome));
                break;

            case 4:

                System.out.print("\n\nInforme a atividade desejada: ");
                String buscaatividade = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE TURMAS POR ATIVIDADES=========");
                TurmaController.imprimirListaTurmaAtv(TurmaController.getClassByActivity(buscaatividade));
                break;

            case 5:

                System.out.print("\n\nInforme o nome do professor: ");
                String buscaprofessor = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE TURMAS POR PROFESSOR=========");
                TurmaController.imprimirListaTurmaProf(TurmaController.getClassByCoach(buscaprofessor));
                break;

            case 6:

                System.out.print("\n\nInforme o nome da turma que deseja excluir: ");
                String excluiturma = Get.texto();

                if (TurmaController.deleteClassByName(excluiturma)) {

                    System.out.println("\nTurma excluída com sucesso.");

                } else {

                    System.out.println("\nFalha ao excluir a turma.");

                }
                break;

        }

    }

    private static void matriculas() {

        System.out.println("\n\n=============Matrículas============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.println("\t(3) Relatório de Matrículas por ALUNO");
        System.out.println("\t(4) Relatório de Matrículas por TURMAS");
        System.out.println("\t(5) Excluir");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                System.out.println("+++++CADASTRO DE MATRÍCULAS+++++");
                MatriculaController.cadastrar();
                break;

            case 2:

                System.out.println("\n\n=======RELATÓRIO GERAL DE MATRÍCULAS=========");
                MatriculaController.imprimirLista(MatriculaController.getAll());
                break;

            case 3:

                System.out.print("\n\nInforme o nome do aluno: ");
                String buscanome = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE MATRÍCULAS POR ALUNO=========");
                MatriculaController.imprimirListaMatAluno(MatriculaController.getRegistrationByName(buscanome));
                break;

            case 4:

                System.out.print("\n\nInforme o nome da turma: ");
                String buscaturma = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE MATRÍCULAS POR ALUNO=========");
                MatriculaController.imprimirListaMatTurma(MatriculaController.getRegistrationByClass(buscaturma));
                break;

            case 5:

                System.out.print("\n\nInforme o nome do aluno que deseja excluir a matrícula: ");
                String excluimatricula = Get.texto();

                if (MatriculaController.deleteRegistration(excluimatricula)) {

                    System.out.println("\nMatrícula excluída com sucesso.");

                } else {

                    System.out.println("\nFalha ao excluir a matrícula.");

                }
                break;

        }

    }

}
