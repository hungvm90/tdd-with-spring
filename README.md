TDD project

#run test:
mvn clean install

#code analytics with sonarqube
```mvn clean verify sonar:sonar -Dsonar.projectKey=tdd -Dsonar.host.url=http://localhost:9000 -Dsonar.login=32b9f1ae0e07af24c903fcb1cd3f4b7ba8a6ba67```