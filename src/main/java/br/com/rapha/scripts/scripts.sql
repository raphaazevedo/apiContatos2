--criar a tabela de categorias
create table categoria(
	id			uuid			primary key,
	nome		varchar(25)		not null unique
);
--criar a tabela de contatos
create table contato(
	id			uuid			primary key,
	nome		varchar(150)	not null,
	telefone	varchar(15)		not null,
	email		varchar(50)		not null,
	categoria_id	uuid			not null,
	foreign key(categoria_id)
		references categoria(id)
);
--cadastrar categoriass
insert into categoria(id, nome) 
values(gen_random_uuid(), 'Familiar');
insert into categoria(id, nome) 
values(gen_random_uuid(), 'Escolar');
insert into categoria(id, nome) 
values(gen_random_uuid(), 'Trabalho');
insert into categoria(id, nome) 
values(gen_random_uuid(), 'Academia');
