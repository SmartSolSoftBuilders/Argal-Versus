--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.6
-- Dumped by pg_dump version 9.1.6
-- Started on 2012-11-05 15:40:32 CST

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 166 (class 3079 OID 11681)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1917 (class 0 OID 0)
-- Dependencies: 166
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 161 (class 1259 OID 210871)
-- Dependencies: 6
-- Name: t_archivo_seq; Type: SEQUENCE; Schema: public; Owner: pruebas
--

-- CATALOGOS

-- Table: "TIPO_SEGURO"
-- DROP TABLE "TIPO_SEGURO";
CREATE TABLE "TIPO_SEGURO"
(
  "ID_TIPO_SEGURO" integer NOT NULL,
  "DESCRIPCION" character varying(45),
  CONSTRAINT "TIPO_SEGURO_pkey" PRIMARY KEY ("ID_TIPO_SEGURO")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "TIPO_SEGURO"
  OWNER TO postgres;

-- Table: "TIPO_EVENTO"
-- DROP TABLE "TIPO_EVENTO";
CREATE TABLE "TIPO_EVENTO"
(
  "ID_TIPO_EVENTO" integer NOT NULL,
  "DESCRIPCION" character varying(45),
  CONSTRAINT "TIPO_EVENTO_pkey" PRIMARY KEY ("ID_TIPO_EVENTO")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "TIPO_EVENTO"
  OWNER TO postgres;

-- Table: "MEDICO_TRATANTE"
-- DROP TABLE "MEDICO_TRATANTE";
CREATE TABLE "MEDICO_TRATANTE"
(
  "ID_MEDICO_TRATANTE" integer NOT NULL,
  "NOMBRE" character varying(120),
  "ID_ESPECIALIDAD_MEDICO_TRATANTE" integer,
  "TIPO_MED" integer,
  "ACTIVO" boolean DEFAULT true,
  CONSTRAINT "MEDICO_TRATANTE_pkey" PRIMARY KEY ("ID_MEDICO_TRATANTE")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "MEDICO_TRATANTE"
  OWNER TO postgres;

-- Index: "INDX_MEDICTR"

-- DROP INDEX "INDX_MEDICTR";

CREATE INDEX "INDX_MEDICTR"
  ON "MEDICO_TRATANTE"
  USING btree
  ("ID_MEDICO_TRATANTE");


-- Table: "IMPLANT"
-- DROP TABLE "IMPLANT";
CREATE TABLE "IMPLANT"
(
  "ID_IMPLANT" integer NOT NULL,
  "NOMBRE_IMPLANT" character varying(45),
  "APP_IMPLANT" character varying(45),
  "APM_IMPLANT" character varying(45),
  "NEXTEL_IMPLANT" character varying(45),
  "ID_NEXTEL" character varying(45),
  "CELULAR_IMPLANT" character varying(45),
  "TEL_OFI_IMPL" character varying(45),
  "PUESTO_IMPLANT" character varying(45),
  "EMAIL_INST_IMPLANT" character varying(55),
  "EMAIL_PERS_IMPLANT" character varying(55),
  "SUPER_MEDICO" boolean,
  "ACTIVO_IMPLANT" boolean,
  "NICK_IMPLANT" character varying(45),
  CONSTRAINT "IMPLANT_pkey" PRIMARY KEY ("ID_IMPLANT")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "IMPLANT"
  OWNER TO postgres;

-- Index: "INDX_IMPLANT"

-- DROP INDEX "INDX_IMPLANT";

CREATE INDEX "INDX_IMPLANT"
  ON "IMPLANT"
  USING btree
  ("ID_IMPLANT");


-- Table: "ICD"
-- DROP TABLE "ICD";
CREATE TABLE "ICD"
(
  "ID_ICD" integer NOT NULL,
  "DESCRIPCION" character varying(125),
  "ES_CATALOGO" boolean,
  "CVE_ICD" character varying(25),
  CONSTRAINT "ICD_pkey" PRIMARY KEY ("ID_ICD")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ICD"
  OWNER TO postgres;

-- Index: "INDX_ICD"

-- DROP INDEX "INDX_ICD";

CREATE INDEX "INDX_ICD"
  ON "ICD"
  USING btree
  ("ID_ICD");

-- Table: "HOSPITAL"
-- DROP TABLE "HOSPITAL";
CREATE TABLE "HOSPITAL"
(
  "ID_HOSP" integer NOT NULL,
  "NOMBRE_HOSP" character varying(55),
  "DIR_HOSP" character varying(90),
  "COL_HOSP" character varying(45),
  "MUN_DEL_HOSP" character varying(45),
  "ESTADO_HOSP" character varying(45),
  "CP_HOSP" character varying(45),
  "CONMUTADOR_HOSP" character varying(45),
  "ACTIVO_HOSP" boolean,
  "TEL_DIR_MOD_HOSP" character varying(45),
  "PAIS_HOSP" character varying(45),
  "CIUDAD_HOSP" character varying(45),
  CONSTRAINT "HOSPITAL_pkey" PRIMARY KEY ("ID_HOSP")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "HOSPITAL"
  OWNER TO postgres;

-- Index: "INDX_HOSP"

-- DROP INDEX "INDX_HOSP";

CREATE INDEX "INDX_HOSP"
  ON "HOSPITAL"
  USING btree
  ("ID_HOSP");



-- Table: "ESPECIALIDAD"
-- DROP TABLE "ESPECIALIDAD";
CREATE TABLE "ESPECIALIDAD"
(
  "ID_ESPECIALIDAD" integer NOT NULL,
  "DESCRIPCION" character varying(40),
  CONSTRAINT "PK_ESPECIALIDAD" PRIMARY KEY ("ID_ESPECIALIDAD")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ESPECIALIDAD"
  OWNER TO postgres;

-- Index: "INDEX_ESPECIALIDAD"

-- DROP INDEX "INDEX_ESPECIALIDAD";

CREATE INDEX "INDEX_ESPECIALIDAD"
  ON "ESPECIALIDAD"
  USING btree
  ("ID_ESPECIALIDAD");


-- Table: "CPT"
-- DROP TABLE "CPT";
CREATE TABLE "CPT"
(
  "ID_CPT" integer NOT NULL,
  "DESCRIPCION" character varying(125),
  "ES_CATALOGO" boolean,
  "CVE_CPT" character varying(30),
  CONSTRAINT "CPT_pkey" PRIMARY KEY ("ID_CPT")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "CPT"
  OWNER TO postgres;

-- Index: "INDX_CPT"

-- DROP INDEX "INDX_CPT";

CREATE INDEX "INDX_CPT"
  ON "CPT"
  USING btree
  ("ID_CPT");

-- Table: "CLIENTE"
-- DROP TABLE "CLIENTE";
CREATE TABLE "CLIENTE"
(
  "ID_CLIENTE" integer NOT NULL,
  "ACTIVO_CLIENTE" boolean,
  "RAZON_SOCIAL_CLIENTE" character varying(120),
  "DOMICILIO_CLIENTE" character varying(80),
  "CONMUTADOR_CLIENTE" character varying(50),
  "DIRECTOR_MEDICO_CLIENTE" character varying(90),
  "NOMBRE_CLIENTE" character varying(90),
  "TEL_OFICINA_CLIENTE" character varying(45),
  "TEL_CELULAR_CLIENTE" character varying(45),
  "EMAIL_CLIENTE" character varying(50),
  "NICK_CLIENTE" character varying(45),
  "NOMBRE_CORTO" character varying(35),
  CONSTRAINT "CLIENTE_pkey" PRIMARY KEY ("ID_CLIENTE")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "CLIENTE"
  OWNER TO postgres;

-- Index: "INDEX-CLIENTES"
-- DROP INDEX "INDEX-CLIENTES";
CREATE INDEX "INDEX-CLIENTES"
  ON "CLIENTE"
  USING btree
  ("ID_CLIENTE");

-- Table: "ANTECEDENTES"
-- DROP TABLE "ANTECEDENTES";
CREATE TABLE "ANTECEDENTES"
(
  "ID_ANTECEDENTE" integer NOT NULL,
  "DESCRIPCION" character varying(65),
  CONSTRAINT "ANTECEDENTES_pkey" PRIMARY KEY ("ID_ANTECEDENTE")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ANTECEDENTES"
  OWNER TO postgres;

-- Index: "INDX_ANT"

-- DROP INDEX "INDX_ANT";

CREATE INDEX "INDX_ANT"
  ON "ANTECEDENTES"
  USING btree
  ("ID_ANTECEDENTE");

-- TABLAS CON LLAVES FORANEAS

-- Table: "EVENTO"

-- DROP TABLE "EVENTO";

CREATE TABLE "EVENTO"
(
  "ID_EVENTO" integer NOT NULL,
  "ID_IMPLANT" integer NOT NULL,
  "ID_HOSP" integer NOT NULL,
  "FECHA_INGRESO" date NOT NULL,
  "FECHA_CAPTURA" date NOT NULL,
  "ID_CLIENTE" integer NOT NULL,
  "ID_TIPO_SEGURO" integer,
  "ID_TIPO_EVENTO" integer,
  "ID_DIAGN_INGRESO_1" integer,
  "ID_ANTECEDENTES" integer,
  "ID_PROCEDIMIENTO" integer,
  "ID_TIPO_TRATAMIENTO_QUIRURGICO" integer,
  "NUM_HABITACION" character varying(45),
  "ID_MEDICO_TRATANTE" integer,
  "ID_DIAGN_INGRESO_2" integer,
  "FECHA_EGRESO" date,
  "ID_DIAGN_EGRESO_1" integer,
  "ID_PROCEDIMIENTO_1" integer,
  "ID_PROCEDIMIENTO_2" integer,
  "EVENTOS_NO_DESEAB_ENT_HOSP" character varying(120),
  "MOTIVO_EGRESO" character varying(50),
  "MONTO_ANTES_DESVIOS2" character varying(10),
  "MONTO_DESPUES_DESVIOS" character varying(10),
  "DESC_HOSPITAL" character varying(10),
  "DIF_FACT_MENOS_DESVIOS" character varying(15),
  "DESCUENTO_NO_APLICADO" character varying(15),
  "CARGOS_PERSONALES" character varying(15),
  "TOTAL_DESVIOS" character varying(15) DEFAULT 0,
  "AJUSTE_FACTURA" character varying(15),
  "TIPO_COMPROBANTE_FISCAL_COR" character varying(25),
  "FOLIO_COMPROB_FISCAL_COR" character varying(25),
  "STATUS_EVENTO" integer,
  "COMENTARIOS_DIF_FACTURACION" character varying(150),
  "FOLIO_ARGAL" character varying(40),
  "FOLIO_HOSP" character varying(40),
  "IVA_FINALIZAR_EVENTO" double precision DEFAULT 0,
  "COASEGURO_FINALIZAR_EVENTO" double precision DEFAULT 0,
  "DEDUCIBLE_FINALIZAR_EVENTO" double precision,
  "DESCUENTO_HOSP_FINALIZAR_EVENTO" double precision DEFAULT 0,
  "HORA_INGRESO" time without time zone,
  "MEDICO_DICTAMINADOR" character varying(80),
  "FECHA_DEF" date,
  "HORA_DEF" time without time zone,
  "CAUSA_DIRECTA_DEF" character varying(250),
  "DIAS_INCAPACIDAD" integer DEFAULT 0,
  "HORA_EGRESO" time without time zone,
  "HORA_EGRESO_CAPT" time without time zone,
  "FECHA_EGRESO_CAPT" date,
  "TOTAL_DESVIOS_COMPROBADOS" double precision DEFAULT 0,
  "NUM_FACTURAS_APROBADAS" integer DEFAULT 0,
  "NUM_FACTURAS_RECHAZADAS" integer DEFAULT 0,
  "MONTO_DESVIOS_FACTURACION" double precision DEFAULT 0,
  "MONTO_AJUSTE_FACTURACION" double precision DEFAULT 0,
  "MONTO_FACTURACION_CORREGIDO" double precision DEFAULT 0,
  "MONTO_FINAL_FACTURACION" double precision DEFAULT 0,
  "MONTO_ANTES_DESVIOS" double precision DEFAULT 0,
  CONSTRAINT "EVENTO_pkey" PRIMARY KEY ("ID_EVENTO"),
  CONSTRAINT "ID_CLIENTE2_FK" FOREIGN KEY ("ID_CLIENTE")
      REFERENCES "CLIENTE" ("ID_CLIENTE") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "ID_HOSP2_FK" FOREIGN KEY ("ID_HOSP")
      REFERENCES "HOSPITAL" ("ID_HOSP") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "ID_IMPL2_FK" FOREIGN KEY ("ID_IMPLANT")
      REFERENCES "IMPLANT" ("ID_IMPLANT") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "ID_TIPO_EVENTO2_FK" FOREIGN KEY ("ID_TIPO_EVENTO")
      REFERENCES "TIPO_EVENTO" ("ID_TIPO_EVENTO") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "ID_TIPO_SEGURO2_FK" FOREIGN KEY ("ID_TIPO_SEGURO")
      REFERENCES "TIPO_SEGURO" ("ID_TIPO_SEGURO") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "EVENTO"
  OWNER TO postgres;

-- Index: "indx-evento"

-- DROP INDEX "indx-evento";

CREATE INDEX "indx-evento"
  ON "EVENTO"
  USING btree
  ("ID_EVENTO");

-- Table: "GASTO_EVENTO"

-- DROP TABLE "GASTO_EVENTO";

-- Table: "IMPLANT_HOSP"
-- DROP TABLE "IMPLANT_HOSP";
CREATE TABLE "IMPLANT_HOSP"
(
  "ID_IMPLANT" integer NOT NULL,
  "ID_HOSP" integer NOT NULL,
  CONSTRAINT "PK_IMP_HOSP" PRIMARY KEY ("ID_IMPLANT", "ID_HOSP"),
  CONSTRAINT "ID_HOSP_FK" FOREIGN KEY ("ID_HOSP")
      REFERENCES "HOSPITAL" ("ID_HOSP") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "ID_IMPLANT_FK" FOREIGN KEY ("ID_IMPLANT")
      REFERENCES "IMPLANT" ("ID_IMPLANT") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "IMPLANT_HOSP"
  OWNER TO postgres;

-- Table: "ESPECIALIDAD_MEDICO_TRATANTE"
-- DROP TABLE "ESPECIALIDAD_MEDICO_TRATANTE";
CREATE TABLE "ESPECIALIDAD_MEDICO_TRATANTE"
(
  "ID_MEDICO_TRATANTE" integer NOT NULL,
  "ID_ESPECIALIDAD" integer NOT NULL,
  CONSTRAINT "PK_ESPC_MED_TRAT" PRIMARY KEY ("ID_MEDICO_TRATANTE", "ID_ESPECIALIDAD"),
  CONSTRAINT "FK_ESPC_MEDTRAT2" FOREIGN KEY ("ID_MEDICO_TRATANTE")
      REFERENCES "MEDICO_TRATANTE" ("ID_MEDICO_TRATANTE") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_ESPMEDT1" FOREIGN KEY ("ID_ESPECIALIDAD")
      REFERENCES "ESPECIALIDAD" ("ID_ESPECIALIDAD") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ESPECIALIDAD_MEDICO_TRATANTE"
  OWNER TO postgres;


-- Table: "BITACORA_MEDICA"
-- DROP TABLE "BITACORA_MEDICA";
CREATE TABLE "BITACORA_MEDICA"
(
  "ID_BITACORA_MEDICA" integer NOT NULL,
  "ID_REGISTRO" integer NOT NULL,
  "OBSERVACIONES" character varying(256),
  "ID_EVENTO" integer NOT NULL,
  "FECHA_BITACORA" date,
  "DESCRIPCION_BITACORA" character varying(40),
  "INTERCONSULTA" character varying(40),
  CONSTRAINT "BITACORA_MEDICA_pkey" PRIMARY KEY ("ID_BITACORA_MEDICA"),
  CONSTRAINT "FK_EVENTO" FOREIGN KEY ("ID_EVENTO")
      REFERENCES "EVENTO" ("ID_EVENTO") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "BITACORA_MEDICA"
  OWNER TO postgres;

-- Index: "INDX_BIT"

-- DROP INDEX "INDX_BIT";

CREATE INDEX "INDX_BIT"
  ON "BITACORA_MEDICA"
  USING btree
  ("ID_BITACORA_MEDICA");



CREATE TABLE "GASTO_EVENTO"
(
  "ID_GASTO_EVENTO" integer NOT NULL,
  "ID_EVENTO" integer NOT NULL,
  "ID_TIPO_CARGO" integer NOT NULL,
  "ID_AREA" integer NOT NULL,
  "ID_RUBRO" integer NOT NULL,
  "ID_RAZON" integer NOT NULL,
  "FECHA_INGRESO" date NOT NULL,
  "NOMBRE" character varying(120) NOT NULL,
  "MONTO_UNITARIO" character varying(120) NOT NULL,
  "CANTIDAD" character varying(120) NOT NULL,
  "TIPO_EVIDENCIA" character varying(120),
  "ARCHIVO" bytea,
  CONSTRAINT "GASTO_EVENTO_pkey" PRIMARY KEY ("ID_GASTO_EVENTO"),
  CONSTRAINT "FK1" FOREIGN KEY ("ID_EVENTO")
      REFERENCES "EVENTO" ("ID_EVENTO") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "GASTO_EVENTO"
  OWNER TO postgres;

-- Index: "INDE_GASTO"

-- DROP INDEX "INDE_GASTO";

CREATE INDEX "INDE_GASTO"
  ON "GASTO_EVENTO"
  USING btree
  ("ID_GASTO_EVENTO");

-- Table: "REG_GASTOS_MAYORES"

-- DROP TABLE "REG_GASTOS_MAYORES";

CREATE TABLE "REG_GASTOS_MAYORES"
(
  "ID_REG_GASTOS_MAYORES" integer NOT NULL,
  "ID_EVENTO" integer NOT NULL,
  "NO_POLIZA" character varying(45),
  "COASEGURO_MED" character varying(40),
  "SUMA_ASEGURADA" character varying(20),
  "MONTO_CARTA_AUT_INI" character varying(20),
  "TAB_HONOR_MEDIC" character varying(20),
  "NOMBRE_TITULAR" character varying(45),
  "APP_TITULAR" character varying(45),
  "APM_TITULAR" character varying(45),
  "NOMBRE_PACIENTE" character varying(45),
  "APP_PACIENTE" character varying(45),
  "APM_PACIENTE" character varying(45),
  "EDAD_PACIENTE" integer,
  "UNIDAD_EDAD" character varying(10),
  "SEXO_PACIENTE" character varying(10),
  "CONDICION_PACIENTE" character varying(30),
  "RELACION_PACIENTE" character varying(30),
  "NAC_FECHA_NACIMIENTO" date,
  "NAC_HORA_NACIMIENTO" time without time zone,
  "NAC_TIPO_PARTO" character varying(30),
  "NAC_TALLA" character varying(20),
  "NAC_PESO" character varying(20),
  "NAC_APGAR" character varying(10),
  "NAC_SILVERMAN" character varying(15),
  "NAC_MEDICO_TRATANTE" character varying(50),
  "DEDUCIBLE_POLIZA" character varying(10) DEFAULT 0,
  "CAPITA" character varying(10),
  "CANTIDAD_CUB_CONVENIO" double precision DEFAULT 0,
  CONSTRAINT "REG_GASTOS_MAYORES_pkey" PRIMARY KEY ("ID_REG_GASTOS_MAYORES"),
  CONSTRAINT "ID_EVNT4_FK" FOREIGN KEY ("ID_EVENTO")
      REFERENCES "EVENTO" ("ID_EVENTO") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "REG_GASTOS_MAYORES"
  OWNER TO postgres;

-- Index: "INDX_REGGASTMAYORES"

-- DROP INDEX "INDX_REGGASTMAYORES";

CREATE INDEX "INDX_REGGASTMAYORES"
  ON "REG_GASTOS_MAYORES"
  USING btree
  ("ID_REG_GASTOS_MAYORES");

-- Table: "REG_SEG_PERSONAL"

-- DROP TABLE "REG_SEG_PERSONAL";

CREATE TABLE "REG_SEG_PERSONAL"
(
  "ID_REG_SEG_PERSONAL" integer NOT NULL,
  "ID_EVENTO" integer NOT NULL,
  "NUM_NOMINA" character varying(45),
  "INSTITUCION" character varying(90),
  "NOMBRE_TITULAR" character varying(45),
  "APP_TITULAR" character varying(45),
  "APM_TITULAR" character varying(45),
  "NOMBRE_PACIENTE" character varying(45),
  "APP_PACIENTE" character varying(45),
  "APM_PACIENTE" character varying(45),
  "EDAD_PACIENTE" integer,
  "UNIDAD_EDAD" character varying(10),
  "SEXO_PACIENTE" character varying(10),
  "CONDICION_PACIENTE" character varying(30),
  "RELACION_PACIENTE" character varying(30),
  "NAC_FECHA_NACIMIENTO" date,
  "NAC_HORA_NACIMIENTO" time without time zone,
  "NAC_TIPO_PARTO" character varying(20),
  "NAC_TALLA" character varying(20),
  "NAC_PESO" character varying(20),
  "NAC_APGAR" character varying(20),
  "NAC_SILVERMAN" character varying(20),
  "NAC_MEDICO_TRATANTE" character varying(50),
  "NUM_AUTORIZACION" character varying(25),
  "CENSO" character varying(2),
  "CANTIDAD_CUB_CONVENIO" double precision,
  "CAPITA" character varying(10),
  CONSTRAINT "REG_SEG_PERSONAL_pkey" PRIMARY KEY ("ID_REG_SEG_PERSONAL"),
  CONSTRAINT "ID_EVNT3_FK" FOREIGN KEY ("ID_EVENTO")
      REFERENCES "EVENTO" ("ID_EVENTO") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "REG_SEG_PERSONAL"
  OWNER TO postgres;

-- Index: "INDX_REG_SEGPERS"

-- DROP INDEX "INDX_REG_SEGPERS";

CREATE INDEX "INDX_REG_SEGPERS"
  ON "REG_SEG_PERSONAL"
  USING btree
  ("ID_REG_SEG_PERSONAL");


  
-- Sequence: sec_bitacora

-- DROP SEQUENCE sec_bitacora;

CREATE SEQUENCE sec_bitacora
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 48
  CACHE 1;
ALTER TABLE sec_bitacora
  OWNER TO postgres;

  
-- Sequence: sec_cliente

-- DROP SEQUENCE sec_cliente;

CREATE SEQUENCE sec_cliente
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 22
  CACHE 1;
ALTER TABLE sec_cliente
  OWNER TO postgres;

 
-- Sequence: sec_evento

-- DROP SEQUENCE sec_evento;

CREATE SEQUENCE sec_evento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 100
  CACHE 1;
ALTER TABLE sec_evento
  OWNER TO postgres;

  
-- Sequence: sec_factura

-- DROP SEQUENCE sec_factura;

CREATE SEQUENCE sec_factura
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 101
  CACHE 1;
ALTER TABLE sec_factura
  OWNER TO postgres;

  
-- Sequence: sec_gasto

-- DROP SEQUENCE sec_gasto;

CREATE SEQUENCE sec_gasto
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 126
  CACHE 1;
ALTER TABLE sec_gasto
  OWNER TO postgres;

  
-- Sequence: sec_hospital

-- DROP SEQUENCE sec_hospital;

CREATE SEQUENCE sec_hospital
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 17
  CACHE 1;
ALTER TABLE sec_hospital
  OWNER TO postgres;

  
-- Sequence: sec_implant

-- DROP SEQUENCE sec_implant;

CREATE SEQUENCE sec_implant
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 39
  CACHE 1;
ALTER TABLE sec_implant
  OWNER TO postgres;

-- Sequence: sec_med_tratante

-- DROP SEQUENCE sec_med_tratante;

CREATE SEQUENCE sec_med_tratante
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 43
  CACHE 1;
ALTER TABLE sec_med_tratante
  OWNER TO postgres;


-- Table: "FACTURA"
-- DROP TABLE "FACTURA";
CREATE TABLE "FACTURA"
(
  "ID_FACTURA" integer NOT NULL,
  "APROBADA" character varying(15),
  "DETALLE" character varying(250),
  "OBSERVACIONES" character varying(200),
  "RUTA_FACTURA" character varying(100),
  "ID_EVENTO" integer,
  "MONTO" double precision,
  "NUM_FACTURA" character varying(60),
  "AJUSTE_FACTURA" double precision,
  "MONTO_CFC" double precision,
  "TIPO_CFC" character varying(40),
  "FOLIO_CFC" character varying(40),
  "ARCHIVO" bytea,
  CONSTRAINT "FACTURA_pkey" PRIMARY KEY ("ID_FACTURA")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "FACTURA"
  OWNER TO postgres;

-- Index: "INDEX_FACTURA"

-- DROP INDEX "INDEX_FACTURA";

CREATE INDEX "INDEX_FACTURA"
  ON "FACTURA"
  USING btree
  ("ID_FACTURA");
