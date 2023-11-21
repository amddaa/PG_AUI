REM Builds application and docker image using Dockerfile and tags it based on org.opencontainers.image.version label in
REM Dockerfile

REM Script main function. Builds application and docker image using Dockerfile and tags it based on
REM org.opencontainers.image.version label in Dockerfile.

setlocal enabledelayedexpansion

call npm install
call npm run build

REM Get values from Dockerfile
for /f "tokens=2 delims==" %%a in ('findstr /i "org.opencontainers.image.title" Dockerfile') do set title=%%a
for /f "tokens=2 delims==" %%b in ('findstr /i "org.opencontainers.image.version" Dockerfile') do set version=%%b

REM Build the Docker image
docker build ^
  --label "org.opencontainers.image.created=!date /T!T!time::=!" ^
  --label "org.opencontainers.image.ref.name=!git rev-parse HEAD!" ^
  --label "org.opencontainers.image.revision=!git rev-parse HEAD!" ^
  -t "%title%":"%version%" .

endlocal
