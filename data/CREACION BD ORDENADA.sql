CREATE TABLE CLIENTES 
(
  NUMERO_DOCUMENTO VARCHAR2(30 BYTE) NOT NULL 
, TIPO_DOCUMENTO VARCHAR2(20 BYTE) NOT NULL 
, NOMBRE VARCHAR2(20 BYTE) NOT NULL 
, NACIONALIDAD VARCHAR2(20 BYTE) NOT NULL 
, TIPO VARCHAR2(20 BYTE) NOT NULL 
, USERNAME VARCHAR2(20 BYTE) NOT NULL 
, CONTRASENA VARCHAR2(20 BYTE) NOT NULL 
, CONSTRAINT CLIENTES_PK PRIMARY KEY 
  (
    NUMERO_DOCUMENTO 
  , TIPO_DOCUMENTO 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX CLIENTES_PK ON CLIENTES (NUMERO_DOCUMENTO ASC, TIPO_DOCUMENTO ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE CLIENTES
ADD CONSTRAINT CLIENTES_CHK1 CHECK 
(TIPO_DOCUMENTO IN('TI','CC','CE','PA'))
ENABLE;

ALTER TABLE CLIENTES
ADD CONSTRAINT CLIENTES_CHK2 CHECK 
(TIPO IN('ESTUDIANTE', 'PROFESOR', 'EMPLEADO', 'PADRE ESTUDIANTE', 'PROFESOR INVITADO', 'REGISTRADO EVENTO'))
ENABLE;




CREATE TABLE PERSONASNATURALES 
(
  NUMERO_DOCUMENTO VARCHAR2(30 BYTE) NOT NULL 
, TIPO_DOCUMENTO VARCHAR2(20 BYTE) NOT NULL 
, NOMBRE VARCHAR2(20 BYTE) NOT NULL 
, NACIONALIDAD VARCHAR2(20 BYTE) NOT NULL 
, TIPO VARCHAR2(20 BYTE) NOT NULL 
, USERNAME VARCHAR2(20 BYTE) NOT NULL 
, CONTRASENA VARCHAR2(20 BYTE) NOT NULL 
, CONSTRAINT PERSONASNATURALES_PK PRIMARY KEY 
  (
    NUMERO_DOCUMENTO 
  , TIPO_DOCUMENTO 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX PERSONASNATURALES_PK ON PERSONASNATURALES (NUMERO_DOCUMENTO ASC, TIPO_DOCUMENTO ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE PERSONASNATURALES
ADD CONSTRAINT PERSONASNATURALES_CHK1 CHECK 
(TIPO_DOCUMENTO IN('TI','CC','CE','PA'))
ENABLE;

ALTER TABLE PERSONASNATURALES
ADD CONSTRAINT PERSONASNATURALES_CHK2 CHECK 
(TIPO IN('UNIANDINO', 'VECINO'))
ENABLE;



CREATE TABLE PERSONASJURIDICAS 
(
  NIT VARCHAR2(30 BYTE) NOT NULL 
, NOMBRE VARCHAR2(20 BYTE) NOT NULL 
, TIPO VARCHAR2(20 BYTE) NOT NULL 
, HORA_APERTURA VARCHAR2(20 BYTE) NOT NULL 
, HORA_CIERRE VARCHAR2(20 BYTE) NOT NULL 
, USERNAME VARCHAR2(20 BYTE) NOT NULL 
, CONTRASENA VARCHAR2(20 BYTE) NOT NULL 
, CONSTRAINT PERSONASJURIDICAS_PK PRIMARY KEY 
  (
    NIT 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX PERSONASJURIDICAS_PK ON PERSONASJURIDICAS (NIT ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE PERSONASJURIDICAS
ADD CONSTRAINT PERSONASJURIDICAS_CHK1 CHECK 
(TIPO IN('EMPRESA', 'HOTEL', 'HOSTAL'))
ENABLE;



CREATE TABLE OFERTAS 
(
  ID_OFERTA VARCHAR2(30 BYTE) NOT NULL 
, TIPO_OFERTA VARCHAR2(30 BYTE) NOT NULL
, DISPONIBLE NUMBER NOT NULL
, PRECIO NUMBER NOT NULL
, CONSTRAINT OFERTAS_PK PRIMARY KEY 
  (
    ID_OFERTA 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX OFERTAS_PK ON OFERTAS (ID_OFERTA ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE OFERTAS
ADD CONSTRAINT OFERTAS_CHK1 CHECK 
(TIPO_OFERTA IN('VIVIENDA UNIVERSITARIA', 'HABITACION DIARIA', 'HABITACION MENSUAL', 'APARTAMENTO'))
ENABLE;

ALTER TABLE OFERTAS
ADD CONSTRAINT OFERTAS_CHK2 CHECK (PRECIO>0)
ENABLE;



CREATE TABLE ADICIONALES 
(
  ID_OFERTA VARCHAR2(30 BYTE) NOT NULL 
, NOMBRE VARCHAR2(20 BYTE) NOT NULL 
, PRECIO NUMBER NOT NULL 
, CONSTRAINT ADICIONALES_PK PRIMARY KEY 
  (
    ID_OFERTA 
  , NOMBRE 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX ADICIONALES_PK ON ADICIONALES (ID_OFERTA ASC, NOMBRE ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE ADICIONALES
ADD CONSTRAINT ADICIONALES_FK1 FOREIGN KEY
(
  ID_OFERTA 
)
REFERENCES OFERTAS
(
  ID_OFERTA 
)
ENABLE;

ALTER TABLE ADICIONALES
ADD CONSTRAINT ADICIONALES_CHK1 CHECK 
(PRECIO > 0)
ENABLE;



CREATE TABLE INTERESAN 
(
  ID_OFERTA VARCHAR2(30 BYTE) NOT NULL 
, DOC_CLIENTE VARCHAR2(30 BYTE) NOT NULL 
, TIPO_DOC_CLIENTE VARCHAR2(20 BYTE) NOT NULL 
, CONSTRAINT INTERESAN_PK PRIMARY KEY 
  (
    ID_OFERTA 
  , DOC_CLIENTE 
  , TIPO_DOC_CLIENTE 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX INTERESAN_PK ON INTERESAN (ID_OFERTA ASC, DOC_CLIENTE ASC, TIPO_DOC_CLIENTE ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE INTERESAN
ADD CONSTRAINT INTERESAN_FK1 FOREIGN KEY
(
  ID_OFERTA 
)
REFERENCES OFERTAS
(
  ID_OFERTA 
)
ENABLE;

ALTER TABLE INTERESAN
ADD CONSTRAINT INTERESAN_FK2 FOREIGN KEY
(
  DOC_CLIENTE 
, TIPO_DOC_CLIENTE 
)
REFERENCES CLIENTES
(
  NUMERO_DOCUMENTO 
, TIPO_DOCUMENTO 
)
ENABLE;


CREATE TABLE RESERVAS 
(
  NUM_RESERVA VARCHAR2(30 BYTE) NOT NULL 
, FECHA_INICIO DATE NOT NULL 
, FECHA_FIN DATE NOT NULL 
, ID_OFERTA VARCHAR2(30 BYTE) NOT NULL 
, DOC_CLIENTE VARCHAR2(30 BYTE) NOT NULL 
, TIPO_DOC_CLIENTE VARCHAR2(20 BYTE) NOT NULL 
, FECHA_CANCELACION DATE NOT NULL 
, CONSTRAINT RESERVAS_PK PRIMARY KEY 
  (
    NUM_RESERVA 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX CONTRATOS_PK ON RESERVAS (NUM_RESERVA ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_FK1 FOREIGN KEY
(
  ID_OFERTA 
)
REFERENCES OFERTAS
(
  ID_OFERTA 
)
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_FK2 FOREIGN KEY
(
  DOC_CLIENTE 
, TIPO_DOC_CLIENTE 
)
REFERENCES CLIENTES
(
  NUMERO_DOCUMENTO 
, TIPO_DOCUMENTO 
)
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_CHK1 CHECK 
(FECHA_INICIO < FECHA_FIN)
ENABLE;



CREATE TABLE CONTRATOS 
(
  NUM_CONTRATO VARCHAR2(30 BYTE) NOT NULL 
, DURACION NUMBER NOT NULL 
, ID_RESERVA VARCHAR2(30 BYTE) NOT NULL 
, CONSTRAINT CONTRATOS_PK PRIMARY KEY 
  (
    NUM_CONTRATO 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX CONTRATOS_PK1 ON CONTRATOS (NUM_CONTRATO ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE CONTRATOS
ADD CONSTRAINT CONTRATOS_FK1 FOREIGN KEY
(
  ID_RESERVA 
)
REFERENCES RESERVAS
(
  NUM_RESERVA 
)
ENABLE;

ALTER TABLE CONTRATOS
ADD CONSTRAINT CONTRATOS_CHK1 CHECK 
(DURACION >0)
ENABLE;




CREATE TABLE OFERTAAPARTAMENTO 
(
  ID VARCHAR2(30 BYTE) NOT NULL 
, CAPACIDAD NUMBER NOT NULL 
, DESCRIPCION VARCHAR2(30 BYTE) NOT NULL 
, ES_AMOBLADO NUMBER NOT NULL 
, UBICACION VARCHAR2(20 BYTE) NOT NULL 
, DOC_OPERADOR VARCHAR2(30 BYTE) NOT NULL 
, TIPO_DOC_OP VARCHAR2(20 BYTE) NOT NULL 
, CONTRATO VARCHAR2(30 BYTE) 
, CONSTRAINT OFERTAAPARTAMENTO_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX OFERTAAPARTAMENTO_PK ON OFERTAAPARTAMENTO (ID ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE OFERTAAPARTAMENTO
ADD CONSTRAINT OFERTAAPARTAMENTO_FK1 FOREIGN KEY
(
  ID 
)
REFERENCES OFERTAS
(
  ID_OFERTA 
)
ENABLE;

ALTER TABLE OFERTAAPARTAMENTO
ADD CONSTRAINT OFERTAAPARTAMENTO_FK2 FOREIGN KEY
(
  DOC_OPERADOR 
, TIPO_DOC_OP 
)
REFERENCES PERSONASNATURALES
(
  NUMERO_DOCUMENTO 
, TIPO_DOCUMENTO 
)
ENABLE;

ALTER TABLE OFERTAAPARTAMENTO
ADD CONSTRAINT OFERTAAPARTAMENTO_FK3 FOREIGN KEY
(
  CONTRATO 
)
REFERENCES CONTRATOS
(
  NUM_CONTRATO 
)
ENABLE;

ALTER TABLE OFERTAAPARTAMENTO
ADD CONSTRAINT OFERTAAPARTAMENTO_CHK1 CHECK 
(CAPACIDAD > 0)
ENABLE;

ALTER TABLE OFERTAAPARTAMENTO
ADD CONSTRAINT OFERTAAPARTAMENTO_CHK2 CHECK 
(ES_AMOBLADO IN(1,0))
ENABLE;



CREATE TABLE OFERTAESPORADICA 
(
  ID VARCHAR2(30 BYTE) NOT NULL 
, DURACION NUMBER NOT NULL 
, DESCRIPCION VARCHAR2(30 BYTE) NOT NULL 
, DESCRIPCION_SEGURO VARCHAR2(20 BYTE) NOT NULL 
, NUM_HABITACIONES NUMBER NOT NULL 
, UBICACION VARCHAR2(20 BYTE) NOT NULL 
, CONSTRAINT OFERTAESPORADICA_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX OFERTAESPORADICA_PK ON OFERTAESPORADICA (ID ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE OFERTAESPORADICA
ADD CONSTRAINT OFERTAESPORADICA_FK1 FOREIGN KEY
(
  ID 
)
REFERENCES OFERTAS
(
  ID_OFERTA 
)
ENABLE;

ALTER TABLE OFERTAESPORADICA
ADD CONSTRAINT OFERTAESPORADICA_CHK1 CHECK 
(DURACION > 0)
ENABLE;

ALTER TABLE OFERTAESPORADICA
ADD CONSTRAINT OFERTAESPORADICA_CHK2 CHECK 
(NUM_HABITACIONES > 0)
ENABLE;



CREATE TABLE OFERTAHABITACIONDIARIA 
(
  ID VARCHAR2(30 BYTE) NOT NULL 
, ES_COMPARTIDA NUMBER NOT NULL 
, UBICACION VARCHAR2(20 BYTE) NOT NULL 
, ID_OPERADOR VARCHAR2(30 BYTE) NOT NULL 
, CONSTRAINT OFERTAHABITACIONDIARIA_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX OFERTAHABITACIONDIARIA_PK ON OFERTAHABITACIONDIARIA (ID ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE OFERTAHABITACIONDIARIA
ADD CONSTRAINT OFERTAHABITACIONDIARIA_FK1 FOREIGN KEY
(
  ID 
)
REFERENCES OFERTAS
(
  ID_OFERTA 
)
ENABLE;

ALTER TABLE OFERTAHABITACIONDIARIA
ADD CONSTRAINT OFERTAHABITACIONDIARIA_FK2 FOREIGN KEY
(
  ID_OPERADOR 
)
REFERENCES PERSONASJURIDICAS
(
  NIT 
)
ENABLE;

ALTER TABLE OFERTAHABITACIONDIARIA
ADD CONSTRAINT OFERTAHABITACIONDIARIA_CHK1 CHECK 
(ES_COMPARTIDA IN(0,1))
ENABLE;



CREATE TABLE OFERTAHABITACIONMENSUAL 
(
  ID VARCHAR2(30 BYTE) NOT NULL 
, CAPACIDAD NUMBER NOT NULL 
, DESCRIPCION VARCHAR2(30 BYTE) NOT NULL 
, UBICACION VARCHAR2(20 BYTE) NOT NULL 
, DOC_OPERADOR VARCHAR2(30 BYTE) NOT NULL 
, TIPO_DOC_OP VARCHAR2(20 BYTE) NOT NULL 
, CONTRATO VARCHAR2(30 BYTE) 
, CONSTRAINT OFERTAHABITACIONMENSUAL_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX OFERTAHABITACIONMENSUAL_PK ON OFERTAHABITACIONMENSUAL (ID ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE OFERTAHABITACIONMENSUAL
ADD CONSTRAINT OFERTAHABITACIONMENSUAL_FK1 FOREIGN KEY
(
  ID 
)
REFERENCES OFERTAS
(
  ID_OFERTA 
)
ENABLE;

ALTER TABLE OFERTAHABITACIONMENSUAL
ADD CONSTRAINT OFERTAHABITACIONMENSUAL_FK2 FOREIGN KEY
(
  DOC_OPERADOR 
, TIPO_DOC_OP 
)
REFERENCES PERSONASNATURALES
(
  NUMERO_DOCUMENTO 
, TIPO_DOCUMENTO 
)
ENABLE;

ALTER TABLE OFERTAHABITACIONMENSUAL
ADD CONSTRAINT OFERTAHABITACIONMENSUAL_FK3 FOREIGN KEY
(
  CONTRATO 
)
REFERENCES CONTRATOS
(
  NUM_CONTRATO 
)
ENABLE;

ALTER TABLE OFERTAHABITACIONMENSUAL
ADD CONSTRAINT OFERTAHABITACIONMENSUAL_CHK1 CHECK 
(CAPACIDAD > 0)
ENABLE;



CREATE TABLE OFERTAVIVIENDAUNIVERSITARIA 
(
  ID VARCHAR2(30 BYTE) NOT NULL 
, CAPACIDAD NUMBER NOT NULL 
, DURACION VARCHAR2(20 BYTE) NOT NULL 
, ES_COMPARTIDA NUMBER NOT NULL
, ID_OPERADOR VARCHAR2(30 BYTE) NOT NULL 
, CONSTRAINT OFERTAVIVIENDAUNIVERSITARI_PK PRIMARY KEY 
  (
    ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX OFERTAVIVIENDAUNIVERSITARI_PK ON OFERTAVIVIENDAUNIVERSITARIA (ID ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE OFERTAVIVIENDAUNIVERSITARIA
ADD CONSTRAINT OFERTAVIVIENDAUNIVERSITAR_FK1 FOREIGN KEY
(
  ID_OPERADOR 
)
REFERENCES PERSONASJURIDICAS
(
  NIT 
)
ENABLE;

ALTER TABLE OFERTAVIVIENDAUNIVERSITARIA
ADD CONSTRAINT OFERTAVIVIENDAUNIVERSITAR_FK2 FOREIGN KEY
(
  ID 
)
REFERENCES OFERTAS
(
  ID_OFERTA 
)
ENABLE;

ALTER TABLE OFERTAVIVIENDAUNIVERSITARIA
ADD CONSTRAINT OFERTAVIVIENDAUNIVERSITA_CHK1 CHECK 
(CAPACIDAD > 0)
ENABLE;

ALTER TABLE OFERTAVIVIENDAUNIVERSITARIA
ADD CONSTRAINT OFERTAVIVIENDAUNIVERSITA_CHK2 CHECK 
(DURACION IN('DIARIA', 'MENSUAL', 'SEMESTRAL'))
ENABLE;

ALTER TABLE OFERTAVIVIENDAUNIVERSITARIA
ADD CONSTRAINT OFERTAVIVIENDAUNIVERSITA_CHK3 CHECK 
(ES_COMPARTIDA IN(0,1))
ENABLE;