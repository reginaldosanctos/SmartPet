import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class SmartPet {

    // URL de conexão ao banco de dados
    static String url = "jdbc:sqlserver://FLOKI-FAMILY:1433;databaseName=SmartPet;encrypt=false";
    static String user = "sa";
    static String password = "289713@(L&r)#";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Scanner scanner = new Scanner(System.in);
            int mainOption;
            do {
                System.out.println("\n--- SmartPet Menu Principal ---");
                System.out.println("1. Menu de Cadastros");
                System.out.println("2. Menu de Consultas");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt();

                switch (mainOption) {
                    case 1 -> menuCadastros(conn);
                    case 2 -> menuConsultas(conn);
                    case 0 -> System.out.println("Saindo do programa...");
                    default -> System.out.println("Opção inválida.");
                }
            } while (mainOption != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void menuCadastros(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n--- Menu de Cadastros ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Pet");
            System.out.println("3. Cadastrar Atendente");
            System.out.println("4. Cadastrar Raça");
            System.out.println("5. Cadastrar Especialidade");
            System.out.println("6. Cadastrar Especialista");
            System.out.println("7. Cadastrar Veterinário");
            System.out.println("8. Cadastrar Categoria de Item");
            System.out.println("9. Cadastrar Item");
            System.out.println("10. Cadastrar Produto");
            System.out.println("11. Cadastrar Serviço");
            System.out.println("12. Cadastrar Nota Fiscal");
            System.out.println("13. Cadastrar Atendimento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> cadastrarCliente(conn);
                case 2 -> cadastrarPet(conn);
                case 3 -> cadastrarAtendente(conn);
                case 4 -> cadastrarRaca(conn);
                case 5 -> cadastrarEspecialidade(conn);
                case 6 -> cadastrarEspecialista(conn);
                case 7 -> cadastrarVeterinario(conn);
                case 8 -> cadastrarCategoriaItem(conn);
                case 9 -> cadastrarItem(conn);
                case 10 -> cadastrarProduto(conn);
                case 11 -> cadastrarServico(conn);
                case 12 -> cadastrarNotaFiscal(conn);
                case 13 -> cadastrarAtendimento(conn);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    private static void menuConsultas(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n--- Menu de Consultas ---");
            System.out.println("1. Consultar Cliente");
            System.out.println("2. Consultar Pet");
            System.out.println("3. Consultar Atendente");
            System.out.println("4. Consultar Raça");
            System.out.println("5. Consultar Especialidade");
            System.out.println("6. Consultar Especialista");
            System.out.println("7. Consultar Veterinário");
            System.out.println("8. Consultar Categoria de Item");
            System.out.println("9. Consultar Item");
            System.out.println("10. Consultar Produto");
            System.out.println("11. Consultar Serviço");
            System.out.println("12. Consultar Nota Fiscal");
            System.out.println("13. Consultar Atendimento");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> consultarCliente(conn);
                case 2 -> consultarPet(conn);
                case 3 -> consultarAtendente(conn);
                case 4 -> consultarRaca(conn);
                case 5 -> consultarEspecialidade(conn);
                case 6 -> consultarEspecialista(conn);
                case 7 -> consultarVeterinario(conn);
                case 8 -> consultarCategoriaItem(conn);
                case 9 -> consultarItem(conn);
                case 10 -> consultarProduto(conn);
                case 11 -> consultarServico(conn);
                case 12 -> consultarNotaFiscal(conn);
                case 13 -> consultarAtendimento(conn);
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    // Metodo de Cadastro para cadastrarCliente
    private static void cadastrarCliente(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("CPF do Cliente: ");
            String cpf = scanner.next(); // Lê apenas o CPF
            scanner.nextLine(); // Limpa o buffer do scanner

            System.out.print("Nome do Cliente: ");
            String nome = scanner.nextLine(); // Lê a linha inteira para o nome

            System.out.print("Email do Cliente: ");
            String email = scanner.nextLine(); // Lê a linha inteira para o email

            System.out.print("Telefone do Cliente: ");
            String telefone = scanner.nextLine(); // Lê a linha inteira para o telefone

            // Validação do tamanho do telefone
            if (telefone.length() != 14) {
                System.out.println("O telefone deve ter exatamente 14 caracteres.");
                return; // Saída do método se a validação falhar
            }

            String sql = "INSERT INTO Cliente (cpf_cliente, nome_cliente, email_cliente, telefone_cliente) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cpf);
                stmt.setString(2, nome);
                stmt.setString(3, email);
                stmt.setString(4, telefone);
                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Cliente cadastrado com sucesso!" : "Erro ao cadastrar cliente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarPet
    private static void cadastrarPet(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("CPF do Cliente (dono): ");
            String cpfCliente = scanner.next();
            System.out.print("ID da Raça: ");
            String idRaca = scanner.next();
            System.out.print("Nome do Pet: ");
            String nomePet = scanner.next();
            System.out.print("Comprimento do Pet: ");
            double comprimentoPet = scanner.nextDouble();
            System.out.print("Peso do Pet: ");
            double pesoPet = scanner.nextDouble();
            System.out.print("Sexo do Pet (M/F): ");
            String sexoPet = scanner.next();

            String sql = "INSERT INTO Pet (cpf_cliente, id_raca, nome_pet, comprimento_pet, peso_pet, sexo_pet) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cpfCliente);
                stmt.setString(2, idRaca);
                stmt.setString(3, nomePet);
                stmt.setDouble(4, comprimentoPet);
                stmt.setDouble(5, pesoPet);
                stmt.setString(6, sexoPet);
                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Pet cadastrado com sucesso!" : "Erro ao cadastrar pet.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarAtendente
    private static void cadastrarAtendente(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Nome do Atendente: ");
            String nome = scanner.next();
            System.out.print("Data de Admissão (AAAA-MM-DD): ");
            String admissao = scanner.next();

            String sql = "INSERT INTO Atendente (nome_atendente, admissao_atendente) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setDate(2, Date.valueOf(admissao));
                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Atendente cadastrado com sucesso!" : "Erro ao cadastrar atendente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarRaca
    private static void cadastrarRaca(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID da Raça: ");
            String id = scanner.next();
            System.out.print("Nome da Raça: ");
            String nome = scanner.next();
            System.out.print("Espécie da Raça (Cachorro/Gato): ");
            String especie = scanner.next();
            System.out.print("Porte da Raça (Miniatura/Pequeno/Médio/Grande): ");
            String porte = scanner.next();

            String sql = "INSERT INTO Raca (id_raca, nome_raca, especie_raca, porte_raca) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                stmt.setString(2, nome);
                stmt.setString(3, especie);
                stmt.setString(4, porte);
                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Raça cadastrada com sucesso!" : "Erro ao cadastrar raça.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarEspecialidade
    private static void cadastrarEspecialidade(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID da Especialidade: ");
            String id = scanner.next();
            System.out.print("Descrição da Especialidade: ");
            String descricao = scanner.next();
            System.out.print("Tipo da Especialidade (Veterinario/Groomer): ");
            String tipo = scanner.next();

            String sql = "INSERT INTO Especialidade (id_especialidade, descricao_especialidade, tipo_especialidade) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, id);
                stmt.setString(2, descricao);
                stmt.setString(3, tipo);
                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Especialidade cadastrada com sucesso!" : "Erro ao cadastrar especialidade.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarEspecialista
    private static void cadastrarEspecialista(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Matrícula do Especialista: ");
            String matricula = scanner.next();
            System.out.print("ID da Especialidade: ");
            String idEspecialidade = scanner.next();
            System.out.print("Nome do Especialista: ");
            String nome = scanner.next();
            System.out.print("Data de Admissão (AAAA-MM-DD): ");
            String admissao = scanner.next();

            String sql = "INSERT INTO Especialista (matricula_especialista, id_especialidade, nome_especialista, admissao_especialista) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, matricula);
                stmt.setString(2, idEspecialidade);
                stmt.setString(3, nome);
                stmt.setDate(4, Date.valueOf(admissao));
                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Especialista cadastrado com sucesso!" : "Erro ao cadastrar especialista.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarVeterinario
    private static void cadastrarVeterinario(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID do Veterinário: ");
            int id = scanner.nextInt();
            System.out.print("Matrícula do Especialista: ");
            String matricula = scanner.next();
            System.out.print("CRMV: ");
            String crmv = scanner.next();

            String sql = "INSERT INTO Veterinario (id_veterinario, matricula_especialista, CRMV) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, matricula);
                stmt.setString(3, crmv);
                int rowsInserted = stmt.executeUpdate();
                System.out.println(rowsInserted > 0 ? "Veterinário cadastrado com sucesso!" : "Erro ao cadastrar veterinário.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarCategoriaItem
    private static void cadastrarCategoriaItem(Connection conn) {
        try {
            String sql = "INSERT INTO CategoriaItem (id_categoria, descricao_categoria, tipo_categoria) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Defina os valores conforme necessário
            pstmt.setString(1, "C001"); // Exemplo de ID
            pstmt.setString(2, "Alimentação"); // Exemplo de Descrição
            pstmt.setString(3, "Produto"); // Exemplo de Tipo
            pstmt.executeUpdate();
            System.out.println("Categoria de Item cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarItem
    private static void cadastrarItem(Connection conn) {
        try {
            String sql = "INSERT INTO Item (id_item, id_categoria, nome_item, custo_item) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Defina os valores conforme necessário
            pstmt.setString(1, "I001"); // Exemplo de ID
            pstmt.setString(2, "C001"); // Exemplo de Categoria
            pstmt.setString(3, "Ração"); // Exemplo de Nome
            pstmt.setDouble(4, 50.00); // Exemplo de Custo
            pstmt.executeUpdate();
            System.out.println("Item cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarProduto
    private static void cadastrarProduto(Connection conn) {
        try {
            String sql = "INSERT INTO Produto (id_item, qtd_estoque) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Defina os valores conforme necessário
            pstmt.setString(1, "I001"); // Exemplo de ID do item
            pstmt.setInt(2, 100); // Exemplo de quantidade em estoque
            pstmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarServico
    private static void cadastrarServico(Connection conn) {
        try {
            String sql = "INSERT INTO Servico (id_item, duracao_servico) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Defina os valores conforme necessário
            pstmt.setString(1, "I002"); // Exemplo de ID do item
            pstmt.setTime(2, Time.valueOf("01:00:00")); // Exemplo de duração
            pstmt.executeUpdate();
            System.out.println("Serviço cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarNotaFiscal
    private static void cadastrarNotaFiscal(Connection conn) {
        try {
            String sql = "INSERT INTO NotaFiscal (id_notaFiscal, cpf_cliente, matricula_atendente, data_notaFiscal, hora_notaFiscal, tipo_notaFiscal) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Defina os valores conforme necessário
            pstmt.setString(1, "N001"); // Exemplo de ID
            pstmt.setString(2, "12345678901"); // Exemplo de CPF
            pstmt.setInt(3, 1000); // Exemplo de matrícula
            pstmt.setDate(4, Date.valueOf("2024-11-01")); // Exemplo de data
            pstmt.setTime(5, Time.valueOf("10:30:00")); // Exemplo de hora
            pstmt.setString(6, "Produto"); // Exemplo de tipo
            pstmt.executeUpdate();
            System.out.println("Nota Fiscal cadastrada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo de cadastro para cadastrarAtendimento
    private static void cadastrarAtendimento(Connection conn) {
        try {
            String sql = "INSERT INTO Atendimento (matricula_especialista, matricula_pet, id_servico, cpf_cliente, data_marcacao, data_atendimento, hora_atendimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Defina os valores conforme necessário
            pstmt.setString(1, "E001"); // Exemplo de matrícula do especialista
            pstmt.setInt(2, 100); // Exemplo de matrícula do pet
            pstmt.setInt(3, 200); // Exemplo de ID do serviço
            pstmt.setString(4, "12345678901"); // Exemplo de CPF do cliente
            pstmt.setDate(5, Date.valueOf("2024-11-01")); // Exemplo de data
            pstmt.setDate(6, Date.valueOf("2024-11-01")); // Exemplo de data
            pstmt.setTime(7, Time.valueOf("10:30:00")); // Exemplo de hora
            pstmt.executeUpdate();
            System.out.println("Atendimento cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void consultarCliente(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("CPF do Cliente para consulta ou 'todos' para exibir todos: ");
            String cpf = scanner.next(); // Lê o CPF do cliente ou a palavra 'todos'

            String sql;
            boolean isAll = cpf.equalsIgnoreCase("todos"); // Verifica se o usuário digitou 'todos'

            if (isAll) {
                sql = "SELECT * FROM Cliente"; // Consulta para exibir todos os clientes
            } else {
                sql = "SELECT * FROM Cliente WHERE cpf_cliente = ?"; // Consulta para exibir um cliente específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do CPF do cliente se não for para exibir todos
                if (!isAll) {
                    stmt.setString(1, cpf);
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada cliente encontrado
                if (isAll) {
                    while (rs.next()) {
                        System.out.println("Nome: " + rs.getString("nome_cliente"));
                        System.out.println("CPF: " + rs.getString("cpf_cliente"));
                        System.out.println("Email: " + rs.getString("email_cliente"));
                        System.out.println("Telefone: " + rs.getString("telefone_cliente"));
                        System.out.println("-------------------------"); // Separador entre clientes
                    }
                } else if (rs.next()) {
                    System.out.println("Nome: " + rs.getString("nome_cliente"));
                    System.out.println("Email: " + rs.getString("email_cliente"));
                    System.out.println("Telefone: " + rs.getString("telefone_cliente"));
                } else {
                    System.out.println("Cliente não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarPet(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Matrícula do Pet para consulta ou 'todos' para exibir todos: ");
            String input = scanner.next(); // Lê a matrícula do pet ou a palavra 'todos'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todos"); // Verifica se o usuário digitou 'todos'

            if (isAll) {
                sql = "SELECT * FROM Pet"; // Consulta para exibir todos os pets
            } else {
                sql = "SELECT * FROM Pet WHERE matricula_pet = ?"; // Consulta para exibir um pet específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro da matrícula do pet se não for para exibir todos
                if (!isAll) {
                    int matriculaPet = Integer.parseInt(input); // Converte a entrada para inteiro
                    stmt.setInt(1, matriculaPet);
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada pet encontrado
                if (isAll) {
                    while (rs.next()) {
                        System.out.println("Nome do Pet: " + rs.getString("nome_pet"));
                        System.out.println("Matrícula do Pet: " + rs.getInt("matricula_pet"));
                        System.out.println("CPF do Dono: " + rs.getString("cpf_cliente"));
                        System.out.println("ID da Raça: " + rs.getString("id_raca"));
                        System.out.println("Comprimento: " + rs.getDouble("comprimento_pet"));
                        System.out.println("Peso: " + rs.getDouble("peso_pet"));
                        System.out.println("Sexo: " + rs.getString("sexo_pet"));
                        System.out.println("-------------------------"); // Separador entre pets
                    }
                } else if (rs.next()) {
                    System.out.println("Nome do Pet: " + rs.getString("nome_pet"));
                    System.out.println("CPF do Dono: " + rs.getString("cpf_cliente"));
                    System.out.println("ID da Raça: " + rs.getString("id_raca"));
                    System.out.println("Comprimento: " + rs.getDouble("comprimento_pet"));
                    System.out.println("Peso: " + rs.getDouble("peso_pet"));
                    System.out.println("Sexo: " + rs.getString("sexo_pet"));
                } else {
                    System.out.println("Pet não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarAtendente(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Matrícula do Atendente para consulta ou 'todos' para exibir todos: ");
            String input = scanner.next(); // Lê a matrícula do atendente ou a palavra 'todos'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todos"); // Verifica se o usuário digitou 'todos'

            if (isAll) {
                sql = "SELECT * FROM Atendente"; // Consulta para exibir todos os atendentes
            } else {
                sql = "SELECT * FROM Atendente WHERE matricula_atendente = ?"; // Consulta para exibir um atendente específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro da matrícula do atendente se não for para exibir todos
                if (!isAll) {
                    int matricula = Integer.parseInt(input); // Converte a entrada para inteiro
                    stmt.setInt(1, matricula);
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada atendente encontrado
                if (isAll) {
                    while (rs.next()) {
                        System.out.println("Nome: " + rs.getString("nome_atendente"));
                        System.out.println("Matrícula: " + rs.getInt("matricula_atendente"));
                        System.out.println("Data de Admissão: " + rs.getDate("admissao_atendente"));
                        System.out.println("-------------------------"); // Separador entre atendentes
                    }
                } else if (rs.next()) {
                    System.out.println("Nome: " + rs.getString("nome_atendente"));
                    System.out.println("Data de Admissão: " + rs.getDate("admissao_atendente"));
                } else {
                    System.out.println("Atendente não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarRaca(Connection conn) {
        // Cria um scanner para capturar a entrada do usuário
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID da Raça para consulta ou 'todas' para exibir todas: ");
            String id = scanner.next(); // Lê o ID da raça ou a palavra 'todas'

            // Define a consulta SQL com base na entrada do usuário
            String sql;
            boolean isAll = id.equalsIgnoreCase("todas"); // Verifica se o usuário digitou 'todas'

            if (isAll) {
                sql = "SELECT * FROM Raca"; // Consulta para exibir todas as raças
            } else {
                sql = "SELECT * FROM Raca WHERE id_raca = ?"; // Consulta para exibir uma raça específica
            }

            // Prepara e executa a consulta SQL em um bloco try separado
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID da raça se não for para exibir todas
                if (!isAll) {
                    stmt.setString(1, id);
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada raça encontrada
                if (rs.next()) {
                    do {
                        System.out.println("ID da Raça: " + rs.getString("id_raca"));
                        System.out.println("Nome da Raça: " + rs.getString("nome_raca"));
                        System.out.println("Espécie: " + rs.getString("especie_raca"));
                        System.out.println("Porte: " + rs.getString("porte_raca"));
                        System.out.println("-------------------------"); // Separador entre raças
                    } while (rs.next()); // Continua exibindo até o último registro
                } else {
                    // Exibe uma mensagem apropriada se nenhuma raça for encontrada
                    System.out.println(isAll ? "Nenhuma raça cadastrada." : "Raça não encontrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }


    private static void consultarEspecialidade(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID da Especialidade para consulta ou 'todas' para exibir todas: ");
            String input = scanner.next(); // Lê o ID da especialidade ou a palavra 'todas'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todas"); // Verifica se o usuário digitou 'todas'

            if (isAll) {
                sql = "SELECT * FROM Especialidade"; // Consulta para exibir todas as especialidades
            } else {
                sql = "SELECT * FROM Especialidade WHERE id_especialidade = ?"; // Consulta para exibir uma especialidade específica
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID da especialidade se não for para exibir todas
                if (!isAll) {
                    stmt.setString(1, input); // Usa a entrada do usuário como parâmetro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada especialidade encontrada
                if (isAll) {
                    while (rs.next()) {
                        System.out.println("ID da Especialidade: " + rs.getString("id_especialidade"));
                        System.out.println("Descrição da Especialidade: " + rs.getString("descricao_especialidade"));
                        System.out.println("Tipo: " + rs.getString("tipo_especialidade"));
                        System.out.println("-------------------------"); // Separador entre especialidades
                    }
                } else if (rs.next()) {
                    System.out.println("Descrição da Especialidade: " + rs.getString("descricao_especialidade"));
                    System.out.println("Tipo: " + rs.getString("tipo_especialidade"));
                } else {
                    System.out.println("Especialidade não encontrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarEspecialista(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Matrícula do Especialista para consulta ou 'todos' para exibir todos: ");
            String input = scanner.next(); // Lê a matrícula do especialista ou a palavra 'todos'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todos"); // Verifica se o usuário digitou 'todos'

            if (isAll) {
                sql = "SELECT * FROM Especialista"; // Consulta para exibir todos os especialistas
            } else {
                sql = "SELECT * FROM Especialista WHERE matricula_especialista = ?"; // Consulta para exibir um especialista específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro da matrícula do especialista se não for para exibir todos
                if (!isAll) {
                    stmt.setString(1, input); // Usa a entrada do usuário como parâmetro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada especialista encontrado
                if (isAll) {
                    while (rs.next()) {
                        System.out.println("Nome: " + rs.getString("nome_especialista"));
                        System.out.println("Matrícula: " + rs.getString("matricula_especialista"));
                        System.out.println("ID Especialidade: " + rs.getString("id_especialidade"));
                        System.out.println("Data de Admissão: " + rs.getDate("admissao_especialista"));
                        System.out.println("-------------------------"); // Separador entre especialistas
                    }
                } else if (rs.next()) {
                    System.out.println("Nome: " + rs.getString("nome_especialista"));
                    System.out.println("ID Especialidade: " + rs.getString("id_especialidade"));
                    System.out.println("Data de Admissão: " + rs.getDate("admissao_especialista"));
                } else {
                    System.out.println("Especialista não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarVeterinario(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID do Veterinário para consulta ou 'todos' para exibir todos: ");
            String input = scanner.next(); // Lê o ID do veterinário ou a palavra 'todos'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todos"); // Verifica se o usuário digitou 'todos'

            if (isAll) {
                sql = "SELECT * FROM Veterinario"; // Consulta para exibir todos os veterinários
            } else {
                sql = "SELECT * FROM Veterinario WHERE id_veterinario = ?"; // Consulta para exibir um veterinário específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID do veterinário se não for para exibir todos
                if (!isAll) {
                    stmt.setInt(1, Integer.parseInt(input)); // Usa a entrada do usuário como parâmetro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada veterinário encontrado
                if (isAll) {
                    while (rs.next()) {
                        System.out.println("ID do Veterinário: " + rs.getInt("id_veterinario"));
                        System.out.println("Matrícula do Especialista: " + rs.getString("matricula_especialista"));
                        System.out.println("CRMV: " + rs.getString("CRMV"));
                        System.out.println("-------------------------"); // Separador entre veterinários
                    }
                } else if (rs.next()) {
                    System.out.println("Matrícula do Especialista: " + rs.getString("matricula_especialista"));
                    System.out.println("CRMV: " + rs.getString("CRMV"));
                } else {
                    System.out.println("Veterinário não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarCategoriaItem(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID da Categoria de Item para consulta ou 'todas' para exibir todas: ");
            String input = scanner.next(); // Lê o ID da categoria ou a palavra 'todas'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todas"); // Verifica se o usuário digitou 'todas'

            if (isAll) {
                sql = "SELECT * FROM CategoriaItem"; // Consulta para exibir todas as categorias
            } else {
                sql = "SELECT * FROM CategoriaItem WHERE id_categoria = ?"; // Consulta para exibir uma categoria específica
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID da categoria se não for para exibir todas
                if (!isAll) {
                    stmt.setString(1, input); // Usa a entrada do usuário como parâmetro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada categoria encontrada
                if (isAll) {
                    System.out.println("Categorias de Itens:");
                    while (rs.next()) {
                        System.out.printf("ID: %s, Descrição: %s, Tipo: %s%n",
                                rs.getString("id_categoria"), rs.getString("descricao_categoria"), rs.getString("tipo_categoria"));
                    }
                } else if (rs.next()) {
                    System.out.printf("ID: %s, Descrição: %s, Tipo: %s%n",
                            rs.getString("id_categoria"), rs.getString("descricao_categoria"), rs.getString("tipo_categoria"));
                } else {
                    System.out.println("Categoria não encontrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarItem(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID do Item para consulta ou 'todos' para exibir todos: ");
            String input = scanner.next(); // Lê o ID do item ou a palavra 'todos'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todos"); // Verifica se o usuário digitou 'todos'

            if (isAll) {
                sql = "SELECT * FROM Item"; // Consulta para exibir todos os itens
            } else {
                sql = "SELECT * FROM Item WHERE id_item = ?"; // Consulta para exibir um item específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID do item se não for para exibir todos
                if (!isAll) {
                    stmt.setString(1, input); // Usa a entrada do usuário como parâmetro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada item encontrado
                if (isAll) {
                    System.out.println("Itens:");
                    while (rs.next()) {
                        System.out.printf("ID: %s, Categoria: %s, Nome: %s, Custo: %.2f%n",
                                rs.getString("id_item"), rs.getString("id_categoria"), rs.getString("nome_item"), rs.getDouble("custo_item"));
                    }
                } else if (rs.next()) {
                    System.out.printf("ID: %s, Categoria: %s, Nome: %s, Custo: %.2f%n",
                            rs.getString("id_item"), rs.getString("id_categoria"), rs.getString("nome_item"), rs.getDouble("custo_item"));
                } else {
                    System.out.println("Item não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarProduto(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID do Produto para consulta ou 'todos' para exibir todos: ");
            String input = scanner.next(); // Lê o ID do produto ou a palavra 'todos'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todos"); // Verifica se o usuário digitou 'todos'

            if (isAll) {
                sql = "SELECT * FROM Produto"; // Consulta para exibir todos os produtos
            } else {
                sql = "SELECT * FROM Produto WHERE id_produto = ?"; // Consulta para exibir um produto específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID do produto se não for para exibir todos
                if (!isAll) {
                    stmt.setString(1, input); // Usa a entrada do usuário como parâmetro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada produto encontrado
                if (isAll) {
                    System.out.println("Produtos:");
                    while (rs.next()) {
                        System.out.printf("ID: %d, ID Item: %s, Quantidade em Estoque: %d%n",
                                rs.getInt("id_produto"), rs.getString("id_item"), rs.getInt("qtd_estoque"));
                    }
                } else if (rs.next()) {
                    System.out.printf("ID: %d, ID Item: %s, Quantidade em Estoque: %d%n",
                            rs.getInt("id_produto"), rs.getString("id_item"), rs.getInt("qtd_estoque"));
                } else {
                    System.out.println("Produto não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarServico(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID do Serviço para consulta ou 'todos' para exibir todos: ");
            String input = scanner.next(); // Lê o ID do serviço ou a palavra 'todos'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todos"); // Verifica se o usuário digitou 'todos'

            if (isAll) {
                sql = "SELECT * FROM Servico"; // Consulta para exibir todos os serviços
            } else {
                sql = "SELECT * FROM Servico WHERE id_servico = ?"; // Consulta para exibir um serviço específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID do serviço se não for para exibir todos
                if (!isAll) {
                    stmt.setString(1, input); // Usa a entrada do usuário como parâmetro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada serviço encontrado
                if (isAll) {
                    System.out.println("Serviços:");
                    while (rs.next()) {
                        System.out.printf("ID: %d, ID Item: %s, Duração: %s%n",
                                rs.getInt("id_servico"), rs.getString("id_item"), rs.getTime("duracao_servico"));
                    }
                } else if (rs.next()) {
                    System.out.printf("ID: %d, ID Item: %s, Duração: %s%n",
                            rs.getInt("id_servico"), rs.getString("id_item"), rs.getTime("duracao_servico"));
                } else {
                    System.out.println("Serviço não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarNotaFiscal(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID da Nota Fiscal para consulta ou 'todas' para exibir todas: ");
            String input = scanner.next(); // Lê o ID da nota fiscal ou a palavra 'todas'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todas"); // Verifica se o usuário digitou 'todas'

            if (isAll) {
                sql = "SELECT * FROM NotaFiscal"; // Consulta para exibir todas as notas fiscais
            } else {
                sql = "SELECT * FROM NotaFiscal WHERE id_notaFiscal = ?"; // Consulta para exibir uma nota fiscal específica
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID da nota fiscal se não for para exibir todas
                if (!isAll) {
                    stmt.setString(1, input); // Usa a entrada do usuário como parâmetro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada nota fiscal encontrada
                if (isAll) {
                    System.out.println("Notas Fiscais:");
                    while (rs.next()) {
                        System.out.printf("ID: %s, CPF Cliente: %s, Matricula Atendente: %d, Data: %s, Hora: %s, Tipo: %s%n",
                                rs.getString("id_notaFiscal"), rs.getString("cpf_cliente"), rs.getInt("matricula_atendente"),
                                rs.getDate("data_notaFiscal"), rs.getTime("hora_notaFiscal"), rs.getString("tipo_notaFiscal"));
                    }
                } else if (rs.next()) {
                    System.out.printf("ID: %s, CPF Cliente: %s, Matricula Atendente: %d, Data: %s, Hora: %s, Tipo: %s%n",
                            rs.getString("id_notaFiscal"), rs.getString("cpf_cliente"), rs.getInt("matricula_atendente"),
                            rs.getDate("data_notaFiscal"), rs.getTime("hora_notaFiscal"), rs.getString("tipo_notaFiscal"));
                } else {
                    System.out.println("Nota Fiscal não encontrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

    private static void consultarAtendimento(Connection conn) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ID do Atendimento para consulta ou 'todas' para exibir todos: ");
            String input = scanner.next(); // Lê o ID do atendimento ou a palavra 'todas'

            String sql;
            boolean isAll = input.equalsIgnoreCase("todas"); // Verifica se o usuário digitou 'todas'

            if (isAll) {
                sql = "SELECT * FROM Atendimento"; // Consulta para exibir todos os atendimentos
            } else {
                sql = "SELECT * FROM Atendimento WHERE id_atendimento = ?"; // Consulta para exibir um atendimento específico
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Define o parâmetro do ID do atendimento se não for para exibir todos
                if (!isAll) {
                    stmt.setInt(1, Integer.parseInt(input)); // Converte a entrada do usuário para inteiro
                }

                ResultSet rs = stmt.executeQuery(); // Executa a consulta e armazena os resultados

                // Verifica se existem resultados e exibe cada atendimento encontrado
                if (isAll) {
                    System.out.println("Atendimentos:");
                    while (rs.next()) {
                        System.out.printf("ID: %d, Matrícula Especialista: %s, Matrícula Pet: %d, ID Serviço: %d, CPF Cliente: %s, Data Marcação: %s, Data Atendimento: %s, Hora Atendimento: %s%n",
                                rs.getInt("id_atendimento"), rs.getString("matricula_especialista"), rs.getInt("matricula_pet"),
                                rs.getInt("id_servico"), rs.getString("cpf_cliente"), rs.getDate("data_marcacao"),
                                rs.getDate("data_atendimento"), rs.getTime("hora_atendimento"));
                    }
                } else if (rs.next()) {
                    System.out.printf("ID: %d, Matrícula Especialista: %s, Matrícula Pet: %d, ID Serviço: %d, CPF Cliente: %s, Data Marcação: %s, Data Atendimento: %s, Hora Atendimento: %s%n",
                            rs.getInt("id_atendimento"), rs.getString("matricula_especialista"), rs.getInt("matricula_pet"),
                            rs.getInt("id_servico"), rs.getString("cpf_cliente"), rs.getDate("data_marcacao"),
                            rs.getDate("data_atendimento"), rs.getTime("hora_atendimento"));
                } else {
                    System.out.println("Atendimento não encontrado.");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Exibe erros SQL, caso ocorram
            }

            // Opção para voltar ao menu de consultas, ao menu principal ou sair
            int mainOption;
            do {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Voltar ao Menu de Consultas");
                System.out.println("2. Voltar ao Menu Principal");
                System.out.println("3. Sair"); // Opção para sair do programa
                System.out.print("Escolha uma opção: ");
                mainOption = scanner.nextInt(); // Captura a opção do usuário

                if (mainOption == 1) {
                    menuConsultas(conn); // Chama o menu de consultas
                } else if (mainOption == 2) {
                    return; // Sai do método e volta ao menu principal
                } else if (mainOption == 3) {
                    System.out.println("Saindo do programa..."); // Mensagem de saída
                    System.exit(0); // Encerra o programa
                } else {
                    System.out.println("Opção inválida. Tente novamente."); // Mensagem para opção inválida
                }
            } while (mainOption < 1 || mainOption > 3); // Repete até uma opção válida ser escolhida
        }
    }

}


