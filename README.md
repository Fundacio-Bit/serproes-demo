# ![Logo](https://github.com/Fundacio-Bit/serproes-demo/blob/main/logo_bioatles.JPG) Projecte BIOATLES 1.0
*Projecte BIOATLES*

En aquest document s'explica primer com configurar les tecnologies que formen part del projecte Bioatles.
Es recomana la utilització de: 

● OpenJDK11

● JBoss 7.2 EAP

**Descripció**

Bioatles és una eina utilitzada pel Servei de Protecció d'Espècies del Govern de les Illes Balears per a tasques de gestió.


 ## Especificacions Tecnològiques

L'aplicació està implementada utilitzant la versió lliure de la plataforma de desenvolupament de Java OpenJDK 11 i 
s'executa sobre un servidor d'aplicacions JBoss EAP 7.2.

La interfície d'usuari està desenvolupada amb ReactJS. 

La persistència de dades funciona sobre una base de dades PostgreSQL 10. L'accés a les dades es durà a terme mitjançant 
la API JPA 1.0.

L'autenticació per a accedir als recursos protegits dels mòduls web es realitza mitjançant un mecanisme d'autenticació 
centralitzat: _RedHat Single Sign-On (Keycloak)_.

Per a la construcció del projecte (compilació i empaquetat) i la gestió de dependències s'utilitza Maven 3.6.

### Seguretat

**<ins>Autenticació en Keycloak desde l'interfaz de usuario ReactJS</ins>**

Hem configurat un client de Keycloak per retornar JWT en iniciar la sessió.

L'usuari s'autentica mitjançant el seu usuari i contrasenya del GOIB. S'accedeix mitjançant una llibreria Javascript 
client-side. Una cosa important que cal destacar sobre l'ús d'aplicacions del client és que el client ha de ser un client 
públic, ja que no hi ha una manera segura d'emmagatzemar les credencials del client en una aplicació del client. Això fa
molt important assegurar-se que els URI de redirecció que heu configurat per al client són correctes i el més específics
possible.

Un cop creat el client feu clic a la pestanya Instal·lació, seleccioneu _**Keycloak JSON OIDC**_ per a l'opció Format i, a 
continuació, feu clic a _Download_.

![img_1.png](img_1.png)

El fitxer "keycloak.json" descarregat s'ha d'enviar al vostre servidor web a la mateixa ubicació que les pàgines HTML i 
és l'única cosa que es necessita per a fer l'autenticació des del client.

En iniciar sessió retorna un *Bearer Token(JWT)*. Aquest token s'emmagatzema en el sessionStorage i
l'aplicació pot fer sol·licituds als services REST garantits mitjançant la inclusió del token a la capçalera d'autorització.

**<ins>Verificació del JWT(JSON Web Token) des de la API REST</ins>**

La API Rest rep com a capçalera el paràmetre _Authorization_ amb el JWT. 

La llibreria _java-jwt_ (auth0/java-jwt) implementa la verificació del token usant l'algorisme _RSA256(RSA Signature 
amb SHA-256)_. Es tracta d'un algorisme asimètric, cosa que significa que hi ha dues claus: una clau pública i una clau 
privada que s'ha de mantenir en secret. Auth0 té la clau privada utilitzada per generar la signatura, i el consumidor de 
JWT recupera una clau pública dels extrems de metadades proporcionats per Auth0 i l'utilitza per validar la signatura 
JWT. Una vegada verificat es retorna la resposta

## Instal·lació

### _Windows_

