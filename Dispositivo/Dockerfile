FROM openjdk

RUN mkdir/dispositivo
ENV DEVICE=Dispositivo.jar
COPY ${DEVICE} /dispositivo/

EXPOSE 9800

ENTRYPOINT ["java","-jar","/dispositivo/DEVICE"]