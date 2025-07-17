# Projektdokumentation
**Modul**: 294   
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
| ID | Komponente / Datei | Testziel |
| :-: | :-- | :-- |
| T1 |  |  |

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

---
>[!NOTE]
>**Swagger**: http://localhost:8080/swagger-ui/index.html

## To-Do:  
- Advanced Query Methods *5A*
- Swagger (Controller + DTO) *5B*
- Swagger Controller: /date
- JavaDoc
- Service: Date Validierung?
- Sicherstellen: Alles zu Date komplett?
- Stehen geblieben bei *6A*: Service anpassen

## Notizen:
**Was muss die API können?**
- Anzahl N an open Events einer Kategorie mit Koordinaten (Filter: Anzahl N, Kategorie, open)
- Anzahl N an closed Events einer Kategorie mit Datum (Filter: Anzahl N, Kategorie, Start- und Enddatum, closed)
- Event erstellen
