USE unfv_bd;

INSERT INTO alumno (codigo_alumno, nombres, apellidos) VALUES
('A0001', 'Juan', 'Pérez'),
('A0002', 'Ana', 'González'),
('A0003', 'Luis', 'Martínez'),
('A0004', 'Carlos', 'Hernández'),
('A0005', 'María', 'López'),
('A0006', 'Pedro', 'Gómez'),
('A0007', 'Sofía', 'Díaz'),
('A0008', 'David', 'Vázquez'),
('A0009', 'Laura', 'Fernández'),
('A0010', 'Miguel', 'Sánchez'),
('A0011', 'Sara', 'Ramírez'),
('A0012', 'José', 'Jiménez'),
('A0013', 'Isabel', 'Ruiz'),
('A0014', 'Raúl', 'Torres'),
('A0015', 'Carmen', 'Álvarez'),
('A0016', 'Javier', 'Gil'),
('A0017', 'Elena', 'Molina'),
('A0018', 'Manuel', 'Suárez'),
('A0019', 'Martín', 'Moreno'),
('A0020', 'Paula', 'Muñoz'),
('A0021', 'Antonio', 'Méndez'),
('A0022', 'Clara', 'Serrano'),
('A0023', 'Fernando', 'Ortíz'),
('A0024', 'Beatriz', 'Crespo'),
('A0025', 'Raquel', 'García'),
('A0026', 'Francisco', 'Navarro'),
('A0027', 'Ángel', 'Romero'),
('A0028', 'Teresa', 'Paredes'),
('A0029', 'Rosa', 'Castro'),
('A0030', 'Víctor', 'Salazar'),
('A0031', 'Adriana', 'Santos'),
('A0032', 'Tomás', 'Gutiérrez'),
('A0033', 'Verónica', 'Luna'),
('A0034', 'Óscar', 'Vega'),
('A0035', 'Luisana', 'Ponce'),
('A0036', 'Esteban', 'Herrera'),
('A0037', 'Dolores', 'Jiménez'),
('A0038', 'Eva', 'Castillo'),
('A0039', 'César', 'Rojas'),
('A0040', 'Gabriela', 'González'),
('A0041', 'Rafael', 'Hidalgo'),
('A0042', 'Lorena', 'Cordero'),
('A0043', 'Juan José', 'Campos'),
('A0044', 'Marta', 'Ruiz'),
('A0045', 'Emilia', 'Sánchez'),
('A0046', 'Alfonso', 'Rodríguez'),
('A0047', 'Verónica', 'Morales'),
('A0048', 'Pablo', 'Rodríguez'),
('A0049', 'Marcos', 'Jiménez'),
('A0050', 'Silvia', 'Pérez');


