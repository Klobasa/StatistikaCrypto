**Sledování kryptoměn**

Vyrobte aplikaci postavenou na technologii SpringBoot v3. Výsledek by měl být spustitelný
.jar file případně docker image a kód na GitLabu. Všechny použité knihovny by měly být
ideálně nejnovější verze.
- Aplikace bude umět vracet statistiky cen kryptoměn které si uživatel vybere.
- Pro každou uloženou sledovanou měnu, bude pravidelně, dle nastavení pro danou
  měnu, kontrolovat na libovolné burze ceny a ukládat cenu do databáze.
- Pro zadané období bude schopna vrátit maximální, minimální a průměrnou cenu.
- Vystavené endpointy jsou zabezpečeny Basic auth.

> [!NOTE]
>**ID měny**
> 
>ID (kód) měny je použit podle použitého API CoinGecko, u běžných kryptoměn jde většinou o jejich název (bitcoin, litecoin, dogecoin).
Celý seznam lze najít [zde](https://docs.google.com/spreadsheets/d/1wTTuxXt8n9q7C4NDXqQpI3wpKu1_5bGVmP9Xz0XGSyU/edit?gid=0#gid=0).



**Vrácení seznamu všech sledovaných kryptoměn**

`GET` /cron

`GET` http://localhost:8080/cron

**Zapnutí sledování pro zadanou měnu**

`POST` /cron/new
>Params
> >__currency__ ID měny
>
> >__interval__ interval volání v sekundách

`POST` http://localhost:8080/cron/new?currency=bitcoin&interval=300

**Vypnutí sledování měny**

`POST` /cron/remove
>Params
> >__currency__ ID měny

`POST` http://localhost:8080/cron/remove?currency=bitcoin

**Vrácení statistik cen pro zadané období**
`GET` /statistics/get
>Params
> >__currency__ ID měny
>
> >__startTime__ YYYY-MM-DDThh:mm:ss
>
> >__endTime__ YYYY-MM-DDThh:mm:ss

`GET` http://localhost:8080/statistics/get?currency=bitcoin&startTime=2024-10-15T00:00:00&endTime=2024-10-31T00:00:00



