FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=rootpass
ENV MYSQL_DATABASE=loans_MySQL
ENV MYSQL_USER=loans
ENV MYSQL_PASSWORD=loans3

# Copiamos el script SQL inicial
COPY init_db.sql /docker-entrypoint-initdb.d/