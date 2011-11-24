[INFO] 
NOTE: Maven is executing in offline mode. Any artifacts not already in your local
repository will be inaccessible.

[INFO] Scanning for projects...
[INFO] Searching repository for plugin with prefix: 'gae'.
[INFO] ------------------------------------------------------------------------
[INFO] Building monodaipoly.Spring.PROJECT
[INFO]    task-segment: [gae:run]
[INFO] ------------------------------------------------------------------------
[INFO] Preparing gae:run
[INFO] [dependency:unpack {execution: unpack-gae}]
[INFO] Configured Artifact: com.google.appengine:appengine-java-sdk:1.5.5:zip
[INFO] Unpacking /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5.zipto
 /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5
with Includes null and excludes:null
[INFO] [install:install-file {execution: install-jdo2-api}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/user/orm/jdo2-api-2.3-eb.jar to /home/instalador/.m2/repository/com/google/appengine/jdo2-api/2.3-eb/jdo2-api-2.3-eb.jar
[INFO] [install:install-file {execution: install-datanucleus-appengine}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/user/orm/datanucleus-appengine-1.0.9.final.jar to /home/instalador/.m2/repository/com/google/appengine/orm/datanucleus-appengine/1.0.9/datanucleus-appengine-1.0.9.jar
[INFO] [install:install-file {execution: install-datanucleus-core}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/user/orm/datanucleus-core-1.1.5.jar to /home/instalador/.m2/repository/com/google/appengine/datanucleus-core/1.1.5/datanucleus-core-1.1.5.jar
[INFO] [install:install-file {execution: install-geronimo-jta}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/user/orm/geronimo-jta_1.1_spec-1.1.1.jar to /home/instalador/.m2/repository/com/google/appengine/geronimo-jta_1.1_spec/1.1.1/geronimo-jta_1.1_spec-1.1.1.jar
[INFO] [install:install-file {execution: install-appengine-tools-api}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/appengine-tools-api.jar to /home/instalador/.m2/repository/com/google/appengine/appengine-tools-api/1.0/appengine-tools-api-1.0.jar
[INFO] [install:install-file {execution: install-geronimo-api}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/user/orm/geronimo-jpa_3.0_spec-1.1.1.jar to /home/instalador/.m2/repository/com/google/appengine/geronimo-jpa_3.0_spec/1.1.1/geronimo-jpa_3.0_spec-1.1.1.jar
[INFO] [install:install-file {execution: install-datanucleus-jpa}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/user/orm/datanucleus-jpa-1.1.5.jar to /home/instalador/.m2/repository/com/google/appengine/datanucleus-jpa/1.1.5/datanucleus-jpa-1.1.5.jar
[INFO] [install:install-file {execution: install-appengine-api-labs}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/impl/appengine-api-labs.jar to /home/instalador/.m2/repository/com/google/appengine/appengine-api-labs/1.5.5/appengine-api-labs-1.5.5.jar
[INFO] [install:install-file {execution: install-appengine-api-stubs}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/impl/appengine-api-stubs.jar to /home/instalador/.m2/repository/com/google/appengine/appengine-api-stubs/1.5.5/appengine-api-stubs-1.5.5.jar
[INFO] [install:install-file {execution: install-appengine-local-runtime}]
[INFO] Installing /home/instalador/.m2/repository/com/google/appengine/appengine-java-sdk/1.5.5/appengine-java-sdk-1.5.5/lib/impl/appengine-local-runtime.jar to /home/instalador/.m2/repository/com/google/appengine/appengine-local-runtime/1.5.5/appengine-local-runtime-1.5.5.jar
[INFO] [resources:resources]
[INFO] Using default encoding to copy filtered resources.
[INFO] [compiler:compile]
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 2 source files to /home/instalador/workspace/monodaipoly/target/classes
[INFO] [datanucleus:enhance {execution: default}]
[INFO] DataNucleus Enhancer (version 1.1.4) : Enhancement of classes
DataNucleus Enhancer : Classpath
>>  /home/instalador/.m2/repository/javax/jdo/jdo2-api/2.3-ec/jdo2-api-2.3-ec.jar
>>  /home/instalador/.m2/repository/javax/transaction/jta/1.1/jta-1.1.jar
>>  /home/instalador/.m2/repository/org/datanucleus/datanucleus-core/1.1.5/datanucleus-core-1.1.5.jar
>>  /home/instalador/.m2/repository/org/datanucleus/datanucleus-rdbms/1.1.5/datanucleus-rdbms-1.1.5.jar
>>  /home/instalador/.m2/repository/javax/transaction/transaction-api/1.1/transaction-api-1.1.jar
>>  /home/instalador/.m2/repository/org/datanucleus/datanucleus-enhancer/1.1.4/datanucleus-enhancer-1.1.4.jar
>>  /home/instalador/.m2/repository/asm/asm/3.3.1/asm-3.3.1.jar
>>  /home/instalador/.m2/repository/org/datanucleus/datanucleus-jpa/1.1.5/datanucleus-jpa-1.1.5.jar
>>  /home/instalador/.m2/repository/org/apache/geronimo/specs/geronimo-jpa_3.0_spec/1.1.1/geronimo-jpa_3.0_spec-1.1.1.jar
>>  /home/instalador/.m2/repository/javax/persistence/persistence-api/1.0/persistence-api-1.0.jar
>>  /home/instalador/.m2/repository/org/codehaus/plexus/plexus-utils/1.2/plexus-utils-1.2.jar
>>  /usr/share/java/maven2-2.0.9.jar
>>  /home/instalador/workspace/monodaipoly/target/classes
>>  /home/instalador/.m2/repository/javax/jdo/jdo2-api/2.3-eb/jdo2-api-2.3-eb.jar
>>  /home/instalador/.m2/repository/com/google/appengine/orm/datanucleus-appengine/1.0.9/datanucleus-appengine-1.0.9.jar
>>  /home/instalador/.m2/repository/com/google/appengine/geronimo-jpa_3.0_spec/1.1.1/geronimo-jpa_3.0_spec-1.1.1.jar
>>  /home/instalador/.m2/repository/com/google/appengine/appengine-api-1.0-sdk/1.5.5/appengine-api-1.0-sdk-1.5.5.jar
>>  /home/instalador/.m2/repository/org/slf4j/slf4j-api/1.6.3/slf4j-api-1.6.3.jar
>>  /home/instalador/.m2/repository/ch/qos/logback/logback-classic/0.9.9/logback-classic-0.9.9.jar
>>  /home/instalador/.m2/repository/ch/qos/logback/logback-core/0.9.9/logback-core-0.9.9.jar
>>  /home/instalador/.m2/repository/com/google/appengine/appengine-api-labs/1.5.5/appengine-api-labs-1.5.5.jar
>>  /home/instalador/.m2/repository/org/springframework/org.springframework.context/3.0.6.RELEASE/org.springframework.context-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/org.springframework.asm/3.0.6.RELEASE/org.springframework.asm-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/org.springframework.aop/3.0.6.RELEASE/org.springframework.aop-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/aopalliance/com.springsource.org.aopalliance/1.0.0/com.springsource.org.aopalliance-1.0.0.jar
>>  /home/instalador/.m2/repository/org/springframework/org.springframework.beans/3.0.6.RELEASE/org.springframework.beans-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/org.springframework.core/3.0.6.RELEASE/org.springframework.core-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/org.springframework.expression/3.0.6.RELEASE/org.springframework.expression-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/org.springframework.web/3.0.6.RELEASE/org.springframework.web-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-expression/3.0.6.RELEASE/spring-expression-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-core/3.0.6.RELEASE/spring-core-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-asm/3.0.6.RELEASE/spring-asm-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-tx/3.0.6.RELEASE/spring-tx-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/aopalliance/aopalliance/1.0/aopalliance-1.0.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-aop/3.0.6.RELEASE/spring-aop-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-beans/3.0.6.RELEASE/spring-beans-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-context/3.0.6.RELEASE/spring-context-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-orm/3.0.6.RELEASE/spring-orm-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-jdbc/3.0.6.RELEASE/spring-jdbc-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-webmvc/3.0.6.RELEASE/spring-webmvc-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-context-support/3.0.6.RELEASE/spring-context-support-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/spring-web/3.0.6.RELEASE/spring-web-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/codehaus/jackson/jackson-core-asl/1.9.0/jackson-core-asl-1.9.0.jar
>>  /home/instalador/.m2/repository/org/codehaus/jackson/jackson-mapper-asl/1.9.0/jackson-mapper-asl-1.9.0.jar
>>  /home/instalador/.m2/repository/org/springframework/security/spring-security-core/3.0.6.RELEASE/spring-security-core-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/aspectj/aspectjrt/1.6.8/aspectjrt-1.6.8.jar
>>  /home/instalador/.m2/repository/org/aspectj/aspectjweaver/1.6.8/aspectjweaver-1.6.8.jar
>>  /home/instalador/.m2/repository/org/springframework/security/spring-security-config/3.0.6.RELEASE/spring-security-config-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/security/spring-security-web/3.0.6.RELEASE/spring-security-web-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/security/spring-security-taglibs/3.0.6.RELEASE/spring-security-taglibs-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/security/spring-security-acl/3.0.6.RELEASE/spring-security-acl-3.0.6.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/webflow/spring-faces/2.3.0.RELEASE/spring-faces-2.3.0.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/webflow/spring-binding/2.3.0.RELEASE/spring-binding-2.3.0.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/webflow/spring-js/2.3.0.RELEASE/spring-js-2.3.0.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/webflow/spring-js-resources/2.3.0.RELEASE/spring-js-resources-2.3.0.RELEASE.jar
>>  /home/instalador/.m2/repository/org/springframework/webflow/spring-webflow/2.3.0.RELEASE/spring-webflow-2.3.0.RELEASE.jar
ENHANCED (PersistenceCapable) : monodaipoly.persistencia.Jugador
ENHANCED (PersistenceCapable) : monodaipoly.persistencia.Usuario
ENHANCED (PersistenceCapable) : monodaipoly.persistencia.Casilla
ENHANCED (PersistenceCapable) : monodaipoly.persistencia.Rol
DataNucleus Enhancer completed with success for 4 classes. Timings : input=190 ms, enhance=165 ms, total=355 ms. Consult the log for full details

[ERROR] --------------------
[ERROR]  Standard error from the DataNucleus tool + org.datanucleus.enhancer.DataNucleusEnhancer :
[ERROR] --------------------
[ERROR] 24-nov-2011 10:19:17 org.datanucleus.enhancer.DataNucleusEnhancer <init>
INFO: DataNucleus Enhancer : Using ClassEnhancer "ASM" for API "JPA"
24-nov-2011 10:19:18 org.datanucleus.plugin.NonManagedPluginRegistry resolveConstraints
INFO: Bundle "org.datanucleus" tiene una dependencia opcional a "org.eclipse.equinox.registry" pero no se puede resolverla
24-nov-2011 10:19:18 org.datanucleus.plugin.NonManagedPluginRegistry resolveConstraints
INFO: Bundle "org.datanucleus" tiene una dependencia opcional a "org.eclipse.core.runtime" pero no se puede resolverla
24-nov-2011 10:19:18 org.datanucleus.enhancer.DataNucleusEnhancer main
INFO: DataNucleus Enhancer (version 1.1.4) : Enhancement of classes
24-nov-2011 10:19:18 org.datanucleus.jpa.metadata.JPAAnnotationReader processClassAnnotations
INFO: La clase "monodaipoly.persistencia.Jugador" tiene anotaciones de JPA y vamos a usarlas.
24-nov-2011 10:19:18 org.datanucleus.jpa.metadata.JPAAnnotationReader processClassAnnotations
INFO: La clase "monodaipoly.persistencia.Usuario" tiene anotaciones de JPA y vamos a usarlas.
24-nov-2011 10:19:18 org.datanucleus.metadata.MetaDataManager loadClasses
INFO: La clase "monodaipoly.persistencia.Caracteristicas" no tiene MetaData o annotaciones.
24-nov-2011 10:19:18 org.datanucleus.jpa.metadata.JPAAnnotationReader processClassAnnotations
INFO: La clase "monodaipoly.persistencia.Casilla" tiene anotaciones de JPA y vamos a usarlas.
24-nov-2011 10:19:18 org.datanucleus.jpa.metadata.JPAAnnotationReader processClassAnnotations
INFO: La clase "monodaipoly.persistencia.Rol" tiene anotaciones de JPA y vamos a usarlas.
24-nov-2011 10:19:18 org.datanucleus.enhancer.asm.ASMClassEnhancer enhance
INFO: Class "monodaipoly.persistencia.Jugador" is already enhanced.
24-nov-2011 10:19:18 org.datanucleus.enhancer.DataNucleusEnhancer addMessage
INFO: ENHANCED (PersistenceCapable) : monodaipoly.persistencia.Jugador
24-nov-2011 10:19:18 org.datanucleus.enhancer.asm.ASMClassEnhancer save
INFO: Updating class file "file:/home/instalador/workspace/monodaipoly/target/classes/monodaipoly/persistencia/Usuario.class" with enhanced definition
24-nov-2011 10:19:18 org.datanucleus.enhancer.DataNucleusEnhancer addMessage
INFO: ENHANCED (PersistenceCapable) : monodaipoly.persistencia.Usuario
24-nov-2011 10:19:18 org.datanucleus.enhancer.asm.ASMClassEnhancer enhance
INFO: Class "monodaipoly.persistencia.Casilla" is already enhanced.
24-nov-2011 10:19:18 org.datanucleus.enhancer.DataNucleusEnhancer addMessage
INFO: ENHANCED (PersistenceCapable) : monodaipoly.persistencia.Casilla
24-nov-2011 10:19:18 org.datanucleus.enhancer.asm.ASMClassEnhancer enhance
INFO: Class "monodaipoly.persistencia.Rol" is already enhanced.
24-nov-2011 10:19:18 org.datanucleus.enhancer.DataNucleusEnhancer addMessage
INFO: ENHANCED (PersistenceCapable) : monodaipoly.persistencia.Rol
24-nov-2011 10:19:18 org.datanucleus.enhancer.DataNucleusEnhancer addMessage
INFO: DataNucleus Enhancer completed with success for 4 classes. Timings : input=190 ms, enhance=165 ms, total=355 ms. Consult the log for full details

[ERROR] --------------------
[INFO] [resources:testResources]
[INFO] Using default encoding to copy filtered resources.
[INFO] [compiler:testCompile]
[INFO] Nothing to compile - all classes are up to date
[INFO] [surefire:test]
[INFO] Surefire report directory: /home/instalador/workspace/monodaipoly/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
There are no tests to run.

Results :

Tests run: 0, Failures: 0, Errors: 0, Skipped: 0

[INFO] [war:war]
[INFO] Packaging webapp
[INFO] Assembling webapp [monodaipoly] in [/home/instalador/workspace/monodaipoly/target/monodaipoly-1.0-SNAPSHOT]
[INFO] Processing war project
[INFO] Copying webapp webResources [/home/instalador/workspace/monodaipoly/src/main/webapp] to [/home/instalador/workspace/monodaipoly/target/monodaipoly-1.0-SNAPSHOT]
[INFO] Copying webapp resources [/home/instalador/workspace/monodaipoly/src/main/webapp]
[INFO] Webapp assembled in [382 msecs]
[INFO] Building war: /home/instalador/workspace/monodaipoly/target/monodaipoly-1.0-SNAPSHOT.war
[INFO] WEB-INF/web.xml already added, skipping
[INFO] [gae:run]
24-nov-2011 10:19:39 com.google.appengine.tools.info.RemoteVersionFactory getVersion
INFO: Unable to access https://appengine.google.com/api/updatecheck?runtime=java&release=1.5.5&timestamp=1318036530&api_versions=['1.0']
java.net.UnknownHostException: appengine.google.com
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:177)
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:366)
	at java.net.Socket.connect(Socket.java:519)
	at com.sun.net.ssl.internal.ssl.SSLSocketImpl.connect(SSLSocketImpl.java:550)
	at com.sun.net.ssl.internal.ssl.BaseSSLSocketImpl.connect(BaseSSLSocketImpl.java:141)
	at sun.net.NetworkClient.doConnect(NetworkClient.java:163)
	at sun.net.www.http.HttpClient.openServer(HttpClient.java:394)
	at sun.net.www.http.HttpClient.openServer(HttpClient.java:529)
	at sun.net.www.protocol.https.HttpsClient.<init>(HttpsClient.java:272)
	at sun.net.www.protocol.https.HttpsClient.New(HttpsClient.java:329)
	at sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.getNewHttpClient(AbstractDelegateHttpsURLConnection.java:172)
	at sun.net.www.protocol.http.HttpURLConnection.plainConnect(HttpURLConnection.java:793)
	at sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.connect(AbstractDelegateHttpsURLConnection.java:158)
	at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1041)
	at sun.net.www.protocol.https.HttpsURLConnectionImpl.getInputStream(HttpsURLConnectionImpl.java:234)
	at java.net.URL.openStream(URL.java:1009)
	at com.google.appengine.tools.info.RemoteVersionFactory.getVersion(RemoteVersionFactory.java:76)
	at com.google.appengine.tools.info.UpdateCheck.checkForUpdates(UpdateCheck.java:99)
	at com.google.appengine.tools.info.UpdateCheck.doNagScreen(UpdateCheck.java:174)
	at com.google.appengine.tools.info.UpdateCheck.maybePrintNagScreen(UpdateCheck.java:142)
	at com.google.appengine.tools.development.DevAppServerMain$StartAction.apply(DevAppServerMain.java:150)
	at com.google.appengine.tools.util.Parser$ParseResult.applyArgs(Parser.java:48)
	at com.google.appengine.tools.development.DevAppServerMain.<init>(DevAppServerMain.java:113)
	at com.google.appengine.tools.development.DevAppServerMain.main(DevAppServerMain.java:89)
24-nov-2011 10:19:39 com.google.apphosting.utils.jetty.JettyLogger info
INFO: Logging to JettyLogger(null) via com.google.apphosting.utils.jetty.JettyLogger
24-nov-2011 10:19:39 com.google.apphosting.utils.config.AppEngineWebXmlReader readAppEngineWebXml
INFO: Successfully processed /home/instalador/workspace/monodaipoly/target/monodaipoly-1.0-SNAPSHOT/WEB-INF/appengine-web.xml
24-nov-2011 10:19:39 com.google.apphosting.utils.config.AbstractConfigXmlReader readConfigXml
INFO: Successfully processed /home/instalador/workspace/monodaipoly/target/monodaipoly-1.0-SNAPSHOT/WEB-INF/web.xml
24-nov-2011 9:19:43 org.springframework.web.context.ContextLoader initWebApplicationContext
GRAVE: Context initialization failed
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.security.authentication.dao.DaoAuthenticationProvider#0': Cannot resolve reference to bean 'usuarioServicio' while setting bean property 'userDetailsService'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'usuarioServicio' is defined
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:328)
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary(BeanDefinitionValueResolver.java:106)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues(AbstractAutowireCapableBeanFactory.java:1325)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1086)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:517)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:456)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:293)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:290)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:192)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:585)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:895)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:425)
	at org.springframework.web.context.ContextLoader.createWebApplicationContext(ContextLoader.java:282)
	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:204)
	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:47)
	at org.mortbay.jetty.handler.ContextHandler.startContext(ContextHandler.java:548)
	at org.mortbay.jetty.servlet.Context.startContext(Context.java:136)
	at org.mortbay.jetty.webapp.WebAppContext.startContext(WebAppContext.java:1250)
	at org.mortbay.jetty.handler.ContextHandler.doStart(ContextHandler.java:517)
	at org.mortbay.jetty.webapp.WebAppContext.doStart(WebAppContext.java:467)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at org.mortbay.jetty.handler.HandlerWrapper.doStart(HandlerWrapper.java:130)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at org.mortbay.jetty.handler.HandlerWrapper.doStart(HandlerWrapper.java:130)
	at org.mortbay.jetty.Server.doStart(Server.java:224)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at com.google.appengine.tools.development.JettyContainerService.startContainer(JettyContainerService.java:186)
	at com.google.appengine.tools.development.AbstractContainerService.startup(AbstractContainerService.java:182)
	at com.google.appengine.tools.development.DevAppServerImpl.start(DevAppServerImpl.java:172)
	at com.google.appengine.tools.development.DevAppServerMain$StartAction.apply(DevAppServerMain.java:164)
	at com.google.appengine.tools.util.Parser$ParseResult.applyArgs(Parser.java:48)
	at com.google.appengine.tools.development.DevAppServerMain.<init>(DevAppServerMain.java:113)
	at com.google.appengine.tools.development.DevAppServerMain.main(DevAppServerMain.java:89)
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'usuarioServicio' is defined
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:529)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1094)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:276)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:192)
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:322)
	... 33 more