INSERT INTO usuario (nombres, apellidos, tipo_documento, nro_documento, numero, tipo_usuario, nombre_usuario, contrasena, email) VALUES
('Juan', 'Pérez', 'DNI', '12345678', '987654321', 'ADMIN', 'juanperez', 'pass123', 'juan.perez@email.com'),
('Ana', 'González', 'PASAPORTE', 'P987654321', '987654322', 'DOCENTE', 'anagonzalez', 'pass123', 'ana.gonzalez@email.com'),
('Luis', 'Martínez', 'DNI', '23456789', '987654323', 'DOCENTE', 'luismartinez', 'pass123', 'luis.martinez@email.com'),
('Carlos', 'Hernández', 'PASAPORTE', 'P123456789', '987654324', 'ADMIN', 'carloshernandez', 'pass123', 'carlos.hernandez@email.com'),
('María', 'López', 'DNI', '34567890', '987654325', 'DOCENTE', 'marialopez', 'pass123', 'maria.lopez@email.com'),
('Pedro', 'Gómez', 'PASAPORTE', 'P234567890', '987654326', 'ADMIN', 'pedrogomez', 'pass123', 'pedro.gomez@email.com'),
('Sofía', 'Díaz', 'DNI', '45678901', '987654327', 'DOCENTE', 'sofiadiaz', 'pass123', 'sofia.diaz@email.com'),
('David', 'Vázquez', 'PASAPORTE', 'P345678901', '987654328', 'ADMIN', 'davidvazquez', 'pass123', 'david.vazquez@email.com'),
('Laura', 'Fernández', 'DNI', '56789012', '987654329', 'DOCENTE', 'laurafernandez', 'pass123', 'laura.fernandez@email.com'),
('Miguel', 'Sánchez', 'PASAPORTE', 'P456789012', '987654330', 'ADMIN', 'miguelsanchez', 'pass123', 'miguel.sanchez@email.com'),
('Sara', 'Ramírez', 'DNI', '67890123', '987654331', 'DOCENTE', 'saramirez', 'pass123', 'sara.ramirez@email.com'),
('José', 'Jiménez', 'PASAPORTE', 'P567890123', '987654332', 'ADMIN', 'josejimenez', 'pass123', 'jose.jimenez@email.com'),
('Isabel', 'Ruiz', 'DNI', '78901234', '987654333', 'DOCENTE', 'isabelruiz', 'pass123', 'isabel.ruiz@email.com'),
('Raúl', 'Torres', 'PASAPORTE', 'P678901234', '987654334', 'ADMIN', 'raultorres', 'pass123', 'raul.torres@email.com'),
('Carmen', 'Álvarez', 'DNI', '89012345', '987654335', 'DOCENTE', 'carmenalvarez', 'pass123', 'carmen.alvarez@email.com'),
('Javier', 'Gil', 'PASAPORTE', 'P789012345', '987654336', 'ADMIN', 'javiergil', 'pass123', 'javier.gil@email.com'),
('Elena', 'Molina', 'DNI', '90123456', '987654337', 'DOCENTE', 'elenamolina', 'pass123', 'elena.molina@email.com'),
('Manuel', 'Suárez', 'PASAPORTE', 'P890123456', '987654338', 'ADMIN', 'manuelsuarez', 'pass123', 'manuel.suarez@email.com'),
('Martín', 'Moreno', 'DNI', '12345679', '987654339', 'DOCENTE', 'martinmoreno', 'pass123', 'martin.moreno@email.com'),
('Paula', 'Muñoz', 'PASAPORTE', 'P234567891', '987654340', 'ADMIN', 'paulamunoz', 'pass123', 'paula.munoz@email.com'),
('Antonio', 'Méndez', 'DNI', '23456780', '987654341', 'DOCENTE', 'antoniomendez', 'pass123', 'antonio.mendez@email.com'),
('Clara', 'Serrano', 'PASAPORTE', 'P345678902', '987654342', 'ADMIN', 'claraserrano', 'pass123', 'clara.serrano@email.com'),
('Fernando', 'Ortíz', 'DNI', '34567891', '987654343', 'DOCENTE', 'fernandoortiz', 'pass123', 'fernando.ortiz@email.com'),
('Beatriz', 'Crespo', 'PASAPORTE', 'P456789013', '987654344', 'ADMIN', 'beatrizcrespo', 'pass123', 'beatriz.crespo@email.com'),
('Raquel', 'García', 'DNI', '45678902', '987654345', 'DOCENTE', 'raquelgarcia', 'pass123', 'raquel.garcia@email.com'),
('Francisco', 'Navarro', 'PASAPORTE', 'P567890124', '987654346', 'ADMIN', 'francisconavarro', 'pass123', 'francisco.navarro@email.com'),
('Ángel', 'Romero', 'DNI', '56789013', '987654347', 'DOCENTE', 'angelromero', 'pass123', 'angel.romero@email.com'),
('Teresa', 'Paredes', 'PASAPORTE', 'P678901235', '987654348', 'ADMIN', 'teresaparedes', 'pass123', 'teresa.paredes@email.com'),
('Rosa', 'Castro', 'DNI', '67890124', '987654349', 'DOCENTE', 'rosacastro', 'pass123', 'rosa.castro@email.com'),
('Víctor', 'Salazar', 'PASAPORTE', 'P789012346', '987654350', 'ADMIN', 'victorsalazar', 'pass123', 'victor.salazar@email.com'),
('Adriana', 'Santos', 'DNI', '78901235', '987654351', 'DOCENTE', 'adrianasantos', 'pass123', 'adriana.santos@email.com'),
('Tomás', 'Gutiérrez', 'PASAPORTE', 'P890123457', '987654352', 'ADMIN', 'tomasgutierrez', 'pass123', 'tomas.gutierrez@email.com'),
('Verónica', 'Luna', 'DNI', '89012346', '987654353', 'DOCENTE', 'veronicaluna', 'pass123', 'veronica.luna@email.com'),
('Óscar', 'Vega', 'PASAPORTE', 'P901234568', '987654354', 'ADMIN', 'oscarvega', 'pass123', 'oscar.vega@email.com'),
('Luisana', 'Ponce', 'DNI', '12345680', '987654355', 'DOCENTE', 'luisanaponce', 'pass123', 'luisana.ponce@email.com'),
('Esteban', 'Herrera', 'PASAPORTE', 'P234567892', '987654356', 'ADMIN', 'estebanherrera', 'pass123', 'esteban.herrera@email.com'),
('Dolores', 'Jiménez', 'DNI', '23456781', '987654357', 'DOCENTE', 'doloresjimenez', 'pass123', 'dolores.jimenez@email.com'),
('Eva', 'Castillo', 'PASAPORTE', 'P345678903', '987654358', 'ADMIN', 'evacastillo', 'pass123', 'eva.castillo@email.com'),
('César', 'Rojas', 'DNI', '34567892', '987654359', 'DOCENTE', 'cesarrojas', 'pass123', 'cesar.rojas@email.com'),
('Gabriela', 'González', 'PASAPORTE', 'P456789014', '987654360', 'ADMIN', 'gabrielagonzalez', 'pass123', 'gabriela.gonzalez@email.com'),
('Rafael', 'Hidalgo', 'DNI', '45678903', '987654361', 'DOCENTE', 'rafaelhidalgo', 'pass123', 'rafael.hidalgo@email.com'),
('Lorena', 'Cordero', 'PASAPORTE', 'P567890125', '987654362', 'ADMIN', 'lorenacordero', 'pass123', 'lorena.cordero@email.com'),
('Juan José', 'Campos', 'DNI', '56789014', '987654363', 'DOCENTE', 'juanjosecampos', 'pass123', 'juan.jose.campos@email.com'),
('Marta', 'Ruiz', 'PASAPORTE', 'P678901236', '987654364', 'ADMIN', 'martaruiz', 'pass123', 'marta.ruiz@email.com'),
('Emilia', 'Sánchez', 'DNI', '67890125', '987654365', 'DOCENTE', 'emiliasanchez', 'pass123', 'emilia.sanchez@email.com'),
('Alfonso', 'Rodríguez', 'PASAPORTE', 'P789012347', '987654366', 'ADMIN', 'alfonsorodriguez', 'pass123', 'alfonso.rodriguez@email.com'),
('Verónica', 'Morales', 'DNI', '78901236', '987654367', 'DOCENTE', 'veronicamorales', 'pass123', 'veronica.morales@email.com'),
('Pablo', 'Rodríguez', 'PASAPORTE', 'P890123458', '987654368', 'ADMIN', 'pablorodriguez', 'pass123', 'pablo.rodriguez@email.com'),
('Marcos', 'Jiménez', 'DNI', '90123457', '987654369', 'DOCENTE', 'marcosjimenez', 'pass123', 'marcos.jimenez@email.com'),
('Silvia', 'Pérez', 'PASAPORTE', 'P901234569', '987654370', 'ADMIN', 'silviaperez', 'pass123', 'silvia.perez@email.com');


