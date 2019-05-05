# XY-Inc

**XY Inc.** é uma empresa especializada na produção de excelentes receptores GPS (Global Positioning System).

O projeto é uma aplicação web para auxiliar as pessoas na localização de ponto de interesse (POIs). Aplicação foi desenvolvida em Java e com framework Spring Boot que ajuda a acelerar e facilitar o desenvolvimento de aplicativos.

A aplicação disponibiliza **3 serviços** para consulta dos pontos de interesse, por proximidade e cadastro de um novo ponto de interesse.

A aplicação utiliza bando de dados [H2](http://www.h2database.com/html/main.html) embutido com alguns pontos de interesse (POIs) cadastrados.

## Começando

O que você precisará:
* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) ou posterior instalado na sua máquina.

Faça o download do arquivo da aplicação em https://github.com/leandrochp/xy-inc/blob/master/target/xy-inc-0.0.1-SNAPSHOT.jar

Para iniciar a aplicação é necessário usar o comando java -jar passando para o comando o arquivo acima. Por exemplo: java -jar xy-inc-0.0.1-SNAPSHOT.jar

## Visão geral (API POI) 
É um serviço REST simples no qual iremos interagir com o H2 usando o Spring Boot.

A API POI executará algumas operações usando diferentes endpoints. Abaixo estão os detalhes dos endpoints:

* Chamada **GET** para **/pois** exibirá todos os pontos de interesse cadastrados no banco de dados H2.
* Chamada **GET** para **/pois/x/{x}/y/{y}** exibirá os pontos de interesse com proximidade de no máximo 10 metros de distância.
* Chamada **POST** para **/pois** irá inserir um novo ponto de interesse no banco de dados.

## Definição de implementação

### Definindo uma entidade
As operações de CRUD serão através de um objeto POI.

    @Entity(name = "poi")
    public class POI {
      @Id
      @GeneratedValue
      private Long id;
      
      private String nome;
      private Integer x;
      private Integer y;
      
      // getters and setters
    }

### Definindo POIRepository
Como estamos usando o JPA para interagir com o banco de dados, então vamos definir o POIRepository:

    public interface POIRepository extends CrudRepository<POI, Long> {}

### Definindo POIService
O POIService estará expondo métodos que serão chamados do Controller que interage com o repositório:

    @Service
    public class POIService {
    
    	@Autowired
    	POIRepository poiRepository;
    	
    	// max distance
    	final int max = 10;
    	
    	public List<POI> getAllPOIs() {
    		List<POI> pois = new ArrayList<POI>();
    		poiRepository.findAll().forEach(poi -> pois.add(poi));
    		return pois;
    	}
    
    	public List<POI> getPOIsByCoordenates(int x, int y) {	
    		List<POI> pois = new ArrayList<POI>();
    		
            // code calculate square
            
    		return pois;
    	}
    	
    	public void saveOrUpdate(POI poi) {
    		poiRepository.save(poi);
    	}
    }

### Definindo POIController
O POIController disponibilizará métodos que serão chamados por diferentes chamadas de endpoints:

    @RestController
    public class POIController {
    	@Autowired
    	POIService poiService;
    	
    	@GetMapping("/pois")
    	private List<POI> getAllPOIs() {
    		return poiService.getAllPOIs();
    	}
    	
    	@GetMapping("/pois/x/{x}/y/{y}")
    	private List<POI> getPOIsByCoordenates(@PathVariable("x") int x, @PathVariable("y") int y) {
     		return poiService.getPOIsByCoordenates(x, y);
    	}
    	
    	@PostMapping("/pois")
    	private POI saveOrUpdate(@RequestBody POI poi) {
    		poiService.saveOrUpdate(poi);
    		return poi;
    	}
    }

## Testando a API POI
Para testar o serviço REST, vamos usar a ferramenta [POSTMAN](https://www.getpostman.com/), que pode ser integrada ao navegador Chrome facilmente usando a [extensão do navegador](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop).

Certifique-se que a aplicação está em execução. A saída no console deve ser algo como isso. Isso confirma que ele foi iniciado corretamente:
> `INFO 17200 --- [           main] br.com.zup.XYIncApplication              : Started XYIncApplication in 30.326 seconds (JVM running for 31.2)`

### Caso de Teste 1: Criando novo objeto POI
`Abra o POSTMAN`

* Definir tipo de solicitação: POST
* Definir URL: http://localhost:8080/pois
* Definir cabeçalho da solicitação: Content-Type:application/json
* Defina o corpo como: {"nome": "Lan house", "x": 8, "y": 2}
* Clique em "Enviar"

Na resposta, obteremos um objeto POI.

### Caso de Teste 2: Recuperando os objetos POIs
`Abra o POSTMAN`

* Definir tipo de solicitação: GET
* Definir URL: http://localhost:8080/pois
* Definir cabeçalho da solicitação: Content-Type:application/json
* Clique em "Enviar"

Na resposta, obteremos os objetos POIs cadastrados.

### Caso de Teste 3: Recuperando os objetos POIs por proximidade
`Abra o POSTMAN`

* Definir tipo de solicitação: GET
* Definir URL: http://localhost:8080/pois/x/20/y/10
* Definir cabeçalho da solicitação: Content-Type:application/json
* Clique em "Enviar"

Na resposta, obteremos os objetos POIs cadastrados por proximidade de x = 20 e y = 10

> Nota: Os valores de x e y são valores do caso de teste.

## Validando os dados
Para validar os dados no banco de dados H2, acesse via browser [Console H2](http://localhost:8080/h2-console) o console administrativo.

Isso é possível pela configuração no arquivo de propriedades da aplicação:

    spring.datasource.type=com.zaxxer.hikari.HikariDataSource
    spring.datasource.url=jdbc:h2:mem:test
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.jpa.database=h2
    spring.datasource.username=sa
    spring.datasource.password=
    #Enabling H2 Console
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console
    spring.h2.console.settings.trace=false
    spring.h2.console.settings.web-allow-others=false

> Nota: Você pode ver um valor diferente na URL do JDBC, portanto, altere a URL do banco de dados para **jdbc:h2:mem:testdb** na tela de login, pois essa é a URL padrão configurada pelo framework Spring Boot.

## Obtendo o código
* [Código fonte](https://github.com/leandrochp/xy-inc)

O que você precisará:

* Um editor de texto favorito ou IDE
* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) ou posterior

Você também pode importar o código direto para o seu IDE:
* [Spring Tool Suite (STS)](https://spring.io/guides/gs/sts)
* [IntelliJ IDEA](https://spring.io/guides/gs/intellij-idea/)


