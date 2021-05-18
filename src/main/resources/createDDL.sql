CREATE TABLE public.pessoa (
	id int4 NOT NULL,
	nome varchar(255) NOT NULL,
	email varchar(50) NOT NULL
);

CREATE SEQUENCE public.gerador_pessoa_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
INSERT INTO public.pessoa
(id, nome, email)
VALUES(1, 'Telmo Q Silva', 'telmoqs@gmail.com');