INSERT INTO laboratorio (numero_lab, capacidad) VALUES
('LAB-101', 30),
('LAB-102', 25),
('LAB-103', 35),
('LAB-104', 40),
('LAB-105', 20),
('LAB-106', 30);



INSERT INTO asignatura (nombre, codigo) VALUES
('Matemáticas I', 'IS001'),
('Matemáticas II', 'IS002'),
('Física I', 'IS003'),
('Física II', 'IS004'),
('Cálculo Diferencial', 'IS005'),
('Cálculo Integral', 'IS006'),
('Estructuras Discretas', 'IS007'),
('Algoritmos y Estructuras de Datos', 'IS008'),
('Programación I', 'IS009'),
('Programación II', 'IS010'),
('Bases de Datos I', 'IS011'),
('Bases de Datos II', 'IS012'),
('Sistemas Operativos', 'IS013'),
('Redes de Computadoras', 'IS014'),
('Arquitectura de Computadores', 'IS015'),
('Ingeniería de Software', 'IS016'),
('Desarrollo Web', 'IS017'),
('Desarrollo Móvil', 'IS018'),
('Análisis y Diseño de Sistemas', 'IS019'),
('Administración de Proyectos de Software', 'IS020'),
('Inteligencia Artificial', 'IS021'),
('Teoría de la Computación', 'IS022'),
('Ingeniería de Requerimientos', 'IS023'),
('Seguridad Informática', 'IS024'),
('Computación en la Nube', 'IS025'),
('Minería de Datos', 'IS026'),
('Comunicaciones Digitales', 'IS027'),
('Sistemas Embebidos', 'IS028'),
('Ética Profesional', 'IS029'),
('Economía y Gestión de la Tecnología', 'IS030');


