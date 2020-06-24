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

```sh
sudo docker-compose up
```

### Backend

Ordner: ~/backend

```sh
./gradlew bootRun
```

Backend läuft auf `http://localhost:8080`

### Frontend

Ordner: ~/frontend

```sh
npm install
npm run start:localhost
```

Frontend läuft auf `http://localhost:4200`

## Curl-Beispiele

- Backend muss hochgefahren und mit der Datenbank verbunden sein

### User erstellen

- E-Mail Adresse darf noch nicht verwendet worden sein

```sh
curl -X POST -H 'Content-Type: application/json' -d '{
   "email": "lucbu01@bluewin.ch",
   "password": "12345678",
   "firstName": "Luca",
   "lastName": "Bucher",
   "street":"Scheid 1",
   "postalCode":"6026",
   "city":"Rain"
  }' http://localhost:8080/api/user/create
```

### User-Info abfragen

- User muss schon erstellt worden sein mit entsprechendem Passwort

```sh
curl -u lucbu01@bluewin.ch:12345678 http://localhost:8080/api/user/info
```

### Neues Produkt erstellen

- User muss schon erstellt worden sein mit entsprechenden Passwort
- Die Rolle des Users muss auf der Datenbank auf ADMIN (1) geändert worden sein

```sh
curl -u admin@admin.ch:12345678 -X POST -H 'Content-Type: application/json' -d '{
 "name": "Samsung Galaxy S20",
 "description": "Das neuste",
 "price": "799.90"
}' http://localhost:8080/api/product/create
```

### Produkte auflisten

- Beschreibung wird nicht angezeigt

```sh
curl localhost:8080/api/product/list
```

### Produkt ansehen

- Produkt muss schon erstellt worden sein

```sh
curl localhost:8080/api/product/get/1
```

### Produkt ändern

- Produkt muss schon erstellt worden sein
- User muss schon erstellt worden sein mit entsprechenden Passwort
- Die Rolle des Users muss auf der Datenbank auf ADMIN (1) geändert worden sein

```sh
curl -u admin@admin.ch:12345678 -X PATCH -H 'Content-Type: application/json' -d '{
 "description": "Das ist die neue und ausführlichere Beschreibung für das Produkt 1"
}' http://localhost:8080/api/product/update/1
```

### Produkt löschen

- Produkt muss schon erstellt worden sein
- User muss schon erstellt worden sein mit entsprechenden Passwort
- Die Rolle des Users muss auf der Datenbank auf ADMIN (1) geändert worden sein

```sh
curl -u admin@admin.ch:12345678 -X DELETE  http://localhost:8080/api/product/delete/1
```

### Produkt in Warenkorb legen

- Produkt muss schon erstellt worden sein
- User muss schon erstellt worden sein mit entsprechenden Passwort

```sh
curl -u lucbu01@bluewin.ch:12345678 -X PUT http://localhost:8080/api/cart/add/1
```

### Warenkorb anzeigen

- User muss schon erstellt worden sein mit entsprechenden Passwort

```sh
curl -u lucbu01@bluewin.ch:12345678 http://localhost:8080/api/cart/get
```

### Produkt aus Warenkorb entfernen

- Produkt muss schon erstellt worden sein
- User muss schon erstellt worden sein mit entsprechenden Passwort

```sh
curl -u lucbu01@bluewin.ch:12345678 -X DELETE http://localhost:8080/api/cart/remove/1
```

### Produkt Anzahl im Warenkorb setzen

- Produkt muss schon erstellt worden sein
- User muss schon erstellt worden sein mit entsprechenden Passwort

```sh
curl -u lucbu01@bluewin.ch:12345678 -X PUT http://localhost:8080/api/cart/set/1/10
```

### Ganze Position eines Produktes aus dem Warenkorb entfernen

- Produkt muss schon erstellt worden sein
- User muss schon erstellt worden sein mit entsprechenden Passwort

```sh
curl -u lucbu01@bluewin.ch:12345678 -X DELETE http://localhost:8080/api/cart/remove/1/all
```

### Bestellvorschau

- Warenkorb enthält mindestens eine Position

Bevor eine Bestellung definitiv bestellt werden kann, muss man sich zuerst eine Bestellvorschau anzeigen lassen. Diese Abfrage speichert die Bestellung mit den aktuellsten Preisen und zeigt sie an. Die Bestellung hat aber noch nicht den Status "BESTELLT". Die Bestellvorschau mit den gespeicherten Preisen ist ab dem Zeitpunkt 10 Minuten gültig. Das heisst, sie muss innert 10 Minuten bestellt werden.

```sh
curl -u lucbu01@bluewin.ch:12345678 http://localhost:8080/api/checkout/preview
```

### Bestellvorschau abbrechen

Ist man mit der Bestellung noch nicht ganz zufrieden, kann man sie abbrechen, wenn sie noch den Status "VORSCHAU" hat.

- Bestellvorschau muss gemacht worden sein

```sh
curl -u lucbu01@bluewin.ch:12345678 -X DELETE http://localhost:8080/api/checkout/cancel
```

### Bestellung abschliessen

Hat man die Bestellvorschau gesehen und ist zufrieden, kann man die Bestellung abschliessen.

- Bestellvorschau muss vor weniger als 10 Minuten gemacht worden sein

```sh
curl -u lucbu01@bluewin.ch:12345678 -X POST http://localhost:8080/api/checkout/order
```

### Offene Bestellungen anzeigen

```sh
curl -u lucbu01@bluewin.ch:12345678 http://localhost:8080/api/order/open
```

### Verschickte Bestellungen anzeigen

```sh
curl -u lucbu01@bluewin.ch:12345678 http://localhost:8080/api/order/sent
```

### Details einer Bestellung anzeigen

- Die Bestellung muss existieren
- Bestellung muss von dem User gemacht worden sein oder der User muss ein Admin sein

```sh
curl -u lucbu01@bluewin.ch:12345678 http://localhost:8080/api/order/details/1
```

### Alle offenen Bestellungen anzeigen

- User muss ein Admin sein

```sh
curl -u admin@admin.ch:12345678 http://localhost:8080/api/order/admin/open
```

### Bestellstatus auf "VERSCHICKT" ändern

- Die Bestellung muss existieren
- User muss ein Admin sein

```sh
curl -u admin@admin.ch:12345678 -X PUT http://localhost:8080/api/order/admin/sent/1
```
