# Projecte SERPROES-DEMO 1.0

Aquesta aplicació mostra com un backoffice desenvolupat en ReactJS s'autentica en Keycloak i fa 
cridades a una REST API java.

### Requeriments

* Java OpenJDK 11
* JBoss EAP 7.2
* Interfície d'usuari: ReactJS
* Base de dades: PostgreSQL 10
* Mecanisme d'autenticació: RedHat Single Sign-On (Keycloak)
* Compilació i empaquetat: Maven 3.6

**<ins>Autenticació en Keycloak desde l'interfície d'usuari ReactJS</ins>**

Hem configurat un client de Keycloak per retornar **JSON WEB TOKEN (JWT)** en iniciar la sessió.

L'usuari s'autentica mitjançant el seu usuari i contrasenya del GOIB. S'accedeix mitjançant una llibreria Javascript
_client-side_. Una cosa important que cal destacar sobre l'ús d'aplicacions _client-side_ és que ha de ser un client keycloak
públic, ja que no hi ha una manera segura d'emmagatzemar les credencials al codi de l'aplicació. Això fa molt important assegurar-se
que les URIs de redirecció que es configuren al client keycloak siguin correctes i el més específiques possible.

Dins l'administrador keycloak, un cop creat el client a la pestanya _Instal·lació/Format/Download_, es troba el 
_**Keycloak JSON OIDC**_ que s'ha d'incloure en el codi de l'aplicació client (ReactJS). Aquest codi inclós en un fitxer 
"keycloak.json", és l'única cosa que es necessita per a fer l'autenticació _client-side_.

En iniciar sessió, keycloak retorna el *JWT*. Aquest token s'emmagatzema en el sessionStorage i l'aplicació pot fer 
sol·licituds segures als services REST mitjançant la inclusió del token a la capçalera d'autorització.

**<ins>Verificació del JWT des de la API REST</ins>**

La API Rest rep com a capçalera el paràmetre _Authorization_ amb el JWT.

La llibreria _java-jwt_ (auth0/java-jwt) implementa la verificació del token usant l'algorisme _RSA256(RSA Signature
amb SHA-256)_. El consumidor de JWT recupera una clau pública dels extrems de metadades proporcionats per Auth0 i 
l'utilitza per validar la signatura JWT. Una vegada verificat es retorna la resposta.

### Ús del repositori

- Desplegar al JBoss el fitxer ear generat.
  

- Executar scripts de creació de la base de dades que estan en la carpeta scripts del repositori:
     ```
      psql -U postgres -f <project-home>/scripts/bbdd/01_create_schema.sql
    
      psql -U postgres -f <project-home>/scripts/bbdd/02_sample_data.sql
     ```

- Configurar el JBoss per a accedir a la base de dades (Veure _Guia de configuració_ apartat 
"5. Configuració d'un Datasource en JBOSS") i crear els datasources que facin falta afegint-los dins l’etiqueta 
  <datasources> de l’standalone.xml:

    ```
    <!-- SERPROES === datasource === -->
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
- Configurar client keycloak.
  
  Assignar el rol SER_ADMIN a l’usuari.
  
VER:adaptar para que funcione con goib-default.
## Documentació

Nom | Descripció | Enllaç
------------ | ------------- | -------------
(CAT) Guia de configuració.odt | Guia de configuració | [Document](./doc/(CAT)%20Guia%20de%20configuració.odt)


## Llicència
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
