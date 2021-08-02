DROP DATABASE serproes;

CREATE DATABASE serproes
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'Spanish_Spain.1252'
LC_CTYPE = 'Spanish_Spain.1252'
CONNECTION LIMIT = -1;

ALTER DATABASE serproes OWNER TO postgres;
ALTER DATABASE serproes SET client_encoding='UTF8';

DROP ROLE serproes;

CREATE ROLE serproes LOGIN
ENCRYPTED PASSWORD 'serproes'
NOSUPERUSER NOINHERIT NOCREATEDB NOCREATEROLE;

ALTER DATABASE serproes OWNER to serproes;

\c serproes;
 
CREATE TABLE public.ser_illa
(
    ila_codi bigint NOT NULL,
    ila_nom character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT ser_illa_pkey PRIMARY KEY (ila_codi)
);

CREATE INDEX ser_illa_nom_i
    ON public.ser_illa USING btree
    (ila_nom COLLATE pg_catalog."default" ASC NULLS LAST);
	
CREATE TABLE public.ser_illot
(
    ill_codi bigint NOT NULL,
    ill_area double precision,
    ill_grup character varying(50) COLLATE pg_catalog."default",
    ill_nom character varying(200) COLLATE pg_catalog."default",
    ill_peri double precision,
    ill_x double precision,
    ill_y double precision,
    CONSTRAINT ser_illot_pkey PRIMARY KEY (ill_codi)
);

