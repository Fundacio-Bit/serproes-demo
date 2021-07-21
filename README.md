# ![Logo](https://github.com/Fundacio-Bit/serproes-demo/blob/main/logo_bioatles.JPG) Projecte BIOATLES 1.0 (serproes-1.0)
*Projecte BIOATLES*



**Documentació**

[Documentació de branca estable](./doc)


**Descripció**

Projecte OpenJDK11 y JBoss 7.2 EAP

**Instal·lació de OpenJDK 11**

Windows

Anar a https://jdk.java.net/java-se-ri/11 i escollir entre versió Linux/x64 o Windows/x64.
 	
Es descarregarà un fitxer openjdk-11+28_windows-x64_bin.zip	que es descomprimirà a C:\Program Files\Java o al directori escollit.
 	
Establir variable JAVA_HOME amb el valor C:\ProgramFiles\Java\jdk-11 i incorporar JAVA_HOME\bin al PATH.
 	
És necessari tenir establerta la variable JAVA_HOME  com a variable d’entorn ja que tots els scripts de JBoss es basen en la variable.
Ubuntu


Ejecutar:
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt update
sudo apt install openjdk-11-jdk

Instal·lació de JBoss 7.2 EAP

JBoss: Servidor de Aplicaciones Java.

Windows
Anar a https://developers.redhat.com/products/eap/download/ i descarregar. És necessari tenir compte a RedHat.
Es descarregarà un fitxer jboss-eap-7.2.0-installer.jar.
 	
Executant el fitxer s’iniciarà l’assistent d’instal·lació que demanarà especificar el directori d’instal·lació del JBoss i la creació d’un usuari administrador del tipus management’.
 	
Si no demanàs usuari i hi hagués problemes d’autenticació, es pot crear un usuari amb l’script JBOSS_HOME/bin/add-user
 		
IMPORTANT: Si la comanda add-user no funciona, provar amb Java 8.
 	
Establir variable JBOSS_HOME amb el valor del directori d’instal·lació. P.ex: C:\Desarrollo\jboss-eap-7.2
 	
IMPORTANT: 	Si aparegués un error de WeldStartService de Contexto de solo lectura, s’arregla afegint require-bean-descriptor="true"  al subsistema Weld



Per encendre JBoss: executar fitxer 	C:\Desarrollo\jboss-eap-7.2\bin\standalone.bat
Per desplegar EARs: Deixar EAR dins la carpeta C:\Desarrollo\jboss-eap-7.2\standalone\deployments i encendre JBoss.
Fitxer de configuració important és l’standalone.xml i es troba a: C:\Desarrollo\jboss-eap-7.2\standalone\configuration

Ubuntu
Descargar  jboss-eap-7.2.0-installer.jar desde https://developers.redhat.com/products/eap/download/
Nota: Las descargas via curl o wget del instalador o zip generan archivos corruptos. Lo resolvemos haciendo la descarga en un PC con  interfaz gráfica (nuestro windows por ejemplo) y moviendo el archivo al servidor de preproducción con WinSCP
Ejecutar instalador:
java -jar jboss-eap-7.2.0-installer.jar
Seguir instrucciones del instalador. Yo di de usuario y password:
usuario admin
password adminadmin0.
Y, aunque no sabía si era necesario respondí afirmativamente a la siguiente pregunta:
¿Le gustaría generar un script de instalación automática y un archivo de propiedades? (y/n) [n]:
AL finalizar la instalación se va a la carpeta /bin del JBOSS instalado y se ejecuta:
sudo ./standalone.sh -b 0.0.0.0 -bmanagement <ip del servidor en el que corre JBOSS>
p.ej. sudo ./standalone.sh -b 0.0.0.0 -bmanagement 10.215.216.210
Al ejecutarlo de esta forma habilitamos el acceso remoto al JBOSS desde cualquier API. Para el ejemplo puesto, se accedería al admin con la URL:
http://10.215.216.210:9990/console



