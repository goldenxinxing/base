FROM ampregistry:5000/sng-biz-base-alpine:2.0.1

ENV TZ "Asia/Bangkok"
ENV LANG th_TH.UTF-8
ENV LC_ALL th_TH.UTF-8
ENV LANGUAGE th_TH:th

WORKDIR $PRO_PATH

ADD example-service.jar $PRO_PATH

CMD java -Xms2g -Xmx2g -Xmn1g -server -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -XX:+CMSClassUnloadingEnabled -XX:+PrintGCTimeStamps -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseCMSInitiatingOccupancyOnly -jar example-service.jar --yourPassword=gxx123456