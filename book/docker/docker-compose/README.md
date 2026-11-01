# Docker Compose
------
- [Folders](#folders)
- [Files](#files)
- [References](#references)
---
# Folders
_No subfolders_
---
## Files
* [Commands](docker-compose-commands.md)
* [Notes](docker-compose-notes.md)
* [Kafka Compose notes](notes.md)
* [Run 2 MySQL containers on a Docker network](docker-compose-files.md)
* [Pass a Compose file path with -f](custom-file-path-name.md)
* [Connect containers to an existing external network](connect-to-existing-external-network.md)
* [External network Compose file](docker-compose-connect-to-existing-external-network.yml)

[Commands](docker-compose-commands.md) prints the Compose version, names `docker-compose.yml` and `docker-compose.yaml`, and starts the app with `docker-compose up -d`. [Notes](docker-compose-notes.md) uses the same file names and the same start command. Each container is a `services` entry.

[Run 2 MySQL containers on a Docker network](docker-compose-files.md) is a Compose file `version: "3"`. `mysql-1` and `mysql-2` both use `mysql:5`, join `my-network-1` and `my-network-2`, and set `MYSQL_ROOT_PASSWORD=admin` and `MYSQL_DATABASE=database1`. `mysql-1` has `depends_on: mysql-2`. `docker-compose up` and `docker-compose up -d` start them. `docker-compose down` stops the containers and removes them.

[Pass a Compose file path with -f](custom-file-path-name.md) runs `docker compose -f C:\github\java\java-v2\docker-compose\files\docker-compose-1-broker.yml up -d`. A second `-f` file overrides matching fields from the first. Paths in every file are relative to the first `-f` file. `-f -` reads the configuration from stdin. With no `-f`, Compose looks for `compose.yaml` or `docker-compose.yaml` in the working directory and its parents. `COMPOSE_FILE` can hold the path.

[Connect containers to an existing external network](connect-to-existing-external-network.md) attaches `kafkaProducer` and `kafkaStreamSpringBoot3` to `my_network_1` with `external: true`. The Compose file is [docker-compose-connect-to-existing-external-network.yml](docker-compose-connect-to-existing-external-network.yml). `kafkaStreamSpringBoot3` uses `donthuavinashbabu/kafka-stream-spring-boot-3-001:3` and publishes `9001:9000`. `kafkaProducer` uses `donthuavinashbabu/kafka-example-001` and `depends_on` the stream service. Both set `container_name`.

[Kafka Compose notes](notes.md) links three Compose files under `files/`: `docker-compose-kafka-1-broker.yml`, `docker-compose-kafka-2-brokers.yml`, and `docker-compose-kafka-1-broker-with-network.yml`. Those files are absent from this folder. The same note starts each stack with `docker-compose up -d`, opens Kafka UI at `http://localhost:8080/`, and stops it with `docker-compose down`.
---
## References
* [Docker Compose](https://docs.docker.com/compose/)
* [Compose file reference](https://docs.docker.com/compose/compose-file/)
* [Control startup and shutdown order in Compose](https://docs.docker.com/compose/startup-order/)
* [docker compose CLI](https://docs.docker.com/reference/cli/docker/compose/)
