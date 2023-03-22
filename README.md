# Starter
## Introduction
starter는 책, 공연, 영화, 전시회 등을 관람하고 후기를 기록하며 친구들과 공유하는 애플리케이션입니다.

## 기술스택
### ci/cd
- NaverCloud
- Github Action
- Docker
### Application
- Java 11
- SpringBoot 2.7
- JPA

## 로컬에서 프로젝트 실행 방법
1. 도커를 통한 mysql 컨테이너 실행
```shell
docker pull mysql
docker images
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=test -d -p 33061:3306 mysql
```
2. 컨테이너 접속 후 데이터베이스 접근
```shell
docker exec -it mysql-container bash
```
```shell
root@234ddfooblahblah:/# mysql -u root -p
Enter password: {패스워드 입력}  # 여기선 test
```
3. 프로젝트를 실행함으로써 hibernate를 통한 테이블 자동 생성
```shell
./gradlew bootRun
```
4. 이후 초기 데이터 삽입
```sql
use starter;

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
