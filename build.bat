REM Builds all applications and all docker images using Dockerfile and tags it based on org.opencontainers.image.version
REM label in Dockerfile.

REM Script main function. Builds all applications and all docker images using Dockerfile and tags it based on
REM org.opencontainers.image.version label in Dockerfile.

cd .\tournament\
call .\build.bat
cd ..

cd .\participant\
call .\build.bat
cd ..

cd .\gateway\
call .\build.bat
cd ..

cd .\js\
call .\build.bat
cd ..

cd .\eureka\
call .\build.bat
cd ..

cd .\config\
call .\build.bat
cd ..
