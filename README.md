# CM1007-journal-journalService

## Creating joint network 
docker network create journalNet

## Creating database
docker run -d --name journal-db --network=journalNet -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=journalDB -p 3307:3306 mysql:latest

## Building and running image
docker build -t journal_service .

docker run -p 8080:8080 --net journalNet -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=journalDB -e MYSQL_USER=root -e MYSQL_PASSWORD=password -e MYSQL_URL=jdbc:mysql://journal-db/journalDB -e ACCOUNT_SERVICE_URL=http://account_service_con:8081 --name journal_service_con journal_service
