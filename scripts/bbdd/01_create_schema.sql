DROP DATABASE serproes;

CREATE DATABASE serproes
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'Spanish_Spain.1252'
LC_CTYPE = 'Spanish_Spain.1252'
CONNECTION LIMIT = -1;

ALTER DATABASE serproes OWNER TO postgres;
ALTER DATABASE serproes SET client_encoding='UTF8';

CREATE ROLE serproes LOGIN
ENCRYPTED PASSWORD 'serproes'
NOSUPERUSER NOINHERIT NOCREATEDB NOCREATEROLE;

ALTER DATABASE serproes OWNER to serproes;

\c serproes;

CREATE TABLE public.ser_cuadri (
    "CUA_CODI" bigint,
    "CUA_X" integer,
    "CUA_Y" integer,
    "CUA_LONGIT" character varying(255),
    "CUA_LATITU" character varying(255),
    "CUA_CODISL" integer,
    "CUA_BORRAD" character(1),
    "CUA_CODQ5" bigint,
    "CUA_CODQ10" bigint,
    CONSTRAINT ser_cua_bor CHECK (("CUA_BORRAD" = ANY (ARRAY['S'::bpchar, 'N'::bpchar]))),
    CONSTRAINT "ser_cuadri_CUA_CODISL_check" CHECK (("CUA_CODISL" IS NOT NULL)),
    CONSTRAINT "ser_cuadri_CUA_CODISL_check1" CHECK (("CUA_CODISL" IS NOT NULL))
);

CREATE INDEX ser_cuadri_cuax ON public.ser_cuadri USING btree ("CUA_X");

CREATE INDEX ser_cuadri_cuay ON public.ser_cuadri USING btree ("CUA_Y");

CREATE UNIQUE INDEX ser_cuadri_pk ON public.ser_cuadri USING btree ("CUA_CODI");


CREATE TABLE public.ser_illa (
    "ILA_CODI" bigint NOT NULL,
    "ILA_NOM" character varying(255)
);

ALTER TABLE ONLY public.ser_illa
    ADD CONSTRAINT ser_illa_pk PRIMARY KEY ("ILA_CODI");


CREATE INDEX ser_illa_nom_i ON public.ser_illa USING btree ("ILA_NOM");

CREATE UNIQUE INDEX ser_illa_pk_i ON public.ser_illa USING btree ("ILA_CODI");


CREATE TABLE public.ser_illot (
    "ILL_CODI" bigint NOT NULL,
    "ILL_NOM" character varying(200),
    "ILL_X" double precision,
    "ILL_Y" double precision,
    "ILL_GRUP" character varying(50),
    "ILL_PERI" double precision,
    "ILL_AREA" double precision
);

ALTER TABLE ONLY public.ser_illot
    ADD CONSTRAINT ser_illot_pk PRIMARY KEY ("ILL_CODI");

CREATE INDEX ser_illot_nom_i ON public.ser_illot USING btree ("ILL_NOM");

CREATE UNIQUE INDEX ser_illot_pk_i ON public.ser_illot USING btree ("ILL_CODI");


CREATE TABLE public.ser_cuaila (
    "CUL_CODILA" bigint,
    "CUL_CODCUA" bigint
);

CREATE INDEX ser_cuaila_cuadri_fk_i ON public.ser_cuaila USING btree ("CUL_CODCUA");

CREATE INDEX ser_cuaila_illa_fk_i ON public.ser_cuaila USING btree ("CUL_CODILA");


CREATE UNIQUE INDEX ser_cuaila_pk_i ON public.ser_cuaila USING btree ("CUL_CODCUA", "CUL_CODILA");

ALTER TABLE ONLY public.ser_cuaila
    ADD CONSTRAINT "SER_CUAILA_CUADRI_FK" FOREIGN KEY ("CUL_CODCUA") REFERENCES public.ser_cuadri("CUA_CODI");


