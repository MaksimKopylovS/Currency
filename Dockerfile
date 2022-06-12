FROM java:8-jdk

COPY currency-0.0.1-SNAPSHOT-plain.jar /currency
COPY currency-0.0.1-SNAPSHOT.jar /currency
COPY application.conf /currency

CMD ["java", "-jar", /currency-0.0.1-SNAPSHOT.jar]
