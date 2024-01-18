FROM alpine:latest
RUN apk add --no-cache openjdk21
RUN apk add --no-cache maven
RUN apk add --no-cache curl bash
RUN curl -L https://repository.savantbuild.org/org/savantbuild/savant-core/1.0.0/savant-1.0.0.tar.gz -o /tmp/savant.tar.gz \
    && tar -xzf /tmp/savant.tar.gz -C /opt/ \
    && rm /tmp/savant.tar.gz
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk
CMD ["bash"]