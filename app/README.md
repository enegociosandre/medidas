# Pet House Admin

## Para executar a API do projeto recomendamos:

### [Java 1.8](http://www.oracle.com)

### [Maven](https://maven.apache.org/)

### [MySQL](https://maven.apache.org/)

#### Vm Options
```
-Dspring.profiles.active=local
```

## Clone o repositório

```
git clone https://github.com/andretecnologia/pet-house-admin.git
```

```
cd pet-house-admin/app
```

```
mvn clean install
```

```
mvn spring-boot:run -Drun.profiles=dev or java -jar -Dspring.profiles.active=dev pet.jar
```

### Production

```
mvn clean package
```