INSERT INTO horario_laboratorio (id_laboratorio, id_asignatura, dia, horario_inicio, horario_fin, fecha_inicio, id_usuario, codigo_horario) VALUES
(1, 1, 'LUNES', '08:00:00', '10:00:00', '02-12-2024', 1, 'HS01'),
(2, 2, 'LUNES', '10:00:00', '12:00:00', '03-12-2024', 2, 'HS02'),
(3, 3, 'LUNES', '12:00:00', '14:00:00', '04-12-2024', 3, 'HS03'),
(4, 4, 'LUNES', '14:00:00', '16:00:00', '05-12-2024', 4, 'HS04'),
(5, 5, 'LUNES', '16:00:00', '18:00:00', '06-12-2024', 5, 'HS05'),
(6, 6, 'LUNES', '08:00:00', '10:00:00', '07-12-2024', 6, 'HS06'),
(1, 7, 'MARTES', '08:00:00', '10:00:00', '08-12-2024', 7, 'HS07'),
(2, 8, 'MARTES', '10:00:00', '12:00:00', '09-12-2024', 8, 'HS08'),
(3, 9, 'MARTES', '12:00:00', '14:00:00', '10-12-2024', 9, 'HS09'),
(4, 10, 'MARTES', '14:00:00', '16:00:00', '11-12-2024', 10, 'HS10'),
(5, 11, 'MARTES', '16:00:00', '18:00:00', '12-12-2024', 11, 'HS11'),
(6, 12, 'MARTES', '08:00:00', '10:00:00', '13-12-2024', 12, 'HS12'),
(1, 13, 'MIERCOLES', '08:00:00', '10:00:00', '14-12-2024', 13, 'HS13'),
(2, 14, 'MIERCOLES', '10:00:00', '12:00:00', '15-12-2024', 14, 'HS14'),
(3, 15, 'MIERCOLES', '12:00:00', '14:00:00', '16-12-2024', 15, 'HS15'),
(4, 16, 'MIERCOLES', '14:00:00', '16:00:00', '17-12-2024', 16, 'HS16'),
(5, 17, 'MIERCOLES', '16:00:00', '18:00:00', '18-12-2024', 17, 'HS17'),
(6, 18, 'MIERCOLES', '08:00:00', '10:00:00', '19-12-2024', 18, 'HS18'),
(1, 19, 'JUEVES', '08:00:00', '10:00:00', '20-12-2024', 19, 'HS19'),
(2, 20, 'JUEVES', '10:00:00', '12:00:00', '21-12-2024', 20, 'HS20'),
(3, 21, 'JUEVES', '12:00:00', '14:00:00', '22-12-2024', 21, 'HS21'),
(4, 22, 'JUEVES', '14:00:00', '16:00:00', '23-12-2024', 22, 'HS22'),
(5, 23, 'JUEVES', '16:00:00', '18:00:00', '24-12-2024', 23, 'HS23'),
(6, 24, 'JUEVES', '08:00:00', '10:00:00', '25-12-2024', 24, 'HS24'),
(1, 25, 'VIERNES', '08:00:00', '10:00:00', '26-12-2024', 25, 'HS25'),
(2, 26, 'VIERNES', '10:00:00', '12:00:00', '27-12-2024', 26, 'HS26'),
(3, 27, 'VIERNES', '12:00:00', '14:00:00', '28-12-2024', 27, 'HS27'),
(4, 28, 'VIERNES', '14:00:00', '16:00:00', '29-12-2024', 28, 'HS28'),
(5, 29, 'VIERNES', '16:00:00', '18:00:00', '30-12-2024', 29, 'HS29'),
(6, 30, 'VIERNES', '08:00:00', '10:00:00', '31-12-2024', 30, 'HS30'),
(1, 1, 'SABADO', '08:00:00', '10:00:00', '01-01-2025', 31, 'HS31'),
(2, 2, 'SABADO', '10:00:00', '12:00:00', '02-01-2025', 32, 'HS32'),
(3, 3, 'SABADO', '12:00:00', '14:00:00', '03-01-2025', 33, 'HS33'),
(4, 4, 'SABADO', '14:00:00', '16:00:00', '04-01-2025', 34, 'HS34'),
(5, 5, 'SABADO', '16:00:00', '18:00:00', '05-01-2025', 35, 'HS35'),
(6, 6, 'SABADO', '08:00:00', '10:00:00', '06-01-2025', 36, 'HS36'),
(1, 7, 'DOMINGO', '08:00:00', '10:00:00', '07-01-2025', 37, 'HS37'),
(2, 8, 'DOMINGO', '10:00:00', '12:00:00', '08-01-2025', 38, 'HS38'),
(3, 9, 'DOMINGO', '12:00:00', '14:00:00', '09-01-2025', 39, 'HS39'),
(4, 10, 'DOMINGO', '14:00:00', '16:00:00', '10-01-2025', 40, 'HS40');