24-nov-2011 9:19:43 com.google.apphosting.utils.jetty.JettyLogger warn
ADVERTENCIA: Failed startup of context com.google.apphosting.utils.jetty.DevAppEngineWebAppContext@19f03d7{/,/home/instalador/workspace/monodaipoly/target/monodaipoly-1.0-SNAPSHOT}
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.security.authentication.dao.DaoAuthenticationProvider#0': Cannot resolve reference to bean 'usuarioServicio' while setting bean property 'userDetailsService'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'usuarioServicio' is defined
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:328)
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary(BeanDefinitionValueResolver.java:106)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues(AbstractAutowireCapableBeanFactory.java:1325)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1086)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:517)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:456)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:293)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:290)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:192)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:585)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:895)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:425)
	at org.springframework.web.context.ContextLoader.createWebApplicationContext(ContextLoader.java:282)
	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:204)
	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:47)
	at org.mortbay.jetty.handler.ContextHandler.startContext(ContextHandler.java:548)
	at org.mortbay.jetty.servlet.Context.startContext(Context.java:136)
	at org.mortbay.jetty.webapp.WebAppContext.startContext(WebAppContext.java:1250)
	at org.mortbay.jetty.handler.ContextHandler.doStart(ContextHandler.java:517)
	at org.mortbay.jetty.webapp.WebAppContext.doStart(WebAppContext.java:467)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at org.mortbay.jetty.handler.HandlerWrapper.doStart(HandlerWrapper.java:130)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at org.mortbay.jetty.handler.HandlerWrapper.doStart(HandlerWrapper.java:130)
	at org.mortbay.jetty.Server.doStart(Server.java:224)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at com.google.appengine.tools.development.JettyContainerService.startContainer(JettyContainerService.java:186)
	at com.google.appengine.tools.development.AbstractContainerService.startup(AbstractContainerService.java:182)
	at com.google.appengine.tools.development.DevAppServerImpl.start(DevAppServerImpl.java:172)
	at com.google.appengine.tools.development.DevAppServerMain$StartAction.apply(DevAppServerMain.java:164)
	at com.google.appengine.tools.util.Parser$ParseResult.applyArgs(Parser.java:48)
	at com.google.appengine.tools.development.DevAppServerMain.<init>(DevAppServerMain.java:113)
	at com.google.appengine.tools.development.DevAppServerMain.main(DevAppServerMain.java:89)
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'usuarioServicio' is defined
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:529)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1094)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:276)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:192)
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:322)
	... 33 more
