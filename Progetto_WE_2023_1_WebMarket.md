# Corso di Web Engineering<br/>*Progetti A.A. 2023/2024* - Specifica 1

<section class="specifica">

## Progetto "WebMarket"
> Versione 1.0

### Premessa

I progetti di fine corso si ispirano sempre ad esigenze
reali, e fanno solitamente riferimento a tipologie di sito già presenti sulla
rete. Nello svolgere il progetto, gli studenti dovranno attenersi alla
specifica data in questo documento, ma potranno raffinarla tramite l'interazione
col docente e l'analisi di siti web analoghi. In ogni caso, la realizzazione
finale dovrà essere completamente originale. Le informazioni pubblicate
dovranno essere sempre ben organizzate ed accessibili, date le varie tipologie
di utenza associate alle applicazioni web pubbliche.  

### Specifiche del Sito

<section class="descrizione">



Il sito *WebMarket* rappresenta un sistema di acquisti online simile ai vari eCommerce che tutti ben conosciamo, ma *vincolato* e *guidato*, in quanto pensato per essere usato all'interno di un'organizzazione pubblica. 

Saranno previste due tipologie di utenza: *ordinanti* e *tecnici*. Nel sistema sarà previsto anche un *amministratore* col solo scopo di registrare altri utenti.

Il sistema funzionerà come segue (ovviamente semplificheremo molti passaggi per evitare di rendere il progetto troppo pesante, quindi alcuni punti saranno poco realistici... se volete siete liberi di renderli più completi e complessi!).

1. **Definizione della *richiesta di acquisto*.** L’ordinante sarà inizialmente guidato nella selezione di una  *categoria* (ad esempio *PC Desktop*, *Notebook*, *Scrivania*,... <b>Come modelliamo le entità? una entità per desktop, notebook, scriviania oppure una tabella per componente (RAM)</b>) che identifichi la tipologia di prodotto da acquistare (*opzionalmente le categorie potranno avere una struttura ad albero, ad esempio Informatica > Computer > Notebook*). Ogni categoria di prodotto avrà associate una serie di *caratteristiche* specifiche (ad esempio quantità di RAM e tipo di CPU per un PC, ecc.). L'ordinante dovrà quindi inserire i valori di tutte caratteristiche desiderate relative alla categoria di prodotto selezionata (per ogni caratteristica sarà comunque sempre prevista l'opzione *indifferente*). Sarà presente anche uno spazio *note* per aggiungere ogni caratteristica peculiare non annoverata tra quelle standard. 

2. **Definizione della *proposta di acquisto*.** La *richiesta di acquisto* definita al punto 1 sarà inoltrata al personale tecnico, che ne riceverà notifica. Un membro del personale prenderà quindi in carico la richiesta, diventando il *tecnico incaricato* della richiesta stessa. Il tecnico incaricato potrà visionare la categoria e le caratteristiche richieste dall'ordinante e cercherà (esternamente al sistema) un prodotto effettivo da ordinare (che chiameremo *proposta di acquisto*), associandone la descrizione (che comprenderà almeno nome produttore, nome prodotto, codice prodotto, prezzo, URL di approfondimento e un campo note) alla *richiesta di acquisto*.

3. **Revisione della proposta di acquisto.** L'ordinante, notificato dell'immissione da parte del tecnico incaricato della proposta relativa a una sua richiesta, ne prenderà visione e potrà *approvarla* o *respingerla*, fornendo in questo caso una motivazione. In caso di richiesta respinta, il processo riprenderà dal passo 2, considerando che il tecnico incaricato sarà sempre lo stesso. La motivazione del rifiuto sarà mostrata assieme alle informazioni della richiesta, e il tecnico incaricato potrà modificare il prodotto candidato e inoltrarlo nuovamente all'ordinante.

4. **Esecuzione dell'*ordine di acquisto*.** Nel caso in cui l'ordinante approvi la scelta del prodotto candidato al passo 3, questo verrà notificato al personale tecnico che procederà a definire il relativo *ordine di acquisto* (questa parte non sarà gestita dal nostro applicativo).

