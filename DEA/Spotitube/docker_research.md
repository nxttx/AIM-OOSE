# Onderzoek naar java intergratie testen met behulp van Docker en Junit 5

##Prioritering
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
  
## docker commands:
- docker ps: Geeft alle containers die op dit moment aanstaan.

## docker file:
FROM: Hiermee kan je een image aangeven waar op de container zal werken. Bij image moet je denken aan een soort besturingssysteem. <br>
WORKDIR: Met workdir geef je aan wat de working directory is van je app. Je kan het beschouwen als windows command ```cd```.<br>

Docker werkt in layers, elk command is een layer en layers worden gecashed. Hoe hoger je command staat hoe langer hij gecasched blijft zolang er maar boven die layer niets veranderd. Daarom kan het handig zijn om je dependency's zo hoog mogelijk in je docker file te zetten. Zodat deze bij code updates niet opnieuw gecashed hoeven te worden. <br>

COPY: Met copy kan je bestanden uit je eigen directory kopieren naar je docker directory. COPY heeft twee argumenten nodig, het eerste argument is het bestand op jou computer en het tweede argument is de locatie in de container. <br>
RUN: Met run kan je commando's uitvoeren zoals je dat normaal ook over de terminal doet. Neem bijvoorbeeld ```npm install```. Dit gaat overigens in een SHELL FORM.<br>

Er is ook een Docker ignore file. Dit is net zoals een gitignore. Alle directory's die in de dockerignore staan zullen bij een copy worden overgeslagen. Handig! Vooral met je dependency's die je in een andere layer wilt hebben.<br>

ENV: Met ENV can je environment variables maken voor in de docker container.<br>
EXPOSE: In EXPOSE geef je de poorten op die de container mag doorlaten/ op mag luisteren. Hierdoor wordt je webserver bereikbaar. <br>
CMD: CMD kan maar 1x worden gebruikt in een container. CMD verteld docker wat er precies moet worden uitgevoerd in de container. Dit commando wordt geschreven in EXEC FORM (```["NPM", "START"]``)








