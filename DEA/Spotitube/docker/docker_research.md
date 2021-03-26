# Onderzoek naar java intergratie testen met behulp van Docker en Junit 5

## Prioritering

Ik heb voor het onderzoek de volgende MoSCoW prioritering besloten:

### Must have:

- Wat is docker en waarvoor is het handig.
- Hoe kan ik met maven en docker testen

### Should have:

- Welke configuratie heeft mijn docker file nodig voor DAO intergratie testen.

### Could have:

- Zou in Docker ook TomEE kunnen werken.

### Wont have:

- Hoe deploy je docker met TomEE naar een server zoals Azure.

## Handige pagina's:

- https://www.docker.com/101-tutorial --gebruikt --
- https://www.testcontainers.org/quickstart/junit_5_quickstart/
  -- https://www.youtube.com/watch?v=gAkwW2tuIqE&ab_channel=Fireship -- gebruikt--
  -- https://github.com/vishnubob/wait-for-it -- gebruikt-- --https://docs.docker.com/compose/startup-order/ --
  gebruikt--

## docker commands:

- docker ps: Geeft alle containers die op dit moment aanstaan.

## docker file:

FROM: Hiermee kan je een image aangeven waar op de container zal werken. Bij image moet je denken aan een soort
besturingssysteem. <br>
WORKDIR: Met workdir geef je aan wat de working directory is van je app. Je kan het beschouwen als windows
command ```cd```.<br>

Docker werkt in layers, elk command is een layer en layers worden gecashed. Hoe hoger je command staat hoe langer hij
gecasched blijft zolang er maar boven die layer niets veranderd. Daarom kan het handig zijn om je dependency's zo hoog
mogelijk in je docker file te zetten. Zodat deze bij code updates niet opnieuw gecashed hoeven te worden. <br>

COPY: Met copy kan je bestanden uit je eigen directory kopieren naar je docker directory. COPY heeft twee argumenten
nodig, het eerste argument is het bestand op jou computer en het tweede argument is de locatie in de container. <br>
RUN: Met run kan je commando's uitvoeren zoals je dat normaal ook over de terminal doet. Neem
bijvoorbeeld ```npm install```. Dit gaat overigens in een SHELL FORM.<br>

Er is ook een Docker ignore file. Dit is net zoals een gitignore. Alle directory's die in de dockerignore staan zullen
bij een copy worden overgeslagen. Handig! Vooral met je dependency's die je in een andere layer wilt hebben.<br>

ENV: Met ENV can je environment variables maken voor in de docker container.<br>
EXPOSE: In EXPOSE geef je de poorten op die de container mag doorlaten/ op mag luisteren. Hierdoor wordt je webserver
bereikbaar. <br>
CMD: CMD kan maar 1x worden gebruikt in een container. CMD verteld docker wat er precies moet worden uitgevoerd in de
container. Dit commando wordt geschreven in EXEC FORM (```["NPM", "START"]```)

## Building

Om je docker image te bouwen gebruik je in je terminal het commando ```docker build```. Door `-t` toe te voegen kan je
je image een naam geven (normaal is username/titel:versienummer). na de naam geef je je directroy op waar je conatiner
gemaakt moet worden. Door een punt (.) te gebruiken doe je dat in je huidige directory. <br>

## Docker uitvoeren

Gebruik `Docker Run imageID/tagname` om je image op te laten starten.<br>
Wanneer je iets als een webserver wilt runnen in je docker moet je het port forwarden in je run command. Dat doe je
met ```docker run -p LocalPoort:ContainerPoort```.<br>
Docker containers stoppen niet wanneer je de terminal sluit. Daarvoor moet je in de UI het stoppen. PAS OP! De data die
is opgeslagen in de container wordt dan verwijderd. Met Volumes kan je er voor zorgen dat je bestanden wel bewaard
blijven. Je maakt dan eigenlijk een gedeelde map met je container en je computer. <br>
Voor meer info over volumes [druk hier](https://youtu.be/gAkwW2tuIqE?t=493).

In de Docker applicatie kan je van elk van je containers gemakkelijk de commandline inzien en gebruiken alsof je in die
bijv. linux distro bent.<br>

## Docker compose

Met docker is het de bedoeling dat je maar 1 service draait per container. Daarom moet je database in een apparte
container staan van je webserver. Hiervoor kan je gemakkelijk Docker compose gebruiken. Met docker comopse kan je
defineren welke docker files op welke manier moeten worden uit gevoerd.<br>

Je maakt een docker compose in een ```docker-compose.yml```. Hierin zet je:

- versie
- services
- volumes

In elke service kan je een appart component zetten. In het onderstaande voorbeeld bijvoorbeeld 'web' en 'db'. in deze
componenten kan je de build locatie aangeven of de image die je wilt gebruiken, environment variables, poorten waarop ze
moeten zijn geforward of gedeelde volumes.<br>

docker-compose.yml

```yml
version: '3'
services:
  web:
    build: . # huidige directory
    ports:
      - "8080:8080" # LocalPoort:ContainerPoort
  db:
    image: "mysql"
    environment:
      MYSQL_ROOT_PASSWORD: root
    volumes:
      - db-data:/foo

volumes:
  db-data:
```

Met het commando `docker-compose up` kan je nu je alle containers tegerlijkertijd openen. en met het
commando `docker-compose down` sluit je direct weer alle containers af.

## bevindingen

Om maven met Docker te gebruiken heb ik een bestaande image gebruikt. Jammer genoeg is er geen image voor de JDK 12. dus
heb ik in mijn POM mijn JDK veranderd naar 11. // outdated<br>

Het eerste waar ik tegen aan liep was dat maven eerder klaar was dan de db. Daarvoor heb ik wait-for-it.sh gebruikt. Dit
moest ik nog wel een beetje tweaken. Omdat de standaard tijd van 15 seconde niet genoeg was voor de db om op te
starten.<br>

Daarna liep ik tegen het probleem van de datasource aan. De datasource was iets dat altijd door tomee geinject werdt.
Echter kan dit nu niet gebeuren. Omdat DataSource zelf een interface is moest ik op zoek gaan naar een andere manier om
een data source te maken. Ik zit nu te denken aan een maven dependency. <br>

Na een gesprek met Michel heeft hij mij geholpen dat ik de connection moet mocken. Of te wel een nieuwe connection
aanmaken en deze bij de get connection mocken. <br>

Ook hoeven mijn tests niet te draaien in docker. Hiervoor kan ik een maven dependency gebruiken die automatisch mijn
container start. https://github.com/fabric8io/docker-maven-plugin <br>

Om een database connectie te kunnen maken in je tests heb ik deze video gebruikt: https://www.youtube.com/watch?v=2i4t-SL1VsU&ab_channel=luv2code , uit enidelijk heb ik dit gebruit voor maven https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.23 <br>

