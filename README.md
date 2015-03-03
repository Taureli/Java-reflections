#Reflection benchmark
Autor: Jakub Karolczak

> Spis treści:
> * [Krótki wstęp o programie](#krótki-wstęp-o-programie)
> * [Wykonywanie testów](#wykonywanie-testów)
>  * [Skrypt](#skrypt)
>  * [Otrzymane czasy](#otrzymane-czasy)
> * [Wnioski](#wnioski)

##Krótki wstęp o programie

[Główny kod programu](https://github.com/Taureli/Java-reflections/blob/master/src/Main.java).

Na potrzeby testów wykorzystałem prostą klasę "Client", praktycznie identyczną do pokazywanej na wykładzie, gdzie istotna jest zmienna "age", którą przy pierwszych testach ustawiłem jako typ prosty (int) a później jako typ referencyjny (Integer), oraz metoda "changeAge", przyjmująca zmienną typu int jako argument i zwiększająca wiek o przekazaną wartość.

Wszystkie operacje testowe wywoływane są jako osobne funkcje co może mieć minimalnie negatywny wpływ na otrzymane czasy, choć nie uświadczyłem zauważalnych różnic.

Każda z operacji wykonywana jest po 100 milionów razy.

##Wykonywanie testów
###Skrypt

Do wielokrotnego uruchomiania programu wykorzystałem prosty [skrypt Bashowy](https://github.com/Taureli/Java-reflections/blob/master/bin/RunXTimes.sh), który wszystkie wykonywane pomiary zapisuje do pliku wyjściowego.
Do poniższych wyników program został uruchomiony 10 razy dla zmiennej typu prostego i kolejne 10 dla zmiennej referencyjnej.

###Otrzymane czasy

Czasy, wypisane przez program i skrypt w [pliku wyjściowym](https://github.com/Taureli/Java-reflections/blob/master/bin/outfile), przedstawione w postaci tabelek.

####Zmienna typu prostego

|wykonanie|odczyt z reflection|odczyt bez reflection|zapis z reflection|zapis bez reflection|wywołanie metody z reflection|wywołanie metody bez reflection|
|:-----:|:---:|:-----:|:---:|:-----:|:---:|:-----:|
|1|1110ms|0ms|1271ms|15ms|875ms|0ms|
|2|1062ms|16ms|1234ms|16ms|889ms|16ms|
|3|1068ms|16ms|1235ms|15ms|891ms|0ms|
|4|1108ms|0ms|1271ms|16ms|891ms|15ms|
|5|1079ms|0ms|1250ms|15ms|921ms|15ms|
|6|1084ms|0ms|1235ms|15ms|875ms|0ms|
|7|1109ms|0ms|1265ms|16ms|865ms|15ms|
|8|1062ms|16ms|1266ms|15ms|889ms|16ms|
|9|1084ms|0ms|1250ms|16ms|875ms|0ms|
|10|1092ms|16ms|1250ms|16ms|869ms|16ms|
|średnio|1085,8ms|6,4ms|1252,7ms|15,5ms|84ms|9,3ms|

####Zmienna typu referencyjnego

|wykonanie|odczyt z reflection|odczyt bez reflection|zapis z reflection|zapis bez reflection|wywołanie metody z reflection|wywołanie metody bez reflection|
|:-----:|:---:|:-----:|:---:|:-----:|:---:|:-----:|
|1|1203ms|0ms|1813ms|16ms|959ms|16ms|
|2|1203ms|0ms|1766ms|31ms|1000ms|0ms|
|3|1203ms|16ms|1766ms|31ms|984ms|16ms|
|4|1214ms|16ms|1781ms|16ms|969ms|15ms|
|5|1219ms|15ms|1774ms|31ms|969ms|15ms|
|6|1291ms|16ms|1797ms|31ms|966ms|0ms|
|7|1219ms|0ms|1812ms|16ms|969ms|0ms|
|8|1215ms|0ms|1781ms|16ms|1000ms|16ms|
|9|1219ms|0ms|1789ms|16ms|953ms|16ms|
|10|1219ms|16ms|1797ms|15ms|972ms|0ms|
|średnio|1220,5ms|7,9ms|1787,6ms|21,9ms|974,1ms|9,4ms|

##Wnioski

Możemy łatwo zauważyć, że różnica między czasami wykonań operacji bezpośrednio a przez refleksje jest bardzo duża, z korzyścią czasową dla operacji bezpośrednich.

Zauważalna jest również różnica w czasach między zmienną prostą a referencyjną, gdzie operacje są zdecydowanie dłuższe dla typu referencyjnego, zwłaszcza z wykorzystaniem refleksji.

Należy oczywiście wziąć pod uwagę fakt, że wszystkie wyniki podane są w milisekundach, trudno więc tu mówić o faktycznie mocno odczuwalnych różnicach czasowych.