INSERT INTO equipo (cod_patrimonial, id_laboratorio, tipo_equipo, numero_serie, estado) 
VALUES 
('EQ001', 1, 'Teclado', 'SN1234567890', 'OPERATIVO'),
('EQ002', 1, 'CPU', 'SN9876543210', 'OPERATIVO'),
('EQ003', 2, 'Monitor', 'SN1122334455', 'NO OPERATIVO'),
('EQ004', 2, 'PizarraDigital', 'SN2233445566', 'OPERATIVO'),
('EQ005', 3, 'Teclado', 'SN3344556677', 'NO OPERATIVO'),
('EQ006', 3, 'CPU', 'SN4455667788', 'OPERATIVO'),
('EQ007', 4, 'Monitor', 'SN5566778899', 'OPERATIVO'),
('EQ008', 4, 'Teclado', 'SN6677889900', 'NO OPERATIVO'),
('EQ009', 5, 'PizarraDigital', 'SN7788990011', 'OPERATIVO'),
('EQ010', 5, 'CPU', 'SN8899001122', 'NO OPERATIVO'),
('EQ011', 6, 'Monitor', 'SN9900112233', 'OPERATIVO'),
('EQ012', 6, 'Teclado', 'SN0011223344', 'NO OPERATIVO'),
('EQ013', 1, 'CPU', 'SN1122334455', 'OPERATIVO'),
('EQ014', 1, 'PizarraDigital', 'SN2233445566', 'NO OPERATIVO'),
('EQ015', 2, 'Monitor', 'SN3344556677', 'OPERATIVO'),
('EQ016', 2, 'Teclado', 'SN4455667788', 'NO OPERATIVO'),
('EQ017', 3, 'CPU', 'SN5566778899', 'OPERATIVO'),
('EQ018', 3, 'Monitor', 'SN6677889900', 'NO OPERATIVO'),
('EQ019', 4, 'PizarraDigital', 'SN7788990011', 'OPERATIVO'),
('EQ020', 4, 'Teclado', 'SN8899001122', 'NO OPERATIVO'),
('EQ021', 5, 'CPU', 'SN9900112233', 'OPERATIVO'),
('EQ022', 5, 'Monitor', 'SN0011223344', 'NO OPERATIVO'),
('EQ023', 6, 'PizarraDigital', 'SN1122334455', 'OPERATIVO'),
('EQ024', 6, 'Teclado', 'SN2233445566', 'NO OPERATIVO'),
('EQ025', 1, 'CPU', 'SN3344556677', 'OPERATIVO'),
('EQ026', 1, 'Monitor', 'SN4455667788', 'NO OPERATIVO'),
('EQ027', 2, 'PizarraDigital', 'SN5566778899', 'OPERATIVO'),
('EQ028', 2, 'Teclado', 'SN6677889900', 'NO OPERATIVO'),
('EQ029', 3, 'CPU', 'SN7788990011', 'OPERATIVO'),
('EQ030', 3, 'Monitor', 'SN8899001122', 'NO OPERATIVO'),
('EQ031', 4, 'PizarraDigital', 'SN9900112233', 'OPERATIVO'),
('EQ032', 4, 'Teclado', 'SN0011223344', 'NO OPERATIVO'),
('EQ033', 5, 'CPU', 'SN1122334455', 'OPERATIVO'),
('EQ034', 5, 'Monitor', 'SN2233445566', 'NO OPERATIVO'),
('EQ035', 6, 'PizarraDigital', 'SN3344556677', 'OPERATIVO'),
('EQ036', 6, 'Teclado', 'SN4455667788', 'NO OPERATIVO'),
('EQ037', 1, 'CPU', 'SN5566778899', 'OPERATIVO'),
('EQ038', 1, 'Monitor', 'SN6677889900', 'NO OPERATIVO'),
('EQ039', 2, 'PizarraDigital', 'SN7788990011', 'OPERATIVO'),
('EQ040', 2, 'Teclado', 'SN8899001122', 'NO OPERATIVO');



