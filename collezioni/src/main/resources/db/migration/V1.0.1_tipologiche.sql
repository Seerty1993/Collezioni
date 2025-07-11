INSERT INTO collezioni.tipo_beyblade_ratchet (id,height, bumps, weight)
VALUES(1,60, 3, 6.30),
(2,80, 3, 7.10),
(3,60, 4, 6.40);

INSERT INTO collezioni.tipo_beyblade_punte
(id,name_point, "owned", weight, originBeyFrom)
VALUES( 1,'Ball', true, 2.10,'BX-03 Wizard Arrow 4-80B'),
( 2,'Flat', true, 2.30, 'BX-01 Dransword 3-60F'),;

INSERT INTO collezioni.tipo_beyblade_blades
(id, name_blades,weight, punte_id, ratchet_id)
VALUES(1, 'Dran Sword', 34.5, 2, 1);


