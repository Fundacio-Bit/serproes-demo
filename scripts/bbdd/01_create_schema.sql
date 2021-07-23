DROP DATABASE serproes;
--
-- Name: serproes; Type: DATABASE; Schema: -; Owner: postgres
--

-- psql -U postgres postgres

CREATE DATABASE serproes 
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'es_ES.UTF-8'
LC_CTYPE = 'es_ES.UTF-8'
CONNECTION LIMIT = -1;

ALTER DATABASE serproes OWNER TO postgres;
ALTER DATABASE serproes SET client_encoding='UTF8';

CREATE ROLE serproes LOGIN
ENCRYPTED PASSWORD 'serproes'
NOSUPERUSER NOINHERIT NOCREATEDB NOCREATEROLE;

ALTER DATABASE serproes OWNER to serproes;


--
-- Name: ser_cuadri; Type: TABLE; 
--

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


--
-- Name: ser_illa; Type: TABLE; 
--

CREATE TABLE public.ser_illa (
    "ILA_CODI" bigint NOT NULL,
    "ILA_NOM" character varying(255)
);

ALTER TABLE ONLY public.ser_illa
    ADD CONSTRAINT ser_illa_pk PRIMARY KEY ("ILA_CODI");


CREATE INDEX ser_illa_nom_i ON public.ser_illa USING btree ("ILA_NOM");

CREATE UNIQUE INDEX ser_illa_pk_i ON public.ser_illa USING btree ("ILA_CODI");


--
-- Name: ser_illot; Type: TABLE; 
--

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




--
-- Name: ser_cuaila; Type: TABLE; 
--

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



--
-- Name: ser_cuaill; Type: TABLE; 
--

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



--
-- Name: ser_munici; Type: TABLE; 
--

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




--
-- Name: ser_cuamun; Type: TABLE; 
--

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



--
-- Name: ser_grupo; Type: TABLE; 
--

CREATE TABLE public.ser_grupo (
    "GRU_CODI" integer,
    "GRU_NOMBRE" character varying(255),
    "GRU_BORRAD" character(1) DEFAULT 'N'::bpchar,
    "GRU_TIPO" character varying(50)
);


CREATE INDEX ser_grupo_nombre ON public.ser_grupo USING btree ("GRU_NOMBRE");

CREATE UNIQUE INDEX ser_grupo_pk ON public.ser_grupo USING btree ("GRU_CODI");

CREATE INDEX ser_grupo_tipo ON public.ser_grupo USING btree ("GRU_TIPO");



--
-- Name: ser_famili; Type: TABLE; 
--

CREATE TABLE public.ser_famili (
    "FAM_CODI" integer,
    "FAM_NOMBRE" character varying(50),
    "FAM_CODGRU" integer,
    "FAM_BORRAD" character(1),
    CONSTRAINT ser_fam_bor CHECK (("FAM_BORRAD" = ANY (ARRAY['S'::bpchar, 'N'::bpchar])))
);

CREATE INDEX ser_famili_nombre ON public.ser_famili USING btree ("FAM_NOMBRE");

CREATE UNIQUE INDEX ser_famili_pk ON public.ser_famili USING btree ("FAM_CODI");


ALTER TABLE ONLY public.ser_famili
    ADD CONSTRAINT "SER_FAMGR_FK" FOREIGN KEY ("FAM_CODGRU") REFERENCES public.ser_grupo("GRU_CODI");



--
-- Name: ser_especi; Type: TABLE; 
--

CREATE TABLE public.ser_especi (
    "ESP_CODI" double precision,
    "ESP_CODFAM" integer,
    "ESP_TAXON" character varying(60),
    "ESP_NOMCOM" character varying(200),
    "ESP_CATALO" character(1),
    "ESP_AMENAZ" character(1),
    "ESP_ENDEMI" double precision,
    "ESP_BORRAD" character(1),
    "ESP_VIS1X1" character(1) DEFAULT 'S'::bpchar,
    "ESP_NOMCOMES" character varying(200),
    CONSTRAINT ser_esp_bor CHECK (("ESP_BORRAD" = ANY (ARRAY['S'::bpchar, 'N'::bpchar])))
);
CREATE INDEX ser_especi_nomcom ON public.ser_especi USING btree ("ESP_NOMCOM");

