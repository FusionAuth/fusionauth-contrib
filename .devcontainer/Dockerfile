FROM --platform=linux/amd64 ubuntu:latest

RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    maven \
    curl \
    bash \
    && rm -rf /var/lib/apt/lists/*
RUN curl -L https://repository.savantbuild.org/org/savantbuild/savant-core/1.0.0/savant-1.0.0.tar.gz -o /tmp/savant.tar.gz \
    && tar -xzf /tmp/savant.tar.gz -C /opt/ \
    && rm /tmp/savant.tar.gz
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
CMD ["bash"]