5. **Consegna e verifica dell'*ordine di acquisto***. Quando il prodotto sarà consegnato (prima o poi!), l'ordinante dovrà *chiudere* la relativa richiesta di acquisto indicando se il prodotto ricevuto è stato *accettato*, *respinto perché non conforme* oppure *respinto perché non funzionante*.


</section>


Di seguito sono illustrati schematicamente i contenuti e le
funzionalità minime che dovrebbero essere inseriti nel sito. Ovviamente, ogni
ulteriore raffinamento o arricchimento di queste specifiche aumenterà il valore
del progetto.

  <section class="operazioni">
  


- L'accesso all'applicazione sarà riservato solo a utenti registrati. Dovrà essere prevista la figura di un amministratore, il quale avrà la facoltà di registrare nel sistema nuovi ordinanti e nuovi tecnici.

- Dopo l'accesso, verrà visualizzata una dashboard contenente 

   - per gli ordinanti, le richieste di acquisto in corso e già chiuse. Le richieste di acquisto per le quali esiste una proposta associata (non ancora accettata) saranno debitamente evidenziate.
   
   - per i tecnici, la lista delle richieste non ancora assegnate e di quelle di cui sono incaricati. Le richieste con proposta accettata o respinta saranno debitamente evidenziate.

- Gli ordinanti potranno in ogni momento creare una nuova richiesta di acquisto, come indicato al punto 1 della procedura precedente.

- I tecnici potranno prendere in carico le richieste non ancora assegnate e, in un secondo momento, inserire i dettagli di una proposta nelle richieste loro assegnate, come descritto al punto 2.

- Gli ordinanti potranno visualizzare i dettagli delle loro richieste e dell'eventuale proposta associata, con la facoltà di accettarla o respingerla come descritto al punto 3.

- I tecnici potranno marcare una richiesta di acquisto con proposta accettata come "ordinata" (punto 4) per aggiornare l'ordinante sullo *stato* della sua richiesta (sempre visibile assieme alla richiesta stessa nelle dashboard).

- Gli ordinanti, una volta che la loro richiesta sarà posta in stato "ordinato", potranno in qualsiasi momento procedere con la chiusura come descritto al punto 5.

- Tutti i passaggi principali appena descritti (inserimento richiesta, presa in carico, inserimento proposta, accettazione o rifiuto della stessa, ordine del prodotto, chiusura della richiesta) dovrebbero essere opportunamente notificati ai soggetti coinvolti, ad esempio per email, oltre che evidenziati nella dashboard degli interessati.

  
  </section>

<section class="indicazioni break">

# Indicazioni per lo Sviluppo del Progetto

### Tecnologie da utilizzare

- La *struttura* base del sito deve essere realizzata in **HTML5**. La validazione
delle principali pagine del sito è parte integrante dello sviluppo e deve essere
riportata nella documentazione.

- Per la realizzazione del *layout* devono essere utilizzati il più
possibile i figli di stile CSS. Il layout può liberamente basarsi su quelli
disponibili in rete o utilizzati a lezione. Il grado di **personalizzazione**
del layout sarà comunque tenuto in considerazione in sede di valutazione. **Un
layout responsive non è strettamente richiesto ma fortemente consigliato.**

- Per la programmazione lato *client* il linguaggio richiesto
è JavaScript. Si possono liberamente includere nel progetto librerie sviluppate
da terze parti, a patto che la loro portabilità cross-browser sia adeguata e
che nella relazione siano citate e descritte. È in ogni caso **sconsigliato
l'abuso di tali tecnologie** , soprattutto quando sia possibile sostituirle
con un adeguato uso di HTML, CSS, ecc. In linea generale, è **ammissibile che gli
script giochino un ruolo più importante nelle funzionalità la cui utenza è
ristretta e predeterminata**, ad esempio nelle funzionalità *back-end*
per gli amministratori, ma non nel *front-end* pubblico del sito o in una
procedura di login. Su queste parti, invece, **l'uso del sito senza script potrebbe
essere meno "agevole" o permettere di accedere solo alle funzionalità "vitali"**. 

- Per la programmazione lato *server* è **richiesto** l'uso
di Java (*servlet*), eventualmente associato a qualsiasi *DBMS* (se necessario)
e a un *template engine* (come *Freemarker*). Anche in questo caso è
possibile avvalersi di librerie esterne.

