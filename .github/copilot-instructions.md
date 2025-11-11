# GitHub Copilot Instructions - ISBNValidator

## 📖 Projektübersicht

**ISBNValidator** ist ein TDD-Lernprojekt in Java zur Validierung von 10-stelligen ISBN-Nummern. Es dient als praktische Demonstration von Test-Driven Development Prinzipien mit fokussierter Codebase.

**Basierte auf:** Java 21 LTS, JUnit 4, Eclipse IDE-basiertes Projekt

### Projektstruktur
```
ISBNValidator/
├── src/com/udemy/tdd/ISBNValidator.java      # Stateless Validator (eine Klasse, zwei Methoden)
├── test/com/udemy/tdd/ISBNValidatorTest.java # 5 Unit Tests
├── docs/                                      # PlantUML Diagramme
│   ├── class_diagram.puml
│   ├── sequence_diagram_test.puml
│   └── validation_flow.puml
├── bin/                                       # Kompilierte Klassen
├── .classpath, .project                       # Eclipse-Metadaten
└── .settings/                                 # IDE-Konfiguration
```

## 🎯 Kernkonzepte

### ISBN-10 Validierungsalgorithmus

In `checkISBN(String isbn)`:
1. **Längenkontrolle**: Exakt 10 Zeichen → `NumberFormatException` bei Abweichung
2. **Zeichenverarbeitung** (für Index i = 0..9):
   - Zeichen 0-8: Muss Ziffer sein, Beitrag: `digit_value × (10-i)`
   - Zeichen 9: Ziffer ODER 'X' (=10) erlaubt
3. **Prüfsumme**: `sum % 11 == 0` → validiert als `true`, andernfalls `false`

**Beobachtete Verhaltensweise:**
- `true`/`false` wird zurückgegeben **UND** "passt"/"passt ned" wird zu stdout geschrieben
- Fehlerbehandlung: `NumberFormatException` (nicht `IllegalArgumentException`)
- 'X' funktioniert **nur** an Position 9

### TDD-Struktur

Die Tests zeigen klassischen TDD-Iterationsprozess:
- **Positive Tests**: `checkAValidISBN()` mit zwei Testfällen
- **Edge Cases**: `ISBNNumberIsEndingInAXAreValid()` isoliert 'X'-Verhalten
- **Negative Tests**: `checkAInValidISBN()` (falsche Prüfsumme)
- **Exception-Tests**: `nineDigitISBNAreNotAllowed()`, `nonNumericISBNAreNotAllowed()`
- **Exception-Muster**: `@Test(expected = NumberFormatException.class)`

## 🔧 Kritische Entwickler-Workflows

### Kompilation und Testausführung

```bash
cd ISBNValidator

# Kompilieren (mit JUnit auf Classpath)
javac -d bin -cp lib/junit-4.13.jar \
  test/com/udemy/tdd/*.java src/com/udemy/tdd/*.java

# Tests ausführen
java -cp bin:lib/junit-4.13.jar \
  org.junit.runner.JUnitCore com.udemy.tdd.ISBNValidatorTest
```

### Hauptklasse direkt ausführen

```bash
javac -d bin src/com/udemy/tdd/ISBNValidator.java
java -cp bin com.udemy.tdd.ISBNValidator
```

**Hinweis**: Die `main()`-Methode wartet auf Benutzereingabe (`System.in.read()`).

## 📋 Projekt-Spezifische Conventions

| Aspekt | Konvention | Grund |
|--------|-----------|-------|
| **Design** | Stateless Utility (`ISBNValidator`) | Einfachheit, keine State-Verwaltung |
| **Exceptions** | `NumberFormatException` (nicht `IllegalArgumentException`) | Differenziert zwischen Parsing/Format-Fehler |
| **Fehlermeldungen** | Deutsche Texte ("ISBN muss 10 Zeichen haben") | Deutsches Lernprojekt |
| **Ausgabe** | `System.out.println()` in Validierungslogik | Beabsichtigte Rückmeldung an Benutzer |
| **Package** | `com.udemy.tdd` | Standard Udemy-Beispiel Pattern |
| **Test-Instanzen** | `new ISBNValidator()` in jedem Test | Explizite Sichtbarkeit des getesteten Objekts |

## 🚀 Häufige Aufgaben & Patterns

### Neue Tests hinzufügen
```java
@Test
public void testYourScenario() {
    ISBNValidator validator = new ISBNValidator();
    boolean result = validator.checkISBN("0123456789");
    assertTrue(result);
}

@Test(expected = NumberFormatException.class)
public void testInvalidCase() {
    ISBNValidator validator = new ISBNValidator();
    validator.checkISBN("invalid");
}
```

### Algorithmische Änderungen

- **Vor Änderungen**: Alle 5 Tests müssen grün sein
- **Änderungsreihenfolge**: Längenkontrolle → Zeichentyp → Checksum-Logik
- **Nach Änderungen**: Testen mit: `checkAValidISBN()`, `ISBNNumberIsEndingInAXAreValid()`, andere Tests

### Dokumentation (PlantUML)

Diagramme speichern in `ISBNValidator/docs/`:
- `class_diagram.puml` - Klassenstruktur
- `sequence_diagram_test.puml` - Test-Ausführungsfluss
- `validation_flow.puml` - Entscheidungslogik

## ❌ Anti-Patterns (vermeiden)

| Problem | Folge |
|---------|--------|
| Nicht-deutsche Fehlertext | Inkonsistent mit Projektsprache |
| Änderung zu `IllegalArgumentException` | Tests brechen |
| Statische Testdaten außerhalb von Tests | Schwer zu verstehen, welche Tests davon abhängen |
| Neue öffentliche Methoden ohne Tests | Nicht im TDD-Spirit |
| Entfernung von `System.out.println()` | Bricht Benutzer-Feedback-Mechanismus |

## 🔗 Zusammenfassung für KI-Agents

1. **Minimales Projekt**: Nur zwei Java-Klassen, fokussiert auf ISBN-Validierung
2. **Keine Abhängigkeiten außer JUnit**: Produktionscode hat null externe Dependencies
3. **Explizite Kompilation erforderlich**: Kein Maven/Gradle, manuelle `javac`-Aufrufe
4. **Stdout ist Feature**: `System.out.println()` ist beabsichtigte Benutzer-Rückmeldung
5. **German-centric**: Alle Fehlermeldungen auf Deutsch, sogar Variablennamen können Deutsch sein
