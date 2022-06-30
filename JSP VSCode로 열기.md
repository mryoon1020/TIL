# JSP VSCode로 열기

### 방법

- JDK 설치

  - https://www.oracle.com/java/technologies/
  - JAVA 설치와 동일
  - JAVA 설치되어있으면 설치할 필요없음

- Tomcat설치

  - http://tomcat.apache.org/
  - zip파일로 받을 것
  - 경로는 찾기 편한 위치로 할 것
  - 설치경로에 한글이 없는 것이 좋음
  - 포트 수정(톰캣설치 폴더에서 해당경로로 들어가면 있음)

  > tomcat-10/conf/server.xml (하기 내용 부분 찾아서 추가)

  ```xml
  <Connector port="8000" protocol="HTTP/1.1" 
                 connectionTimeout="20000" 
                 redirectPort="8443" URIEncoding="UTF-8"/> 
  ```

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    (the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at
  
        http://www.apache.org/licenses/LICENSE-2.0
  
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
  -->
  <!-- Note:  A "Server" is not itself a "Container", so you may not
       define subcomponents such as "Valves" at this level.
       Documentation at /docs/config/server.html
   -->
  <Server port="8005" shutdown="SHUTDOWN">
    <Listener className="org.apache.catalina.startup.VersionLoggerListener" />
    <!-- Security listener. Documentation at /docs/config/listeners.html
    <Listener className="org.apache.catalina.security.SecurityListener" />
    -->
    <!-- APR library loader. Documentation at /docs/apr.html -->
    <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
    <!-- Prevent memory leaks due to use of particular java/javax APIs-->
    <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
    <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
    <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />
  
    <!-- Global JNDI resources
         Documentation at /docs/jndi-resources-howto.html
    -->
    <GlobalNamingResources>
      <!-- Editable user database that can also be used by
           UserDatabaseRealm to authenticate users
      -->
      <Resource name="UserDatabase" auth="Container"
                type="org.apache.catalina.UserDatabase"
                description="User database that can be updated and saved"
                factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
                pathname="conf/tomcat-users.xml" />
    </GlobalNamingResources>
  
    <!-- A "Service" is a collection of one or more "Connectors" that share
         a single "Container" Note:  A "Service" is not itself a "Container",
         so you may not define subcomponents such as "Valves" at this level.
         Documentation at /docs/config/service.html
     -->
    <Service name="Catalina">
  
      <!--The connectors can use a shared executor, you can define one or more named thread pools-->
      <!--
      <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
          maxThreads="150" minSpareThreads="4"/>
      -->
  
  
      <!-- A "Connector" represents an endpoint by which requests are received
           and responses are returned. Documentation at :
           HTTP Connector: /docs/config/http.html
           AJP  Connector: /docs/config/ajp.html
           Define a non-SSL/TLS HTTP/1.1 Connector on port 8080
      -->
      <Connector port="8000" protocol="HTTP/1.1"
                 connectionTimeout="20000"
                 redirectPort="8443" URIEncoding="UTF-8"/>
      <!-- A "Connector" using the shared thread pool-->
      <!--
      <Connector executor="tomcatThreadPool"
                 port="8080" protocol="HTTP/1.1"
                 connectionTimeout="20000"
                 redirectPort="8443" />
      -->
      <!-- Define an SSL/TLS HTTP/1.1 Connector on port 8443 with HTTP/2
           This connector uses the NIO implementation. The default
           SSLImplementation will depend on the presence of the APR/native
           library and the useOpenSSL attribute of the AprLifecycleListener.
           Either JSSE or OpenSSL style configuration may be used regardless of
           the SSLImplementation selected. JSSE style configuration is used below.
      -->
      <!--
      <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
                 maxThreads="150" SSLEnabled="true">
          <UpgradeProtocol className="org.apache.coyote.http2.Http2Protocol" />
          <SSLHostConfig>
              <Certificate certificateKeystoreFile="conf/localhost-rsa.jks"
                           type="RSA" />
          </SSLHostConfig>
      </Connector>
      -->
  
      <!-- Define an AJP 1.3 Connector on port 8009 -->
      <!--
      <Connector protocol="AJP/1.3"
                 address="::1"
                 port="8009"
                 redirectPort="8443" />
      -->
  
      <!-- An Engine represents the entry point (within Catalina) that processes
           every request.  The Engine implementation for Tomcat stand alone
           analyzes the HTTP headers included with the request, and passes them
           on to the appropriate Host (virtual host).
           Documentation at /docs/config/engine.html -->
  
      <!-- You should set jvmRoute to support load-balancing via AJP ie :
      <Engine name="Catalina" defaultHost="localhost" jvmRoute="jvm1">
      -->
      <Engine name="Catalina" defaultHost="localhost">
  
        <!--For clustering, please take a look at documentation at:
            /docs/cluster-howto.html  (simple how to)
            /docs/config/cluster.html (reference documentation) -->
        <!--
        <Cluster className="org.apache.catalina.ha.tcp.SimpleTcpCluster"/>
        -->
  
        <!-- Use the LockOutRealm to prevent attempts to guess user passwords
             via a brute-force attack -->
        <Realm className="org.apache.catalina.realm.LockOutRealm">
          <!-- This Realm uses the UserDatabase configured in the global JNDI
               resources under the key "UserDatabase".  Any edits
               that are performed against this UserDatabase are immediately
               available for use by the Realm.  -->
          <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
                 resourceName="UserDatabase"/>
        </Realm>
  
        <Host name="localhost"  appBase="webapps"
              unpackWARs="true" autoDeploy="true">
  
          <!-- SingleSignOn valve, share authentication between web applications
               Documentation at: /docs/config/valve.html -->
          <!--
          <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
          -->
  
          <!-- Access log processes all example.
               Documentation at: /docs/config/valve.html
               Note: The pattern used is equivalent to using pattern="common" -->
          <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
                 prefix="localhost_access_log" suffix=".txt"
                 pattern="%h %l %u %t &quot;%r&quot; %s %b" />
  
        </Host>
      </Engine>
    </Service>
  </Server>
  ```

- VS Code 설정

  - 하기 확장프로그램 설치(extensions 클릭후 검색)

    - Debugger for java (Microsoft)
    - Tomcat for java (Wei Shen)

  - 하단에 TOMCAT SERVERS 생성확인 및 설정

    - \+ 버튼 클릭 및 톰캣 설치폴더 선택

    - 추가 된 톰캣서버에서 마우스 우클릭

    - Open Server Configuration 클릭

    - xml파일 수정(수정시 경로에 한글 없는 것이 좋음)

      `<Host name="localhost" appBase="webapps" unpackWARs="true" autoDeploy="true">` 

      하단

       ` <Context path="" docBase="jsp파일이 위치한 폴더 경로"></Context>` 추가 및 저장

- WebBrowser에서 실행하기

  - VS Code => TOMCAT SERVERS => tomcat-10 우클릭 => start => 녹색불 들어오면 성공
  - Browser => 주소창에 localhost:8000/파일명.jsp 입력 => jsp 실행 성공



