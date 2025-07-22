# Projektdokumentation
**Modul**: 295   
**Autor:in**: Natascha Blumer  
**Datum**: 22.07.2025  
**Version**: 1.0  

## Einleitung
[Hier kommt Einleitung]

## Projektidee
Ich entwickle ein Backend für eine bestehende React-Frontend-Applikation (*Earth Natural Events Tracker*, Modul 294), mit der Nutzer:innen aktuelle und vergangene Naturereignisse wie Waldbrände, Erdbeben oder Überschwemmungen entdecken und dokumentieren können.

Das Backend stellt eine REST-API zur Verfügung, über welche autorisierte Benutzer:innen (z. B. Forscher:innen oder Behörden) neue Naturereignisse erfassen, bestehende aktualisieren oder nicht mehr relevante Einträge löschen können. Zusätzlich werden Filter- und Statistik-Endpunkte angeboten, um Ereignisse nach Datum, Kategorie oder Status auszuwerten. Die Daten werden persistent in einer PostgreSQL-Datenbank gespeichert.

Ziel der Anwendung ist es, eine strukturierte, filterbare und leicht erweiterbare Datenbasis für globale Naturereignisse zu schaffen, die sich mit dem vorhandenen Frontend nahtlos kombinieren lässt. Dabei werden bewährte Technologien wie Spring Boot, Spring Data JPA, PostgreSQL und OpenAPI eingesetzt.

## Anforderungsanalyse
Im Rahmen dieser Anforderungsanalyse wurden die funktionalen Erwartungen an das Backend des *Earth Natural Events Trackers* systematisch erfasst. Die Anforderungen orientieren sich an den typischen Aufgaben von Nutzer:innen und wurden in Form von User Stories, einem Use-Case-Diagramm sowie konkreten funktionalen Anforderungen dokumentiert. Ziel ist es, die zentralen Interaktionen mit dem System nachvollziehbar und technisch umsetzbar zu beschreiben.

### User Stories
Die folgenden User Stories beschreiben typische Nutzungsszenarien aus Sicht der Anwender:innen. Sie helfen, die fachlichen Anforderungen verständlich zu formulieren und den Fokus auf die Nutzerbedürfnisse zu legen.

