echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push $DOCKER_USERNAME/eureka-service:$BUILD_NAME
docker push $DOCKER_USERNAME/authentication-service:$BUILD_NAME
docker push $DOCKER_USERNAME/config-service:$BUILD_NAME
docker push $DOCKER_USERNAME/zuul-service:$BUILD_NAME
