CREATE SEQUENCE OT_SUPPORT.ISEQ_PET_OTCCM
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;

CREATE SEQUENCE OT_SUPPORT.ISEQ_DET_OTCCM
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;

GRANT ALL ON OT_SUPPORT.ISEQ_PET_OTCCM TO DS_JOB_TEST;

GRANT ALL ON OT_SUPPORT.ISEQ_DET_OTCCM TO DS_JOB_TEST;


DROP TABLE OT_SUPPORT.CCM_DETALLE;

DROP TABLE OT_SUPPORT.CCM_PETICION;

CREATE TABLE OT_SUPPORT.CCM_PETICION
(
	IDPETICION NUMBER,
	SISTEMA VARCHAR2(500) NOT NULL,
	NOMFASE VARCHAR2(100) NOT NULL,
	NUMOPERACION varchar2(200) NOT NULL,
	USUCREACION varchar2(50),
	FECCREACION date,
	USUMODIF varchar2(50),
	FECMODIF date,	
	INDERROR number(1,0) NOT NULL CHECK (INDERROR IN (1,0)),
	DESCERROR varchar2(500),
	constraint CCM_PETICION_PK primary key (IDPETICION),
	CONSTRAINT CCM_PETICION_NUMOPERACION UNIQUE (NUMOPERACION)
);


CREATE TABLE OT_SUPPORT.CCM_DETALLE
(
	IDDETALLE NUMBER,
	IDPETICION number(10) NOT NULL,
	USUCREACION varchar2(50),
	FECCREACION date,
	USUMODIF varchar2(50),
	FECMODIF date,
	NOMFASE varchar2(50),
	INPUTJSON clob,
	INDERROR number(1,0) NOT NULL CHECK (INDERROR IN (1,0)),
	DESCERROR varchar2(100),
	constraint CCM_DETALLE_PK primary key (IDDETALLE),
	constraint CCM_DETALLE_FK FOREIGN KEY (IDPETICION) REFERENCES CCM_PETICION(IDPETICION)
);

DROP TABLE OT_SUPPORT.CCM_DOCUMENTO;

DROP TABLE OT_SUPPORT.CCM_SISTEMA;

CREATE TABLE OT_SUPPORT.CCM_SISTEMA
(
	IDSISTEMA NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
	USUCREACION varchar2(50),
	FECCREACION date,
	USUMODIF varchar2(50),
	FECMODIF date,
	NOMBRE varchar2(100) NOT NULL,
	INDACTIVO number(1,0) NOT NULL CHECK (INDACTIVO IN (1,0)),
	constraint CCM_SISTEMA primary key (IDSISTEMA)
);



CREATE TABLE OT_SUPPORT.CCM_DOCUMENTO
(
	IDDOCUMENTO number GENERATED BY DEFAULT ON NULL AS IDENTITY,
	IDSISTEMA number(10) NOT NULL,
	USUCREACION varchar2(50),
	FECCREACION date,
	USUMODIF varchar2(50),
	FECMODIF date,
	CODIGO varchar2(100) NOT NULL,
	NOMBRE varchar2(100) NOT NULL,
	RESOURCEID varchar2(500),
	INDACTIVO number(1,0) NOT NULL CHECK (INDACTIVO IN (1,0)),
	constraint CCM_DOCUMENTO_PK primary key (IDDOCUMENTO),
	constraint CCM_DOCUMENTO_FK FOREIGN KEY (IDSISTEMA) REFERENCES CCM_SISTEMA(IDSISTEMA)
);

  

GRANT ALL ON OT_SUPPORT.CCM_PETICION TO DS_JOB_TEST;
GRANT ALL ON OT_SUPPORT.CCM_DETALLE TO DS_JOB_TEST;
GRANT ALL ON OT_SUPPORT.CCM_SISTEMA TO DS_JOB_TEST;
GRANT ALL ON OT_SUPPORT.CCM_DOCUMENTO TO DS_JOB_TEST;


DELETE FROM OT_SUPPORT.CCM_SISTEMA;

--INSERTS DE SISTEMAS
INSERT INTO OT_SUPPORT.CCM_SISTEMA (USUCREACION, FECCREACION, NOMBRE, INDACTIVO) VALUES ('CS', SYSDATE, 'Salesforce', 1);
INSERT INTO OT_SUPPORT.CCM_SISTEMA (USUCREACION, FECCREACION, NOMBRE, INDACTIVO) VALUES ('CS', SYSDATE, 'Bizagi', 1);
INSERT INTO OT_SUPPORT.CCM_SISTEMA (USUCREACION, FECCREACION, NOMBRE, INDACTIVO) VALUES ('CS', SYSDATE, 'Thunderhead', 1);
INSERT INTO OT_SUPPORT.CCM_SISTEMA (USUCREACION, FECCREACION, NOMBRE, INDACTIVO) VALUES ('CS', SYSDATE, 'Flexivida', 1);

delete from OT_SUPPORT.CCM_DOCUMENTO;

