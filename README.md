# FlickrGallery

# Beskrivning 
Detta är en Java-applikation som hämtar bilder från Flickr API. Applikationen har ett användargränssnitt där användaren kan mata in sökord som filtrerar vilka bilder som kommer att visas. Applikationen är byggd genom en REST-baserad web api och skapad med Spring Boot.

# Bygg och kör applikationen
För att bygga och köra applikationen, följ stegen nedan:
1. Klona projektet från huvudgrenen (master) till din utvecklingsmiljö. (Läs här om hur: https://www.jetbrains.com/guide/java/tutorials/creating-a-project-from-github/clone-from-github/ )
2. Öppna projektet i din IDE och konfigurera det enligt dina behov.
3. Bygg applikationen genom att köra byggkommandot i din IDE.
4. Navigera sedan till: http://localhost:3000 i din webbläsare.

# Användarbeskrivning 
För att använda applikationen, följ stegen nedan:
1. Mata in vald text i täxtfäletet.
2. Klicka på knappen för att hämta bilder som matchar texten från Flickr.
3. Navigera mellan knapparna "Next" och "Previous" för att visa bilder på nästkommande respektive föregående sida. Brevid knapparna visas hur många sidor som finns.
4. Skriv in ny söktext och upprepa för att hämta nya bilder.

# Förbättringsområden
- Mer attraktiv design av webapplikationen.
- Optimera sökresultat från Flickr API:et genom ytterligare filtrering.
