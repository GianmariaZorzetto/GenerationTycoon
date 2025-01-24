CREATE DATABASE generation;

USE generation;

INSERT INTO Brainj (question, answer) VALUES
('Crea un programma che stampi il risultato della serie di Fibonacci con n = 20.', '6765\n'),
('Crea un programma che stampi i numeri pari da 1 a 100, tutti su una riga separati da \'=\'.', '2=4=6=8=10=12=14=16=18=20=22=24=26=28=30=32=34=36=38=40=42=44=46=48=50=52=54=56=58=60=62=64=66=68=70=72=74=76=78=80=82=84=86=88=90=92=94=96=98=100\n'),
('Crea un programma che stampi i numeri dispari da 1 a 100, tutti su una riga separati da \'_\'.', '1_3_5_7_9_11_13_15_17_19_21_23_25_27_29_31_33_35_37_39_41_43_45_47_49_51_53_55_57_59_61_63_65_67_69_71_73_75_77_79_81_83_85_87_89_91_93_95_97_99\n'),
('Crea un programma che stampi i numeri da 1 a 50, ma per i multipli di 3 stampi ''Fizz'', per i multipli di 5 stampi ''Buzz'' e per i multipli sia di 3 e di 5 ''FizzBuzz''', '1\n2\nFizz\n4\nBuzz\nFizz\n7\n8\nFizz\nBuzz\n11\nFizz\n13\n14\nFizzBuzz\n16\n17\nFizz\n19\nBuzz\nFizz\n22\n23\nFizz\nBuzz\n26\nFizz\n28\n29\nFizzBuzz\n31\n32\nFizz\n34\nBuzz\nFizz\n37\n38\nFizz\nBuzz\n41\nFizz\n43\n44\nFizzBuzz\n46\n47\nFizz\n49\nBuzz\n'),
('Crea un programma che stampi il contenuto di questa lista ordinata: lista = {482, 37, 915, 256, 673, 104, 829, 512, 390, 745, 218, 654, 88, 399, 102}', '37\n88\n102\n104\n218\n256\n390\n399\n482\n512\n654\n673\n745\n829\n915\n'),
('Scrivi un programma che trovi il massimo tra questa sequenza di numeri: {763, 45, 289, 912, 578, 34, 672, 150, 804, 219, 487, 356, 921, 634, 78, 405, 812, 267, 815, 814}. (Per la stampa del numero utilizza System.out.println())', '921\n'),
('Crea un programma che prenda il mio nome da questa stringa: ''SSTEFANO STEEFFANO STEFAANO STEFANIO STREFANO STEFANO STEFANP STEFAFA'' e ne stampi tutti i caratteri a capo. Attenzione: tutto in minuscolo!', 's\nt\ne\nf\na\nn\no\n'),
('Stampa la somma di tutti i numeri primi compresi tra 1 e 30.', '130\n'),
('Converti 52 in numero binario.', '110100\n'),
('Stampa il numero di vocali minuscole non accentate in questa frase: ''En mathématiques, la suite de Fibonacci est une suite de nombres entiers dans laquelle chaque nombre est la somme des deux nombres qui le précèdent.''', '50\n'),
('Stampa tutte le stringhe palindrome da questa lista: {radar, albero, 101, casa, ottimo, mare, anana, sole, civic, luna, rotator, farfalla, kayak, cielo, redivider, mappa, stella, non, serra, abba, orso, anagramma, otto, palindromo, elefante}', 'radar\n101\nanana\ncivic\nrotator\nkayak\nredivider\nnon\nabba\notto\n'),
('Scrivi un programma che trovi il minimo tra questa sequenza di numeri: {76, 45, 28, 91, 57, 34, 67, 15, 80, 21, 48, 35, 21, 34, 78, 45, 12, 67, 16, 14}', '12\n'),
('Quanto vale la somma di tutte le consonanti in byte di questa frase: ''The national flag of Mozambique is a horizontal tricolour of dark green, black, and golden-yellow with white fimbriations and a red isosceles triangle at the hoist.''', '15386\n'),
('Scrivi un programma che stampa la somma di tutti i numeri tra 1 e 50 pari e multipli di 6.', '512\n'),
('Scrivi un programma che dalla stringa ''ciaosonostefano'' stampi la stringa con tutti i caratteri successivi nella tabella ASCII.', 'djbptpoptufgbop\n');

