docker-compose down

APP_NAME_API=point-api

./gradlew $APP_NAME_API:clean
./gradlew $APP_NAME_API:bootJar

docker build --build-arg \
JAR_FILE=$APP_NAME_API/build/libs/\*.jar \
-f Dockerfile \
-t $APP_NAME_API/latest .

export APP_NAME_API=$APP_NAME_API

docker-compose up -d