INSERT INTO asistencia (fecha, estado, id_alumno, id_horario) VALUES
('2024-11-29', 'PRESENTE', 1, 1),
('2024-11-29', 'AUSENTE', 2, 1),
('2024-11-29', 'PRESENTE', 3, 2),
('2024-11-29', 'AUSENTE', 4, 2),
('2024-11-29', 'PRESENTE', 5, 3),
('2024-11-29', 'PRESENTE', 6, 3),
('2024-11-29', 'AUSENTE', 7, 4),
('2024-11-29', 'PRESENTE', 8, 4),
('2024-11-29', 'AUSENTE', 9, 5),
('2024-11-29', 'PRESENTE', 10, 5),
('2024-11-29', 'AUSENTE', 11, 6),
('2024-11-29', 'PRESENTE', 12, 6),
('2024-11-29', 'PRESENTE', 13, 7),
('2024-11-29', 'AUSENTE', 14, 7),
('2024-11-29', 'PRESENTE', 15, 8),
('2024-11-29', 'AUSENTE', 16, 8),
('2024-11-29', 'PRESENTE', 17, 9),
('2024-11-29', 'AUSENTE', 18, 9),
('2024-11-29', 'PRESENTE', 19, 10),
('2024-11-29', 'PRESENTE', 20, 10),
('2024-11-29', 'AUSENTE', 21, 11),
('2024-11-29', 'PRESENTE', 22, 11),
('2024-11-29', 'AUSENTE', 23, 12),
('2024-11-29', 'PRESENTE', 24, 12),
('2024-11-29', 'PRESENTE', 25, 13),
('2024-11-29', 'AUSENTE', 26, 13),
('2024-11-29', 'PRESENTE', 27, 14),
('2024-11-29', 'AUSENTE', 28, 14),
('2024-11-29', 'PRESENTE', 29, 15),
('2024-11-29', 'AUSENTE', 30, 15),
('2024-11-29', 'PRESENTE', 31, 16),
('2024-11-29', 'AUSENTE', 32, 16),
('2024-11-29', 'PRESENTE', 33, 17),
('2024-11-29', 'AUSENTE', 34, 17),
('2024-11-29', 'PRESENTE', 35, 18),
('2024-11-29', 'AUSENTE', 36, 18),
('2024-11-29', 'PRESENTE', 37, 19),
('2024-11-29', 'AUSENTE', 38, 19),
('2024-11-29', 'PRESENTE', 39, 20),
('2024-11-29', 'AUSENTE', 40, 20),
('2024-11-29', 'PRESENTE', 41, 21),
('2024-11-29', 'AUSENTE', 42, 21),
('2024-11-29', 'PRESENTE', 43, 22),
('2024-11-29', 'AUSENTE', 44, 22),
('2024-11-29', 'PRESENTE', 45, 23),
('2024-11-29', 'AUSENTE', 46, 23),
('2024-11-29', 'PRESENTE', 47, 24),
('2024-11-29', 'AUSENTE', 48, 24),
('2024-11-29', 'PRESENTE', 49, 25),
('2024-11-29', 'AUSENTE', 50, 25);

