FROM openjdk:21-jdk

# 타임존 설정 (Debian/Ubuntu 기반 이미지 예시)
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && echo "Asia/Seoul" > /etc/timezone

ENV project="ezenFirst"

RUN mkdir /${project}

COPY build/libs/*.jar /${project}/${project}.jar

ENTRYPOINT ["java", "-jar", "/ezenFirst/ezenFirst.jar"]