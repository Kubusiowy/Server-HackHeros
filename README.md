# Server HackHeros

Backend przygotowany na konkurs HackHeroes. 

## Technologie

Poniżej zestawienie głównych narzędzi i technologii wykorzystanych w projekcie.

## Technologie

## Technologie

| Obszar        | Technologia |
|---------------|:-----------:|
| Język         | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg" width="28" /> Kotlin |
| Framework     | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/ktor/ktor-original.svg" width="28" /> Ktor |
| Baza danych   | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="32" /> MySQL |
| Build system  | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-original.svg" width="30" /> Gradle |
| JVM           | <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="28" /> Temurin JDK 21 |



Repozytorium projektu dostępne jest pod adresem:  
**[Pobierz projekt z GitHub](https://github.com/Kubusiowy/Server-HackHeros)**

---

## Wymagania środowiskowe

Aby uruchomić projekt, wymagane są:

- JDK 21 (Temurin)
- MySQL uruchomiony na porcie `8889`
- IntelliJ IDEA (zalecane Ultimate, dopuszczalne Community)
- Gradle (projekt korzysta z wbudowanego wrappera)
- 
### Wersje

- Kotlin: 2.x
- Ktor: 3.x
- JDK: Temurin 21
- Gradle: wrapper (wersja zgodna z projektem)
---

## Instalacja

### 1. Przygotowanie bazy danych MySQL

Uruchom MySQL na porcie `8889`.

Pobierz i zaimportuj strukturę bazy danych:

[dbMySql.sql](SQL/HackHeros.sql)

Diagram struktury tabel:

![Struktura bazy](SQL/struktura.png)

---

### 2. Instalacja JDK 21 (Temurin)

Pobierz odpowiednie wydanie JDK:

https://adoptium.net/en-GB/temurin/releases?version=21&os=any&arch=any

Po instalacji upewnij się, że JDK jest dodane do zmiennej systemowej PATH.

---

### 3. Konfiguracja IntelliJ IDEA

Otwórz projekt, a następnie przejdź do:

**File → Project Structure**

Ustawienia:

| Opcja          | Wartość                                   |
|----------------|--------------------------------------------|
| SDK            | temurin-21 (Eclipse Temurin 21.0.x)        |
| Language Level | SDK default                                |

Następnie przejdź do:

**Run → Edit Configurations**

Upewnij się, że opcja build/run korzysta z **Java 21**.

---

## Uruchamianie serwera

W katalogu projektu wykonaj:

```bash
./gradlew run
```