CREATE INDEX ser_illot_nom_i
    ON public.ser_illot USING btree
    (ill_nom COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
	
CREATE TABLE public.ser_munici
(
    mun_codi character varying(3) COLLATE pg_catalog."default" NOT NULL,
    mun_nom character varying(255) COLLATE pg_catalog."default",
    mun_codila bigint,
    CONSTRAINT ser_munici_pkey PRIMARY KEY (mun_codi),
    CONSTRAINT ser_munici_illa_fk FOREIGN KEY (mun_codila)
        REFERENCES public.ser_illa (ila_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE INDEX ser_munici_illa_fk_i
    ON public.ser_munici USING btree
    (mun_codila ASC NULLS LAST);

CREATE INDEX ser_munici_nom_i
    ON public.ser_munici USING btree
    (mun_nom COLLATE pg_catalog."default" ASC NULLS LAST);	
	
	
CREATE TABLE public.ser_cuadri
(
    cua_codi bigint NOT NULL,
    cua_borrad character varying(1) COLLATE pg_catalog."default",
    cua_codisl integer NOT NULL,
    cua_codq10 bigint,
    cua_codq5 bigint,
    cua_latitu character varying(255) COLLATE pg_catalog."default",
    cua_longit character varying(255) COLLATE pg_catalog."default",
    cua_x integer,
    cua_y integer,
    CONSTRAINT ser_cuadri_pkey PRIMARY KEY (cua_codi),
    CONSTRAINT ser_cuadri_cua_borrad_check CHECK (cua_borrad::text = ANY (ARRAY['S'::character varying, 'N'::character varying]::text[]))
);

ALTER TABLE public.ser_cuadri
    OWNER to serproes;

CREATE INDEX ser_cuadri_cuax_i
    ON public.ser_cuadri USING btree
    (cua_x ASC NULLS LAST);

CREATE INDEX ser_cuadri_cuay_i
    ON public.ser_cuadri USING btree
    (cua_y ASC NULLS LAST);

CREATE TABLE public.ser_cuaila
(
    cul_codcua bigint NOT NULL,
    cul_codila bigint NOT NULL,
    CONSTRAINT ser_cuaila_pkey PRIMARY KEY (cul_codcua, cul_codila),
    CONSTRAINT ser_cuaila_cuadri_fk FOREIGN KEY (cul_codcua)
        REFERENCES public.ser_cuadri (cua_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ser_cuaila_illa_fk FOREIGN KEY (cul_codila)
        REFERENCES public.ser_illa (ila_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX ser_cuaila_cuadri_fk_i
    ON public.ser_cuaila USING btree
    (cul_codcua ASC NULLS LAST);

CREATE INDEX ser_cuaila_illa_fk_i
    ON public.ser_cuaila USING btree
    (cul_codila ASC NULLS LAST);
	
CREATE TABLE IF NOT EXISTS public.ser_cuaill
(
    cui_codill bigint NOT NULL,
    cui_codcua bigint NOT NULL,
    CONSTRAINT ser_cuaill_pkey PRIMARY KEY (cui_codcua, cui_codill),
    CONSTRAINT ser_cuaill_cuadri_fk FOREIGN KEY (cui_codcua)
        REFERENCES public.ser_cuadri (cua_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ser_cuaill_illot_fk FOREIGN KEY (cui_codill)
        REFERENCES public.ser_illot (ill_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX ser_cuaill_codcua_i
    ON public.ser_cuaill USING btree
    (cui_codcua ASC NULLS LAST);

CREATE INDEX ser_cuaill_codill_i
    ON public.ser_cuaill USING btree
    (cui_codill ASC NULLS LAST);
	
CREATE TABLE public.ser_cuamun
(
    cum_codcua bigint NOT NULL,
    cum_codmun character varying(3) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ser_cuamun_pkey PRIMARY KEY (cum_codcua, cum_codmun),
    CONSTRAINT ser_cuamun_cuadri_fk FOREIGN KEY (cum_codcua)
        REFERENCES public.ser_cuadri (cua_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ser_cuamun_munici_fk FOREIGN KEY (cum_codmun)
        REFERENCES public.ser_munici (mun_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX ser_cuamun_cuadri_fk_i
    ON public.ser_cuamun USING btree
    (cum_codcua ASC NULLS LAST);

CREATE INDEX ser_cuamun_munici_fk_i
    ON public.ser_cuamun USING btree
    (cum_codmun COLLATE pg_catalog."default" ASC NULLS LAST);

CREATE TABLE public.ser_grupo
(
    gru_codi integer NOT NULL,
    gru_borrad character varying(255) COLLATE pg_catalog."default",
    gru_nombre character varying(255) COLLATE pg_catalog."default",
    gru_tipo character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT ser_grupo_pkey PRIMARY KEY (gru_codi)
);


CREATE INDEX ser_grupo_nombre_i
    ON public.ser_grupo USING btree
    (gru_nombre COLLATE pg_catalog."default" ASC NULLS LAST);

CREATE INDEX ser_grupo_tipo_i
    ON public.ser_grupo USING btree
    (gru_tipo COLLATE pg_catalog."default" ASC NULLS LAST);	
	
CREATE TABLE public.ser_famili
(
    fam_codi integer NOT NULL,
    fam_borrad character varying(1) COLLATE pg_catalog."default",
    fam_nombre character varying(255) COLLATE pg_catalog."default",
    fam_codgru integer,
    CONSTRAINT ser_famili_pkey PRIMARY KEY (fam_codi),
    CONSTRAINT ser_famgr_fk FOREIGN KEY (fam_codgru)
        REFERENCES public.ser_grupo (gru_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ser_famili_fam_borrad_check CHECK (fam_borrad::text = ANY (ARRAY['S'::character varying, 'N'::character varying]::text[]))
);

CREATE INDEX ser_famili_nombre_i
    ON public.ser_famili USING btree
    (fam_nombre COLLATE pg_catalog."default" ASC NULLS LAST);	
	
CREATE TABLE public.ser_fuente
(
    fue_codi bigint NOT NULL,
    fue_anyo timestamp without time zone,
    fue_autor character varying(255) COLLATE pg_catalog."default",
    fue_borrad character varying(1) COLLATE pg_catalog."default",
    fue_codtif integer,
    fue_refere character varying(500) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ser_fuente_pkey PRIMARY KEY (fue_codi),
    CONSTRAINT ser_fuente_fue_borrad_check CHECK (fue_borrad::text = ANY (ARRAY['S'::character varying, 'N'::character varying]::text[]))
);

CREATE INDEX ser__fuente_autor_i
    ON public.ser_fuente USING btree
    (fue_autor COLLATE pg_catalog."default" ASC NULLS LAST);

CREATE INDEX ser_fuente_refere_i
    ON public.ser_fuente USING btree
    (fue_refere COLLATE pg_catalog."default" ASC NULLS LAST);	

CREATE TABLE public.ser_especi
(
    esp_codi double precision NOT NULL,
    esp_amenaz character varying(255) COLLATE pg_catalog."default",
    esp_borrad character varying(1) COLLATE pg_catalog."default",
    esp_catalo character varying(255) COLLATE pg_catalog."default",
    esp_endemi double precision,
    esp_nomcom character varying(255) COLLATE pg_catalog."default",
    esp_nomcomes character varying(255) COLLATE pg_catalog."default",
    esp_taxon character varying(255) COLLATE pg_catalog."default",
    esp_vis1x1 character varying(1) COLLATE pg_catalog."default" DEFAULT 'S'::character varying,
    esp_codfam integer,
    CONSTRAINT ser_especi_pkey PRIMARY KEY (esp_codi),
    CONSTRAINT ser_espfam_fk FOREIGN KEY (esp_codfam)
        REFERENCES public.ser_famili (fam_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ser_especi_esp_borrad_check CHECK (esp_borrad::text = ANY (ARRAY['S'::character varying, 'N'::character varying]::text[]))
);

CREATE INDEX ser_especi_nomcom_i
    ON public.ser_especi USING btree
    (esp_nomcom COLLATE pg_catalog."default" ASC NULLS LAST);

CREATE INDEX ser_especi_nomcomes_i
    ON public.ser_especi USING btree
    (esp_nomcomes COLLATE pg_catalog."default" ASC NULLS LAST);

CREATE INDEX ser_especi_taxon_i
    ON public.ser_especi USING btree
    (esp_taxon COLLATE pg_catalog."default" ASC NULLS LAST);
	
CREATE TABLE public.ser_regist
(
    reg_codi double precision NOT NULL,
    error_old_data character varying(255) COLLATE pg_catalog."default",
    reg_borrad character varying(255) COLLATE pg_catalog."default",
    reg_codtir integer,
    reg_descon character varying(255) COLLATE pg_catalog."default",
    reg_fecbd timestamp without time zone,
    reg_fecha timestamp without time zone,
    reg_forfec character varying(255) COLLATE pg_catalog."default",
    reg_tipcua character varying(255) COLLATE pg_catalog."default",
    reg_xdetal double precision,
    reg_ydetal double precision,
    reg_codcua bigint,
    reg_codesp double precision,
    reg_codfue bigint,
    reg_codill bigint,
    CONSTRAINT ser_regist_pkey PRIMARY KEY (reg_codi),
    CONSTRAINT ser_regist_cua_fk FOREIGN KEY (reg_codcua)
        REFERENCES public.ser_cuadri (cua_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ser_regist_esp_fk FOREIGN KEY (reg_codesp)
        REFERENCES public.ser_especi (esp_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ser_regist_fue_fk FOREIGN KEY (reg_codfue)
        REFERENCES public.ser_fuente (fue_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ser_regist_ill_fk FOREIGN KEY (reg_codill)
        REFERENCES public.ser_illot (ill_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX ser_regist_codcua_i
    ON public.ser_regist USING btree
    (reg_codcua ASC NULLS LAST);

CREATE INDEX ser_regist_codesp_i
    ON public.ser_regist USING btree
    (reg_codesp ASC NULLS LAST);

CREATE INDEX ser_regist_codfue_i
    ON public.ser_regist USING btree
    (reg_codfue ASC NULLS LAST);

CREATE INDEX ser_regist_codill_i
    ON public.ser_regist USING btree
    (reg_codill ASC NULLS LAST);

CREATE INDEX ser_regist_codtir_i
    ON public.ser_regist USING btree
    (reg_codtir ASC NULLS LAST);

CREATE INDEX ser_regist_fecha_i
    ON public.ser_regist USING btree
    (reg_fecha ASC NULLS LAST);

CREATE TABLE public.ser_hisesp
(
    his_codi double precision NOT NULL,
    his_borrad character varying(255) COLLATE pg_catalog."default",
    his_sintax character varying(255) COLLATE pg_catalog."default",
    his_codesp double precision,
    CONSTRAINT ser_hisesp_pkey PRIMARY KEY (his_codi),
    CONSTRAINT ser_hisesp_fk FOREIGN KEY (his_codesp)
        REFERENCES public.ser_especi (esp_codi) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.ser_parame
(
    par_codi integer NOT NULL,
    par_borrad character varying(1) COLLATE pg_catalog."default",
    par_descri character varying(255) COLLATE pg_catalog."default",
    par_nombre character varying(255) COLLATE pg_catalog."default",
    par_valor character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT ser_parame_pkey PRIMARY KEY (par_codi),
    CONSTRAINT ser_parame_par_borrad_check CHECK (par_borrad::text = ANY (ARRAY['S'::character varying, 'N'::character varying]::text[]))
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO serproes;

CREATE SEQUENCE public.hibernate_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE public.ser_seqesp
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE public.ser_seqfam
    INCREMENT 1
    START 3640
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 20;

CREATE SEQUENCE public.ser_seqfue
    INCREMENT 1
    START 6880
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 20;

CREATE SEQUENCE public.ser_seqgru
    INCREMENT 1
    START 290
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 20;

CREATE SEQUENCE public.ser_seqgrup
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 20;

CREATE SEQUENCE public.ser_seqhis
    INCREMENT 1
    START 18960
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 20;

CREATE SEQUENCE public.ser_seqpar
    INCREMENT 1
    START 3
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 20;

CREATE SEQUENCE public.ser_seqqua
    INCREMENT 1
    START 9907
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 20;

CREATE SEQUENCE public.ser_seqreg
    INCREMENT 1
    START 213781
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 20;