CREATE INDEX ser_especi_nomcomes ON public.ser_especi USING btree ("ESP_NOMCOMES");

CREATE UNIQUE INDEX ser_especi_pk ON public.ser_especi USING btree ("ESP_CODI");

CREATE INDEX ser_especi_taxon ON public.ser_especi USING btree ("ESP_TAXON");

ALTER TABLE ONLY public.ser_especi
    ADD CONSTRAINT "SER_ESPFAM_FK" FOREIGN KEY ("ESP_CODFAM") REFERENCES public.ser_famili("FAM_CODI");


--
-- Name: ser_fuente; Type: TABLE; 
--

CREATE TABLE public.ser_fuente (
    "FUE_CODI" bigint,
    "FUE_AUTOR" character varying(255),
    "FUE_ANYO" timestamp(0) without time zone,
    "FUE_REFERE" character varying(4000),
    "FUE_CODTIF" integer,
    "FUE_BORRAD" character(1),
    CONSTRAINT ser_fue_bor CHECK (("FUE_BORRAD" = ANY (ARRAY['S'::bpchar, 'N'::bpchar]))),
    CONSTRAINT ser_fue_ref CHECK (("FUE_REFERE" IS NOT NULL))
);


CREATE INDEX ser_fuente_autor ON public.ser_fuente USING btree ("FUE_AUTOR");

CREATE UNIQUE INDEX ser_fuente_pk ON public.ser_fuente USING btree ("FUE_CODI");

CREATE INDEX ser_fuente_refere ON public.ser_fuente USING btree ("FUE_REFERE");



--
-- Name: ser_hisesp; Type: TABLE; 
--

CREATE TABLE public.ser_hisesp (
    "HIS_CODI" double precision,
    "HIS_SINTAX" character varying(50),
    "HIS_CODESP" double precision,
    "HIS_BORRAD" character(1),
    CONSTRAINT ser_his_bor CHECK (("HIS_BORRAD" = ANY (ARRAY['S'::bpchar, 'N'::bpchar])))
);

CREATE UNIQUE INDEX ser_hisesp_pk ON public.ser_hisesp USING btree ("HIS_CODI");

ALTER TABLE ONLY public.ser_hisesp
    ADD CONSTRAINT "SER_HISESP_FK" FOREIGN KEY ("HIS_CODESP") REFERENCES public.ser_especi("ESP_CODI");



--
-- Name: ser_parame; Type: TABLE; 
--

CREATE TABLE public.ser_parame (
    "PAR_CODI" integer,
    "PAR_NOMBRE" character varying(50),
    "PAR_VALOR" character varying(100),
    "PAR_DESCRI" character varying(100),
    "PAR_BORRAD" character(1),
    CONSTRAINT ser_par_bor CHECK (("PAR_BORRAD" = ANY (ARRAY['S'::bpchar, 'N'::bpchar])))
);

CREATE UNIQUE INDEX ser_parame_pk ON public.ser_parame USING btree ("PAR_CODI");


--
-- Name: ser_regist; Type: TABLE; 
--

CREATE TABLE public.ser_regist (
    "REG_CODI" double precision,
    "REG_CODESP" double precision,
    "REG_FECBD" timestamp(0) without time zone,
    "REG_DESCON" character(1),
    "REG_CODTIR" integer,
    "REG_FECHA" timestamp(0) without time zone,
    "REG_CODFUE" integer,
    "REG_CODCUA" bigint,
    "REG_BORRAD" character(1),
    "REG_XDETAL" double precision,
    "REG_YDETAL" double precision,
    "REG_FORFEC" character varying(10) DEFAULT 'dd/MM/yyyy'::character varying,
    "REG_TIPCUA" character varying(5) DEFAULT NULL::character varying,
    "ERROR_OLD_DATA" character varying(255),
    "REG_CODILL" bigint,
    CONSTRAINT ser_reg_bor CHECK (("REG_BORRAD" = ANY (ARRAY['S'::bpchar, 'N'::bpchar])))
);


