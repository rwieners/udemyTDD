# ISBNValidator - TDD Projektbeispiel

Ein einfaches Java-Projekt zur Validierung von 10-stelligen ISBN-Nummern, entwickelt nach Test-Driven Development (TDD) Prinzipien.

## 📋 Projektübersicht

Das Projekt demonstriert die grundlegenden Konzepte von Test-Driven Development:
- **Rote Phase**: Tests schreiben, die fehlschlagen
- **Grüne Phase**: Minimal Code schreiben, um Tests zu bestehen
- **Refactoring Phase**: Code verbessern ohne Tests zu brechen

### Projektstruktur
```
ISBNValidator/
├── src/
│   └── com/udemy/tdd/
│       └── ISBNValidator.java          # Hauptimplementierung
├── test/
│   └── com/udemy/tdd/
│       └── ISBNValidatorTest.java      # JUnit Tests
├── bin/                                 # Kompilierte Klassen
└── docs/
    ├── class_diagram.puml              # Klassendiagramm
    ├── sequence_diagram_test.puml      # Ablaufdiagramm
    └── validation_flow.puml            # Validierungslogik
```

## 🔍 Kernlogik: ISBN-10 Validierung

Die ISBN-10 Prüfziffer wird nach folgendem Algorithmus berechnet:

$$\text{Prüfsumme} = \sum_{i=0}^{9} \text{Ziffer}_i \times (10-i) \bmod 11$$

**Gültig** wenn: `Prüfsumme % 11 == 0`

### Besonderheiten
- Genau **10 Zeichen** erforderlich
- Zeichen 0-8 müssen **Ziffern** sein (0-9)
- Zeichen 9 kann eine **Ziffer** oder das Zeichen **'X'** (= 10) sein
- Bei ungültiger Länge oder nicht-numerischen Zeichen: `NumberFormatException`

## 📊 Diagramme

### Klassendiagramm
![Class Diagram](docs/class_diagram.png)

Zeigt die Struktur der `ISBNValidator`-Klasse und ihre Beziehung zu den Tests.

### Sequenzdiagramm (Test-Ausführung)
![Sequence Diagram](docs/sequence_diagram_test.png)

Illustriert den Ablauf beim Aufrufen von `checkISBN()` während eines Tests.

### Validierungsfluss
![Validation Flow](docs/validation_flow.png)

Detaillierter Ablauf der ISBN-Validierungslogik mit Entscheidungspunkten.

## 🧪 Tests

Die Test-Suite in `ISBNValidatorTest` deckt folgende Szenarien ab:

| Test | Beschreibung | Ergebnis |
|------|-------------|----------|
| `checkAValidISBN()` | Zwei gültige ISBNs testen | ✅ TRUE |
| `ISBNNumberIsEndingInAXAreValid()` | ISBN mit 'X' am Ende | ✅ TRUE |
| `checkAInValidISBN()` | Ungültige ISBN (falsche Prüfziffer) | ❌ FALSE |
| `nineDigitISBNAreNotAllowed()` | Weniger als 10 Zeichen | ❌ Exception |
| `nonNumericISBNAreNotAllowed()` | Nicht-numerische Zeichen | ❌ Exception |

### Tests ausführen
```bash
cd ISBNValidator
javac -d bin -cp lib/junit-4.13.jar test/com/udemy/tdd/*.java src/com/udemy/tdd/*.java
java -cp bin:lib/junit-4.13.jar org.junit.runner.JUnitCore com.udemy.tdd.ISBNValidatorTest
```

## 💻 Verwendung

### Programmatisch
```java
ISBNValidator validator = new ISBNValidator();

// Gültige ISBN
boolean valid = validator.checkISBN("0140449116");  // true

// Mit 'X'
valid = validator.checkISBN("012000030X");  // true

// Ungültige Länge
validator.checkISBN("123");  // throws NumberFormatException
```

### Kommandozeile
```bash
cd ISBNValidator
javac -d bin src/com/udemy/tdd/ISBNValidator.java
java -cp bin com.udemy.tdd.ISBNValidator
```

## 🏗️ Architektur

Die `ISBNValidator`-Klasse ist eine **stateless Utility-Klasse**:
- Statische Methoden (`checkISBN`, `main`)
- Keine Instanzvariablen
- Keine Abhängigkeiten zu anderen Klassen
- Einfach zu testen und zu verwenden

## 📌 Wichtige Erkenntnisse

1. **TDD-Workflow**: Tests zuerst → Implementierung → Refactoring
2. **Exception-Handling**: Ungültige Eingaben werfen aussagekräftige Exceptions
3. **Console Output**: `System.out.println()` wird während Validierung aufgerufen
4. **Input Validation**: Länge und Format werden vor Verarbeitung geprüft

## 🔗 Referenzen

- [ISBN-10 Verifikationsalgorithmus](https://en.wikipedia.org/wiki/International_Standard_Book_Number#Check_digits)
- [JUnit 4 Dokumentation](https://junit.org/junit4/)
- [Test-Driven Development](https://en.wikipedia.org/wiki/Test-driven_development)

## 📝 Lizenz

Udemy TDD Kursbeispiel
