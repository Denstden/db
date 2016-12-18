@echo off
SET DEVELOPMENT_HOME=D:\Univ\5\tcct\db

cd %DEVELOPMENT_HOME%\web-jsp\
call mvn clean tomcat7:run