CREATE INDEX ser_regist_codcua ON public.ser_regist USING btree ("REG_CODCUA");

CREATE INDEX ser_regist_codesp ON public.ser_regist USING btree ("REG_CODESP");


CREATE INDEX ser_regist_codfue ON public.ser_regist USING btree ("REG_CODFUE");


CREATE INDEX ser_regist_codill_i ON public.ser_regist USING btree ("REG_CODILL");


CREATE INDEX ser_regist_codtir ON public.ser_regist USING btree ("REG_CODTIR");


CREATE INDEX ser_regist_fecha ON public.ser_regist USING btree ("REG_FECHA");


CREATE UNIQUE INDEX ser_registro_pk ON public.ser_regist USING btree ("REG_CODI");


ALTER TABLE ONLY public.ser_regist
    ADD CONSTRAINT "SER_REGIST_FK01" FOREIGN KEY ("REG_CODESP") REFERENCES public.ser_especi("ESP_CODI");


ALTER TABLE ONLY public.ser_regist
    ADD CONSTRAINT "SER_REGIST_FK02" FOREIGN KEY ("REG_CODFUE") REFERENCES public.ser_fuente("FUE_CODI");


ALTER TABLE ONLY public.ser_regist
    ADD CONSTRAINT "SER_REGIST_FK03" FOREIGN KEY ("REG_CODCUA") REFERENCES public.ser_cuadri("CUA_CODI");


ALTER TABLE ONLY public.ser_regist
    ADD CONSTRAINT "SER_REGIST_ILLOT_FK" FOREIGN KEY ("REG_CODILL") REFERENCES public.ser_illot("ILL_CODI");





CREATE SEQUENCE public.ser_seqesp



ALTER TABLE public.ser_seqesp OWNER TO postgres;

--
-- Name: ser_seqfam; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ser_seqfam
    START WITH 3640
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 20;


ALTER TABLE public.ser_seqfam OWNER TO postgres;

--
-- Name: ser_seqfue; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ser_seqfue
    START WITH 6880
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 20;


ALTER TABLE public.ser_seqfue OWNER TO postgres;

--
-- Name: ser_seqgru; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ser_seqgru
    START WITH 290
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 20;


ALTER TABLE public.ser_seqgru OWNER TO postgres;

--
-- Name: ser_seqgrup; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ser_seqgrup
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 20;


ALTER TABLE public.ser_seqgrup OWNER TO postgres;

--
-- Name: ser_seqhis; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ser_seqhis
    START WITH 18960
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 20;


ALTER TABLE public.ser_seqhis OWNER TO postgres;

--
-- Name: ser_seqpar; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ser_seqpar
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 20;


ALTER TABLE public.ser_seqpar OWNER TO postgres;

--
-- Name: ser_seqqua; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ser_seqqua
    START WITH 9907
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 20;


ALTER TABLE public.ser_seqqua OWNER TO postgres;

--
-- Name: ser_seqreg; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ser_seqreg
    START WITH 213781
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9999999999999
    CACHE 20;


ALTER TABLE public.ser_seqreg OWNER TO postgres;

--
-- Name: ser_seqesp; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqesp', 19300, false);


--
-- Name: ser_seqfam; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqfam', 3640, false);


--
-- Name: ser_seqfue; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqfue', 6880, false);


--
-- Name: ser_seqgru; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqgru', 290, false);


--
-- Name: ser_seqgrup; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqgrup', 1, false);


--
-- Name: ser_seqhis; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqhis', 18960, false);


--
-- Name: ser_seqpar; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqpar', 3, false);


--
-- Name: ser_seqqua; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqqua', 9907, false);


--
-- Name: ser_seqreg; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ser_seqreg', 213781, false);



