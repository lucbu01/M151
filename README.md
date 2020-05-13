# M151 Webshop
Webshop Projekt für das Modul 151

 - [Projektbeschrieb](#projektbeschrieb)
   - [Personengruppen](#personengruppen)
   - [User Stories](#user-stories)
   - [Technologien](#technologien)
 - [Installation](#installation)
 - [Curl-Beispiele](#curl-beispiele)

## Projektbeschrieb
In meinem Projekt im Modul 151 will ich einen Webshop für verschiedene Produkte entwickeln.

### Personengruppen

#### Besucher
Alle Besucher der Webseite sollen die Produkte angezeigt bekommen. Haben sie einen Account, können sie sich anmelden, um die Funktionen eines Benutzers zu haben. Besucher, die keinen Account haben, können sich registrieren.

#### Benutzer
Eingeloggte Benutzer können alles, was ein Besucher auch kann und zusätzlich können sie auch noch Produkte in den Warenkorb legen können und sich diesen anzeigen lassen. Sind sie mit der Wahl zufrieden, können sie die Produkte bestellen.

#### Administrator
Benutzer mit der Rolle ADMIN können die Produkte anpassen, löschen und neue erstellen. Ein Administrator kann auch den Status einer Bestellung ändern (BESTELLT, VERSCHICKT).


### User Stories

#### Produkte anschauen
Als Besucher des Webshops will ich alle Produkte, die der Webshop aktuell anbietet, angezeigt haben.

#### Registrieren
Als Besucher des Webshops habe ich mich für ein Produkt entschieden, das ich kaufen will. Dafür will ich mir ein neues Benutzerkonto anlegen.

#### Anmelden
Als Besucher des Webshops habe ich mich für ein Produkt entschieden, das ich kaufen will. Dafür will ich mich mit meinem bestehenden Benutzerkonto anmelden.

#### Warenkorb
Als Benutzer des Webshops möchte ich Produkte in den Warenkorb legen können und mir diesen anzeigen lassen.

#### Bestellen
Als Benutzer des Webshops möchte ich die Produkte, die ich im Warenkorb habe, bestellen. Diese werden dann aus dem Warenkorb gelöscht und in "Meine Bestellungen" angezeigt.

#### Produkte hinzufügen
Als Administrator möchte ich neue Produkte hinzufügen können.

#### Produkte bearbeiten
Als Administrator möchte ich die Produkte, die bereits existieren, bearbeiten können.

#### Produkte löschen
Als Administrator möchte ich Produkte aus dem Sortiment entfernen können.

#### Bestellstatus ändern
Als Administrator möchte ich den Status einer Bestellung auf VERSCHICKT stellen, wenn ich die Produkte verschickt habe.


### Technologien
 - Spring Boot
 - Postrgresql
 - Angular



## Installation
~ = repository root folder

### Datenbank
Ordner: ~
```
sudo docker-compose up
```

### Backend
Ordner: ~/backend
```
./gradlew bootRun
```

### Frontend
Ordner: ~/frontend

```
npm install
npm run startLocalhost
```



## Curl-Beispiele
Backend muss hochgefahren und mit der Datenbank verbunden sein

### User erstellen
E-Mail Adresse darf noch nicht verwendet worden sein
```
curl -d '{"email": "lucbu01@bluewin.ch","password": "12345678","firstName": "Luca","lastName": "Bucher","street":"Scheid 1","postalCode":"6026","city":"Rain"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/user/create
```

### User-Info abfragen
User muss schon erstellt worden sein mit entsprechendem Passwort
```
curl -u lucbu01@bluewin.ch:12345678 http://localhost:8080/api/user/info
```