ALTER TABLE ONLY public.ser_cuaila
    ADD CONSTRAINT "SER_CUAILA_ILLA_FK" FOREIGN KEY ("CUL_CODILA") REFERENCES public.ser_illa("ILA_CODI");



CREATE TABLE public.ser_cuaill (
    "CUI_CODILL" bigint,
    "CUI_CODCUA" bigint
);

CREATE INDEX ser_cuaill_codcua_i ON public.ser_cuaill USING btree ("CUI_CODCUA");

CREATE INDEX ser_cuaill_codill_i ON public.ser_cuaill USING btree ("CUI_CODILL");

CREATE UNIQUE INDEX ser_cuaill_pk_i ON public.ser_cuaill USING btree ("CUI_CODCUA", "CUI_CODILL");

ALTER TABLE ONLY public.ser_cuaill
    ADD CONSTRAINT "SER_CUAILL_CUADRI_FK" FOREIGN KEY ("CUI_CODCUA") REFERENCES public.ser_cuadri("CUA_CODI");


ALTER TABLE ONLY public.ser_cuaill
    ADD CONSTRAINT "SER_CUAILL_ILLOT_FK" FOREIGN KEY ("CUI_CODILL") REFERENCES public.ser_illot("ILL_CODI");



CREATE TABLE public.ser_munici (
    "MUN_CODI" character varying(3) NOT NULL,
    "MUN_NOM" character varying(255),
    "MUN_CODILA" bigint
);

ALTER TABLE ONLY public.ser_munici
    ADD CONSTRAINT ser_munici_pk PRIMARY KEY ("MUN_CODI");

CREATE INDEX ser_munici_illa_fk_i ON public.ser_munici USING btree ("MUN_CODILA");

CREATE INDEX ser_munici_nom_i ON public.ser_munici USING btree ("MUN_NOM");

CREATE UNIQUE INDEX ser_munici_pk_i ON public.ser_munici USING btree ("MUN_CODI");

ALTER TABLE ONLY public.ser_munici
    ADD CONSTRAINT "SER_MUNICI_ILLA_FK" FOREIGN KEY ("MUN_CODILA") REFERENCES public.ser_illa("ILA_CODI");



CREATE TABLE public.ser_cuamun (
    "CUM_CODMUN" character varying(3) NOT NULL,
    "CUM_CODCUA" bigint NOT NULL
);

ALTER TABLE ONLY public.ser_cuamun
    ADD CONSTRAINT ser_cuamun_pk PRIMARY KEY ("CUM_CODCUA", "CUM_CODMUN");

CREATE INDEX ser_cuamun_cuadri_fk_i ON public.ser_cuamun USING btree ("CUM_CODCUA");

CREATE INDEX ser_cuamun_munici_fk_i ON public.ser_cuamun USING btree ("CUM_CODMUN");

CREATE UNIQUE INDEX ser_cuamun_pk_i ON public.ser_cuamun USING btree ("CUM_CODCUA", "CUM_CODMUN");

ALTER TABLE ONLY public.ser_cuamun
    ADD CONSTRAINT "SER_CUAMUN_CUADRI_FK" FOREIGN KEY ("CUM_CODCUA") REFERENCES public.ser_cuadri("CUA_CODI");

ALTER TABLE ONLY public.ser_cuamun
    ADD CONSTRAINT "SER_CUAMUN_MUNICI_FK" FOREIGN KEY ("CUM_CODMUN") REFERENCES public.ser_munici("MUN_CODI");



CREATE TABLE public.ser_grupo (
    "GRU_CODI" integer,
    "GRU_NOMBRE" character varying(255),
    "GRU_BORRAD" character(1) DEFAULT 'N'::bpchar,
    "GRU_TIPO" character varying(50)
);


CREATE INDEX ser_grupo_nombre ON public.ser_grupo USING btree ("GRU_NOMBRE");

CREATE UNIQUE INDEX ser_grupo_pk ON public.ser_grupo USING btree ("GRU_CODI");

CREATE INDEX ser_grupo_tipo ON public.ser_grupo USING btree ("GRU_TIPO");
