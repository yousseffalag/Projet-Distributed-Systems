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
| **`ebank-bot`** | `8058` | Agent IA (Spring AI OpenAI), Client MCP et passerelle conversationnelle |

---

## ⚙️ Configuration & Variables d'Environnement

Un fichier template [`.env.example`](./.env.example) est fourni à la racine :
```properties
OPENAI_API_KEY=sk-proj-your-openai-api-key-here
```

---

## 🚀 Ordre de Démarrage des Services

1. **`discovery-service`** (port 8761)
2. **`customer-service`** (port 8056)
3. **`ebank-service`** (port 8057)
4. **`gateway-service`** (port 9999)
5. **`ebank-bot`** (port 8058)

---

## 🧪 Documentation et Tests des APIs

### 1. Tests avec Client REST (HTTP Client)
Un fichier de requêtes exécutables [`ebank-bot/rest-client-tests.http`](./ebank-bot/rest-client-tests.http) est fourni pour tester directement les endpoints REST :
- **GET /chat** : `GET http://localhost:8058/chat?question=Bonjour`
- **POST /chat** : Envoi de questions complexes en JSON vers l'Agent IA.

Une suite de tests d'intégration automatisés est également disponible dans `EbankChatbotControllerTest.java`.
