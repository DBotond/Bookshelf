# Könyvespolc  dokumentáció

## **Áttekintés**
Célom egy olyan program létrehozása, ami képes bejelentkeztetni a felhasználót
egy grafikus felületre, és ott egy adatbázis segítségével könyveket tudjon 
"elhelyezni" egy könyvespolcra.

## **Felhasznált keretrendszerek**
A programot Java nyelven fogom elkészíteni, egyszerű grafikus felülettel (JFrame).

## **Miért választottam ezt?**
-Véleményem szerint nem lesz gond, hogy unit testeket írjak hozzá.

-Sok féle tervezési mintát rá lehet húzni.

-Kellően bonyolult, de annyira talán nem, hogy hetekig folyamatosan írnom kellene.


## **Felhasználandó tervezési minták**
-MVC
 Model   (magyarul  modell): Az  adatokat  kezelő,  vagyis  tulajdonképpen  az  üzleti
 logikát megvalósító réteg. Ez felel az adatok tárolásáért, visszaolvasásáért. 
 Itt foglalnak helyet azok a függvények is, amik műveleteket végeznek az adatokon.
 Része az adatbázis is.
 View  (magyarul  nézet): A  felhasználói  felület  megjelenítéséért,
 a  felhasználó  különféle nyűgjeinek a Vezérlő felé továbbításáért 
 felelős réteg. Itt jelennek meg a felhasználó számára
 a vezérlőelemek, a felhasználónak szánt adatok megfelelő formában való megjelenítése
 is itt történik.
 Controller  (magyarul  vezérlő): Ez a  réteg  a  vezérlőelemek  eseményeinek
 alapján  meghívja  a modell  megfelelő  függvényeit,  illetve  ha  a  megjelenítésben
 érintett  adatok  változnak,  akkor erről értesíti a Nézetet.

-Építő programtervezési minta:  Az Absztrakt gyár és a Gyártó metódus programtervezési 
 mintákkal szemben, melyek célja a többalakúság alkalmazása, az építő minta célja,
 hogy alternatívát találjon a teleszkópos konstruktor anti-mintára.

-Felelősséglánc tervezési minta: Az objektumorientált tervezésben 
 a felelősséglánc egy tervezési minta, amely parancsobjektumokból és feldolgozó 
 objektumokból áll. Minden egyes feldolgozó objektum tartalmazza azt a logikát, 
 amellyel a parancsobjektum definiálható és kezelhető, valamint olyan folyamatokat, 
 amiket kidolgozásra továbbadhat a lánc egy következő folyamatának. 
 Olyan mechanizmus is létezik, amivel lehetőségünk adódik arra, hogy a 
 felelősséglánc végén egy újabb feldolgozó objektumot adjunk hozzá.

## **Tesztelés**
- Unit teszt:
	- Unit tesztelésre a JUnit keretrendszert fogom felhasználni. Ebben Test osztályokat fogok létrehozni, amelyekben
  assert metódusokkal fogom összehasonlítani a várt, és a kapott eredményeket.
  
  - Mit tesztelek: szinte mindent, ami nem triviális.
  Tesztelni fogom, hogy a könyv objektumok akkor is külön példányok, ha ugyanolyan mezőik vannak,
  azt is, hogy nem-e üres példányt ad-e vissza. A felhasználónál tesztelni fogom , hogy a
  UserId-t megfelelően állítja-e be a program.
  A logikán kívül a GUI elemeit is tesztelni fogom, hogy a gombok és egér-műveletek rendben legyenek.
  Ugyanakkor próbálom az alkalmazáslogikát külön tartani a GUI-tól, hogy minél kevesebb tesztre legyen szükség vele.
  
  - Mi történik, ha nem az elvárt értéket kapjuk vissza: az assert metódusok érthető szövegeket fognak visszaadni, 
  melyeket a program még egy külön logban el is fog tárolni.

