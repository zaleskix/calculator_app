# Calculator app


Aplikacja stworzona zgodnie z zadaniem rekrutacyjnym.

### Instrukcja uruchomienia
*   Uruchomic skrypt "compile_and_run.sh"
    * W razie błedów usunąc folder .m2 
#### Przetestowane na systemie Windows oraz Linux Ubuntu


-----------------------
### Tresc zadania

![zadanie](https://i.ibb.co/DM7mbkB/Przechwytywanie.png)
-----------------------

### Edycja parametrow programu
*   Plik znajduje sie w folderze /resources
*   Nazwę pliku i ścieżke można zmienic w klasie App.java

#### Założenia konfiguracyjne:
*   Plik jest w formacie *.txt
*   Plik ma nazwę "instructions.txt"
    
-----------------------
### Uruchomienie testów
*   Uruchomic skrypt "run_tests.sh"

-----------------------
### Założenia zadania:

#### Aplikacja jest samodzielna - stąd zastosowanie wszedzie klas Exception a nie logowanie błedów przy użyciu np. slf4j

*   Aplikacja zwraca output w konsoli  
 
*   Aplikacja obsługuje liczby zmienno-przecinkowe

*   Aplikacja/Moduł bedzie w przyszłości rozbudowywany - stąd wykorzystanie interfesów w pakietach uniwersalnych takich jak validator

*   Za pomoca testow jednostkowych przetestowane scenariusze ponizej 

-----------------------
### Przetestowane przypadki
1) Poprawny plik (program dziala, validuje, wczytuje i przetwarza plik poprawnie po czym zwraca wynik) 
2) Plik w jakis sposób nie jest poprawny (validacyjna klasa zwraca false)
3) Uzytkownik próbuje dzielic przez 0
4) Nazwa pliku jest pusta 
    *   W przyszlosci moze byc wprowadzana przez uzytkownika a nie zdefiniowana przez programiste
5) Plik nie istnieje
6) Plik jest pusty
7) Slowo kluczowe APPLY wystepuje wiele razy
8) Slowo wystepujace w pliku to nie poprawna instrukcja
9) Linia w pliku posiada cos wiecej niz tylko instrukcje i liczbe
10) Slowo kluczowe APPLY nie wystepuje w pliku
11) Slowo kluczowe APPLY nie jest na koncu pliku (wystepuje po nim inna instrukcja/slowo)
12) Drugi argument w którejkolwiek z linni w pliku nie jest poprawna liczba liczba


-----------------------
### Technologie

*  Maven
*  jUnit
*  Mockito







