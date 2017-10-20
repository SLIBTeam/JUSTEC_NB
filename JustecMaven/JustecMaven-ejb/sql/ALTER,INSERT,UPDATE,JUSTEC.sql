alter table usuario add identificacion_us VARCHAR(50);

UPDATE usuario set nombre_us = 'ARAUJO PARRA LUIS SEBASTIAN', identificacion_us = '1723723092' WHERE codigo_us = 1;

INSERT into usuario (codigo_us, nombre_us, estado_us, fecha_act_us, identificacion_us) values (2, 'POZO DIEGO', 'A', CURRENT_DATE, '1234567890');
INSERT into usuario (codigo_us, nombre_us, estado_us, fecha_act_us, identificacion_us) values (3, 'FALCONES LUIS', 'A', CURRENT_DATE, '0987654321');
INSERT into usuario (codigo_us, nombre_us, estado_us, fecha_act_us, identificacion_us) values (4, 'SANGACHA GARCÍA WASHINGTON NORBERTO', 'A', CURRENT_DATE, '1721126686');

INSERT into usuario_clave (codigo_usc, clave_usc, estado_usc, fecha_act_usc, codigo_us) values (1, 'bcdc3f2d256385e347aff08050b2fa88', 'A', CURRENT_DATE, 1);
INSERT into usuario_clave (codigo_usc, clave_usc, estado_usc, fecha_act_usc, codigo_us) values (2, 'e807f1fcf82d132f9bb018ca6738a19f', 'A', CURRENT_DATE, 2);
INSERT into usuario_clave (codigo_usc, clave_usc, estado_usc, fecha_act_usc, codigo_us) values (3, '6fb42da0e32e07b61c9f0251fe627a9c', 'A', CURRENT_DATE, 3);
INSERT into usuario_clave (codigo_usc, clave_usc, estado_usc, fecha_act_usc, codigo_us) values (4, 'e334f832b73b3973f3c52c8c3e0e08ed', 'A', CURRENT_DATE, 4);