* **OpenJDK 11** (Java Development Kit: JRE + JVM)

    * Descarregar **_openjdk-11+28_windows-x64_bin.zip_** des [https://jdk.java.net/java-se-ri/11](https://jdk.java.net/java-se-ri/11)
      i escollir entre versió Linux/x64 o Windows/x64.
    * Es descarregarà un fitxer openjdk-11+28_windows-x64_bin.zip que es descomprimirà a C:\Program Files\Java o al
      directori escollit.
    * Seleccionem la ubicació on volem que quedi els binaris de l'JDK. En el meu cas vaig a utilitzar: c: \\ Java.
      S'ha creat una carpeta anomenada jdk-11.0.2. Hem de recordar aquesta ruta perquè la configurarem en les variables
      d'entorn.
    * Establir variable JAVA_HOME amb el valor C:\ProgramFiles\Java\jdk-11 i incorporar JAVA_HOME\bin al PATH:
      ```
      JAVA_HOME=C:/Java/jdk-11
      PATH=%PATH;C:/Java/jdk-11/bin
      ```
      És necessari tenir establerta la variable JAVA_HOME  com a variable d’entorn ja que tots els scripts de JBoss es
      asen en la variable.

* **JBoss 7.2 EAP** (Servidor d'aplicacions Java)

    * Anar a https://developers.redhat.com/products/eap/download/ i descarregar. És necessari tenir compte a RedHat.
      Es descarregarà un fitxer jboss-eap-7.2.0-installer.jar.

    * Executar instal·lador:
      ```bash
      java -jar jboss-eap-7.2.0-installer.jar
      ```
      Executant el fitxer s’iniciarà l’assistent d’instal·lació que demanarà especificar el directori d’instal·lació del
      JBoss i la creació d’un usuari administrador del tipus management’.   Jo vaig donar d'usuari i password, usuari: admin i password: admin.2021

      Si no demanàs usuari i hi hagués problemes d’autenticació, es pot crear un usuari amb l’script JBOSS_HOME/bin/add-user

      IMPORTANT: Si la comanda add-user no funciona, provar amb Java 8.

    * Establir variable JBOSS_HOME amb el valor del directori d’instal·lació. P.ex:
      ```
      JBOSS_HOME = C:\Java\jboss-eap-7.2
      ```

      IMPORTANT: 	Si aparegués un error de WeldStartService de Contexto de solo lectura, s’arregla afegint
      require-bean-descriptor="true"  al subsistema Weld

      mvn clean
      mvn install

    * Per encendre JBoss: executar fitxer 	C:\Desarrollo\jboss-eap-7.2\bin\standalone.bat
    * Per desplegar EARs: Deixar EAR dins la carpeta C:\Desarrollo\jboss-eap-7.2\standalone\deployments i encendre JBoss.

      Fitxer de configuració important és l’standalone.xml i es troba a: C:\Desarrollo\jboss-eap-7.2\standalone\configuration

* **PostgreSQL 10**


* **JDBC Drivers**

  Before your application can connect to a datasource, your datasource vendor's JDBC drivers need to be installed.
    * Descarregar les llibreries des dels webs oficials:descarregar el fitxer postgresql-42.2.5.jar del següent web
      https://jdbc.postgresql.org/download.html.

      NOTA: Es recomana la descàrrega de la versió 42.2.5 que es troba en la taula de Other versions en la columna JDBC 4.2

    * Crear fitxer amb el nom module.xml amb el següent contingut:
      ```
      <module xmlns="urn:jboss:module:1.0" name="org.postgresql">
      <resources>
      <resource-root path="postgresql-42.2.5.jar"/>
      </resources>
      <dependencies>
      <module name="javax.api"/>
      <module name="javax.transaction.api"/>
      </dependencies>
      </module>
      ```
    * Copiar el fitxer **_module.xml_** i **_postgresql-42.2.5.jar_** a la ruta JBOSS_HOME/modules/system/layers/base/org/postgresql/main

    * Afegir la següent configuració dels drivers al fitxer standalone.xml dins JBOSS_HOME/standalone/configuration.

    ```
    <datasources>
          ...
       <drivers>
            <driver name="h2" module="com.h2database.h2">
               <xa-datasource-class>org.h2.jdbcx.JdbcDataSource
               </xa-datasource-class>
            </driver>
            <!-- BIOATLES === drivers === -->
            <driver name="postgresql" module="org.postgresql">
                <xa-datasource-class>org.postgresql.xa.PGXADataSource
                 </xa-datasource-class>
            </driver>
    </datasources>
    ```
    * Reiniciar JBoss, si es trobàs en marxa, i crear els datasources que facin falta afegint-los dins l’etiqueta
      <datasources> de l’standalone.xml:

    ```
    <!-- BIOATLES === datasource === -->
	<datasource jndi-name="java:/SerproesDS" pool-name="SerproesDS">
        <connection-url>jdbc:postgresql://localhost:5432/serproes</connection-url>
        <driver-class>org.postgresql.Driver</driver-class>
        <datasource-class>org.postgresql.ds.PGSimpleDataSource</datasource-class>
        <driver>postgresql</driver>
        <security>
            <user-name>serproes</user-name>
            <password>serproes</password>
        </security>
        <validation>
            <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
            <background-validation>true</background-validation>
            <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
        </validation>
    </datasource>
    ```


### _Ubuntu_

* **OpenJDK 11** (Java Development Kit: JRE + JVM)

    ```bash
    sudo add-apt-repository ppa:openjdk-r/ppa
    sudo apt update
    sudo apt install openjdk-11-jdk
    ```

* **JBoss 7.2 EAP** (Servidor d'aplicacions Java)

    * Descarregar jboss-eap-7.2.0-installer.jar des https://developers.redhat.com/products/eap/download/

      Nota: Les descàrregues via curl o wget de l'instal·lador o zip generen arxius corruptes. El resolem fent la
      descàrrega en un PC amb interfície gràfica (el nostre windows per exemple) i movent el fitxer tal com el servidor
      de preproducció amb WinSCP

    * Executar instal·lador:
      ```bash
      java -jar jboss-eap-7.2.0-installer.jar
      ```
      Seguir instruccions de l'instal·lador.

      Encara que no sabia si era necessari respondre afirmativament a la següent pregunta:    
      Li agradaria generar un script d'instal·lació automàtica i un arxiu de propietats? (Y / N) [n]:

    * Al finalitzar la instal·lació es va a la carpeta / bin de l'JBOSS instal·lat i s'executa:
      ```bash
      ./standalone.sh -b 0.0.0.0 -bmanagement <ip de servidor en el qual corre JBOSS>
      ```
      saber quin és suo ./standalone.sh -b 0.0.0.0 -bmanagement 10215216210
      A l'executar d'aquesta manera habilitem l'accés remot a l'JBOSS des de qualsevol API. Per l'exemple posat,
      s'accediria a l'admin amb la URL:
      http://10.215.216.210:9990/console

## Documentació
* [Keycloak Javascript Adapter](https://github.com/keycloak/keycloak-documentation/blob/master/securing_apps/topics/oidc/javascript-adapter.adoc     )
* [Keycloak connection using a Java application](https://developers.redhat.com/blog/2020/11/24/authentication-and-authorization-using-the-keycloak-rest-api#keycloak_connection_using_a_java_application)
* [auth0/java-jwt](https://github.com/auth0/java-jwt)

## Llicència
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
