INSERT INTO MathunakiUser(user_id, first_name, last_name, address, email, phone_number, phone_number_parent, phone_number_2, information, status, price)
VALUES(1, 'Bernard', 'Laporte', '22 Avenue du Colysée 59130 Lambersart', 'trigalliere@gmail.com', '0681134594', null, '0320939873', 'Cours de Maple. Passe par le CNED.', 1, 25.00),
(2, 'Alexia', 'Potdevin', '145 rue Gambetta, 3e étage, Appartement du fond', 'alexia18@numericable.fr', '0607651918', '0672461872', '0320470578', 'Cours Automatique. École d''ingénieur Polytech''Lille, département Génie Biologique Agroalimentaire.', 1, 20.00),
(3, 'Thomas', 'Nicolazic Zimmermann', '15 rue du Boulevard 59170 Croix', 'nicolazic.jeannoel@wanadoo.fr', null, null, '0687584968', 'Bon niveau.', 1, 25.00),
(4, 'Valérie', 'Alvares', '5 rue de la Grande Brasserie, Résidence Jardin des Sciences, Appartement 204', 'vf.alva@orange.fr', '0683764126', '0688901753', '0466509839', 'Prépa intégrée à l''ENSCL. Cours de physique.', 1, 25.00);

INSERT INTO Tuition(tuition_id, user_id, user_level, description, resource)
VALUES(1, 1, 'MPSI', 'Cours de Maple.', null),
(2, 2, 'ECOLE_INGENIEUR', 'Cours d''automatique.', null),
(3, 3, 'MPSI', 'Cours de Maths. Élève au lycée Faidherbe en MPSI.', null),
(4, 4, 'PREPA_INTEGREE', 'Prépa intégrée à l''ENSCL. Cours de physique.', null);

INSERT INTO Schedule(schedule_id, tuition_id, start_date, duration, description)
VALUES(1, 1, '2012-09-15 11:00:00', 60, null),
(2, 1, '2012-09-18 13:00:00', 60, null),
(3, 1, '2012-09-26 14:00:00', 60, null),
(4, 1, '2012-09-29 11:00:00', 60, null),
(5, 1, '2012-10-02 11:00:00', 60, null),
(6, 1, '2012-10-05 11:00:00', 60, null),
(7, 1, '2012-10-09 14:00:00', 60, null),
(8, 1, '2012-10-17 11:00:00', 60, null),
(9, 2, '2012-10-04 16:00:00', 120, null),
(10, 2, '2012-10-11 14:00:00', 120, null),
(11, 3, '2012-10-16 17:00:00', 60, null),
(12, 3, '2012-10-23 17:00:00', 90, null),
(13, 4, '2012-10-08 18:00:00', 60, null),
(14, 4, '2012-10-15 18:00:00', 60, null),
(15, 1, '2012-10-24 11:00:00', 60, null),
(16, 2, '2012-10-18 14:00:00', 120, null);