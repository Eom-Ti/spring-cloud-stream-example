# Spring Cloud Stream Example Project
이 repository는 Spring Cloud Strea을 활용해 코인 거래소의 Candle 현황을 조회하여 Proudce, Consume 하는 간단한 프로젝트 입니다.
Srping Cloud Stream의 기능을 실험해 볼 수 있도록 구성한 간단한 예제 프로젝트입니다. 해당 셈플에 사용되는 메시징 프레임 워크는 `Kafka` 와 `RabbitMQ`를 사용합니다.
각 메시징 프레임워크에 따라 Docker Compose File을 별도 추가하였으며, 초기 프로젝트 실행시 이미지 Pull 작업 및 Container 생성 작업으로 조금의 시간이 소요될 수 있습니다.

# Project
## coin-functions
consumer, transformer-server, producer 에서 사용할 수 있는 함수, pojo 형태의 객체를 가지고있는 모듈입니다.
## consumer
Spring Boot Application으로 Candle 데이터 객체를 수신하고, 기존의 데이터는 캐시를 통해 관리하며, 상승/하락 의 정보를 기록하는 역할을 수행합니다.
## producer
Spring Boot Application으로 코인의 현재 거래 현황(Candle)을 거래소로 부터 조회하여 String 데이터를 지정된 대상(`Kafka` or `RabbigMq`)으로 전송하는 역할을 수행합니다.
## transformer-server
Srping Boot Application으로 String 데이터를 수신하여 코인 Candle 데이터 객체로 변환하여 메시지를 발행하는 역할을 수행합니다.