- Il sito, in generale, deve funzionare ed avere un buon *rendering*
sulle versioni più recenti di Edge, Firefox e Chrome, e *possibilmente*
essere compatibile con i browser più datati (in questo caso non c'è bisogno che
tutto funzioni perfettamente, ma almeno che le funzionalità *degradino bene*)
e con le ultime versioni di altri browser, come Opera. Tale compatibilità **deve**
essere esplicitamente dichiarata nella documentazione.  

### Svolgimento e Documentazione del Progetto

Le specifiche fornite potrebbero non risultare esaustive o
completamente definite. Ogni funzionalità aggiunta o raffinata, anche tramite
l'interazione con il committente o con gli utenti finali del sito, sarà
adeguatamente valutata. Tutte le scelte progettuali vanno comunque discusse e
motivate.

Il progetto, svolto secondo le linee guida date dalle
specifiche, dovrà essere consegnato nella forma di un sito web completamente
funzionante, i cui contenuti e le cui caratteristiche saranno valutati in sede
d'esame. Le parti della specifica marcate come *opzionali*, se omesse, non
renderanno il progetto insufficiente ma non gli permetteranno comunque di
raggiungere il massimo dei voti. Nel caso si decida di realizzarle, non sarà
necessario che siano perfette o complete, ma che dimostrino chiaramente il
vostro impegno nell'affrontare una tematica avanzata.

La documentazione (**in formato elettronico**) che
accompagna il progetto **deve** contenere almeno le seguenti informazioni:

- Indicazione delle dipendenze software (di quali librerie avete
bisogno dal lato server e client?).

- Indicazione delle funzionalità realizzate e di quelle
eventualmente non realizzate. Descrizione dettagliata delle eventuali
funzionalità extra o opzionali inserite nel progetto.

- Diagramma che illustra la struttura e la navigabilità del sito (ad es.
un *navigation diagram*).

- Schema relazionale della base di dati (se presente).

- Descrizione analitica del layout del sito, con indicazione delle
sue principali componenti statiche/dinamiche.

- Descrizione delle eventuali tecnologie avanzate (linguaggi,
framework, plugin, librerie, ...) utilizzate, del motivo per cui sono state
adottate e del contributo effettivo che hanno dato alla realizzazione del
progetto.

- Descrizione di *eventuali* problemi riscontrati nella
fruizione del sito su browser differenti, lista dei browser compatibili.

- Screenshot delle pagine più importanti del sito (*opzionale*).

Nel caso di gruppi di lavoro composti da più componenti, *il
contributo effettivo offerto da ciascun componente* alla realizzazione
finale **deve** essere descritto nella documentazione (indicando, ad
esempio, chi si è dedicato prevalentemente alla programmazione server, chi ha
realizzato il layout, chi ha programmato lato client, ecc.). In sede di esame,
i responsabili potranno essere chiamati a riferire sugli aspetti loro delegati.  

### Valutazione del Progetto

Nel valutare il progetto consegnato saranno prese in
considerazione le seguenti caratteristiche (in ordine di importanza):
1. Rispetto delle specifiche.
2. Correttezza tecnica.
3. Chiarezza e correttezza organizzativa dei contenuti.
4. Accessibilità e conformità agli standard.
5. Uso appropriato di contenuti statici e dinamici.
6. Qualità del design.
7. Adeguatezza della documentazione.

A questa valutazione si aggiungerà quella generale derivata
dalla discussione del progetto in sede d'esame.  

### Ulteriori Informazioni

Questa specifica è disponibile nel repository del corso di Web Engineering, 
all'indirizzo https://github.com/WebEngineering-Univaq/WE_Project_Specifications.
Ulteriori informazioni e chiarimenti sulle specifiche possono essere richiesti
direttamente via email all'indirizzo giuseppe.dellapenna@univaq.it.

Si ricorda che i progetti vanno svolti in *piccoli* gruppi (tre persone è il numero consigliato). 
Eccezioni a questa regola andranno concordate direttamente col docente.

An English translation of this project specification is also available in the course repository (https://github.com/WebEngineering-Univaq/WE_Project_Specifications).
If the translation is not there, ask the teacher to publish it :smile:
</section>