--INSERTS DE DOCUMENTOS DE BIZAGI
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Bizagi'), 'CS', SYSDATE, 'BIZAGI_001', 'Cargo recepción de Siniestros plataforma', 1, 'cxr:/?id=1ab63cc9-1a6e-7741-960b-b00eb6fbdddc;v=19;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Bizagi'), 'CS', SYSDATE, 'BIZAGI_002', 'Carta ampliación de plazo - beneficiario', 1, 'cxr:/?id=6c53e303-7229-b043-91b8-d6910c8d2a92;v=4;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Bizagi'), 'CS', SYSDATE, 'BIZAGI_003', 'Carta siniestro observado', 1, 'cxr:/?id=450ffc11-a95c-5e46-a865-5dfc83a7fdcd;v=7;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Bizagi'), 'CS', SYSDATE, 'BIZAGI_004', 'Carta rechazo exclusión', 1, 'cxr:/?id=85e3b7e5-a01b-0443-bd54-24de41376884;v=6;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Bizagi'), 'CS', SYSDATE, 'BIZAGI_005', 'Carta rechazo informativo', 1, 'cxr:/?id=50fd7ea0-423c-3340-a34e-066570642d8e;v=8;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Bizagi'), 'CS', SYSDATE, 'BIZAGI_006', 'Notificación de pago – su cheque/depósito está listo', 1, 'cxr:/?id=83353572-d314-c744-a2f2-044eb1a2a6c8;v=5;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Bizagi'), 'CS', SYSDATE, 'BIZAGI_007', 'Notificación de Vencimiento - No trajo documentos y se venció el plazo y ya no tiene derecho', 1, 'cxr:/?id=8fbd074a-fa20-1246-833d-8c16ebef9907;v=11;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');

--INSERTS DE DOCUMENTOS DE SALESFORCE
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Salesforce'), 'CS', SYSDATE, 'SALESF_001', 'Carta de Ampliación', 1, 'cxr:/?id=e925c0a3-66aa-dc4c-90c9-f468cb627ed7;v=1;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Salesforce'), 'CS', SYSDATE, 'SALESF_002', 'Cierre de reclamos', 1, 'cxr:/?id=6981e878-1d14-9347-a6cb-cb821be55622;v=1;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Salesforce'), 'CS', SYSDATE, 'SALESF_003', 'Informe de resultados', 1, 'cxr:/?id=e4974431-b3dc-dc41-a8aa-bc51ea83124e;v=1;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Salesforce'), 'CS', SYSDATE, 'SALESF_004', 'Libro de reclamaciones', 1, 'cxr:/?id=c0d06d2d-500d-c247-9663-51474749918b;v=1;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Salesforce'), 'CS', SYSDATE, 'SALESF_005', 'Notificación de servicio', 1, 'cxr:/?id=ad5137ba-da1a-104f-8c15-71c798f74ed9;v=1;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Salesforce'), 'CS', SYSDATE, 'SALESF_006', 'Reporte Siniestro vehicular broker', 1, 'cxr:/?id=cac234bc-750e-6246-8ace-5636d0476e54;v=1;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Salesforce'), 'CS', SYSDATE, 'SALESF_007', 'Reporte Siniestro vehicular proveedor', 1, 'cxr:/?id=ea591e99-28ef-ac4f-ba72-23efc66ee3a8;v=2;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');

--INSERTS DE DOCUMENTOS THUNDERHEAD
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_NBR_002_PRINT', 'Propuesta_Renovacion', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_NBR_002_EMAIL', 'Propuesta_Renovacion web', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_NBR_008_SMS', 'Notificación para regularización de deuda vencida', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_NBR_009_EMAIL', 'Notificacion_de_rechazo_por_no_regulacion_de_deuda', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_NBR_010_EMAIL', 'Notificación de Bienvenida', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_NBR_011_EMAIL', 'Notificación de Bienvenida a la Web', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM-NBRG-001_EMAIL', 'Notificación solicitar información completa', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM-NBRG-002_EMAIL', 'Notificación de Envío Slip', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM-NBRG-004_EMAIL', 'Solicitud Visto BuenoEndoso WEB Mail', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM-NBRG-004_PRINT', 'Solicitud Visto BuenoEndoso PDF', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_Sujeto', 'Sujeto', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_SujetoNBR', 'SujetoNBR', 0);
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Thunderhead'), 'CS', SYSDATE, 'CM_SujetoNBRG', 'SujetoNBRG', 0);

--FLEXIVIDA
INSERT INTO OT_SUPPORT.CCM_DOCUMENTO (IDSISTEMA, USUCREACION, FECCREACION, CODIGO, NOMBRE, INDACTIVO, RESOURCEID) VALUES ((SELECT MAX(IDSISTEMA) FROM OT_SUPPORT.CCM_SISTEMA WHERE NOMBRE = 'Flexivida'), 'CS', SYSDATE, 'FLEXIVIDA_001', '	
Seguro de Vida', 1, 'cxr:/?id=2ea34313-f125-1342-9799-bff1657ad0da;v=1;t=cfa97b6c-34d4-9201-3a82-3dc10e5175f5');

