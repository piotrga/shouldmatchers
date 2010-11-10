JAVA_OPTS="-Xmx512M -noverify"
exec java $JAVA_OPTS -jar `dirname $0`/sbt-launch.jar "$@"