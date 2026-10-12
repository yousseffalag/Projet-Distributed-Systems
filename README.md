# Projet : E-Bank Microservices Architecture (Partie 1 & Partie 2)

Projet d'architecture distribuée pour une application bancaire (E-Bank) basé sur **Spring Cloud**, **OpenFeign**, **Resilience4j**, **Spring AI** et le protocole **MCP (Model Context Protocol)**.

Développé par : **Falag Youssef** (`com.falag.youssef`)

---

## 🏛️ Architecture et Microservices

| Service | Port | Description |
| :--- | :--- | :--- |
| **`discovery-service`** | `8761` | Serveur d'enregistrement Netflix Eureka |
| **`customer-service`** | `8056` | Gestion des clients + Serveur MCP Tools |
| **`ebank-service`** | `8057` | Gestion des comptes, OpenFeign, Circuit Breaker + Serveur MCP Tools |
| **`gateway-service`** | `9999` | API Gateway avec routage dynamique |
| **`ebank-bot`** | `8058` | Agent IA (Spring AI OpenAI) + Client MCP pour interagir avec les microservices |

---

## ⚙️ Configuration & Variables d'Environnement

Un fichier template [`.env.example`](./.env.example) est fourni à la racine :
```properties
OPENAI_API_KEY=sk-proj-your-openai-api-key-here
```
Avant de démarrer `ebank-bot`, définissez votre clé d'API OpenAI dans votre environnement :
- Sous PowerShell : `$env:OPENAI_API_KEY="votre_cle_openai"`
- Ou dans un fichier `.env` chargé par votre IDE.

---

## 🚀 Ordre de Démarrage des Services

1. **`discovery-service`** (port 8761)
2. **`customer-service`** (port 8056)
3. **`ebank-service`** (port 8057)
4. **`gateway-service`** (port 9999)
5. **`ebank-bot`** (port 8058)

---

## 🧪 Documentation et Tests des APIs

### 1. APIs Métier (Direct ou via Gateway Port 9999)
- **Clients** : `GET http://localhost:9999/CUSTOMER-SERVICE/customers`
- **Comptes** : `GET http://localhost:9999/EBANK-SERVICE/accounts`

### 2. Chatbot IA via MCP Client (Port 8058)
- **Requête GET** :
  `GET http://localhost:8058/chat?question=Donne moi la liste des clients et leurs soldes`
- **Requête POST** :
  ```http
  POST http://localhost:8058/chat
  Content-Type: application/json

  {
      "question": "Trouve le client avec l'identifiant 1"
  }
  ```
- **Swagger UI du Chatbot** : `http://localhost:8058/swagger-ui.html`

### 3. Collection Postman
Une collection complète de requêtes est fournie dans le fichier [`postman-collection.json`](./postman-collection.json).
