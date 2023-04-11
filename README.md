
# Software de cadastro de licenças com relatório

A aplicação de software desktop foi desenvolvida em linguagem Java com a interface gráfica do usuário criada utilizando a biblioteca JavaFX. Foi utilizado o banco de dados Postgres e a geração de relatórios foi feita através da ferramenta Jasper.

O propósito do software é possibilitar o cadastro de certificações completadas por técnicos em campo que realizam a instalação de equipamentos de telecomunicação para empresas de telecomunicações brasileiras.


## Funcionalidade
- Cadastro de pessoas
- Cadastro de treinamentos
- Registo de treinamentos concluídos
- Geração de relatórios em PDF
## Demonstração

![](https://github.com/welyson1/atividadePratica01/blob/c7e6bb7ec3b7b0b92051fa6f10632ac415bd71c7/docs/demo.gif)
## Instalação

1. Clone este repositório
2. Abra no vscode
3. Instale as bibliotecas
4. Crie o banco de dados postgres
5. Crie as tabelas com query abaixo
```SQL
  create table recurso(
	id serial primary key,
	recurso_nome varchar(100) not null,
	recurso_email varchar(100) not null,
	recurso_projeto varchar(50) default 'SEM PROJETO'	
);

create table projeto(
	id serial primary key,
	projeto_nome varchar(100) not null,
	projeto_tecnologia varchar(100) not null,
	projeto_valor REAL not null
);

create table licencasNecessarias(
	id serial primary key,
	lnecessaria_nome varchar(100) not null,
	lnecessaria_link varchar(200) not null,
	lnecessaria_categoria varchar(50),
	lnecessaria_level varchar(10) not null	
);

create table licencasObtidas(
	id serial primary key,
	lobtida_nome varchar(100) not null,
	lobtida_recurso_email varchar(100) not null,
	lobtida_conclusao varchar(20) not null
)
```
6. Execute o arquivo `\src\view\Main.java` para iniciar