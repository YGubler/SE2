Info
====

Das Skript erstellt die Datenbank Kinoverwaltung.

Beim Start wird ein neuer Benutzer 'kino' mit Passwort erstellt.
Danach wird eine Datenbank mit diesem Benutzer als Besitzer erstellt.

Anschliessend werden die Tabellen angelegt, mit Daten befüllt und ein paar Abfragen darauf ausgeführt.



Ausführen der Skripts auf den verschiedenen Systemen
====================================================


Windows:
--------

1. Eine Windows Command Shell starten (Windows-R, dann cmd eintippen und Eingabetaste drücken)
2. Überprüfen ob psql ausgeführt werden kann
2.1 Wenn nicht muss in der Systemsteuerung die Umgebungsvariable PATH, für den aktuellen Benutzer oder für alle, durch den Pfad zum postgres\9.x\bin Verzeichnis ergänzt werden. Beispiel:
3. Folgendes Kommando, im Verzeichnis der vorliegenden SQL-Dateien, eintippen und ausführen:
psql -U postgres -f 0_runAllScripts.sql

Alternativ kann auch die Datei run.bat in der Konsole ausgeführt werden. Darin ist eine Anpassung der Konsolencodepage schon eingebaut.

Sollten Fehler in der Darstellung von Zeichen auftreten (Umlaute etc.) kann man versuchen dies mit der Datei WindowsConsoleSettings.reg zu korrigieren.



Linux / Mac OSX
--------------

Unter Linux wird in den meisten Fällen ein eigener Benutzer postgres für die Datenbankverwaltung angelegt. Daher muss in der Grundkonfiguration jeweils sudo verwendet werden um Datenbankbefehle auszuführen.

sudo -u postgres psql -f 0_runAllScripts.sql

Alternativ kann man sich für den Linux-Login einen postgres Superuser Account einrichten wonach die Verwendung von sudo -u postgres entfällt. Dies geschieht mit folgendem Kommando (LoginName durch den *nix-Account-Namen ersetzen):

$ createuser -s -U postgres --interactive
Enter name of role to add: LoginName
...

(Siehe auch: https://wiki.archlinux.org/index.php/Postgres#Create_your_first_database.2Fuser )
