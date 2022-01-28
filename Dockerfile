FROM openjdk

COPY build/libs/getSetKeyVal* /app/getSetKeyVal.jar

RUN groupadd keyval --gid 1000 && useradd -M keyval --uid 1000 --gid 1000 && chmod +x /app/keyval.jar && chown -R keyval:keyval /app

WORKDIR /app

USER keyval

EXPOSE 8080

CMD ["java", "-jar", "keyval.jar"]