24-nov-2011 9:19:43 com.google.apphosting.utils.jetty.JettyLogger warn
ADVERTENCIA: Nested in org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'org.springframework.security.authentication.dao.DaoAuthenticationProvider#0': Cannot resolve reference to bean 'usuarioServicio' while setting bean property 'userDetailsService'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'usuarioServicio' is defined:
org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'usuarioServicio' is defined
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:529)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1094)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:276)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:192)
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveReference(BeanDefinitionValueResolver.java:322)
	at org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary(BeanDefinitionValueResolver.java:106)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyPropertyValues(AbstractAutowireCapableBeanFactory.java:1325)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1086)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:517)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:456)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:293)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:290)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:192)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:585)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:895)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:425)
	at org.springframework.web.context.ContextLoader.createWebApplicationContext(ContextLoader.java:282)
	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:204)
	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:47)
	at org.mortbay.jetty.handler.ContextHandler.startContext(ContextHandler.java:548)
	at org.mortbay.jetty.servlet.Context.startContext(Context.java:136)
	at org.mortbay.jetty.webapp.WebAppContext.startContext(WebAppContext.java:1250)
	at org.mortbay.jetty.handler.ContextHandler.doStart(ContextHandler.java:517)
	at org.mortbay.jetty.webapp.WebAppContext.doStart(WebAppContext.java:467)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at org.mortbay.jetty.handler.HandlerWrapper.doStart(HandlerWrapper.java:130)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at org.mortbay.jetty.handler.HandlerWrapper.doStart(HandlerWrapper.java:130)
	at org.mortbay.jetty.Server.doStart(Server.java:224)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at com.google.appengine.tools.development.JettyContainerService.startContainer(JettyContainerService.java:186)
	at com.google.appengine.tools.development.AbstractContainerService.startup(AbstractContainerService.java:182)
	at com.google.appengine.tools.development.DevAppServerImpl.start(DevAppServerImpl.java:172)
	at com.google.appengine.tools.development.DevAppServerMain$StartAction.apply(DevAppServerMain.java:164)
	at com.google.appengine.tools.util.Parser$ParseResult.applyArgs(Parser.java:48)
	at com.google.appengine.tools.development.DevAppServerMain.<init>(DevAppServerMain.java:113)
	at com.google.appengine.tools.development.DevAppServerMain.main(DevAppServerMain.java:89)
24-nov-2011 9:19:43 com.google.appengine.tools.development.DevAppServerImpl start
INFO: The server is running at http://localhost:8888/
