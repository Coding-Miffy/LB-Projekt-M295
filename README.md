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
[Einleitung]

### Testfälle
| ID | Klasse | Testziel |
| :-: | :-- | :-- |
| T1 | `EventService` | Sicherstellen, dass `EventService.getAllEvents()` alle Events aus dem Repository korrekt abruft und zurückliefert |
| T2 | `EventService` | Sicherstellen, dass ein `Event` korrekt in ein `EventDTO` konvertiert wird |
| T3 | `EventRepository` | Sicherstellen, dass ein gespeichertes Event erfolgreich per `findById()` abgerufen werden kann |
| T4 | `EventRepository` | Sicherstellen, dass die Methode `findByCategory()` nur Events mit der gewünschten Kategorie zurückgibt |
| T5 | `EventController` | Sicherstellen, dass der Endpunkt `/api/events` eine vollständige Eventliste im JSON-Format zurückgibt |
| T6 | `EventController` | 	Sicherstellen, dass der Endpunkt `/api/events/status/{status}` korrekt filtert |

## Durchführung der Tests
[Einleitung]

### T1 - Name
[Durchführung des Tests]

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

