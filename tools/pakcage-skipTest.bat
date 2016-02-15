@echo off
echo [INFO] build and install modules.
cd ..\
call mvn clean package -Dmaven.test.skip=true 
pause
