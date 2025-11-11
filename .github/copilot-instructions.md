# GitHub Copilot Instructions - ISBNValidator

## Projektübersicht

**ISBNValidator** ist ein TDD-Lernprojekt in Java zur Validierung von 10-stelligen ISBN-Nummern. Es dient als praktische Demonstration von Test-Driven Development Prinzipien.

### Struktur
- `ISBNValidator/src/com/udemy/tdd/ISBNValidator.java` - Hauptimplementierung (stateless Utility-Klasse)
- `ISBNValidator/test/com/udemy/tdd/ISBNValidatorTest.java` - JUnit 4 Tests
- `ISBNValidator/bin/` - Kompilierte Klassen

## Kritische Patterns

### ISBN-10 Validierungsalgorithmus

Die Kernlogik in `checkISBN()`:
```java
// 1. Länge validieren (genau 10 Zeichen)
// 2. Für jeden Character i (0-9):
//    - Char 0-8: muss Ziffer sein → value * (10-i) addieren
//    - Char 9: kann Ziffer sein ODER 'X' (Wert 10)
// 3. Summe % 11 == 0 → gültig
```

**Wichtige Besonderheiten:**
- 'X' ist **nur** an Position 9 erlaubt
- `NumberFormatException` für ungültige Länge/Zeichen (nicht `IllegalArgumentException`)
- Methode gibt `true`/`false` zurück UND schreibt "passt"/"passt ned" zu stdout

### TDD-Ansatz erkennbar

Die Tests zeigen den iterativen Entwicklungsprozess:
- Positive Tests (gültige ISBNs) vor Exception-Tests
- Spezifische Szenarien isoliert testen (z.B. 'X'-Handling)
- `@Test(expected = NumberFormatException.class)` für Exception-Validierung

## Entwickler-Workflows

### Tests kompilieren und ausführen
```bash
cd ISBNValidator
# Kompilieren (benötigt junit-4.13.jar in lib/)
javac -d bin -cp lib/junit-4.13.jar \
  test/com/udemy/tdd/*.java src/com/udemy/tdd/*.java

# Tests ausführen
java -cp bin:lib/junit-4.13.jar \
  org.junit.runner.JUnitCore com.udemy.tdd.ISBNValidatorTest
```

### Hauptklasse ausführen
```bash
javac -d bin src/com/udemy/tdd/ISBNValidator.java
java -cp bin com.udemy.tdd.ISBNValidator
```

## Wichtige Conventions

1. **Stateless Design**: `ISBNValidator` hat keine Instanzvariablen; Methoden sind statisch
2. **Fehlerbehandlung**: `NumberFormatException` mit deutschen Fehlermeldungen werfen
3. **Console Output**: `System.out.println()` in `checkISBN()` ist beabsichtigt (zeigt Validierungsergebnis)
4. **Package-Namen**: `com.udemy.tdd` - vollständiger Qualified Name nutzen
5. **Test-Struktur**: Ein `@Test` pro Szenario; `ISBNValidator`-Instanz in jedem Test

## Integration & Abhängigkeiten

- **JUnit 4** - Testing Framework (junit-4.13.jar)
- **Keine externen Dependencies** für Produktionscode
- **Keine Dateisystem-Zugriffe** - nur In-Memory Validierung

## Häufige Aufgaben

### Neue Tests hinzufügen
- Pattern: `@Test public void testDescriptionHere() { ... }`
- Exception-Tests: `@Test(expected = NumberFormatException.class)`
- Assertions: `assertTrue()`, `assertFalse()` von JUnit

### Algorithmische Änderungen
- Änderungen in `checkISBN()` müssen alle 5 bestehenden Tests erfüllen
- Reihenfolge: Längenprüfung → Zeichenprüfung → Prüfsummenberechnung

### Dokumentation erweitern
- PlantUML Diagramme in `ISBNValidator/docs/` speichern
- Siehe `class_diagram.puml`, `sequence_diagram_test.puml`, `validation_flow.puml`

## Anti-Patterns zu vermeiden

- ❌ Nicht-deutscher Text in Fehlermeldungen (Projekt verwendet Deutsch)
- ❌ Neue Klassen/Methoden ohne entsprechende Tests
- ❌ Änderung der Exception-Typen ohne Test-Anpassung
- ❌ Hardcodierte Testdaten außerhalb von Testmethoden
