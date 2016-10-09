@echo off
SET DEVELOPMENT_HOME=D:\Univ\5\tcct\db

cd %DEVELOPMENT_HOME%\desktop-db\
call mvn clean install
cd %DEVELOPMENT_HOME%\desktop-db\target\
call java -Xmx1024m -jar %~dp0\desktop-db\target\desktop-db-1.0-SNAPSHOT.jar

echo on
pause