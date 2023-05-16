# AgenciaBancariaJava

<h2>💻 Introdução</h2>

<p>O código fornecido é um programa em Java para uma aplicação bancária. Ele permite que os usuários realizem várias operações, como criar uma conta, depositar dinheiro, sacar dinheiro, transferir dinheiro entre contas e gerar um relatório de todas as contas.</p>

<p>Na opção 1 é usado para criar uma nova conta. Ele solicita informações ao usuário, como nome, CPF, data de nascimento e senha. Em seguida, cria um novo objeto Usuario (usuário) e um objeto Conta (conta) correspondente, e os adiciona às listas usuarios e contas, respectivamente. </p>

<p>Na opção 2 é usado para realizar uma operação de depósito. Ele solicita ao usuário o número da conta e o valor a ser depositado. Em seguida, ele procura a conta correspondente na lista contas e chama o método depositar da classe Conta para realizar o depósito.</p>

<p>Na opção 3 é usado para realizar uma operação de saque. Ele solicita ao usuário o número da conta e o valor a ser sacado. Em seguida, ele procura a conta correspondente na lista contas e chama o método sacar da classe Conta para realizar o saque.</p>

<p>Na opção 4 é usado para realizar uma operação de transferência entre duas contas. Ele solicita ao usuário os números das contas de origem e destino, bem como o valor a ser transferido. Em seguida, ele procura as contas correspondentes na lista contas e chama o método transferir da classe Conta para realizar a transferência.</p>

<p>Na opção 5 é usado para gerar um relatório de todas as contas. Ele percorre a lista contas e imprime os detalhes da conta usando o método toString da classe Conta.</p>

<p>A opção 0 finaliza o programa.</p>

