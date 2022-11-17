# spring-boot-3
crud spring-boot 3.0

### Docker - Redis
docker run -d -ti -v redis-data:/var/lib/redis -p 6379:6379 --name local-redis6-6.2 redis:6.2

### Docker - mysql
docker run -d -ti --name local-mysql-8 -p 3306:3306 -p 33060:33060 -v mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=tester mysql:8

### Docker - rabbitMQ
docker run -d -ti -p 5672:5672 -p 15672:15672 --name local-rabbitmq -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest rabbitmq:3.8-management-alpine
