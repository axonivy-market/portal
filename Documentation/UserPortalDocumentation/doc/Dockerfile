FROM python:alpine

ADD requirements.txt requirements.txt

RUN apk add --update --no-cache make && \
    pip3 install -r requirements.txt

WORKDIR /ivy-core/doc

USER 1000:1000