- **User Story 1**: [Naturereignisse erfassen](https://github.com/Coding-Miffy/LB-Projekt-M295/issues/1)
- **User Story 2**: [Naturereignisse filtern](https://github.com/Coding-Miffy/LB-Projekt-M295/issues/2)
- **User Story 3**: [Naturereignisse bearbeiten oder löschen](https://github.com/Coding-Miffy/LB-Projekt-M295/issues/3)

### Use Case
Das folgende Use-Case-Diagramm veranschaulicht die Interaktion verschiedener Benutzerrollen mit der EONET REST API.
Zwei Akteur:innen – `Forscher:in` und `Benutzer:in` – führen jeweils spezifische Aktionen durch. Gemeinsam genutzte Teilfunktionalitäten und Funktionalitäten mit Abhängigkeiten (z. B. Eingabevalidierung oder Filterung) sind als `<<include>>`-Beziehungen dargestellt.

![Use Case Diagramm](/resources/images/usecase-diagram.jpg)

### Kernaufgaben
Die nachfolgende Liste dokumentiert die zentralen funktionalen Anforderungen der Anwendung. Diese Anforderungen definieren, welche konkreten Fähigkeiten das System bieten muss, um die in den User Stories beschriebenen Anwendungsfälle abzudecken.

| ID | Funktionale Anforderungen |
| :-: | :-- |
| F1 | Die Anwendung soll es ermöglichen, ein Naturereignis mit Titel, Datum, Kategorie, Koordinaten und Status zu erfassen. |
| F2 | Die Anwendung soll alle gespeicherten Ereignisse abrufbar machen. |
| F3 | Die Anwendung soll es erlauben, Ereignisse nach Status, Kategorie und Zeitraum zu filtern |
| F4 | Die Anwendung soll einzelne Ereignisse anhand ihrer ID abrufen. |
| F5 | Die Anwendung soll es ermöglichen, ein bestehendes Ereignis zu bearbeiten. |
| F6 | Die Anwendung soll es ermöglichen, ein Ereignis zu löschen. |
| F7 | Die Anwendung soll validieren, dass alle Pflichtfelder korrekt ausgefüllt sind. |
| F8 | Die REST-API soll im JSON-Format kommunizieren. |

## Klassendiagramm der Backend-Komponenten
Das folgende Klassendiagramm veranschaulicht den strukturellen Aufbau der entwickelten Applikation sowie die Beziehungen zwischen den zentralen Komponenten. Es bildet die wichtigsten Klassen, Enums, Interfaces und benutzerdefinierten Exceptions des Projekts ab und zeigt deren Zusammenspiel in der Backend-Architektur.

Im Mittelpunkt steht der `EventService`, der als zentrale Geschäftslogik fungiert. Er greift auf das `EventRepository` zur Datenpersistenz zu und verwendet den `EventMapper` zur Konvertierung zwischen den Entitäten (`Event`) und den Datenübertragungsobjekten (`EventDTO`, `EventFormDTO`). Die REST-Schnittstelle wird über den `EventController` bereitgestellt, der eingehende Anfragen entgegennimmt und an den Service weiterleitet.

![Klassendiagramm](/resources/images/class-diagram.jpg)

Zusätzlich zeigt das Diagramm den globalen Exception-Handler (`GlobalExceptionHandler`), der alle anwendungsweiten Fehlerfälle behandelt und einheitliche Fehlermeldungen in Form von `ErrorResponseDTO` zurückliefert. Eigene Exceptionklassen wie `EventNotFoundException` oder `InvalidEventDataException` dienen zur gezielten Fehlerdifferenzierung.

Zur besseren Lesbarkeit wurden alle Abhängigkeiten als «uses»-Beziehungen gekennzeichnet. Die Pfeile geben jeweils an, welche Klasse eine andere verwendet. Die Enums `EventStatus` und `EventCategory` werden sowohl in den Entitäten als auch in der Business-Logik verwendet.

Das Diagramm bietet damit eine übersichtliche Darstellung der Klassenstruktur und erleichtert das Verständnis für den Aufbau und die Verantwortlichkeiten innerhalb der Anwendung.

## REST-Schnittstellen
Die entwickelte Applikation stellt eine REST-API zur Verfügung, über die alle relevanten Operationen auf Naturereignissen durchgeführt werden können. Diese Schnittstellen bilden die zentrale Interaktionsebene für das Frontend sowie potenzielle Drittanwendungen.

### Aufbau
Die REST-Endpunkte sind im `EventController` definiert und folgen weitestgehend den Konventionen für Ressourcen-orientierte APIs. Alle Anfragen und Antworten verwenden das JSON-Format, und HTTP-Statuscodes werden konsistent zur Rückmeldung über den Erfolg oder Fehler einer Operation eingesetzt.

### Unterstützte Endpunkte
| Methode | Pfad | Beschreibung |
| :-: | :-- | :-- |
| `GET` | `/api/events` | Listet alle gespeicherten Naturereignisse auf |
| `GET` | `/api/events/{id}` | Gibt ein spezifisches Naturereigniss anhand der ID zurück |
| `GET` | `/api/events/categories/{category}` | Listet alle Naturereignisse einer Kategorie auf |
| `GET` | `/api/events/status/{status}` | Listet alle Naturereignisse eines Status auf |
| `GET` | `/api/events/date/{date}` | Listet alle Naturereignisse eines Datums auf |
| `GET` | `/api/events/filter` | Filtert Naturereignisse nach Kombination von Kategorie, Status und Zeitraum |
| `GET` | `/api/events/count` | Gibt die Gesamtzahl aller gespeicherter Naturereignisse zurück |
| `GET` | `/api/events/stats/categories/{category}` | Gibt die Gesamtzahl aller Naturereignisse einer Kategorie zurück |
| `GET` | `/api/events/stats/status/{status}` | Gibt die Gesamtzahl aller Naturereignisse eines Status zurück |
| `GET` | `/api/events/stats/date/{start}/{end}` | Gibt die Gesamtzahl aller Naturereignisse eines Datumsbereichs zurück |
| `POST` | `/api/events` | Speichert ein neues Naturereignis |
| `PUT` | `/api/events/{id}` | Aktualisiert ein bestehendes Naturereignis anhand der ID |
| `DELETE` | `/api/events/{id}` | Löscht ein bestehendes Naturereignis anhand der ID |
| `GET` | `/api/events/all` | Listet alle gespeicherten Naturereignisse als Formulardaten auf |
| `GET` | `/api/events/{id}/edit` | Gibt ein spezifisches Naturereigniss anhand der ID in Formulardaten zurück |
| `POST` | `/api/events/create` | Speichert ein neues Naturereignis in Formulardaten |
| `PUT` | `/api/events/{id}/update` | Aktualisiert ein bestehendes Naturereignis anhand der ID in Formulardaten |

### Besonderheiten
- **Validierung**: Eingehende Daten werden mithilfe von Bean Validation überprüft. Ungültige Eingaben führen zu spezifischen Fehlermeldungen.

- **Fehlerbehandlung**: Über den zentralen `GlobalExceptionHandler` werden auftretende Fehler in konsistente Fehlerobjekte (`ErrorResponseDTO`) überführt.

- **CORS-Konfiguration**: Um Cross-Origin-Anfragen vom Frontend (z. B. `http://localhost:3000`) zu ermöglichen, wurde die API gezielt für bestimmte Ursprünge freigegeben. Dies geschieht in der `WebConfig`-Klasse, welche `WebMvcConfigurer` implementiert und die Methode `addCorsMappings()` überschreibt, um gezielt Pfade freizuschalten.

- **Kombination von REST und Formularlogik**: Die API folgt weitgehend RESTful-Prinzipien, bietet jedoch zusätzlich alternative Endpunkte wie `/create`, `/update` oder `/edit` an. Diese orientieren sich an Formulardaten-Workflows und erleichtern die Integration in UI-orientierte Anwendungen. Durch diese Hybridstruktur wird eine flexible Anbindung unterschiedlicher Frontends ermöglicht, sowohl für klassische REST-Clients als auch für formularbasierte Web-Oberflächen.

### Beispiel für eine Anfrage
>**POST**: `/api/events`

Erstellt ein neues Naturereignis basierend auf den übergebenen Daten. Die Anfrage erwartet ein JSON-Objekt basierend auf dem `EventDTO`.

**Request-Body (JSON)**:
```json
{
  "title": "Vulkanausbruch Island",
  "date": "2025-07-01",
  "latitude": 64.9631,
  "longitude": -19.0208,
  "status": "open",
  "category": "volcanoes"
}
```

## Testplan
Dieser Testplan dokumentiert die systematische Überprüfung der Geschäftslogik und API-Funktionalität der entwickelten Spring-Boot-Anwendung. Ziel war es, die wichtigsten Komponenten mit geeigneten Testverfahren abzusichern.

### Testfälle
| ID | Name | Klasse | Testziel | Status |
| :-: | :-- | :-- | :-- | :-: |
| T1 | Abruf aller Events | `EventService` | Sicherstellen, dass `EventService.getAllEvents()` alle Events aus dem Repository korrekt abruft und zurückliefert | ✅ |
| T2 | DTO-Konvertierung | `EventService` | Sicherstellen, dass ein `Event` korrekt in ein `EventDTO` konvertiert wird | ✅ |
| T3 | Event speichern und finden | `EventRepository` | Sicherstellen, dass ein gespeichertes Event erfolgreich per `findById()` abgerufen werden kann | ✅ |
| T4 | Events nach Kategorie | `EventRepository` | Sicherstellen, dass die Methode `findByCategory()` nur Events mit der gewünschten Kategorie zurückgibt | ✅ |
| T5 | REST-Antwort | `EventController` | Sicherstellen, dass der Endpunkt `/api/events` eine vollständige Eventliste im JSON-Format zurückgibt | ✅ |
| T6 | REST-Antwort | `EventController` | 	Sicherstellen, dass der Endpunkt `/api/events/status/{status}` korrekt filtert | ✅ |

### Vorgehen
Die Testfälle wurden in drei Kategorien unterteilt:

- **Unit-Tests** (*EventService*): Mit `@ExtendWith(MockitoExtension.class)` und `@Mock` wurde der Service isoliert vom Repository getestet.

- **Integrationstests mit Datenbank** (*EventRepository*): Mit `@DataJpaTest` und einer H2-In-Memory-Datenbank wurden Repository-Methoden realitätsnah geprüft.

- **Controller-Tests mit MockMvc** (*EventController*): Mit `@WebMvcTest` und `MockMvc` wurden HTTP-Requests simuliert und das Verhalten der REST-Endpunkte getestet.

### Testumgebung
- **Test-Framework**: JUnit 5
- **Mocking**: Mockito
- **Datenbank**: H2 (In-Memory)
- **HTTP-Simulation**: Spring `MockMvc`
- **Testprofil**: `test`-Profil mit isolierter Konfiguration

## Durchführung der Tests
Zur Sicherstellung der Funktionalität der REST-API wurden insgesamt **6 Testfälle** umgesetzt, die sowohl die **Service-**, **Repository-** als auch **Controller-Schicht** abdecken. Die Tests wurden mit JUnit 5, Mockito sowie Spring Boot Test durchgeführt.  
Alle Tests wurden automatisiert durchgeführt und vollständig bestanden.

### T1 - Abruf aller Events
>**Methode**: `EventService.getAllEvents()`

Die Methode `getAllEvents()` wurde mit einem gemockten Repository getestet. Dieses lieferte zwei vordefinierte Event-Objekte zurück. Anschliessend wurde überprüft, ob die zurückgegebene Liste korrekt ist und ob `findAll()` wie erwartet aufgerufen wurde.  

### T2 - DTO-Konvertierung
>**Methode**: `EventService.getAllEventsAsDTO()`

Ein einzelnes Event mit definierten Feldern wurde vom Mock-Repository zurückgegeben. Der Test prüfte, ob die Felder korrekt ins DTO übertragen wurden (z. B. Titel, Datum, Kategorie, Koordinaten, Status).

### T3 - Event speichern und finden
>**Methode**: `EventRepository.findById()`

Ein neues Event wurde mit dem `TestEntityManager` persistiert. Danach wurde mit `findById()` versucht, das Event zu laden. Der Test verifizierte, dass das Event existiert und die erwarteten Felder korrekt gespeichert wurden.

### T4 - Events nach Kategorie
>**Methode**: `EventRepository.findByCategory()`

Drei Events mit unterschiedlichen Kategorien wurden in die Datenbank geschrieben. Anschliessend wurde die Methode `findByCategory()` mit dem Wert `wildfires` getestet. Der Test prüfte, ob genau zwei passende Events zurückgegeben wurden.

### T5 - REST-Antwort
>**Methode**: `EventController.getAllEvents()`

Über `MockMvc` wurde ein HTTP-GET-Request an `/api/events` simuliert. Der Mock-Service lieferte zwei Events zurück. Der Test prüfte, ob die JSON-Antwort korrekt strukturiert war und den Titel des ersten Events korrekt enthielt.

### T6 - REST-Antwort
>**Methode**: `EventController.getEventsByStatus()`

Ein GET-Request an `/api/events/status/closed` wurde mit `MockMvc` simuliert. Der Mock-Service lieferte ein einzelnes geschlossenes Event zurück. Der Test prüfte, ob dieses Event korrekt im JSON-Response enthalten war und den erwarteten Status hatte.

## Installationsanleitung
In den folgenden Abschnitten wird beschrieben, wie das Backend des *Earth Natural Events Tracker* installiert und gestartet werden kann.  
Zusätzlich wird erklärt, wie das React-Frontend lokal ausgeführt und mit der REST-API verbunden werden kann.

### Voraussetzungen
Für die Ausführung werden folgende Komponenten benötigt:

**Backend**:
- **Java Development Kit (JDK) 17** oder neuer
- **Maven**
- **Git** (zum Klonen des Repositories)
- **PostgreSQL-Datenbank** (lokal oder gehostet, z. B. DBeaver)

**Optional für die Frontend-Integration**:
- **Node.js** (empfohlene LTS-Version)
- **npm** (wird automatisch mit Node.js installiert)

### Projekt vorbereiten
**1. Projektverzeichnis entpacken oder aus dem Repository klonen**:

```bash
git clone https://github.com/Coding-Miffy/LB-Projekt-M295.git
```

**2. In das Projektverzeichnis wechseln**:

```bash
cd LB-Projekt-M295
```

### Datenbank vorbereiten
- PostgreSQL-Instanz starten (z. B. auf DBeaver)
- Neue Datenbank anlegen
- Benutzer:in mit passenden Rechten anlegen

### Datenbank-Zugangsdaten eintragen
Die Zugangsdaten zur Datenbank müssen entweder:

- in der Datei `src/main/resources/application.properties` angepasst werden, **oder**

- als Umgebungsvariablen übergeben werden


### Backend starten
Die Anwendung kann mit folgendem Befehl gestartet werden:

```bash
mvn spring-boot:run
```

>[!NOTE]
>Unter `src/main/resources/data/data.sql` befinden sich einige Beispiel-Events, die beim Start in die Datenbank eingefügt werden, **sofern** die Tabelle beim Start leer ist.

### Optional: Frontend installieren und starten
**1. Ins Frontend-Verzeichnis wechseln**:

```bash
cd frontend
```

**2. Abhängigkeiten installieren**:

```bash
npm install
```

**3. Entwicklungsserver starten**:

```bash
npm start
```

Die Anwendung ist anschliessend unter [http://localhost:5173](http://localhost:5173) erreichbar.

>[!NOTE]
>Die CORS-Konfiguration ist im Backend (`WebConfig.java`) entsprechend angepasst. Falls das Frontend auf einem anderen Port läuft, muss dieser in der Methode `addCorsMappings()` explizit freigegeben sein.

## Dokumentation
Die Applikation verfügt über zwei Arten technischer Dokumentation: **Swagger / OpenAPI** und **JavaDoc**. Beide werden nachfolgend kurz erläutert.

### Swagger / OpenAPI
Die REST-Schnittstellen des Backends sind mittels Swagger automatisch dokumentiert.  
Nach dem Start der Applikation ist die Dokumentation unter folgender URL aufrufbar:

```bash
http://localhost:8080/swagger-ui/index.html
```

Dort sind alle verfügbaren Endpunkte übersichtlich dargestellt, inklusive Parameter, Rückgabewerten und HTTP-Statuscodes. Diese Oberfläche kann zur Exploration und zum Testen der API verwendet werden.

### JavaDoc
Der Quellcode ist ausführlich mit JavaDoc-Kommentaren versehen. Diese Dokumentation beschreibt die wichtigsten Klassen, DTOs und Methoden inklusive ihrer Aufgaben, Parameter und Rückgabewerte.

Um die JavaDoc-Dokumentation lokal zu generieren, kann folgender Maven-Befehl verwendet werden:

```bash
mvn javadoc:javadoc
```

Die generierte HTML-Dokumentation befindet sich anschliessend in diesem Verzeichnis:

```bash
target/site/apidocs/index.html
```

Diese Datei kann im Browser geöffnet werden, um einen detaillierten Überblick über die Klassenstruktur und deren Beziehungen zu erhalten.

## Hilfestellungen
[Einleitung Hilfestellungen]

### ChatGPT
- Rechtschreibekorrektur und als Unterstützung beim Verfassen von Textabschnitten der Dokumentation.

@PastOrPresent bei EventDTO  
Ideen bei Tests  
Verfassen der JavaDoc Kommentare
Exception Handling für MethodArgumentTypeMismatchException und HttpMessageNotReadableException
Beim Erstellen des Klassendiagramms (Beziehungen erkennen)

### Graziano Laveder (Dozent M 295)

### SideQuests M 295
