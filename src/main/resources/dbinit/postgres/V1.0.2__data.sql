INSERT INTO collezioni.tipo_beyblade_ratchet (id,height, bumps, weight)
VALUES(1,60, 3, 6.30),
      (2,80, 3, 7.10),
      (3,60, 4, 6.40);

INSERT INTO collezioni.tipo_beyblade_punte (id,namepoint, owned, weight, originBeyFrom)
VALUES( 1,'Ball', true, 2.10,'BX-03 Wizard Arrow 4-80B'),
      ( 2,'Flat', true, 2.30, 'BX-01 Dransword 3-60F');

INSERT INTO collezioni.tipo_beyblade_blades
(id, spin, type, weight, nameblades, owned)
VALUES(1, 'RIGHT', 'ATTACK',34.5,'Dran Sword',true);

INSERT INTO collezioni.category (id,name_category)
VALUES(1,'CARDS'),
      (2,'BEYBLADE'),
      (3,'ALTRO');

INSERT INTO collezioni.beyblade
(id, name, description, quantity, formato, ratchet_id, punta_id, blades_id, owned, wish, category_id, note)
VALUES(nextval('collezioni.beyblade_id_seq'::regclass), 'Dran Sword 3-60F', 'Dran Sword is a welcome addition to any collection as it features solid attack but is generally outclassed by a lot more options specifically after Dran Sword gets worn after use. Due to the fact other releases like Phoenix Wing and other attackers not only outclass for pure attack but for other combination types, Dran Sword is a welcome addition but not a must-have currently.', 1, 'HASBRO', 1, 2, 1, true, false, 2, 'Attacco');

