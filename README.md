# Spring Cloud Stream Example Project
이 repository는 Spring Cloud Strea을 활용해 코인 거래소의 Candle 현황을 조회하여 Proudce, Consume 하는 간단한 프로젝트 입니다.
Srping Cloud Stream의 기능을 실험해 볼 수 있도록 구성한 간단한 예제 프로젝트입니다. 해당 셈플에 사용되는 메시징 프레임 워크는 `Kafka` 와 `RabbitMQ`를 사용합니다.
각 메시징 프레임워크에 따라 Docker Compose File을 별도 추가하였으며, 초기 프로젝트 실행시 이미지 Pull 작업 및 Container 생성 작업으로 조금의 시간이 소요될 수 있습니다.
