# Pet House Admin

## Para executar a API do projeto recomendamos:

### [Java 1.8](http://www.oracle.com)

### [Maven](https://maven.apache.org/)

### [MySQL](https://maven.apache.org/)

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

## Para executar a UI do projeto recomendamos

### [Node 12+](https://nodejs.org/pt-br/download/)

### [Angular 9+ CLI ](https://cli.angular.io/)


## Clone o repositório

```
git clone https://github.com/andretecnologia/pet-house-admin.git
```

```
cd pet-house-admin/ui
```

```
npm install -g @angular/cli
```

```
npm i ng2-search-filter --save
```

```
npm i angular2-text-mask --save
```

```
npm i
```

```
ng serve
```

### Production

```
ng build --base-href /medidas --deploy-url /medidas/ --prod
```

## Git Ignore

* _My gitignore is under each directory_

## Gerenciador de dependências

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [GitHub](http://github.com) for versioning.

## Authors

* Created by - **André Rosa** - [Git Hub](https://github.com/andretecnologia)
* Contact me -  [Website](http://andretecnologia.com.br) | [Email](enegocios.andre@gmail.com)




