alter table SECCION add nombre_corto VARCHAR(25);

UPDATE SECCION set nombre_corto = 'Biodiversidad' WHERE codigo_sec = 1;
UPDATE SECCION set nombre_corto = 'Derechos' WHERE codigo_sec = 2;
UPDATE SECCION set nombre_corto = 'Económico' WHERE codigo_sec = 3;
UPDATE SECCION set nombre_corto = 'Estructura estatal' WHERE codigo_sec = 4;
UPDATE SECCION set nombre_corto = 'Fiscalización' WHERE codigo_sec = 5;
UPDATE SECCION set nombre_corto = 'Justicia' WHERE codigo_sec = 6;
UPDATE SECCION set nombre_corto = 'Participación' WHERE codigo_sec = 7;
UPDATE SECCION set nombre_corto = 'Relaciones' WHERE codigo_sec = 8;
UPDATE SECCION set nombre_corto = 'Salud' WHERE codigo_sec = 9;
UPDATE SECCION set nombre_corto = 'Alimentaria' WHERE codigo_sec = 10;