-- Asistencia para el 28 de noviembre de 2024
INSERT INTO asistencia (fecha, estado, id_alumno, id_horario) VALUES
('2024-11-28', 'PRESENTE', 1, 1),
('2024-11-28', 'AUSENTE', 2, 1),
('2024-11-28', 'PRESENTE', 3, 2),
('2024-11-28', 'AUSENTE', 4, 2),
('2024-11-28', 'PRESENTE', 5, 3),
('2024-11-28', 'PRESENTE', 6, 3),
('2024-11-28', 'AUSENTE', 7, 4),
('2024-11-28', 'PRESENTE', 8, 4),
('2024-11-28', 'AUSENTE', 9, 5),
('2024-11-28', 'PRESENTE', 10, 5),
('2024-11-28', 'AUSENTE', 11, 6),
('2024-11-28', 'PRESENTE', 12, 6),
('2024-11-28', 'PRESENTE', 13, 7),
('2024-11-28', 'AUSENTE', 14, 7),
('2024-11-28', 'PRESENTE', 15, 8),
('2024-11-28', 'AUSENTE', 16, 8),
('2024-11-28', 'PRESENTE', 17, 9),
('2024-11-28', 'AUSENTE', 18, 9),
('2024-11-28', 'PRESENTE', 19, 10),
('2024-11-28', 'PRESENTE', 20, 10),
('2024-11-28', 'AUSENTE', 21, 11),
('2024-11-28', 'PRESENTE', 22, 11),
('2024-11-28', 'AUSENTE', 23, 12),
('2024-11-28', 'PRESENTE', 24, 12),
('2024-11-28', 'PRESENTE', 25, 13),
('2024-11-28', 'AUSENTE', 26, 13),
('2024-11-28', 'PRESENTE', 27, 14),
('2024-11-28', 'AUSENTE', 28, 14),
('2024-11-28', 'PRESENTE', 29, 15),
('2024-11-28', 'AUSENTE', 30, 15),
('2024-11-28', 'PRESENTE', 31, 16),
('2024-11-28', 'AUSENTE', 32, 16),
('2024-11-28', 'PRESENTE', 33, 17),
('2024-11-28', 'AUSENTE', 34, 17),
('2024-11-28', 'PRESENTE', 35, 18),
('2024-11-28', 'AUSENTE', 36, 18),
('2024-11-28', 'PRESENTE', 37, 19),
('2024-11-28', 'AUSENTE', 38, 19),
('2024-11-28', 'PRESENTE', 39, 20),
('2024-11-28', 'AUSENTE', 40, 20),
('2024-11-28', 'PRESENTE', 41, 21),
('2024-11-28', 'AUSENTE', 42, 21),
('2024-11-28', 'PRESENTE', 43, 22),
('2024-11-28', 'AUSENTE', 44, 22),
('2024-11-28', 'PRESENTE', 45, 23),
('2024-11-28', 'AUSENTE', 46, 23),
('2024-11-28', 'PRESENTE', 47, 24),
('2024-11-28', 'AUSENTE', 48, 24),
('2024-11-28', 'PRESENTE', 49, 25),
('2024-11-28', 'AUSENTE', 50, 25);

-- Insertando datos en la tabla horarios_alumno
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (1, 1);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (2, 2);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (3, 3);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (4, 4);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (5, 5);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (6, 6);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (7, 7);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (8, 8);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (9, 9);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (10, 10);

-- Continuación con más datos
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (11, 11);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (12, 12);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (13, 13);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (14, 14);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (15, 15);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (16, 16);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (17, 17);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (18, 18);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (19, 19);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (20, 20);

-- Más ejemplos con alumnos y horarios diferentes
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (1, 2);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (2, 3);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (3, 4);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (4, 5);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (5, 6);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (6, 7);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (7, 8);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (8, 9);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (9, 10);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (10, 11);

-- Continuación con más combinaciones
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (11, 12);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (12, 13);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (13, 14);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (14, 15);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (15, 16);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (16, 17);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (17, 18);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (18, 19);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (19, 20);
INSERT INTO horarios_alumno (id_alumno, id_horario) VALUES (20, 1);