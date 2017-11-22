INSERT into usuario (codigo_us, nombre_us, estado_us, fecha_act_us, identificacion_us) values (5, 'JOSE S.', 'A', CURRENT_DATE, '2345678901');

INSERT into usuario_clave (codigo_usc, clave_usc, estado_usc, fecha_act_usc, codigo_us) values (5, 'b1fa0218c1040b082e2374a941707c35', 'A', CURRENT_DATE, 5);

update usuario set nombre_us = 'ASAMBLEA NACIONAL' where codigo_us = 2;

--INSERT into seccion values (11, 'CONSTITUCIÓN', 'A', CURRENT_DATE, 'S11','Constitución');

--INSERT into seccion values (12, 'NIÑEZ Y ADOLESCENCIA', 'A', CURRENT_DATE, 'S11','Niñez y adolescencia');
