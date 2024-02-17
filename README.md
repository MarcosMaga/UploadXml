# UploadXml

Este projeto tem o objetivo de receber arquivos XML de Notas Fiscais, processa-los e armazenar os dados em um banco de dados PostgreSQL e permitir que o usuário faça o download deste arquivo novamente.

## Tecnologias
<ul>
  <li><b>Backend: </b> Java Spring Boot</li>
  <li><b>Frontend: </b> React.js</li>
  <li><b>Banco de dados: </b> PostgreSQL</li>  
  <li><b>Virtualização: </b> Docker</li>
</ul>

# API

API feita usando Java Spring Boot e arquiteturado em RESTFul.

## Rotas e Endpoints

A API possui 2 rotas, uma para envio de arquivos XML e outra para receber os arquivos já cadastrados.

### `/api/invoice` Métdodo: GET

Retorna Notas Fiscais cadastradas.

**Parâmetros:**
- `page` (não obrigatório, tipo: int): Página desejada.
- `size` (não obrigatório, tipo: int): Quantidade de itens por página.

**Exemplo de soliticatação:**
`/api/invoice?page=0&size=1`

**Exemplo de resposta:**
```json
{
	"content": [
		{
			"id": "NFe35240100000000000001550010000117371016243501",
			"dhEmi": "2024-01-03T13:50:59",
			"nNF": 11761,
			"cUF": 35,
			"emitCnpj": "1351231907000167",
			"xFant": "EMPRESA EMISSORA",
			"destCnpj": "41116198000120",
			"xNome": "EMPRESA COMPRADORA 01",
			"vTotTrib": 733.85,
			"vNF": 21230.00,
			"binary": {
				"id": 1,
				"fileXml": "{Binario do XML}"
      }
		}
	],
	"pageable": {
		"pageNumber": 0,
		"pageSize": 1,
		"sort": {
			"empty": true,
			"sorted": false,
			"unsorted": true
		},
		"offset": 0,
		"paged": true,
		"unpaged": false
	},
	"totalPages": 9,
	"totalElements": 9,
	"last": false,
	"size": 1,
	"number": 0,
	"sort": {
		"empty": true,
		"sorted": false,
		"unsorted": true
	},
	"numberOfElements": 1,
	"first": true,
	"empty": false
}
```

### `/api/invoice/upload` Métdodo: POST Multipart Form

Rota responsavél por receber 1 ou mais arquivos XML de NF e armazena-los no banco de dados. Retorna quantos arquivos foram processados com sucesso, o total de arquivos e informações de erros.

**Parâmetros:**
- `files` (obrigatório, tipo: MultipartFile[]): Arquivos XML para processar.

**Exemplo de resposta:**
```json
{
	"success": 5,
	"total": 6,
	"invoicesStatus": [
		{
			"fileName": "file_teste_01.xml",
			"success": true,
			"error": null
		},
		{
			"fileName": "file_teste_02.xml",
			"success": true,
			"error": null
		},
		{
			"fileName": "file_teste_03.xml",
			"success": true,
			"error": null
		},
		{
			"fileName": "file_teste_04.xml",
			"success": true,
			"error": null
		},
		{
			"fileName": "file_teste_08.xml",
			"success": true,
			"error": null
		},
		{
			"fileName": "file_teste_10.xml",
			"success": false,
			"error": "Duplicated XML"
		}
	]
}
```
## Logs

A API possui um arquivo de logs que fica localizado em `src/logs/app.log` onde fica armazenado erros e avisos ocasionados. 

# Execução e Preparo
* O Projeto se encontra totalmente (Backend, Frontend e Banco de Dados) virtualizado utilizando microserviços com Docker.
* Para executar o projeto de maneira rápida e simples basta ter o Docker Engine e o Docker Compose instalado na máquina host.
* Após isso entre na pasta do Projeto e executar o comando: `docker-compose up -d`.
* A API rodará na porta 8080 e o Frontend na porta 80. (Verifique a disponibilidade destas portas).
* Para acessar o Front entre na seguinte url: `http://localhost/`.
* Os conteiners estão expondo a porta 8080 para a máquina host para que seja possível acessar a API por um aplicativo de teste de Endpoints.






















