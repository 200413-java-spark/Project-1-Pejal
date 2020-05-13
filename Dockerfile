FROM postgres:10
ENV POSTGRES_USER mydata
ENV POSTGRES_PASSWORD mydata
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432