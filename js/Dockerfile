FROM nginx:1.23.3

LABEL org.opencontainers.image.title="angular"
LABEL org.opencontainers.image.authors="Adam Ślusarek"
LABEL org.opencontainers.image.source=""
LABEL org.opencontainers.image.url=""
LABEL org.opencontainers.image.vendor="Gdańsk University of Technology"
LABEL org.opencontainers.image.version="1.0.0-snapshot"
LABEL org.opencontainers.image.description="AUI lab project"
LABEL org.opencontainers.image.licenses="MIT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="1.0.0-snapshot"

ENV API_URL http://localhost:8080/api

EXPOSE 80

ADD dist/js /usr/share/nginx/html/
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
