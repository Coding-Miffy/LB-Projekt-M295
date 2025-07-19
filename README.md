# Projektdokumentation
**Modul**: 295   
**Autor:in**: Natascha Blumer  
**Datum**:  
**Version**:  

## Einleitung
[Hier kommt Einleitung]

## Projektidee
Ich entwickle ein Backend für eine bestehende React-Frontend-Applikation (Modul 294), mit der Nutzer:innen aktuelle und vergangene Naturereignisse wie Waldbrände, Erdbeben oder Überschwemmungen entdecken und dokumentieren können.

Das Backend stellt eine REST-API zur Verfügung, über welche autorisierte Benutzer:innen (z. B. Forscher:innen oder Behörden) neue Naturereignisse erfassen, bestehende aktualisieren oder nicht mehr relevante Einträge löschen können. Zusätzlich werden Filter- und Statistik-Endpunkte angeboten, um Ereignisse nach Datum, Kategorie oder Status auszuwerten. Die Daten werden persistent in einer PostgreSQL-Datenbank gespeichert.

Ziel der Anwendung ist es, eine strukturierte, filterbare und leicht erweiterbare Datenbasis für globale Naturereignisse zu schaffen, die sich mit dem vorhandenen Frontend nahtlos kombinieren lässt. Dabei werden bewährte Technologien wie Spring Boot, Spring Data JPA, PostgreSQL und OpenAPI eingesetzt.

## Anforderungsanalyse
[Einleitung]

### User Stories
[Einleitung]

- **User Story 1**: [Hier kommt User Story 1](https://github.com/Coding-Miffy/LB-Projekt-M295/issues/1)
- **User Story 2**: [Hier kommt User Story 2](https://github.com/Coding-Miffy/LB-Projekt-M295/issues/2)
- **User Story 3**: [Hier kommt User Story 3](https://github.com/Coding-Miffy/LB-Projekt-M295/issues/3)

### Use Case
[Einleitung]
[Diagramm]

### Kernaufgaben
[Einleitung]

| ID | Funktionale Anforderungen |
| :-: | :-- |
| F1 |  |

## Diagramm der Modell-Komponenten
[WIP]

## REST-Schnittstellen
[WIP]

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
[Einleitung]

[Anleitung]

## Dokumentation
[Einleitung]

### Swagger
[Einleitung]  
[Link]

### JavaDoc
[Einleitung]  
[Link]

## Hilfestellungen
[Einleitung Hilfestellungen]

### ChatGPT
- Rechtschreibekorrektur und als Unterstützung beim Verfassen von Textabschnitten der Dokumentation.

@PastOrPresent bei EventDTO  
Ideen bei Tests  
Verfassen der JavaDoc Kommentare

---
>[!NOTE]
>**Swagger**: http://localhost:8080/swagger-ui/index.html

## To-Do:  
- Advanced Query Methods *5A*
- Swagger (Controller + DTO) *5B*
- Swagger Controller: /date
- JavaDoc
- Service: Date Validierung?
- Service (und vlt mehr): Longitude/Latitude Validierung
- Sicherstellen: Alles zu Date komplett?
- Stehen geblieben bei *6A*: Service anpassen
- Frontend: Englisch
- Backend: Deutsch
- Controller/Swagger: API und Responses testen
- 500er wenn daten unvollständig

## JavaDoc abgeschlossen:
Verzeichnis 'exception': **komplett**  
- GlobalExceptionHandler
- CoordinateOutOfRangeException
- EventNotFoundException
- FutureDateException
- InvalidEventDataException
- package info

Verzeichnis 'config': **komplett**  
- SwaggerConfig
- WebConfig
- package info

Verzeichnis 'entity': **komplett**  
- Event
- package info

Verzeichnis 'model': **komplett**  
- EventCategory
- EventStatus
- package info

Verzeichnis 'dto': **komplett**  
- ErrorResponseDTO
- EventDTO
- EventFormDTO
- package info

Verzeichnis 'mapper': **komplett**  
- EventMapper
- package info

Verzeichnis 'controller':

Verzeichnis 'repository':

Verzeichnis 'service':

Verzeichnis 'com.wiss.backend' (inkl. Application):

test: **komplett** 
- EventControllerTest
- EventRepositoryTest
- EventServiceTest

