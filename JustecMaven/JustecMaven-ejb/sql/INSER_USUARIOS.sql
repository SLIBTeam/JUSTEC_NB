INSERT into usuario (codigo_us, nombre_us, estado_us, fecha_act_us, identificacion_us) values (5, 'JOSE S.', 'A', CURRENT_DATE, '2345678901');

INSERT into usuario_clave (codigo_usc, clave_usc, estado_usc, fecha_act_usc, codigo_us) values (5, 'b1fa0218c1040b082e2374a941707c35', 'A', CURRENT_DATE, 5);

update usuario set nombre_us = 'ASAMBLEA NACIONAL' where codigo_us = 2;

INSERT into seccion values (11, 'CONSTITUCIÓN', 'A', CURRENT_DATE, 'S11','Constitución');

INSERT into seccion values (12, 'NIÑEZ Y ADOLESCENCIA', 'A', CURRENT_DATE, 'S11','Niñez y adolescencia');


INSERT INTO documento VALUES (47,'Normativa Nacional e Internacional sobre Adolescentes en Conflicto con la Ley Penal', 20414, '2017-09-15', '2017-09-15', 'A', '2017-09-15', 4, 1, 2, 'ley_niñez.pdf', 12, 1);

INSERT INTO documento VALUES (48,'Constitución', 20414, '2017-09-15', '2017-09-15', 'A', '2017-09-15', 4, 1, 2, 'constitucion_chile.pdf', 11, 2);

INSERT INTO documento VALUES (49,'Constitución', 20414, '2017-09-15', '2017-09-15', 'A', '2017-09-15', 4, 1, 2, 'constitucion_ecuador.pdf', 11, 1);

INSERT INTO documento VALUES (50,'Constitución', 20414, '2017-09-15', '2017-09-15', 'A', '2017-09-15', 4, 1, 2, 'constitucion_peru.pdf', 11, 3);

INSERT INTO documento VALUES (51,'Constitución', 20414, '2017-09-15', '2017-09-15', 'A', '2017-09-15', 4, 1, 2, 'constitucion_colombia.pdf', 11, 4);