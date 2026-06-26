# Esercizio Docker e Git: Webapp multi-container con GUI versionata

## Obiettivo dell'esercizio

L'obiettivo è realizzare una semplice webapp eseguita tramite Docker Compose, composta da più container che comunicano tra loro.

L'esercizio deve inoltre dimostrare l'utilizzo di Git e GitHub per storicizzare le modifiche apportate ai file di personalizzazione della GUI.

Al termine dell'esercizio dovrà essere disponibile un repository GitHub contenente il progetto completo e la cronologia delle modifiche effettuate.

---

## Requisiti funzionali

La webapp deve rispettare i seguenti requisiti:

1. deve essere avviabile tramite Docker Compose;
2. deve essere composta da almeno due container, preferibilmente tre;
3. i container devono comunicare tra loro tramite la rete interna di Docker Compose;
4. almeno un container deve utilizzare una cartella volumizzata;
5. la cartella volumizzata deve contenere file utili alla personalizzazione della GUI;
6. modificando i file della cartella volumizzata, la GUI deve cambiare senza dover ricostruire completamente l'immagine Docker;
7. la cartella contenente i file della GUI deve essere gestita tramite Git;
8. le modifiche alla GUI devono essere sincronizzate su GitHub tramite commit e push.

---

## Scenario proposto

Realizzare una piccola webapp composta da:

- un container frontend, ad esempio basato su Nginx;
- un container backend, ad esempio basato su Flask, Node.js, FastAPI o tecnologia equivalente;
- un eventuale container database/cache, ad esempio Redis, PostgreSQL, MySQL o simile.

Il frontend deve esporre una GUI raggiungibile da browser.

Il backend deve esporre almeno una API.

Il frontend deve interrogare il backend.

Il backend deve, a sua volta, comunicare con il terzo container, se presente.

Esempio di architettura accettata:

```text
Browser
  |
  | http://localhost:8080
  |
frontend
  |
  | chiamata HTTP interna
  |
backend
  |
  | connessione interna
  |
redis / database / servizio di supporto
```

---

## Cartella volumizzata

Il progetto deve contenere una cartella dedicata alla GUI, ad esempio:

```text
gui/
```

Questa cartella deve contenere file come:

```text
gui/
├── index.html
├── style.css
└── app.js
```

La cartella deve essere montata all'interno del container frontend tramite volume Docker.

Esempio concettuale:

```yaml
volumes:
  - ./gui:/percorso/interno/del/container
```

In questo modo, modificando localmente i file della cartella `gui`, la modifica sarà immediatamente disponibile nel container.

---

## Requisiti Git e GitHub

Il progetto deve essere inizializzato come repository Git.

Devono essere presenti almeno due commit:

1. un primo commit con la versione iniziale del progetto;
2. un secondo commit contenente una modifica grafica alla GUI.

Il repository locale deve essere collegato a un repository remoto GitHub.

Le modifiche devono essere inviate su GitHub tramite `git push`.

---

## Comandi Git minimi richiesti

Durante lo svolgimento dell'esercizio devono essere utilizzati almeno i seguenti comandi:

```bash
git init
```

```bash
git status
```

```bash
git add .
```

```bash
git commit -m "Prima versione webapp docker"
```

```bash
git remote add origin <URL_REPOSITORY_GITHUB>
```

```bash
git branch -M main
```

```bash
git push -u origin main
```

Dopo una modifica alla GUI:

```bash
git status
```

```bash
git add gui/
```

```bash
git commit -m "Personalizzazione grafica GUI"
```

```bash
git push
```

---

## Comandi Docker minimi richiesti

Il progetto deve essere avviabile con:

```bash
docker compose up -d --build
```

Deve essere possibile verificare lo stato dei container con:

```bash
docker compose ps
```

Deve essere possibile visualizzare i log con:

```bash
docker compose logs -f
```

Per arrestare l'ambiente:

```bash
docker compose down
```

---

## Struttura minima suggerita del progetto

La struttura può essere simile alla seguente:

```text
docker-git-webapp/
├── docker-compose.yml
├── frontend-config/
│   └── configurazione del web server
├── backend/
│   ├── Dockerfile
│   └── sorgenti backend
└── gui/
    ├── index.html
    ├── style.css
    └── app.js
```

Non è obbligatorio usare esattamente questi nomi, ma il progetto deve essere chiaro e ordinato.

---

## Comportamento atteso della webapp

La webapp deve permettere di verificare visivamente che i container stanno comunicando.

Un esempio semplice può essere:

1. l'utente apre la GUI da browser;
2. la GUI mostra un pulsante;
3. premendo il pulsante, il frontend effettua una chiamata al backend;
4. il backend risponde con un messaggio JSON;
5. il backend aggiorna o legge un dato da un altro container, ad esempio Redis;
6. la GUI mostra il risultato della chiamata.

Esempio di risposta attesa:

```json
{
  "message": "Backend raggiunto correttamente",
  "service": "Redis raggiunto correttamente",
  "counter": 1
}
```

---

## Personalizzazione della GUI richiesta

Dopo aver avviato correttamente la webapp, modificare almeno un file nella cartella della GUI.

Esempi di modifica accettati:

- cambiare il colore principale della pagina;
- modificare il titolo;
- aggiungere un testo descrittivo;
- modificare lo stile del pulsante;
- aggiungere un'immagine o un logo;
- cambiare il layout della pagina.

La modifica deve essere visibile ricaricando la pagina nel browser.

La modifica deve poi essere salvata in Git e inviata su GitHub.

---

## Verifiche richieste

Lo studente deve dimostrare che:

1. i container sono attivi;
2. il frontend è raggiungibile da browser;
3. il frontend comunica con il backend;
4. il backend comunica con il container di supporto, se previsto;
5. la cartella GUI è montata come volume;
6. le modifiche alla GUI sono visibili senza ricostruire l'immagine frontend;
7. le modifiche sono presenti nella cronologia Git;
8. il repository GitHub contiene il progetto aggiornato.

---

## Output da consegnare

Consegnare:

1. link al repository GitHub;
2. screenshot della GUI aperta nel browser;
3. screenshot della risposta del backend visualizzata nella GUI;
4. screenshot o output testuale di:

```bash
docker compose ps
```

5. screenshot o output testuale di:

```bash
git log --oneline
```

6. breve descrizione della modifica grafica effettuata.

---

## Criteri di valutazione

| Criterio | Descrizione |
|---|---|
| Docker Compose funzionante | Il progetto si avvia correttamente con `docker compose up -d --build` |
| Multi-container | Sono presenti almeno due container comunicanti |
| Comunicazione tra servizi | Il frontend comunica con il backend e il backend comunica con un altro servizio, se previsto |
| Volume Docker | La cartella GUI è montata nel container frontend |
| Personalizzazione GUI | Le modifiche ai file della GUI sono visibili nel browser |
| Git | Il progetto è versionato correttamente |
| GitHub | Il repository remoto contiene i commit richiesti |
| Chiarezza del progetto | La struttura delle cartelle e dei file è ordinata e comprensibile |

---

## Suggerimenti

Per rendere l'esercizio più semplice, è consigliato usare:

- Nginx per il frontend statico;
- Flask o Node.js per il backend;
- Redis come servizio di supporto per salvare un contatore.

Questa combinazione consente di dimostrare facilmente:

- la comunicazione frontend-backend;
- la comunicazione backend-Redis;
- l'uso dei volumi Docker;
- la modifica live della GUI;
- la gestione delle modifiche tramite Git e GitHub.
