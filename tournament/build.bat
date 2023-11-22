REM Builds application and docker image using Dockerfile and tags it based on org.opencontainers.image.version label in
REM Dockerfile.

REM Script main function. Builds application and docker image using Dockerfile and tags it based on
REM org.opencontainers.image.version label in Dockerfile.

set "JAVA_HOME=C:\Program Files\Java\jdk-20" 

call mvn clean verify -U

REM Pobierz wartości z Dockerfile
for /f "tokens=2 delims==" %%a in ('findstr /i "org.opencontainers.image.title" Dockerfile') do set title=%%a
for /f "tokens=2 delims==" %%b in ('findstr /i "org.opencontainers.image.version" Dockerfile') do set version=%%b

REM Buduj obraz Dockerowy
docker build ^
  --label "org.opencontainers.image.created=%date:~-4%-%date:~3,2%-%date:~0,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%%time:~9,2%:z%" ^
  --label "org.opencontainers.image.ref.name=%GIT_COMMIT%" ^
  --label "org.opencontainers.image.revision=%GIT_COMMIT%" ^
  -t "%title%:%version%" .

REM Zakończ
exit /b 0